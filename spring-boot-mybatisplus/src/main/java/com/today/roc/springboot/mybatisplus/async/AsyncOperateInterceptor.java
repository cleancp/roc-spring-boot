package com.today.roc.springboot.mybatisplus.async;

import com.baomidou.mybatisplus.core.executor.MybatisBatchExecutor;
import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.today.roc.springboot.mybatisplus.entity.BaseEntity;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.StatementType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Statement;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

/**
 * description：异步操作拦截器
 * 当method为prepare时,批量语句执行可能只会拦截一次,所以设置在parameterize处拦截
 *
 * @see MybatisBatchExecutor#doUpdate(MappedStatement, Object)
 * author：roc.zou
 * 2021/5/24 2:05 下午
 */
@Intercepts
        ({
                @Signature(type = StatementHandler.class, method = "parameterize", args = {Statement.class}),
                //@Signature(type = ParameterHandler.class, method = "setParameters", args = {PreparedStatement.class})
        })
@Component
@Order(value = Integer.MAX_VALUE)
public class AsyncOperateInterceptor implements Interceptor {

    /**
     * @description: 此处拦截异步操作下，NextopMetaObjectHandler session获取当前更新人失效导致updateId或createId = 1001 问题
     * @param: [invocation]
     * @return: java.lang.Object
     * @author: roc.zou
     * @date: 2021/5/24 2:25 下午
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("intercept :" + AsyncOperateContextHolder.getCondition());
        System.out.println("intercept :" + Thread.currentThread().getId() + Thread.currentThread().getName());
        AsyncOperateCondition condition = AsyncOperateContextHolder.getCondition();
        //是否跳过
        if (null == condition || null == condition.getOperatorId() || condition.getSkip()) {
            return invocation.proceed();
        }
        Long operatorId = condition.getOperatorId();
        StatementHandler statementHandler = PluginUtils.realTarget(invocation.getTarget());
        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
        // 先判断是不是UPDATER INSERT操作 跳过存储过程
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        if ((SqlCommandType.UPDATE != sqlCommandType && SqlCommandType.INSERT != sqlCommandType)
                || StatementType.CALLABLE == mappedStatement.getStatementType()) {
            return invocation.proceed();
        }
        //获取对应的执行语句及绑定的对象信息
        //BoundSql boundSql = (BoundSql) metaObject.getValue("delegate.boundSql");
        BoundSql boundSql = statementHandler.getBoundSql();
        Object paramObj = boundSql.getParameterObject();
        // 判断参数里是否有BaseEntity对象及子对象
        if (paramObj instanceof BaseEntity) {
            updateAndWrap((BaseEntity) paramObj, operatorId, sqlCommandType);
        } else if (paramObj instanceof Map) {
            for (Object arg : ((Map) paramObj).values()) {
                if (arg instanceof BaseEntity) {
                    updateAndWrap((BaseEntity) arg, operatorId, sqlCommandType);
                    break;
                }

            }
        }
        return invocation.proceed();
    }

    /**
     * @description:参数是baseEntity，updateId createId不为空且为默认值1001才进行处理
     * @param:
     * @return:
     * @author: roc.zou
     * @date: 2021/5/24 7:00 下午
     */
    void updateAndWrap(BaseEntity baseEntity, Long operatorId, SqlCommandType sqlCommandType) {
        if (Objects.nonNull(baseEntity) && Objects.nonNull(baseEntity.getUpdateId())) {
            if (SqlCommandType.UPDATE == sqlCommandType && APPLICATION_SYSTEM_UID.equals(baseEntity.getUpdateId())) {
                baseEntity.setUpdateId(operatorId);
            } else if (SqlCommandType.INSERT == sqlCommandType && APPLICATION_SYSTEM_UID.equals(baseEntity.getCreateId())) {
                baseEntity.setCreateId(operatorId);
                baseEntity.setUpdateId(operatorId);
            }
        }

    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

    Long APPLICATION_SYSTEM_UID = 1001L;

}

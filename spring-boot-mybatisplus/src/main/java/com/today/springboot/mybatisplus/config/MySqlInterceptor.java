package com.today.springboot.mybatisplus.config;

import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.today.springboot.mybatisplus.entity.BaseEntity;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年05月22日 20:28*
 * log.info()
 * extends JsqlParserSupport
 */
public class MySqlInterceptor implements InnerInterceptor {

    ThreadLocal<BaseEntity> threadLocal = new InheritableThreadLocal<>();

    /**
     * 判断是否执行 {@link Executor#update(MappedStatement, Object)}
     * <p>
     * 如果不执行update操作,则影响行数的值为 -1
     *
     * @param executor  Executor(可能是代理对象)
     * @param ms        MappedStatement
     * @param parameter parameter
     */
    @Override
    public boolean willDoUpdate(Executor executor, MappedStatement ms, Object parameter) throws SQLException {
        System.out.println("willDoUpdate");
        return true;
    }


    /**
     * {@link Executor#update(MappedStatement, Object)} 操作前置处理
     * <p>
     * 改改sql啥的
     *
     * @param executor  Executor(可能是代理对象)
     * @param ms        MappedStatement
     * @param parameter parameter
     */
    @Override
    public void beforeUpdate(Executor executor, MappedStatement ms, Object parameter) throws SQLException {
        // do nothing
        System.out.println("beforeUpdate");
        System.out.println(parameter);
        SqlCommandType sct = ms.getSqlCommandType();
        if ((sct == SqlCommandType.INSERT || sct == SqlCommandType.UPDATE) && parameter instanceof BaseEntity) {
            threadLocal.set((BaseEntity) parameter);
        }
    }

    /**
     * {@link StatementHandler#prepare(Connection, Integer)} 操作前置处理
     * <p>
     * 改改sql啥的
     *
     * @param sh                 StatementHandler(可能是代理对象)
     * @param connection         Connection
     * @param transactionTimeout transactionTimeout
     */
    @Override
    public void beforePrepare(StatementHandler sh, Connection connection, Integer transactionTimeout) {
        // do nothing
        System.out.println("beforePrepare");
        PluginUtils.MPStatementHandler mpSh = PluginUtils.mpStatementHandler(sh);
        SqlCommandType sct = mpSh.mappedStatement().getSqlCommandType();
        if (sct == SqlCommandType.INSERT || sct == SqlCommandType.UPDATE) {
            PluginUtils.MPBoundSql mpBs = mpSh.mPBoundSql();
            Map<String, Object> stringObjectMap = mpBs.additionalParameters();
            Object parameterObject = sh.getBoundSql().getParameterObject();
            if (parameterObject instanceof BaseEntity) {
                BaseEntity entity = (BaseEntity) parameterObject;
//                if (entity.getCreateId().equals(1001L) || entity.getUpdateId().equals(1001L)) {
                BaseEntity submitEntity = threadLocal.get();
                if (Objects.nonNull(submitEntity.getCreateId())) {
                    entity.setCreateId(submitEntity.getCreateId());
                }
                if (Objects.nonNull(submitEntity.getUpdateId())) {
                    entity.setUpdateId(submitEntity.getUpdateId());
                }
                for (String s : stringObjectMap.keySet()) {
                    if (stringObjectMap.get(s) instanceof BaseEntity) {
                        stringObjectMap.put(s, entity);
                        return;
                    }
                }
//                }
            }
        }
    }

    @Override
    public void setProperties(Properties properties) {
        // do nothing
        System.out.println("setProperties");
    }


}

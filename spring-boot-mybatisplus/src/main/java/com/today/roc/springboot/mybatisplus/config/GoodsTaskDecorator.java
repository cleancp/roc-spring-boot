package com.today.roc.springboot.mybatisplus.config;

import com.today.roc.springboot.mybatisplus.async.AsyncOperateCondition;
import com.today.roc.springboot.mybatisplus.async.AsyncOperateContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.task.TaskDecorator;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年06月05日 15:56*
 * log.info()
 */
@Slf4j
public class GoodsTaskDecorator implements TaskDecorator {
    @Override
    public Runnable decorate(Runnable runnable) {
        //AsyncOperateCondition condition = AsyncOperateContextHolder.getCondition();
        AsyncOperateCondition condition = new AsyncOperateCondition();
        condition.setOperatorId(12345L);
        condition.setSkip(false);
        AsyncOperateContextHolder.setCondition(condition);
        return ()->{
            try {
                AsyncOperateContextHolder.setCondition(condition);
                runnable.run();
            }catch (Exception e){
                log.error("线程装饰器执行异常",e);
            }finally {
                AsyncOperateContextHolder.remove();
            }
        };
    }
}

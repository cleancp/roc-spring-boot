package com.today.roc.springboot.mybatisplus.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.SystemClock;
import com.today.roc.springboot.mybatisplus.async.AsyncOperateContextHolder;
import com.today.roc.springboot.mybatisplus.common.RocConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.awt.*;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年05月22日 17:28*
 * log.info()
 */
@Slf4j
@Component("oneMetaObjectHandler")
public class MybatisPlusMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        long now = SystemClock.now();
        System.out.println("OneInsertFill");
        fillStrategy(metaObject, "updateId", RocConstants.APPLICATION_SYSTEM_UID);
        fillStrategy(metaObject, "updateTime", now);

        fillStrategy(metaObject, "createId", RocConstants.APPLICATION_SYSTEM_UID);
        fillStrategy(metaObject, "createTime", now);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        long now = SystemClock.now();
        System.out.println("OneUpdateFill");
        log.info("updateFill {}", AsyncOperateContextHolder.getCondition());
        metaObject.setValue("updateId", null);
        metaObject.setValue("updateTime", null);
        fillStrategy(metaObject, "updateId", RocConstants.APPLICATION_SYSTEM_UID);
        fillStrategy(metaObject, "updateTime", now);
    }
}

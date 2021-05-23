package com.today.springboot.mybatisplus.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.toolkit.SystemClock;
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

@Component("oneMetaObjectHandler")
public class MybatisPlusMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        long now = SystemClock.now();
        System.out.println("OneInsertFill");
        fillStrategy(metaObject, "updateId", 1L);
        fillStrategy(metaObject, "updateTime", now);

        fillStrategy(metaObject, "createId", 1L);
        fillStrategy(metaObject, "createTime", now);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        long now = SystemClock.now();
        System.out.println("OneUpdateFill");
        fillStrategy(metaObject, "updateId", 1);
        fillStrategy(metaObject, "updateTime", now);
    }
}

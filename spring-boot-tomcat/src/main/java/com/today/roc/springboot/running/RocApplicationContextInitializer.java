package com.today.roc.springboot.running;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年03月02日 17:58*
 * log.info()
 */
public class RocApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        System.out.println("RocApplicationContextInitializer initialize");
    }
}

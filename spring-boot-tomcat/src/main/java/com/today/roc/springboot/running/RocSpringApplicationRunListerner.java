package com.today.roc.springboot.running;

import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年03月02日 17:09*
 * log.info()
 */
public class RocSpringApplicationRunListerner implements SpringApplicationRunListener {

    /**
     * 必须有的构造器
     * @param application
     * @param args
     */
    public RocSpringApplicationRunListerner(SpringApplication application,String[] args){

    }

    @Override
    public void running(ConfigurableApplicationContext context) {
        System.out.println("RocSpringApplicationRunListerner running");
    }

    @Override
    public void started(ConfigurableApplicationContext context) {
        System.out.println("RocSpringApplicationRunListerner started");
    }

    @Override
    public void starting(ConfigurableBootstrapContext bootstrapContext) {
        System.out.println("RocSpringApplicationRunListerner starting");
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        System.out.println("RocSpringApplicationRunListerner contextPrepared");
    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
        System.out.println("RocSpringApplicationRunListerner failed");
    }

}

package com.today.roc.springboot.mybatisplus;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年05月22日 18:54*
 * log.info()
 */
@Slf4j
@SpringBootApplication(scanBasePackages = "com.today.roc.springboot.mybatisplus")
@MapperScan("com.today.roc.springboot.mybatisplus.mapper")
@EnableAsync
public class MybatisPlusApp {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusApp.class, args);
        System.out.println("-------启动成功-------");
    }
}

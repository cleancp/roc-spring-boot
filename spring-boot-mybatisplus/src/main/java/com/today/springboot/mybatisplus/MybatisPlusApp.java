package com.today.springboot.mybatisplus;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

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
@SpringBootApplication(scanBasePackages = "com.today.springboot.mybatisplus")
@MapperScan("com.today.springboot.mybatisplus.mapper")
public class MybatisPlusApp {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusApp.class, args);
        System.out.println("-------启动成功-------");
    }
}

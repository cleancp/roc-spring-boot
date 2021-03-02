package com.today.roc.springboot.base.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @program: 智灵时代广州研发中心
 * @description: swagger配置文件类
 * @author: 吞星(yangguojun)
 * @create: 2019-04-30 17:32
 **/
@Data
@ConfigurationProperties(prefix = SwaggerProperties.SWAGGER_PREFIX)
public class SwaggerProperties {

    public static final String SWAGGER_PREFIX = "roc.swagger";


    /**
     * swagger注解扫描范围
     */
    private String scanPackage = "";
    /**
     * swagger在线文档名称
     */
    private String tile;

    /**
     * swagger在线文档描述
     */
    private String description;

    /**
     * 项目维护人员 example
     */
    private String names;

    /**
     * 是否开启swagger true:开启；false：关闭
     */
    private String flag;
}

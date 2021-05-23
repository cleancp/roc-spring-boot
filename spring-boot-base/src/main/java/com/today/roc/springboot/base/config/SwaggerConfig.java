package com.today.roc.springboot.base.config;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年03月01日 12:51*
 * log.info()
 */
@Configuration
@EnableSwagger2
@EnableConfigurationProperties(SwaggerProperties.class)
public class SwaggerConfig implements BeanDefinitionRegistryPostProcessor, EnvironmentAware, ApplicationContextAware {

    private SwaggerProperties properties;

    private static Map<String, String> docketMaps;

    private ApplicationContext context;

    private Environment environment;

    static {
        docketMaps = new HashMap<>();
        docketMaps.put("docket", "/hello/**");
        docketMaps.put("docketOne", "/hello2/**");
        docketMaps.put("docketTwo", "/hello/3/**");
    }

    //    @Bean
    public Docket createRestApi() {
        //添加head参数start
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = Lists.newArrayList();
        tokenPar.name("session").defaultValue("00000000-0000-0000-0000-000000000000").description("登陆Session").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        //添加head参数end
        return new Docket(DocumentationType.SWAGGER_2)
                //.groupName("docket")
                .globalOperationParameters(pars)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(properties.getScanPackage()))
                //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
//                .paths("true".equals(properties.getFlag()) ? PathSelectors.any() : PathSelectors.none())
                .paths(PathSelectors.ant("/jsp/**"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(properties.getTile())
                .description(properties.getDescription())
                .contact(new Contact(properties.getNames(), null, null))
                .version("1.0")
                .build();
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        docketMaps.forEach(
                (docketName, scanPath) -> {
                    if (!registry.containsBeanDefinition(docketName)) {
                        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(Docket.class).getBeanDefinition();
                        ConstructorArgumentValues values = new ConstructorArgumentValues();
                        values.addIndexedArgumentValue(0, DocumentationType.SWAGGER_2);
                        beanDefinition.setConstructorArgumentValues(values);
                        //默认为单例
                        beanDefinition.setScope(ConfigurableBeanFactory.SCOPE_SINGLETON);
                        registry.registerBeanDefinition(docketName, beanDefinition);
//                        //bean注册的holer类.
//                        BeanDefinitionHolder beanDefinitionHolder = new BeanDefinitionHolder(beanDefinition, docketName);
//                        //使用bean注册工具类进行注册.
//                        BeanDefinitionReaderUtils.registerBeanDefinition(beanDefinitionHolder, registry);
                    }
                }
        );
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        registerDocket(beanFactory);
    }

    private void registerDocket(ConfigurableListableBeanFactory beanFactory) {
        //添加head参数start
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = Lists.newArrayList();
        tokenPar.name("session").defaultValue("00000000-0000-0000-0000-000000000000").description("登陆Session").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        docketMaps.forEach(
                (docketName, scanPath) -> {
                    Docket docket = beanFactory.getBean(docketName, Docket.class);
                    docket.groupName(docketName)
                            .globalOperationParameters(pars)
                            .apiInfo(apiInfo())
                            .select()
                            .apis(RequestHandlerSelectors.basePackage(properties.getScanPackage()))
                            //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
//                .paths("true".equals(properties.getFlag()) ? PathSelectors.ant(scanPath) : PathSelectors.none())
                            .paths(PathSelectors.ant(scanPath)).build();
                }
        );
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
//        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext
//                .getAutowireCapableBeanFactory();
//        this.registerDocket(beanFactory);
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
        properties = Binder.get(this.environment).bind(SwaggerProperties.SWAGGER_PREFIX, SwaggerProperties.class).get();
    }
}

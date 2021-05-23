//package com.today.roc.springboot.base.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.MutablePropertyValues;
//import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
//import org.springframework.beans.factory.config.ConstructorArgumentValues;
//import org.springframework.beans.factory.support.AbstractBeanDefinition;
//import org.springframework.beans.factory.support.BeanDefinitionBuilder;
//import org.springframework.beans.factory.support.BeanDefinitionRegistry;
//import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
//import org.springframework.stereotype.Component;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * ^---^---^---^---^---^---^---^
// * --v---v---v---v---v---v---v--
// *
// * @author zou.cp
// * @version 1.0
// * @Description
// * @createTime 2021年04月16日 23:36*
// * log.info()
// */
//@Slf4j
//@Component
//public class DcketBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
//
//    private static Map<String, String> docketMaps;
//
//    static {
//        docketMaps.put("docket", "/hello/**");
//        docketMaps.put("docketOne", "/hello2/**");
//        docketMaps.put("docketTwo", "/hello/3/**");
//    }
//
//    @Override
//    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
//        docketMaps.forEach(
//                (docketName, scanPath) -> {
//                    if (!registry.containsBeanDefinition(docketName)) {
//                        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(Docket.class).getBeanDefinition();
//                        beanDefinition.setBeanClassName(docketName);
//                        ConstructorArgumentValues values = new ConstructorArgumentValues();
//                        values.addIndexedArgumentValue(0, DocumentationType.SWAGGER_2);
//                        beanDefinition.setConstructorArgumentValues(values);
//                        MutablePropertyValues propertyValues = new MutablePropertyValues();
//                        propertyValues.add("groupName", docketName);
//                        propertyValues.add("paths", PathSelectors.ant(scanPath));
//                        beanDefinition.setPropertyValues(propertyValues);
//                        registry.registerBeanDefinition(docketName, beanDefinition);
//                    }
//                }
//        );
//
//    }
//
//    @Override
//    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
//
//        docketMaps.forEach(
//                (docketName, scanPath) -> {
//                    Docket bean = configurableListableBeanFactory.getBean(docketName, Docket.class);
//                    bean.select().build();
//                }
//        );
//    }
//}

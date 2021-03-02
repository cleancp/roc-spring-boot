package com.today.roc.springboot.base.aop;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年03月01日 14:32*
 * log.info()
 */
@Slf4j
@Aspect
@Component
public class WebLogAspect {

    @Pointcut("execution(public * com.today.roc.springboot.base.web.*.*(..))")
    public void webLog() {
    }


    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        //ProceedingJoinPoint jp
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        log.info("URL：{}", request.getRequestURL());
        log.info("HTTP METHOD：{}", request.getMethod());
        log.info("IP：{}", request.getRemoteAddr());
        log.info("joinPoint.getArgs：", JSONObject.toJSONString(joinPoint.getArgs()));
        Enumeration<String> parameterNames = request.getParameterNames();
        StringBuffer sb = new StringBuffer();
        sb.append("[name:value]:");
        while (parameterNames.hasMoreElements()) {
            String nextElement = parameterNames.nextElement();
            String parameter = request.getParameter(nextElement);
            sb.append("[");
            sb.append(nextElement);
            sb.append(":");
            sb.append(parameter);
            sb.append("]");
            sb.append("，");
        }
        log.info(sb.toString());
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturn(Object ret) {
        log.info("出参：{}", JSONObject.toJSONString(ret));
    }

//    private Boolean isPrintArgs(Object[] objects) {
//        try {
//            return Arrays.stream(objects).noneMatch(object -> StringUtils.equals(object.getClass().getName(), UNPRINT_CLASS));
//        } catch (Exception ex) {
//            log.warn("判断是否输出接口入参出错，ex:{}", ex.toString());
//            return true;
//        }
//    }
}

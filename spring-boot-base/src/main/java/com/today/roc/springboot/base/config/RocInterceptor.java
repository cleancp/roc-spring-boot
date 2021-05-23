//package com.today.roc.springboot.base.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.WebRequestInterceptor;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//import org.springframework.web.servlet.handler.WebRequestHandlerInterceptorAdapter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * ^---^---^---^---^---^---^---^
// * --v---v---v---v---v---v---v--
// *
// * @author zou.cp
// * @version 1.0
// * @Description
// * @createTime 2021年04月16日 18:05*
// * log.info()
// */
//@Slf4j
//public class RocInterceptor extends WebRequestHandlerInterceptorAdapter {
//
//    public RocInterceptor(WebRequestInterceptor requestInterceptor) {
//        super(requestInterceptor);
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        System.out.println("postHandle");
//        super.postHandle(request, response, handler, modelAndView);
//        System.out.println("super postHandle");
//    }
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        System.out.println("preHandle");
//        return super.preHandle(request, response, handler);
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        System.out.println("afterCompletion");
//        super.afterCompletion(request, response, handler, ex);
//        System.out.println("super afterCompletion");
//    }
//
//    @Override
//    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) {
//        System.out.println("afterConcurrentHandlingStarted");
//        super.afterConcurrentHandlingStarted(request, response, handler);
//        System.out.println("super afterConcurrentHandlingStarted");
//    }
//}

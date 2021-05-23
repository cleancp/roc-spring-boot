//package com.today.roc.springboot.base.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import java.io.IOException;
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
//@Component
//public class WebFilter implements Filter {
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        log.info("init,{}",filterConfig);
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        log.info("doFilter \n{} \n {}",servletRequest,servletResponse);
//    }
//
//    @Override
//    public void destroy() {
//        System.out.println("destroy");
//    }
//}

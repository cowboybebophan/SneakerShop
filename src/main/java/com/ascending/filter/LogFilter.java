package com.ascending.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "logFilter", urlPatterns = {"/*"}, dispatcherTypes = {DispatcherType.REQUEST})
public class LogFilter implements Filter {

    @Autowired
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        if (logger == null) {
            SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, request.getServletContext());
        }
        logger.debug(">>>>>> Entering Filter......");
        filterChain.doFilter(request, response);
        logger.debug(">>>>>> Exiting Filter......");
    }

    @Override
    public void init(FilterConfig filterConfig) {
        // TODO Auto-generated method stub
        //logger.debug(">>>>>> Initializing filter......");
    }

    public void destroy() {
        // TODO Auto-generated method stub
        //logger.debug(">>>>>> Destroying filter......");
    }
}

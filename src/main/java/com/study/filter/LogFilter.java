package com.study.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @version V1.0
 * @Description:
 * @author: h'mm
 * @date: 2020-10-10 21:06
 */
public class LogFilter implements Filter {
    FilterConfig  filterConfig;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("日志过滤器配置加载");
        this.filterConfig=filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("日志过滤器启动");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        System.out.println("日志记录中:"+httpServletRequest.getServletPath());

        try {
            chain.doFilter(request, response);
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
        System.out.println("日志记录结束");
    }

    @Override
    public void destroy() {
        this.filterConfig=null;
    }
}

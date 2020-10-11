package com.study.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * @version V1.0
 * @Description:
 * @author: h'mm
 * @date: 2020-10-10 21:52
 */
public class ActionFilter implements Filter {


    private String redirectPath;
    private String isDisable;
    private String encoding;
    private String[] loginStrings;
    private String[] includeStrings;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String loginString = filterConfig.getInitParameter("loginString");
        String includeString = filterConfig.getInitParameter("includeString");
        redirectPath = filterConfig.getInitParameter("redirectPath");
        isDisable = filterConfig.getInitParameter("isDisable");
        encoding = filterConfig.getInitParameter("encoding");

        loginStrings = loginString.split(";");
        includeStrings = includeString.split(";");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        httpResponse.setContentType("text/html;charset=utf-8");
        httpRequest.setCharacterEncoding(encoding);
        httpResponse.setCharacterEncoding(encoding);
        if (Boolean.getBoolean(isDisable)) {
            chain.doFilter(request, response);
            return;
        }
        Object user = httpRequest.getSession().getAttribute("user");
        if (user == null) {
            String uri = httpRequest.getRequestURI();
            if (isRelease(uri,loginStrings)) {
                chain.doFilter(request, response);
                return;
            }
            if (isRelease(uri, includeStrings)) {
                chain.doFilter(request, response);
                return;
            }
            httpResponse.sendRedirect(httpRequest.getContextPath()+ File.separator+redirectPath);
        }else{
            chain.doFilter(request, response);
        }
    }

    private boolean isRelease(String uri, String[] regx) {
        for (String string : regx) {
            if (uri.endsWith(string)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void destroy() {

    }
}

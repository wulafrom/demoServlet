package com.study.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version V1.0
 * @Description:
 * @author: h'mm
 * @date: 2020-10-10 15:29
 */
@WebServlet(name = "myServlet")
public class MyServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(HttpServlet.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getRequestLine(req);
        req.setAttribute("test","hello");
        req.getRequestDispatcher("/hello.html").include(req,  resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
        setCookie(req,resp);
        getCookies(req);
        System.out.println("来客了");
    }

    private void getCookies(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            System.out.println("name" + cookie.getName() + " value" + cookie.getValue());
        }
    }

    private void setCookie(HttpServletRequest req, HttpServletResponse resp) {
        Cookie comCookie = new Cookie("computer", "Fire Shadow");
        Cookie mouseCookie = new Cookie("mouse", "logitech");
        Cookie keyCookie = new Cookie("key", "jazz");
        mouseCookie.setMaxAge(3600*24*3);
        keyCookie.setPath(req.getContextPath()+"/car");
        resp.addCookie(comCookie);
        resp.addCookie(mouseCookie);
        resp.addCookie(keyCookie);
    }

    private void getRequestLine(HttpServletRequest req) {
        System.out.println(req.getMethod());
        System.out.println(req.getContextPath());
        logger.info("请求方式"+req.getMethod());
    }
}

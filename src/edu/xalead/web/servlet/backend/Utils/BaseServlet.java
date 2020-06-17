package edu.xalead.web.servlet.backend.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {

    //基类servlet 进行了深层的封装
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //请求头 ：Get uri?method=add Http/1.1
        //取出method
        String method = request.getParameter("method");

        //通过反射获取里面的方法
        Class c = this.getClass();
        try {
            Method m = c.getMethod(method, HttpServletRequest.class, HttpServletResponse.class);
            m.setAccessible(true);
            m.invoke(this, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

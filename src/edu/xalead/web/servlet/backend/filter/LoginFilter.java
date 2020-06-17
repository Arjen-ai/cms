package edu.xalead.web.servlet.backend.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "LoginFilter", urlPatterns = {"/backend/*"})
//过滤器，用来检查是否持有令牌，没有登陆不能去访问后台页面
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException{
        HttpServletRequest request = (HttpServletRequest) req;
        String requestUri = request.getRequestURI();
        //第二个条件比如图片png等的求
        if (request.getSession().getAttribute("login_user") != null ||
                !requestUri.matches("\\/backend\\/\\w+(\\.jsp|JSP|Servlet)") ||
                requestUri.equals("/backend/login.jsp")) {
            chain.doFilter(req, resp);
        } else {
            request.setAttribute("errors", "你还没登陆！");
            //转回登录页面去登陆
            request.getRequestDispatcher("/backend/login.jsp").forward(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}

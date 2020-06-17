package edu.xalead.web.servlet.backend.login;

import edu.xalead.web.servlet.backend.Utils.DB;
import edu.xalead.web.servlet.backend.vo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.Date;

@WebServlet(name = "LoginServlet", urlPatterns = {"/servlet/LoginServlet"})
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取表单提交数据
        String user = request.getParameter("user");
        String pwd = request.getParameter("pwd");
        String checkCode = request.getParameter("chknumber");
        response.setContentType("UTF-8");
        /**
         *  验证
         *  先校验验证码
         */
        //取出服务器生成的验证码
        String checkCode1 = (String) request.getSession().getAttribute("checkCode");
        if (checkCode.equalsIgnoreCase(checkCode1)) {
            //访问数据库
            try {
                //执行的语句
                String sql = "select * from t_user where user = ?";

                //链接对象
                Connection conn = null;
                //结果集
                ResultSet rs = null;
                PreparedStatement past = null;//带参数的查询

                //创建链接
                conn = DB.getConnection();
                //执行语句准备对象
                past = conn.prepareStatement(sql);
                //对问号赋值
                past.setString(1, user);
                //执行查询
                rs = past.executeQuery();//得到连线集

                if (rs.next()) {
                    int id = rs.getInt("uid");
                    String user1 = rs.getString("user");
                    String pwd1 = rs.getString("pwd");
                    if (user.equals(user1)) {//验证用户名
                        //验证密码
                        if (pwd.equals(pwd1)) {
                            //创建令牌，存到session
                            User u = new User();
                            u.setUserName(user);
                            u.setLoginTime(new Date());
                            //存到session
                            request.getSession().setAttribute("login_user", u);

                            if(request.getSession(false)==null)
                                request.getRequestDispatcher("/backend/login.jsp").forward(request, response);
                            //转到成功页面
                            request.getRequestDispatcher("/backend/main.jsp").forward(request, response);
                        } else {
                            request.setAttribute("errors", "密码与用户名不匹配！");
                            //转回登录页面
                            request.getRequestDispatcher("/backend/login.jsp").forward(request, response);
                        }
                    } else {
                        //转回登录页面
                        request.setAttribute("errors", "用户名输入错误！");
                        //转回登录页面
                        request.getRequestDispatcher("/backend/login.jsp").forward(request, response);
                    }
                } else {
                    request.setAttribute("errors", "你还没有注册！");
                    //转回登录页面
                    request.getRequestDispatcher("/backend/login.jsp").forward(request, response);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            //转回登录页面
            request.setAttribute("errors", "验证码输入错误！");
            //转回登录页面
            request.getRequestDispatcher("/backend/login.jsp").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

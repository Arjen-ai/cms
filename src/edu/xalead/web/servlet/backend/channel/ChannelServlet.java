package edu.xalead.web.servlet.backend.channel;

import edu.xalead.web.servlet.backend.Utils.BaseServlet;
import edu.xalead.web.servlet.backend.Utils.DB;
import edu.xalead.web.servlet.backend.vo.Channel;
import edu.xalead.web.servlet.backend.vo.Page;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@WebServlet(name = "ChannelServlet", urlPatterns = "/backend/ChannelServlet")
public class ChannelServlet extends BaseServlet {
    //请求派发
    //添加
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //得到表单提交的数据
        String cname = request.getParameter("cname");
        String description = request.getParameter("description");

        String sql = "insert into t_channel(c_name,description) values(?,?)";

        Connection conn = null;
        PreparedStatement prst = null;
        try {
            conn = DB.getConnection();
            prst = conn.prepareStatement(sql);
            prst.setString(1, cname);
            prst.setString(2, description);

            int count = prst.executeUpdate();
            if (count > 0) {
                //转到成功页面
                request.getRequestDispatcher("/backend/channel/success.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/backend/channel/defeated.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB.closeConnection(conn);
        }
    }

    //查询(分页查询)
    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sql = "select * from t_channel limit ?,?";
        String offset = request.getParameter("offset");
        String pgNumber = request.getParameter("pgNumber");
        Page pg = new Page();
        if(offset==null)
            pg.setOffset(0);
        else
            pg.setOffset(Integer.parseInt(offset));
        if(pgNumber == null)
            pg.setPageNumber(5);
        else
            pg.setPageNumber(Integer.parseInt(pgNumber));
        //创建链接对象
        Connection conn = null;
        //结果集
        ResultSet rs = null;
        //带参数的查询
        PreparedStatement past = null;
        try {
            //创建链接
            conn = DB.getConnection();
            //执行语句准备对象
            past = conn.prepareStatement(sql);
            past.setInt(1,pg.getOffset());
            past.setInt(2,pg.getPageNumber());
            //执行查询,得到连线集
            rs = past.executeQuery();
            //得到离线集
            ArrayList<ArrayList<String>> list = DB.getResultSet(rs);
            pg.setList(list);
            //得到中频道数
            String sqlCount = "select count(*) from t_channal";
            past = conn.prepareStatement(sqlCount);
            rs = past.executeQuery();

            rs.next();
            int total = rs.getInt(1);
            pg.setTotal(total);

            //转发,都存在页面中带过去
            request.setAttribute("table",pg);
            request.getRequestDispatcher("/backend/channel/list.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB.closeConnection(conn);
        }
    }

    //删除，既能删除一个又能删除多个
    public void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //语句
        String sql = "delete from t_channel where c_id=?";
        //通过id删除
        String[] aids = request.getParameterValues("id");
        Connection conn = null;
        PreparedStatement prst = null;
        try {
            //创建链接
            conn = DB.getConnection();
            int count = 0;
            for(String aid:aids) {
                prst = conn.prepareStatement(sql);
                prst.setInt(1, Integer.parseInt(aid));
                count += prst.executeUpdate();
            }
            if(count >= aids.length) {
                request.getRequestDispatcher("/backend/channel/success.jsp").forward(request, response);
            }else{
                request.getRequestDispatcher("/backend/channel/defeated.jsp").forward(request,response);
            }
            } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB.closeConnection(conn);
        }
    }

    //更新输入
    public void selUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //语句
        String sql = "select * from t_channel where c_id=?";
        //通过id查询
        String cid = request.getParameter("id");
        Connection conn = null;
        PreparedStatement prst = null;
        ResultSet rs = null;
        try {
            //创建链接
            conn = DB.getConnection();
            prst = conn.prepareStatement(sql);
            prst.setInt(1, Integer.parseInt(cid));
            rs = prst.executeQuery();//得到连线集
            rs.next();

            Channel channel = new Channel();
            channel.setCid(rs.getInt(1));
            channel.setCname(rs.getString(2));
            channel.setDescription(rs.getString(3));

            request.setAttribute("channel",channel);
            request.getRequestDispatcher("/backend/channel/updateInput.jsp").forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB.closeConnection(conn);
        }
    }

    //更新
    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cname = request.getParameter("cname");
        String description = request.getParameter("description");
        //语句
        String sql = "update t_channel set c_name=?,description=? where c_id=?";
        //通过id删除
        String cid = request.getParameter("id");
        Connection conn = null;
        PreparedStatement prst = null;
        try {
            //创建链接
            conn = DB.getConnection();
            prst = conn.prepareStatement(sql);
            prst.setString(1,cname);
            prst.setString(2,description);
            prst.setInt(3, Integer.parseInt(cid));
            int count = prst.executeUpdate();
            if(count > 0) {
                request.getRequestDispatcher("/backend/channel/success.jsp").forward(request, response);
            }else{
                request.getRequestDispatcher("/backend/channel/defeated.jsp").forward(request,response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB.closeConnection(conn);
        }
    }
}
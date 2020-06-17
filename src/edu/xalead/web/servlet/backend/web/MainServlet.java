package edu.xalead.web.servlet.backend.web;

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

@WebServlet(name = "MainServlet",urlPatterns = "/mainServlet")
public class MainServlet extends BaseServlet {

    public void nav(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement prst = null;
        ResultSet rs = null;
        String sql = "select * from t_channel";
        conn = DB.getConnection();
        try {
            prst = conn.prepareStatement(sql);
            rs = prst.executeQuery();
            ArrayList<ArrayList<String>> list = DB.getResultSet(rs);
            request.setAttribute("list",list);
            request.getRequestDispatcher("/inc/nav.jsp").include(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DB.closeConnection(conn);
        }
    }

    public void headline(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement prst = null;
        ResultSet rs = null;
        String sql = "select * from t_article where headline=true limit 0,2";
        conn = DB.getConnection();
        try {
            prst = conn.prepareStatement(sql);
            rs = prst.executeQuery();
            ArrayList<ArrayList<String>> list = DB.getResultSet(rs);
            request.setAttribute("list",list);
            request.getRequestDispatcher("/inc/headline.jsp").include(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DB.closeConnection(conn);
        }
    }

    public void tj(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement prst = null;
        ResultSet rs = null;
        String sql = "select * from t_article where headline=true and topic=true limit 0,4";
        conn = DB.getConnection();
        try {
            prst = conn.prepareStatement(sql);
            rs = prst.executeQuery();
            ArrayList<ArrayList<String>> list = DB.getResultSet(rs);
            request.setAttribute("list",list);
            request.getRequestDispatcher("/indexM.jsp").forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DB.closeConnection(conn);
        }
    }

    public void chn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = request.getParameter("id");
        Connection conn = null;
        PreparedStatement prst = null;
        ResultSet rs = null;
        String sql = "select * from t_article where c_id=? limit 0,14";
        String sql1 = "select * from t_channel where c_id=?";
        conn = DB.getConnection();
        try {
            prst = conn.prepareStatement(sql);
            prst.setInt(1,Integer.parseInt(cid));
            rs = prst.executeQuery();
            ArrayList<ArrayList<String>> article = DB.getResultSet(rs);
            request.setAttribute("article",article);

            prst = conn.prepareStatement(sql1);
            prst.setInt(1,Integer.parseInt(cid));
            rs = prst.executeQuery();
            rs.next();
            Channel channel = new Channel();
            channel.setCid(rs.getInt(1));
            channel.setCname(rs.getString(2));
            channel.setDescription(rs.getString(3));
            request.setAttribute("channel",channel);
            request.getRequestDispatcher("/inc/channel.jsp").include(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DB.closeConnection(conn);
        }
    }
    public void seeArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = request.getParameter("id");
        Connection conn = null;
        PreparedStatement prst = null;
        ResultSet rs = null;
        String sql = "select * from t_article where a_id=?";
        conn = DB.getConnection();
        try {
            prst = conn.prepareStatement(sql);
            prst.setInt(1, Integer.parseInt(cid));
            rs = prst.executeQuery();
            ArrayList<ArrayList<String>> article = DB.getResultSet(rs);

            request.setAttribute("art", article);
            request.getRequestDispatcher("/alist.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB.closeConnection(conn);
        }
    }

    public void topic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("channelId");
        String off = request.getParameter("offset");
        String pgNumber = request.getParameter("pgNumber");
        Page pg = new Page();
        if(off==null)
            pg.setOffset(0);
        else
            pg.setOffset(Integer.parseInt(off));
        if(pgNumber == null)
            pg.setPageNumber(5);
        else
            pg.setPageNumber(Integer.parseInt(pgNumber));
        Connection conn = null;
        PreparedStatement prst = null;
        ResultSet rs = null;
        String sql = "select * from t_article where c_id=? limit ?,?";
        String sqlCount = "select count(*) from t_article where c_id=?";
        conn = DB.getConnection();
        try {
            prst = conn.prepareStatement(sql);
            prst.setInt(1, Integer.parseInt(id));
            prst.setInt(2,pg.getOffset());
            prst.setInt(3,pg.getPageNumber());
            rs = prst.executeQuery();
            ArrayList<ArrayList<String>> article = DB.getResultSet(rs);
            pg.setList(article);

            prst = conn.prepareStatement(sqlCount);
            prst.setInt(1,Integer.parseInt(id));
            rs = prst.executeQuery();
            rs.next();
            int total = rs.getInt(1);
            pg.setTotal(total);
            request.setAttribute("pg", pg);
            request.getRequestDispatcher("/topic.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB.closeConnection(conn);
        }
    }

    public void selAllArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String aid = request.getParameter("articleId");
        Connection conn = null;
        PreparedStatement prst = null;
        ResultSet rs = null;
        String sql = "select * from t_article where a_id=?";
        conn = DB.getConnection();
        try {
            prst = conn.prepareStatement(sql);
            prst.setInt(1, Integer.parseInt(aid));
            rs = prst.executeQuery();
            ArrayList<ArrayList<String>> art = DB.getResultSet(rs);

            request.setAttribute("art", art);
            request.getRequestDispatcher("/article.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB.closeConnection(conn);
        }
    }
}

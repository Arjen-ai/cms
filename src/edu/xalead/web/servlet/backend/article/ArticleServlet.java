package edu.xalead.web.servlet.backend.article;

import edu.xalead.web.servlet.backend.Utils.BaseServlet;
import edu.xalead.web.servlet.backend.Utils.DB;
import edu.xalead.web.servlet.backend.vo.Article;
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

@WebServlet(name = "ArticleServlet", urlPatterns = "/backend/ArticleServlet")
public class ArticleServlet extends BaseServlet {
    //请求派发
    //添加
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //得到表单提交的数据
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String author = request.getParameter("author");
        String source = request.getParameter("source");
        String headline = request.getParameter("headline");
        String topic = request.getParameter("topic");
        String sql = "insert into t_article(title,content,author,source,createTime,headline,topic) values(?,?,?,?,now(),?,?)";

        Connection conn = null;
        PreparedStatement prst = null;
        try {
            conn = DB.getConnection();
            prst = conn.prepareStatement(sql);
            prst.setString(1, title);
            prst.setString(2, content);
            prst.setString(3, author);
            prst.setString(4, source);
            prst.setBoolean(5,Boolean.getBoolean(headline));
            prst.setBoolean(6,Boolean.getBoolean(topic));

            int count = prst.executeUpdate();

            if (count > 0) {

                //转到成功页面
                request.getRequestDispatcher("/backend/article/success.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/backend/article/defeated.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB.closeConnection(conn);
        }
    }

    //查询(分页查询)
    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        String sql = "select * from t_article limit ?,?";
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
            //获取文章中枢
            String pageCount = "select count(*) from t_article";
            past = conn.prepareStatement(pageCount);
            rs = past.executeQuery();

            rs.next();
            pg.setTotal(rs.getInt(1));

            //转发,都存在页面中带过去
            request.setAttribute("pg",pg);
            request.getRequestDispatcher("/backend/article/list.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB.closeConnection(conn);
        }
    }

    //删除，既能删除一个又能删除多个
    public void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //语句
        String sql = "delete from t_article where a_id=?";
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
                request.getRequestDispatcher("/backend/article/success.jsp").forward(request, response);
            }else{
                request.getRequestDispatcher("/backend/article/defeated.jsp").forward(request,response);
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
        String sql = "select * from t_article where a_id=?";
        //通过id查询
        String aid = request.getParameter("id");
        Connection conn = null;
        PreparedStatement prst = null;
        ResultSet rs = null;
        try {
            //创建链接
            conn = DB.getConnection();
            prst = conn.prepareStatement(sql);
            prst.setInt(1, Integer.parseInt(aid));
            rs = prst.executeQuery();//得到连线集
            rs.next();

            Article article = new Article();
            article.setA_id(rs.getInt(1));
            article.setTitle(rs.getString(2));
            article.setContent(rs.getString(3));
            article.setAuthor(rs.getString(4));
            article.setCreateTime(rs.getDate(5));
            article.setSource(rs.getString(6));
            article.setHeadline(rs.getBoolean(7));
            article.setTopic(rs.getBoolean(8));

            request.setAttribute("article",article);
            request.getRequestDispatcher("/backend/article/updateInput.jsp").forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB.closeConnection(conn);
        }
    }

    //更新
    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String author = request.getParameter("author");
        String source = request.getParameter("source");
        String headline = request.getParameter("headline");
        String topic = request.getParameter("topic");
        //语句
        String sql = "update t_article set title=?,content=?,author=?,source=?,headline=?,topic=? where a_id=?";
        //通过id删除
        String aid = request.getParameter("id");
        Connection conn = null;
        PreparedStatement prst = null;
        try {
            //创建链接
            conn = DB.getConnection();
            prst = conn.prepareStatement(sql);
            prst.setString(1,title);
            prst.setString(2,content);
            prst.setString(3,author);
            prst.setString(4,source);
            prst.setBoolean(5,Boolean.valueOf(headline));
            prst.setBoolean(6,Boolean.valueOf(topic));
            prst.setInt(7, Integer.parseInt(aid));
            int count = prst.executeUpdate();
            if(count > 0) {
                request.getRequestDispatcher("/backend/article/success.jsp").forward(request, response);
            }else{
                request.getRequestDispatcher("/backend/article/defeated.jsp").forward(request,response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB.closeConnection(conn);
        }
    }


}
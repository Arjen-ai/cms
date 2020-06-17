package edu.xalead.web.servlet.backend.web;

import edu.xalead.web.servlet.backend.Utils.DB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "MIndexServlet",urlPatterns = "/index.jsp")
public class MIndexServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //查出四个频道
        Connection conn = null;
        PreparedStatement prst = null;
        ResultSet rs = null;
        String sql = "select * from t_channel limit 0,4";
        conn = DB.getConnection();
        try {
            prst = conn.prepareStatement(sql);
            rs = prst.executeQuery();
            ArrayList<ArrayList<String>> list = DB.getResultSet(rs);
            request.setAttribute("ch",list);
            request.getRequestDispatcher("/indexM.jsp").forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DB.closeConnection(conn);
        }
    }
}

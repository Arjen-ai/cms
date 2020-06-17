package edu.xalead.web.servlet.backend.Utils;

import java.sql.*;
import java.util.ArrayList;

//封装
public class DB {

    //获取连接
    public static Connection getConnection() {
        //链接数据库
        String driverClass = "com.mysql.jdbc.Driver";
        String uri = "jdbc:mysql://localhost:3306/cms";
        String username = "root";
        String pwd = "root";

        Connection connection = null;
        try {
            //加载驱动
            Class.forName(driverClass);
            connection = DriverManager.getConnection(uri, username, pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //结果集的处理
    public static ArrayList<ArrayList<String>> getResultSet(ResultSet rs) {
        ArrayList<ArrayList<String>> list = null;
        try {
            ResultSetMetaData meta = rs.getMetaData();
            list = new ArrayList();
            while (rs.next()) {
                ArrayList<String> row = new ArrayList<>();
                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }
                list.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}

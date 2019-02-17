package com.blog.utils;

import java.sql.*;

public class DBUtils {
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection(){
        String url = "jdbc:mysql://localhost:3306/myblog?useUnicode=true&characterEncoding=utf-8&useSSL=true";
        String user = "myblog";
        String password = "z6XtZkj3KBKWxxjA";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    public static PreparedStatement getPst(Connection conn,String sql){
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pst;
    }

    public static void close(ResultSet rs,PreparedStatement pst,Connection conn){
        try {
            if(rs != null)
                rs.close();
            if(pst !=null)
                pst.close();
            if(conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

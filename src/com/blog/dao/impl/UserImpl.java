package com.blog.dao.impl;

import com.blog.dao.UserDao;
import com.blog.entity.User;
import com.blog.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserImpl implements UserDao {

    @Override
    public int reg(User user) {
        int num = 0;
        Connection conn = DBUtils.getConnection();
        String sql ="insert into users(username,password,email) " +
                "values(?,?,?)";
        PreparedStatement pst = DBUtils.getPst(conn,sql);
        try {
            pst.setString(1,user.getUsername());
            pst.setString(2,user.getPassword());
            pst.setString(3,user.getEmail());

            num = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(null,pst,conn);
        }
        return num;
    }

    @Override
    public User login(String username, String password) {
        User user = null;
        ResultSet rs = null;
        Connection conn = DBUtils.getConnection();
        String sql = "select * from users where username = ?";
        PreparedStatement pst = DBUtils.getPst(conn,sql);
        try {
            pst.setString(1,username);
            rs = pst.executeQuery();
            if(!rs.next()){
                user = new User();
                user.setUid("-1");
            }else{
                if(password.equals(rs.getString("password"))){
                   user = new User();
                   user.setUid(rs.getString("uid"));
                   user.setUsername(rs.getString("username"));
                   user.setPassword(rs.getString("password"));
                   user.setEmail(rs.getString("email"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs,pst,conn);
        }
        return user;
    }
}

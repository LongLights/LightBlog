package com.blog.dao.impl;

import com.blog.dao.SliderDao;
import com.blog.entity.Post;
import com.blog.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SliderImpl implements SliderDao {
    @Override
    public List<Post> getRandList() {
        List<Post> randList = new ArrayList<>();
        Connection conn = DBUtils.getConnection();
        ResultSet rs = null;
        String sql = "select * from posts " +
                "where post_thumbnail is not null " +
                "order by rand() limit 9 ";
        PreparedStatement pst = DBUtils.getPst(conn,sql);
        try {
            rs = pst.executeQuery();
            while(rs.next()){
                Post post = new Post();
                post.setPost_title(rs.getString("post_title"));
                post.setPost_id(rs.getString("post_id"));
                post.setPost_thumbnail(rs.getString("post_thumbnail"));

                randList.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs,pst,conn);
        }
        return randList;
    }

    @Override
    public List<Post> getRecList() {
        List<Post> recList = new ArrayList<>();
        Connection conn = DBUtils.getConnection();
        String sql = "select * from posts " +
                "where post_thumbnail is not null and is_rec = 1 " +
                "order by rand() limit 8 ";
        PreparedStatement pst = DBUtils.getPst(conn,sql);
        ResultSet rs = null;
        try {
            rs = pst.executeQuery();
            while(rs.next()){
                Post post = new Post();
                post.setPost_thumbnail(rs.getString("post_thumbnail"));
                post.setPost_title(rs.getString("post_title"));
                post.setPost_id(rs.getString("post_id"));

                recList.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs,pst,conn);
        }
        return recList;
    }
}

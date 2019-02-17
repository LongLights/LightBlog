package com.blog.dao.impl;

import com.blog.dao.PostDao;
import com.blog.entity.Post;
import com.blog.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostImpl implements PostDao {
    @Override
    public List<Post> getPostsByCategory(String category_id,int page) {
        List<Post> postsByCategory = new ArrayList<>();
        Connection conn = DBUtils.getConnection();
        String sql = "select * from posts " +
                "where category_id = ? " +
                "order by post_date desc " +
                "limit ?,10";
        PreparedStatement pst = DBUtils.getPst(conn,sql);
        ResultSet rs = null;
        try {
            pst.setString(1,category_id);
            pst.setInt(2,(page-1)*10);
            rs = pst.executeQuery();
            while(rs.next()){
                Post post = new Post();
                post.setPost_id(rs.getString("post_id"));
                post.setPost_title(rs.getString("post_title"));
                post.setPost_excerpt(rs.getString("post_excerpt"));
                String detailedDate = rs.getString("post_date");
                String briefDate = detailedDate.split(" ")[0];
                post.setPost_date(briefDate);
                post.setPost_thumbnail(rs.getString("post_thumbnail"));

                postsByCategory.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs,pst,conn);
        }
        return postsByCategory;
    }

    @Override
    public Post getOnePost(String post_id) {
        Post post = new Post();
        Connection conn = DBUtils.getConnection();
        String sql = "select * from posts " +
                "where post_id = ?";
        PreparedStatement pst = DBUtils.getPst(conn,sql);
        ResultSet rs = null;
        try {
            pst.setString(1,post_id);
            rs = pst.executeQuery();
            if(rs.next()){
                post.setPost_date(rs.getString("post_date"));
                post.setPost_content(rs.getString("post_content"));
                post.setPost_title(rs.getString("post_title"));
                post.setPost_tags(rs.getString("post_tags"));
                post.setPost_excerpt(rs.getString("post_excerpt"));
                post.setPost_date(rs.getString("post_date"));
                post.setCategory_id(rs.getString("category_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs,pst,conn);
        }
        return post;
    }

    @Override
    public Post getPrevPost(String post_id) {
        Post prevPost = new Post();
        Connection conn = DBUtils.getConnection();
        String sql = "select * from posts " +
                "where post_id<? order by post_id desc limit 1";
        PreparedStatement pst = DBUtils.getPst(conn,sql);
        ResultSet rs = null;
        try {
            pst.setString(1,post_id);
            rs = pst.executeQuery();
            if(rs.next()){
                prevPost.setPost_title(rs.getString("post_title"));
                prevPost.setPost_id(rs.getString("post_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs,pst,conn);
        }
        return prevPost;
    }

    @Override
    public Post getNextPost(String post_id) {
        Post nextPost = new Post();
        Connection conn = DBUtils.getConnection();
        String sql = "select * from posts " +
                "where post_id>? limit 1";
        PreparedStatement pst = DBUtils.getPst(conn,sql);
        ResultSet rs = null;
        try {
            pst.setString(1,post_id);
            rs = pst.executeQuery();
            if(rs.next()){
                nextPost.setPost_title(rs.getString("post_title"));
                nextPost.setPost_id(rs.getString("post_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs,pst,conn);
        }
        return nextPost;
    }

    //查询最后一页的页码
    public int getLastPage(String category_id){
        int lastPage = 0;
        int postNum = 0;
        Connection conn = DBUtils.getConnection();
        String sql = "select count(1) from posts " +
                "where category_id ="+category_id;
        PreparedStatement pst = DBUtils.getPst(conn,sql);
        ResultSet rs = null;
        try {
            rs = pst.executeQuery();
            if(rs.next()){
                postNum = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs,pst,conn);
        }
        lastPage = postNum / 10;
        if(postNum % 10 != 0){
            lastPage += 1;
        }
        return lastPage;
    }
}

package com.blog.dao.impl;

import com.blog.dao.IndexDao;
import com.blog.entity.Post;
import com.blog.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IndexImpl implements IndexDao {
    /*getIndexSeo方法获取首页的TKD信息*/
    @Override
    public String[] getIndexSeo() {
        String[] indexSeo = new String[3];
        Connection conn = DBUtils.getConnection();
        String sql = "select * from seo_settings";
        PreparedStatement pst = DBUtils.getPst(conn,sql);
        ResultSet rs = null;
        try {
            rs = pst.executeQuery();
            if(rs.next()){
                indexSeo[0] = rs.getString("website_name");
                indexSeo[1] = rs.getString("website_keyword");
                indexSeo[2] = rs.getString("website_description");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs,pst,conn);
        }
        return indexSeo;
    }


    /*getSpecial方法，对应首页"精彩专题"部分的文章
    * 选择的分类id存储在layout_settings表中的index_special字段
    * index_special字段最多存储5个分类id，以英文逗号分开*/
    @Override
    public List<Post> getSpecial() {
        List<Post> special_posts = new ArrayList<>();
        String[] special_posts_id = getIndex_special_id();
        ResultSet rs = null;

        //将index_special中的分类都放进sql的where条件中
        StringBuilder sql_where = new StringBuilder("where ");
        for(int i=0;i<special_posts_id.length;i++){
            if(i != special_posts_id.length-1){
                sql_where.append("category_id=").append(special_posts_id[i]).append(" or ");
            }else{
                sql_where.append("category_id=").append(special_posts_id[i]);
            }
        }
        //where条件定义后的完整sql语句
        String sql = "select * from posts " + sql_where + " and post_thumbnail is not null " +
                "order by post_date desc limit 6";
        Connection conn = DBUtils.getConnection();
        PreparedStatement pst = DBUtils.getPst(conn,sql);
        try {
            rs = pst.executeQuery();
            while (rs.next()){
                Post post = new Post();
                post.setPost_id(rs.getString("post_id"));
                post.setPost_title(rs.getString("post_title"));
                post.setPost_excerpt(rs.getString("post_excerpt"));
                post.setPost_thumbnail(rs.getString("post_thumbnail"));
                special_posts.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs,pst,conn);
        }
        return special_posts;
    }

    @Override
    public List<Post> getNewPosts() {
        List<Post> new_posts = new ArrayList<>();
        Connection conn = DBUtils.getConnection();
        String sql = "select * from posts " +
                "order by post_date desc limit 9";
        ResultSet rs = null;
        PreparedStatement pst = DBUtils.getPst(conn,sql);
        try {
            rs = pst.executeQuery();
            while(rs.next()){
                Post post = new Post();
                post.setPost_thumbnail(rs.getString("post_thumbnail"));
                post.setPost_title(rs.getString("post_title"));
                post.setPost_id(rs.getString("post_id"));
                post.setPost_excerpt(rs.getString("post_excerpt"));
                String detailedDate = rs.getString("post_date");
                String briefDate = detailedDate.split(" ")[0];
                post.setPost_date(briefDate);
                new_posts.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs,pst,conn);
        }
        return new_posts;
    }

    @Override
    public List<Post> getIndex_push_pic_posts() {
        List<Post> index_push_pic_posts = new ArrayList<>();
        Connection conn = DBUtils.getConnection();
        ResultSet rs = null;
        String sql = "select * from posts " +
                "where is_rec = 1 and post_thumbnail is not null " +
                "order by post_date desc limit 2 ";
        PreparedStatement pst = DBUtils.getPst(conn,sql);
        try {
            rs = pst.executeQuery();
            while(rs.next()){
                Post post = new Post();
                post.setPost_id(rs.getString("post_id"));
                post.setPost_title(rs.getString("post_title"));
                post.setPost_thumbnail(rs.getString("post_thumbnail"));
                index_push_pic_posts.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs,pst,conn);
        }
        return index_push_pic_posts;
    }

    @Override
    public List<Post> getIndex_push_posts() {
        List<Post> posts = new ArrayList<>();
        ResultSet rs = null;
        Connection conn = DBUtils.getConnection();
        String sql = "select * from posts " +
                "where is_rec = 1 " +
                "order by post_date desc limit 5 ";
        PreparedStatement pst = DBUtils.getPst(conn,sql);
        try {
            rs = pst.executeQuery();
            while(rs.next()){
                Post post = new Post();
                post.setPost_id(rs.getString("post_id"));
                post.setPost_title(rs.getString("post_title"));
                post.setPost_excerpt(rs.getString("post_excerpt"));
                post.setCategory_id(rs.getString("category_id"));
                posts.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs,pst,conn);
        }
        return posts;
    }

    @Override
    public List<Post> getAnnouncement() {
        List<Post> announcement_posts = new ArrayList<>();
        String announcement_id = getAnnouncement_id();
        Connection conn = DBUtils.getConnection();
        String sql = "select * from posts " +
                "where category_id = ? limit 4";
        PreparedStatement pst = DBUtils.getPst(conn,sql);
        ResultSet rs = null;
        try {
            pst.setString(1,announcement_id);
            rs = pst.executeQuery();
            while(rs.next()){
                Post post = new Post();
                post.setPost_title(rs.getString("post_title"));
                post.setPost_id(rs.getString("post_id"));
                announcement_posts.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs,pst,conn);
        }
        return announcement_posts;
    }


    private static String[] getIndex_special_id() {
        String[] index_special_id = new String[5];
        Connection conn = DBUtils.getConnection();
        String sql = "select index_special from layout_settings";
        PreparedStatement pst = DBUtils.getPst(conn,sql);
        ResultSet rs = null;
        try {
            rs = pst.executeQuery();
            if(rs.next()){
                index_special_id = rs.getString("index_special").split(",");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs,pst,conn);
        }
        return index_special_id;
    }
    private static String getAnnouncement_id(){
        String announcement_id = "";
        Connection conn = DBUtils.getConnection();
        String sql = "select index_announcement from layout_settings ";
        PreparedStatement pst = DBUtils.getPst(conn,sql);
        ResultSet rs = null;
        try {
            rs = pst.executeQuery();
            if(rs.next()){
                announcement_id = rs.getString("index_announcement");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs,pst,conn);
        }
        return announcement_id;
    }
}
package com.blog.dao.impl;

import com.blog.dao.CategoryDao;
import com.blog.entity.Category;
import com.blog.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryImpl implements CategoryDao {
    @Override
    public String[] getSeoInfo(String category_id) {
        String[] category_seo = new String[3];
        Connection conn = DBUtils.getConnection();
        String sql = "select * from category " +
                "where category_id = ?";
        PreparedStatement pst = DBUtils.getPst(conn,sql);
        ResultSet rs = null;
        try {
            pst.setString(1,category_id);
            rs = pst.executeQuery();
            if(rs.next()){
                category_seo[0] = rs.getString("category_name");
                category_seo[1] = rs.getString("category_keyword");
                category_seo[2] = rs.getString("category_description");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs,pst,conn);
        }
        return category_seo;
    }

    @Override
    public List<Category> getCategories() {
        List<Category> categoryList = new ArrayList<>();
        Connection conn = DBUtils.getConnection();
        String sql = "select * from category limit 8";
        PreparedStatement pst = DBUtils.getPst(conn,sql);
        ResultSet rs = null;
        try {
            rs = pst.executeQuery();
            while(rs.next()){
                Category cate = new Category();
                cate.setCategory_id(rs.getString("category_id"));
                cate.setCategory_name(rs.getString("category_name"));
                categoryList.add(cate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs,pst,conn);
        }

        return categoryList;
    }
}

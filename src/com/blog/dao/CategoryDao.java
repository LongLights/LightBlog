package com.blog.dao;

import com.blog.entity.Category;

import java.util.List;

public interface CategoryDao {
    List<Category> getCategories();
    String[] getSeoInfo(String category_id);
}

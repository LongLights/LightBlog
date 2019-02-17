package com.blog.dao;

import com.blog.entity.Post;

import java.util.List;

public interface SliderDao {
    List<Post> getRandList();
    List<Post> getRecList();
}

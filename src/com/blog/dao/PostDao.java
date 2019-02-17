package com.blog.dao;

import com.blog.entity.Post;

import java.util.List;

public interface PostDao {
    List<Post> getPostsByCategory(String category_id,int page);
    Post getOnePost(String post_id);
    Post getPrevPost(String post_id);
    Post getNextPost(String post_id);
}

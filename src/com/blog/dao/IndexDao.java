package com.blog.dao;

import com.blog.entity.Post;

import java.util.List;
import java.util.Map;

public interface IndexDao {
    List<Post> getIndex_push_posts();
    List<Post> getAnnouncement();
    List<Post> getIndex_push_pic_posts();
    List<Post> getNewPosts();
    List<Post> getSpecial();
    String[] getIndexSeo();
}

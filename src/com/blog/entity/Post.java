package com.blog.entity;

public class Post {
    private String post_id;
    private String post_date;
    private String post_title;
    private String post_content;
    private String post_excerpt;
    private String post_thumbnail;
    private String post_tags;
    private String category_id;
    private String is_rec;

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getPost_date() {
        return post_date;
    }

    public void setPost_date(String post_date) {
        this.post_date = post_date;
    }

    public String getPost_title() {
        return post_title;
    }

    public void setPost_title(String post_title) {
        this.post_title = post_title;
    }

    public String getPost_content() {
        return post_content;
    }

    public void setPost_content(String post_content) {
        this.post_content = post_content;
    }

    public String getPost_excerpt() {
        return post_excerpt;
    }

    public void setPost_excerpt(String post_excerpt) {
        this.post_excerpt = post_excerpt;
    }

    public String getPost_thumbnail() {
        return post_thumbnail;
    }

    public void setPost_thumbnail(String post_thumbnail) {
        this.post_thumbnail = post_thumbnail;
    }

    public String getPost_tags() {
        return post_tags;
    }

    public void setPost_tags(String post_tags) {
        this.post_tags = post_tags;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getIs_rec() {
        return is_rec;
    }

    public void setIs_rec(String is_rec) {
        this.is_rec = is_rec;
    }
}

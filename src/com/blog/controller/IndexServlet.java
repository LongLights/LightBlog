package com.blog.controller;

import com.blog.dao.IndexDao;
import com.blog.dao.impl.IndexImpl;
import com.blog.entity.Category;
import com.blog.entity.Post;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "IndexServlet")
public class IndexServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IndexDao dao = new IndexImpl();
        List<Post> announcement_posts = dao.getAnnouncement();
        List<Post> index_push_posts = dao.getIndex_push_posts();
        List<Post> index_push_pic_posts = dao.getIndex_push_pic_posts();
        List<Post> new_posts = dao.getNewPosts();
        List<Post> special_posts = dao.getSpecial();
        String[] indexSeo = dao.getIndexSeo();
        request.setAttribute("website_name",indexSeo[0]);
        request.setAttribute("website_keyword",indexSeo[1]);
        request.setAttribute("website_description",indexSeo[2]);

        request.setAttribute("announcement_posts",announcement_posts);
        request.setAttribute("index_push_posts",index_push_posts);
        request.setAttribute("index_push_pic_posts",index_push_pic_posts);
        request.setAttribute("new_posts",new_posts);
        request.setAttribute("special_posts",special_posts);
    }
}

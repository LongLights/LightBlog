package com.blog.controller;

import com.blog.dao.CategoryDao;
import com.blog.dao.impl.CategoryImpl;
import com.blog.entity.Category;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "NavServlet")
public class NavServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoryDao dao = new CategoryImpl();
        List<Category> categoryList = dao.getCategories();
        request.setAttribute("categoryList",categoryList);
    }
}

package com.blog.controller;

import com.blog.dao.SliderDao;
import com.blog.dao.impl.SliderImpl;
import com.blog.entity.Post;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SliderServlet")
public class SliderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SliderDao dao = new SliderImpl();
        List<Post> randList = dao.getRandList();
        List<Post> recList = dao.getRecList();
        request.setAttribute("randList",randList);
        request.setAttribute("recList",recList);
    }
}

package com.blog.controller;

import com.blog.dao.UserDao;
import com.blog.dao.impl.UserImpl;
import com.blog.entity.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String page = request.getParameter("fromPage");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmed = request.getParameter("confirmed");
        String email = request.getParameter("email");
        String submit_button = request.getParameter("submit_button");
        request.getSession().setAttribute("username",username);
        request.getSession().setAttribute("password",password);
        if (page.equals("login")){
            loginPage(request,response,submit_button,username,password);
        }else{
            regPage(request,response,submit_button,username,password,confirmed,email);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request,response);
    }

    private void loginPage(HttpServletRequest request, HttpServletResponse response, String submit_button, String username, String password) throws IOException {
        //用户选择了注册按钮
        if(submit_button.equals("Sign up")){
            response.sendRedirect("reg.jsp");
        }else{
            //用户选的是登录
            UserDao dao = new UserImpl();
            User user = dao.login(username,password);
            String msg;
            if(user == null){
                msg = "*用户名与密码不匹配";
            }else if(user.getUid().equals("-1")){
                msg = "*不存在该用户";
            }else {
                msg = "*用户名与密码匹配";
            }
            request.getSession().setAttribute("msg",msg);
            response.sendRedirect("login.jsp");
        }
    }
    private void regPage(HttpServletRequest request, HttpServletResponse response, String submit_button, String username, String password, String confirmed, String email) throws IOException {
        if(submit_button.equals("Log in")){
            response.sendRedirect("login.jsp");
        }else{
            String msg;
            if(!email.contains("@")){
                msg = "邮箱格式非法";
            }else if(!password.equals(confirmed)){
                msg = "两次输入的密码不一致";
            }else{
                User user = new User();
                user.setUsername(username);
                user.setPassword(password);
                user.setEmail(email);
                UserDao dao = new UserImpl();
                int num = dao.reg(user);
                if(num == 0){
                    msg = "注册失败！";
                }else{
                    msg = "注册成功，请去登录！";
                }
            }
            request.getSession().setAttribute("msg",msg);
            response.sendRedirect("login.jsp");
        }
    }
}

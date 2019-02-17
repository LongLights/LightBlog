package com.blog.controller;

import com.blog.dao.CategoryDao;
import com.blog.dao.IndexDao;
import com.blog.dao.PostDao;
import com.blog.dao.impl.CategoryImpl;
import com.blog.dao.impl.IndexImpl;
import com.blog.dao.impl.PostImpl;
import com.blog.entity.Post;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "PostServlet")
public class PostServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        PostImpl dao = new PostImpl();
        if(method.equals("getPostsByCategory")){
            String cid = request.getParameter("cid");
            CategoryDao dao2 =  new CategoryImpl();
            IndexDao dao3 = new IndexImpl();
            //从url的参数获取的页码信息，为String类型
            int page = 1;
            String stringPage = request.getParameter("page");
            if(stringPage != null){
                page = Integer.parseInt(stringPage);
            }
            getPostsByCategory(request,response,dao,cid,dao2,dao3,page);
        } else if (method.equals("getOnePost")){
            IndexDao dao2 = new IndexImpl();
            String pid = request.getParameter("pid");
            getOnePost(request,response,dao,pid,dao2);
        }

    }
    void getPostsByCategory(HttpServletRequest request, HttpServletResponse response, PostImpl dao, String cid,CategoryDao dao2,IndexDao dao3,int page) throws ServletException, IOException {
        List<Post> postsByCategory = dao.getPostsByCategory(cid,page);
        request.setAttribute("postsByCategory",postsByCategory);
        String[] category_seo = dao2.getSeoInfo(cid);
        request.setAttribute("category_name",category_seo[0]);
        request.setAttribute("category_keyword",category_seo[1]);
        request.setAttribute("category_description",category_seo[2]);
        String[] index_seo = dao3.getIndexSeo();
        request.setAttribute("website_name",index_seo[0]);
        //再次传的cid用于分页按钮
        request.setAttribute("cid",cid);
        request.setAttribute("page",page);
        int prevPage = page - 1;
        int nextPage = page + 1;
        request.setAttribute("prevPage",prevPage);
        request.setAttribute("nextPage",nextPage);
        int lastPage = dao.getLastPage(cid);
        request.setAttribute("lastPage",lastPage);
        String pageSeo = "";
        if(page != 1){
            pageSeo = "|"+"第"+page+"页";
        }
        request.setAttribute("pageSeo",pageSeo);
        request.getRequestDispatcher("category.jsp").forward(request,response);
    }
    void getOnePost(HttpServletRequest request, HttpServletResponse response, PostDao dao, String pid, IndexDao dao2) throws ServletException, IOException {
        Post post = dao.getOnePost(pid);
        String category_id =post.getCategory_id();
        request.setAttribute("category_id",category_id);
        CategoryDao dao3 = new CategoryImpl();
        String[] category_seo = dao3.getSeoInfo(category_id);
        request.setAttribute("category_name",category_seo[0]);
        String[] indexSeo = dao2.getIndexSeo();
        request.setAttribute("website_name",indexSeo[0]);
        request.setAttribute("post",post);
        //获取"上一篇"、"下一篇"
        Post prevPost = dao.getPrevPost(pid);
        request.setAttribute("prevPost",prevPost);
        Post nextPost = dao.getNextPost(pid);
        request.setAttribute("nextPost",nextPost);
        //跳转到文章页
        request.getRequestDispatcher("article.jsp").forward(request,response);
    }
}

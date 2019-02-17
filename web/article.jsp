<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: sunjingyu
  Date: 2019-01-27
  Time: 21:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>${post.post_title}|${website_name}</title>
    <meta name="keywords" content="${post.post_tags}" />
    <meta name="description" content="${post.post_excerpt}" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="css/base.css" rel="stylesheet">
    <link href="css/m.css" rel="stylesheet">
    <script src="js/jquery-1.8.3.min.js" ></script>
    <script src="js/comm.js"></script>
    <!--[if lt IE 9]>
    <script src="js/modernizr.js"></script>
    <![endif]-->
</head>
<body>
<!--top begin-->
<jsp:include page="header.jsp"></jsp:include>
<!--top end-->
<article>
    <!--lbox begin-->
    <div class="lbox">
        <div class="content_box whitebg">
            <h2 class="htitle"><span class="con_nav">您现在的位置是：<a href="index.jsp">首页</a>><a href="PostServlet?method=getPostsByCategory&cid=${category_id}">${category_name}</a></span>${category_name}</h2>
            <h1 class="con_tilte">${post.post_title}</h1>
            <p class="bloginfo"><span>本文发布时间：${post.post_date}</span><span>发布于栏目【<a href="PostServlet?method=getPostsByCategory&cid=${category_id}">${category_name}</a>】</span></p>
            <div class="con_text">
                ${post.post_content}
                <div class="nextinfo">
                    <c:choose>
                    <c:when test="${prevPost.post_id != null}">
                    <p>上一篇：<a href="PostServlet?method=getOnePost&pid=${prevPost.post_id}">${prevPost.post_title}</a></p>
                    </c:when>
                    </c:choose>

                    <c:choose>
                    <c:when test="${nextPost.post_id != null}">
                    <p>下一篇：<a href="PostServlet?method=getOnePost&pid=${nextPost.post_id}">${nextPost.post_title}</a></p>
                    </c:when>
                    </c:choose>

                </div>
            </div>
        </div>
    </div>
    <!--lbox end-->
    <div class="rbox">
        <jsp:include page="slider.jsp"></jsp:include>
    </div>
</article>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>


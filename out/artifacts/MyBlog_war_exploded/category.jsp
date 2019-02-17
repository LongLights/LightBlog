<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: sunjingyu
  Date: 2019-01-27
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>${category_name}栏目${pageSeo}-${website_name}</title>
    <meta name="keywords" content="${category_keyword}" />
    <meta name="description" content="${category_description}" />
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
        <!--<div class="whitebg lanmu"> <img src="images/lm01.jpg">
            <h1>个人博客日记</h1>
            <p>个人博客日记，记录一些优秀个人站长是如何制作个人博客，建个人博客、以及经营个人网站的，本站还会推荐一些优秀的个人博客站长网站。</p>
        </div>-->
        <div class="whitebg bloglist">
            <h2 class="htitle">最新博文</h2>
            <ul>

                <c:forEach items="${postsByCategory}" var="post">
                    <c:choose>
                        <c:when test="${post.post_thumbnail == null}">
                            <!--纯文字-->
                            <li>
                                <h3 class="blogtitle"><a href="PostServlet?method=getOnePost&pid=${post.post_id}" target="_blank">${post.post_title}</a></h3>
                                <p class="blogtext">${post.post_excerpt}... </p>
                                <p class="bloginfo"><span>发布于 ${post.post_date}</span></p>
                                <a href="PostServlet?method=getOnePost&pid=${post.post_id}" class="viewmore">阅读更多</a> </li>
                        </c:when>
                        <c:otherwise>
                            <!--单图-->
                            <li>
                                <h3 class="blogtitle"><a href="PostServlet?method=getOnePost&pid=${post.post_id}" target="_blank">${post.post_title}</a></h3>
                                <span class="blogpic imgscale"><a href="PostServlet?method=getOnePost&pid=${post.post_id}" title=""><img src="${post.post_thumbnail}" alt="${post.post_title}"></a></span>
                                <p class="blogtext">${post.post_excerpt}... </p>
                                <p class="bloginfo"><span>发布于 ${post.post_date}</span></p>
                                <a href="PostServlet?method=getOnePost&pid=${post.post_id}" class="viewmore">阅读更多</a> </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

            </ul>
            <!--pagelist-->
            <div class="pagelist">
                <c:choose>
                    <c:when test="${page != 1}">
                        <c:if test="${page == 2}">
                            <a href="PostServlet?method=getPostsByCategory&cid=${cid}">上一页</a>
                        </c:if>
                        <c:if test="${page !=2}">
                            <a href="PostServlet?method=getPostsByCategory&cid=${cid}&page=${prevPage}">上一页</a>
                        </c:if>
                    </c:when>
                </c:choose>
                &nbsp;<b>${page}</b>&nbsp;
                <c:choose>
                    <c:when test="${page != lastPage}">
                        <a href="PostServlet?method=getPostsByCategory&cid=${cid}&page=${nextPage}">下一页</a>
                    </c:when>
                </c:choose>
            </div>
            <!--pagelist end-->
        </div>

        <!--bloglist end-->
    </div>
    <div class="rbox">
        <jsp:include page="slider.jsp"></jsp:include>
    </div>
</article>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>

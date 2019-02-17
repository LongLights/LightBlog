<!doctype html>
<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="IndexServlet"></jsp:include>
<head>
  <meta charset="utf-8">
  <title>${website_name}</title>
  <meta name="keywords" content="${website_keyword}" />
  <meta name="description" content="${website_description}" />
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
<jsp:include page="header.jsp"></jsp:include>
<article>
  <!--lbox begin-->
  <div class="lbox">
    <div class="clearblank"></div>
    <div class="tab_box whitebg">
        <h2 class="htitle">精选文章</h2>
      <div class="newstab">
        <div class="newsitem">
          <div class="newspic">
            <ul>
              <c:forEach items="${index_push_pic_posts}" var="pic_post">
              <li><a href="PostServlet?method=getOnePost&pid=${pic_post.post_id}"><img src="${pic_post.post_thumbnail}"><span>${pic_post.post_title}</span></a></li>
              </c:forEach>
            </ul>
          </div>
          <ul class="newslist">
            <c:forEach items="${index_push_posts}" var="index_push_posts">
            <li><i></i><a href="PostServlet?method=getOnePost&pid=${index_push_posts.post_id}">${index_push_posts.post_title}</a>
              <p>${index_push_posts.post_excerpt}</p>
            </li>
            </c:forEach>
          </ul>
        </div>
      </div>
    </div>
    <!--tab_box end-->
    <div class="whitebg bloglist">
      <h2 class="htitle">最新文章</h2>
      <ul>
        <c:forEach items="${new_posts}" var="post">
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
    </div>
    <!--bloglist end-->
    <div class="zhuanti whitebg">
      <h2 class="htitle"><span class="hnav"><c:forEach items="${categoryList}" var="cate"><a href="PostServlet?method=getPostsByCategory&cid=${cate.category_id}">${cate.category_name}</a></c:forEach></span>精彩专题</h2>
      <ul>
        <c:forEach items="${special_posts}" var="special_post">
          <li> <i class="ztpic"><a href="PostServlet?method=getOnePost&pid=${special_post.post_id}" target="_blank"><img src="${special_post.post_thumbnail}"></a></i> <a href="PostServlet?method=getOnePost&pid=${special_post.post_id}" target="_blank"><b>${special_post.post_title}</b></a><span>${special_post.post_excerpt}</span><a href="PostServlet?method=getOnePost&pid=${special_post.post_id}" target="_blank" class="readmore">文章阅读</a> </li>
        </c:forEach>
      </ul>
    </div>
    <!--<div class="ad whitebg"> <img src="images/longad.jpg"> </div>-->
  </div>
  <div class="rbox">
    <div class="whitebg notice">
      <h2 class="htitle">网站公告</h2>
      <ul>
        <c:forEach items="${announcement_posts}" var="announcement">
        <li><a href="PostServlet?method=getOnePost&pid=${announcement.post_id}">${announcement.post_title}</a></li>
        </c:forEach>
      </ul>
    </div>
      <jsp:include page="slider.jsp"></jsp:include>
    <!--<div class="links whitebg">
      <h2 class="htitle"><span class="sqlink"><a href="/">申请链接</a></span>友情链接</h2>
      <ul>
        <li><a href="http://www.yangqq.com" target="_blank">杨青青个人博客</a></li>
      </ul>
    </div>-->
  </div>
</article>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
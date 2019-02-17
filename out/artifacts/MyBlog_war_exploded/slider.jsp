<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: sunjingyu
  Date: 2019-01-30
  Time: 18:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="SliderServlet"></jsp:include>
<div class="whitebg paihang">
    <h2 class="htitle">随机文章</h2>
    <c:forEach items="${randList}" var="randPost" varStatus="count">
    <c:choose>
    <c:when test="${count.index==0}">
    <section class="topnews imgscale"><a href="PostServlet?method=getOnePost&pid=${randPost.post_id}"><img src="${randPost.post_thumbnail}"><span>${randPost.post_title}</span></a></section>
    <ul>
    </c:when>
        <c:otherwise>
        <li><i></i><a href="PostServlet?method=getOnePost&pid=${randPost.post_id}">${randPost.post_title}</a></li>
        </c:otherwise>
    </c:choose>
    </c:forEach>
    </ul>
</div>
<div class="whitebg tuijian">
    <h2 class="htitle">精选推荐</h2>
    <c:forEach items="${recList}" var="recPost" varStatus="count">
    <c:choose>
    <c:when test="${count.index == 0}">
    <section class="topnews imgscale"><a href="PostServlet?method=getOnePost&pid=${recPost.post_id}"><img src="${recPost.post_thumbnail}"><span>${recPost.post_title}</span></a></section>
    <ul>
    </c:when>
        <c:otherwise>
        <li><a href="PostServlet?method=getOnePost&pid=${recPost.post_id}"><i><img src="${recPost.post_thumbnail}"></i>
            <p>${recPost.post_title}</p>
        </a></li>
        </c:otherwise>
        </c:choose>
        </c:forEach>
    </ul>
</div>
<div class="ad whitebg imgscale">
    <ul>
        <a href="/"><img src="images/ad.jpg"></a>
    </ul>
</div>

<!--<div class="whitebg wenzi">
    <h2 class="htitle">猜你喜欢</h2>
    <ul>
        <li><a href="/">十条设计原则教你学会如何设计网页布局!</a></li>
    </ul>
</div>-->

<div class="whitebg tongji">
    <h2 class="htitle">站点信息</h2>
    <ul>
        <li><b>网站站长</b>：久光</li>
        <li><b>网站程序</b>：基于jsp/Servlet原创编写</li>
        <li><b>前段模板</b>：<a href="http://www.yangqq.com" target="_blank">《今夕何夕》</a></li>
        <li><b>统计数据</b>：<a href="https://tongji.baidu.com/">百度统计</a></li>
        <li><b>微信公众号</b>：扫描二维码，关注我们</li>
        <img src="https://i.v2ex.co/E3YNK7Xr.jpeg" class="tongji_gzh">
    </ul>
</div>
<div class="ad whitebg imgscale">
    <ul>
        <a href="/"><img src="images/ad.jpg"></a>
    </ul>
</div>
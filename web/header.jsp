<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: sunjingyu
  Date: 2019-01-26
  Time: 23:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--top begin-->
<jsp:include page="NavServlet"></jsp:include>
<header id="header">
    <div class="navbox">
        <h2 id="mnavh"><span class="navicon"></span></h2>
        <div class="logo"><a href="index.jsp">久光的个人博客</a></div>
        <nav>
            <ul id="starlist">
                <li><a href="index.jsp">首页</a></li>
                <c:forEach items="${categoryList}" var="categoryList">
                    <li><a href="PostServlet?method=getPostsByCategory&cid=${categoryList.category_id}">${categoryList.category_name}</a></li>
                </c:forEach>
                <!--<li class="menu"><a href="list2.html">博客网站制作</a>
                    <ul class="sub">
                        <li><a href="/6">推荐工具</a></li>
                        <li><a href="/7">JS经典实例</a></li>
                        <li><a href="/8">网站建设</a></li>
                        <li><a href="/7">CSS3|Html5</a></li>
                        <li><a href="/8">心得笔记</a></li>
                    </ul>
                    <span></span></li>-->
            </ul>
        </nav>
        <div class="searchico"></div>
    </div>
</header>
<div class="searchbox">
    <div class="search">
        <form action="/e/search/index.php" method="post" name="searchform" id="searchform">
            <input name="keyboard" id="keyboard" class="input_text" value="请输入关键字词" style="color: rgb(153, 153, 153);" onfocus="if(value=='请输入关键字词'){this.style.color='#000';value=''}" onblur="if(value==''){this.style.color='#999';value='请输入关键字词'}" type="text">
            <input name="show" value="title" type="hidden">
            <input name="tempid" value="1" type="hidden">
            <input name="tbname" value="news" type="hidden">
            <input name="Submit" class="input_submit" value="搜索" type="submit">
        </form>
    </div>
    <div class="searchclose"></div>
</div>
<!--top end-->

# LightBlog安装及使用说明
LightBlog是由本人原创编写的一套基于jsp/Servlet的博客程序，本程序演示站点[https://me.ugediao.com](https://me.ugediao.com)
## 安装说明


### 环境配置
1.服务器环境建议使用宝塔面板，宝塔面板中安装nginx、tomcat（建议安装tomcat8或9）、、mysql、phpmyadmin。

2.在宝塔面板的“安全”中，放行8080端口。

3.在宝塔面板中添加网站。
### 上传源码至服务器
1.下载Myblog.zip([下载地址1](https://github.com/LongLights/LightBlog/raw/master/MyBlog.zip)    [下载地址2](https://img.ugediao.com/code/MyBlog.zip))并解压。

2.解压后获得一个文件夹“MyBlog”和一个文件“DBUtils.java”。

3.编辑"DBUtils.java"文件，修改其中相应位置为自己服务器的数据库用户名及密码（相应位置有注释）。

4.在本地编译”DBUtils.java“文件(安装jdk后，键入命令“javac DBUtils.java”)，将编译出的“DBUtils.class”放入MyBlog/WEB-INF/classes/com/blog/utils/文件夹中。

5.将MyBlog内的所有文件上传至网站根目录（宝塔面板默认是/www/wwwroot/网站域名）。


### 配置Mysql数据库
1.登录phpmyadmin，新建一个名为“MyBlog”的数据库。
2.下载MyBlog.sql([下载地址](https://img.ugediao.com/code/MyBlog.sql))，并将其导入至刚刚创建的MyBlog数据库。

## 程序相关说明
本博客程序没有编写后台部分，发布文章、SEO设置、布局设置等需要直接在数据库中修改、插入内容，一些重要的说明如下：


### 数据表中一些重要字段说明
1.表“seo_settings”中，website_name字段代表网站名以及首页的title，website_keyword代表首页的keyword,website_description代表首页的description。
![BFC4F581-DC39-426D-B393-C9444DFB3F23.png](https://i.loli.net/2019/02/17/5c6970a7a8960.png)

2.表“layout_settings”中，index_announcement字段填写的数字为“公告”分类的id，该分类最新发布的5篇文章将显示在首页的侧栏“网站公告”中。

3.表“posts”存储网站内的所有文章，其中有一个字段“is_rec”，默认值为0，如果将某一篇文章的该字段设置为1，则该文章将会显示在首页的“精选文章中”。
![82D7BCD2-1DF3-4DCF-B52B-B2A3265B3DD0.jpeg](https://i.loli.net/2019/02/17/5c6972a837f46.jpeg)

## 补充说明
本博客程序前端模板来自杨青青编写的帝国CMS主题《今夕何夕》[https://www.yangqq.com/download/div/917.html](https://www.yangqq.com/download/div/917.html)，模板由本人向杨青青购买所得。

除前端部分以外，所有代码均是原创。
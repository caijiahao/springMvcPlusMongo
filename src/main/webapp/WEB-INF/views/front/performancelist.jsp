<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <jsp:include page="../inc.jsp"></jsp:include>
    <base href="<%=basePath%>">
    <jsp:include page="./inc-head.jsp"></jsp:include>
 <link href="${ctx}/style/index/performance.css" rel="stylesheet" media="screen">
	
  </head>
  
<body class="home blog">
 <!--start 登录注册浮动栏 -->
    <jsp:include page="./wpthemedemobar.jsp"></jsp:include>
 <!-- end 登录注册浮动栏 -->

   <!-- start:头部导航 -->
  <jsp:include page="./header.jsp"></jsp:include>
  <!-- end:头部导航-->

<!--head:所在位置-->
<div id="page_muen_nav">  <b>您现在所在的位置：</b><a href="${ctx}/front/web/base/index">首页</a> <a> &gt; </a><a href="${ctx}/front/web/base/getPerformanceList"> 绩效管理</a></div>
<!--end:所在位置-->

<!--head:内容项目-->
<div id="content">

<!--head:左侧内容-->
<div class="left_mian"> 
<!--head:查询历史-->
<div class="widget">
<ul>
<li>
	<div class="searchbox">
		<input type="text" id="input_search" autocomplete="off" value="" placeholder="搜索下乡关键字...">
		<button id="btn_search"><i class="search_icon"></i></button>
	</div>
</li>
</ul>
</div>
<!--end:查询历史-->

<!--head:扁平图标导航-->
  <jsp:include page="./navigation.jsp"></jsp:include>
<!--head:扁平图标导航-->

</div>
<!--end:左侧内容-->


<!--head:右侧内容-->
<div class="right_mian">

<!-- head:下乡绩效列表 -->
<div class="widget performancelist">
   <div class="widge_hd">
   <span>
       <b>梅头乡图片</b>
   </span>
   <p>
   	<span  class="performance_title">服务天数：</span>
   	<span  class="performance_days">14天 </span>
   </p>
  <a href="${ctx}/front/web/base/getPerformanceManage">详情</a>
   </div>
<div id="performanceitem1" class="caseleft" style="visibility: visible; overflow: hidden; position: relative; z-index: 2; left: 0px; width: 1000px;">
<ul style="margin: 0px; padding: 0px; position: relative; list-style-type: none; z-index: 1; width: 3000px; left: -1750px;">

<li style="overflow: hidden; float: left; width: 230px; height: 150px;">
<img src="${ctx}/uploadfile/p1.png">
</li>

<li style="overflow: hidden; float: left; width: 230px; height: 150px;">
<img src="${ctx}/uploadfile/p2.png">
</li>
<li style="overflow: hidden; float: left; width: 230px; height: 150px;">
<img src="${ctx}/uploadfile/p3.png">
</li>

<li style="overflow: hidden; float: left; width: 230px; height: 150px;">
<img src="${ctx}/uploadfile/p4.png">
</li>

</ul>
  <div class="loop_big_caj_nav">
<a class="prve"> &lt; 上一页</a>
<a class="next"> 下一页 &gt; </a>
</div>
</div>
<script>
$(function() {
$("#performanceitem1").jCarouselLite({
btnNext: "#performanceitem1 .next",
btnPrev: "#performanceitem1 .prve",
speed:1000,//滚动动画的时间
auto:3000,//滚动间隔时间
visible:4,
onMouse:true,
start:0,
easing: "easeOutCubic",
});
});
 </script> 
</div>

<div class="widget performancelist">
   <div class="widge_hd">
   <span>
       <b>上九里图片</b>
   </span>
   <p>
   	<span  class="performance_title">服务天数：</span>
   	<span  class="performance_days">6天 </span>
   </p>
  <a href="${ctx}/front/web/base/getPerformanceManage">详情</a>
   </div>
<div id="performanceitem2" class="caseleft" style="visibility: visible; overflow: hidden; position: relative; z-index: 2; left: 0px; width: 1000px;">
<ul style="margin: 0px; padding: 0px; position: relative; list-style-type: none; z-index: 1; width: 3000px; left: -1750px;">

<li style="overflow: hidden; float: left; width: 230px; height: 150px;">
<img src="${ctx}/uploadfile/p5.jpg">
</li>

<li style="overflow: hidden; float: left; width: 230px; height: 150px;">
<img src="${ctx}/uploadfile/p6.jpg">
</li>
<li style="overflow: hidden; float: left; width: 230px; height: 150px;">
<img src="${ctx}/uploadfile/p7.jpg">
</li>

<li style="overflow: hidden; float: left; width: 230px; height: 150px;">
<img src="${ctx}/uploadfile/p8.jpg">
</li>

</ul>
  <div class="loop_big_caj_nav">
<a class="prve"> &lt; 上一页</a>
<a class="next"> 下一页 &gt; </a>
</div>
</div>
<script>
$(function() {
$("#performanceitem2").jCarouselLite({
btnNext: "#performanceitem2 .next",
btnPrev: "#performanceitem2 .prve",
speed:1000,//滚动动画的时间
auto:3000,//滚动间隔时间
visible:4,
onMouse:true,
start:0,
easing: "easeOutCubic",
});
});
 </script> 
</div>

<!-- end:下乡绩效列表 -->

<!--head:列表分页-->
<div class="list_paginator">
	<a data-page="1" href="javascript:void(0);" class="paginator_current">1</a> 
	<a data-page="2" href="javascript:void(0);">2</a>
</div>
<!--end:列表分页-->
</div>
<!--end:右侧内容-->
</div>  
<!--end:内容项目-->

<!--head:底部信息-->
  <jsp:include page="./footer.jsp"></jsp:include>
<!--end:底部信息-->

<script type="text/javascript" src="${ctx}/jslib/index/thickbox.js"></script>
<script type="text/javascript" src="${ctx}/jslib/index/themepark.js"></script>
</body>
</html>

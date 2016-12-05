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
<div id="page_muen_nav">  <b>您现在所在的位置：</b><a href="${ctx}/front/web/base/index">首页</a> <a> &gt; </a><a href="${ctx}/front/web/base/getPerformanceList"> 绩效管理</a><a> &gt; </a><a href="#"> 梅头乡</a></div>
<!--end:所在位置-->

<!--head:内容项目-->
<div id="content">

<!--head:左侧内容-->
<div class="left_mian"> 
<!--head:查询历史-->
<div class="widget performancedetail">
<ul>
<li>
	<span>下乡目的地：<strong>梅头乡</strong></span>
</li>
<li>
	<span>出发日期：<strong>2016-05-01</strong></span>
</li>
<li>
	<span>返程日期：<strong>2016-05-20</strong></span>
</li>
<li>
	<span>服务天数：<strong>12天</strong></span>
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
<div class="widget performancelist2">
   <div class="widge_hd">
   <span>
       <b>资源列表</b>
   </span>
   </div>
<ul>
<li>
	<div class="del_btn">
		<i class="del_icon"></i>
	</div>
	<img src="${ctx}/uploadfile/p1.png">
</li>
<li>
	<div class="del_btn">
		<i class="del_icon"></i>
	</div>
	<img src="${ctx}/uploadfile/p2.png">
</li>
<li>
	<div class="del_btn">
		<i class="del_icon"></i>
	</div>
	<img src="${ctx}/uploadfile/p3.png">
</li>
<li>
	<div class="del_btn">
		<i class="del_icon"></i>
	</div>
	<img src="${ctx}/uploadfile/p4.png">
</li>
<li>
	<div class="del_btn">
		<i class="del_icon"></i>
	</div>
	<img src="${ctx}/uploadfile/p5.jpg">
</li>
</ul>
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

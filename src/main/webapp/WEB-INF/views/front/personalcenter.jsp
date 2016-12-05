<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	 <jsp:include page="../inc.jsp"></jsp:include>
    <base href="<%=basePath%>">
    <jsp:include page="./inc-head.jsp"></jsp:include>
	<link href="${ctx}/style/index/detailinputunit.css" rel="stylesheet" media="screen">
</head>
<body class="page page-id-2 page-parent page-template page-template-aboutus page-template-aboutus-php">

 <!--start 登录注册浮动栏 -->
    <jsp:include page="./wpthemedemobar.jsp"></jsp:include>
 <!-- end 登录注册浮动栏 -->

   <!-- start:头部导航 -->
  <jsp:include page="./header.jsp"></jsp:include>
  <!-- end:头部导航-->

<!--head:所在位置-->
<div id="page_muen_nav">  <b>您现在所在的位置：</b><a href="${ctx}/front/web/base/index">首页</a> <a> &gt; </a><a href="${ctx}/front/web/base/getPersonalCenter"> 个人中心</a></div>
<!--end:所在位置-->

<!--head:内容项目-->
<div id="content">

<!--head:左侧内容-->
<div class="left_mian"> 

<!--head:扁平图标导航-->
 <jsp:include page="./personalnavigation.jsp"></jsp:include>
<!--head:扁平图标导航-->

</div>
<!--end:左侧内容-->


<!--head:右侧内容-->
<div class="right_mian">

<!-- head:账号信息 -->
<div class="into_unit">.
	<c:if test="${sessionInfo==null}">
	        <p style="width: 100%;">
             <span><strong>您还未登录，请先登录。</strong></span>
            </p>

	
	</c:if>
	<c:if test="${sessionInfo!=null}">
	<div class="info_title" style="top: 15px; left: 60px;">
        	账号信息
	</div>
	<div class="info_detail">
      	<div>
        	<p style="width: 100%;">
             <span><strong>账号名</strong></span>
             <span>
             	<input id="logincode" name="loginName" type="text" placeholder="必填项" value="${sessionInfo.loginname}" readonly>
            </span>
            </p>
            </div>
            <div>
            <p style="float: left;">
            <span><strong>密&nbsp;&nbsp;&nbsp;码</strong></span>
            <span>
            	<input id="pwd" name="password" type="password" placeholder="输入新密码">
            </span>
            </p>
            <p style="float: left; margin-left: 30px;">
            	<input id="btn_editpwd" type="button" value="修改密码" class="edit_password_btn" style="margin: 20px auto 5px;">
            </p>
            </div>
	</div>

	
	</c:if>

</div>
<!-- end:账号信息 -->

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
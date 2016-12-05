<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!--start: not login-->
<c:if test="${sessionInfo==null && false}">
	<div id="wpthemedemobar"
		style="position:fixed!important;_position:absolute; _top:expression(eval(document.documentElement.scrollTop+0)); top:0px; z-index:99999;">
		<div class="wpthemedemobar_wrapper">
			<div class="wptdb_right">
				<a style="float:left;" href="${ctx}/front/web/base/getLoginPage"><div id="btnlogin"></div></a> 
				<a style="float:left;" href="${ctx}/front/web/base/getRegister"><div id="btnregister"></div> </a>
			</div>
			<div class="wptdb_current">
				当前：<span class="wptdb_themename">未登录</span>
			</div>
		</div>
	</div>
</c:if>
<!--end: not login-->
<!--start: have login-->

<c:if test="${sessionInfo!=null && false}">
	<div id="wpthemedemobar"
		style="position:fixed!important;_position:absolute; _top:expression(eval(document.documentElement.scrollTop+0)); top:0px; z-index:99999;">
		<div class="wpthemedemobar_wrapper">
			<div class="wptdb_right">

				<a style="float:left;" href="${ctx}/front/web/expert/logout"><div id="btnlogout"></div>
				</a>

			</div>
			<div class="wptdb_current">
				<span class="wptdb_themename"></span>
			</div>
		</div>
	</div>
</c:if>
<!--end: not login-->

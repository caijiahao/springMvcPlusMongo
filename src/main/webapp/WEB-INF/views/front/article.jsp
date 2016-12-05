<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../inc.jsp"></jsp:include>
<base href="<%=basePath%>">
<jsp:include page="./inc-head.jsp"></jsp:include>
<link href="${ctx}/style/index/article.css" rel="stylesheet" media="screen">
</head>
<body
	class="page page-id-2 page-parent page-template page-template-aboutus page-template-aboutus-php">

	<!--start 登录注册浮动栏 -->
	<jsp:include page="./wpthemedemobar.jsp"></jsp:include>
	<!-- end 登录注册浮动栏 -->

	<!-- start:头部导航 -->
	<jsp:include page="./header.jsp"></jsp:include>
	<!-- end:头部导航-->

	<!--head:所在位置-->
	<div id="page_muen_nav">
		<b>您现在所在的位置：</b>
		<a href="${ctx}/front/web/base/index">首页</a>
		<a> &gt; </a>
		<a href="${ctx}/front/news/base/getArticleList?categoryTypeCode=${categoryType.code}">${categoryType.text}</a>
		<a> &gt; </a>
		<a href="javascript:void(0);">${article.shortTitle}</a>
	</div>
	<!--end:所在位置-->

	<!--head:内容项目-->
	<div id="content">

		<!--head:左侧内容-->
		<div class="left_mian">
			<!--head:热门文章-->
			<div class="widget news_model">
				<div class="widge_hd">
					<span><b>${categoryType.text}</b></span>
				</div>
				<ul id="newsList">
					<c:forEach items="${frequencyNewsList}" var="fnl">
						<li><a href="${ctx}/front/news/base/getArticle?article=${fnl.autoID}" target="_blanck" title="${fnl.title}">${fnl.shortTitle}</a>
	            		<span class="time">${fnl.readCount}</span></li>
					</c:forEach>
				</ul>
			</div>
			<!--end:热门文章-->
		</div>
		<!--end:左侧内容-->


		<!--head:右侧内容-->
		<div class="right_mian">
			<!-- head:标题栏目 -->
			<div class="articletitle">
				<span class="title">${article.title}</span>
				<div class="time">
					${article.publishDate}
					&nbsp;&nbsp;
					${article.author}
					&nbsp;&nbsp;
					阅读量：${article.readCount}
				</div>
			</div>
			<!-- end:标题栏目 -->
			<!--head:文章正文-->
			<div class="enter">
				<iframe src="${ctx}/${article.webPath}" id="iframepage"
					frameborder="0" scrolling="no" marginheight="0" marginwidth="0"
					onLoad="iFrameHeight()" width="100%"></iframe>

			</div>
			<!--end:文章正文-->
			<c:if test="${!empty attachmentList}">
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td><span style="font-size:16px; line-height:2em;">附件：</span></td>
						<td><c:forEach items="${attachmentList}" var="attachment">
								<div style="display:block;line-height:2em;">
									<a style="font-size:16px; line-height:2em;"
										href="${ctx}/front/news/base/downloadAttachment?attachmentID=${attachment.autoID}"
										target="_blank">${attachment.metaDescription}</a>
								</div>
							</c:forEach></td>
					</tr>
				</table>
			</c:if>
		</div>
		<!--end:右侧内容-->

	</div>
	<!--end:内容项目-->


	<!--head:底部信息-->
	<jsp:include page="./footer.jsp"></jsp:include>
	<!--end:底部信息-->

	<script type="text/javascript" src="${ctx}/jslib/index/thickbox.js"></script>
	<script type="text/javascript" src="${ctx}/jslib/index/themepark.js"></script>
	<script type="text/javascript" language="javascript">
		function iFrameHeight() {
			var ifm = document.getElementById("iframepage");
			var subWeb = document.frames ? document.frames["iframepage"].document
					: ifm.contentDocument;
			if (ifm != null && subWeb != null) {
				ifm.height = subWeb.body.scrollHeight;
				//ifm.width = subWeb.body.scrollWidth;
			}
		}
	</script>
</body>
</html>
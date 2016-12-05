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
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<jsp:include page="../inc.jsp"></jsp:include>
<base href="<%=basePath%>">
<jsp:include page="./inc-head.jsp"></jsp:include>
<script type="text/javascript" src="${ctx}/jslib/index/jquery.easing.1.3.js"></script>
</head>

<body class="home blog">
	<!--start 登录注册浮动栏 -->
	<jsp:include page="./wpthemedemobar.jsp"></jsp:include>
	<!-- end 登录注册浮动栏 -->

	<!-- start:头部导航 -->
	<jsp:include page="./header.jsp"></jsp:include>
	<!-- end:头部导航-->

	<!--head:内容项目-->
	<div id="index_model">
		<div id="index_model_in">
			<div style="float: left; width: 800px;">
				<!-- head:最新新闻 -->
				<div class="widget" id="widget_news">
					<div class="widge_hd">
						<span> <b>新闻动态</b>
							<p>News</p> </span> <a href="${ctx}/front/news/base/getArticleList?categoryTypeCode=News" target="_blanck">更多</a>
					</div>
					<table id="articleList" width="330" border="0" cellspacing="0" cellpadding="0">
					<c:forEach items="${newsList}" var="nl">
						<tr>
							<td width="10"><span class="articleicon"></span></td>
							<td><a href="${ctx}/front/news/base/getArticle?article=${nl.autoID}" target="_blank" title="${nl.title}">${nl.shortTitle}</a></td>
							<td align="right" width="40">${nl.publishDate.toString().substring(0,10)}</td>
						</tr>
					</c:forEach>
					</table>
				</div>
				<!-- end:最新新闻 -->
				
				<!-- head:通知公告 -->
				<div class="widget" id="widget_inform">
					<div class="widge_hd">
						<span> <b>通知公告</b>
							<p>Information</p> </span> <a
							href="${ctx}/front/news/base/getArticleList?categoryTypeCode=Information" target="_blanck">更多</a>
					</div>
					<table id="informationList" width="330" border="0" cellspacing="0" cellpadding="0">
					<c:forEach items="${informationList}" var="il">
						<tr>
							<td width="10"><span class="articleicon"></span></td>
							<td><a href="${ctx}/front/news/base/getArticle?article=${il.autoID}" target="_blank" title="${il.title}">${il.shortTitle}</a></td>
							<td align="right" width="40">${il.publishDate.toString().substring(0,10)}</td>
						</tr>
					</c:forEach>
					</table>
				</div>
				<!-- end:通知公告 -->

				<!-- head:工作简报 -->
				<div class="widget" id="widget_news">
					<div class="widge_hd">
						<span> <b>工作简报</b>
							<p>Report</p> </span> <a href="${ctx}/front/news/base/getArticleList?categoryTypeCode=Report" target="_blanck">更多</a>
					</div>
					<table id="reportList" width="330" border="0" cellspacing="0" cellpadding="0">
					<c:forEach items="${reportList}" var="rl">
						<tr>
							<td width="10"><span class="articleicon"></span></td>
							<td><a href="${ctx}/front/news/base/getArticle?article=${rl.autoID}" target="_blank" title="${rl.title}">${rl.shortTitle}</a></td>
							<td align="right" width="40">${rl.publishDate.toString().substring(0,10)}</td>
						</tr>
					</c:forEach>
					</table>
				</div>
				<!-- end:工作简报 -->
				
				<!-- head:示范基地 -->
				<div class="widget" id="widget_inform">
					<div class="widge_hd">
						<span> <b>示范基地</b>
							<p>Base Construction</p> </span> <a
							href="${ctx}/front/news/base/getArticleList?categoryTypeCode=BaseConstruction" target="_blanck">更多</a>
					</div>
					<table id="baseList" width="330" border="0" cellspacing="0" cellpadding="0">
					<c:forEach items="${baseConstructionList}" var="bl">
						<tr>
							<td width="10"><span class="articleicon"></span></td>
							<td><a href="${ctx}/front/news/base/getArticle?article=${bl.autoID}" target="_blank" title="${bl.title}">${bl.shortTitle}</a></td>
							<td align="right" width="40">${bl.publishDate.toString().substring(0,10)}</td>
						</tr>
					</c:forEach>
					</table>
				</div>
				<!-- end:示范基地 -->
				
				<!-- head:农企信息 --
				<div class="widget" id="widget_company">
					<div class="widge_hd">
						<span> <b>农企信息</b>
							<p>Cooperative Company</p> </span> <a
							href="${ctx}/front/agribusiness/base/getCommpanyList">更多</a>
					</div>
					<ul id="companyList">

					</ul>
				</div>
				<!-- end:农企信息 -->
				
				<!--head:专家库展示-->
				<div class="widget_border"></div>
<div class="widget" id="widget_expert">
   <div class="widge_hd">
   <span>
       <b>专家库</b><p>Expert</p>
   </span>
  <a href="./">更多</a>
   </div>
<div id="expertList" class="caseleft" style="visibility: visible; overflow: hidden; position: relative; z-index: 2; left: 0px; width: 972px;">
<ul style="margin: 0px; padding: 0px; position: relative; list-style-type: none; z-index: 1; width: 2916px; left: -1458px;">
<li style="overflow: hidden; float: left; width: 230px; height: 150px;"><a href="./">
<img src="${ctx}/uploadfile/p1.png">
</a></li>

<li style="overflow: hidden; float: left; width: 230px; height: 150px;"><a href="./">
<img src="${ctx}/uploadfile/p2.png">
</a></li>
<li style="overflow: hidden; float: left; width: 230px; height: 150px;"><a href="./">
<img src="${ctx}/uploadfile/p3.png">
</a></li>

<li style="overflow: hidden; float: left; width: 230px; height: 150px;"><a href="./">
<img src="${ctx}/uploadfile/p4.png">
</a></li>

</ul>
<div class="loop_big_caj_nav">
<a class="prve"> &lt; 上一页</a>
<a class="next"> 下一页 &gt; </a>

</div>
</div>
 
 <script>
$(function() {
$("#expertList").jCarouselLite({
btnNext: "#expertList .next",
btnPrev: "#expertList .prve",
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
 <!--end:专家库展示-->

				<div class="widget_border"></div>

				<!-- head:相关链接 -->
				<div class="widget" id="widget_link">
					<div class="widge_hd">
						<span> <b>相关链接</b>
							<p>Link</p> </span>
					</div>
					<ul>
						<li><a href="http://www.scau.edu.cn/">华南农业大学官方网站</a>
						</li>
						<li><a
							href="http://meeting.tenchong.com/9?userParameter=streaming">视频会议系统</a>
						</li>
						<li><a href="http://localhost:8080/thingToInternet/">物联网监测平台</a>
						</li>
						<li><a
							href="http://125.88.25.226:8006/easyFarm/admin/index">华南农大农技推广服务综合管理平台</a>
						</li>
						<li><a href="http://info.scau.edu.cn/">数学与信息学院</a>
						</li>
						<li><a href="./">中华人民共和国农业部</a>
						</li>
					</ul>
				</div>
				<!-- end:相关链接 -->
			</div>
			<div style="float: left; width: 150px;">
				<!--head:扁平图标导航-->
				<jsp:include page="./navigation.jsp"></jsp:include>
				<!--head:扁平图标导航-->
			</div>
			
			<!-- head:在线交流 --
				<div class="widget" id="widget_question">
					<div class="widge_hd">
						<span> <b>在线交流</b>
							<p>Communication</p> </span> <a
							href="${ctx}/front/web/base/getQuestionList" target="_blanck">更多</a>
					</div>
					<ul id="ul_questionList">

					</ul>
					<script>
						//展开问题
						$(".question_expandable")
								.click(
										function() {
											$(this).hide();
											$(this)
													.nextAll(
															".question_content")
													.show();
											$(this)
													.nextAll(
															".question_actions")
													.find(".question_collapse")
													.show();
										});

						//折叠问题
						$(".question_collapse").click(
								function() {
									$(this).hide();
									$(this).parent(".question_actions")
											.prevAll(".question_content")
											.hide();
									$(this).parent(".question_actions")
											.prevAll(".question_expandable")
											.show();
								});
					</script>
				</div>
				<!-- end:在线交流 -->
			


			<!--head:滑动图片--
  <div class="widget" style="width:440px;">
<div id="pic_out" style="width:420px; height: 300px; margin: 0px;">
<div id="pic" style="visibility: visible; overflow: hidden; position: relative; z-index: 2; left: 0px; width: 420px; height: 320px; border: 0px;">
 <ul style="margin: 0px; padding: 0px; position: relative; list-style-type: none; z-index: 1; width: 4700px; left: -1880px;">
    <li style="overflow: hidden; float: left; width: 400px; height: 320px;"><a href="./"> <img src="${ctx}/uploadfile/p1.png"> </a>
    </li>                                      
		   
    <li style="overflow: hidden; float: left; width: 400px; height: 320px;"><a href="./"> <img src="${ctx}/uploadfile/p2.png"> </a>
    </li>
    
      <li style="overflow: hidden; float: left; width: 400px; height: 320px;"><a href="./"> <img src="${ctx}/uploadfile/p3.png"> </a>
    </li>
    
       <li style="overflow: hidden; float: left; width: 400px; height: 320px;"><a href="./"> <img src="${ctx}/uploadfile/p4.png"> </a>
    </li>
    </ul>

<a class="prve" style="left: 0.41492502334286px;"></a>
<a class="next" style="right: 0.41492502334286px;">  </a>
<script>
$(function() {
$("#pic").jCarouselLite({
btnNext: "#pic .next",
btnPrev: "#pic .prve",
speed:2000,//滚动动画的时间
auto:4000,//滚动间隔时间
visible:1,
onMouse:true,
start:0,
easing: "easeInOutBack",
});
});
</script>
      
</div>
</div>
</div>
  <!-- end:滑动图片 -->

			<!--head:视频中心--
<div class="widget" id="vedios">
   <div class="widge_hd">
   <span>
       <b>视频中心</b><p>Video Center</p>
   </span>
   <a href="./">MORE</a>
   </div>
<ul>
<div class="vedio_kuang">
  <iframe width="510" height="498" frameborder="0" allowfullscreen="" src="./showvideopage.html"></iframe>  
</div>

<li><a class="in" href="./">芽苗菜的高产栽培技术</a></li>
<li><a href="./">未来农业信息管理系统</a></li>
<li><a href="./">走进华农农业大学信息学院</a></li>

</ul>
</div>
<!--end:视频中心-->


		</div>
	</div>
	<!-- end:内容项目 -->

	<!--head:底部信息-->
	<jsp:include page="./footer.jsp"></jsp:include>
	<!--end:底部信息-->
	<script>
		$(document).ready(function() {
			//getNewsData();
			//getInformationData();
			//getCompanyData();
			//getQuestionData();
		});

		function getNewsData() {
			var pagesize = 8;
			var current = 1;

			$.ajax({
						url : "${ctx}/front/news/base/getListPublish",
						data : {
							"rows" : pagesize,
							"page" : current,
							"categoryType" : "news"
						},
						type : "POST",
						dataType : "json",
						success : function(json) {
							$("#articleList").html('');						
							for ( var i = 0; i < json.rows.length; i++) {
								var item = "<tr><td width='10'><span class='articleicon'></span></td>"
										+ "<td><a href='${ctx}/front/news/base/getArticle?article="
										+ json.rows[i].autoID
										+ "' target='_blank' title='" + json.rows[i].title + "'>"
										+ json.rows[i].title + "</a></td>";
								item += "<td align='right' width='40'>"
										+ json.rows[i].publishDate.split(" ")[0].substring(5,10) + "</td></tr>";
								$("#articleList").append(item);
							}
						},
						error : function(xhr, error, thrown) {

						}
					});
		}

		function getInformationData() {
			var pagesize = 8;
			var current = 1;

			$.ajax({
						url : "${ctx}/front/news/base/getListPublish",
						data : {
							"rows" : pagesize,
							"page" : current,
							"categoryType" : "information"
						},
						type : "POST",
						dataType : "json",
						success : function(json) {
							$("#informationList").html('');
							for ( var i = 0; i < json.rows.length; i++) {
								var item = "<tr><td width='10'><span class='articleicon'></span></td>"
										+ "<td><a href='${ctx}/front/news/base/getArticle?article="
										+ json.rows[i].autoID
										+ "' target='_blank' title='" + json.rows[i].title + "'>"
										+ json.rows[i].title + "</a></td>";
								item += "<td align='right' width='40'>"
										+ json.rows[i].publishDate.split(" ")[0].substring(5,10) + "</td></tr>";
								$("#informationList").append(item);
							}
						},
						error : function(xhr, error, thrown) {

						}
					});
		}

		function getCompanyData() {
			var pagesize = 4;
			var current = 1;

			$.ajax({
				url : "${ctx}/front/agribusiness/base/searchCompanyList",
				data : {
					"rows" : pagesize,
					"page" : current
				},
				type : "POST",
				dataType : "json",
				success : function(json) {
					$("#companyList").html('');
					for ( var i = 0; i < json.rows.length; i++) {
						var item = "<li><span href='javascript:void(0);'>"
								+ json.rows[i].businessName + "</span><br/><p>"
								+ json.rows[i].businessIntroduce;
						item += "<br/> " + json.rows[i].businessSite
								+ "</p></li>";
						$("#companyList").append(item);
					}
				},
				error : function(xhr, error, thrown) {

				}
			});
		}
		
		function getQuestionData() {

		$.ajax({
					url : '${ctx}/front/web/communicate/base/getQuestionList',
					data : {
						page : "1",
						rows : "3",
						sort : "autoID",
						order : "desc",
						searchKey : ""
					},
					cache : true,
					dataType : 'json',
					success : function(data) {
						$("#ul_questionList").empty();
						for ( var e in data.obj) {
							var str = '';
							str += $
									.formatString(
											'<li><a class="question_title" href="${ctx}/front/web/communicate/base/getQuestionPage?id={0}" target="_blank">{1}</a>',
											data.obj[e].autoID,
											data.obj[e].title);
							str += $
									.formatString(
											' <div class="question_expandable"><a class="toggle-expand">显示全部</a></div><div class="question_content" style="display: none;">{0}</div><div class="question_actions">',
											data.obj[e].content);
							str += $
									.formatString(
											' <a class="comments_count"><i class="icon_comment"></i><span class="highlight">{0}</span>条评论</a>',
											data.obj[e].commentCount);
							str += $
									.formatString('<button class="question_collapse" style="display: none;"><i></i>收起</button></div></li>');
							$('#ul_questionList').append(function() {
								return str;
							});
						}
						$(".question_expandable")
								.click(
										function() {
											$(this).hide();
											$(this)
													.nextAll(
															".question_content")
													.show();
											$(this)
													.nextAll(
															".question_actions")
													.find(".question_collapse")
													.show();
											$(this).nextAll(".comment_list")
													.show();

											//创建评论区
											var replybox = $(this).nextAll(
													".comment_list").find(
													".comment_replybox");
											if ($(replybox).length == 0) {
												var replyboxhtml = '<div class="comment_replybox"><a class="replybox_avatar" href="javascript:void(0);" onclick="return false"><img src="${ctx}/style/images/noavatar_default.png" alt=""></a><div class="replybox_textarea"><textarea name="message" placeholder="写下你的评论…"></textarea></div><div class="replybox_toolbar"><div class="replybox_gradient"></div><button class="replybox_button" type="submit">评论</button></div></div>';
												$(this)
														.nextAll(
																".comment_list")
														.append(replyboxhtml);
											}
										});

						//折叠问题
						$(".question_collapse").click(
								function() {
									$(this).hide();
									$(this).parent(".question_actions")
											.prevAll(".question_content")
											.hide();
									$(this).parent(".question_actions")
											.nextAll(".comment_list").hide();
									$(this).parent(".question_actions")
											.prevAll(".question_expandable")
											.show();
								});

						//点击已有评论的回复
						$(".comment_reply")
								.click(
										function() {
											var littlebox = $(this).parent(
													".comment_footer").next(
													".comment_replylittlebox");
											if ($(littlebox).length == 0) {
												var replyboxhtml = '<div class="comment_replylittlebox"><div class="replylittlebox_textarea"><textarea name="message" placeholder="写下你的评论…"></textarea></div><div class="replylittlebox_toolbar"><div class="replylittlebox_gradient"></div><button class="replylittlebox_button" type="submit">评论</button></div></div>';
												$(this)
														.parents(
																".comment_body")
														.append(replyboxhtml);
											} else {
												$(littlebox).show();
											}
										});
					}
				});
		}
	</script>
</body>
</html>


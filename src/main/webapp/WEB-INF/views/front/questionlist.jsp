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
<link href="${ctx}/style/index/comment.css" rel="stylesheet"
	media="screen">
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
		<b>您现在所在的位置：</b><a href="${ctx}/front/web/base/index">首页</a> <a>
			&gt; </a><a href="${ctx}/front/web/base/getQuestionList"> 在线交流</a>
	</div>
	<!--end:所在位置-->

	<!--head:内容项目-->
	<div id="content">

		<!--head:左侧内容-->
		<div class="left_mian">
			<!--head:查询提问-->
			<div class="widget">
				<ul>
					<li>
						<div class="searchquestion">
							<input type="text" id="input_search" autocomplete="off" value=""
								placeholder="搜索你感兴趣的内容...">
							<div onclick="search_indistinct();"
								style="width: 40px; height: 35px; position: absolute; top: 0px; right: 0px;"></div>
						</div> <!--<div class="searchbox">
		<input type="text" id="input_search" autocomplete="off" value="" placeholder="搜索你感兴趣的内容...">
		<button id="btn_search" onclick="search_indistinct();"><i class="search_icon"></i></button>
	</div>  -->
					</li>
					<li><a href="${ctx}/front/web/communicate/getAskQuestionPage"><div
								style="margin-top: 10px;" class="askquestion"></div> </a>
					</li>
				</ul>
			</div>
			<!--end:查询提问-->
		</div>
		<!--end:左侧内容-->


		<!--head:右侧内容-->
		<div class="right_mian">

			<div class="widget" id="widget_question">
				<ul id="ul_questionList">

				</ul>
				<!--head:列表分页-->
				<div class="list_paginator">
					<input id="selectedCategoryCode" type="hidden" value="" /> <input
						id="page_size" type="hidden" value="10" /> <input
						id="records_total" type="hidden" value="0" /> <a
						href="javascript:changePage(1);">上一页</a> <a id="current_page"
						currentpage="1" href="javascript:void(0); "
						class="paginator_current">1</a> <a
						href="javascript:changePage(2);">下一页</a>
				</div>
				<!--end:列表分页-->
			</div>

		</div>
		<!--end:右侧内容-->

	</div>
	<!--end:内容项目-->


	</div>
	<!--end:内容项目-->

	<!--head:底部信息-->
	<jsp:include page="./footer.jsp"></jsp:include>
	<!--end:底部信息-->

	<script type="text/javascript" src="${ctx}/jslib/index/thickbox.js"></script>
	<script type="text/javascript" src="${ctx}/jslib/index/themepark.js"></script>
	<script>
		$(document).ready(function() {
			getData();

			$("#input_search").keyup(function(event) {
				if (!event || event.which != 13)
					return true;
				search_indistinct();
			});
		});

		function changePage(flag) {
			var pagesize = parseInt($("#page_size").val());
			var current = parseInt($("#current_page").attr("currentpage"));
			var total = parseInt($("#records_total").val());
			if (flag == 1) {
				//上一页
				if (current <= 1) {
					$("#current_page").attr("currentpage", 1);
					alert("已经到达第一页");
					return;
				}
				current--;
			} else {
				//下一页
				var pageCount = parseInt(total / pagesize) == (total / pagesize) ? (total / pagesize)
						: ((total / pagesize) + 1);
				if ((current + 1) > pageCount) {
					alert("已经到达最后一页");
					return false;
				}
				current++;
			}
			$("#current_page").attr("currentpage", current);
			$("#current_page").text(current);
			getData();
		}

		function getData() {
			var searchKey = $("#input_search").val();
	  		searchKey = searchKey.trim();
	  		var pagesize = parseInt($("#page_size").val());
	  		var current = parseInt($("#current_page").attr("currentpage"));
	  		var sort = "autoID";
	  		var order = "desc";
	  		var url = "${ctx}/front/web/communicate/base/getQuestionList";
	  	
			$.ajax({
						url : url,
						data: { "rows": pagesize, "page": current, "sort" : sort, "order" : order, "searchKey": searchKey },
						cache : true,
						dataType : 'json',
						success : function(json) {
							$("#ul_questionList").html('');
							$("#records_total").val(json.total);
							for (var i = 0; i < json.rows.length; i++) {
								var str = '';
								str += $.formatString(
												'<li><a class="question_title" href="${ctx}/front/web/communicate/base/getQuestionPage?id={0}" target="_blank">{1}</a><div class="question_expandable">',
												json.rows[i].autoID,
												json.rows[i].title);
								if (json.rows[i].resourceMetaList != null) {
									str += $.formatString(' <img src="{0}">',
													json.rows[i].resourceMetaList[0].metaPath);
								}
								str += $.formatString('<a class="toggle-expand">显示全部</a></div><div class="question_content" style="display: none;">{0}</div><div class="question_actions">',
												json.rows[i].content);
								str += $.formatString(' <a class="comments_count"><i class="icon_comment"></i><span class="highlight">{0}</span>条评论</a>',
												json.rows[i].commentCount);
								str += $.formatString('<button class="question_collapse" style="display: none;"><i></i>收起</button>');
								str += $.formatString('<div style="float:right;">{0}</div></div></li>',json.rows[i].questionDate);
								$('#ul_questionList').append(str);
							}
							$(".question_expandable")
									.click(
											function() {
												$(this).hide();
												$(this).nextAll(
														".question_content")
														.show();
												$(this)
														.nextAll(
																".question_actions")
														.find(
																".question_collapse")
														.show();
												$(this)
														.nextAll(
																".comment_list")
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
															.append(
																	replyboxhtml);
												}
											});

							//折叠问题
							$(".question_collapse")
									.click(
											function() {
												$(this).hide();
												$(this)
														.parent(
																".question_actions")
														.prevAll(
																".question_content")
														.hide();
												$(this)
														.parent(
																".question_actions")
														.nextAll(
																".comment_list")
														.hide();
												$(this)
														.parent(
																".question_actions")
														.prevAll(
																".question_expandable")
														.show();
											});

							//点击已有评论的回复
							$(".comment_reply")
									.click(
											function() {
												var littlebox = $(this)
														.parent(
																".comment_footer")
														.next(
																".comment_replylittlebox");
												if ($(littlebox).length == 0) {
													var replyboxhtml = '<div class="comment_replylittlebox"><div class="replylittlebox_textarea"><textarea name="message" placeholder="写下你的评论…"></textarea></div><div class="replylittlebox_toolbar"><div class="replylittlebox_gradient"></div><button class="replylittlebox_button" type="submit">评论</button></div></div>';
													$(this)
															.parents(
																	".comment_body")
															.append(
																	replyboxhtml);
												} else {
													$(littlebox).show();
												}
											});

						}
					});
		}

		function search_indistinct() {
			$("#current_page").attr("currentpage", 1);
	  		$("#current_page").text(1);
			getData();
		}

		//展开问题
		$(".question_expandable")
				.click(
						function() {
							$(this).hide();
							$(this).nextAll(".question_content").show();
							$(this).nextAll(".question_actions").find(
									".question_collapse").show();
							$(this).nextAll(".comment_list").show();

							//创建评论区
							var replybox = $(this).nextAll(".comment_list")
									.find(".comment_replybox");
							if ($(replybox).length == 0) {
								var replyboxhtml = '<div class="comment_replybox"><a class="replybox_avatar" href="javascript:void(0);" onclick="return false"><img src="${ctx}/style/images/noavatar_default.png" alt=""></a><div class="replybox_textarea"><textarea name="message" placeholder="写下你的评论…"></textarea></div><div class="replybox_toolbar"><div class="replybox_gradient"></div><button class="replybox_button" type="submit">评论</button></div></div>';
								$(this).nextAll(".comment_list").append(
										replyboxhtml);
							}
						});

		//折叠问题
		$(".question_collapse").click(
				function() {
					$(this).hide();
					$(this).parent(".question_actions").prevAll(
							".question_content").hide();
					$(this).parent(".question_actions")
							.nextAll(".comment_list").hide();
					$(this).parent(".question_actions").prevAll(
							".question_expandable").show();
				});

		//点击已有评论的回复
		$(".comment_reply")
				.click(
						function() {
							var littlebox = $(this).parent(".comment_footer")
									.next(".comment_replylittlebox");
							if ($(littlebox).length == 0) {
								var replyboxhtml = '<div class="comment_replylittlebox"><div class="replylittlebox_textarea"><textarea name="message" placeholder="写下你的评论…"></textarea></div><div class="replylittlebox_toolbar"><div class="replylittlebox_gradient"></div><button class="replylittlebox_button" type="submit">评论</button></div></div>';
								$(this).parents(".comment_body").append(
										replyboxhtml);
							} else {
								$(littlebox).show();
							}
						});
	</script>
</body>
</html>

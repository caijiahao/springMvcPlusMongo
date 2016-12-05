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
	<link href="${ctx}/style/index/comment.css" rel="stylesheet" media="screen">
	<link href="${ctx}/style/index/personalquestionlist.css" rel="stylesheet" media="screen">
  </head>
  
<body class="home blog">
 <!--start 登录注册浮动栏 -->
    <jsp:include page="./wpthemedemobar.jsp"></jsp:include>
 <!-- end 登录注册浮动栏 -->

   <!-- start:头部导航 -->
  <jsp:include page="./header.jsp"></jsp:include>
  <!-- end:头部导航-->

<!--head:所在位置-->
<div id="page_muen_nav">  <b>您现在所在的位置：</b><a href="${ctx}/front/web/base/index">首页</a> <a> &gt; </a><a href="${ctx}/front/web/communicate/getPersonalQuestionList"> 查看个人问答</a></div>
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

<div class="personaltabs" id="personal_message_tabs" style="display: block;">
<div class="personal_message_head" id="personal_message_tabs_inner">
<div class="personal_message_container clearfix">
<button class="personal_message_item" action="atmessage">
<span class="icon">提到我的消息</span>
</button>
<button class="personal_message_item current" action="recvmessage">
<span class="icon">我已发布的消息</span>
</button>
</div>
</div>
<div class="personal_message_border"></div>
</div>
<!--控制tabs标题-->
<script>
var arg = {
	page :"1",
	rows :"10",
	sort :"autoID",
	order:"desc",
	personalID:"${sessionInfo.id}"
};

recvmessage();

$(".personal_message_item").click(function(){
	var action = $(this).attr("action");
	$(this).parent(".personal_message_container").find(".personal_message_item").each(function(){
		if($(this).hasClass("current")){
			$(this).removeClass("current");
		}
	});
	$(this).addClass("current");
	// 根据入参action的不同值，调用方法获取不同的数据
	if($(this).attr('action')=='atmessage')
	{
		arg.page=1;
		atemessage();
	
	}
	else{

	    arg.page=1;
		recvmessage();
	}
});

function recvmessage()
{
		$.ajax({
		url:'${ctx}/front/web/communicate/question/getRecMessage',
		data:arg,
		cache:false,
		dataType:'json',
		success:function(data){
$("#ul_questionList").empty();
				for(var e in data.obj)
				{
				var str = '';
				 str += $.formatString('<li><a class="question_title" href="${ctx}/front/web/communicate/base/getQuestionPage?id={0}" target="_blank">{1}</a>',data.obj[e].autoID,data.obj[e].title);
				 str += $.formatString(' <div class="question_expandable"><a class="toggle-expand">显示全部</a></div><div class="question_content" style="display: none;">{0}</div><div class="question_actions">',data.obj[e].content);
				 str += $.formatString(' <a class="comments_count"><i class="icon_comment"></i><span class="highlight">{0}</span>条评论</a>',data.obj[e].commentCount);
		         str += $.formatString('<button class="question_collapse" style="display: none;"><i></i>收起</button></div></li>');
				 $('#ul_questionList').append(function(){return str;} );
				}
				$(".question_expandable").click(function(){
		        $(this).hide();
				$(this).nextAll(".question_content").show();
				$(this).nextAll(".question_actions").find(".question_collapse").show();
				$(this).nextAll(".comment_list").show();
		
					//创建评论区
					var replybox = $(this).nextAll(".comment_list").find(".comment_replybox");
					if($(replybox).length == 0){
						var replyboxhtml ='<div class="comment_replybox"><a class="replybox_avatar" href="javascript:void(0);" onclick="return false"><img src="${ctx}/style/images/noavatar_default.png" alt=""></a><div class="replybox_textarea"><textarea name="message" placeholder="写下你的评论…"></textarea></div><div class="replybox_toolbar"><div class="replybox_gradient"></div><button class="replybox_button" type="submit">评论</button></div></div>';
						$(this).nextAll(".comment_list").append(replyboxhtml);
					}
				});

				//折叠问题
				$(".question_collapse").click(function(){
					$(this).hide();
					$(this).parent(".question_actions").prevAll(".question_content").hide();
					$(this).parent(".question_actions").nextAll(".comment_list").hide();
					$(this).parent(".question_actions").prevAll(".question_expandable").show();
				});

				//点击已有评论的回复
				$(".comment_reply").click(function(){
					var littlebox = $(this).parent(".comment_footer").next(".comment_replylittlebox");
					if($(littlebox).length == 0){
						var replyboxhtml = '<div class="comment_replylittlebox"><div class="replylittlebox_textarea"><textarea name="message" placeholder="写下你的评论…"></textarea></div><div class="replylittlebox_toolbar"><div class="replylittlebox_gradient"></div><button class="replylittlebox_button" type="submit">评论</button></div></div>';
						$(this).parents(".comment_body").append(replyboxhtml);
					}
					else{
						$(littlebox).show();
					}
				});
		}
	});
	
	   /*  暂不需要  author:yeyaowen
		$.ajax({
		url:'${ctx}/front/web/communicate/comment/getRecMessage',
		data:{personalID:"${sessionInfo.id}"},
		cache:false,
		dataType:'json',
		success:function(data){
			$("#ul_commentList").empty();
				for(var e in data.obj)
				{
				var str = '';
				 str += $.formatString('<li><a href="${ctx}/front/web/communicate/getQuestionPage?id={0}">在问题：<span class="highline">{1}</span>回复：</a><span class="time">{2}</span><p>{3}</p></li>',data.obj[e].questionID,data.obj[e].questionTitle,data.obj[e].createDate,data.obj[e].content);

				 $('#ul_commentList').append(function(){return str;} );
				}
			
			
		}
	});
	*/
}

function atemessage()
{
	
		$.ajax({
		url:'${ctx}/front/web/communicate/question/getAtMessage',
		data:arg,
		cache:true,
		dataType:'json',
		success:function(data){
				$("#ul_questionList").empty();
				for(var e in data.obj)
				{
				var str = '';
				 str += $.formatString('<li><a class="question_title" href="${ctx}/front/web/communicate/base/getQuestionPage?id={0}" target="_blank">{1}</a>',data.obj[e].autoID,data.obj[e].title);
				 str += $.formatString(' <div class="question_expandable"><a class="toggle-expand">显示全部</a></div><div class="question_content" style="display: none;">{0}</div><div class="question_actions">',data.obj[e].content);
				 str += $.formatString(' <a class="comments_count"><i class="icon_comment"></i><span class="highlight">{0}</span>条评论</a>',data.obj[e].commentCount);
		         str += $.formatString('<button class="question_collapse" style="display: none;"><i></i>收起</button></div></li>');
				 $('#ul_questionList').append(function(){return str;} );
				}
				$(".question_expandable").click(function(){
		        $(this).hide();
				$(this).nextAll(".question_content").show();
				$(this).nextAll(".question_actions").find(".question_collapse").show();
				$(this).nextAll(".comment_list").show();
		
					//创建评论区
					var replybox = $(this).nextAll(".comment_list").find(".comment_replybox");
					if($(replybox).length == 0){
						var replyboxhtml ='<div class="comment_replybox"><a class="replybox_avatar" href="javascript:void(0);" onclick="return false"><img src="${ctx}/style/images/noavatar_default.png" alt=""></a><div class="replybox_textarea"><textarea name="message" placeholder="写下你的评论…"></textarea></div><div class="replybox_toolbar"><div class="replybox_gradient"></div><button class="replybox_button" type="submit">评论</button></div></div>';
						$(this).nextAll(".comment_list").append(replyboxhtml);
					}
				});

				//折叠问题
				$(".question_collapse").click(function(){
					$(this).hide();
					$(this).parent(".question_actions").prevAll(".question_content").hide();
					$(this).parent(".question_actions").nextAll(".comment_list").hide();
					$(this).parent(".question_actions").prevAll(".question_expandable").show();
				});

				//点击已有评论的回复
				$(".comment_reply").click(function(){
					var littlebox = $(this).parent(".comment_footer").next(".comment_replylittlebox");
					if($(littlebox).length == 0){
						var replyboxhtml = '<div class="comment_replylittlebox"><div class="replylittlebox_textarea"><textarea name="message" placeholder="写下你的评论…"></textarea></div><div class="replylittlebox_toolbar"><div class="replylittlebox_gradient"></div><button class="replylittlebox_button" type="submit">评论</button></div></div>';
						$(this).parents(".comment_body").append(replyboxhtml);
					}
					else{
						$(littlebox).show();
					}
				});
		}
	});
}

function pageing(ifup)
{
	if(ifup==1)
	{
		if(arg.page==1)
		{
			alert("已经是首页");
			return ;
		}
		else
		{
			arg.page--;
		}
	}
	else
	{
		arg.page++;
	}
	if($(".personal_message_container .current").attr("action")=='atmessage')
	{atemessage();}
    else
	{recvmessage(); }
}

</script>

<div class="widget" id="widget_question">
<ul id="ul_questionList">
    
	<c:if test="${!empty questionList}">
	<c:forEach items="${questionList}" var="n">
	<!--head:一条问题-->
	<li>
		<a class="question_title" href="${ctx}/front/web/communicate/base/getQuestionPage?id=${n.autoID}">${n.title}</a>
		<div class="question_expandable">
			${n.content}
			<a class="toggle-expand">显示全部</a>
		</div>
		<div class="question_actions">
			<a class="comments_count"><i class="icon_comment"></i><span class="highlight">${n.commentCount}</span>条评论</a>
			<a class="like_count"><i class="icon_like"></i><span class="highlight">${n.likeCount}</span>个喜欢</a>
			<button class="question_collapse" style="display: none;"><i></i>收起</button>
		</div>
	</li>
	<!--end:一条问题-->
	<!--head:一条问题-->
	</c:forEach>
	</c:if>
	
	
</ul>
<!--head:列表分页-->
<div class="list_paginator">
	<a  href="javascript:pageing(1);">上一页</a>
	<a  id="now_page" href="javascript:void(0);" class="paginator_current">1</a> 
	<a  href="javascript:pageing(0);">下一页</a> 

</div>
<!--end:列表分页-->

<script>
//展开问题
$(".question_expandable").click(function(){
	$(this).hide();
	$(this).nextAll(".question_content").show();
	$(this).nextAll(".question_actions").find(".question_collapse").show();
	$(this).nextAll(".comment_list").show();
	
	//创建评论区
	var replybox = $(this).nextAll(".comment_list").find(".comment_replybox");
	if($(replybox).length == 0){
		var replyboxhtml ='<div class="comment_replybox"><a class="replybox_avatar" href="javascript:void(0);" onclick="return false"><img src="./styles/images/noavatar_default.png" alt=""></a><div class="replybox_textarea"><textarea name="message" placeholder="写下你的评论…"></textarea></div><div class="replybox_toolbar"><div class="replybox_gradient"></div><button class="replybox_button" type="submit">评论</button></div></div>';
		$(this).nextAll(".comment_list").append(replyboxhtml);
	}
});

//折叠问题
$(".question_collapse").click(function(){
	$(this).hide();
	$(this).parent(".question_actions").prevAll(".question_content").hide();
	$(this).parent(".question_actions").nextAll(".comment_list").hide();
	$(this).parent(".question_actions").prevAll(".question_expandable").show();
});

//点击已有评论的回复
$(".comment_reply").click(function(){
	var littlebox = $(this).parent(".comment_footer").next(".comment_replylittlebox");
	if($(littlebox).length == 0){
		var replyboxhtml = '<div class="comment_replylittlebox"><div class="replylittlebox_textarea"><textarea name="message" placeholder="写下你的评论…"></textarea></div><div class="replylittlebox_toolbar"><div class="replylittlebox_gradient"></div><button class="replylittlebox_button" type="submit">评论</button></div></div>';
		$(this).parents(".comment_body").append(replyboxhtml);
	}
	else{
		$(littlebox).show();
	}
});
</script>
</div>

<div class="widget answerlist" id="widget_anserlist">
<ul id="ul_commentList">
    <c:if test="${!empty commentList}">
	   <c:forEach items="${commentList}" var="comment">
	<li><a href="${ctx}/front/web/communicate/getQuestionPage?id=${comment.questionID}">在问题：<span class="highline">${comment.questionTitle}</span>回复：</a><span class="time">${comment.createDate}</span><p>${comment.content}</p></li>
	   </c:forEach>
	</c:if>
</ul>
</div>

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

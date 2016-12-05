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
	 <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <base href="<%=basePath%>">
    <jsp:include page="./inc-head.jsp"></jsp:include>
	<link href="${ctx}/style/index/comment.css" rel="stylesheet" media="screen">
	<link href="${ctx}/jslib/Uploadify/uploadify.css" rel="stylesheet" type="text/css" />
    <script src="${ctx}/jslib/Uploadify/swfobject.js" type="text/javascript"></script>
    <script src="${ctx}/jslib/Uploadify/jquery.uploadify.min.js" type="text/javascript"></script>
	 <script type="text/javascript" src="${ctx}/jslib/index/jquery.easing.1.3.js"></script>
	  <script type="text/javascript" src="${ctx}/jslib/index/lrscroll.js"></script> 
	
	<script>
		var arg = {
	page :"1",
	rows :"10",
	sort :"autoID",
	order:"desc",
	questionID:"${question.autoID}"
    };
	
	$(function(){
	$('#commentform').form({
		    url:'${ctx}/front/web/communicate/comment/add',
		    onSubmit : function() {
		    	progressLoad();
				var isValid = $(this).form('validate');
				if(!isValid){
					progressClose();
				}
				return isValid;
			},
		    success:function(result){
		    	result = $.parseJSON(result);
		    	progressClose();
		    	if (result.success) {
		    		window.location.href='${ctx}/front/web/communicate/base/getQuestionPage?id=${question.autoID}';
		    	}else{
		    		$.messager.show({
		    			title:'提示',
		    			msg:'<div class="light-info"><div class="light-tip icon-tip"></div><div>'+result.msg+'</div></div>',
		    			showType:'show'
		    		});
		    	}
		    }
		});
		
			
	});
	getcomment();
	function getcomment()
    {
	
		$.ajax({
		url:'${ctx}/front/web/communicate/base/getCommentList',
		data:arg,
		cache:true,
		dataType:'json',
		success:function(data){
			$("#ul_commentList").empty();
				for(var e in data.obj)
				{
				var str = '';
				 str += $.formatString('<li class="commentitem"><div class="comment_post"><div class="avatar"><a target="_blank" href="#" title="{0}">',data.obj[e].commentName);
				 str += $.formatString('<img src="./styles/images/noavatar_default.png" alt="{0}"></a></div><div class="comment_body">',data.obj[e].commentName);
				 str += $.formatString('<div class="comment_header"><a class="user_name" href="#" target="_blank">{0}</a></div><p>{1}</p><div class="comment_footer"><span class="comment_time" title="">${n.createDate}</span>',data.obj[e].commentName,data.obj[e].content);
		         str += $.formatString('<a class="comment_reply" href="javascript:void(0);" rel="{0}"><span class="comment_icon comment_icon_reply"></span>回复</a></div></div></div></li>',data.obj[e].autoID);
				 $('#ul_commentList').append(function(){return str;} );
				}
				$(".comment_reply").click(function(){
				    var littlebox = $(this).parent(".comment_footer").next(".comment_replylittlebox");
					if($(littlebox).length == 0){
						var cID=$(this).attr("rel");
						var input1='<input type="hidden" name="commentID" value="'+cID+'">';
						var replyboxhtml = '<form method="post" id="replycommentform">'+input1+'<input type="hidden" name="questionID" value="${question.autoID}"></input></input><div class="comment_replylittlebox"><div class="replylittlebox_textarea"><textarea name="content" placeholder="写下你的评论…"></textarea></div><div class="replylittlebox_toolbar"><div class="replylittlebox_gradient"></div><button class="replylittlebox_button" type="submit">评论</button></div></div></form>';
						$(this).parents(".comment_body").append(replyboxhtml);
						
						$('#replycommentform').form({
							url:'${ctx}/front/web/communicate/comment/add',
							onSubmit : function() {
								progressLoad();
								var isValid = $(this).form('validate');
								if(!isValid){
									progressClose();
								}
								return isValid;
							},
							success:function(result){
								result = $.parseJSON(result);
								progressClose();
								if (result.success) {
									window.location.href='${ctx}/front/web/communicate/base/getQuestionPage?id=${question.autoID}';
								}else{
									$.messager.show({
										title:'提示',
										msg:'<div class="light-info"><div class="light-tip icon-tip"></div><div>'+result.msg+'</div></div>',
										showType:'show'
									});
								}
							}
						});
						
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
	getcomment();
}
	
	</script>
	
</head>
<body class="page page-id-2 page-parent page-template page-template-aboutus page-template-aboutus-php">

 <!--start 登录注册浮动栏 -->
    <jsp:include page="./wpthemedemobar.jsp"></jsp:include>
 <!-- end 登录注册浮动栏 -->

   <!-- start:头部导航 -->
  <jsp:include page="./header.jsp"></jsp:include>
  <!-- end:头部导航-->

<!--head:所在位置-->
<div id="page_muen_nav">  <b>您现在所在的位置：</b><a href="${ctx}/front/web/base/index">首页</a> <a> &gt; </a><a href="${ctx}/front/web/communicate/getQuestionList"> 在线交流</a></div>
<!--end:所在位置-->

<!--head:内容项目-->
<div id="content">

<!--head:左侧内容-->
<div class="left_mian"> 
<!--head:查询提问-->
<div class="widget">
<ul>
<li>
	<div class="searchbox">
		<input type="text" id="input_search" autocomplete="off" value="" placeholder="搜索你感兴趣的内容...">
		<button id="btn_search"><i class="search_icon"></i></button>
	</div>
</li>
<li>
	<a href="${ctx}/front/web/communicate/getAskQuestionPage" target="_blank"><button class="askquestion" id="btn_askquestion">提问</button></a>
</li>
</ul>
</div>
<!--end:查询提问-->

<!--head:扁平图标导航-->
  <jsp:include page="./navigation.jsp"></jsp:include>
<!--head:扁平图标导航-->

</div>
<!--end:左侧内容-->


<!--head:右侧内容-->
<div class="right_mian">


<div class="widget" id="widget_question">
    <c:if test="${!empty resourceMetaList}">
    <!--head:文章开头的图片集-->
		<div id="enter_xz" class="smaoll_xzs" style="visibility: visible; overflow: hidden; position: relative; z-index: 2; left: 0px; width: 600px;">
        		<ul id="gallery-1" class="gallery_xz" style="margin: 0px; padding: 0px; position: relative; list-style-type: none; z-index: 1; width: 4200px; left: -3000px;">
        			
			    
				  <c:forEach items="${resourceMetaList}" var="e">
					 <li class="gallery-item" style="overflow: hidden; float: left; width: 600px; height: 400px;">
					   <a><img src="${e.metaPath}"></a>
				     </li>
				  </c:forEach>
				
             </ul>
             <a class="next" style="right: -100px;"></a><a class="prve" style="left: -100px;"></a>
       </div>
	<!--end:文章开头的图片集-->
	</c:if>
	
		<a class="question_title" >${question.title}</a>
		<div class="question_content">
		${question.content}	
		</div>
		<div class="question_actions">
			<a class="comments_count"><i class="icon_comment"></i><span class="highlight">${question.commentCount}</span>条评论</a>
			<a class="like_count"><i class="icon_like"></i><span class="highlight">${question.likeCount}</span>个喜欢</a>
			<button class="question_collapse" style="display: none;"><i></i>收起</button>
		</div>
			<!--head:评论区域-->
		 <div class="comment_list">
			<!--head:评论列表-->
			<ul id="ul_commentList">		
			
			</ul>
			<!--end:评论列表-->
			<!--head:评论分页-->
			<div class="comment_paginator">
				    <a  href="javascript:pageing(1);">上一页</a>
	               <a  id="now_page" href="javascript:void(0);" class="paginator_current">1</a> 
	               <a  href="javascript:pageing(0);">下一页</a> 
			</div>
			<!--end:评论分页-->
			<form method="post" id="commentform">
			<input type="hidden" name="questionID" value="${question.autoID}"></input>
			<input type="hidden" name="commentID" value="0"></input>
			<div class="comment_replybox">
				<a class="replybox_avatar" href="javascript:void(0);" onclick="return false"><img src="./styles/images/noavatar_default.png" alt=""></a>
				<div class="replybox_textarea"><textarea name="content" placeholder="写下你的评论…"></textarea></div>
				<div class="replybox_toolbar">
					<div class="replybox_gradient"></div>
					<button class="replybox_button" type="submit">评论</button>
				</div>
			</div>
			</form>
		</div>
		<!--end:评论区域-->
<script>
//点击已有评论的回复
$(".comment_reply").click(function(){
	var littlebox = $(this).parent(".comment_footer").next(".comment_replylittlebox");
	if($(littlebox).length == 0){
		var cID=$(this).attr("rel");
		var input1='<input type="hidden" name="commentID" value="'+cID+'">';
		var replyboxhtml = '<form method="post" id="replycommentform">'+input1+'<input type="hidden" name="questionID" value="${question.autoID}"></input></input><div class="comment_replylittlebox"><div class="replylittlebox_textarea"><textarea name="content" placeholder="写下你的评论…"></textarea></div><div class="replylittlebox_toolbar"><div class="replylittlebox_gradient"></div><button class="replylittlebox_button" type="submit">评论</button></div></div></form>';
		$(this).parents(".comment_body").append(replyboxhtml);
		
		$('#replycommentform').form({
		    url:'${ctx}/front/web/communicate/comment/add',
		    onSubmit : function() {
		    	progressLoad();
				var isValid = $(this).form('validate');
				if(!isValid){
					progressClose();
				}
				return isValid;
			},
		    success:function(result){
		    	result = $.parseJSON(result);
		    	progressClose();
		    	if (result.success) {
		    		window.location.href='${ctx}/front/web/communicate/base/getQuestionPage?id=${question.autoID}';
		    	}else{
		    		$.messager.show({
		    			title:'提示',
		    			msg:'<div class="light-info"><div class="light-tip icon-tip"></div><div>'+result.msg+'</div></div>',
		    			showType:'show'
		    		});
		    	}
		    }
		});
		
	}
	else{
		$(littlebox).show();
	}
});
</script>
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
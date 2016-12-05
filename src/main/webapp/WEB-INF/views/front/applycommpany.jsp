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
	<title>农技推广综合管理平台</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="IE=9" >
	<link rel="shortcut icon" href="${ctx}/style/images/ico.ico" type="image/x-icon" />
	<link href="${ctx}/style/index/themepark.css" rel="stylesheet" media="screen">
	<link href="${ctx}/style/index/default.css" rel="stylesheet" media="screen">
	<link href="${ctx}/style/index/detailinputunit.css" rel="stylesheet" media="screen">
	<link href="${ctx}/style/index/commpany.css" rel="stylesheet" media="screen">
  
 	<style>
 		.question_toolbar{position: relative; width: 100%; box-shadow: 0 1px 0 rgba(255,255,255,0.6);}
		.question_gradient{position: relative; margin-right: 100px; height: 30px; border: 1px solid #ccc; border-right: none; border-bottom-color: #aaa; border-bottom-left-radius: 3px; -webkit-border-bottom-left-radius: 3px;}
		.question_button{outline:none;cursor: pointer; font-family: "Helvetica Neue",Helvetica,Arial,sans-serif; position: absolute; right: 0; top: 0; height: 32px; width: 100px; text-align: center; text-shadow: 0 1px 0 #fff; color: #555; font-size: 18px; font-weight: bold; border: 1px solid #ccc; border-bottom-color: #aaa; border-bottom-right-radius: 3px;  -webkit-border-bottom-right-radius: 3px; background-color: #e6e6e6; background-repeat: no-repeat; background-image: linear-gradient(#fcfcfc, #fcfcfc 25%, #e6e6e6); transition: all .15s linear; box-shadow: inset 0 0 1px #fff;}
		.question_title{border: 1px solid #045bb2; border-radius: 4px; box-shadow: inset 0 1px 3px rgba(0,0,0,.2),0 1px 0 rgba(255,255,255,.1); outline: 0;padding: 5px; width: 300px; font-size: 15px;}
 	</style>
</head>
<body class="page page-id-2 page-parent page-template page-template-aboutus page-template-aboutus-php">

  <!--start 登录注册浮动栏 -->
    <jsp:include page="./wpthemedemobar.jsp"></jsp:include>
 <!-- end 登录注册浮动栏 -->

   <!-- start:头部导航 -->
  <jsp:include page="./header.jsp"></jsp:include>
  <!-- end:头部导航-->

<!--head:所在位置-->
<div id="page_muen_nav">  <b>您现在所在的位置：</b><a href="${ctx}/front/web/base/index">首页</a> <a> &gt; </a><a href="${ctx}/front/agribusiness/base/getCommpanyList"> 农企信息列表</a><a> &gt; </a><a href="${ctx}/front/agribusiness/base/getApplyCommpany"> 申请</a></div>
<!--end:所在位置-->

<!--head:内容项目-->
<div id="content">

<!--head:左侧内容-->
<div class="left_mian"> 
<!--head:扁平图标导航-->
 <jsp:include page="./navigation.jsp"></jsp:include>
<!--head:扁平图标导航-->

</div>
<!--end:左侧内容-->


<!--head:右侧内容-->
<div class="right_mian">

<div class="into_unit">
	<div class="info_title" style="top: 15px; left: 60px;">
        	申请信息
	</div>
	<div class="info_detail">
	   <form id="applyForm" method="post" autocomplete=off>
      	    <div>
        	<p style="width: 100%;">
             <span><strong>企业名称</strong></span>
             <span>
             	<input type="text" id="businessName" name="businessName" class="easyui-validatebox" data-options="required:true" placeholder="输入企业名称" />
            </span>
            </p>
            </div>
            <div>
            <p style="width: 100%;">
            <span><strong>企业官网</strong></span>
            <span>
            	<input type="text" id="businessSite" name="businessSite" class="easyui-validatebox" data-options="required:true,validType:'webhttp'" placeholder="输入企业官网" />
            </span>
            </p>
            </div>
            <div>
            <p style="width: 100%;">
            	<span><strong>企业介绍</strong></span>
            	<textarea id="businessIntroduce" name="businessIntroduce" class="easyui-validatebox" data-options="required:true" style="width: 490px; height: 50px; outline:none; margin-top: 10px;"  placeholder="企业介绍描述..."></textarea>
            </p>
            </div>
            <div style="width: 173px; height:43px; margin: 0 auto;">
            	<a href="javascript:submitForm();"><div class="btn_applycompany"></div></a>
            </div>
		</form>
	</div>
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
<script>
	$.extend($.fn.validatebox.defaults.rules, {
    webhttp: {
		validator: function(value, param){
			 if (/^(https?:\/\/)?([\da-z\.-]+)\.([a-z\.]{2,6})([\/\w \.-]*)*\/?$/i.test(value)) {
        		return true;
    		}
    		else
    			return false;
		},
		message: '请输入有效的网址'
    	}
	});
	$(function() {
		$('#applyForm').form({
		    url:'${ctx}/front/agribusiness/base/add',
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
		    		alert(result.msg);
		    		window.location.href='${ctx}/front/agribusiness/base/getCommpanyList';
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
	function submitForm(){	
		$('#applyForm').submit();
		
	}
	</script>
</body>
</html>
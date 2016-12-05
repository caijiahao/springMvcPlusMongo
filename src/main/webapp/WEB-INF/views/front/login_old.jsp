<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!--[if lt IE 7]> <html class="ie lt-ie9 lt-ie8 lt-ie7 fluid sticky-top"> <![endif]-->
<!--[if IE 7]>    <html class="ie lt-ie9 lt-ie8 fluid"> <![endif]-->
<!--[if IE 8]>    <html class="ie lt-ie9 fluid"> <![endif]-->
<!--[if gt IE 8]> <html class="ie gt-ie8 fluid"> <![endif]-->
<!--[if !IE]><!-->
<html class="fluid">
<!-- <![endif]-->
<head runat="server">
    <jsp:include page="../inc.jsp"></jsp:include>
    <title>登陆系统</title>
  <!-- Meta -->
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
    <!-- Bootstrap -->
    <link href="${ctx}/jslib/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/jslib/bootstrap/css/responsive.css" rel="stylesheet" type="text/css" />
    <!-- Glyphicons Font Icons -->
    <link href="${ctx}/style/theme/fonts/glyphicons/css/glyphicons.css" rel="stylesheet" />
    <link rel="stylesheet" href="${ctx}/style/theme/fonts/font-awesome/css/font-awesome.min.css">
    <!--[if IE 7]><link rel="stylesheet" href="theme/fonts/font-awesome/css/font-awesome-ie7.min.css"><![endif]-->
    <!-- Uniform Pretty Checkboxes -->
    <link href="${ctx}/style/theme/scripts/plugins/forms/pixelmatrix-uniform/css/uniform.default.css"
        rel="stylesheet" />
    <!-- PrettyPhoto -->
    <link href="${ctx}/style/theme/scripts/plugins/gallery/prettyphoto/css/prettyPhoto.css"
        rel="stylesheet" />
    <!-- Main Theme Stylesheet :: CSS -->
    <link href="${ctx}/style/theme/css/style-flat.css?1371698490" rel="stylesheet" type="text/css" />
    <!-- Skin Stylesheet :: CSS -->
    <link href="${ctx}/style/theme/skins/css/blue-gray.css?1371698490" rel="stylesheet" />
    <!-- LESS.js Library -->
    <script src="${ctx}/style/theme/scripts/plugins/system/less.min.js"></script>
	<style>
	.Login_Button
	{
		width: 78px; 
		height: 43px; 
		background:url("${ctx}/style/images/index/login_b.png"); 
		cursor: pointer;
	}
	
	.Login_Button:hover
	{
		background:url("${ctx}/style/images/index/login_bs.png");
	}
	
	.Register_Button
	{
		width: 173px; 
		height: 43px; 
		background:url("${ctx}/style/images/index/register_e.png"); 
		cursor: pointer;
		margin: 0 auto;
	}
	
	.Register_Button:hover
	{
		background:url("${ctx}/style/images/index/register_es.png");
	}
	
	</style>
	
</head>
<body class="login">
    <!-- Wrapper -->
    <div id="login">
        <div class="container">
            <div class="wrapper">
                <h1 class="glyphicons lock">
                    登录<i></i></h1>
                <!-- Box -->
                <div class="widget widget-heading-simple widget-body-gray">
                    <div class="widget-body">
                        <!-- Form -->
						  <form id="loginform"  method="post">
                        <label>
                            用户名</label>
                        <input name="loginName" type="text" id="txtUserCode" class="input-block-level easyui-validatebox" placeholder="输入登录账号">
                        <label>
                            密 码 <a class="password" href="forgetpassword.html">忘记密码?</a></label>
                        <input name="password" type="password" id="txtPassword" class="input-block-level margin-none easyui-validatebox" placeholder="输入密码">
                        <div class="separator bottom">
                        </div>
                        <div class="row-fluid">
                            <div class="span8">
							   <a href="${ctx}/front/web/base/getRegister"><div class="Register_Button"></div></a>
                            </div>
                            <div class="span4 center">
                            	<a href='javascript:submitForm();'><div class="Login_Button"></div></a>
                            </div>
                        </div>
						</form>
                        <!-- // Form END -->
                    </div>
                </div>
                <!-- // Box END -->
                <div class="innerT center">
                    <!-- <a href="${ctx}/front/web/base/getRegister"><div class="Register_Button"></div></a> -->
                </div>
            </div>
        </div>
    </div>
    <!-- // Wrapper END -->
    <script>
	var sessionInfo_userId = '${sessionInfo.id}';
	if (sessionInfo_userId) {//如果登录,直接跳转到index页面
		window.location.href='${ctx}/front/web/base/index/';
	}
		
	$(function() {
		$('#loginform').form({
		    url:'${ctx}/front/web/user/login',
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
		    		var originUrl = '${originUrl}';
		    		if(originUrl == undefined || originUrl == ""){
		    			window.location.href='${ctx}/front/web/base/index/';
		    		}
		    		else {
		    			window.location.href='${ctx}' + originUrl;
		    		}
		    			
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
		 if ($("#txtUserCode").val() == "" || $("#txtPassword").val() == "")
            return false;
		else{$('#loginform').submit();}
		
	}
	
	function clearForm(){
		$('#loginform').form('clear');
	}
</script>
</body>
</html>


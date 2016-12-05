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
	<link href="${ctx}/style/index/login.css"
        rel="stylesheet" />
	<style>
	
	
	</style>
	
</head>
<body class="login">
    
    <div class="loginform">
		<form id="loginform" method="post" autocomplete=off>
			<div class="username">
			 	<label>用户名</label>
			 	<input name="loginName" type="text" id="txtUserCode" placeholder="输入登录账号" />
			</div>
            <div class="password">
            	<label>密 码 <a class="forgetpassword" href="forgetpassword.html">忘记密码?</a></label>
            	<input name="password" type="password" id="txtPassword" placeholder="输入密码">
            </div>  
            <div class="button">
            	<a href="${ctx}/front/web/base/getRegister"><div class="Register_Button"></div></a>
                <a href='javascript:submitForm();'><div id="btnLoginOnclick" class="Login_Button"></div></a>
            </div>
		</form>
	</div>

    <script>
	var sessionInfo_userId = '${sessionInfo.id}';
	if (sessionInfo_userId) {//如果登录,直接跳转到index页面
		window.location.href='${ctx}/front/web/base/index/';
	}
		
	$(function() {
		$("#txtUserCode").focus();
		$("#txtUserCode").keyup(function (event) {
            if (!event || event.which != 13)
                return true;
            if ($("#txtUserCode").val() != "") {
                $("#txtPassword").focus();
            }
        });
        $("#txtPassword").keyup(function (event) {
            if (!event || event.which != 13)
                return true;
            $("#btnLoginOnclick").click();
            $("#btnLoginOnclick").attr("disabled", true);
        });
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


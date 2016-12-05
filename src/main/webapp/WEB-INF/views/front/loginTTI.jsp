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
    <title>登陆物联网系统</title>
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
	
	<script>

	
</script>
</head>
<body class="login ">
    <!-- Wrapper -->
    <div id="login">
        <div class="container">
            <div class="wrapper">
                <h1 class="glyphicons lock">
                    登陆物联网系统<i></i></h1>
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
							   <select style="width:200px">
							     <option> 普通用户 </option>
								 <option> 专家用户 </option>
							   </select>
                            </div>
                            <div class="span4 center">
                                <button class="btn btn-block btn-inverse" onclick="">登入</button>
                            </div>
                        </div>
						</form>
                        <!-- // Form END -->
                    </div>
                </div>
                <!-- // Box END -->
                <div class="innerT center">
                    <a href="${ctx}/front/web/base/getRegister" class="btn btn-icon-stacked btn-block btn-success glyphicons user_add">
                        <i></i><span>未有账号?</span><span class="strong">注册</span></a>
                </div>
            </div>
        </div>
    </div>
    <!-- // Wrapper END -->
	

</body>
</html>


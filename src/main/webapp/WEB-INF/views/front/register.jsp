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

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>
	免费注册
</title>
    <!-- Meta -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE">
    <link href="${ctx}/style/register/main.css" rel="stylesheet" type="text/css">
    <link href="${ctx}/style/register/signin.css" rel="stylesheet" type="text/css">
    <style>
        .logo
        {
            float: left;
        }
        
        .area
        {
            width: 1002px;
            margin: 0px auto;
        }
        
        a.btnf80, .btnf80
        {
            text-decoration: none;
            float: left;
            text-align: center;
            border: 1px solid #f80;
            border-top: 1px solid #fb3;
            border-radius: 4px;
            box-shadow: 0 1px 1px rgba(255,255,255,1), inset 0 1px 1px rgba(255,255,255,.6);
            color: #fff;
            text-shadow: 0 1px 1px rgba(0,0,0,.3);
            background: #f80;
            background: -webkit-gradient(linear, left top, left bottom, from(#ffbb33),to(#ff8800));
            background: -moz-linear-gradient( top, #ffbb33, #ff8800);
            filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#ffbb33',endColorstr='#ff8800');
        }
        
        .Login_Button
        {
        	width: 78px; 
			height: 43px; 
			background: url("${ctx}/style/images/index/login_a.png"); 
			cursor: pointer;
        }
        
        .Login_Button:hover
        {
        	background: url("${ctx}/style/images/index/login_as.png"); 
        }
        
        .Register_Button
        {
        	width: 173px; 
			height: 43px; 
			background: url("${ctx}/style/images/index/register_a.png"); 
			cursor: pointer;
        }
        
        .Register_Button:hover
        {
        	background: url("${ctx}/style/images/index/register_as.png"); 
        }
    </style>
	
</head>
<body class="">
    <div class="head">
        <div id="headMenu" class="area">
            <div style="width: 635px; margin: 0 auto;">
                <p style="float: left; margin-left: 222px; margin-top: 32px;">
                    <font class="xtdl" style="color: #26B76D;">免 费 注 册</font></p>
                <a href="${ctx}/front/web/base/getLoginPage" style="float: right;margin-top: 45px;"><div class="Login_Button"></div></a>
            </div>
        </div>
    </div>
    <!-- Main Container Fluid -->
    <div class="container-fluid fluid menu-left">
        <!-- Content -->
        <div id="content">
            <div class="area">
                <table width="100%">
                    <tbody>
                        <tr>
                            <td width="100%" height="450" colspan="2" align="center" valign="middle">
                                <table>
                                    <tbody>
                                        <tr>
                                            <td align="center" valign="middle">
                                                <div class="area table_new">
                                                    <div class="content form">
                                                        <div class="table_content talbe_area2">
                                                            <form id="validateform1" method="post" action="">
                                                            <div class="bitian" style="top: 15px; left: 60px;">
                                                                必填
                                                            </div>
                                                            <div class="detail">
                                                                <div>
                                                                    <p style="width: 100%;">
                                                                        <span><strong>账号名</strong><label id="spelogincode" class="sp" style="display: inline;">账号名不能为空</label></span>
                                                                        <span>
                                                                            <input id="logincode" name="logincode" type="text" placeholder="必填项">
                                                                            <font>由英文或数字组成，且首字符必须以英文字母开头</font> </span>
                                                                    </p>
                                                                </div>
                                                                <div>
                                                                    <p style="float: left;">
                                                                        <span><strong>密&nbsp;&nbsp;&nbsp;码</strong><label id="spepwd" class="sp"></label></span>
                                                                        <span>
                                                                            <input id="pwd" name="pwd" type="password" placeholder="必填项">
                                                                        </span>
                                                                    </p>
                                                                    <p style="float: right;">
                                                                        <span><strong>确认密码</strong><label id="spepwdconfirm" class="sp"></label></span>
                                                                        <span>
                                                                            <input id="pwdconfirm" name="pwdconfirm" type="password" placeholder="必填项"></span>
                                                                    </p>
                                                                </div>
                                                            </div>
                                                            <div class="bitian" style="top: 224px; left: 60px; width: 320px;">
                                                                必填.我们会通过以下方式为您提供更优质的服务.
                                                            </div>
                                                            <div class="detail">
                                                                <div>
																    <p style="float: left;">
                                                                        <span><strong>姓&nbsp;&nbsp;&nbsp;名</strong></span>
                                                                        <span>
                                                                            <input id="realName" name="realName" type="text" placeholder="必填项"></span>
                                                                    </p>
																	<p style="float: right;">
                                                                        <span><strong>年&nbsp;&nbsp;&nbsp;龄</strong></span>
                                                                        <span>
                                                                            <input id="age" name="age" type="text" placeholder="必填项"></span>
                                                                    </p>
                                                                    <p style="float: left;">
                                                                        <span><strong>邮&nbsp;&nbsp;&nbsp;箱</strong><label id="speemail" class="sp"></label></span>
                                                                        <span>
                                                                            <input id="email" name="email" type="text" placeholder="必填项"></span>
                                                                    </p>

                                                                    <p style="float: right;">
                                                                        <span><strong>联系电话</strong><label id="specontactphone" class="sp"></label></span>
                                                                        <span>
                                                                            <input id="contactphone" name="contactphone" type="text" placeholder="必填项"></span>
                                                                    </p>
                                                                </div>
                                                                <div>
                                                                    <p style="width: 100%;">
                                                                        <span><strong>联系地址</strong><label id="spedetailaddr" class="sp"></label></span>
                                                                        <span><span id="provincecity" style="width: 370px;"></span>
                                                                            <!--  <input name="detailaddr" id="detailaddr" type="text" placeholder="必填项" style="width: 222px;
                                                                                margin-top: 2px;">-->
                                                                            <input name="detailaddr" id="detailaddr" type="text" placeholder="详细地址" style="width: 350px;
                                                                                margin-top: 2px;">
                                                                        </span>
                                                                    </p>
                                                                </div>
                                                            </div>
                                                            <div class="bitian" style="top: 510px; left: 60px; width: 60px;">
                                                                验证码
                                                            </div>
                                                            <div class="detail">
                                                                <div>
                                                                    <p style="width: 100%;">
                                                                        <span>
                                                                            <img src="${ctx}/baseUtil/getCheckCode" id="validImg" onclick="f_refreshtype()" style="cursor: pointer;" /><label
                                                                                id="spevalidvalue" class="sp">填写的验证码错误</label></span> <span>
                                                                                    <input id="validvalue" name="validvalue" type="text" placeholder="输入验证码" />
                                                                                    <font>点击图片刷新验证码</font> </span>
                                                                    </p>
                                                                </div>
                                                            </div>
                                                            <div>
                                                            	<div style="margin: 0 auto; width: 173px; float:none;">
                                                            		<a href="javascript:register();">
	                                                            		<div class="Register_Button"></div>
	                                                            	</a>
                                                            	</div>
                                                                <!-- <input id="btn_register" type="button" onclick="register()" value="立即注册" class="login_btn" style="margin: 20px auto 5px;"> -->
                                                            </div>
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <!-- // Content END -->
        <div id="footer" class="hidden-print">
            <!--  Copyright Line -->
            <div class="copy">
                <p> 版权所有： SCAU Copyright © 2015华南农业大学.All rights reserved.   |   粤ICP备05008874号 　备案编号：4401060500010</p>
            </div>
            <!--  End Copyright Line -->
        </div>
        <!-- // Footer END -->
    </div>
    <!-- // Main Container Fluid END -->
    <!-- JQuery -->
  
    <script src="${ctx}/jslib/register/provincesdata.js" type="text/javascript"></script>
    <script src="${ctx}/jslib/register/jquery.provincesCity.new.js" type="text/javascript"></script>
    <script src="${ctx}/jslib/register/website.register.js" type="text/javascript"></script>
	


</body></html>
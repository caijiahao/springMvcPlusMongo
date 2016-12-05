<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!--head:底部信息-->
<div id="footer">
	<div id="footer_in">
             <div class="footer_shadow"></div>
                <p> 版权所有： SCAU Copyright © 2015华南农业大学.All rights reserved.   |   粤ICP备05008874号 　备案编号：4401060500010</p>
                <p>地址：广州市天河区五山华南农业大学</p>
     </div>	  
 </div>
<!--end:底部信息-->

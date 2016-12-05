<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>  
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
	<link href="${ctx}/style/index/commpany.css" rel="stylesheet" media="screen">
  </head>
<body class="home blog">
 <!--start 登录注册浮动栏 -->
    <jsp:include page="./wpthemedemobar.jsp"></jsp:include>
 <!-- end 登录注册浮动栏 -->

   <!-- start:头部导航 -->
  <jsp:include page="./header.jsp"></jsp:include>
  <!-- end:头部导航-->

<!--head:所在位置-->
<div id="page_muen_nav">  <b>您现在所在的位置：</b><a href="${ctx}/front/web/base/index">首页</a> <a> &gt; </a><a href="${ctx}/front/agribusiness/base/getCommpanyList"> 农企信息列表</a></div>
<!--end:所在位置-->

<!--head:内容项目-->
<div id="content">

<!--head:左侧内容-->
<div class="left_mian"> 
<!--head:查询农企-->
<div class="widget">
<ul>
<li>
	<div class="searchbox">
		<input type="text" id="input_search" autocomplete="off" value="" placeholder="搜索你感兴趣的公司...">
		<button id="btn_search" onclick="fnSearch();"><i class="search_icon"></i></button>
	</div>
</li>
<li>
	<a href="${ctx}/front/agribusiness/base/getApplyCommpany"><div style="margin-top: 10px;" class="applycommpany" id="btn_applycommpany"></div></a>
</li>
</ul>
</div>
<!--end:查询农企-->

<!-- head:农企信息 -->
<div class="widget" id="widget_company">
   <div class="widge_hd">
   <span>
       <b>待审核农企信息</b>
   </span>
   </div>
<ul id="notAuditCompanyList">
	<c:if test="${!empty notAuditCompanyList}">
	<c:forEach items="${notAuditCompanyList}" var="n">
		<li><a href="javascript:void(0);">${n.businessName}</a><p>${n.businessIntroduce}<br/>网址： ${n.businessSite}<br/>状态：<span style="color: red;">${n.statusDesc}</span></p></li>
	</c:forEach>
	</c:if>
</ul>
</div>
<!-- end:农企信息 -->

<!--head:扁平图标导航-->
 <jsp:include page="./navigation.jsp"></jsp:include>
<!--head:扁平图标导航-->

</div>
<!--end:左侧内容-->


<!--head:右侧内容-->
<div class="right_mian">

<!-- head:农企信息 -->
  <div class="widget companylist" id="widget_companylist">
<ul id="companyList">
	<c:if test="${!empty companyList}">
	<c:forEach items="${companyList}" var="n">
		<li><a href="javascript:void(0);">${n.businessName}</a><br/><p>${n.businessIntroduce}<br/>${n.businessSite}</p></li>
	</c:forEach>
	</c:if>
</ul>
<!--head:列表分页-->
<div class="list_paginator">
	<input id="page_size" type="hidden" value="10" />
	<input id="records_total" type="hidden" value="0" />
	<a href="javascript:changePage(1);">上一页</a> 
	<a id="current_page" currentpage="1" href="javascript:void(0); " class="paginator_current">1</a> 
	<a href="javascript:changePage(2);">下一页</a>
</div>
<!--end:列表分页-->
</div>
<!-- end:农企信息 -->

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
<script>
  $(document).ready(function(){
  	$.ajax({
            type: "GET",
            contentType: "application/json",
            url: "${ctx}/front/agribusiness/base/listUnAduitedByUser?rows=10&page=1",
            data: "{}",
            dataType: "json",
            success: function (json) {
            	if(json.total > 0){
            		for (var i = 0; i < json.total; i++) {
            			var item = "<li><a href='javascript:void(0);'>" + json.rows[i].businessName + "</a><p>" + json.rows[i].businessIntroduce;
            			item += "<br/>网址： " + json.rows[i].businessSite + "<br/>状态：<span style='color: red;'>" + json.rows[i].statusDesc + "</span></p></li>";
            			$("#notAuditCompanyList").append(item);
            		}
            	}               
            },
            error: function (xhr, error, thrown) {
                
            }
        });
      getData();
  });
  
  function changePage(flag){
  	var pagesize = parseInt($("#page_size").val());
  	var current = parseInt($("#current_page").attr("currentpage"));
  	var total = parseInt($("#records_total").val());
  	if(flag == 1){
  		//上一页
  		if(current <= 1){
  			$("#current_page").attr("currentpage", 1);
  			alert("已经到达第一页");
  			return;
  		}
  		current--;
  	}
  	else{
  		//下一页
        var pageCount = parseInt(total / pagesize) == (total / pagesize) ? (total / pagesize) : ((total / pagesize) + 1);
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
  
  function fnSearch(){
  	$("#current_page").attr("currentpage", 1);
  	$("#current_page").text(1);
  	getData();
  }
  
  function getData(){
  	var searchcontent = $("#input_search").val();
  	searchcontent = searchcontent.trim();
  	var pagesize = parseInt($("#page_size").val());
  	var current = parseInt($("#current_page").attr("currentpage"));
  	var url = "${ctx}/front/agribusiness/base/searchCompanyList";
  	
  	$.ajax({
            url: url,
            data: { "rows": pagesize, "page": current, "searchcontent": searchcontent},
            type: "POST",
            dataType: "json",
            success: function (json) {
            	$("#companyList").html('');
            	$("#records_total").val(json.total);
            	for (var i = 0; i < json.rows.length; i++) {
            		var item = "<li><span href='javascript:void(0);'>" + json.rows[i].businessName + "</span><br/><p>" + json.rows[i].businessIntroduce;
            		item += "<br/> " + json.rows[i].businessSite + "</p></li>";
            		$("#companyList").append(item);
            	}              
            },
            error: function (xhr, error, thrown) {
                
            }
        });
  	}
  </script>

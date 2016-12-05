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
    <base href="<%=basePath%>">
    <jsp:include page="./inc-head.jsp"></jsp:include>
	<link href="${ctx}/style/index/expert.css" rel="stylesheet" media="screen">
</head>
<body class="page page-id-2 page-parent page-template page-template-aboutus page-template-aboutus-php">

 <!--start 登录注册浮动栏 -->
    <jsp:include page="./wpthemedemobar.jsp"></jsp:include>
 <!-- end 登录注册浮动栏 -->

   <!-- start:头部导航 -->
  <jsp:include page="./header.jsp"></jsp:include>
  <!-- end:头部导航-->

<!--head:所在位置-->
<div id="page_muen_nav">  <b>您现在所在的位置：</b><a href="${ctx}/front/web/base/index">首页</a> <a> &gt; </a><a href="${ctx}/front/">专家库</a></div>
<!--end:所在位置-->

<!--head:内容项目-->
<div id="content">

<!--head:左侧内容-->
<div class="left_mian"> 
<!--head:查询-->
<div class="widget">
<ul>
<li>
	<div class="searchexpert">
		<input type="text" id="input_search" autocomplete="off" value=""
								placeholder="搜索专家...">
		<div onclick="fnSearch();"
								style="width: 40px; height: 35px; position: absolute; top: 0px; right: 0px;"></div>
	</div>
</li>
</ul>
</div>
<!--end:查询-->
</div>
<!--end:左侧内容-->


<!--head:右侧内容-->
<div class="right_mian">

<!-- head:列表信息 -->
<div class="widget expertlist">

<ul id="expertList">
	
</ul>
<!--head:列表分页-->
<div class="list_paginator">
	<input id="page_size" type="hidden" value="20" />
	<input id="records_total" type="hidden" value="0" />
	<a href="javascript:changePage(1);">上一页</a> 
	<a id="current_page" currentpage="1" href="javascript:void(0); " class="paginator_current">1</a> 
	<a href="javascript:changePage(2);">下一页</a>
</div>
<!--end:列表分页-->
</div>
<!-- end:列表信息 -->

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
	$(document).ready(function(){
		getData();
		
		$("#input_search").keyup(function(event) {
			if (!event || event.which != 13)
				return true;
			fnSearch();
		});
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
	  	var url = "${ctx}/front/web/expert/base/getExpertData";
	  	
	  	$.ajax({
	            url: url,
	            data: { "rows": pagesize, "page": current, "searchKey": searchcontent },
	            type: "POST",
	            dataType: "json",
	            success: function (json) {
	            	$("#expertList").html('');
	            	$("#records_total").val(json.total);
	            	for (var i = 0; i < json.rows.length; i++) {
	            		var item = "<li>";
						if (json.rows[i].imagePath != null && json.rows[i].imagePath != "") {
									item += $.formatString('<img src="{0}">', json.rows[i].imagePath);
									item += $.formatString('<span class="expertname">{0}</span><br><span class="expertcontent">研究方向：{1}</span><br><span class="expertcontent">职称：{2}</span><br><span class="expertcontent">联系方式：{3}</span><br><p>{4}</p>',
												json.rows[i].realName, json.rows[i].techType, json.rows[i].techTitle, json.rows[i].email, json.rows[i].description);
						}
						item += "</li>";
	            		$("#expertList").append(item);
	            	}              
	            },
	            error: function (xhr, error, thrown) {
	                
	            }
	        });
  	}
</script>
</body>
</html>
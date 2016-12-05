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
	<link href="${ctx}/style/index/information.css" rel="stylesheet" media="screen">
</head>
<body class="page page-id-2 page-parent page-template page-template-aboutus page-template-aboutus-php">

 <!--start 登录注册浮动栏 -->
    <jsp:include page="./wpthemedemobar.jsp"></jsp:include>
 <!-- end 登录注册浮动栏 -->

   <!-- start:头部导航 -->
  <jsp:include page="./header.jsp"></jsp:include>
  <!-- end:头部导航-->

<!--head:所在位置-->
<div id="page_muen_nav">  <b>您现在所在的位置：</b><a href="${ctx}/front/web/base/index">首页</a> <a> &gt; </a><a href="${ctx}/front/news/base/getInformationList">通知列表</a></div>
<!--end:所在位置-->

<!--head:内容项目-->
<div id="content">

<!--head:左侧内容-->
<div class="left_mian"> 
<!--head:查询农企-->
<div class="widget">
<ul>
<li>
	<div class="searchdate">
		<span>开始日期：</span>
		<input type="text" id="startdate" style="width: 150px; height: 25px;" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'enddate\')}',readOnly:true})" />
	<div>
</li>
<li>
	<div class="searchdate">
		<span>结束日期：</span>
		<input type="text" id="enddate" style="width: 150px; height: 25px;" onclick="WdatePicker({minDate:'#F{$dp.$D(\'startdate\')}',readOnly:true})" />
	</div>
</li>
<li>
	<div class="category" style="margin-bottom: 10px;">
		<span>所属类别：</span>
		<select id="categoryID" class="easyui-combobox" data-options="width:150,height:29,editable:false,valueField:'value', textField:'text',panelHeight:'auto'">
		</select>
	</div>
</li>
<li>
	<div class="searchbox">
		<input type="text" id="input_search" autocomplete="off" value="" placeholder="搜索你感兴趣的新闻...">
		<button id="btn_search" onclick="fnSearch();"><i class="search_icon"></i></button>
	</div>
</li>
</ul>
</div>
<!--end:查询农企-->

<!--head:扁平图标导航-->
  <jsp:include page="./navigation.jsp"></jsp:include>
<!--head:扁平图标导航-->

</div>
<!--end:左侧内容-->


<!--head:右侧内容-->
<div class="right_mian">

<!-- head:通知列表 -->
 <div class="widget informationlist" id="widget_informationlist">
<ul id="articleList">
	<!--  
	<li><a href="./">新农村发展研究院</a><span class="time">2016/7/11</span></li>
	<li><a href="./">华农与榄核镇签订合作框架协议，香云纱成果发布惊艳全场</a><span class="time">2016/7/1</span></li>
	<li><a href="./">互联网+现代农业三年 行动实施方案</a><span class="time">2016/6/20</span></li>
	<li><a href="./">2016高校科技成果转化排名，华南农大广东第3，全国第23</a><span class="time">2016/6/12</span></li>
	<li><a href="./">湖北省高校大学生创新创业工作考察团来校交流</a><span class="time">2016/5/30</span></li>
	<li><a href="./">泰国宋卡王子大学师生代表团来校交流</a><span class="time">2016/5/27</span></li>
	-->
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
<!-- end:通知列表 -->

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
		$.ajax({
			type:"post",
			dataType:"json",
			url:"${ctx}/front/news/base/getCategory",
			data:{
				"categoryType" : "information"
			},
			success:function(json){
				if(json && json.total > 0){
					var data = [];
					for(var i = 0; i < json.total; i++){
						data.push({ "value": json.rows[i].autoID, "text": json.rows[i].categoryName });
					}
					$("#categoryID").combobox("loadData", data);
				}
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
	  	var url = "${ctx}/front/news/base/getListPublish";
	  	var selectCategoryID = $("#categoryID").combobox("getValue");
	  	var stDate = $("#startdate").val();
	  	var endDate = $("#enddate").val();
	  	
	  	$.ajax({
	            url: url,
	            data: { "rows": pagesize, "page": current, "searchcontent": searchcontent, "categoryType": "information", "category": selectCategoryID, "stDate": stDate, "endDate": endDate },
	            type: "POST",
	            dataType: "json",
	            success: function (json) {
	            	$("#articleList").html('');
	            	$("#records_total").val(json.total);
	            	for (var i = 0; i < json.rows.length; i++) {
	            		var item = "<li><a href='${ctx}/front/news/base/getArticle?article=" + json.rows[i].autoID + "'>" + json.rows[i].title + "</a>";
	            		item += "<span class='time'>" + json.rows[i].publishDate + "</span></li>";
	            		$("#articleList").append(item);
	            	}              
	            },
	            error: function (xhr, error, thrown) {
	                
	            }
	        });
  	}
</script>
</body>
</html>
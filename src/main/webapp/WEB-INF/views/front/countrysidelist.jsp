<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
	<link href="${ctx}/style/index/countryside.css" rel="stylesheet" media="screen">
  </head>
  
<body class="home blog">
 <!--start 登录注册浮动栏 -->
    <jsp:include page="./wpthemedemobar.jsp"></jsp:include>
 <!-- end 登录注册浮动栏 -->

   <!-- start:头部导航 -->
  <jsp:include page="./header.jsp"></jsp:include>
  <!-- end:头部导航-->

<!--head:所在位置-->
<div id="page_muen_nav">  <b>您现在所在的位置：</b><a href="${ctx}/front/web/base/index">首页</a> <a> &gt; </a><a href="${ctx}/front/village/base/getCountrysideList">下乡管理</a></div>
<!--end:所在位置-->

<!--head:内容项目-->
<div id="content">

<!--head:左侧内容-->
<div class="left_mian"> 
<!--head:查询农企-->
<div class="widget">
<ul>
<li>
	<a href="${ctx}/front/village/base/getApplycountryside"><div class="applycountryside" id="btn_applycountryside"></div></a>
</li>
</ul>
</div>
<!--end:查询农企-->

<!-- head:农企信息 -->
<!-- 屏蔽待审核的
<div class="widget" id="widget_company">
   <div class="widge_hd">
   <span>
       <b>待审核下乡记录</b>
   </span>
   </div>

<ul id="notAuditCountrysideList">
     <c:if test="${!empty efApplication}">
	    <c:forEach items="${efApplication}" var="n">   
			<li><a href="./">${n.businessMatter}</a><p>地址:${n.businessArea}${n.businessAddress}<br/>出发日期： ${n.businessDate}<br/>状态：<span style="color: red;">未审核</span></p></li>	  
		</c:forEach>	
	</c:if>
</ul>
</div>
 -->
<!-- end:农企信息 -->

<!--head:扁平图标导航-->
 <jsp:include page="./navigation.jsp"></jsp:include>
<!--head:扁平图标导航-->

</div>
<!--end:左侧内容-->


<!--head:右侧内容-->
<div class="right_mian">

<!-- head:农企信息 -->
 <div class="widget countrysidelist" id="widget_countrysidelist">
<ul id="countrysideList">
	<!--  
	<li><a href="./">前往常德恒凤渔业有限公司</a><br/><p>地址： 湖南省常德市鼎城区蒿子港镇长安村八组<br/>出发日期： 2016/5/31</p></li>
	<li><a href="./">前往建德市大同镇锦惠农庄</a><br/><p>主营： 热食类食品制售（小型餐饮）（依法须经批准的项目，经相关部门批准后方可开展经营活动）<br/>出发日期： 2016/6/16</p></li>
	<li><a href="./">前往兰溪市野狐山寨休闲农庄</a><br/><p>主营： 饭馆；中餐类制售；农家乐；不含凉菜；不含裱花蛋糕；不含生食海产品<br/>出发日期： 2016/6/14</p></li>
	<li><a href="./">前往诸暨市枫桥良禹农庄</a><br/><p>主营： 食品经营（具体经营项目以许可证或批准文件核定的为准） 养殖销售：淡水产 <br/>出发日期： 2016/6/14</p></li>
	<li><a href="./">前往诸暨市岭北晋坤农庄</a><br/><p>主营： 食品经营（具体经营项目以许可证或批准文件核定的为准） 种植：蔬菜、水果<br/>出发日期： 2016/6/14</p></li>
	-->
</ul>
<!--head:列表分页  -->
<div class="list_paginator">
	<input id="page_size" type="hidden" value="3" />
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
  
  function getData(){
  	var pagesize = parseInt($("#page_size").val());
  	var current = parseInt($("#current_page").attr("currentpage"));
  	var url = "${ctx}/front/village/base/listAllByUser";
  	
  	$.ajax({
            url: url,
            data: { "rows": pagesize, "page": current},
            type: "POST",
            dataType: "json",
            success: function (json) {
            	$("#countrysideList").html('');
            	$("#records_total").val(json.total);
            	for (var i = 0; i < json.rows.length; i++) {
            		var item = "<li><a href='javascript:void(0);'>前往地址：" + json.rows[i].businessArea + json.rows[i].businessAddress + "</a><br/>";
            		item += "<p>下乡事由：" + json.rows[i].businessReason + "<br/>出发日期：" + json.rows[i].businessDate.split(" ")[0] + "，结束日期：" + json.rows[i].businessDate.split(" ")[0];
            		item += "<br/>下乡人员：" + json.rows[i].personalListStr + "<br/>联系人：" + json.rows[i].businessLinkMan + "，电话：" + json.rows[i].businessLinkPhone;
            		item += "<br/>状态：<span style='color: red;'>" + json.rows[i].statusDesc + "</span></p></li>";
            		$("#countrysideList").append(item);
            	}              
            },
            error: function (xhr, error, thrown) {
                
            }
        });
  }
</script>

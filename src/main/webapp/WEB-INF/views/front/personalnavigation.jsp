<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<style>
	#xiaoxi
	{
		width: 104px;
		height: 104px;
		background: url("${ctx}/style/images/index/xiaoxi.png");
		cursor: pointer;
	}
	#xiaoxi:hover
	{
		background: url("${ctx}/style/images/index/xiaoxi_s.png");
	}
	
	#nongqi
	{
		width: 104px;
		height: 104px;
		background: url("${ctx}/style/images/index/nongqi.png");
		cursor: pointer;
	}
	#nongqi:hover
	{
		background: url("${ctx}/style/images/index/nongqi_s.png");
	}
	
	#xiaxiang
	{
		width: 104px;
		height: 104px;
		background: url("${ctx}/style/images/index/xiaxiang.png");
		cursor: pointer;
	}
	#xiaxiang:hover
	{
		background: url("${ctx}/style/images/index/xiaxiang_s.png");
	}
	
	#jixiao
	{
		width: 104px;
		height: 104px;
		background: url("${ctx}/style/images/index/jixiao.png");
		cursor: pointer;
	}
	#jixiao:hover
	{
		background: url("${ctx}/style/images/index/jixiao_s.png");
	}
	
	#zhishiku
	{
		width: 104px;
		height: 104px;
		background: url("${ctx}/style/images/index/zhishiku.png");
		cursor: pointer;
	}
	#zhishiku:hover
	{
		background: url("${ctx}/style/images/index/zhishiku_s.png");
	}
	
	#communicate
	{
		width: 104px;
		height: 104px;
		background: url("${ctx}/style/images/index/communicate.png");
		cursor: pointer;
	}
	#communicate:hover
	{
		background: url("${ctx}/style/images/index/communicate_s.png");
	}
</style>
<!--head:扁平图标导航-->
<div class="widget func_nav">
   <div class="widge_hd">
   <span>
       <b>功能导航</b><p>Function</p>
   </span>
   </div>
<ul>

<li><a href="${ctx}/front/web/communicate/getPersonalQuestionList"><div id="xiaoxi"></div><div style="margin: 0 auto; text-align: center; font-size: 14px; color: green;">消息列表</div></a></li>
<li><a href="${ctx}/front/agribusiness/base/getCommpanyList"><div id="nongqi"></div><div style="margin: 0 auto; text-align: center; font-size: 14px; color: green;">农企信息</div></a></li>
<li><a href="${ctx}/front/village/base/getCountrysideList"><div id="xiaxiang"></div><div style="margin: 0 auto; text-align: center; font-size: 14px; color: green;">下乡管理</div></a></li>
<li><a href="${ctx}/front/web/base/getPerformanceList"><div id="jixiao"></div><div style="margin: 0 auto; text-align: center; font-size: 14px; color: green;">绩效考核</div></a></li>
<li><a href="${ctx}/front/web/base/getKnowledgeData"><div id="zhishiku"></div><div style="margin: 0 auto; text-align: center; font-size: 14px; color: green;">农技知识</div></a></li>
<li><a href="${ctx}/front/web/base/getQuestionList"><div id="communicate"></div><div style="margin: 0 auto; text-align: center; font-size: 14px; color: green;">在线交流</div></a></li>

</ul>
</div>
<!--head:扁平图标导航-->

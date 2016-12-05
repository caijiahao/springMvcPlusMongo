<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
  <!-- start:头部导航 -->
   <div id="header">
            <div id="header_in">
                <img src="${ctx}/style/images/index/banner.png"/>
            </div>
            <div id="nav">
	            <a class="home_url"></a>
	            <div class="menu-dai_you_she_qu_de_dao_hang_cai_dan-container">
	            	<ul id="menu-dai_you_she_qu_de_dao_hang_cai_dan" class="menu_nav">
	            	<li id="menu-item-253" class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-253"><a href="${ctx}/front/web/base/index">首页</a><div class="hover"></div></li>
					<li id="menu-item-254" class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-254"><a href="${ctx}/front/news/base/getArticleList?categoryTypeCode=News">新闻动态</a> <div class="hover" style="display: none;"></div></li>
					<li id="menu-item-260" class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-260"><a href="${ctx}/front/news/base/getArticleList?categoryTypeCode=Information">通知公告</a><div class="hover" style="display: none;"></div></li>
					<li id="menu-item-261" class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-261"><a href="${ctx}/front/news/base/getArticleList?categoryTypeCode=Report">工作简报</a><div class="hover" style="display: none;"></div></li>
					<li id="menu-item-262" class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-262"><a href="${ctx}/front/news/base/getArticleList?categoryTypeCode=BaseConstruction">示范基地</a><div class="hover" style="display: none;"></div></li>
					<li id="menu-item-263" class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-263"><a href="${ctx}/front/web/expert/base/getExpertList">服务专家</a><div class="hover" style="display: none;"></div></li>
					<li id="menu-item-264" class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-264"><a href="${ctx}/front/web/base/getKnowledgeData?categoryCode=Industry">知识库</a><div class="hover" style="display: none;"></div></li>
					<li id="menu-item-265" class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-265"><a href="${ctx}/front/web/base/getKnowledgeData?categoryCode=Variety">品种库</a><div class="hover" style="display: none;"></div></li>
					<li id="menu-item-266" class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-266"><a href="${ctx}/front/web/base/getKnowledgeData?categoryCode=Achievement">成果库</a><div class="hover" style="display: none;"></div></li>
					<li id="menu-item-267" class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-267"><a href="${ctx}/front/web/base/getKnowledgeData?categoryCode=Technology">技术库</a><div class="hover" style="display: none;"></div></li>
					<!--  
					<li id="menu-item-265" class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-265"><a href="${ctx}/front/web/base/getLoginVideoPage">视频会议</a><div class="hover" style="display: none;"></div></li>
					<li id="menu-item-263" class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-263"><a href="${ctx}/front/web/base/getLoginTTIPage">农业物联网</a><div class="hover" style="display: none;"></div></li>
					<li id="menu-item-264" class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-264"><a href="${ctx}/front/web/base/getPersonalCenter">个人中心</a><div class="hover" style="display: none;"></div></li>
					-->
					</ul>
				</div>            
            </div>
<script>
$('#nav .menu_nav li').not(".sub-menu li").append('<div class="hover"><\/div>');
$('#nav .menu_nav li .sub-menu li').children("ul").addClass("block")
$('#nav .menu_nav li').hover(
function() {
$(this).children(".sub-menu").not(".block").stop(true, true).fadeIn('200');},
function() {
$(this).children(".sub-menu").not(".block").stop(true, true).fadeOut('1000');
	}
)
$('#nav .menu_nav li').not(".current-menu-item,.current-menu-ancestor,.current-category-ancestor").hover(
function() {
$(this).children('.hover').stop(true, true).fadeIn('200');
},
function() {
$(this).children('.hover').stop(true, true).fadeOut('1000');
});            
</script>
	</div>
  <!-- end:头部导航-->

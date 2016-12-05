<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<jsp:include page="../inc.jsp"></jsp:include>
<base href="<%=basePath%>">
<jsp:include page="./inc-head.jsp"></jsp:include>
<link href="${ctx}/style/index/knowledgedata.css" rel="stylesheet"
	media="screen">
</head>

<body class="home blog">
	<!--start 登录注册浮动栏 -->
	<jsp:include page="./wpthemedemobar.jsp"></jsp:include>
	<!-- end 登录注册浮动栏 -->

	<!-- start:头部导航 -->
  	<jsp:include page="./header.jsp"></jsp:include>
  	<!-- end:头部导航-->
	
	<!--head:所在位置-->
	<div id="page_muen_nav">
		<b>您现在所在的位置：</b><a href="${ctx}/front/web/base/index">首页</a> <a>
			&gt; </a><a href="${ctx}/front/web/base/getKnowledgeData"> 农技知识库</a>
	</div>
	<!--end:所在位置-->
	
	<!--head:内容项目-->
	<div class="knowledgecontent">
		<div class="selectknowledge">
			<ul>
				<li><div knowledgeitem="Industry" class="itemlabel selectedlabel1 selected"></div>
				</li>
				<li><div knowledgeitem="Variety" class="itemlabel"></div>
				</li>
				<li><div knowledgeitem="Achievement" class="itemlabel"></div>
				</li>
				<li><div knowledgeitem="Technology" class="itemlabel"></div>
				</li>
			</ul>
			<div class="searchknowledge">
				<input type="text" id="txtSearchTitle" autocomplete="off" value=""
					placeholder="搜索标题关键字...">
				<div onclick="fnSearchByTitle();" style="width: 40px; height: 35px; position: absolute; top: 0px; right: 0px;"></div>
			</div>
		</div>

		<!--head:农技目录-->
		<div id="knowledgecategory" class="knowledgecategory">
			<!--  
			<div class="categoryname" categorylevel="1">
				<span>一级目录</span>
			</div>
			<ul>
				<li>
					<a href="#">目录1</a>
				</li>
				<li>
					<a href="#">目录2</a>
				</li>
				<li>
					<a href="#">目录3</a>
				</li>
			</ul>
			<div class="separate"></div>
			<div class="categoryname" categorylevel="2">
				<span>二级目录</span>
			</div>
			<ul>
				<li>
					<a href="#">目录1</a>
				</li>
				<li>
					<a href="#">目录2</a>
				</li>
				<li>
					<a href="#">目录3</a>
				</li>
			</ul>
			-->
		</div>

		<!--end:农技目录-->

		<div class="clear"></div>
		<!-- head:知识库内容 -->
		<div class="knowledgelist">
			<ul id="manualList">

			</ul>
			<!--head:列表分页-->
			<div class="list_paginator">
				<input id="selectedCategoryCode" type="hidden" value="" />
				<input id="page_size" type="hidden" value="10" /> <input
					id="records_total" type="hidden" value="0" /> <a
					href="javascript:changePage(1);">上一页</a> <a id="current_page"
					currentpage="1" href="javascript:void(0); "
					class="paginator_current">1</a> <a href="javascript:changePage(2);">下一页</a>
			</div>
			<!--end:列表分页-->
		</div>
		<!-- end:知识库内容 -->

	</div>
	<!--end:内容项目-->

	<!--head:底部信息-->
	<jsp:include page="./footer.jsp"></jsp:include>
	<!--end:底部信息-->

	<script type="text/javascript" src="${ctx}/jslib/index/thickbox.js"></script>
	<script type="text/javascript" src="${ctx}/jslib/index/themepark.js"></script>
</body>
<script>
	$(function() {
		$("#txtSearchTitle").keyup(function(event){
			if (!event || event.which != 13)
                return true;
            fnSearchByTitle();
		});	
		$(".selectknowledge li").click(function(){
			var categoryCode = $(this).find(".itemlabel").attr("knowledgeitem");
			var selectedDiv = $(this).parent("ul").find(".selected");
			if(selectedDiv.length > 0){
				selectedDiv.removeClass();
				selectedDiv.addClass("itemlabel");
			}
			var index = $(this).index() + 1;
			$(this).find(".itemlabel").addClass("selected selectedlabel" + index);
			
			getCategoryTreeData();
		});
		
		var code = "${categoryCode}";
		if(code != ""){
			var div = $(".selectknowledge div[knowledgeitem=" + code + "]");
			if(div.length > 0){
				$(div).parent("li").click();
			}
		}
		
		getCategoryTreeData();
	});
	
	$.CategoryTreeData = null;
	$.RootCategoryCode = null;
	function getCategoryTreeData(categoryCode){
		var url = "${ctx}/front/web/manual/base/getCategoryTree";
		var categoryCode = $(".selectknowledge .selected").attr("knowledgeitem");
		$.ajax({
					url : url,
					data : {
						"categoryCode" : categoryCode
					},
					type : "POST",
					dataType : "json",
					success : function(json) {
						$("#knowledgecategory").html('');
						$("#manualList").html('');
						$.CategoryTreeData = json;
						//alert(JSON.stringify(json));
						var rootid = "";
						var childcount = 0;
						var item = "<div class='categoryname' categorylevel='1'><span>1级目录</span></div>";
						item += "<ul>";
						for ( var i = 0; i < json.rows.length; i++) {
							if(json.rows[i].parentID == null){
								rootid = json.rows[i].id;
								$.RootCategoryCode = json.rows[i].categoryCode;
								break;
							}							
						}
						for ( var i = 0; i < json.rows.length; i++) {
							if(json.rows[i].parentID == rootid){
								childcount++;
								item += "<li><a onclick='selectCategoryTree(this, 1);' href='javascript:void(0);' categoryid='" + json.rows[i].id + "' pid='" + json.rows[i].parentID + "' categorycode='" + json.rows[i].categoryCode + "'>" + json.rows[i].categoryName + "</a></li>";
							}
						}
						item += "</ul>";
						if(childcount > 0){
							$("#knowledgecategory").append(item);
						}
						fnSearchByCategoryCode($.RootCategoryCode);
					},
					error : function(xhr, error, thrown) {

					}
				});
	}
	
	function selectCategoryTree(obj, level){
		var parentid = $(obj).attr("categoryid");
		var categorycode = $(obj).attr("categorycode");
		
		$("#knowledgecategory").find("a").removeClass("selected");
		$(obj).addClass("selected");
		
		var json = $.CategoryTreeData;
		var nextlevel = level + 1;
		
		//清除已有目录
		var tmpnextlevel = nextlevel;
		var leveldiv = $("#knowledgecategory").find(".categoryname[categorylevel=" + tmpnextlevel + "]");
		while(leveldiv.length >0){
			var levelul = $(leveldiv).next("ul");
			var levelseparate = $(leveldiv).prev(".separate");
			if(levelul.length > 0){
				$(levelul).remove();
			}
			if(levelseparate.length > 0){
				$(levelseparate).remove();
			}
			$(leveldiv).remove();
			
			tmpnextlevel++;
			leveldiv = $("#knowledgecategory").find(".categoryname[categorylevel=" + tmpnextlevel + "]");
		}
		
		var item = "<div class='separate'></div><div class='categoryname' categorylevel='" + nextlevel + "'><span>" + nextlevel + "级目录</span></div>";
		item += "<ul>";
		var childcount = 0;
		for ( var i = 0; i < json.rows.length; i++) {
			if(json.rows[i].parentID == parentid){
				childcount++;
				item += "<li><a onclick='selectCategoryTree(this, " + nextlevel + ");' href='javascript:void(0);' categoryid='" + json.rows[i].id + "' pid='" + json.rows[i].parentID + "' categorycode='" + json.rows[i].categoryCode + "'>" + json.rows[i].categoryName + "</a></li>";
			}
		}
		item += "</ul>";
		if(childcount > 0){
			$("#knowledgecategory").append(item);
		}
		
		fnSearchByCategoryCode(categorycode);
	}

	function replaceStr(str) {
		str = str.replace("&rdquo;", "”");
		str = str.replace("&ldquo;", "“");
		str = str.replace("&gt;", ">");
		str = str.replace("&lt;", "<");
		str = str.replace("&quot;", "\"");
		// str = str.replace("''", "'");
		return str;
	}
	
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
  	
  	function fnSearchByCategoryCode(categoryCode){
	  	$("#current_page").attr("currentpage", 1);
	  	$("#current_page").text(1);
	  	$("#selectedCategoryCode").val(categoryCode);
	  	getData();
	}
	
	function fnSearchByTitle(){
	  	$("#current_page").attr("currentpage", 1);
	  	$("#current_page").text(1);
	  	$("#selectedCategoryCode").val($.RootCategoryCode);
	  	$("#knowledgecategory").find("a").removeClass("selected");
	  	getData();
	}
	
	function getData(){
	  	var searchKey = $("#txtSearchTitle").val();
	  	searchKey = searchKey.trim();
	  	var pagesize = parseInt($("#page_size").val());
	  	var current = parseInt($("#current_page").attr("currentpage"));
	  	var url = "${ctx}/front/web/manual/base/getContentByCodeWithSearch";
	  	var selectedCategoryCode = $("#selectedCategoryCode").val();

	  	$.ajax({
	            url: url,
	            data: { "rows": pagesize, "page": current, "searchKey": searchKey, "categoryCode": selectedCategoryCode },
	            type: "POST",
	            dataType: "json",
	            success: function (json) {
	            	$("#manualList").html('');
	            	$("#records_total").val(json.total);
	            	for (var i = 0; i < json.rows.length; i++) {
						var item = "<li><a class='knowledge_title' href='javascript:void(0);'>" + json.rows[i].title + "</a>"
						if(json.rows[i].resourceList != null){
	            			for(var j = 0; j < json.rows[i].resourceList.length; j++) {
	            				item += "<a href='${ctx}/front/news/base/downloadAttachment?attachmentID=" + json.rows[i].resourceList[j].autoID + "' target='_blank'>";
	            				item += json.rows[i].resourceList[j].metaDescription + "</a><br />";
	            			}
	            		}
						else{
							item += "<div class='knowledge_expandable' onclick='fnExpandable(this);'>";
							if(json.rows[i].imgContent != ""){
								item += json.rows[i].imgContent; 
							}
							item += json.rows[i].shortContent + "<a class='toggle-expand'>显示全部</a></div>";
							item += "<div class='knowledge_content' style='display: none;'>" + json.rows[i].content + "</div>";
							item += "<div class='knowledge_actions'><button class='knowledge_collapse' style='display: none;' onclick='fncollapse(this);'><i></i>收起</button></div>";
						}
						
						item += "</li>";
	            		$("#manualList").append(item);
	            	}   
	            },
	            error: function (xhr, error, thrown) {
	                
	            }
	        });
  	}
  	
  	function fnExpandable(obj){
  		$(obj).hide();
		$(obj).nextAll(".knowledge_content").show();
		$(obj).nextAll(".knowledge_actions").find(".knowledge_collapse").show();
  	}
  	
  	function fncollapse(obj){
  		$(obj).hide();
		$(obj).parent(".knowledge_actions").prevAll(".knowledge_content").hide();
		$(obj).parent(".knowledge_actions").prevAll(".knowledge_expandable").show();
  	}
</script>
</html>

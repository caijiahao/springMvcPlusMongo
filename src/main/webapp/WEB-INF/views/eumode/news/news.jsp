<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="edge" />
<c:if test="${fn:contains(sessionInfo.resourceList, '/news/detail')}">
	<script type="text/javascript">
		$.canDetail = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/news/edit')}">
	<script type="text/javascript">
		$.canEdit = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/news/delete')}">
	<script type="text/javascript">
		$.canDelete = true;
	</script>
</c:if>
<title>新闻管理</title>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',fit:true,border:false">
		<table id="dataGrid" data-options="fit:true,border:false"></table>
	</div>
	<div id="toolbar" style="display: none;">
		<c:if test="${fn:contains(sessionInfo.resourceList, '/news/add')}">
			<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon_add'">添加</a>
		</c:if>
		<c:if test="${fn:contains(sessionInfo.resourceList, '/news/delete')}">
			<a onclick="deleteFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon_del'">删除</a>
		</c:if>
		<div style="margin-bottom: 5px;">
			<select id="selectPersonal" class="easyui-combobox" panelHeight="auto" style="width:80px;">
				<option value="0" selected="selected">本人发布</option>
				<c:if test="${fn:contains(sessionInfo.resourceList, '/news/readAll')}">
					<option value="1">查看全部</option>
				</c:if>
			</select>
			所属栏目：
			<select id="selectCategory" class="easyui-combobox" panelHeight="auto" style="width:80px;">
				<option value="" selected="selected">全部</option>
				<c:forEach items="${categoryList}" var="c">
					<option value="${c.autoID}">${c.categoryName} 
					</option>
				</c:forEach>
			</select>
			<input id="searchTitle" type="text" value="" placeholder="输入搜索的标题" style="width: 120px; margin-left: 10px;" />
			<a href="#" class="easyui-linkbutton" onclick="doSearch()">搜索</a>
		</div>
	</div>
	<input type="hidden" name="categoryTypeID" value="${categoryTypeID }"/>
	<script type="text/javascript">	
	var dataGrid;
	/* 默认显示所有的资源 */
	$(function() {
		dataGrid = $('#dataGrid').datagrid({
			url : '${ctx}' + '/news/dataGrid?categoryTypeID=' + $('input[name=categoryTypeID]').val(),
			queryParams: { selectPersonal: $("#selectPersonal").combobox('getValue')},
			striped : true,
			rownumbers : false,//是否显示行号
			pagination : true,
			singleSelect : true,//允许选择多行
			idField : 'autoID',
			sortName : 'autoID',
			sortOrder : 'desc',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
			frozenColumns : [ [ {
				width : '30',
				field : 'ck',
				checkbox : true
			} ,{
				width : '100',
				title : '所属栏目',
				field : 'categoryName',
				sortable : false
			} ,{
				width : '250',
				title : '新闻标题',
				field : 'title',
				sortable : true
			} ,{
				width : '150',
				title : '发布时间',
				field : 'publishDate',
				sortable : true
			} ,{
				width : '150',
				title : '发布人',
				field : 'author',
				sortable : true
			} ,{
				width : '100',
				title : '状态',
				field : 'statusDesc',
				sortable : false
			} ,{
				field : 'action',
				title : '操作',
				width : 160,
				formatter : function(value, row, index) {
					var str = '&nbsp;';
					if ($.canDetail) {
						str += $.formatString('<a href="javascript:void(0)" onclick="detailFun(\'{0}\');" >详情</a>', row.autoID);
						str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
					}
					if ($.canEdit && row.statusDesc == "编辑") {
							str += $.formatString('<a href="javascript:void(0)" onclick="editFun(\'{0}\');" >编辑</a>', row.autoID);
							str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
					}
					if ($.canDelete && row.statusDesc == "编辑") {
						str += $.formatString('<a href="javascript:void(0)" onclick="deleteFun({0});" >删除</a>', row.autoID);
						str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
					}
					if (row.statusDesc == "编辑") {//编辑状态，非发布状态
						str += $.formatString('<a href="javascript:void(0)" onclick="publishFun({0});" >发布</a>', row.autoID);
					}else if(row.statusDesc == "发布"){//发布状态
						str += $.formatString('<a href="javascript:void(0)" onclick="publishCallbackFun({0});" >撤回</a>', row.autoID);
					}
					return str;
				}
			} ] ],
			toolbar : '#toolbar'
		});
	});
	
	function deleteFun(id) {
		/* 定义json数组ids */
		var ids = [];
		if (id == undefined) {//点击删除按钮才会触发这个 
			var rows = dataGrid.datagrid('getSelections');
			if (rows.length==0) {  
				parent.$.messager.alert("提示", "请选择要删除的行！", "info");  
	            return;  
	        }else{ 
				for(i=0;i<rows.length;i++)
					ids.push(rows[i].autoID);
	        }
		} else {//点击操作里面的删除图标会触发这个
			dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
			ids.push(id);
		}
		var jsonIds = JSON.stringify(ids);
		parent.$.messager.confirm('询问', '您是否要删除当前选中的资源？', function(b) {
			if (b) {
					progressLoad();
					$.post('${ctx}/news/delete', {
						ids : jsonIds
					}, function(result) {
						if (result.success) {
							//清除datagrid之前的选择，防止下次删除时重复删除
							dataGrid.datagrid('clearSelections');
							parent.$.messager.alert('提示', result.msg, 'info');
							dataGrid.datagrid('reload');
						}
						progressClose();
					}, 'JSON');
			}
		});
	}
	
	function detailFun(id) {
		if (id == undefined) {
			var rows = dataGrid.datagrid('getSelections');
			id = rows[0].autoID;
		} else {
			dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		parent.$.modalDialog({
			title : '详情',
			width : 950,
			height : 600,
			href : '${ctx}/news/detailPage?id=' + id
		});
	}
	
	function editFun(id) {
		if (id == undefined) {
			var rows = dataGrid.datagrid('getSelections');
			id = rows[0].autoID;
		} else {
			dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		parent.$.modalDialog({
			title : '编辑',
			width : 950,
			height : 600,
			href : '${ctx}/news/editPage?id=' + id,
			buttons : [ {
				text : '保存',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#newsEditForm');
					f.submit();
				}
			} ]
		});
	}
	function addFun() {
		parent.$.modalDialog({
			title : '添加',
			width : 950,
			height : 600,
			href : '${ctx}/news/addPage?categoryTypeID=' + $('input[name=categoryTypeID]').val(),
			buttons : [ {
				text : '保存',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#newsAddForm');
					f.submit();
				}
			} ]
		});
	}
	
	function publishFun(id){
		$.ajax({
			type:"post",
			url:"${ctx}/news/publish",
			data:{
				"id":id
			},
			dataType:"json",
			success:function(result){
				if (result.success) {
					dataGrid.datagrid('reload');//
				} else {
					$.messager.alert('提示', result.msg, 'warning');
				}
			}
		});
	}
	
	function publishCallbackFun(id){
		$.ajax({
			type:"post",
			url:"${ctx}/news/unPublish",
			data:{
				"id":id
			},
			dataType:"json",
			success:function(result){
				if (result.success) {
					dataGrid.datagrid('reload');//
				} else {
					$.messager.alert('提示', result.msg, 'warning');
				}
			}
		});
	}
	
	function doSearch(){
		$('#dataGrid').datagrid('load', {    
	        title: $('#searchTitle').val(),
	        selectPersonal: $("#selectPersonal").combobox('getValue'),
	        categoryID: $("#selectCategory").combobox('getValue')
	    }); 
	}
	
	</script>
</body>
</html>
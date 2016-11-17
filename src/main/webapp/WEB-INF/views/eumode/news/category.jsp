<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="edge" />
<c:if test="${fn:contains(sessionInfo.resourceList, '/category/edit')}">
	<script type="text/javascript">
		$.canEdit = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/category/delete')}">
	<script type="text/javascript">
		$.canDelete = true;
	</script>
</c:if>
<title>新闻管理</title>
	<script type="text/javascript">
	var dataGrid;
	/* 默认显示所有的资源 */
	$(function() {
		dataGrid = $('#dataGrid').datagrid({
			url : '${ctx}' + '/category/dataGrid',
			striped : true,
			rownumbers : false,//是否显示行号
			pagination : true,
			singleSelect : true,//允许选择多行
			idField : 'autoID',
			sortName : 'type',
			sortOrder : 'asc',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
			frozenColumns : [ [ {
				width : '30',
				field : 'ck',
				checkbox : true
			},{
				width : '150',
				title : '栏目名称',
				field : 'categoryName',
				sortable : true
			},{
				width : '250',
				title : '栏目描述',
				field : 'categoryDescription',
				sortable : true
			},{
				width : '150',
				title : '栏目类型',
				field : 'typeDesc'
			}, {
				field : 'action',
				title : '操作',
				width : 120,
				formatter : function(value, row, index) {
					var str = '&nbsp;';
					if ($.canEdit) {
							str += $.formatString('<a href="javascript:void(0)" onclick="editFun(\'{0}\');" >编辑</a>', row.autoID);
						}
					str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
					if ($.canDelete) {
						str += $.formatString('<a href="javascript:void(0)" onclick="deleteFun({0});" >删除</a>', row.autoID);
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
					$.post('${ctx}/category/delete', {
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
			id = rows[0].id;
		} else {
			dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		parent.$.modalDialog({
			title : '详情',
			width : 500,
			height : 300,
			href : '${ctx}/category/detailPage?id=' + id
		});
	}
	
	function editFun(id) {
		if (id == undefined) {
			var rows = dataGrid.datagrid('getSelections');
			id = rows[0].id;
		} else {
			dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
		parent.$.modalDialog({
			title : '编辑',
			width : 500,
			height : 280,
			href : '${ctx}/category/editPage?id=' + id
		});
	}
	
	function addFun() {
		parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
		parent.$.modalDialog({
			title : '添加',
			width : 500,
			height : 280,
			href : '${ctx}/category/addPage'
		});
	}
	
	function doSearch(){
		$('#dataGrid').datagrid('load', {    
	        type: $('#selectType').combobox('getValue'),
	    }); 
	}
	</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',fit:true,border:false">
		<table id="dataGrid" data-options="fit:true,border:false"></table>
	</div>
	
	<div id="toolbar" style="display: none;">
		<c:if test="${fn:contains(sessionInfo.resourceList, '/category/add')}">
			<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon_add'">添加</a>
		</c:if>
	
		<c:if test="${fn:contains(sessionInfo.resourceList, '/category/delete')}">
			<a onclick="deleteFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon_del'">删除</a>
		</c:if>
		<div style="margin-bottom: 5px;">
			栏目类型: 
			<select id="selectType" class="easyui-combobox" panelHeight="auto" style="width:100px">
				<option value="">全部</option>
				<c:forEach items="${typeList}" var="type">
					<option value="${type.id}">${type.text} 
					</option>
				</c:forEach>
			</select>
			<a href="#" class="easyui-linkbutton" onclick="doSearch()">搜索</a>
		</div>
	</div>
</body>
</html>
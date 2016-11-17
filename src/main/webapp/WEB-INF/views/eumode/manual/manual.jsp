<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="edge" />
<c:if test="${fn:contains(sessionInfo.resourceList, '/manualContent/detail')}">
	<script type="text/javascript">
		$.canDetail = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/manualContent/edit')}">
	<script type="text/javascript">
		$.canEdit = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/manualContent/delete')}">
	<script type="text/javascript">
		$.canDelete = true;
	</script>
</c:if>
<title>知识管理</title>
	<script type="text/javascript">
	var dataGrid;
	/* 默认显示所有的资源 */
	$(function() {
		dataGrid = $('#dataGrid').datagrid({
			url : '${ctx}' + '/manualContent/dataGrid',
			striped : true,
			rownumbers : false,//是否显示行号
			pagination : true,
			singleSelect : true,//允许选择多行
			idField : 'autoID',
			sortName : 'autoID',
			sortOrder : 'asc',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
			frozenColumns : [ [ {
				width : '30',
				field : 'ck',
				checkbox : true
			},{
				width : '150',
				title : '题目',
				field : 'title',
				sortable : true
			},{
				width : '150',
				title : '栏目编号',
				field : 'categoryCode',
				sortable : true
			}, {
				field : 'action',
				title : '操作',
				width : 120,
				formatter : function(value, row, index) {
					var str = '&nbsp;';
					if ($.canDetail) {
						str += $.formatString('<a href="javascript:void(0)" onclick="detailFun(\'{0}\');" >详情</a>', row.autoID);
					}
					str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
					if ($.canDelete) {
						str += $.formatString('<a href="javascript:void(0)" onclick="deleteFun({0});" >删除</a>', row.autoID);
					}
					str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
					if ($.canEdit) {
							str += $.formatString('<a href="javascript:void(0)" onclick="editFun(\'{0}\');" >编辑</a>', row.autoID);
					}
					return str;
				}
			} ] ],
			toolbar : '#toolbar'
		});
		
		$('#categoryRoot').combotree({
			url : '${ctx}/manualCategory/getManualTreeByCode',
			parentField : 'pid',
			lines : true,
			panelHeight : 'auto',
			onChange: function (n, o) {
				var manualCategoryID=$("[name='categoryRoot']").val();
        		dataGrid.datagrid('load', {
				    manualCategoryID: manualCategoryID
				});
            }
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
					ids.push(rows[i].id);
	        }
		} else {//点击操作里面的删除图标会触发这个
			dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
			ids.push(id);
		}
		var jsonIds = JSON.stringify(ids);
		parent.$.messager.confirm('询问', '您是否要删除当前选中的资源？', function(b) {
			if (b) {
					progressLoad();
					$.post('${ctx}/manualContent/delete', {
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
			width : 600,
			height : 500,
			href : '${ctx}/manualContent/detailPage?id=' + id
		});
	}
	function editFun(id) {
		if (id == undefined) {
			var rows = dataGrid.datagrid('getSelections');
			id = rows[0].id;
		} else {
			dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		parent.$.modalDialog({
			title : '编辑',
			width : 600,
			height : 500,
			href : '${ctx}/manualContent/editPage?id=' + id,
			buttons : [ {
				text : '编辑',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#dataEditForm');
					f.submit();
				}
			} ]
		});
	}
	function addFun() {
		parent.$.modalDialog({
			title : '添加',
			width : 800,
			height : 400,
			href : '${ctx}/manualContent/addPage',
			buttons : [ {
				text : '添加',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#newsAddForm');
					f.submit();
				}
			} ]
		});
	}
	
	</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',fit:true,border:false">
		<table id="dataGrid" data-options="fit:true,border:false"></table>
	</div>
	<div id="toolbar" style="display: none;">
	    <div>

		<c:if test="${fn:contains(sessionInfo.resourceList, '/manualContent/delete')}">
			<a onclick="deleteFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon_del'">删除</a>
		</c:if>
		</div>
		<div>
			<span>选择目录库</span>
			<select id="categoryRoot" name="categoryRoot" class="easyui-combobox" panelHeight="auto" style="width:100px">
			</select>

		</div>
	</div>
</body>
</html>
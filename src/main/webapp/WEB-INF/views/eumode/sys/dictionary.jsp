<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="edge" />
<c:if test="${fn:contains(sessionInfo.resourceList, '/dictionary/edit')}">
	<script type="text/javascript">
		$.canEdit = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/dictionary/delete')}">
	<script type="text/javascript">
		$.canDelete = true;
	</script>
</c:if>
<title>资源管理</title>
<script type="text/javascript">
	var dataGrid;
	var dictionarytypeTree;
	$(function() {
	
		dictionarytypeTree = $('#dictionarytypeTree').tree({
			url : '${ctx}/dictionarytype/tree',
			parentField : 'pid',
			lines : true,
			onClick : function(node) {
 				dataGrid.datagrid('load', {
				    dictionarytypeId: node.id
				});
 			}
		});
	
		dataGrid = $('#dataGrid').datagrid({
			url : '${ctx}' + '/dictionary/dataGrid',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			idField : 'id',
			sortName : 'id',
			sortOrder : 'asc',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
			frozenColumns : [ [ {
				width : '100',
				title : 'id',
				field : 'id',
				align : 'center',
				sortable : true
			},{
				width : '100',
				title : '编码',
				field : 'code',
				align : 'center',
				sortable : true
			}, {
				width : '80',
				title : '名称',
				field : 'text',
				align : 'center',
				sortable : true
			} , {
				width : '80',
				title : '排序号',
				field : 'seq',
				align : 'center',
				sortable : true
			},{
				width : '80',
				title : '所属类别',
				align : 'center',
				field : 'dictionarytypeName'
			}, {
				width : '80',
				title : '是否默认',
				field : 'isDefault',
				align : 'center',
				sortable : true,
				formatter : function(value, row, index) {
					switch (value) {
					case 1:
						return '默认';
					case 0:
						return '否';	
					}
				}
			}, {
				width : '80',
				title : '状态',
				field : 'active',
				align : 'center',
				formatter : function(value, row, index) {
					switch (value) {
					case 1:
						return '正常';
					case 0:
						return '停用';	
					}
				}
			} , {
				field : 'action',
				title : '操作',
				width : 120,
				align : 'center',
				formatter : function(value, row, index) {
					var str = '&nbsp;';
					if(row.isdefault!=0){
						if ($.canEdit) {
							str += $.formatString('<a href="javascript:void(0)" onclick="editFun(\'{0}\');" >编辑</a>', row.id);
						}
						str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
						if ($.canDelete) {
							str += $.formatString('<a href="javascript:void(0)" onclick="deleteFun(\'{0}\');" >删除</a>', row.id);
						}
					}
					return str;
				}
			} ] ],
			toolbar : '#toolbar'
		});
	});
	
	function addFun() {
		parent.$.modalDialog({
			title : '添加',
			width : 500,
			height : 300,
			href : '${ctx}/dictionary/addPage',
			buttons : [ {
				text : '添加',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#dictionaryAddForm');
					f.submit();
				}
			} ]
		});
	}
	
	function deleteFun(id) {
		if (id == undefined) {//点击右键菜单才会触发这个
			var rows = dataGrid.datagrid('getSelections');
			id = rows[0].id;
		} else {//点击操作里面的删除图标会触发这个
			dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		parent.$.messager.confirm('询问', '您是否要删除当前字典？', function(b) {
			if (b) {
				var currentUserId = '${sessionInfo.id}';/*当前登录用户的ID*/
				if (currentUserId != id) {
					progressLoad();
					$.post('${ctx}/dictionary/delete', {
						id : id
					}, function(result) {
						if (result.success) {
							parent.$.messager.alert('提示', result.msg, 'info');
							dataGrid.datagrid('reload');
						}
						progressClose();
					}, 'JSON');
				} else {
					parent.$.messager.show({
						title : '提示',
						msg : '不可以删除自己！'
					});
				}
			}
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
			width : 500,
			height : 300,
			href : '${ctx}/dictionary/editPage?id=' + id,
			buttons : [ {
				text : '编辑',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#dictionaryEditForm');
					f.submit();
				}
			} ]
		});
	}
	

	</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',fit:true,border:false" title="字典列表">
		<table id="dataGrid" data-options="fit:true,border:false"></table>
	</div>
	<div data-options="region:'west',border:false,split:true" title="字典类别" style="width:200px;overflow: hidden; ">
		<table id="dictionarytypeTree" style="width:180px;margin: 10px 10px 10px 10px"></table>
	</div>
	<div id="toolbar" style="display: none;">
		<c:if test="${fn:contains(sessionInfo.resourceList, '/user/add')}">
			<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon_add'">添加</a>
		</c:if>
	</div>
</body>
</html>
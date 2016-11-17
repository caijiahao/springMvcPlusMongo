<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="edge" />
<c:if test="${fn:contains(sessionInfo.resourceList, '/user/edit')}">
	<script type="text/javascript">
		$.canEdit = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/user/delete')}">
	<script type="text/javascript">
		$.canDelete = true;
	</script>
</c:if>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>
	<script type="text/javascript">
	var dataGrid;
	var organizationTree;
	$(function() {
		
	
		organizationTree = $('#organizationTree').tree({
			url : '${ctx}/organization/tree',
			parentField : 'pid',
			lines : true,
			onClick : function(node) {
				dataGrid.datagrid('load', {
				    organizationId: node.id
				});
			}
		});
	
		dataGrid = $('#dataGrid').datagrid({
			url : '${ctx}' + '/user/dataGrid',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			idField : 'autoID',
			sortName : 'createDate',
			sortOrder : 'asc',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
			frozenColumns : [ [ {
				width : '100',
				title : '登录名',
				field : 'loginName',
				sortable : false
			}, {
				width : '80',
				title : '姓名',
				field : 'realName',
				sortable : true
			}, {
				width : '80',
				title : '职称',
				field : 'techTitle',
				sortable : true
			}, {
				width : '80',
				title : '专长',
				field : 'techType',
				sortable : true
			},{
				width : '80',
				title : '部门ID',
				field : 'organizationId',
				hidden : true
			},{
				width : '80',
				title : '所属部门',
				field : 'organizationName'
			}] ],
			columns : [ [ {
				width : '50',
				title : '性别',
				field : 'sex',
				sortable : true,
				formatter : function(value, row, index) {
					switch (value) {
					case 1:
						return '男';
					case 0:
						return '女';
					}
				}
			}, {
				width : '50',
				title : '年龄',
				field : 'age',
				sortable : true
			}, {
				width : '80',
				title : '用户类型',
				field : 'roleNames',
				sortable : true
			},{
				width : '80',
				title : '状态',
				field : 'active',
				sortable : true,
				formatter : function(value, row, index) {
					switch (value) {
					case 1:
						return '正常';
					case 0:
						return '停用';
					}
				}
			},{
				width : '80',
				title : '是否发布',
				field : 'needPublish',
				sortable : true,
				formatter : function(value, row, index) {
					switch (value) {
					case 1:
						return '发布';
					case 0:
						return '不发布';
					}
				}
			} , {
				field : 'action',
				title : '操作',
				width : 200,
				formatter : function(value, row, index) {
					var str = '';
					if(row.isdefault!=0){
						if ($.canEdit) {
							str += $.formatString('<a href="javascript:void(0)" onclick="editFun(\'{0}\');" >编辑</a>', row.autoID);
						}
						str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
						if ($.canDelete) {
							str += $.formatString('<a href="javascript:void(0)" onclick="deleteFun(\'{0}\');" >删除</a>', row.autoID);
						}
						str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
						str += $.formatString('<a href="javascript:void(0)" onclick="manualFun(\'{0}\');" >科目</a>', row.autoID);
					}
					return str;
				}
			}] ],
			toolbar : '#toolbar'
		});
	});
	
		function manualFun(id) {
			if (id == undefined) {
				var rows = dataGrid.datagrid('getSelections');
				id = rows[0].id;
			} else {
				dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
			}
			
			parent.$.modalDialog({
				title : '授权',
				width : 500,
				height : 500,
				href : '${ctx}/user/manualPage?id=' + id,
				buttons : [ {
					text : '授权',
					handler : function() {
						parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
						var f = parent.$.modalDialog.handler.find('#usermanualForm');
						f.submit();
					}
				} ]
			});
		}
	function addFun() {
		parent.$.modalDialog({
			title : '添加',
			width : 500,
			height : 450,
			href : '${ctx}/user/addPage',
			buttons : [ {
				text : '添加',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#userAddForm');
					f.submit();
				}
			} ]
		});
	}
	
	function deleteFun(id) {
		if (id == undefined) {//点击右键菜单才会触发这个
			var rows = dataGrid.datagrid('getSelections');
			id = rows[0].autoID;
		} else {//点击操作里面的删除图标会触发这个
			dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		parent.$.messager.confirm('询问', '您是否要删除当前用户？', function(b) {
			if (b) {
				var currentUserId = '${sessionInfo.id}';/*当前登录用户的ID*/
				if (currentUserId != id) {
					progressLoad();
					$.post('${ctx}/user/delete', {
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
			height : 450,
			href : '${ctx}/user/editPage?id=' + id,
			buttons : [ {
				text : '编辑',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#userEditForm');
					f.submit();
				}
			} ]
		});
	}
	
	function searchByBox(value,name)
	{
		$('#dataGrid').datagrid('load', {    
			"searchKey": name, "searchValue": value,
		});	
	}
	
	function doSearch(){
			    $('#dataGrid').datagrid('load', {    
	        realName: $('#searchByName').val(),
	    }); 
	}
	

	</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',fit:true,border:false" title="用户列表">
		<table id="dataGrid" data-options="fit:true,border:false"></table>
	</div>
	<div data-options="region:'west',border:false,split:true" title="组织机构" style="width:200px;overflow: hidden; ">
		<table id="organizationTree" style="width:180px;margin: 10px 10px 10px 10px"></table>
	</div>
	<div id="toolbar" style="display: none;">
	     <div>
            <c:if test="${fn:contains(sessionInfo.resourceList, '/user/add')}">
			<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon_add'">添加</a>
		    </c:if>
		 </div>
		 <div>
			 姓名：
			<input id="searchByName" type="text" placeholder="请输入姓名" class="easyui-validatebox"/>
			<a href="#" class="easyui-linkbutton" data-options="plain:true" onclick="doSearch()">搜索</a>
		 </div>
	</div>

</body>

</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="edge" />
<c:if test="${fn:contains(sessionInfo.resourceList, '/manualCategory/editPage')}">
	<script type="text/javascript">
		$.canEdit = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/manualCategory/delete')}">
	<script type="text/javascript">
		$.canDelete = true;
	</script>
</c:if>
<title>知识库栏目管理</title>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',fit:true,border:false">
		<table id="dataGrid" data-options="fit:true,border:false"></table>
	</div>
	<div id="toolbar" style="display: none;">
	    <div>
		<c:if test="${fn:contains(sessionInfo.resourceList, '/manualCategory/add')}">
			<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon_add'">添加</a>
		</c:if>
	
		<c:if test="${fn:contains(sessionInfo.resourceList, '/manualCategory/delete')}">
			<a onclick="deleteFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon_del'">删除</a>
		</c:if>
		</div>
		<div>
			<span>选择目录库种类</span>
			<select id="categoryRoot" name="categoryRoot" class="easyui-combobox" panelHeight="auto" style="width:100px">
				<c:forEach items="${rootList}" var="rl">
					<option value="${rl.categoryCode}">${rl.categoryName} 
					</option>
				</c:forEach>
			</select>
			<!-- 
			<input class="easyui-combobox" id="categoryRoot" name="categoryRoot" style="width:100%;" data-options="
                    loader: rootLoader,
                    mode: 'remote',
                    valueField: 'code',
                    textField: 'name',
                    label: 'State:',
                    labelPosition: 'top'			
                    ">
             -->
		</div>
	</div>
</body>
<script type="text/javascript">
	var dataGrid;
	var categoryCodeRoot="Variety";
	/* 默认显示所有的资源 */
	$(function() {
		dataGrid = $('#dataGrid').treegrid({
			url : '${ctx}' + '/manualCategory/treeGrid',
			idField : 'id',
			treeField : 'categoryName',
			parentField : 'parentID',
			fit : true,
			fitColumns : false,
			border : false,
			queryParams:{ categoryCode: $('#categoryRoot').val() },
			columns : [ [ {
				title : '编号',
				width : '30',
				field : 'id',
			},{
				width : '150',
				title : '栏目名称',
				field : 'categoryName',			
			} ,{
				width : '250',
				title : '栏目描述',
				field : 'categoryDescription',	
			} ,{
				width : '80',
				title : '栏目pid',
				field : 'parentID',
			}, {
				field : 'action',
				title : '操作',
				width : '120',
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
		
		$('#categoryRoot').combobox({
        	onChange: function (n, o) {
        		getCategoryDatagrid();
            }
        });
	});

	
	function reLoadByCategoryCode(code){
		dataGrid.treegrid('load', { categoryCode: code });
	}
	
	function deleteFun(id) {
		/* 定义json数组ids */
		var ids = [];
		if (id == undefined) {//点击删除按钮才会触发这个 
			var rows = dataGrid.treegrid('getSelections');
			if (rows.length==0) {  
				parent.$.messager.alert("提示", "请选择要删除的行！", "info");  
	            return;  
	        }else{ 
				for(i=0;i<rows.length;i++)
					ids.push(rows[i].id);
	        }
		} else {//点击操作里面的删除图标会触发这个
			dataGrid.treegrid('unselectAll').treegrid('uncheckAll');
			ids.push(id);
		}
		var jsonIds = JSON.stringify(ids);
		parent.$.messager.confirm('询问', '您是否要删除当前选中的资源？', function(b) {
			if (b) {
					progressLoad();
					$.post('${ctx}/manualCategory/delete', {
						ids : jsonIds
					}, function(result) {
						if (result.success) {
							//清除datagrid之前的选择，防止下次删除时重复删除
							dataGrid.treegrid('clearSelections');
							parent.$.messager.alert('提示', result.msg, 'info');
							dataGrid.treegrid('load',{categoryCode: categoryCodeRoot});
						}
						progressClose();
					}, 'JSON');
			}
		});
	}
	
	function detailFun(id) {
		if (id == undefined) {
			var rows = dataGrid.treegrid('getSelections');
			id = rows[0].id;
		} else {
			dataGrid.treegrid('unselectAll').treegrid('uncheckAll');
		}
		parent.$.modalDialog({
			title : '详情',
			width : 500,
			height : 300,
			href : '${ctx}/manualCategory/detailPage?id=' + id
		});
	}
	
	function editFun(id) {
		if (id == undefined) {
			var rows = dataGrid.treegrid('getSelections');
			id = rows[0].id;
		} else {
			dataGrid.treegrid('unselectAll').treegrid('uncheckAll');
		}
		parent.$.modalDialog({
			title : '编辑',
			width : 600,
			height : 500,
			href : '${ctx}/manualCategory/editPage?id=' + id+'&categoryRoot='+categoryCodeRoot,
			buttons : [ {
				text : '编辑',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#categoryEditForm');
					f.submit();
				}
			} ]
		});
	}
	
		function addFun() {
		parent.$.modalDialog({
			title : '添加',
			width : 500,
			height : 300,
			href : '${ctx}/manualCategory/addPage?categoryRoot='+categoryCodeRoot,
			buttons : [ {
				text : '添加',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#categoryAddForm');
					f.submit();
				}
			} ]
		});
	}
	
		function getCategoryDatagrid() {
			categoryCodeRoot=$("[name='categoryRoot']").val();
		//	alert(categoryCodeRoot);
			reLoadByCategoryCode(categoryCodeRoot);	
	}
	
	
	    var rootLoader = function(param,success,error){
            $.ajax({
                url: '${ctx}' + '/manualCategory/getRootCategory',
				async: false,
                dataType: 'json',
                success: function(data){
                    var items = new Array();
					for(var e in data.obj)
					{
						var item= new Object();
						item.code= data.obj[e].categoryCode;
						item.name= data.obj[e].categoryName;
						if(item.code=="Variety")
						{
							item.selected=true;
						}
						else{
							item.selected=false;
						}
						items[e]=item;
					}
                    success(items);
                },
                error: function(){
                    error.apply(this, arguments);
                }
            });
        }
</script>
</html>
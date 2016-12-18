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
<title>传感器管理</title>
	<script type="text/javascript">
		<!--将时间戳转为日期-->
		function getTime() {
			var ts = arguments[0] || 0;
			var t, y, m, d, h, i, s;
			t = ts ? new Date(ts) : new Date();
			y = t.getFullYear();
			m = t.getMonth() + 1;
			d = t.getDate();
			h = t.getHours();
			i = t.getMinutes();
			s = t.getSeconds();
			// 可根据需要在这里定义时间格式
			return y + '-' + (m < 10 ? '0' + m : m) + '-' + (d < 10 ? '0' + d : d) + ' ' + (h < 10 ? '0' + h : h) + ':' + (i < 10 ? '0' + i : i) + ':' + (s < 10 ? '0' + s : s);
		}
	var dataGrid;
	var stationTree;
	var sensorDateGrid;
	$(function() {		

		var monitoringNode ={
			station:"9"
		}

		stationTree = $('#stationTree').tree({
			url : '${ctx}/station/tree',
			parentField : 'pid',
			lines : true,
			onClick : function(node) {
				monitoringNode.station=node.id
				$.ajax({

					type : 'post',

					url : '${ctx}/pestMoniNode/getnodeinfo',

					contentType : 'application/json',
					//json数据

					data : JSON.stringify(monitoringNode),

					dataType: 'json',

					//请求成功后的回调函数

					success : function(data) {
						if(data.length == 7){
							sensorDateGrid=$('#sensorDateGrid').datagrid({
								url : '${ctx}' + '/sensor/dataGrid',
								striped : true,
								rownumbers : false,//是否显示行号
								pagination : true,
								singleSelect : false,//允许选择多行
								idField : 'autoID',
								sortName : 'autoID',
								sortOrder : 'desc',
								pageSize : 50,
								pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
								frozenColumns : [ [ {
									width : '30',
									field : 'ck',
									checkbox : true
								},{
									width : '100',
									title : '监测点',
									field : 'serialNum',
									sortable : true
								} ,{
									width : '150',
									title : '采集日期',
									field : 'createDate',
									sortable : true,
									formatter:function(date){
										return getTime(date)
									}
								},{
									width : '60',
									title : data[0].name,
									field : 'sensor1',
									sortable : true
								},{
									width : '60',
									title : data[1].name,
									field : 'sensor2',
									sortable : true
								},{
									width : '60',
									title : data[2].name,
									field : 'sensor3',
									sortable : true
								},{
									width : '60',
									title : data[3].name,
									field : 'sensor4',
									sortable : true
								},{
									width : '60',
									title : data[4].name,
									field : 'sensor5',
									sortable : true
								},{
									width : '60',
									title : data[5].name,
									field : 'sensor6',
									sortable : true
								},{
									width : '60',
									title : data[6].name,
									field : 'sensor7',
									sortable : true
								}] ],
								//toolbar : '#toolbar'
							});
						}
					}

				});
				dataGrid.datagrid('load', {
				    station: node.id
				});
				sensorDateGrid.datagrid('load', {
				    station: node.id
				});
			}
		});
	
		dataGrid = $('#dataGrid').datagrid({
			url : '${ctx}' + '/pestMoniNode/dataGrid',
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
				width : '150',
				title : '传感器名称',
				field : 'name',
				sortable : true
			} ,{
				width : '100',
				title : '传感器节点',
				field : 'map',
				sortable : true
			} ,{
				width : '50',
				title : '坐标：x',
				field : 'locationX',
				sortable : true
			},{
				width : '50',
				title : '坐标：y',
				field : 'locationY',
				sortable : true
			},{
				width : '200',
				title : '监测位置',
				field : 'address',
				sortable : true
			},{
				width : '250',
				title : '描述',
				field : 'description',
				sortable : true
			}, {
				field : 'active',
				title : '操作',
				width : 120,
				formatter : function(value, row, index) {
					var str = '&nbsp;';
					
					str += $.formatString('<a href="javascript:void(0)" onclick="detailFun(\'{0}\');" >详情</a>', row.autoID);
						
					str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
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
			toolbar : '#toolbar',
			//onClickRow : function(index,row) {
			//	sensorDateGrid.datagrid('load', {
			//	    monitoringNodeId: row.autoID
			//	});
			//}
		});
		
		sensorDateGrid=$('#sensorDateGrid').datagrid({
			url : '${ctx}' + '/sensor/dataGrid',
			striped : true,
			rownumbers : false,//是否显示行号
			pagination : true,
			singleSelect : false,//允许选择多行
			idField : 'autoID',
			sortName : 'autoID',
			sortOrder : 'desc',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
			frozenColumns : [ [ {
				width : '30',
				field : 'ck',
				checkbox : true
			},{
				width : '100',
				title : '监测点',
				field : 'serialNum',
				sortable : true
			} ,{
				width : '150',
				title : '采集日期',
				field : 'createDate',
				sortable : true,
				formatter:function(date){
					return getTime(date)
				}
			},{
				width : '60',
				title : '${sensor1}',
				field : 'sensor1',
				sortable : true
			},{
				width : '60',
				title : '${sensor2}',
				field : 'sensor2',
				sortable : true
			},{
				width : '60',
				title : '${sensor3}',
				field : 'sensor3',
				sortable : true
			},{
				width : '60',
				title : '${sensor4}',
				field : 'sensor4',
				sortable : true
			},{
				width : '60',
				title : '${sensor5}',
				field : 'sensor5',
				sortable : true
			},{
				width : '60',
				title : '${sensor6}',
				field : 'sensor6',
				sortable : true
			},{
				width : '60',
				title : '${sensor7}',
				field : 'sensor7',
				sortable : true
			},{
				width : '60',
				title : '${sensor8}',
				field : 'sensor8',
				sortable : true
			},{
				width : '60',
				title : '${sensor9}',
				field : 'sensor9',
				sortable : true
			},{
				width : '60',
				title : '${sensor10}',
				field : 'sensor10',
				sortable : true
			}] ],
			//toolbar : '#toolbar'
		});
		
	});
	
	
	function addFun() {
		parent.$.modalDialog({
			title : '添加',
			width : 500,
			height : 450,
			href : '${ctx}/pestMoniNode/addPage',
			buttons : [ {
				text : '添加',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#moniNodeAddForm');
					f.submit();
				}
			} ]
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
			href : '${ctx}/pestMoniNode/editPage?id=' + id,
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
	<div data-options="region:'center',fit:true,border:false" title="设备列表">
		<table id="dataGrid" data-options="fit:true,border:false"></table>
	</div>
	<div data-options="region:'west',border:false,split:true" title="站点列表" style="width:200px;overflow: hidden; ">
		<table id="stationTree" style="width:180px;margin: 10px 10px 10px 10px"></table>
	</div>
	<div data-options="region:'east',border:false,split:true" title="数据列表" style="width:800px;overflow: hidden; ">
		<table id="sensorDateGrid" data-options="fit:true,border:false"></table>
	</div>
	<div id="toolbar" style="display: none;">
	     <div>
            <c:if test="${fn:contains(sessionInfo.resourceList, '/MoniNode/add')}">
			<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon_add'">添加</a>
			<a  href="${ctx}/sensorUpdate/updateStation?stationId=9" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon_add'">更新数据</a>
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
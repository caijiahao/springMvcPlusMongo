<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<div class="dholl" style="margin:5px 5px 5px 5px">
<div class="pageHeader">
   <div width="450" style="float:left">
    <div id="nodeInfo"  >
			<div class="unit">
				<label>监测点基本信息：</label>
			</div>
		<table class="nodeInfo"  style="border-bottom-width:1px;">
			<tbody>
			<tr>
				<td width="220" height="30">监测点名称：${moniNode_info.moniNodeName}</td>
				<td width="220" height="30">监测点位置：[${moniNode_info.location_x},${moniNode_info.location_y}]</td>
			</tr>
			<tr>
			    <td width="220" height="30">监测点地址：${moniNode_info.location}</td>
				<td width="220" height="30">创建用户：${moniNode_info.createBy}</td>
			</tr>
			<tr>
			    <td width="220" height="30">监测点状态：正常</td>
				<td width="220" height="30">创建日期：${moniNode_info.createDate}</td>
			</tr>
		  </tbody>
		</table>

	</div>
	<div class="searchBar"  >
		<form method="post" action="#"  onsubmit="return navTabSearch(this);">
		<div class="pageFormContent" >
			<div class="unit">
				<label>请选择检索条件：</label>
			</div>
			<div class="unit">
				<label>虫害种类：</label>
				<label class="radioButton"><input name="name" type="radio" />全部</label>
				<label class="radioButton"><input name="name" type="radio" />桔小实蝇</label>
				<label class="radioButton"><input name="name" type="radio" />烟粉虱</label>
				<label class="radioButton"><input name="name" type="radio" />黄曲条跳甲</label>
				<label class="radioButton"><input name="name" type="radio" />蓟马</label>
				<label class="radioButton"><input name="name" type="radio" />小菜蛾</label>
			</div>
			<input name="node_id" type="hidden"  value="1"/> 
			<div class="unit" width="220">
				<label>开始日期：</label>
					 <input class="easyui-datebox"  name="startDate"  data-options="sharedCalendar:'#cc'"/>
				<label>结束日期：</label>
					<input class="easyui-datebox"  name="endDate"  data-options="sharedCalendar:'#cc'"/>
			</div>


	         <ul>
			   <li><a onclick=";" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon_add'">开始检索</a>
			   </li>
			   <li><a onclick=";" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon_add'">生成图表</a>
			   </li>
			</ul>
		</div>
	</form>
	</div>
	</div>
	<div width="250"style="float:right">
	  <img src="/pests/img/yangtao.jpg" style="margin:5px 5px 5px 5px"  width="250" height="250"  alt="图片" />
	</div>
    <div class="clear" style="clear:both"></div> 
</div>

<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',fit:true,border:false" title="虫害列表">
		<table id="dataGrid" data-options="fit:true,border:false"></table>
	</div>
</div>

</div>
<script>
	var dataGrid;
	/* 默认显示所有的资源 */
	$(function() {
		dataGrid = $('#dataGrid').datagrid({
			url :  '/pests/pestData/pestSensordataGrid',
			striped : true,
			rownumbers : false,//是否显示行号
			pagination : true,
			singleSelect : false,//允许选择多行
			idField : 'id',
			sortName : 'id',
			sortOrder : 'asc',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
			frozenColumns : [ [ {
				width : '30',
				field : 'ck',
				checkbox : true
			},{
				width : '40',
				title : 'id',
				field : 'pestDataId',
				sortable : true
			},{
				width : '80',
				title : '虫害名称',
				field : 'pestName',
				sortable : true
			} ,{
				width : '70',
				title : '虫害数量',
				field : 'amount',
				sortable : true
			},{
				width : '75',
				title : '监测站id',
				field : 'moniNodeId',
				sortable : true
			},{
				width : '60',
				title : '空气温度',
				field : 'sensor1',
				sortable : true
			},{
				width : '60',
				title : '空气湿度',
				field : 'sensor2',
				sortable : true
			},{
				width : '60',
				title : '土壤温度',
				field : 'sensor3',
				sortable : true
			},{
				width : '60',
				title : '土壤湿度',
				field : 'sensor4',
				sortable : true
			},{
				width : '70',
				title : '叶表温度',
				field : 'sensor5',
				sortable : true
			},{
				width : '70',
				title : 'co2',
				field : 'sensor6',
				sortable : true
			}, {
				field : 'action',
				title : '操作',
				width : 120,
				formatter : function(value, row, index) {
					var str = '&nbsp;';
					if ($.canEdit) {
							str += $.formatString('<a href="javascript:void(0)" onclick="editFun(\'{0}\');" >编辑</a>', row.id);
						}
					str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
					if ($.canDelete) {
						str += $.formatString('<a href="javascript:void(0)" onclick="deleteFun({0});" >删除</a>', row.id);
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
					$.post('${ctx}/pestData/delete', {
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
			href : '${ctx}/pestData/detailPage?id=' + id
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
			href : '${ctx}/pestData/editPage?id=' + id,
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
			width : 600,
			height : 500,
			href : '${ctx}/pestData/addPage',
			buttons : [ {
				text : '添加',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#dataAddForm');
					f.submit();
				}
			} ]
		});
	}


	$.fn.datebox.defaults.formatter = function(date){
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	var d = date.getDate();
	return y+'/'+m+'/'+d;
}
</script>
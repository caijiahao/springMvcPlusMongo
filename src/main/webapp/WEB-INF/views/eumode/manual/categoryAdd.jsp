<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
	

	var categoryCodeRoot="${categoryRoot}";
	$(function() {
		$('#categoryAddForm').form({
			url : '${ctx}/manualCategory/add',
			
			onSubmit : function() {
				progressLoad();
				var isValid = $(this).form('validate');
				if (!isValid) {
					progressClose();
				}
				return isValid;
			},
			success : function(result) {
				progressClose();
				result = $.parseJSON(result);
				if (result.success) {
					parent.$.modalDialog.openner_dataGrid.treegrid('load',{categoryCode: categoryCodeRoot});//
					parent.$.modalDialog.handler.dialog('close');
				}else{
					parent.$.messager.alert('提示', result.msg, 'warning');
				}
			}
		});
		
		$('#pid').combotree({
			url : '${ctx}/manualCategory/getManualTreeByCode?categoryRoot='+categoryCodeRoot,
			parentField : 'pid',
			lines : true,
			panelHeight : 'auto',
			required: true
		   });
		
	});
</script>
<div style="padding: 3px;">
	<form id="categoryAddForm" method="post">
		<table class="grid">
			<tr>
				<td>栏目名称</td>
				<td><input name="categoryName" type="text" placeholder="请输入栏目名称" class="easyui-validatebox" data-options="required:true" style="width: 140px; height: 29px;" ></td>
				
			</tr>
			<tr>
				<td>栏目描述</td>
				<td><input name="categoryDescription" type="text" placeholder="请输入栏目描述" class="easyui-validatebox" data-options="required:true" style="width: 250px; height: 29px;" ></td>
				
			</tr>
			<tr>
				<td>上级栏目</td>
				<td><select id="pid" name="parentID"  data-options="width:140,height:29,editable:false,panelHeight:'auto'"></select>
				</td>
			</tr>
			
		</table>
	</form>
</div>
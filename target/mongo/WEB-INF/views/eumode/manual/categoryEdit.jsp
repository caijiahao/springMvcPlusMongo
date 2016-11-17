<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
	
    var categoryCodeRoot="${categoryRoot}";
	$(function() {
		
		$('#pid').combotree({
			url : '${ctx}/manualCategory/getManualTreeByCode?categoryRoot='+categoryCodeRoot,
			parentField : 'pid',
			lines : true,
			panelHeight : 'auto',
			required: true,
			value:'${category.parentID}'
		});
		
		$('#categoryEditForm').form({
			url : '${ctx}/manualCategory/edit',
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
					parent.$.modalDialog.openner_dataGrid.treegrid('load',{categoryCode: categoryCodeRoot});
					parent.$.modalDialog.handler.dialog('close');
				}
			}
		});
		
	});
</script>
<div style="padding: 3px;">
	<form id="categoryEditForm" method="post">
		<table class="grid">
			<tr>
				<input type="hidden" name="autoID" value="${category.autoID}"/>
				<input name="categoryCode" type="hidden" value="${category.categoryCode}" / >
				<td>名称</td>
				<td><input name="categoryName" type="text" value="${category.categoryName}" placeholder="请输入字典名称" class="easyui-validatebox" data-options="required:true" style="width: 140px; height: 29px;" ></td>
			</tr>
			<tr>
				<td>所属类别</td>
				<td><select id="pid" name="parentID" style="width: 140px; height: 29px;" ></select></td>
				
			</tr>
			     <td>备注</td>
			 	<td><input name="categoryDescription" type="text" value="${category.categoryDescription}" placeholder="请输入字典名称" class="easyui-validatebox" data-options="required:true" style="width: 140px; height: 29px;" ></td>
			<tr>
			</tr>
		</table>
	</form>
</div>

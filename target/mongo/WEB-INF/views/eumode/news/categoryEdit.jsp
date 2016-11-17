<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript">
	$(function() {
		$('#editCategory').bind('click',function(){
			$('#categoryEditForm').submit();
		});
		$('#categoryEditForm').form({
			url : '${ctx}/category/edit',
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
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');//
					parent.$.modalDialog.handler.dialog('close');
				} else {
					parent.$.messager.alert('提示', result.msg, 'warning');
				}
			}
		});

	});
</script>
<div style="padding: 3px;">
	<form id="categoryEditForm" method="post">
		<table class="grid">
			<tr>
				<td>类型</td>
				<td><select class="easyui-combobox" name="type"
					style="width: 200px">
						<c:if test="${!empty typeList}">
								<c:forEach items="${typeList}" var="tl">
									<c:if test="${category.type == tl.id}"><option value="${tl.id}" selected="selected">${tl.text}</option></c:if>
									<c:if test="${category.type != tl.id}"><option value="${tl.id}">${tl.text}</option></c:if>
								</c:forEach>
						</c:if>
				</select></td>
			</tr>
			<tr>
				<td>栏目名称</td>
				<td><input name="categoryName" type="text"
					placeholder="请输入栏目名称" class="easyui-validatebox"
					data-options="required:true" style="width: 140px; height: 29px;" value="${category.categoryName}"></td>

			</tr>
			<tr>
				<td>栏目描述</td>
				<td><input name="categoryDescription" type="text"
					placeholder="请输入栏目描述" class="easyui-validatebox"
					data-options="required:true,multiline:true"
					style="width: 300px; height: 100px;" value="${category.categoryDescription}"></td>
			</tr>
		</table>
		<input type="hidden" name="autoID" value="${category.autoID}" />
	</form>
</div>
<div id="toolbar">
	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon_sys'" id="editCategory">修改</a>
</div>
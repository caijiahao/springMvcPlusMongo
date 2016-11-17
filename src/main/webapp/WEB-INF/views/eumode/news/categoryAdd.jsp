<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
	

	$(function() {
		$('#addCategory').bind('click',function(){
			$('#categoryAddForm').submit();
		});
		
		$('#categoryAddForm').form({
			url : '${ctx}/category/add',
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
				}else{
					parent.$.messager.alert('提示', result.msg, 'warning');
				}
			}
		});
		
	});
</script>
<div style="padding: 3px;">
	<form id="categoryAddForm" method="post">
		<table class="grid">
			<tr>
				<td>类型</td>
				<td>
					<select class="easyui-combobox" name="type" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
						<c:if test="${!empty typeList}">
								<c:forEach items="${typeList}" var="tl">
									<option value="${tl.id}">${tl.text}</option>
								</c:forEach>
						</c:if>
					</select>
				</td>
			</tr>
			<tr>
				<td>栏目名称</td>
				<td><input name="categoryName" type="text" placeholder="请输入栏目名称" class="easyui-validatebox" data-options="required:true" style="width: 140px; height: 29px;" ></td>
				
			</tr>
			<tr>
				<td>栏目描述</td>
				<td><input name="categoryDescription" type="text" placeholder="请输入栏目描述" class="easyui-validatebox" data-options="required:true,multiline:true" style="width: 300px; height: 100px;" ></td>
			</tr>
		</table>
	</form>
</div>
<div id="toolbar">
	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon_add'" id="addCategory">添加</a>
</div>
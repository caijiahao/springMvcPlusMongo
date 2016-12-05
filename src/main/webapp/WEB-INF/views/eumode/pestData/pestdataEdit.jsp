<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
	

	$(function() {
		
		$('#pid').combotree({
			url : '${ctx}/dictionarytype/tree',
			parentField : 'pid',
			lines : true,
			panelHeight : 'auto',
			value:'${dictionary.dictionarytypeId}'
		});
		
		$('#dictionaryEditForm').form({
			url : '${pageContext.request.contextPath}/dictionary/edit',
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
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');
					parent.$.modalDialog.handler.dialog('close');
				}
			}
		});
		
	});
</script>
<div style="padding: 3px;">
	<form id="dictionaryEditForm" method="post">
		<table class="grid">
			<tr>
				<td>编码</td>
				<td><input type="hidden" name="id" value="${dictionary.id}"/>
				<input name="code" type="text" value="${dictionary.code}" placeholder="请输入字典编码" class="easyui-validatebox" data-options="required:true" style="width: 140px; height: 29px;" ></td>
				<td>名称</td>
				<td><input name="text" type="text" value="${dictionary.text}" placeholder="请输入字典名称" class="easyui-validatebox" data-options="required:true" style="width: 140px; height: 29px;" ></td>
			</tr>
			<tr>
				<td>所属类别</td>
				<td><select id="pid" name="dictionarytypeId" style="width: 140px; height: 29px;" ></select></td>
				<td>排序</td>
				<td><input name="seq" value="${dictionary.seq}"  class="easyui-numberspinner" style="width: 140px; height: 29px;" required="required" data-options="editable:false"></td>
			</tr>
			<tr>
				<td>状态</td>
				<td><select name="state" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
						<c:forEach items="${stateList}" var="stateList">
							<option value="${stateList.key}" <c:if test="${stateList.key == dictionary.state}">selected="selected"</c:if>>${stateList.value}</option>
						</c:forEach>
					</select></td>
			</tr>
		</table>
	</form>
</div>

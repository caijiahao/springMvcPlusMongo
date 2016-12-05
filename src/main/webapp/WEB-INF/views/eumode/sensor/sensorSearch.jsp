<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
	

	$(function() {
		$('#dataSearchForm').form({
			url : '${ctx}/sensor/search',
			
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
	
	$.fn.datebox.defaults.formatter = function(date){
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	var d = date.getDate();
	return y+'/'+m+'/'+d;
}


</script>
<div style="padding: 3px;">
	<form id="dataSearchForm" method="post">
		<table class="grid">
		       <tr>	<label>请选择检索条件：</label></tr>
				<tr>
					<td><label>监测点ID：         </label> <input name="nodeId" type="text" class="required" /></td>	
				</tr>
	 
				<tr>
				   	<td><label>开始日期：</label> 
						 <input class="easyui-datebox"  name="createDate"  data-options="sharedCalendar:'#cc'">
					</td>	
					<td><label>结束日期：</label> 
						 <input class="easyui-datebox"  name="createDate"  data-options="sharedCalendar:'#cc'">
					</td>	

				</tr>
				<tr><a onclick=";" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon_add'">生成图表</a></tr>
		</table>
	</form>
</div>
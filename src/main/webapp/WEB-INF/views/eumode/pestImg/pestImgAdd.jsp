<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
	

	$(function() {
		$('#dataAddForm').form({
			url : '${ctx}/pestData/add',
			
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
	<form id="categoryAddForm" method="post">
		<table class="grid">
				<tr>
					<td><label>监测点ID：         </label> <input name="nodeId" type="text" class="required" /></td>
					<td><label>数据获取日期：</label> 
						 <input class="easyui-datebox"  name="createDate"  data-options="sharedCalendar:'#cc'">
					</td>		
					
				</tr>
	 
				<tr>
				    <td><label>图像上传： </label> <input name="dictionaryId" type="text"  class="required"/>
					 

				</tr>
				
				<tr>
					<td><label>备注：      </label> <input name="remark" type="text"  class="required"/></td>
				</tr>
		</table>
	</form>
</div>
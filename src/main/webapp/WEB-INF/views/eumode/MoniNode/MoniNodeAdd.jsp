<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
	

	$(function() {
		$('#moniNodeAddForm').form({
			url : '${ctx}/pestMoniNode/add',
			
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
	<form id="moniNodeAddForm" method="post">
		<table class="grid">
				<tr>
					<td><label>监测点名称：</label> <input name="name" type="text"  class="required" value=""/></td>
					<td><label>编码：</label> 
						
                        <input name="code" type="text"  class="required" value=""/>					
					</td>
				</tr>		
				<tr>
					<td><label>创建用户：</label> <input name="createBy" type="text"  class="required" value=""/></td>
					<td><label>类型：</label> <input name="type" type="text" class="required"  value=""/></td>
				</tr>
				<tr>
					<td><label>坐标 x：</label> <input name="locationX" type="text"  class="required" value=""/></td>
					<td><label>坐标 y：</label> <input name="locationY" type="text" class="required"  value=""/></td>
				</tr>
				<tr>
					<td><label>站点id：</label> <input name="station" type="text"  class="required" value=""/></td>
					<td><label>地址：</label> <input name="address" type="text" class="required"  value=""/></td>
				</tr>
				<tr>
					<td><label>截取间隔时间：</label> <input name="fps" type="text"  class="required" value=""/></td>
					<td><label>详细描述：</label> <input name="description" type="text" class="required"  value=""/></td>
				</tr>		
		</table>
	</form>
</div>
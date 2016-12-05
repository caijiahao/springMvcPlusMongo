<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
	

	$(function() {
		
		
		$('#dataEditForm').form({
			url : '${pageContext.request.contextPath}/pestMoniNode/edit',
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
					 <td>
						<label>节点ID：</label> 
						<input name="id" readonly="readonly" type="text" class="required" value="${enode.id}" />
					</td>
					<td>
						<label>节点名称：</label> 
						<input name="nodename" type="text"  class="required" value="${enode.nodename}" />
					</td>
					
				</tr>
				
				<tr>
				
				    <td>
						<label>是否使用：</label> 
						<input name="active"  type="text" class="required"  value="${enode.active}" />
					</td>
					<td>
						<label>创建日期：</label> 
						<input name="createDate"  type="text" class="date"  dateFmt="yyyy/MM/dd" value="${enode.createDate}" />
				        <a class="inputDateButton" href="javascript:;">选择</a>	
					</td>
				
				</tr>
				
				<tr>
					<td>
						<label>创建用户：</label> 
						<input name="createBy"  type="text" class="required" value="${enode.createBy}" />
					</td>
					
					<td>
						<label>更新日期：</label> 
						<input name="updateDate"   type="text" class="date"  dateFmt="yyyy/MM/dd" value="${enode.updateDate}" />
				        <a class="inputDateButton" href="javascript:;">选择</a>	
					</td>
					
				</tr>
				
				
				
				<tr>
					<td>
						<label>坐标 x：</label> 
						<input name="location_x"  type="text" class="required" value="${enode.location_x}" />
					</td>
					
					<td>
						<label>坐标 y：</label> 
						<input name="location_y"   type="text" class="required" value="${enode.location_y}" />
					</td>
					
				</tr>
				
				
				
				<tr>
					<td>
						<label>截取间隔时间（s）：</label> 
						<input name="fps"  type="text" class="required" value="${enode.fps}" />
					</td>
					
					<td>
						<label>详细描述：</label> 
						<input name="description"   type="text" class="required" value="${enode.description}" />
					</td>
					
					
				</tr>
		</table>
	</form>
</div>

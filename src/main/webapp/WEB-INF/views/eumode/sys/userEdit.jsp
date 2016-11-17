<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
	$(function() {
	
		$('#organizationId').combotree({
			url : '${ctx}/organization/tree',
			parentField : 'pid',
			lines : true,
			panelHeight : 'auto',
			value : '${user.organizationId}'
		});
		
		$('#roleIds').combotree({
			url : '${ctx}/role/tree',
			parentField : 'pid',
			lines : true,
			panelHeight : 'auto',
			multiple : true,
			required: true,
			cascadeCheck : false,
			value : $.stringToList('${user.roleIds}')
		});
		
		$('#userEditForm').form({
			url : '${ctx}/user/edit',
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
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
					parent.$.modalDialog.handler.dialog('close');
				} else {
					parent.$.messager.alert('错误', result.msg, 'error');
				}
			}
		});
		
		pubMethod.bind('usertype', 'usertype');
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
		<form id="userEditForm" method="post">
			<div class="light-info" style="overflow: hidden;padding: 3px;">
				<div>密码不修改请留空。</div>
			</div>
			<table class="grid">
				<tr>
					<td>登录名</td>
					<td><input name="autoID" type="hidden"  value="${user.autoID}">
					<input name="loginName" type="text" placeholder="请输入登录名称" class="easyui-validatebox" data-options="required:true" value="${user.loginName}"></td>
					<td>姓名</td>
					<td><input name="realName" type="text" placeholder="请输入姓名" class="easyui-validatebox" data-options="required:true" value="${user.realName}"></td>
				</tr>
				<tr>
					<td>密码</td>
					<td><input type="text" name="password"/></td>
					<td>性别</td>
					<td><select name="sex" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
						<c:forEach items="${sexList}" var="sexList">
							<option value="${sexList.key}" <c:if test="${sexList.key == user.sex}">selected="selected"</c:if>>${sexList.value}</option>
						</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td>年龄</td>
					<td><input type="text" name="age" value="${user.age}"/></td>
					<td>专长</td>
					<td><input id="techType" name="techType" value="${user.techType}" style="width: 140px; height: 29px;" /></td>
				</tr>
				<tr>
					<td>职称</td>
					<td><input type="text" name="techTitle" value="${user.techTitle}"/></td>
					<td>是否展示</td>
					<td><select name="needPublish" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
						<c:forEach items="${needPublishList}" var="needPublishList">
							<option value="${needPublishList.key}" <c:if test="${needPublishList.key == user.needPublish}">selected="selected"</c:if>>${needPublishList.value}</option>
						</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td>部门</td>
					<td><select id="organizationId" name="organizationId" style="width: 140px; height: 29px;" class="easyui-validatebox" data-options="required:true"></select></td>
					<td>角色</td>
					<td><input  id="roleIds" name="roleIds" style="width: 140px; height: 29px;"/></td>
				</tr>
				<tr>
					<td>联系电话</td>
					<td><input type="text" name="phoneNumber" class="easyui-validatebox" data-options="required:true" value="${user.phoneNumber}"/></td>
				</tr>
				<tr>
					<td>专家描述</td>
					<td colspan="3"><textarea name="description" rows="5" cols="50" value="${user.description}" ></textarea></td>
				</tr>
			</table>
		</form>
	</div>
</div>
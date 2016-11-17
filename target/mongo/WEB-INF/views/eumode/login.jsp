<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="inc.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="edge" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
<script>

	var sessionInfo_userId = '${sessionInfo.id}';
	if (sessionInfo_userId) {//如果登录,直接跳转到index页面
		window.location.href='${ctx}/admin/index';
	}
		
	$(function() {
		
		$('#loginform').form({
		    url:'${ctx}/admin/login',
		    onSubmit : function() {
		    	progressLoad();
				var isValid = $(this).form('validate');
				if(!isValid){
					progressClose();
				}
				return isValid;
			},
		    success:function(result){
		    	result = $.parseJSON(result);
		    	progressClose();
		    	if (result.success) {
		    		window.location.href='${ctx}/admin/index';
		    	}else{
		    		$.messager.show({
		    			title:'提示',
		    			msg:'<div class="light-info"><div class="light-tip icon-tip"></div><div>'+result.msg+'</div></div>',
		    			showType:'show'
		    		});
		    	}
		    }
		});
	});
	function submitForm(){
		$('#loginform').submit();
	}
	
	function clearForm(){
		$('#loginform').form('clear');
	}
</script>
</head>
<body>
	<div align="center" style="padding:160px 0 0 0">
	<div class="easyui-panel" title="登录" style="width:400px;" >
		<div style="padding:10px 0 10px 100px" >
	    <form id="loginform"  method="post">
	    	<table>
	    		<tr>
	    			<td>用户名:</td>
	    			<td><input class="easyui-validatebox" type="text" name="loginName" data-options="required:true" value="admin"></input></td>
	    		</tr>
	    		<tr>
	    			<td>密&nbsp;&nbsp;码:</td>
	    			<td><input class="easyui-validatebox" type="password" name="password" value="123456"></input></td>
	    		</tr>
	    	</table>
	    </form>
	    </div>
	    <div style="text-align:center;padding:5px">
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">登录</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">清除</a>
		</div>
	</div>
	</div>
	</body>
</html>
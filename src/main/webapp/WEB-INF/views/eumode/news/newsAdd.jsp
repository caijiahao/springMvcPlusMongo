<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link href="${ctx}/jslib/Uploadify/uploadify.css" rel="stylesheet"
	type="text/css" />
<div style="padding: 3px;">
	<form id="newsAddForm" method="post">
		<div class="table">
			<table class="grid">
				<tr>
					<td>新闻标题</td>
					<td><input name="title" type="text" placeholder="请输入标题"
						class="easyui-validatebox" data-options="required:true"
						style="width: 80%; height: 29px;">
					</td>
				</tr>
				<tr>
					<td>所属栏目</td>
					<td><select id="categoryID" name="categoryID"
						class="easyui-combobox"
						data-options="width:140,height:29,editable:false,panelHeight:'auto'">
							<c:if test="${!empty categories}">
								<c:forEach items="${categories}" var="category">
									<option value="${category.autoID}">${category.categoryName}</option>
								</c:forEach>
							</c:if>
					</select></td>
				</tr>
			</table>
		</div>
		<div>
			<textarea id="content" rows="35" cols="150"
				class="easyui-validatebox" data-options="required:true"
				style="width: 100%;"></textarea>
		</div>
		<c:if test="${!empty allowAttachment}">
			<div id="file_upload_area" class="table">
				<table class="grid">
					<tr>
						<td style="width:200px;">上传附件</td>
						<td>
							<div id="file_upload"></div></td>
					</tr>
					<tr>
						<td></td>
						<td><div id="uploadfileQueue"></div>
						</td>
					</tr>
				</table>
			</div>
		</c:if>
		<input type="hidden" name="pageContent" value="" /> <input
			type="hidden" name="attachmentContent" value="" />
	</form>
</div>
<script src="${ctx}/jslib/Uploadify/swfobject.js" type="text/javascript"></script>
<script src="${ctx}/jslib/Uploadify/jquery.uploadify.min.js"
	type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		//var editor = $('#content').xheditor();
		var editor = $('#content').xheditor({
			cleanPaste : 0,
			height : '300px',
			upBtnText : '选择',
			upImgUrl : '${ctx}/news/getUploadFile'
		});
		var waitupload = false;
		$('#newsAddForm')
				.form(
						{
							url : '${ctx}/news/add',
							onSubmit : function() {
								progressLoad();
								var uploadCount = $('#uploadfileQueue').find(
										'.uploadify-queue-item').length; //上传附件的个数
								
								var isValid = $(this).form('validate');
								if($("#categoryID").combobox('getValue') == undefined || $("#categoryID").combobox('getValue') == ""){
									alert("请先选择栏目！");
									isValid = false;
								}
								
								if (!isValid) {
									progressClose();
								} else if ($("#file_upload_area").is(":hidden") == false
										&& waitupload == false
										&& uploadCount > 0) {
									//有附件需要上传
									waitupload = true;
									allFileUploadSuccess = true;
									$('#file_upload').uploadify("upload", "*");
									return false;
								}
								$('input[name=pageContent]').val(
										encodeURIComponent(editor.getSource()));
								return isValid;
							},
							success : function(result) {
								progressClose();
								result = $.parseJSON(result);
								if (result.success) {
									parent.$.modalDialog.openner_dataGrid
											.datagrid('reload');//
									parent.$.modalDialog.handler
											.dialog('close');
								} else {
									parent.$.messager.alert('提示', result.msg,
											'warning');
								}
							}
						});

		fnInituploadify();
	});

	var allFileUploadSuccess = true;
	//初始化上传控件
	function fnInituploadify() {
		$("#file_upload")
				.uploadify(
						{
							//开启调试  
							'debug' : false,
							//是否自动上传  
							'auto' : false,
							'buttonText' : '选择附件',
							//flash  
							'swf' : "${ctx}/jslib/Uploadify/uploadify.swf",
							//文件选择后的容器ID  
							'queueID' : 'uploadfileQueue',
							'uploader' : '${ctx}/news/getUploadFile',
							'width' : '120',
							'height' : '29',
							'multi' : true,
							'fileTypeDesc' : '支持格式',
							'fileTypeExts' : '*.doc;*.docx;*.xls;*.xlsx;*.pdf;*.jpg;*.png;*.bmp;*.jpeg;',
							'fileSizeLimit' : '5MB',
							'removeTimeout' : 3,
							'successTimeout' : 60,
							'removeCompleted' : false,
							'fileObjName' : 'Filedata',
							'formData' : {},
							'cancelImg' : "${ctx}/jslib/Uploadify/uploadify-cancel.png",
							//返回一个错误，选择文件的时候触发  
							'onSelectError' : function(file, errorCode,
									errorMsg) {
								switch (errorCode) {
								case -100:
									alert("上传的文件数量已经超出系统限制的"
											+ $('#file_upload').uploadify(
													'settings',
													'queueSizeLimit') + "个文件！");
									break;
								case -110:
									alert("文件 ["
											+ file.name
											+ "] 大小超出系统限制的"
											+ $('#file_upload')
													.uploadify('settings',
															'fileSizeLimit')
											+ "大小！");
									break;
								case -120:
									alert("文件 [" + file.name + "] 大小异常！");
									break;
								case -130:
									alert("文件 [" + file.name + "] 类型不正确！");
									break;
								}
							},
							//检测FLASH失败调用  
							'onFallback' : function() {
								waitupload = false;
								progressClose();
								alert("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试。");
							},
							//上传到服务器，服务器返回相应信息到data里  
							'onUploadSuccess' : function(file, data, response) {
								if (response == true) {
									var dataobj = JSON.parse(data);
									if (dataobj.err != "") {
										allFileUploadSuccess = false;
										//alert(dataobj.err);
									}
									if (dataobj.msg != "") {
										$('input[name=attachmentContent]')
												.val(
														$(
																'input[name=attachmentContent]')
																.val()
																+ dataobj.msg);
									}
								} else {
									allFileUploadSuccess = false;
								}
							},
							'onQueueComplete' : function(queueData) {
								if (allFileUploadSuccess == false) {
									waitupload = false;
									progressClose();
									alert("有附件上传不成功");
								} else {
									$('#newsAddForm').submit();
								}
							}
						});
	}
</script>
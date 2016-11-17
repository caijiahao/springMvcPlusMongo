<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<jsp:include page="../inc.jsp"></jsp:include>

<link href="${ctx}/jslib/Uploadify/uploadify.css" rel="stylesheet" type="text/css" />

<div style="padding: 3px;">
	<form id="manualAddForm" method="post">
	<div class="table">
		<table class="grid">
			<tr>
				<td>题目</td>
				<td><input name="title" type="text" placeholder="请输入题目" class="easyui-validatebox" data-options="required:true" style="width: 80%; height: 29px;" ></td>
			</tr>
			
			<tr>
				<td>所属类别</td>
				<td><select id="manualCategoryID" name="manualCategoryID" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
				</select></td>
			</tr>
			<!-- 
			<tr>
				<td>发布时间</td>
					<td><input name="createTime" type="text"  class="easyui-validatebox" readonly="readonly" value=" <%=new java.util.Date().toLocaleString( )%>" data-options="required:true" style="width: 80%; height: 29px;" >
				</td>
			</tr>
			<tr>
				<td>发布人工号</td>
				<td>
					<input name="createBy" type="text"  class="easyui-validatebox" readonly="readonly" value="${sessionInfo.id}"  style=" height: 29px;" >
				</td>
			</tr>
			 -->
			 <tr>
			<td>添加关键字</td>
			<td>
				<input id="keyword" name="keyword" type="text" placeholder="请输入关键词" class="easyui-validatebox" style="width: 50%; height: 29px;" />
				 <a onclick="addKeyword();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon_add'">确定</a>
			</td>
			  
		
			</tr> 
            <tr>
			    <input id="keywordList" name="keywordList" type="hidden" />
				<td>关键字列表</td>
				<td>		
					<table id="keywordListSpan" class="grid">
					</table>
				</td>
            </tr>		
			
			 <tr>
			<td>发布形式</td>
			<td>
				<button onclick="showUploadArea();">使用附件</button> <button onclick="showXContentArea();">手工输入</button>
			</td>
			</tr>  	
            
			
		
		</table>


	</div>
	<div id="contentArea">
	 <textarea id="content" name="content" rows="35" cols="150"
				class="easyui-validatebox" style="width: 100%;"></textarea>
	</div>
	<div id="file_upload_area" class="table"  style="display:none;">
			<table id="file_upload_table" class="grid" >
				<tr>
					<td style="width:200px;">上传附件</td>
					<td>
						<div id="file_upload">
                    	</div>
					</td>
				</tr>
				<tr>
					<td></td>
					<td><div id="uploadfileQueue"></div></td>
				</tr>
			</table>
	</div>
	<input type="hidden" name="pageContent" value=""/>
	<input type="hidden" name="attachmentContent" value=""/>
	</form>
</div>
	<div id="toolbar">	    
			<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon_add'">发布</a>

	</div>
	<script src="${ctx}/jslib/Uploadify/swfobject.js" type="text/javascript"></script>
<script src="${ctx}/jslib/Uploadify/jquery.uploadify.min.js" type="text/javascript"></script>
	<script type="text/javascript">
	

	var categoryCodeRoot="${categoryRoot}";
	$(function() {
		
		var editor = $('#content').xheditor({
			cleanPaste : 2,
			height : '300px',
			upBtnText : '选择',
			upImgUrl : '${ctx}/manualContent/getUploadFile'
		});
		
		var waitupload = false;
		$('#manualAddForm').form({
			url : '${ctx}/manualContent/add',
			onSubmit : function() {
				
				progressLoad();
				var uploadCount = $('#uploadfileQueue').find('.uploadify-queue-item').length; //上传附件的个数
				var isValid = $(this).form('validate');
				if(uploadCount==0&&$('#content').val()==""){
					isValid=false;
				}
				if (!isValid) {
					progressClose();
				}
				else if($("#file_upload_area").is(":hidden") == false && waitupload == false && uploadCount > 0){
					//有附件需要上传时，则先让附件上传完毕再保存页面内容
					waitupload = true;
					allFileUploadSuccess = true;
					$('#file_upload').uploadify("upload","*");
					
					return false;
				}
				
				$('input[name=pageContent]').val(encodeURIComponent(editor.getSource()));
				return isValid;
			},
			success : function(result) {
				progressClose();
				result = $.parseJSON(result);
				if (result.success) {
					parent.$.messager.alert('提示', result.msg, 'warning');
					window.location.href = window.location.href; 
				}else{
					parent.$.messager.alert('提示', result.msg, 'warning');
				}
			}
		});
		fnInituploadify();
		
		$('#manualCategoryID').combotree({
			url : '${ctx}/manualCategory/getManualTreeByCode?categoryRoot='+categoryCodeRoot,
			parentField : 'pid',
			lines : true,
			panelHeight : 'auto'
		   });
		
	});
	
		function addFun() {
			getKeywordString();
			$('#manualAddForm').submit();				
	}
	
	function addKeyword()
	{
		var keyword= $('#keyword').val();
		if(keyword==''||keyword==null){
			alert("关键词不能为空");
			return;
		}
		else{
			var htmlitem='<tr><td>关键字：</td><td name="keywordA">'+keyword+'</td><td><a  onclick="fndeleteKeyword(this);" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'+"'icon_del'"+'">'+"删除"+"</a></td></tr>";
			$('#keywordListSpan').append(htmlitem);
			$('#keyword').val('');
			$('#keyword').focus();
		}
	}
	
	function getKeywordString()
	{
		var keywords = '';
			$('td[name=keywordA]').each(function(){
				keywords += $(this).text() + ",";
			});
		if(keywords.length > 0){
				keywords = keywords.substr(0, keywords.length - 1);
			}
			$('input[name=keywordList]').val(keywords);
	}
	
	function fndeleteKeyword(itm)
	{
		$(itm).parent("td").parent("tr").remove();
	}
	
	function showUploadArea(){
		
		$('#contentArea').hide();
		$('#content').val("");
	
		$('#file_upload_area').show();
	}
	
	function showXContentArea(){
		$('#file_upload_area').hide();
		$('#contentArea').show();
		//$('#uploadfileQueue').empty();
		//$('.uploadify-queue-item').empty();
		$("#file_upload").uploadify('cancel','*');
	}
	
	
	 var allFileUploadSuccess = true;
    //初始化上传控件
    function fnInituploadify() {
        $("#file_upload").uploadify({
            //开启调试  
            'debug': false,
            //是否自动上传  
            'auto': false,
            'buttonText': '选择附件',
            //flash  
            'swf': "${ctx}/jslib/Uploadify/uploadify.swf",
            //文件选择后的容器ID  
            'queueID': 'uploadfileQueue',
            'uploader': '${ctx}/manualContent/getUploadFile',
            'width': '120',
            'height': '29',
            'multi': false,
            'fileTypeDesc' : '支持格式',
			'fileTypeExts' : '*.pdf;*.jpg;*.png;*.bmp;*.jpeg;',
            'fileSizeLimit': '10MB',
            'removeTimeout': 3,
            'successTimeout': 60,
            'removeCompleted': false,
            'fileObjName': 'Filedata',
            'formData': {  },
			'cancelImg': "${ctx}/jslib/Uploadify/uploadify-cancel.png",
            //返回一个错误，选择文件的时候触发  
            'onSelectError': function (file, errorCode, errorMsg) {
                switch (errorCode) {
                    case -100:
                        alert("上传的文件数量已经超出系统限制的" + $('#file_upload').uploadify('settings', 'queueSizeLimit') + "个文件！");
                        break;
                    case -110:
                        alert("文件 [" + file.name + "] 大小超出系统限制的" + $('#file_upload').uploadify('settings', 'fileSizeLimit') + "大小！");
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
            'onFallback': function () {
            	waitupload = false;
            	progressClose();
                alert("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试。");
            },
            //上传到服务器，服务器返回相应信息到data里  
            'onUploadSuccess': function (file, data, response) {
				
				
                if (response == true) {
                	var dataobj = JSON.parse(data);
                    if(dataobj.err != ""){
                    	allFileUploadSuccess = false;
                    	//alert(dataobj.err);
                    }
					
                    if(dataobj.msg != ""){
                    	$('input[name=attachmentContent]').val($('input[name=attachmentContent]').val() + dataobj.msg);
                    }
                }
                else {
                	allFileUploadSuccess = false;
                }
            },
            'onQueueComplete': function (queueData) {
            	if(allFileUploadSuccess == false){
            		waitupload = false;
                    progressClose();
                    alert("有附件上传不成功");
            	}
            	else{
			
            		$('#manualAddForm').submit();
            	}
            }
        });
     }
</script>

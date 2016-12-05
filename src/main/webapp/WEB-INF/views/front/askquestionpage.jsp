<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <jsp:include page="../inc.jsp"></jsp:include>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>农技推广综合管理平台</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="IE=9" >
	<link rel="shortcut icon" href="${ctx}/style/images/ico.ico" type="image/x-icon" />
	<link href="${ctx}/style/index/themepark.css" rel="stylesheet" media="screen">
	<link href="${ctx}/style/index/default.css" rel="stylesheet" media="screen">
	<link href="${ctx}/style/index/detailinputunit.css" rel="stylesheet" media="screen">
	<link href="${ctx}/style/index/countryside.css" rel="stylesheet" media="screen">
	<link href="${ctx}/jslib/xheditor/xheditor_skin/nostyle/ui.css" rel="stylesheet" media="screen">
	
	<link href="${ctx}/jslib/Uploadify/uploadify.css" rel="stylesheet" type="text/css" />

 	<style>
 		.question_toolbar{position: relative; width: 100%; box-shadow: 0 1px 0 rgba(255,255,255,0.6);}
		.question_gradient{position: relative; margin-right: 100px; height: 30px; border: 1px solid #ccc; border-right: none; border-bottom-color: #aaa; border-bottom-left-radius: 3px; -webkit-border-bottom-left-radius: 3px;}
		.question_button{outline:none;cursor: pointer; font-family: "Helvetica Neue",Helvetica,Arial,sans-serif; position: absolute; right: 0; top: 0; height: 32px; width: 100px; text-align: center; text-shadow: 0 1px 0 #fff; color: #555; font-size: 18px; font-weight: bold; border: 1px solid #ccc; border-bottom-color: #aaa; border-bottom-right-radius: 3px;  -webkit-border-bottom-right-radius: 3px; background-color: #e6e6e6; background-repeat: no-repeat; background-image: linear-gradient(#fcfcfc, #fcfcfc 25%, #e6e6e6); transition: all .15s linear; box-shadow: inset 0 0 1px #fff;}
		.question_title{border: 1px solid #045bb2; border-radius: 4px; box-shadow: inset 0 1px 3px rgba(0,0,0,.2),0 1px 0 rgba(255,255,255,.1); outline: 0;padding: 5px; width: 300px; font-size: 15px;}
 	</style>
	<script>
		$(function() {
			$('#pid').combotree({
			url : '${ctx}/front/web/communicate/getAllManualTree',
			parentField : 'pid',
			lines : true,
			panelHeight : 'auto',
			onSelect:function(node){ 
		    var id= node.id;
			getExpert(id);
            }
		   });
		  });
		 /*
		 $('#pid').change(function(){ 
		    alert("hello");
		    var id= $('#pid').val();
			getExpert(id);
		 });
		 */
		 
		
		
		function getExpert(id)
       {
		   
		$.ajax({
		url:'${ctx}/front/web/manual/base/getExpertByCategory',
		data:{'id':id},
		cache:true,
		dataType:'json',
		success:function(data){
			$("#select_expert").empty();
				for(var e in data.obj.list)
				{
				var str = '';
				 str += $.formatString('<a rel="{0}">{1}</a>',data.obj.list[e].autoID,data.obj.list[e].realName);
				 $('#select_expert').append(function(){return str;} );
				}
				$(".tag_pro #select_expert a").click(function(){
					$(this).addClass("selecttag");
					$(this).nextAll("a").removeClass("selecttag");
					$(this).prevAll("a").removeClass("selecttag");
					var rel2=$(this).attr("rel");
					$("#expertPersonalID").val(function(){return rel2});	
				});				

				
				}
				});
    }
	</script>
	


</head>
<body class="page page-id-2 page-parent page-template page-template-aboutus page-template-aboutus-php">

  <!--start 登录注册浮动栏 -->
    <jsp:include page="./wpthemedemobar.jsp"></jsp:include>
 <!-- end 登录注册浮动栏 -->

   <!-- start:头部导航 -->
  <jsp:include page="./header.jsp"></jsp:include>
  <!-- end:头部导航-->

<!--head:所在位置-->
<div id="page_muen_nav">  <b>您现在所在的位置：</b><a href="${ctx}/front/web/base/index">首页</a> <a> &gt; </a><a href="${ctx}/front/web/base/getQuestionList"> 在线交流</a><a> &gt; </a><a href="#"> 提问</a></div>
<!--end:所在位置-->

<!--head:内容项目-->
<div id="content">

<!--head:左侧内容-->
<div class="left_mian"> 
<!--head:扁平图标导航-->
 <jsp:include page="./navigation.jsp"></jsp:include>
<!--head:扁平图标导航-->

</div>
<!--end:左侧内容-->


<!--head:右侧内容-->
<div class="right_mian">

<div class="widget" id="widget_question">
	<div class="question_unit">
		<div class="question_detail">
		    <form id="questionform" method="post">
			<div class="tag_pro question_detail_div">
		 		<span style="font-size:20px; float:left;line-height:25px; margin-right: 10px; width: 50px;font-weight: bold;">问题分类</span>

		 		<div id="select_category">
                    <select id="pid" name="manualCategoryID" style="width: 200px; height: 29px;"></select>
				</div>
			</div>
			<div class="tag_pro question_detail_div">
		 		<span style="font-size:20px; float:left;line-height:25px; margin-right: 10px; width: 50px;font-weight: bold;">指定专家</span>

		 		<div id="select_expert">
				</div>
				<input type="hidden" name="expertPersonalID" id="expertPersonalID" value=""></input>
			</div>
			
			<div class="question_detail_div">
				<span style="font-size:20px; float:left;line-height:25px; margin-right: 10px; width: 50px;font-weight: bold;">标题</span>
				<input class="question_title" id="question_title" type="text" name="title" placeholder="请写下你的问题..." />
			</div>
			

			
			<div class="question_detail_div">
				<p>请对问题进行补充或者上传问题中涉及的图片 </p>
				<textarea name="content" id="question_content"  class="xheditor-mini {skin:'nostyle',layerShadow:0,upBtnText:'本地图片',upImgUrl:'upload.asp'}" style="width: 100%; height: 150px;" placeholder="问题说明（可选）..."></textarea>
			    <div id="file_upload_area" class="table">
					<table class="grid">
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
                 <input type="hidden" name="attachmentContent" value=""/>  <!--保存文件上传后的路径-->
				<div class="question_toolbar">
					<div class="question_gradient"></div>

					<button class="question_button" type="submit" >发布</button>
				</div>
			</div>
			</form>
		</div>
<script type="text/javascript">
	$(document).ready(function(){
		$(".tag_pro #select_expert a").click(function(){
			$(this).addClass("selecttag");
			$(this).nextAll("a").removeClass("selecttag");
			$(this).prevAll("a").removeClass("selecttag");
			var rel2=$(this).attr("rel");
			$("#expertPersonalID").val(function(){return rel2});	
		});
	});
</script>
    <script src="${ctx}/jslib/Uploadify/swfobject.js" type="text/javascript"></script>
    <script src="${ctx}/jslib/Uploadify/jquery.uploadify.min.js" type="text/javascript"></script>
  
	<script>
		$(function() {
		//var editor = $('#content').xheditor();
		var editor = $('#question_content').xheditor({cleanPaste:0,height:'300px',upBtnText:'选择',upImgUrl:'${ctx}/front/web/communicate/getUploadFile'});
		var waitupload = false; //是否已经触发附件上传

		$('#questionform').form({
			url : '${ctx}/front/web/communicate/question/add',
			onSubmit : function() {
				progressLoad();
				var uploadCount = $('#uploadfileQueue').find('.uploadify-queue-item').length; //等待上传附件的个数
				var isValid = $(this).form('validate');
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
			
				return isValid;
			},
			success : function(result) {
				result = $.parseJSON(result);
		    	progressClose();
		    	if (result.success) {
		    		window.location.href='${ctx}/front/web/base/getQuestionList';
		    	}else{
		    		$.messager.show({
		    			title:'提示',
		    			msg:'<div class="light-info"><div class="light-tip icon-tip"></div><div>'+result.msg+'</div></div>',
		    			showType:'show'
		    		});
		    	}
			}
		});
		
		fnInituploadify();
	});
    
    var allFileUploadSuccess = true; //标识是否全部附件都上传成功
    //初始化上传控件
    function fnInituploadify() {
        $("#file_upload").uploadify({
            'debug': false,	//开启调试  
            'auto': false,	//是否自动上传  
            'buttonText': '选择附件',
            'swf': "${ctx}/jslib/Uploadify/uploadify.swf",	//flash  
            'queueID': 'uploadfileQueue',	//文件选择后的容器ID  
            'uploader': '${ctx}/front/web/communicate/base/getUploadFile',  	//服务器后台的上传响应函数
            'width': '120',
            'height': '29',
            'multi': true,
            'fileTypeDesc': '支持格式',
            'fileTypeExts': '*.jpg;*.png;*.bmp;*.jpeg;',
            'fileSizeLimit': '5MB',
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
                    	alert(dataobj.err);
                    }	
                    if(dataobj.msg != ""){
						
                    	$('input[name=attachmentContent]').val($('input[name=attachmentContent]').val() + dataobj.msg); //将附件在服务器中的路径写入attachmentContent
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
            		$('#questionform').submit();
            	}
            }
        });
     }
		
	
	</script>
	</div>
</div>

</div>
<!--end:右侧内容-->

</div>  
<!--end:内容项目-->

<!--head:底部信息-->
  <jsp:include page="./footer.jsp"></jsp:include>
<!--end:底部信息-->
<script type="text/javascript" src="${ctx}/jslib/index/thickbox.js"></script>
<script type="text/javascript" src="${ctx}/jslib/index/themepark.js"></script>


</body>
</html>
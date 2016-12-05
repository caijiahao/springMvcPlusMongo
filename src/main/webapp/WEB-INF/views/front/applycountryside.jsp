<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../inc.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>农技推广综合管理平台</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=9">
<link rel="shortcut icon" href="${ctx}/style/images/ico.ico"
	type="image/x-icon" />
<link href="${ctx}/style/index/themepark.css" rel="stylesheet"
	media="screen">
<link href="${ctx}/style/index/default.css" rel="stylesheet"
	media="screen">
<link href="${ctx}/style/index/detailinputunit.css" rel="stylesheet"
	media="screen">
<link href="${ctx}/style/index/countryside.css" rel="stylesheet"
	media="screen">

<style>
	#applytijiao
	{
		width: 173px; 
		height: 43px; 
		cursor: pointer; 
		background: url("${ctx}/style/images/index/applytijiao.png");
	}
	#applytijiao:hover
	{
		background: url("${ctx}/style/images/index/applytijiao_s.png");
	}
</style>
</head>
<body
	class="page page-id-2 page-parent page-template page-template-aboutus page-template-aboutus-php">

	<!--start 登录注册浮动栏 -->
	<jsp:include page="./wpthemedemobar.jsp"></jsp:include>
	<!-- end 登录注册浮动栏 -->

	<!-- start:头部导航 -->
	<jsp:include page="./header.jsp"></jsp:include>
	<!-- end:头部导航-->

	<!--head:所在位置-->
	<div id="page_muen_nav">
		<b>您现在所在的位置：</b><a href="${ctx}/front/web/base/index">首页</a> <a>
			&gt; </a><a href="${ctx}/front/village/base/getCountrysideList"> 下乡管理列表</a><a>
			&gt; </a><a href="${ctx}/front/village/base/getApplycountryside"> 申请</a>
	</div>
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

			<div class="into_unit">
				<div class="info_title" style="top: 15px; left: 60px;">申请信息</div>
				<div class="info_detail">
					<form id="applyForm" method="post" autocomplete=off>
						<div>
							<p style="width: 100%;">
								<span><strong>下乡目的地</strong></span> <span><span
									id="provincecity" style="width: 260px;"></span> <input
									name="businessAddress" type="text" class="easyui-validatebox" data-options="required:true"
									placeholder="详细地址" style="width: 222px; margin-top: 2px;" /> </span>
							</p>
						</div>
						<div>
							<p style="float: left">
								<span><strong>开始日期</strong></span> <span> <input
									id="businessDate" type="text" name="businessDate" class="easyui-validatebox" data-options="required:true" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'returnDate\')}',isShowClear:false,readOnly:true})"/>
								</span>
							</p>
							<p style="float: right">
								<span><strong>结束日期</strong></span> <span> <input
									id="returnDate" type="text" name="returnDate" class="easyui-validatebox" data-options="required:true" onclick="WdatePicker({minDate:'#F{$dp.$D(\'businessDate\')}',isShowClear:false,readOnly:true})"/></span>
							</p>
						</div>
						<div>
							<p style="float: left">
								<span><strong>联系人</strong></span> <span> <input
									type="text" id="personalName" name="personalName" disabled=true
									placeholder="" value="${personalName}" />
								</span>
							</p>
							<p style="float: right">
								<span><strong>联系人电话</strong></span> <span> <input
									type="text" id="personalPhone" name="personalPhone" disabled=true
									placeholder="" value="${personalPhone}" />
								</span>
							</p>
						</div>
						<div>
							<p style="width: 100%;">
								<span><strong>下乡事由</strong></span>
								<select id="reasonType" name="reasonType" style="width:215px; margin-top: 3px;" onchange="fnReasonTypeChange();">
									<c:forEach items="${reasonTypeList}" var="reasonType">
										<option value="${reasonType.id}" code="${reasonType.code}">${reasonType.text} 
										</option>
									</c:forEach>
								</select>
								<span>
								<textarea id="businessReason" name="businessReason" class="easyui-validatebox" data-options="validType:'length[0,500]'"
									style="width: 490px; height: 50px; outline: none; margin-top: 10px; display:none;"
									placeholder="详细描述..."></textarea></span>
							</p>
						</div>
						<div>
							<p style="float: left">
								<span><strong>下乡人员</strong></span> <span> <input
									type="text" id="txtperson" 
									placeholder="搜索下乡人员姓名" />
								</span>
							</p>
							<div style="float: right; width: 50%; margin-top: 28px; overflow: hidden; height: 43px;">
							<div class="tag_pro question_detail_div">
								<div id="select_person">
									<!--<a rel="tag" tel="123123" personid="1" onclick="fnselectperson(this);">张一</a>  -->
								</div>
							</div>
							</div>
						</div>
						<div style=" margin-top:5px;">
							<table id="tpersonlist" cellpadding="0" cellspacing="0"
								border="1">
								<thead>
									<th style="display: none;">人员ID</th>
									<th style="width: 50px;">姓名</th>
									<th style="width: 150px;">联系方式</th>
									<th style="width: 30px;">操作</th>
								</thead>
								<tbody>
									
								</tbody>
							</table>
						</div>
						
						<div style="width: 173px; margin: 0 auto; margin-top:10px;">
							<a href="javascript:submitForm();"><div id="applytijiao"></div></a>
						</div>
						<input type="hidden" name="businessArea" />
						<input type="hidden" name="personIDs" />
					</form>
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
	<script src="${ctx}/jslib/register/provincesdata.js"
		type="text/javascript"></script>
	<script src="${ctx}/jslib/register/jquery.provincesCity.js"
		type="text/javascript"></script>
	<script>
		$.extend($.fn.validatebox.defaults.rules, {
	    telphone: {
			validator: function(value, param){
				 if (/^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$|(^(13[0-9]|15[0|3|6|7|8|9]|18[8|9])\d{8}$)/i.test(value)) {
	        		return true;
	    		}
	    		else
	    			return false;
			},
			message: '请输入有效的手机号'
	    	}
		});
		
		function fnReasonTypeChange() {
			if($("#reasonType option:selected").attr("code") == "Other"){
				//其他
				$("#businessReason").show();
			}
			else{
				$("#businessReason").hide();
			}
		}
		
		function submitForm() {
			//$('#businessArea').value=$('#province').text()+$('#city').text();
			var province = $('#province').val();
			if('请选择省份' == province){
				$.messager.alert('提示', '请选择省份', 'warning');
				return;
			}
			var city = $('#city').val();
			if('请选择城市' == city){
				$.messager.alert('提示', '请选择城市', 'warning');
				return;
			}
			var area = province + '省' + city + '市';
			$('input[name=businessArea]').val(area);
			
			var personIDs = '';
			$('tr[personid]').each(function(){
				personIDs += $(this).attr('personid') + ",";
			});
			
			if(personIDs.length < 1){
				$.messager.alert('提示', '请添加下乡人员', 'warning');
				return ;
			}
			personIDs = personIDs.substr(0, personIDs.length - 1);
			$('input[name=personIDs]').val(personIDs);
			$('#applyForm').submit();
		}
	
		$(document).ready(function() {
			//省市控件     
			$("#provincecity").ProvinceCity("label");
			$("#provincecity label").css({
				"margin-top" : "-3px"
			});
			
			var time = 0;
			$('#txtperson').bind('keyup', function(){
				clearTimeout(time);
				time = setTimeout(function(){
					time = 0;
					var searchKey = $('#txtperson').val();
					if(!searchKey || searchKey.length < 1){
						$('#select_person').html('');
						return;
					}
					$.ajax({
						type:"post",
						dataType:"json",
						url:"${ctx}/front/village/base/search",
						data:{
							"searchKey" : searchKey
						},
						success:function(json){
							$('#select_person').html('');
							
							if(json && json.total > 0){
								var html = '';
								for(var i = 0; i < json.total; i++){
									var obj = json.rows[i];
									html += '<a rel="tag" tel="'+obj.phoneNumber+'" personid="'+obj.autoID+'" onclick="fnselectperson(this);">'+obj.realName+'</a>';
								}
								$('#select_person').html(html);
							}
						}
					});
				}, 300);
				return;
			});
		
			$('#applyForm').form({
				url : '${ctx}/front/village/base/add',
				onSubmit : function() {
					progressLoad();
					var isValid = $(this).form('validate');
					if (!isValid) {
						progressClose();
					}
					return isValid;
				},
				success : function(result) {
					result = $.parseJSON(result);
					progressClose();
					if (result.success) {
						alert(result.msg);
						window.location.href='${ctx}/front/village/base/getCountrysideList';
					} else {
						$.messager.show({
									title : '提示',
									msg : '<div class="light-info"><div class="light-tip icon-tip"></div><div>'
											+ result.msg
											+ '</div></div>',
									showType : 'show'
								});
					}
				}
			});
		});

		$.fn.datebox.defaults.formatter = function(date) {
			var y = date.getFullYear();
			var m = date.getMonth() + 1;
			var d = date.getDate();
			return y + '-' + m + '-' + d;
		};

		function fnselectperson(itm) {
			var personid = $(itm).attr("personid");
			var bfind = false;
			$('tr[personid]').each(function() {
				if ($(this).attr('personid') == personid) {
					bfind = true;
				}
			});
			if (bfind) {
				return false;
			}
			var personname = $(itm).html();
			var telphone = $(itm).attr("tel");
			var htmlitem = "<tr personid='"+personid+"'>"
					+ "<td>"
					+ personname
					+ "</td><td>"
					+ telphone
					+ "</td><td><span class='del_btn'  onclick='fndeleteperson(this);'><i class='del_icon'></i></span></td></tr>";
			$("#tpersonlist tbody").append(htmlitem);
		}

		function fndeleteperson(itm) {
			$(itm).parent("td").parent("tr").remove();
		}
	</script>
</body>
</html>
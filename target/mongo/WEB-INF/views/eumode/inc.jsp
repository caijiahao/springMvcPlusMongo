<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="edge" />
<link rel="shortcut icon" href="${ctx}/style/eu/images/index/favicon.png" />
<!-- 引入my97日期时间控件 -->
<script type="text/javascript" src="${ctx}/jslib/eu_js/My97DatePicker/WdatePicker.js" charset="utf-8"></script>

<!-- 引入jQuery -->
<script src="${ctx}/jslib/eu_js/jquery-1.8.3.js" type="text/javascript" charset="utf-8"></script>
<!-- xheditor -->
<script src="${ctx}/jslib/eu_js/xheditor/xheditor-1.2.2.min.js" type="text/javascript"></script>
<script src="${ctx}/jslib/eu_js/xheditor/xheditor_lang/zh-cn.js" type="text/javascript"></script>

<!-- 引入EasyUI -->
<link id="easyuiTheme" rel="stylesheet" href="${ctx}/jslib/eu_js/easyui1.3.3/themes/<c:out value="${cookie.easyuiThemeName.value}" default="default"/>/easyui.css" type="text/css">
<script type="text/javascript" src="${ctx}/jslib/eu_js/easyui1.3.3/jquery.easyui.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/jslib/eu_js/easyui1.3.3/locale/easyui-lang-zh_CN.js" charset="utf-8"></script>

<!-- 扩展EasyUI -->
<script type="text/javascript" src="${ctx}/jslib/eu_js/extEasyUI.js" charset="utf-8"></script>

<!-- 扩展Jquery -->
<script type="text/javascript" src="${ctx}/jslib/eu_js/extJquery.js" charset="utf-8"></script>

<!-- 自定义工具类 -->
<script type="text/javascript" src="${ctx}/jslib/eu_js/lightmvc.js" charset="utf-8"></script>

<!-- 扩展EasyUI图标 -->
<link rel="stylesheet" href="${ctx}/style/eu/lightmvc.css" type="text/css">

<script type="text/javascript">
$(window).load(function(){
	$("#loading").fadeOut();
});
//公共方法
var pubMethod = {
    bind: function (control,code) {
        if (control == ''|| code == '')
        {
            return;
        }

        $('#'+ control).combobox({
            url: '${ctx}/dictionary/combox?code=' + code,
            method: 'get',
            valueField: 'id',
            textField: 'text',
            editable: false,
            panelHeight: 'auto',
            required:true
        });
    }
}
</script>

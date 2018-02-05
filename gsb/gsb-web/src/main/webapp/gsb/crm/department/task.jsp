<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>任务信息</title>
	<link href='/package/easyui/themes/material/easyui.css' rel='stylesheet' type='text/css' />
	<link href='/package/easyui/themes/easyui.extend.css' rel='stylesheet' type='text/css' />
	<link href='/package/easyui/themes/color.css' rel='stylesheet' type='text/css' />
	<link href='/package/easyui/themes/icon.css' rel='stylesheet' type='text/css' />
</head>
<body class="easyui-layout">
	<div id="tt" class="easyui-tabs">
	    <div title="任务信息" iconCls="icon-page" style="display:none;">
	        <iframe id="iframe_task" scrolling="auto" frameborder="0" style="width:100%;height:100%;"></iframe>  
	    </div>   
	    <div title="客户信息" iconCls="icon-user" style="display:none;">
	        <iframe id="iframe_customer" scrolling="auto" frameborder="0" style="width:100%;height:100%;"></iframe>
	    </div>
	</div>  
</body>
<script src='/package/easyui/jquery.min.js'></script>
<script src='/package/layer/layer.js'></script>
<script src='/package/easyui/jquery.easyui.min.js'></script>
<script src='/package/easyui/jquery.easyui.patch.js'></script>
<script src='/package/easyui/locale/easyui-lang-zh_CN.js'></script>
<script src='/package/easyui/jquery.easyui.extend.js'></script>

<script src='/package/jquery/jquery.md5.js'></script>
<script src='/panda-res/js/system.js'></script>

<script src='/gsb/crm/base/js/task-base.ctrl.js'></script>

<script src='/gsb/crm/department/js/task.ctrl.js'></script>
<script>
	$(function(){

		var taskCtrl = new com.gongsibao.crm.web.DepartmentTaskCtrl();
		taskCtrl.init();
	});
</script>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>关联订单</title>
	<link href='/package/font-awesome/css/font-awesome.min.css' rel='stylesheet' type='text/css' />
	<link href='/package/easyui/themes/material/easyui.css' rel='stylesheet' type='text/css' />
	<link href='/package/easyui/themes/easyui.extend.css' rel='stylesheet' type='text/css' />
	<link href='/package/easyui/themes/color.css' rel='stylesheet' type='text/css' />
	<link href='/package/easyui/themes/icon.css' rel='stylesheet' type='text/css' />
	<link href='/panda-res/css/panda.form.css' rel='stylesheet' type='text/css' />
	<style>
		.form-panel td.label_td{width:120px;}
	</style>
	<script src='/package/easyui/jquery.min.js'></script>
	<script src='/package/layer/layer.js'></script>
	<script src='/package/easyui/jquery.easyui.min.js'></script>
	<script src='/package/easyui/locale/easyui-lang-zh_CN.js'></script>
	<script src='/package/easyui/jquery.easyui.extend.js'></script>
	<script src='/panda-res/js/system.js'></script>
	<script src='/panda-res/js/panda.core.js'></script>
	<script src='/panda-res/js/panda.js'></script>
	<script src='/gsb/platform/trade/js/order-pay-map.ctrl.js'></script>
</head>
     <body class="easyui-layout">
        <div data-options="region:'north',split:false,collapsible:false,closed:false,height:80">
        	 <div class="formContent">
				  <table cellpadding="3" cellspacing="0" class="form-panel">
				      <tr>
				          <td class="label_td"><label>订单号：</label></td>
				          <td class="control_td">
				          		<input id="orderNo" class="easyui-validatebox nsInput" style="width:200px;"/>
				          </td>
				          <td class="label_td"><label>订单分配金额：</label></td>
				          <td class="control_td">
					          	<input id="amount" class="easyui-numberbox nsInput" data-options="precision:2,width:200,min:1"/>
				          </td>
				          <td class="label_td"><label>付款类别：</label></td>
				          <td class="control_td">
				          		<input id="u8BankId" class="easyui-combobox nsInput" data-options="editable:false,width:200"/>
				          </td>
					</tr>
				</table>
        	 </div>
		</div>
		<div id="center" data-options="region:'center'">
			 <table id="pay_dep_grid"></table>
		</div>
</body>

<script>

	var payMapCtrl = null;
	$(function(){
		
		payMapCtrl = new com.gongsibao.trade.web.OrderPayMapCtrl();
		payMapCtrl.init(); 
	});
</script>
</html>

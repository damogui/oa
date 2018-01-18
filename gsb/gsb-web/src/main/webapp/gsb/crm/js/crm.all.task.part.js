//任务中的子页面工具栏中的跟进操作（沟通日志的跟进操作）
System.Declare("com.gongsibao.crm.web"); 
com.gongsibao.crm.web.NCustomerFollowPart = org.netsharp.panda.commerce.DetailPart.Extends( {
	saveAfter:function(entity){
		var id = this.queryString("id");
		entity.id=null;
		entity.customerId=id;
		var me = this;
		this.invokeService("save", [entity], function(data) {
			me.parent.byId(entity.customerId);
		});
	}
});
//无法签单中的工具栏
com.gongsibao.crm.web.CheckAbmormalPart = org.netsharp.panda.commerce.ListPart.Extends({
	ctor : function() {
		this.base();
	},
	checkAbmormal : function() {
		var me = this;
		var row = this.getSelectedItem();
		if (row == null) {
			IMessageBox.info('请选择记录');
			return;
		}
		// 支付id
		var payId = row.id;
		// 订单编号
		var orderNo = row.orderNo;
		// 回单编号
		var receiptNo = row.receiptNo;
		
		var content = '<br/><p style="padding-left:50px;">&nbsp;订单编号：<input  type="text" disabled="disabled"  class="easyui-validatebox nsInput"  value="'
				+ orderNo
				+ '" style="width:180px;"></input></p>'
				+ '<p style="padding-left:50px;">&nbsp;回单号：<input id="txtReceiptNo" type="text" class="easyui-validatebox nsInput" value="'
				+ receiptNo
				+ '" required="true" style="width:180px;"></input></p>';
		
		// window.top.layer.open({
		layer.open({
			type : 1,
			title : '绑定回单编号',
			fixed : false,
			maxmin : false,
			shadeClose : false,
			area : [ '500px', '300px' ],
			content : content,
			btn : [ '保存', '取消' ],// 可以无限个按钮
			btn1 : function(index, layero) {
				var receiptNo = $("#txtReceiptNo").val();
				if (System.isnull(receiptNo)) {
					IMessageBox.info('请输入回单编号');
					return false;
				}
				me.doBindReceiptWeb(payId, receiptNo);
			},
			btn2 : function(index, layero) {
			}
		});
	},
	doBindReceiptWeb : function(payId, receiptNo) {
		var me = this;
		this.invokeService("bindReceiptNo", [ payId, receiptNo ],
				function() {
					me.reload();
					IMessageBox.toast('绑定成功');
					layer.closeAll();
					return;
				});
	},
	changeReceiptStatusFormatter:function(value,row,index){				
		var checked = value=='已完成'?true:false;
		return '<input class="easyui-switchbutton" data-options="'
		+'checked:'+checked
		+',onText:\'已完成\',offText:\'未完成\','
		+'onChange:function(checked){ controllerpayReceiptCheckDTOList.changeReceiptStatus(\''+row.id+'\',checked);}">';
	},
	changeReceiptStatus(payId,value){
		
		var state = value==true?1:0;
		var me = this;
		this.invokeService("changeReceiptStatus", [payId,state], function(data) {

			me.reload();
			IMessageBox.toast("操作成功！");
		});
	},
	onLoadSuccess:function(data){				
		$('.easyui-switchbutton').switchbutton();
	}		
});

System.Declare("com.gongsibao.trade.web");
//订单审核-结转审核
com.gongsibao.trade.web.AuditCarryoverListPart = org.netsharp.panda.commerce.ListPart.Extends({
    ctor: function () {
        this.base();
        this.addCarryOverUrl = '/nav/gsb/platform/trade/auditCarryover';//结转审核jsp
    },
    operateFormatter:function(value,row,index){
    	if(row.status!="待审核"){ 
    		return "<a class='grid-btn' href='javascript:controllerauditLogList.detail(" + value + ");'>查看</a>";
    	}else{    		
    		return "<a class='grid-btn' href='javascript:controllerauditLogList.auditCarryOver(" + value + ");'>审核</a>";
    	}
    	
    },
    auditCarryOver : function (id){
    	var me = this;
    	var row = this.getSelectedItem();
    	var carryoverId = row.formId; 
    	var orderId = row.carryover_formOrderId;
    	var contentUrl = this.addCarryOverUrl + "?carryoverId=" + carryoverId + "&id=" + orderId + "&auditId=" + id;
    	if(row.status != '待审核'){
    		layer.msg('该审核记录的状态不是【待审核】，禁止审核！');
    		return;
    	}
        layer.open({
            type: 2,//1是字符串 2是内容
            title: '查看结转',
            fixed: false,
            maxmin: true,
            shadeClose: true,
            area: ['95%', '95%'],
            zIndex: 100000,
            id: "carryOverIframe",
            content: contentUrl,
            btn: ['审核通过', '审核不通过'],// 可以无限个按钮
            btn1: function (index, layero) {
                document.getElementById('carryOverIframe').firstElementChild.contentWindow.auditCarryoverCtrl.approved(function(s){
                	me.reload();
                });
            },
            btn2: function (index, layero){
            	document.getElementById('carryOverIframe').firstElementChild.contentWindow.auditCarryoverCtrl.rejected(function(s){
            		me.reload();
            	});
            	return false;
            }
        });
    },
    detail : function(id){
    	var row = this.getSelectedItem();
    	var carryoverId = row.formId; 
    	var orderId = row.carryover_formOrderId;
    	var contentUrl = this.addCarryOverUrl + "?carryoverId=" + carryoverId + "&id=" + orderId;
         layer.open({
             type: 2,//1是字符串 2是内容
             title: '查看结转',
             fixed: false,
             maxmin: true,
             shadeClose: true,
             area: ['95%', '95%'],
             zIndex: 100000,
             id: "carryOverIframe",
             content: contentUrl
         });
    }
});




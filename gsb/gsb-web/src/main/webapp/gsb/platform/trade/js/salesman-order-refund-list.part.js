System.Declare("com.gongsibao.trade.web");
//我的订单-我的退款业绩
com.gongsibao.trade.web.OrderSalesmanRefundListPart = org.netsharp.panda.commerce.ListPart.Extends({
    ctor: function () {
        this.base();
        this.addRefundUrl = '/nav/gsb/platform/trade/auditRefund';//创建退款jsp
    },
    detail : function(id){
    	var row = this.getSelectedItem();
    	var orderId = row.orderId;
    
    	var contentUrl = this.addRefundUrl + "?fefundId=" + id + "&id=" + orderId;
    	
         layer.open({
             type: 2,//1是字符串 2是内容
             title: '查看退款',
             fixed: false,
             maxmin: true,
             shadeClose: true,
             area: ['95%', '95%'],
             zIndex: 100000,
             id: "auditRefundIframe",
             content: contentUrl
         });
    },
    serviceNameFormatter : function(value,row,index){
    	
    }
});




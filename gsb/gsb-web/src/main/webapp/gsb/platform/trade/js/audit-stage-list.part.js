System.Declare("com.gongsibao.trade.web");
//订单审核-分期审核
com.gongsibao.trade.web.AuditStageListPart = org.netsharp.panda.commerce.ListPart.Extends({
    ctor: function () {
        this.base();
        this.auditStageUrl = '/nav/gsb/platform/trade/auditStage';//分期审核jsp
    },
    auditStage: function (id) {//订单审核-分期审核
        var me = this;
        var row = this.getSelectedItem();
        var rows = this.getSelections();
        if (rows.length <= 0) {
            IMessageBox.info('请先选择订单数据');
            return false;
        }
        var contentUrl = this.auditStageUrl + "?id=" + row.id;
        layer.open({
            type: 2,//1是字符串 2是内容
            title: '分期审核',
            fixed: false,
            maxmin: true,
            shadeClose: false,
            area: ['50%', '70%'],
            zIndex: 100000,
            id: "auditStageIframe",
            content: contentUrl,
            btn: ['审核通过', '审核不通过'],// 可以无限个按钮
            btn1: function (index, layero) {
                document.getElementById('auditStageIframe').firstElementChild.contentWindow.auditStageCtrl.approved();
            },
            btn2: function (){
            	document.getElementById('auditStageIframe').firstElementChild.contentWindow.auditStageCtrl.rejected();
            }
        });

    },
    detail : function(id){
    	var row = this.getSelectedItem();
    	var contentUrl = this.auditStageUrl + "?id=" + row.id;
    	  layer.open({
              type: 2,//1是字符串 2是内容
              title: '查看',
              fixed: false,
              maxmin: true,
              shadeClose: false,
              area: ['50%', '70%'],
              zIndex: 100000,
              content: contentUrl
          });
    },
    serviceNameFormatter : function(value,row,index){
    	
    }
});




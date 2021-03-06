System.Declare("com.gongsibao.trade.web");
com.gongsibao.trade.web.AuditStageCtrl = com.gongsibao.trade.web.AuditBaseCtrl.Extends({
    ctor: function () {
    	
    	this.base();
    	this.initializeDetailList = new System.Dictionary();
    	//获取枚举
    	this.auditLogStatusEnum = PandaHelper.Enum.get('com.gongsibao.entity.bd.dic.AuditLogStatusType');
    	this.service = 'com.gongsibao.trade.web.audit.AuditStageController';
    },
    initData:function(){
    	var stageId = this.queryString('stageId');
    	var orderId = this.queryString('id');
    	
    	var me = this;
    	//加载Tab项
    	$('#detail_tabs').tabs({ 
    		tabHeight:30,
		    onSelect:function(title){
		    	var detailCtrl = me.initializeDetailList.byKey(title);
		    	if(detailCtrl){		    		
		    		//已经初始化过的不再执行
		    		return;
		    	}
		    	if(title=='审批进度'){
		    		me.initAuditLog(stageId,1047);
		    	}
		    }
    	});
    	me.initializeDetailList.add('分期信息',this.installInfo(orderId));
    },
    installInfo: function(orderId){
    	//tab-获取分期信息
    	this.invokeService ("getOrderStage", [orderId], function(data){
    		//分期金额=订单金额（yyk提供）
    		var stageAmount = (data.payablePrice/100).toFixed(2);    		
    		var builder = new System.StringBuilder();
    		builder.append('<tr>');
    		builder.append('<td class="label_td"><label>分期期数：</label></td>');
    		builder.append('<td class="control_td">');
    		builder.append('<label>'+ data.stageNum +'</label>');
    		builder.append('</td>');
    		builder.append('<td class="label_td"><label>分期金额：</label></td>');
    		builder.append('<td class="control_td">');
    		builder.append('<label>'+ stageAmount +'</label>');
    		builder.append('</td>');
    		builder.append('</tr>');
    		
    		$(data.stages).each(function(i,item){
    			builder.append('<tr>');
        		builder.append('<td class="label_td"><label>'+item.instalmentIndex+'期付款：</label></td>');
        		builder.append('<td class="control_td">');
        		builder.append('<label>'+ (item.amount/100).toFixed(2) +'</label>');
        		builder.append('</td>');
        		builder.append('<td class="label_td"><label>付款比例：</label></td>');
        		builder.append('<td class="control_td">');
        		builder.append('<label>'+ item.percentage +'%</label>');
        		builder.append('</td>');
        		builder.append('</tr>');
        	});
    		$("#stage_info_grid").append(builder.toString());
    	});
    }
});
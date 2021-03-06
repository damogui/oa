System.Declare("com.gongsibao.franchisee.web");
com.gongsibao.franchisee.web.FranchiseeFormPart = org.netsharp.panda.commerce.FormPart.Extends( {

    ctor: function () {
        this.base();
    },
    follow:function(){
    	
    	//切换到【沟通日志】
    	$("#tabcenter").tabs('select',2);
	
    	//将【内容】设置为可写
    	$('#content').prop('disabled',false);
    	
    	//跟进
    	controllertracks.add();
    },
    
    onSaving: function (entity) {

    	if (System.isnull(entity.mobile) && System.isnull(entity.weixin)
    			&& System.isnull(entity.qq) && System.isnull(entity.tel)) {

    		IMessageBox.info('联系方式最少填写一项');
			return false;
		}
        return true;
    }

});

com.gongsibao.franchisee.web.TrackDetailPart = org.netsharp.panda.commerce.DetailPart.Extends( {

	
	addBefore:function(){
		
    	//将【内容】设置为禁用
    	$('#content').prop('disabled',false);
    	
	},	
	editBefore:function(){

    	//将【内容】设置为禁用
    	$('#content').prop('disabled',true);
    	
	},
	saveAfter:function(entity){
		
		entity.id=null;
		var me = this;
		this.invokeService("save", [entity], function(data) {

			me.parent.byId(entity.customerId);
		});
		
	}
});

com.gongsibao.crm.web.TaskAllListPart = com.gongsibao.crm.web.BaseTaskListPart.Extends({
	ctor : function() {
		this.base();
	},
	add:function(){
		
		window.open("/panda/crm/department/task/add");
	},
	edit : function(id) {
		
		var url = "/panda/crm/department/task/edit?id="+id;
		window.open(url);
	},
	takeBack:function(){
		
		alert('收回');
	}
});

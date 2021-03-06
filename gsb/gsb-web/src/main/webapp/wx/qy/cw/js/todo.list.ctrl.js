org.netsharp.we.core.TodoListCtrl = org.netsharp.we.core.listCtrl.Extends({
    ctor: function () {
    	this.base();
    	this.template = '<a class="weui-cell weui-cell_access" href="{0}"> '+
    		            '<div class="weui-cell__bd"><p>{1}</p></div><div class="weui-cell__ft">{2}</div> '+
    					'</a>';
    	this.service = 'com.gongsibao.cw.web.wx.TodoListController';
    	this.oper = "todo"; //操作类型
    	
    },
    query:function(){
    	var me = this;
    	var employeeId = this.queryString('employeeId');
    	var searchKeyWord = $("#searchKeyWord").val().trim();
    	console.log("当前页："+this.paging.pageIndex);
    	//查询
    	var pars = [employeeId,searchKeyWord,this.paging.pageIndex,this.paging.pageSize,this.oper];
    	this.invokeService('query', pars, function(result){
    		
    		me.paging.loadingCount = me.paging.loadingCount + result.rows.length;
    		me.paging.totalCount = result.total;
            //$(me.listId).setTemplateElement("template", null, { filter_data: false }).processTemplate(result.rows);
    		var html = me.getTemplateHtml(result.rows);
    		$(me.listId).append(html);
            me.queryAfter();
    	});
    },
    getTemplateHtml:function(rows){
    	var me = this;
    	var html = '';
    	var billUrl = "";
    	$(rows).each(function(i,item){
    		var formTypeText = formTypeDict.byKey(item.formType);
    		billUrl = me.urlHandle(item);
    		html += me.template.format(billUrl,formTypeText,item.code);
    	});
    	return html;
    },
    urlHandle:function (item){
    	var billUrl = "";
    	var employeeId = this.queryString('employeeId');
    	if(item.formType == 3 ){
			billUrl = "loanDetail?employeeId="+employeeId+"&id="+item.formId;
		}
		if(item.formType == 4 ){
			billUrl = "expenseDetail?employeeId="+employeeId+"&id="+item.formId;
		}
    	return billUrl;
    },
    filter:function(){
    	
    	var searchKeyWord = $("#searchKeyWord").val().trim();
    	if(System.isnull(searchKeyWord)){
    		
    		return;
    	}
    	
    	$(this.listId).html('');
    	this.query();
    },
    cancel:function(){
    	
    	$("#searchKeyWord").val("");
    	$(this.listId).html('');
    	this.query();
    }
});
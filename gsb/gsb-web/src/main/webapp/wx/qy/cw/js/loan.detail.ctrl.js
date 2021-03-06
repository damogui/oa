org.netsharp.we.core.LoanDetailCtrl = org.netsharp.we.core.detailCtrl.Extends({
    ctor: function () {
    	this.base();
    	this.service = 'com.gongsibao.cw.web.wx.TodoListController';
    	this.id = this.queryString('id');
    	this.oper = this.queryString('oper');
    },
    init:function(){
    	this.byId();
    	if(this.oper == "done"){
    		$("#auditDiv").hide();
    		$("#saveBtn").hide();
    	}
    },
    byId:function(){
    	var me = this;
    	var pars = [this.id];
    	this.invokeService('getLoanById', pars, function(result){
    		me.bindData(result);
    		if(result.paymentMethod == 1){
    			$("#payBank").attr("disabled","disabled");  
    			$("#payBankDiv").hide();
    		}else{
    			me.bindU8BankSelect(result.setOfBooksId);
    		}
    		
    	});
    },
    bindData:function(entity){
    	$("#formId").val(entity.id);
    	$("#apply_user_id").val(entity.creatorId);
		$("#apply_department_id").val(entity.departmentId);
    	
    	
    	$("#bill_code").text(entity.code);
    	$("#create_time").text(entity.createTime);
    	$("#amount").text(entity.amount/100);
    	$("#borrower_name").text(entity.borrowerEmployee.name);
    	$("#creator").text(entity.creator);
    	$("#payeeName").text(entity.payeeName);
    	$("#books_name").text(entity.setOfBooks.name);
    	$("#payment_method").text(paymentMethodDict.byKey(entity.paymentMethod));
    	$("#bill_type").text(loanBillTypeDict.byKey(entity.type));
    	$("#memoto_txt").text(entity.memoto);
    	$("#companyAccount").text((System.isnull(entity.companyAccount))?"无":entity.companyAccount);
    	$("#companyBank").text((System.isnull(entity.companyBank))?"无":entity.companyBank);
    	
    	
    	
    },
    bindU8BankSelect: function (setOfBooksId){
    	this.invokeService("getU8BankList", [setOfBooksId], function(data){  
    		for(var i =0;i<data.length;i++){
    			$("#payBank").append("<option value='"+data[i].id+"'>"+data[i].name+"</option>");
    		}
	   	});
    },
    saveAudit:function (status){
    	var me = this;
    	var employeeId = me.queryString('employeeId');
    	var memoto = $("#memoto").val();
    	if(System.isnull(memoto)){
    		$.toptip('审批意见不能为空', 'warning');
			return;
		}
    	var auditRecord = {};
    	auditRecord.status = status;
    	auditRecord.memoto =memoto;
    	auditRecord.formId = $("#formId").val();
    	auditRecord.formType = 3;  //借款单
    	auditRecord.auditUserId = employeeId;
    	auditRecord.applyUserId = $("#apply_user_id").val();
    	auditRecord.applyDepartmentId = $("#apply_department_id").val();
    	this.invokeService("saveAudit", [auditRecord], function(data){  
    		$.toptip('提交成功', 'success');
    		setTimeout(function() {
    			window.location.href = 'todoList?employeeId=' + employeeId;
			}, 1000);
			
	   	});
    },
    toAuditList : function (){
    	var formId = this.queryString('id');
    	window.location.href = 'auditList?formId=' + formId +"&formType="+3;
    },
    toFileList : function (){
    	var formId = this.queryString('id');
    	window.location.href = 'filesList?formId=' + formId +"&formType="+3;
    }
});



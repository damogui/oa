System.Declare("com.gongsibao.trade.web");
com.gongsibao.trade.web.ProdTraceCtrl = org.netsharp.panda.core.CustomCtrl.Extends({
    ctor: function () {
    	this.base();
    	this.$gridCtrlId = '#order_prod_trace_grid';
    	this.service = 'com.gongsibao.trade.web.OrderProdDetailController';
    	this.orderProdId = null;
    	this.mainCtrl = null;
    },
    init:function(orderProdId){

    	this.orderProdId = orderProdId;
    	var me = this;
    	this.initGrid();
    	this.query(1,10);
    },
    query:function(pageIndex,pageSize){
    	
    	var me = this;
    	this.invokeService ("queryProdTraceList", [this.orderProdId,pageIndex,pageSize], function(data){
    		
    		$(me.$gridCtrlId).datagrid('loadData',data);
    	});
    },
    initGrid:function(){
    	
    	var me = this;
		$(this.$gridCtrlId).datagrid({
			idField:'id',
			emptyMsg:'暂无记录',
			height:380,
			striped:true,
			rownumbers:true,
			pagination:true,
			showFooter:true,
			singleSelect:true,
			pageSize:10,
			rowStyler: function(index,row){
				
				switch(row.tipColor){
					
					case 'text-muted':
						return 'color: #909FA7;';
					case 'text-warning':
						return 'color: #fbc02d;';
					case 'text-purple':
						return 'color: #ae81ff;';
					case 'text-success':
						return 'color: #4CAF50;';
					case 'text-primary':
						return 'color: #1E7CB5;';
					default:
						return 'color: ##404040;';
				}
			},
			onLoadSuccess:function(data){
				
				var pager = $(this).datagrid('getPager');
				$(pager).pagination('options').onSelectPage = function(pageNumber, pageSize){
					
					me.query(pageNumber,pageSize);
				}; 
			},
		    columns:[[
		        {field:'info',title:'操作信息',width:250},
		        {field:'operatorId',title:'操作人',width:60,align:'center',formatter:function(value,row,index){

		        	if(row.operator){
		        		
		        		return row.operator.name
		        	}
		        }},
		        {field:'orderProdStatusId',title:'订单状态',width:80,align:'center',formatter:function(value,row,index){

		        	if(row.orderProdStatus){
		        		
		        		return row.orderProdStatus.name
		        	}
		        }},
		        {field:'createTime',title:'操作时间',width:130,align:'center'},   
		        {field:'tipColor',title:'配色',width:60,align:'center',formatter:function(value,row,index){

		        	var builder = new System.StringBuilder();
		        	builder.append('<select style="width:53px;" class="'+row.tipColor+'" onchange="traceCtrl.updateTraceTipColor('+row.id+',this);">');
		        	builder.append('<option value="text-default" class="text-default" '+(value=='text-default'?'selected':'')+' >默认</option>');
		        	builder.append('<option value="text-primary" class="text-primary" '+(value=='text-primary'?'selected':'')+' >蓝色</option>');
		        	builder.append('<option value="text-success" class="text-success" '+(value=='text-success'?'selected':'')+' >绿色</option>');
		        	builder.append('<option value="text-purple" class="text-purple" '+(value=='text-purple'?'selected':'')+' >紫色</option>');
		        	builder.append('<option value="text-warning" class="text-warning" '+(value=='text-warning'?'selected':'')+' >黄色</option>');
		        	builder.append('<option value="text-muted" class="text-muted" '+(value=='text-muted'?'selected':'')+' >灰色</option>');
		        	builder.append('</select>');
		        	return builder.toString();
		        }},
		        {field:'id',title:'操作',width:60,align:'center',formatter:function(value,row,index){
		        	
		        	//原系统中：类型为异常的才能删除
		        	//return '<a class="grid-btn" href="javascript:;">删除</a>';
		        }}
		    ]]
		});
    },
    updateTraceTipColor:function(traceId,element){
    	
    	var me = this;
    	var row = $(this.$gridCtrlId).datagrid('getSelected');
    	if(row){

    		var tipColor = $(element).val();
    		var rowIndex = $(this.$gridCtrlId).datagrid('getRowIndex',row);
    		row.tipColor = tipColor;
    		$(this.$gridCtrlId).datagrid('updateRow',{
    			index: rowIndex,
    			row: row
    		});
    		$(this.$gridCtrlId).datagrid('refreshRow',rowIndex);
    		
        	this.invokeService ("updateTraceTipColor", [traceId,tipColor], function(data){
        		
        		
        	});
    	}
    },
    updateProcessStatus(){
    	
    	var me = this;
    	//var version = this.mainCtrl.orderProd.version;//要更新老数据至version（根据order_prod_status_id到prod_workflow_node表里冗余version）
    	//var productId = this.mainCtrl.orderProd.productId;
    	//var cityId = this.mainCtrl.orderProd.cityId;
    	productId = 1040;
    	cityId = 101440106;
    	version = 8;
    	this.invokeService("queryWorkflowNodeList", [productId,cityId,version], function(data){
    		
    		if(data){
    			
    			var builder = new System.StringBuilder();
    			builder.append('<form id="dynamicForm">');
    			builder.append('<div style="margin:10px;">');
    			builder.append('	<table cellpadding="5" cellspacing="10" class="form-panel">');
    			builder.append('		<tr><td>提示:状态回退将被<span style="color:red;">标红</span>，请谨慎操作。如当地办理流程有变化，请与系统管理员及时联系修改流程。</td></tr>');
    			builder.append('		<tr><td><input id="processStatus"/></td></tr>');
    			builder.append('		<tr><td><input id="isSendMessage" type="checkbox" style="vertical-align: middle;"/><label for="isSendMessage" style="vertical-align: middle;">短信通知客户</label></td></tr>');
    			builder.append('	</table>');
    			builder.append('</div>');
    			builder.append('</form>');
    			
    			//短信通知客户
    			layer.open({
    				type : 1,
    				title : '更改状态',
    				fixed : false,
    				maxmin : false,
    				shadeClose : true,
    				zIndex : 100000,
    				area : [ '680px', '250px' ],
    				content : builder.toString(),
    				btn : [ '确定', '取消' ],
    				success : function(layero, index) {
    					
    					$("#processStatus").combobox({
    						width:600,
    						editable:false,
    						panelHeight:250,
    						valueField: 'id',    
    				        textField: 'name',
    				        data:data});
    				},
    				btn1 : function(index, layero) {

    					//提交更新状态
    					var processStatusId = $('#processStatus').combobox('getValue');
    					if(System.isnull(processStatusId)){
    						
    						layer.msg('请选择状态');
    						return;
    					}
    					
    					var processStatusText = $('#processStatus').combobox('getText');
    					var isSendMessage = $('#isSendMessage').prop('checked');
    					
    					var trace = new Object();
    					trace.orderProdId = me.orderProdId;
    					trace.orderProdStatusId = processStatusId;
    					trace.info = "更新状态:" + processStatusText;
    					trace.isSendMessage = isSendMessage;
    					trace.version = data[0].version;
    					
    					//更新状态:网提
    					me.invokeService("updateProcessStatus", [trace], function(data){
    						
    						layer.close(index);
    						me.init(me.orderProdId);
    					});
    				}
    			});
    		}
    	});
    }
});

//更改状态时，根据prod_workflow_node的type更新订单的process_status_id（要检查是否还有明细） 和订单明细的process_status_id

//process_status_id 不是枚举，要改成关联prod_workflow_node
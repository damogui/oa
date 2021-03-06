System.Declare("com.gongsibao.igirl.tm.web");
var countinput = 0;
com.gongsibao.igirl.tm.web.TradeMarkCasePart = org.netsharp.panda.commerce.FormPart.Extends({

	ctor: function () {
		this.base();
	},
	save: function(){
		if(this.checkShareGroups()){
			this.base();
		}
	},
	checkShareGroups:function(){
		var rows = $('#datagridtradeMarks').datagrid('getRows');
		if(rows.length==0){
			return true;
		}
		var hasGroup1 = false;
		var maxValue=0;
		var isOrderBy=true;
		for(var i=0;i<rows.length;i++){
			if(rows[i].shareGroup>maxValue){
				maxValue=rows[i].shareGroup;
			}
			if(rows[i].shareGroup==1){
				hasGroup1=true;
			}
			if(rows[i].shareGroup==0){
				IMessageBox.error("【商标选项】编码为"+rows[i].nclOne_code+"的商标未选择共享分组！");
				return false;
			}
		}
		if(hasGroup1){
			for(var i=maxValue;i>1;i--){
				if(!this.isHasBeforeGroup(i,rows)){
					IMessageBox.error("【商标选项】附件共享要按顺序选择分组.");
					isOrderBy=false;
					break
				}
			}
			if(isOrderBy){
				return true;
			}
		}else{
			IMessageBox.error("【商标选项】附件共享要有共享分组1.");
		}
		return false;
	},
	isHasBeforeGroup:function(maxValue,rows){
		for(var i=0;i<rows.length;i++){
			if(rows[i].shareGroup==(maxValue-1)){
				return true;
			}
		}
		return false;
	},
	companyNameChange: function (newValue) {
		var name = $(newValue).val();
		// name=encodeURIComponent(name);

		if (name && name != "") {
			this.invokeService("fetchCompanyByName", [name], function (data) {
				if (data) {
					if (data.appCnName) {
						if (data.appCnName.indexOf(name) != -1 && countinput < 2) {
							$("#companyName").val(data.appCnName);
							countinput++;
						}
					}
					if (data.certCode) {
						if (data.appCnName.indexOf(name) != -1) {
							$("#creditCode").val(data.certCode);
						}
					}
					if (data.appCnName) {
						if (data.appCnName.indexOf(name) != -1) {
							$("#applier").val(data.appCnName);
						}
					}
					if (data.appCnAddr) {
						if (data.appCnName.indexOf(name) != -1) {
							$("#applierAddress").val(data.appCnAddr);
						}
					}

				}
			});

		}
	},
	onload:function(){
		this.base();
		$("fieldset:contains('自然人信息')").hide();
		$("#tokenImgUrl").click(function(){
			var qrurl=$(this).attr("src");
			if(qrurl && qrurl!=""){
				var enurl=qrurl.split("=")[1];
				window.open(decodeURIComponent(enurl));
			}
		});
	},
	applierTypeChange: function (newValue, oldValue) {
		if (newValue == 1) {
			$("#companyName").validatebox('disable').validatebox('disableValidation');
			$("fieldset:contains('自然人信息')").show();
		} else {
			$("#companyName").validatebox('enable').validatebox('enableValidation');
			$("fieldset:contains('自然人信息')").hide()
		}
	},
	mobileChange: function (ctl) {
//		var newValue=$(ctl).val().trim();
//		var qcurl="http://192.168.4.1:3000/qc?detailLink=http://192.168.28.41:8080/gsb/igirl/tmcase.html?mobile="+newValue;
//		//请求获取生成二位码的服务url
//		if(newValue && newValue!=""){
//		if(controllertradeMarkCase.viewModel.currentItem){
//		var code=controllertradeMarkCase.viewModel.currentItem.code;
//		if(code && code!=""){
//		this.invokeService("fetchQrCodeUrl", [newValue,code], function(data) {
//		var qarray= data.split("|");
//		var url=qarray[0];
//		var q=encodeURIComponent(qarray[1])
//		$("#tokenImgUrl").attr("src",url+q);
//		});
//		}

//		}

//		}
	},
	validate: function () {
		var isValidate = $("#" + this.context.formName).form('validate');
		if (isValidate) {
			var applierType=$("#applierType").val();
			var writeType=$("#writeType").val();
			if(System.isnull(applierType) || System.isnull(writeType))
			{
				IMessageBox.error("请选择申请人类型和书式类型");
				return false;
			}
			if(writeType!='0')
			{
				IMessageBox.error("书式类型目前只支持中国大陆");
				return false;
			}

			var ywPhone = $("#ywPhone").val();
			var certificateType=$("#certificateType").val();
			var idencode = $("#identityCode").val();
			var applierAddress = $("#applierAddress").val();

			if(applierType=='1')
			{
				if(System.isnull(certificateType))
				{
					IMessageBox.error("请选择证件名称");
					return false;
				}
				if(certificateType=='0')
				{
					if (System.isnull(idencode) || !/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test(idencode)) {
						IMessageBox.error("【身份证】格式错误");
						return false;
					}
				}
			}
			var x = /(([省])|([中][国])|([中][华][人][民][共][和][国])|([北][京][市])|([上][海][市])|([天][津][市])|([重][庆][市])|([内][蒙][古])|([广][西])|([宁][夏])|([新][疆])|([西][藏])|([内][蒙][古][自][治][区])|([新][疆][维][吾][尔][自][治][区])|([广][西][壮][族][自][治][区])|([宁][夏][回][族][自][治][区])|([西][藏][自][治][区]))/g.test(applierAddress);
			//var x=/([广][西])/g.test(applierAddress);
			if (!x) {
				IMessageBox.error("请检查申请人地址，必须出现省市县（区）三级！");
				return false;
			}
			return true;
		} else {
			return false
		}

	}
});
///"省"|"上海市"|"天津市"|"重庆市"|"内蒙古"|"广西"|"宁夏"|"新疆"|"西藏"|"内蒙古自治区"|"新疆维吾尔自治区"|" 广西壮族自治区"|"宁夏回族自治区"|"西藏自治区"/g


com.gongsibao.igirl.tm.web.DownloadAttachmentDetailPart = org.netsharp.panda.commerce.DetailPart.Extends({
	ctor: function () {
		this.base();

	},
	saveP: function () {
		this.parent.save();
	},

});

com.gongsibao.igirl.tm.web.UploadAttachmentDetailPart = org.netsharp.panda.commerce.DetailPart.Extends({
	ctor: function () {
		this.base();

	},
	saveP: function () {
		this.parent.save();
	},
	attachmentMake: function () {

		IMessageBox.confirm("确认要生成新的附件记录吗？", function (istrue) {
			if (istrue) {
				var ent = controllertradeMarkCase.viewModel.currentItem;
				if (ent && ent.id) {
					controllertradeMarkCase.invokeService("attachmentMake", [ent.id], function (d) {
						if (d == 0) {
							IMessageBox.toast("生成附件成功，您可以去上传附件了！");
							controllertradeMarkCase.reload();
						} else {
							IMessageBox.toast("生成附件失败！");
						}

					});
				}
			}
		});

	},

});
com.gongsibao.igirl.tm.web.TradeMarkDetailPart = org.netsharp.panda.commerce.DetailPart.Extends({
	tmFlag: "",//商标选项打开时状态 add or update
	ctor: function () {
		this.base();
		tmFlag="";
	},
	copy: function () {
		var row = controllertradeMarks.datagrid.datagrid('getSelected');
		var newRow = $.extend({}, row)
		if (newRow != null) {
			delete newRow.id;
			delete newRow.proxyCode;
			controllertradeMarks.datagrid.datagrid('appendRow', newRow);
		} else {
			IMessageBox.warn("请选择要复制的行！");
		}
	},
	saveP: function () {
		this.parent.save();
	},
	attachmentMake: function () {
		IMessageBox.confirm("确认要生成新的附件记录吗？", function (istrue) {
			if (istrue) {
				var ent = controllertradeMarkCase.viewModel.currentItem;
				if (ent && ent.id) {
					controllertradeMarkCase.invokeService("attachmentMake", [ent.id], function (d) {
						if (d == 0) {
							IMessageBox.toast("生成附件成功，您可以去上传附件了！");
							controllertradeMarkCase.reload();
						} else {
							IMessageBox.toast("生成附件失败！");
						}

					});
				}
			}
		});

	},
	addAfter: function () {
		this.base();
		if (controllertradeMarks.viewModel.currentItem) {
			if (!controllertradeMarks.viewModel.currentItem.id) {
				var rows = controllertradeMarks.datagrid.datagrid('getRows');
				if (rows && rows.length > 0) {
					var lastIndex = rows.length - 1;
					var memo = rows[lastIndex].memo;
					$("#memo").val(memo);
				}
			}
		}
		tmFlag="add";
	},
	addBefore: function () {
		//检查当前案件
//		var rows= controllertradeMarks.datagrid.datagrid('getRows');
//		if(rows && rows.length>0){
//		var lastIndex=rows.length-1;
//		var memo =rows[lastIndex].memo;
//		alert(memo)
//		$("#memo").val(memo)
//		}
	},
	saveBefore: function (entity) {
		tmFlag="";
		var g = $('#nclOne_name').combogrid('grid');	// get datagrid object
		var r = g.datagrid('getSelected');	// get the selected row

		entity.nclOne.code = r.code;

		entity.cost = $("#cost").parent().find("span > input").eq(0).val();
	},
	editBefore:function(){
		this.viewModel.clear();
		tmFlag="update";
	},
	checkMemo: function (value) {
		var momo=$("#memo").val();
		momo=momo.replace(/(^\s*)|(\s*$)/g,"")
		var result=momo.match(/^(\s*[·，、。！,.\-@!+$￥\u4e00-\u9fa5_a-zA-Z0-9])*$/); 
		if(result==null){
			IMessageBox.error("【商标说明】只能输入中文、英文、数字和常用标点符号!");
			$("#memo").val("");
		}else{
			$("#memo").val(momo);
		}
	},
	nclOneChange: function (newValue, oldValue) {
		$("#selectedTwoStr").val("");
		$("#spandiv").remove();
//		if($('#ncltwogrid')){
//			$('#ncltwogrid').datagrid('clearSelections');
//		}
		//$("#selectedTwoStr").hide();
		function DeleteFromArrayByFilter(attrs, field, fieldvalue, filter) {
			var index = -1;
			for (var i = 0; i < attrs.length; i++) {
				if (filter(attrs[i], field, fieldvalue)) {
					index = i;
					break;

				}
			}
			if (index != -1) {
				attrs.splice(index, 1)
			}


		}

		//更新选择的小类文本字符串,加载时需要解析
		function updateSelectTxtVal() {
			var tmpArray = [];
			var i = 1;
			selectedItems.forEach(function (row) {
				tmpArray.push(i + ":" + row.name + ":" + row.code + ":" + row.id);
				i++;
			})
			var rtnStr = tmpArray.join("\n")
			$("#selectedTwoStr").val(rtnStr)
			showInSpan();
		}
		

		function push(arr, obj) {
			var exist = false;
			arr.forEach(function (value) {
				if (value.id == obj.id) {
					exist = true;
					return false;
				}
			})
			if (!exist) {
				arr.push(obj)
			}

		}

		function backSelect() {
			var selectStr = $("#selectedTwoStr").val();
			if (selectStr) {
				selectStrs = selectStr.split('\n');
				selectStrs.forEach(function (str) {
					var index = str.lastIndexOf(":");
					var id = str.substring(index + 1);
					// console.log(id)
					$("#ncltwogrid").datagrid('getRows').forEach(function (row, i) {
						if (row.id == id) {
							$("#ncltwogrid").datagrid('checkRow', i)
						}

					});
				});
			}

		}
		function showInSpan(){
			$("#spandiv").remove();
			var $t = $("#selectedTwoStr");
			$t.hide();
			$("<div id='spandiv'></div>").insertAfter($t);
			var $t2 = $("#spandiv");
			var selectStr = $t.val();
			if (selectStr) {
				selectStrs = selectStr.split('\n');
				selectStrs.forEach(function (str) {
					var index = str.lastIndexOf(":");
					var id = "span_"+str.substring(index + 1);
					$t2.append("<i class='fa fa-window-close-o' style='display:block;'>&nbsp<span id="+id+" name = 'selected_span' style ='font:400 13.3333px Arial;color: black;'>"+str+"</span></i>");
				});
			}
			$("[name='selected_span']").dblclick(function(){
				var id =this.id.split("span_")[1];
				var rowIndex = $("[value="+id+"]:checkbox").parent().parent().parent().attr("datagrid-row-index");
				$('#ncltwogrid').datagrid("unselectRow",rowIndex); 
				this.remove();
			});
		}
		

		// 计算成本
		function countCost() {
			var c = $("#cost").parent().find("span > input").eq(0);

			var itemCount = (selectedItems || []).length;

			var cost = 0;
			if(itemCount <= 10) {
				cost = 300;
			} else {
				cost = 300 + (Number(itemCount) - 10) * 30;
			}
			c.val(cost);
		}

		if ($("#ncltwogrid").length <= 0) {
			var $t = $("[name='nclOne_name']");
			$("<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><input type='button' id='cancleAllSelect'  value='取消全部' width='30px'/>").insertAfter($t.parent());
			$("<input id='filterinput' width='30px'/>").insertAfter($t.parent());
			$("<table id='ncltwogrid' style='margin-top:5px;padding-top:3px;'></table>").insertBefore("#selectedTwoStr");
			$("#cancleAllSelect").on("click", function () {
				IMessageBox.confirm("确定要取消全部小类吗？",function(istrue){
					if (istrue) {
						$('#ncltwogrid').datagrid('clearSelections');
					}
				});
				
//				$('#ncltwogrid').datagrid('clearSelections');
			});
		}
		var selectedItems = [];
		var rtnData = null;
		//首次加载需要按照大类id获取所有的小类列表数据，按照selectedTwoStr去初始化selectedItems
		//去选择grid,调用selectRecord --idValue
		var me = this;

		if (newValue != -1) {
			this.invokeService("findSubsByNclOneId", [newValue], function (data) {
				//me.reload();
				//IMessageBox.toast(JSON.stringify(data));
				rtnData = data;
				$('#ncltwogrid').datagrid({
					width: 450,
					height: 100,
					singleSelect: false,
					checkOnSelect: true,
					selectOnCheck: true,
					columns: [[
						{field: 'id', title: 'id', width: 60, checkbox: true},
						{field: 'code', title: '二级编码', width: 60},
						{field: 'thirdCode', title: '小类编码', width: 100},
						{field: 'name', title: '内容', width: 120},
						]],
						data: rtnData,
						onCheck: function (rindex, rowData) {
							push(selectedItems, rowData)
							updateSelectTxtVal();
							countCost();
						},
						onUncheck: function (rindex, rowData) {
							DeleteFromArrayByFilter(selectedItems, "id", rowData.id, function (row, fd, fv) {
								if (row[fd] == fv)
									return true
									else
										return false
							})
							updateSelectTxtVal();
							countCost();
						},
						onCheckAll: function (rows) {
							rows.forEach(function (value) {
								push(selectedItems, value);
							})
							updateSelectTxtVal();
							countCost();
						},
						onUncheckAll: function (rows) {
							rows.forEach(function (rowData) {
								DeleteFromArrayByFilter(selectedItems, "id", rowData.id, function (row, fd, fv) {
									if (row[fd] == fv)
										return true
										else
											return false
								})
							})
							updateSelectTxtVal();
							countCost();
						}
				});

				var selfme=me;
				me.invokeService("getTmplByNclOneId", [newValue], function(data) {
					if($.trim($("#selectedTwoStr").val())=="" && data!=""){
						$("#selectedTwoStr").val(data);
					}
					showInSpan();
					backSelect();
				});


			});

		}
		var tmpDatas = [];
		$("#filterinput").on("input", function (e) {
			tmpDatas.length = 0;
			var currentVal = $(e.currentTarget).val();
			if (rtnData) {
				rtnData.forEach(function (obj, index) {
					if (obj.code.indexOf(currentVal) >= 0 || obj.name.indexOf(currentVal) >= 0 || obj.thirdCode.indexOf(currentVal) >= 0) {
						tmpDatas.push(obj);
					}
				})
			}
			setTimeout(function () {
				$('#ncltwogrid').datagrid({
					data: tmpDatas
				});
				//根据文本框内的值反选择grid
				showInSpan();
				backSelect();
			}, 1000)
		});


	}
});
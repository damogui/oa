org.netsharp.we.core.franchiseeFormCtrl = org.netsharp.we.core.formCtrl
		.Extends({
			ctor : function() {
				this.base();
				this.service = 'com.gongsibao.franchisee.web.FranchiseeController';
			},
			byId : function() {

				document.title = '修改客户信息';
				var me = this;
				var pars = [ this.id ];
				this.invokeService('getFranchiseeById', pars, function(result) {

					me.context.entity = result;
					me.bindData(result);
				});
			},
			newInstance : function() {

				document.title = '添加客户信息';
				var me = this;
				this.invokeService('getNewTrack', [], function(result) {

					me.context.entity = result;
					me.bindData(result);
				});
			},
			bindData : function(entity) {

				$("#city-picker").cityPicker({
					title : "选择地区"
				});

				var items = [];
				$(businessScopeDictionary.innerValues).each(function(i, item) {

					var obj = {};
					obj.title = item.value;
					obj.value = item.key;
					items.push(obj);
				});

				$("#scopes").select({
					title : "选择经营范围",
					multi : true,
					items : items
				});

				if (entity) {

					$('#name').val(entity.name);
					$('#legalPerson').val(entity.legalPerson);
					$('#employeeCount').val(entity.employeeCount);
					$('#annualIncome').val(entity.annualIncome);
					$('#registerAddress').val(entity.registerAddress);
					$('#workdAddress').val(entity.workdAddress);
					$('#memoto').val(entity.memoto);

					if (entity.provinceId && entity.cityId && entity.countyId) {

						var codes = entity.provinceId + ',' + entity.cityId
								+ ',' + entity.countyId;
						var names = entity.province.name + ' '
								+ entity.city.name + ' ' + entity.county.name;
						$('#city-picker').val(names);
						$('#city-picker').attr('data-codes', codes);
					}

					if (entity.scopes && entity.scopes.length > 0) {

						var names = [], ids = [];
						$(entity.scopes).each(
								function(i, item) {

									var name = businessScopeDictionary
											.byKey(item.scope);
									if (name) {

										names.push(name);
										ids.push(item.scope);
									}
								});

						var texts = names.join();
						var values = ids.join();
						$("#scopes").val(texts);
						$("#scopes").attr('data-values', values);
					}

					$('#linkmanName').val(entity.linkmanName);
					$('#mobile').val(entity.mobile);
					$('#post').val(entity.post);
					$('#weixin').val(entity.weixin);
					$('#qq').val(entity.qq);
					$('#tel').val(entity.tel);

					if (entity.owner != null) {

						$('#owner').text(entity.owner.name);
					}

					if (entity.department != null) {

						$('#department').text(entity.department.name);
					}
				}
			},
			getEntity : function(entity) {

				// 业务员
				var employeeId = this.queryString('employeeId');
				if (employeeId) {

					entity.ownerId = employeeId;
				}

				entity.name = $('#name').val();
				entity.legalPerson = $('#legalPerson').val();
				entity.employeeCount = $('#employeeCount').val();
				entity.annualIncome = $('#annualIncome').val();
				entity.registerAddress = $('#registerAddress').val();
				entity.workdAddress = $('#workdAddress').val();

				var pccids = $('#city-picker').attr('data-codes');

				if (pccids) {

					var ss = pccids.split(',');
					entity.provinceId = ss[0];
					entity.cityId = ss[1];
					
					if(ss.length==3){

						entity.countyId = ss[2];
					}
				}

				entity.memoto = $('#memoto').val();
				entity.linkmanName = $('#linkmanName').val();
				entity.mobile = $('#mobile').val();
				entity.post = $('#post').val();
				entity.weixin = $('#weixin').val();
				entity.qq = $('#qq').val();
				entity.tel = $('#tel').val();
				return entity;
			},
			validate : function() {

				var name = $('#name').val();
				var pccids = $('#city-picker').attr('data-codes');
				var mobile = $('#mobile').val();
				var weixin = $('#weixin').val();
				var qq = $('#qq').val();
				var tel = $('#tel').val();

				if (System.isnull(name)) {

					$.toptip('请输入公司名称');
					return false;
				}

				if (System.isnull(pccids)) {

					$.toptip('请选择地区');
					return false;
				}

				if (mobile != ""
						&& !/^0?(13[0-9]|15[012356789]|18[0123456789]|14[57]|17[01367])[0-9]{8}$/
								.test(mobile)) {

					$.toptip('手机号格式错误');
					return false;
				}

				if (System.isnull(mobile) && System.isnull(weixin)
						&& System.isnull(qq) && System.isnull(tel)) {

					$.toptip('联系方式最少填写一项');
					return false;
				}

				return true;
			},
			save : function() {

				var isValidate = this.validate();
				if (!isValidate) {
					return;
				}

				var me = this;
				var entity = this.getEntity(this.context.entity);
				this.invokeService('saveFranchisee', [ entity ], function(
						result) {

					$.toptip('提交成功', 'success');
					setTimeout(function() {
						var employeeId = me.queryString('employeeId');
						window.location.href = 'franchiseeList?employeeId='
								+ entity.ownerId;
					}, 1000);
				});
			}
		});
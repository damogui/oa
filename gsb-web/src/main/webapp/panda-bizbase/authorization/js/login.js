System.Declare("org.netsharp.core");

org.netsharp.core.LoginController = System.Object.Extends({
	ctor : function() {
		this.jServiceLocator = null;
		this.service = "org.netsharp.organization.controller.LoginController";
	},
	invoke : function(month, pars, callback) {
		if (this.jServiceLocator == null) {
			this.jServiceLocator = new org.netsharp.core.JServiceLocator();
		}
		this.jServiceLocator.invoke(this.service, month, pars, callback);
	},
	init : function() {
		this.setShortcutKey();
		this.setCookies();
	},
	setCookies : function() {
		var c = System.Cookies.get("netsharp_login");
		if (c != null) {
			var j = null;
			eval("j=" + c);
			if (j.username) {
				$("#username").val(j.username);
			}
			if (j.password) {
				$("#password").val(j.password);
			}
			$("#remember").prop("checked", true);
		}
	},

	setShortcutKey : function() {
		
		var me = this;
		$("#username,#password,#remember").keydown(function(e) {
			if (e.keyCode == 13) {
				me.login();
			}
		});
	},

	login : function() {
		
		var username = $("#username").val();
		var password = $("#password").val();
		if (username == "") {
			
			IMessageBox.error("账号不能为空！", function(){
				$("#username").focus();
			});
			return;
		}
		
		if (password == "") {
			
			IMessageBox.error("密码不能为空！", function(){
				$("#password").focus();
			});
			return;
		}

		var mpassword = $.md5(password + "user!@#123").substring(8,24);
		var pars = [];
		pars.push(username);
		pars.push(mpassword);
		var me=this;
		this.invoke("login", pars, function(message) {
			
			if (message.result == 1 || message.result == 3) {
				
				var isRemember = $("#remember").prop('checked');
				if (isRemember == true) {
					var c = "{username:'" + username + "',password:'" + password + "'}";
					System.Cookies.set("netsharp_login", c, 100);
				}
				
				//设置ticket
				System.Cookies.set("COOKIE_ER_LOGIN_TICKET", message.data, 100);
				
				//这种方法不行
				//System.Cookies.set("COOKIE_ER_LOGIN_TICKET", 'b81a9201-b17f-4f40-aac8-924561a3cdfd', 100);
				
				window.location.href = "/panda/workbench";
			} else if (message.result == 2) {
				
				IMessageBox.error("您的帐号已经冻结，请联系管理员！");
				
			} else {
				
				IMessageBox.error("您的用户名或密码错误！");
			}
		});
	}
});
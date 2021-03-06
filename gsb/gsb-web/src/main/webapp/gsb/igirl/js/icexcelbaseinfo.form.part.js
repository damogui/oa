System.Declare("com.gongsibao.igirl.ic.web");
com.gongsibao.igirl.ic.web.IcExcelBaseInfoPart = org.netsharp.panda.commerce.FormPart.Extends({
    ctor: function () {
        this.base();
    },
    isTel: function (el) {
        var me = this;
        var customerMobile = $(el).val();
        var myreg = /^[1][3,4,5,7,8][0-9]{9}$/;
        if (!myreg.test(customerMobile)) {
            IMessageBox.error("【手机】格式错误");
            $("#customerMobile").val("");
            return false;
        }
        me.invokeService("findByMobile",[customerMobile],function (customer) {
            if(customer!=null){
                $("#customerName").val(customer.realName).attr('readonly',true);
                //选取对应id的下拉框选项值
                var data= $("#source").combobox("getData");
                for (var i = 0; i < data.length; i++){
                    if(data[i].value==customer.customerSourceId){
                        $("#source").combobox("setValue",customer.customerSourceId);
                        break;
                    }
                }
                var dataway= $("#consultWay").combobox("getData");
                for (var i = 0; i < dataway.length; i++){
                    if(dataway[i].value==customer.consultWay){
                        $("#consultWay").combobox("setValue",customer.consultWay);
                        break;
                    }
                }
            }
        });
        return true;
    }
});

com.gongsibao.igirl.ic.web.CompanyNameDetailPart = org.netsharp.panda.commerce.DetailPart.Extends({
    ctor: function () {
        this.base();
    },
    entTraChange:function (entTra) {
        var str = $(entTra).val();
        var me = this;
        me.invokeService("checkEntTra",[str],function (result) {
            if(result.length>0){
                IMessageBox.error(result);
                $(entTra).val("");
                return false;
            }
        });
        return true;
    }
});

com.gongsibao.igirl.ic.web.MemberDetailPart = org.netsharp.panda.commerce.DetailPart.Extends({
    ctor: function () {
        this.base();
    },
    isTel:function (el) {
        var me = this;
        var mobile = $(el).val();
        var myreg = /^[1][3,4,5,7,8][0-9]{9}$/;
        if (!myreg.test(mobile)) {
            IMessageBox.error("【手机】格式错误");
            $(el).val("");
            return false;
        }
        me.invokeService("byMobile",[mobile],function (member) {
            if(member!=null){
                $("#name").val(member.name);
                $("#email").val(member.email);
                $("#identify").val(member.identify);
                $("#telephone").val(member.telephone);
                $("#education").combobox("setValue",member.education);
                $("#address").val(member.address);
                $("#idAddress").val(member.idAddress);
            }
        });
        return true;
    },
    isIdentify:function (el) {
        var identify = $(el).val();
        isIdCardNo(identify);
    }
});

function isIdCardNo(num) {
    num = num.toUpperCase();
    //身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X。
    if (!(/(^\d{15}$)|(^\d{17}([0-9]|X)$)/.test(num))) {
        IMessageBox.error("输入的身份证号长度不对，或者号码不符合规定！\n15位号码应全为数字，18位号码末位可以为数字或X。");
        return false;
    }
    //校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。
    //下面分别分析出生日期和校验位
    var len, re;
    len = num.length;
    if (len == 15) {
        re = new RegExp(/^(\d{6})(\d{2})(\d{2})(\d{2})(\d{3})$/);
        var arrSplit = num.match(re);
        //检查生日日期是否正确
        var dtmBirth = new Date('19' + arrSplit[2] + '/' + arrSplit[3] + '/' + arrSplit[4]);
        var bGoodDay;
        bGoodDay = (dtmBirth.getYear() == Number(arrSplit[2]))
            && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3]))
            && (dtmBirth.getDate() == Number(arrSplit[4]));
        if (!bGoodDay) {
            IMessageBox.error("输入的身份证号里出生日期不对！");
            return false;
        } else {
            //将15位身份证转成18位
            //校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。
            var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
            var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
            var nTemp = 0, i;
            num = num.substr(0, 6) + '19' + num.substr(6, num.length - 6);
            for (i = 0; i < 17; i++) {
                nTemp += num.substr(i, 1) * arrInt[i];
            }
            num += arrCh[nTemp % 11];
            return num;
        }
    }
    if (len == 18) {
        re = new RegExp(/^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/);
        var arrSplit = num.match(re);
        //检查生日日期是否正确
        var dtmBirth = new Date(arrSplit[2] + "/" + arrSplit[3] + "/" + arrSplit[4]);
        var bGoodDay;
        bGoodDay = (dtmBirth.getFullYear() == Number(arrSplit[2]))
            && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3]))
            && (dtmBirth.getDate() == Number(arrSplit[4]));
        if (!bGoodDay) {
            alert(dtmBirth.getYear());
            alert(arrSplit[2]);
            IMessageBox.error("输入的身份证号里出生日期不对！");
            return false;
        } else {
            //检验18位身份证的校验码是否正确。
            //校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。
            var valnum;
            var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
            var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
            var nTemp = 0, i;
            for (i = 0; i < 17; i++) {
                nTemp += num.substr(i, 1) * arrInt[i];
            }
            valnum = arrCh[nTemp % 11];
            if (valnum != num.substr(17, 1)) {
                IMessageBox.error("18位身份证的校验码不正确！应该为："+valnum);
                return false;
            }
            return num;
        }
    }
    return false;
}

com.gongsibao.igirl.ic.web.ShareholderDetailPart = org.netsharp.panda.commerce.DetailPart.Extends({
    ctor: function () {
        this.base();
    },
    isTel:function (el) {
        var me = this;
        var mobile = $(el).val();
        var myreg = /^[1][3,4,5,7,8][0-9]{9}$/;
        if (!myreg.test(mobile)) {
            IMessageBox.error("【手机】格式错误");
            $(el).val("");
            return false;
        }
        me.invokeService("byMobile",[mobile],function (member) {
            if(member!=null){
                $("#memberName").val(member.name);
            }else{
                IMessageBox.error("当前成员不存在")
                $(el).val("");
                $("#memberName").val("");
                return false;
            }
        });
        return true;
    }
});


com.gongsibao.igirl.ic.web.WorkerDetailPart = org.netsharp.panda.commerce.DetailPart.Extends({
    ctor: function () {
        this.base();
    },
    isTel:function (el) {
        var me = this;
        var mobile = $(el).val();
        var myreg = /^[1][3,4,5,7,8][0-9]{9}$/;
        if (!myreg.test(mobile)) {
            IMessageBox.error("【手机】格式错误");
            $(el).val("");
            return false;
        }
        me.invokeService("byMobile",[mobile],function (member) {
            if(member!=null){
                $("#memberName").val(member.name);
            }else{
                IMessageBox.error("当前成员不存在")
                $(el).val("");
                $("#memberName").val("");
                return false;
            }
        });
        return true;
    }
});

com.gongsibao.igirl.ic.web.CorporateAddressDetailPart = org.netsharp.panda.commerce.DetailPart.Extends({
    ctor: function () {
        this.base();
        $("fieldset:contains('供地')").hide();
    },
    ownLandTypeChange:function (newValue,oldValue) {
        if(newValue==0){
            $("fieldset:contains('供地')").hide();
            $("fieldset:contains('自有地')").show();
        }else{
            $("fieldset:contains('供地')").show();
            $("fieldset:contains('自有地')").hide()
        }
    }
});

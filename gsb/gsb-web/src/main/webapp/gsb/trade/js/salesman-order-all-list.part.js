System.Declare("com.gongsibao.trade.web");
//所有订单
com.gongsibao.trade.web.SalesmanAllOrderListPart = org.netsharp.panda.commerce.ListPart.Extends({
    ctor: function () {
        this.base();

        this.addOrderReceivedUrl = '/panda/crm/order/salesman/coperformance';
        this.addReceived = '/panda/crm/order/salesman/coperformance';//回款业绩


    },
    addOrderReceived: function () {//创建订单业绩
        var me = this;
        var row = this.getSelectedItem();
        var rows = this.getSelections();
        if (rows.length <= 0) {
            IMessageBox.info('请先选择订单数据');
            return false;
        }

        // var builder = new System.StringBuilder();
        // builder.append('<div style="margin:10px;">');
        // builder.append('	<table cellpadding="5" cellspacing="10" class="query-panel">');
        // builder.append(' 		<tr><td class="title">创建订单业绩</td><td><input id="ywyUser"/></td></tr>');
        // builder.append('	</table>');
        // builder.append('</div>');

        layer.open({
            type: 2,//1是字符串 2是内容
            title: '订单信息',
            fixed: false,
            maxmin: true,
            shadeClose: false,
            area: ['60%', '60%'],
            zIndex: 100000,
            content: this.addOrderReceivedUrl + "?id=" + row.id,
            btn: ['保存', '取消'],// 可以无限个按钮
            success: function (layero, index) {


                // $('#ywyUser').combogrid(options);

            },
            yes: function (index, layero) {

                IMessageBox.toast('创建成功');

                me.invokeService("BatchTransferSalesman", [ywyUserId, orderIdList], function () {
                    me.reload();
                    IMessageBox.toast('创建成功');
                    layer.closeAll();
                    return;
                });


            },

            btn2: function (index, layero) {
            }
        });
    },
    addReceived: function (id) {//创建回款业绩

        this.edit(id);
    },
    addRefund: function (id) {//创建退款

        this.edit(id);
    },
    addCarryover: function (id) {//创建结转

        this.edit(id);
    },
    addStaging: function (id) {//创建分期

        this.edit(id);
    },
    addContract: function (id) {//创建合同

        this.edit(id);
    },
    addInvoice: function (id) {//申请发票

        this.edit(id);
    },
    batchOrderTran: function (id) {//批量订单转移
        //this.edit(id);

        var me = this;

        var rows = this.getSelections();
        if (rows.length <= 0) {
            IMessageBox.info('请先选择订单数据');
            return false;
        }

        var orderIdList = [];
        $.each(rows, function (k, v) {
            orderIdList.push(v.id);
        });

        //公共控件集合
        var pubControlList = new org.netsharp.controls.PubControlList();
        PandaHelper.openDynamicForm({
            title: '订单转移',
            width: 450,
            height: 300,
            items: [
                pubControlList.getSupplierCombogrid(),
                pubControlList.getDepartmentCombogrid(),
                pubControlList.getEmployeeCombogrid()
            ],
            callback: function (index, layero) {

                var supplierId = $('#supplier_name').combogrid('getValue');
                var departmentId = $('#department_name').combogrid('getValue');
                var toUserId = $('#employee_name').combogrid('getValue');

                if (System.isnull(toUserId)) {
                    IMessageBox.info('请选择一个业务员');
                    return;
                }

                alert("分配成功");


                /*me.invokeService("allocation", [taskId,supplierId,departmentId,toUserId],function(data) {
                 me.reload();
                 IMessageBox.toast('分配成功');
                 layer.closeAll();
                 return;
                 });*/
            }
        });

    },
    orderTran: function (id) {//订单转移
        //this.edit(id);
    },
    begOption: function (id) {//开始操作

        this.edit(id);
    },
    detail: function (id) {

        var url = '/nav/gsb/trade/orderDetail?id=' + id;
        window.open('/nav/gsb/trade/orderDetail');
    },
    doubleClickRow: function (index, row) {
        this.detail(row.id);
    },
    // edit : function(id) {

    batchAllocation: function () {
        //任务批量分配
        var me = this;
        var row = this.getSelectedItem();
        var id = this.getSelectionIds();
        if (id == "" || id == null) {
            IMessageBox.info('请选择记录');
            return;
        }
        me.doAllot(id);
    },

    batchTransfer: function () {
        //任务批量转移
        var me = this;
        var id = this.getSelectionIds();
        if (id == "" || id == null) {
            IMessageBox.info('请选择记录');
            return;
        }
        me.doTransfer(id);
    },
    transfer: function (id) {
        //任务转移
        var me = this;
        var row = this.getSelectedItem();
        if (row == null) {
            IMessageBox.info('请选择记录');
            return;
        }
        me.doTransfer(id);
    }


});




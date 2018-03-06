package com.gongsibao.panda.supplier.order;

import com.gongsibao.entity.trade.Contract;
import com.gongsibao.trade.base.IContractService;
import com.gongsibao.trade.base.IOrderService;

import org.junit.Test;
import org.netsharp.meta.base.ResourceCreationBase;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.trade.OrderProd;
import com.gongsibao.entity.trade.OrderProdItem;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.base.IOrderProdService;

public class OrderResourceTest extends ResourceCreationBase {
    @Test
    public void run() {

        ResourceNode node = resourceNodeService.byCode ("Gsb_Supplier_System");
        this.createResourceNodeVouchers (node);
    }

    @Override
    protected void createResourceNodeVouchers(ResourceNode node) {

        ResourceNode node1 = this.createResourceNodeCategory ("订单管理", "Gsb_Supplier_Order", node.getId ());
        {
            node1 = this.createResourceNodeCategory ("我的订单", "Gsb_Supplier_Order_Salesman", node1.getId ());
            {
                this.createResourceNodeVoucher (SoOrder.class.getName (), "创建订单", "Gsb_Supplier_Order_Salesman_Add", IOrderService.class.getName (), node1.getId ());
                this.createResourceNodeVoucher (SoOrder.class.getName (), "全部订单", "Gsb_Supplier_Order_Salesman_All", IOrderService.class.getName (), node1.getId ());
 /*我的订单按钮beg*/
                this.createResourceNodeVoucher (SoOrder.class.getName (), "创建订单业绩", "Gsb_Supplier_Order_Salesman_CoPerformance", IOrderService.class.getName (), node1.getId ());
                this.createResourceNodeVoucher (SoOrder.class.getName (), "创建回款业绩", "Gsb_Supplier_Order_Salesman_CReceivedPerformance", IOrderService.class.getName (), node1.getId ());
                /*我的订单按钮end*/

                this.createResourceNodeVoucher (SoOrder.class.getName (), "订单业绩", "Gsb_Supplier_Order_Salesman_Performance", IOrderService.class.getName (), node1.getId ());
                this.createResourceNodeVoucher (SoOrder.class.getName (), "回款业绩", "Gsb_Supplier_Order_Salesman_Received", IOrderService.class.getName (), node1.getId ());
                this.createResourceNodeVoucher (SoOrder.class.getName (), "退款订单", "Gsb_Supplier_Order_Salesman_Refund", IOrderService.class.getName (), node1.getId ());
                this.createResourceNodeVoucher (SoOrder.class.getName (), "分期订单", "Gsb_Supplier_Order_Salesman_Staging", IOrderService.class.getName (), node1.getId ());
                this.createResourceNodeVoucher (SoOrder.class.getName (), "结转订单", "Gsb_Supplier_Order_Salesman_Carryover", IContractService.class.getName (), node1.getId ());
                this.createResourceNodeVoucher (Contract.class.getName (), "合同管理", "Gsb_Supplier_Order_Salesman_Contract", IOrderService.class.getName (), node1.getId ());
                this.createResourceNodeVoucher (SoOrder.class.getName (), "日统计", "Gsb_Supplier_Order_Salesman_Day_Report", IOrderService.class.getName (), node1.getId ());
                this.createResourceNodeVoucher (SoOrder.class.getName (), "周统计", "Gsb_Supplier_Order_Salesman_Week_Report", IOrderService.class.getName (), node1.getId ());
                this.createResourceNodeVoucher (SoOrder.class.getName (), "月统计", "Gsb_Supplier_Order_Salesman_Month_Report", IOrderService.class.getName (), node1.getId ());

                this.createResourceNodeVoucher (OrderProd.class.getName (), "订单明细", "Gsb_Supplier_Order_Salesman_OrderProd", IOrderProdService.class.getName (), node1.getId ());
                this.createResourceNodeVoucher (OrderProdItem.class.getName (), "服务明细", "Gsb_Supplier_Order_Salesman_OrderProdItem", IOrderProdService.class.getName (), node1.getId ());



            }

            node1 = this.createResourceNodeCategory ("部门订单", "Gsb_Supplier_Order_Department", node1.getId ());
            {
                this.createResourceNodeVoucher (SoOrder.class.getName (), "全部订单", "Gsb_Supplier_Order_Department_All", IOrderService.class.getName (), node1.getId ());
                this.createResourceNodeVoucher (SoOrder.class.getName (), "订单业绩", "Gsb_Supplier_Order_Department_Performance", IOrderService.class.getName (), node1.getId ());
                this.createResourceNodeVoucher (SoOrder.class.getName (), "回款业绩", "Gsb_Supplier_Order_Department_Received", IOrderService.class.getName (), node1.getId ());
                this.createResourceNodeVoucher (SoOrder.class.getName (), "退款订单", "Gsb_Supplier_Order_Department_Refund", IOrderService.class.getName (), node1.getId ());
                this.createResourceNodeVoucher (SoOrder.class.getName (), "日统计", "Gsb_Supplier_Order_Department_Day_Report", IOrderService.class.getName (), node1.getId ());
                this.createResourceNodeVoucher (SoOrder.class.getName (), "周统计", "Gsb_Supplier_Order_Department_Week_Report", IOrderService.class.getName (), node1.getId ());
                this.createResourceNodeVoucher (SoOrder.class.getName (), "月统计", "Gsb_Supplier_Order_Department_Month_Report", IOrderService.class.getName (), node1.getId ());
            }

            node1 = this.createResourceNodeCategory ("订单审核", "Gsb_Supplier_Order_Audit", node1.getId ());
            {
                this.createResourceNodeVoucher (SoOrder.class.getName (), "定价审核", "Gsb_Supplier_Order_Audit_Pricing", IOrderService.class.getName (), node1.getId ());
                this.createResourceNodeVoucher (SoOrder.class.getName (), "退款审核", "Gsb_Supplier_Order_Audit_Refund", IOrderService.class.getName (), node1.getId ());
                this.createResourceNodeVoucher (SoOrder.class.getName (), "分期审核", "Gsb_Supplier_Order_Audit_Staging", IOrderService.class.getName (), node1.getId ());
                this.createResourceNodeVoucher (SoOrder.class.getName (), "结转审核", "Gsb_Supplier_Order_Audit_Carryover", IOrderService.class.getName (), node1.getId ());
            }
        }
    }
}
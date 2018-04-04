package com.gongsibao.entity.igirl.settle;

import com.gongsibao.entity.trade.OrderProd;
import com.gongsibao.entity.trade.SoOrder;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

@Table(name = "ig_settle_order", header = "结算单关联订单")
public class SettleOrder extends Entity {

    @Column(name = "settle_id", header = "结算单id")
    private Integer settleId;

    @Column(name = "order_prod_id", header = "明细订单id")
    private Integer orderProdId;

    @JsonIgnore
    @Reference(foreignKey = "orderProdId", primaryKey = "pkid", header = "明细订单")
    private OrderProd orderProd;

    @Column(name = "order_id", header = "订单id")
    private Integer orderId;

    @JsonIgnore
    @Reference(foreignKey = "orderId", primaryKey = "pkid", header = "订单")
    private SoOrder soOrder;

    public Integer getSettleId() {
        return settleId;
    }

    public void setSettleId(Integer settleId) {
        this.settleId = settleId;
    }

    public Integer getOrderProdId() {
        return orderProdId;
    }

    public void setOrderProdId(Integer orderProdId) {
        this.orderProdId = orderProdId;
    }

    public OrderProd getOrderProd() {
        return orderProd;
    }

    public void setOrderProd(OrderProd orderProd) {
        this.orderProd = orderProd;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public SoOrder getSoOrder() {
        return soOrder;
    }

    public void setSoOrder(SoOrder soOrder) {
        this.soOrder = soOrder;
    }
}
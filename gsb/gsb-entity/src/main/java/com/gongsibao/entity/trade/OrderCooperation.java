package com.gongsibao.entity.trade;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name = "so_order_cooperation", header = "万达订单关联表")
public class OrderCooperation extends BaseEntity {

    private static final long serialVersionUID = -1407501547018300129L;

    @Column(name = "order_id", header = "订单序号")
    private Integer orderId;

    @Column(name = "cooperation_company_id", header = "入驻公司")
    private Integer cooperationCompanyId;

    @Column(name = "branch_id", header = "分支机构id")
    private Integer branch_id;

    @Column(name = "branch_name", header = "分支机构名称")
    private String branch_name;


    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getCooperationCompanyId() {
        return cooperationCompanyId;
    }

    public void setCooperationCompanyId(Integer cooperationCompanyId) {
        this.cooperationCompanyId = cooperationCompanyId;
    }

    public Integer getBranch_id() {
        return branch_id;
    }

    public void setBranch_id(Integer branch_id) {
        this.branch_id = branch_id;
    }

    public String getBranch_name() {
        return branch_name;
    }

    public void setBranch_name(String branch_name) {
        this.branch_name = branch_name;
    }
}

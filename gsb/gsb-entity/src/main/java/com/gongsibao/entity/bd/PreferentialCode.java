package com.gongsibao.entity.bd;

import com.gongsibao.entity.BaseEntity;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;

import java.util.Date;

@Table(name="bd_preferential_code")
public class PreferentialCode extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -7099033472190066232L;
	@Column(name="preferential_id",header="PreferentialId")
    private Integer preferentialId;
	@Reference(foreignKey = "preferentialId")
	private Preferential preferential;
    @Column(header="no")
    private String no;
    @Column(header="status，状态（0：未激活，1：已激活，2：已使用）")
    private Integer status;
    @Column(name="account_id",header="AccountId")
    private Integer accountId;
    @Column(name="order_id",header="OrderId")
    private Integer orderId;
    @Column(name="activate_time",header="ActivateTime")
    private Date activateTime;
    @Column(name="use_time",header="UseTime")
    private Date useTime;
    @Column(name="is_enabled",header="IsEnabled")
    private Integer isEnabled;

    public Integer getPreferentialId() {
        return preferentialId;
    }
    public void setPreferentialId(Integer preferentialId) {
        this.preferentialId = preferentialId;
    }

    public Preferential getPreferential() {
        return preferential;
    }

    public void setPreferential(Preferential preferential) {
        this.preferential = preferential;
    }

    public String getNo() {
        return no;
    }
    public void setNo(String no) {
        this.no = no;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Integer getAccountId() {
        return accountId;
    }
    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }
    public Integer getOrderId() {
        return orderId;
    }
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
    public Date getActivateTime() {
        return activateTime;
    }
    public void setActivateTime(Date activateTime) {
        this.activateTime = activateTime;
    }
    public Date getUseTime() {
        return useTime;
    }
    public void setUseTime(Date useTime) {
        this.useTime = useTime;
    }
    public Integer getIsEnabled() {
        return isEnabled;
    }
    public void setIsEnabled(Integer isEnabled) {
        this.isEnabled = isEnabled;
    }
}
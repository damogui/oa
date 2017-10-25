package com.gongsibao.entity.uc;

import java.sql.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="uc_user1")
public class User1 extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 7958191292392282873L;
	private String passwd;
    private String ticket;
    @Column(name="real_name")
    private String realName;
    private String email;
    private String qq;
    private String weixin;
    @Column(name="mobile_phone")
    private String mobilePhone;
    private Integer sex;
    @Column(name="ability_id")
    private Integer abilityId;
    @Column(name="is_inner")
    private Integer isInner;
    @Column(name="head_thumb_file_id")
    private Integer headThumbFileId;
    @Column(name="user_type_id")
    private Integer userTypeId;
    @Column(name="is_enabled")
    private Integer isEnabled;
    @Column(name="add_time")
    private Date addTime;
    @Column(name="is_bbk")
    private String isBbk;
    @Column(name="add_user_id")
    private Integer addUserId;
    @Column(name="is_accept_order")
    private Integer isAcceptOrder;
    private String remark;
    @Column(name="supply_status")
    private Integer supplyStatus;
    @Column(name="supply_reject_reason")
    private String supplyRejectReason;

    public String getPasswd() {
        return passwd;
    }
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
    public String getTicket() {
        return ticket;
    }
    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
    public String getRealName() {
        return realName;
    }
    public void setRealName(String realName) {
        this.realName = realName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getQq() {
        return qq;
    }
    public void setQq(String qq) {
        this.qq = qq;
    }
    public String getWeixin() {
        return weixin;
    }
    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }
    public String getMobilePhone() {
        return mobilePhone;
    }
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }
    public Integer getSex() {
        return sex;
    }
    public void setSex(Integer sex) {
        this.sex = sex;
    }
    public Integer getAbilityId() {
        return abilityId;
    }
    public void setAbilityId(Integer abilityId) {
        this.abilityId = abilityId;
    }
    public Integer getIsInner() {
        return isInner;
    }
    public void setIsInner(Integer isInner) {
        this.isInner = isInner;
    }
    public Integer getHeadThumbFileId() {
        return headThumbFileId;
    }
    public void setHeadThumbFileId(Integer headThumbFileId) {
        this.headThumbFileId = headThumbFileId;
    }
    public Integer getUserTypeId() {
        return userTypeId;
    }
    public void setUserTypeId(Integer userTypeId) {
        this.userTypeId = userTypeId;
    }
    public Integer getIsEnabled() {
        return isEnabled;
    }
    public void setIsEnabled(Integer isEnabled) {
        this.isEnabled = isEnabled;
    }
    public Date getAddTime() {
        return addTime;
    }
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
    public String getIsBbk() {
        return isBbk;
    }
    public void setIsBbk(String isBbk) {
        this.isBbk = isBbk;
    }
    public Integer getAddUserId() {
        return addUserId;
    }
    public void setAddUserId(Integer addUserId) {
        this.addUserId = addUserId;
    }
    public Integer getIsAcceptOrder() {
        return isAcceptOrder;
    }
    public void setIsAcceptOrder(Integer isAcceptOrder) {
        this.isAcceptOrder = isAcceptOrder;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Integer getSupplyStatus() {
        return supplyStatus;
    }
    public void setSupplyStatus(Integer supplyStatus) {
        this.supplyStatus = supplyStatus;
    }
    public String getSupplyRejectReason() {
        return supplyRejectReason;
    }
    public void setSupplyRejectReason(String supplyRejectReason) {
        this.supplyRejectReason = supplyRejectReason;
    }
}
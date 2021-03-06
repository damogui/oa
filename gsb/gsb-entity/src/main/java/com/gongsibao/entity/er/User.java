package com.gongsibao.entity.er;


import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="er_user")
public class User extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 5766892072256976179L;
	@Column(name="name",header="name")
    private String name;
    @Column(name="mobile",header="mobile")
    private String mobile;
    @Column(name="email",header="email")
    private String email;
    @Column(name="passwd",header="passwd")
    private String passwd;
    @Column(name="ticket",header="ticket")
    private String ticket;
    @Column(name="avatar",header="avatar")
    private String avatar;
    @Column(name="ding_qr_code_url",header="DingQrCodeUrl")
    private String dingQrCodeUrl;
    @Column(name="role",header="role")
    private String role;
    @Column(name="is_inside",header="IsInside")
    private Integer isInside;
    @Column(name="is_outside",header="IsOutside")
    private Integer isOutside;

    @Column(name="tenant_id",header="TenantId")
    private Integer tenantId;
    @Column(name="uc_user_id",header="UcUserId")
    private Integer ucUserId;
    @Column(name="is_enabled",header="IsEnabled")
    private Integer isEnabled;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
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
    public String getAvatar() {
        return avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public String getDingQrCodeUrl() {
        return dingQrCodeUrl;
    }
    public void setDingQrCodeUrl(String dingQrCodeUrl) {
        this.dingQrCodeUrl = dingQrCodeUrl;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public Integer getIsInside() {
        return isInside;
    }
    public void setIsInside(Integer isInside) {
        this.isInside = isInside;
    }
    public Integer getIsOutside() {
        return isOutside;
    }
    public void setIsOutside(Integer isOutside) {
        this.isOutside = isOutside;
    }


    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }
    public Integer getUcUserId() {
        return ucUserId;
    }
    public void setUcUserId(Integer ucUserId) {
        this.ucUserId = ucUserId;
    }
    public Integer getIsEnabled() {
        return isEnabled;
    }
    public void setIsEnabled(Integer isEnabled) {
        this.isEnabled = isEnabled;
    }
}
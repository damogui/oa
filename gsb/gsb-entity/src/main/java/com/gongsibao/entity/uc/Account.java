package com.gongsibao.entity.uc;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;
import com.gongsibao.entity.bd.Dict;

@Table(name="uc_account")
public class Account extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 2827881401134957483L;
	
	@Column(name="name",header="名称")
	private String name;
	
	@Column(name="passwd",header="密码")
    private String passwd;
    
	@Column(name="ticket",header="ticket")
    private String ticket;
    
	@Column(name="email",header="邮箱")
    private String email;
    
    @Column(name="mobile_phone",header="手机号")
    private String mobilePhone;
    
    @Column(name="telephone",header="座机号")
    private String telephone;
    
    @Column(name="head_thumb_file_id",header="头像Id")
    private Integer headThumbFileId;
    
    @Column(name="real_name",header="姓名")
    private String realName;
    
    @Column(name="source_client_id",header="注册客户端序号，type=6")
    private Integer sourceClientId;
    
	@Reference(foreignKey="sourceClientId",header="注册客户端")
	private Dict sourceClient;

    @Column(name="is_bbk",header="是否八百客")
    private String isBbk="0";
    
    @Column(name="identity_card",header="身份证号码")
    private String identityCard;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Integer getHeadThumbFileId() {
		return headThumbFileId;
	}

	public void setHeadThumbFileId(Integer headThumbFileId) {
		this.headThumbFileId = headThumbFileId;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Integer getSourceClientId() {
		return sourceClientId;
	}

	public void setSourceClientId(Integer sourceClientId) {
		this.sourceClientId = sourceClientId;
	}

	public Dict getSourceClient() {
		return sourceClient;
	}

	public void setSourceClient(Dict sourceClient) {
		this.sourceClient = sourceClient;
	}

	public String getIsBbk() {
		return isBbk;
	}

	public void setIsBbk(String isBbk) {
		this.isBbk = isBbk;
	}

	public String getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}
}
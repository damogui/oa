package com.gongsibao.entity.uc;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name = "uc_account_cooperation", header = "��Ա��פ��˾������")
public class AccountCooperation extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "account_id", header = "�û�id")
	private Integer accountId;
	@Column(name = "cooperation_company_id", header = "�ϻ﹫˾id")
	private Integer cooperationCompanyId;
	@Column(name = "branch_id", header = "�ϻ﹫˾��֧����id")
	private Integer branchId;
	@Column(name="open_type",header = "��¼���� 1΢��")
	private String openType;
	@Column(name = "openid", header = "�û���¼ƽ̨id")
	private String openid;
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public Integer getCooperationCompanyId() {
		return cooperationCompanyId;
	}
	public void setCooperationCompanyId(Integer cooperationCompanyId) {
		this.cooperationCompanyId = cooperationCompanyId;
	}
	public Integer getBranchId() {
		return branchId;
	}
	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}
	public String getOpenType() {
		return openType;
	}
	public void setOpenType(String openType) {
		this.openType = openType;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	
	

}

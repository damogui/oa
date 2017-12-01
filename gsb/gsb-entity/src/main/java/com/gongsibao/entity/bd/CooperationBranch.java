package com.gongsibao.entity.bd;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="bd_cooperation_branch",header="��פ�㳡��")
public class CooperationBranch extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name="cooperation_company_id",header="������˾ �ֵ�type508")
    private Integer cooperationCompanyId;
    @Column(name="branch_code",header="��֧��������")
    private Integer branchCode;
    @Column(name="branch_name",header="��֧��������")
    private String branchName;
    @Column(name="province_id",header="ʡ")
    private Integer provinceId;
    @Column(name="city_id",header="��")
    private Integer cityId;
    @Column(name="area_id",header="��")
    private Integer areaId;
    @Column(header="addr")
    private String addr;
    @Column(name="is_enabled",header="1���� 0ͣ��")
    private Integer isEnabled;
    @Column(name="sort",header="����")
    private Integer sort;
	public Integer getCooperationCompanyId() {
		return cooperationCompanyId;
	}
	public void setCooperationCompanyId(Integer cooperationCompanyId) {
		this.cooperationCompanyId = cooperationCompanyId;
	}
	public Integer getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(Integer branchCode) {
		this.branchCode = branchCode;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public Integer getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public Integer getIsEnabled() {
		return isEnabled;
	}
	public void setIsEnabled(Integer isEnabled) {
		this.isEnabled = isEnabled;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}

}

package com.gongsibao.entity.crm;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="crm_company_position",header="职位信息")
public class CompanyPosition extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 5789648367055571062L;
	private String name;
    @Column(name="mutex_group_no",header="")
    private Integer mutexGroupNo;
    private String description;
    @Column(name="is_must",header="")
    private String isMust;
    private Integer sort;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getMutexGroupNo() {
        return mutexGroupNo;
    }
    public void setMutexGroupNo(Integer mutexGroupNo) {
        this.mutexGroupNo = mutexGroupNo;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getIsMust() {
        return isMust;
    }
    public void setIsMust(String isMust) {
        this.isMust = isMust;
    }
    public Integer getSort() {
        return sort;
    }
    public void setSort(Integer sort) {
        this.sort = sort;
    }

}
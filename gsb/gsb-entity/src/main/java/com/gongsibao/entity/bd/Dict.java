package com.gongsibao.entity.bd;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="bd_dict")
public class Dict extends BaseEntity {
	private static final long serialVersionUID = -1666319042067350999L;
	
	@Column(name="pid",header="父子关系")
    private Integer parentId;
	
    @Column(header="类型")
    private Integer type;
    
    @Column(header="名称")
    private String name;
    
    @Column(header="别名")
    private String alias;
    
    @Column(header="编码")
    private Integer code;
    
    @Column(header="sort")
    private Double sort;
    
    @Column(name="is_enabled",header="使用中")
    private Integer enabled = 1;
    
    @Column(header="备注")
    private String remark;
    
    public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAlias() {
        return alias;
    }
    public void setAlias(String alias) {
        this.alias = alias;
    }
    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    public Double getSort() {
        return sort;
    }
    public void setSort(Double sort) {
        this.sort = sort;
    }

    public Integer getEnabled() {
		return enabled;
	}
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
}
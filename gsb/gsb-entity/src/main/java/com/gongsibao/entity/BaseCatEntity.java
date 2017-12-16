package com.gongsibao.entity;

import org.netsharp.core.annotations.Column;

public abstract class BaseCatEntity extends BaseEntity{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -373873895333033007L;

	@Column(name = "pid", header = "父序号，默认0")
	private Integer pid;

	@Column(name = "sort", header = "排序")
	private Double sort = 0D;

	@Column(name = "level", header = "层级")
	private Integer level = 0;
	
	@Column(name = "short_name", header = "注：组织名称")
	private String shortName;
	
	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Double getSort() {
		return sort;
	}

	public void setSort(Double sort) {
		this.sort = sort;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
}

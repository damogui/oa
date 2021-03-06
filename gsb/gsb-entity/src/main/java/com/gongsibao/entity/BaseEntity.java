package com.gongsibao.entity;

import java.util.Date;

import org.netsharp.core.annotations.Auto;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Id;
import org.netsharp.entity.IEntity;
import org.netsharp.entity.Persistable;

public class BaseEntity extends Persistable implements IEntity{
	
	private static final long serialVersionUID = -8014614866116107945L;
	
	@Id
	@Auto
	@Column(name="pkid",header="id")
	private Integer id;
	
    @Column(name="add_user_id",header="添加人")
    private Integer creatorId = 0;
    
	@Column(name="add_time",header="创建时间")
    private Date createTime;
    
    @Column(name="upd_user_id",header="修改人")
    private Integer updatorId = 0;
    
    @Column(name="upd_time",header="修改时间")
    private Date updateTime;
    
	@Column(name = "creator", header = "创建人名称")
	private String creator;

	@Column(name = "updator", header = "更新人名称")
	private String updator;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getUpdatorId() {
		return updatorId;
	}

	public void setUpdatorId(Integer updatorId) {
		this.updatorId = updatorId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getUpdator() {
		return updator;
	}

	public void setUpdator(String updator) {
		this.updator = updator;
	}
}
package com.gongsibao.entity.cms;

import java.sql.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="cms_product_related")
public class ProductRelated extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -4972462548667533047L;
	@Column(name="cms_product_id")
    private Integer cmsProductId;
    @Column(name="product_id")
    private Integer productId;
    @Column(name="recommend_product_id")
    private Integer recommendProductId;
    @Column(name="product_name")
    private String productName;
    @Column(name="recommend_product_name")
    private String recommendProductName;
    @Column(name="add_user_id")
    private Integer addUserId;
    private Double sort;
    private String remark;
    @Column(name="add_time")
    private Date addTime;

    public Integer getCmsProductId() {
        return cmsProductId;
    }
    public void setCmsProductId(Integer cmsProductId) {
        this.cmsProductId = cmsProductId;
    }
    public Integer getProductId() {
        return productId;
    }
    public void setProductId(Integer productId) {
        this.productId = productId;
    }
    public Integer getRecommendProductId() {
        return recommendProductId;
    }
    public void setRecommendProductId(Integer recommendProductId) {
        this.recommendProductId = recommendProductId;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getRecommendProductName() {
        return recommendProductName;
    }
    public void setRecommendProductName(String recommendProductName) {
        this.recommendProductName = recommendProductName;
    }
    public Integer getAddUserId() {
        return addUserId;
    }
    public void setAddUserId(Integer addUserId) {
        this.addUserId = addUserId;
    }
    public Double getSort() {
        return sort;
    }
    public void setSort(Double sort) {
        this.sort = sort;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Date getAddTime() {
        return addTime;
    }
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}
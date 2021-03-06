package com.gongsibao.entity.yj;

import org.netsharp.core.annotations.Auto;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Id;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Persistable;

@Table(name="yj_trademark_img")
public class TrademarkImg extends Persistable {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -2558661387979106951L;
	@Id
	@Auto
	@Column(name="pkid",header="id")
	private Integer id;
	@Column(name="trademark_id",header="TrademarkId")
    private Integer trademarkId;
    @Column(name="file_id",header="FileId")
    private Integer fileId;
    @Column(header="name")
    private String name;

    public Integer getTrademarkId() {
        return trademarkId;
    }
    public void setTrademarkId(Integer trademarkId) {
        this.trademarkId = trademarkId;
    }
    public Integer getFileId() {
        return fileId;
    }
    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
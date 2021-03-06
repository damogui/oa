package com.gongsibao.entity.cw.dto;

import java.util.Date;

import org.netsharp.core.annotations.Auto;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Id;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Persistable;

import com.gongsibao.entity.cw.dict.FinanceDict;
import com.gongsibao.entity.u8.SetOfBooks;


/**
 * 
*  财务单据审核视图  
* 项目名称：gsb-entity   
* 类名称：BillAuditDTO   
* 类描述：   
* 创建人：angang
* 创建时间：2018年3月28日 上午10:34:55   
* @version
 */
@Table(name = "bill_audit_dto", isView = true)
public class BillAuditDTO extends Persistable{

	private static final long serialVersionUID = 7812453255149595296L;

	@Id
	@Auto
	@Column(name = "id", header = "主键")
	private Integer id;
	
	//单据类型
	private FinanceDict.FormType formType ;
	//单据类型value
	private Integer formTypeValue;
	//单据id
	private Integer formId ;
	//订单编号
	private String code;
	//金额
	private Integer  amount;
	//审核人id
	private Integer auditUserId;
	//创建人id
	private Integer creatorId;
    //创建人名称
	private String creator;
	//创建时间
	private Date createTime;
	//备注
    private String memoto;
    
    //付款单位（对应套帐
	private Integer setOfBooksId;
	
	@Reference(foreignKey = "setOfBooksId")
	private SetOfBooks setOfBooks;
    
	//状态 1:待审核 ，2：审核中 ，3：已通过，4：财务办理，5：驳回
	private FinanceDict.AuditStatus status = FinanceDict.AuditStatus.Status_1;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public FinanceDict.FormType getFormType() {
		return formType;
	}
	public void setFormType(FinanceDict.FormType formType) {
		this.formType = formType;
	}
	public Integer getFormId() {
		return formId;
	}
	public void setFormId(Integer formId) {
		this.formId = formId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Integer getAuditUserId() {
		return auditUserId;
	}
	public void setAuditUserId(Integer auditUserId) {
		this.auditUserId = auditUserId;
	}
	public Integer getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getMemoto() {
		return memoto;
	}
	public void setMemoto(String memoto) {
		this.memoto = memoto;
	}
	public Integer getFormTypeValue() {
		return formTypeValue;
	}
	public void setFormTypeValue(Integer formTypeValue) {
		this.formTypeValue = formTypeValue;
	}
	public Integer getSetOfBooksId() {
		return setOfBooksId;
	}
	public void setSetOfBooksId(Integer setOfBooksId) {
		this.setOfBooksId = setOfBooksId;
	}
	public SetOfBooks getSetOfBooks() {
		return setOfBooks;
	}
	public void setSetOfBooks(SetOfBooks setOfBooks) {
		this.setOfBooks = setOfBooks;
	}
	public FinanceDict.AuditStatus getStatus() {
		return status;
	}
	public void setStatus(FinanceDict.AuditStatus status) {
		this.status = status;
	}

    
}

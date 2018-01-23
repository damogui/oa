package com.gongsibao.entity.crm;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Subs;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;
import org.netsharp.organization.entity.Employee;

import com.gongsibao.entity.bd.Dict;
import com.gongsibao.entity.crm.dic.AllocationDispositon;
import com.gongsibao.entity.crm.dic.AllocationState;
import com.gongsibao.entity.crm.dic.CustomerFollowStatus;
import com.gongsibao.entity.crm.dic.NAllocationType;
import com.gongsibao.entity.crm.dic.QualityCategory;
import com.gongsibao.entity.crm.dic.TaskCustomerType;
import com.gongsibao.entity.crm.dic.TaskInspectionState;
import com.gongsibao.entity.supplier.Salesman;
import com.gongsibao.entity.supplier.Supplier;
import com.gongsibao.entity.supplier.SupplierDepartment;

@Table(name = "n_crm_customer_task", header = "客户任务")
public class NCustomerTask extends Entity {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 4684375504055933956L;

	@JsonIgnore
	@Reference(foreignKey = "customerId", header = "客户")
	private NCustomer customer;

	@Column(name = "customer_id", header = "客户")
	private Integer customerId = 0;

	@Column(name = "task_type", header = "任务类型")
	private TaskCustomerType taskType = TaskCustomerType.NEW;

	@Column(name = "name", size = 200, header = "名称")
	private String name;

	@Column(name = "supplier_id", header = "分配服务商Id")
	private Integer supplierId;

	@Reference(foreignKey = "supplierId", header = "分配服务商")
	private Supplier supplier;

	@Column(name = "department_id", header = "分配服务商部门Id")
	private Integer departmentId;

	@Reference(foreignKey = "departmentId", header = "分配服务商部门")
	private SupplierDepartment department;

	@Column(name = "salesman_id", header = "业务员Id")
	private Integer salesmanId;

	@Reference(foreignKey = "salesmanId", header = "业务员")
	private Salesman salesman;

	@Column(name = "last_allocation_time", header = "最后分配时间")
	private Date lastAllocationTime;

	@Column(name = "last_allocation_user_id", header = "最后分配人Id")
	private Integer lastAllocationUserId = 0;

	@JsonIgnore
	@Reference(foreignKey = "lastAllocationUserId", header = "最后分配人")
	private Employee lastAllocationUser;

	@Column(name = "foolow_status", header = "跟进状态")
	private CustomerFollowStatus foolowStatus;

	@Column(name = "intention_category", header = "质量分类")
	private QualityCategory intentionCategory;

	@Column(name = "quality_id", header = "客户质量id")
	private Integer qualityId;

	@Reference(foreignKey = "qualityId", header = "客户质量")
	private NCustomerTaskQuality quality;

	@Column(name = "last_follow_time", header = "最近跟进时间")
	private Date lastFollowTime;

	@Column(name = "last_foolow_user_id", header = "最后跟进人Id")
	private Integer lastFoolowUserId = 0;

	@JsonIgnore
	@Reference(foreignKey = "lastFoolowUserId", header = "最后跟进人")
	private Employee lastFoolowUser;

	@Column(name = "last_content", size = 1000, header = "最后跟进内容")
	private String lastContent;

	@Column(name = "next_foolow_time", header = "下次跟进时间")
	private Date nextFoolowTime;

	@Column(name = "inspection_state", header = "抽查异常状态")
	private TaskInspectionState inspectionState = TaskInspectionState.UNINSPECTION;

	@Column(name = "memoto", header = "备注", size = 1000)
	private String memoto;

	@Column(name = "cost_supplier_id", header = "费用服务商Id")
	private Integer costSupplierId;

	@Reference(foreignKey = "costSupplierId", header = "费用服务商")
	private Supplier costSupplier;

	@Column(name = "costed", header = "是否有费用服务商")
	private Boolean costed = false;

	@Column(name = "allocation_type", header = "分配方式")
	private NAllocationType allocationType = NAllocationType.AUTO;

	@Column(name = "allocation_statee", header = "分配状态")
	private AllocationState allocationState = AllocationState.WAIT;

	@Column(name = "allocation_dispositon", header = "自营/平台")
	private AllocationDispositon allocationDispositon = AllocationDispositon.UNLIMITED;

	@Reference(foreignKey = "sourceId", header = "任务来源")
	private Dict source;

	@Column(name = "source_id", header = "任务来源")
	private Integer sourceId = 0;

	@Column(name = "source_other", header = "客户来源选择其他时填写的详情")
	private String sourceOther;

	@Column(name = "consult_way_id", header = "咨询途径")
	private Integer consultWayId = 0;

	@Reference(foreignKey = "consultWayId", header = "咨询途径,421 CRM咨询途径: 4211 400电话、 4212 在线客服、 4213企业QQ、 4214 PC官网、 4215 H5官网、 4216 手机APP")
	private Dict consultWay;

	@Column(name = "consult_way_other", header = "咨询途径选择其他时填写的详情")
	private String consultWayOther;

	@Subs(foreignKey = "taskId", header = "意向产品", subType = NCustomerProduct.class)
	private List<NCustomerProduct> products;

	@Subs(foreignKey = "taskId", header = "沟通日志", subType = NCustomerTaskFoolow.class)
	private List<NCustomerTaskFoolow> follows;

	@Subs(foreignKey = "customerId", header = "通知日志", subType = NCustomerTaskNotify.class)
	private List<NCustomerTaskNotify> notifys;

	@Subs(foreignKey = "taskId", header = "流转日志", subType = NCustomerChange.class)
	private List<NCustomerChange> changes;

	public Boolean getCosted() {
		return costed;
	}

	public void setCosted(Boolean costed) {
		this.costed = costed;
	}

	public Integer getConsultWayId() {
		return consultWayId;
	}

	public void setConsultWayId(Integer consultWayId) {
		this.consultWayId = consultWayId;
	}

	public Dict getConsultWay() {
		return consultWay;
	}

	public void setConsultWay(Dict consultWay) {
		this.consultWay = consultWay;
	}

	public String getConsultWayOther() {
		return consultWayOther;
	}

	public void setConsultWayOther(String consultWayOther) {
		this.consultWayOther = consultWayOther;
	}

	public Integer getCostSupplierId() {
		return costSupplierId;
	}

	public void setCostSupplierId(Integer costSupplierId) {
		this.costSupplierId = costSupplierId;
	}

	public Supplier getCostSupplier() {
		return costSupplier;
	}

	public void setCostSupplier(Supplier costSupplier) {
		this.costSupplier = costSupplier;
	}

	public NAllocationType getAllocationType() {
		return allocationType;
	}

	public void setAllocationType(NAllocationType allocationType) {
		this.allocationType = allocationType;
	}

	public AllocationState getAllocationState() {
		return allocationState;
	}

	public void setAllocationState(AllocationState allocationState) {
		this.allocationState = allocationState;
	}

	public AllocationDispositon getAllocationDispositon() {
		return allocationDispositon;
	}

	public void setAllocationDispositon(AllocationDispositon allocationDispositon) {
		this.allocationDispositon = allocationDispositon;
	}

	public Dict getSource() {
		return source;
	}

	public void setSource(Dict source) {
		this.source = source;
	}

	public Integer getSourceId() {
		return sourceId;
	}

	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}

	public String getSourceOther() {
		return sourceOther;
	}

	public void setSourceOther(String sourceOther) {
		this.sourceOther = sourceOther;
	}
	
	public TaskCustomerType getTaskType() {
		return taskType;
	}

	public void setTaskType(TaskCustomerType taskType) {
		this.taskType = taskType;
	}

	public Integer getSalesmanId() {
		return salesmanId;
	}

	public void setSalesmanId(Integer salesmanId) {
		this.salesmanId = salesmanId;
	}

	public Salesman getSalesman() {
		return salesman;
	}

	public void setSalesman(Salesman salesman) {
		this.salesman = salesman;
	}

	public TaskInspectionState getInspectionState() {
		return inspectionState;
	}

	public void setInspectionState(TaskInspectionState inspectionState) {
		this.inspectionState = inspectionState;
	}

	public NCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(NCustomer customer) {
		this.customer = customer;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public SupplierDepartment getDepartment() {
		return department;
	}

	public void setDepartment(SupplierDepartment department) {
		this.department = department;
	}

	public QualityCategory getIntentionCategory() {
		return intentionCategory;
	}

	public void setIntentionCategory(QualityCategory intentionCategory) {
		this.intentionCategory = intentionCategory;
	}

	public NCustomerTaskQuality getQuality() {
		return quality;
	}

	public void setQuality(NCustomerTaskQuality quality) {
		this.quality = quality;
	}

	public String getMemoto() {
		return memoto;
	}

	public void setMemoto(String memoto) {
		this.memoto = memoto;
	}

	public List<NCustomerProduct> getProducts() {
		return products;
	}

	public void setProducts(List<NCustomerProduct> products) {
		this.products = products;
	}

	public List<NCustomerTaskFoolow> getFollows() {
		return follows;
	}

	public void setFollows(List<NCustomerTaskFoolow> follows) {
		this.follows = follows;
	}

	public List<NCustomerTaskNotify> getNotifys() {
		return notifys;
	}

	public void setNotifys(List<NCustomerTaskNotify> notifys) {
		this.notifys = notifys;
	}

	public List<NCustomerChange> getChanges() {
		return changes;
	}

	public void setChanges(List<NCustomerChange> changes) {
		this.changes = changes;
	}

	public CustomerFollowStatus getFoolowStatus() {
		return foolowStatus;
	}

	public void setFoolowStatus(CustomerFollowStatus foolowStatus) {
		this.foolowStatus = foolowStatus;
	}

	public Date getLastFollowTime() {
		return lastFollowTime;
	}

	public void setLastFollowTime(Date lastFollowTime) {
		this.lastFollowTime = lastFollowTime;
	}

	public Integer getLastFoolowUserId() {
		return lastFoolowUserId;
	}

	public void setLastFoolowUserId(Integer lastFoolowUserId) {
		this.lastFoolowUserId = lastFoolowUserId;
	}

	public Employee getLastFoolowUser() {
		return lastFoolowUser;
	}

	public void setLastFoolowUser(Employee lastFoolowUser) {
		this.lastFoolowUser = lastFoolowUser;
	}

	public String getLastContent() {
		return lastContent;
	}

	public void setLastContent(String lastContent) {
		this.lastContent = lastContent;
	}

	public Date getNextFoolowTime() {
		return nextFoolowTime;
	}

	public void setNextFoolowTime(Date nextFoolowTime) {
		this.nextFoolowTime = nextFoolowTime;
	}

	public Date getLastAllocationTime() {
		return lastAllocationTime;
	}

	public void setLastAllocationTime(Date lastAllocationTime) {
		this.lastAllocationTime = lastAllocationTime;
	}

	public Integer getLastAllocationUserId() {
		return lastAllocationUserId;
	}

	public void setLastAllocationUserId(Integer lastAllocationUserId) {
		this.lastAllocationUserId = lastAllocationUserId;
	}

	public Employee getLastAllocationUser() {
		return lastAllocationUser;
	}

	public void setLastAllocationUser(Employee lastAllocationUser) {
		this.lastAllocationUser = lastAllocationUser;
	}

	public Integer getQualityId() {
		return qualityId;
	}

	public void setQualityId(Integer qualityId) {
		this.qualityId = qualityId;
	}

}

package org.netsharp.scrum.entity;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.BizEntity;
import org.netsharp.organization.entity.Employee;
import org.netsharp.scrum.dic.Importance;
import org.netsharp.scrum.dic.StoryStatus;
import org.netsharp.scrum.dic.SupportType;
import org.netsharp.scrum.dic.Urgency;

@Table(name="scrum_support",header="服务支持")
public class Support extends BizEntity {

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -8413780480237473194L;
	@Column(name="project_id")
	private Integer projectId;
	@Reference(foreignKey="projectId")
	private Story project;
	
	@Column(name="owner_id")
	private Integer ownerId;
	@Reference(foreignKey="ownerId")
	private Employee owner;//处理人
	
	@Column(name="putor_id")
	private Integer putorId;
	@Reference(foreignKey="putorId")
	private Employee putor;//提出人
	
	private StoryStatus status = StoryStatus.hibernate;//支持状态
	@Column(size=2000)
	private String content;//详细内容
	
	@Column(size=1000)
	private String service;//开发测试支持
	
	private Importance importance = Importance.general;//重要性
	private Urgency urgency = Urgency.general;//紧急性
	
	@Column(name="estimate_hours")
	private Double estimateHours;//估时，以小时为单位
	@Column(name="actual_hours")
	private Double actualHours;//实际耗时，以小时为单位
	private SupportType type;
	
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public Story getProject() {
		return project;
	}
	public void setProject(Story project) {
		this.project = project;
		if(this.project==null){
			this.projectId=null;
		}
		else{
			this.projectId=this.project.getId();
		}
	}
	public Integer getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}
	public Employee getOwner() {
		return owner;
	}
	public void setOwner(Employee owner) {
		this.owner = owner;
		
		if(this.owner==null){
			this.ownerId=null;
		}
		else{
			this.ownerId=this.owner.getId();
		}
	}
	public StoryStatus getStatus() {
		return status;
	}
	public void setStatus(StoryStatus status) {
		this.status = status;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getPutorId() {
		return putorId;
	}
	public void setPutorId(Integer putorId) {
		this.putorId = putorId;
	}
	public Employee getPutor() {
		return putor;
	}
	public void setPutor(Employee putor) {
		this.putor = putor;
		
		if(this.putor==null){
			this.putorId=null;
		}
		else{
			this.putorId=this.putor.getId();
		}
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public Importance getImportance() {
		return importance;
	}
	public void setImportance(Importance importance) {
		this.importance = importance;
	}
	public Urgency getUrgency() {
		return urgency;
	}
	public void setUrgency(Urgency urgency) {
		this.urgency = urgency;
	}
	public Double getEstimateHours() {
		return estimateHours;
	}
	public void setEstimateHours(Double estimateHours) {
		this.estimateHours = estimateHours;
	}
	public Double getActualHours() {
		return actualHours;
	}
	public void setActualHours(Double actualHours) {
		this.actualHours = actualHours;
	}
	public SupportType getType() {
		return type;
	}
	public void setType(SupportType type) {
		this.type = type;
	}
	
	
}

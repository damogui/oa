package com.gongsibao.crm.web;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.entity.IPersistable;
import org.netsharp.panda.commerce.FormNavigation;
import org.netsharp.panda.commerce.FormPart;

import com.gongsibao.crm.base.INCustomerOperationLogService;
import com.gongsibao.crm.base.INCustomerService;
import com.gongsibao.crm.base.INCustomerTaskService;
import com.gongsibao.entity.crm.NCustomer;

public class NCustomerFormPart extends FormPart{

	INCustomerService customerService = ServiceFactory.create(INCustomerService.class);
	INCustomerTaskService taskService = ServiceFactory.create(INCustomerTaskService.class);
	public NCustomer validationContactWay(Integer id,String contactWay,String type){
	
		return customerService.validationContactWay(id,contactWay, type);
	}
	
	/**
	 * @Title: recordLookLog
	 * @Description: TODO(记录查看联系方式日志)
	 * @param: @param customerId
	 * @param: @param typeName
	 * @param: @return
	 * @return: boolean
	 * @throws
	 */
	public boolean recordLookLog(Integer customerId) {

		INCustomerOperationLogService changeService = ServiceFactory.create(INCustomerOperationLogService.class);
		return changeService.recordLookLog(customerId, "手机号，座机号，QQ号，微信号，邮箱");
	}
	
	
	public FormNavigation bySwtCustomerId(String swtCustomerId) {

		FormNavigation navigation = this.createFormNavigation(id);
		IPersistable obj = customerService.bySwtCustomerId(swtCustomerId);
		if (obj == null) {
			
			navigation.Entity = this.newInstance(null);
		} else {
			navigation.Entity = obj;
		}

		return navigation;
	}
	
	public FormNavigation byContactWay(String contactWay,String type) {

		//type:mobile,telephone,qq,weixin
		FormNavigation navigation = this.createFormNavigation(id);
		IPersistable obj = customerService.byContactWay(contactWay,type);
		navigation.Entity = obj;

		return navigation;
	}
	
	public NCustomer save(NCustomer entity) {

		entity = customerService.create(entity);
		entity = customerService.byId(entity);
		return entity;
	}
	
	/**
	 * 根据客户id获取商机所属人的集合
	 * @param customerId
	 * @return
	 */
	public String isHaveTask(int customerId){		
		return taskService.getTaskNamesByCustomerId(customerId);
	}
}
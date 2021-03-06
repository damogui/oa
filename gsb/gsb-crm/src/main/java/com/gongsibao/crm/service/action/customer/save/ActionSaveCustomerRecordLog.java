package com.gongsibao.crm.service.action.customer.save;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.EntityState;
import org.netsharp.persistence.session.SessionManager;

import com.gongsibao.crm.base.INCustomerOperationLogService;
import com.gongsibao.entity.crm.NCustomer;
import com.gongsibao.entity.crm.NCustomerOperationLog;
import com.gongsibao.entity.crm.dic.ChangeType;
import com.gongsibao.entity.crm.dic.OperationType;

/**
 * @author hw 客户保存：记录日志 NCustomerChange
 */
public class ActionSaveCustomerRecordLog implements IAction {

	@Override
	public void execute(ActionContext ctx) {

		NCustomer customer = (NCustomer) ctx.getItem();
		EntityState state = ctx.getState();
		String creator = SessionManager.getUserName();
		String content = null; 
		if(state == EntityState.New){
			
			content = String.format("[%s]创建客户", creator); 
		}else{
			
			content = String.format("[%s]编辑客户", creator); 
		}
		NCustomerOperationLog changeLog = new NCustomerOperationLog();
		{
			changeLog.toNew();
			changeLog.setChangeType(ChangeType.INPUT);
			changeLog.setType(OperationType.MANUAL);
			changeLog.setCustomerId(customer.getId());
			changeLog.setFormUserId(SessionManager.getUserId());
			changeLog.setContent(content);
			changeLog.setSupplierId(customer.getSupplierId());
			changeLog.setDepartmentId(customer.getDepartmentId());
		}
		
		INCustomerOperationLogService service = ServiceFactory.create(INCustomerOperationLogService.class);
		service.save(changeLog);
	}
}

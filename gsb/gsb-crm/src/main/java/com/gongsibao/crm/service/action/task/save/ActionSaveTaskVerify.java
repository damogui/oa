package com.gongsibao.crm.service.action.task.save;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.core.BusinessException;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.dic.AllocationState;
import com.gongsibao.entity.crm.dic.NAllocationType;

/**
 * @author hw 新增任务校验
 */
public class ActionSaveTaskVerify implements IAction {

	@Override
	public void execute(ActionContext ctx) {

		NCustomerTask task = (NCustomerTask) ctx.getItem();

		// 客户，名称，来源，其它来源，咨询途径，其它咨询途径
		if (task.getCustomerId() == null) {

			throw new BusinessException("[客户]不能为空！");
		}

		if (StringManager.isNullOrEmpty(task.getName())) {

			throw new BusinessException("[名称]不能为空！");
		}

		if (task.getSourceId() == null) {

			throw new BusinessException("[来源]不能为空！");
		} else if (StringManager.isNullOrEmpty(task.getSourceOther())) {

			throw new BusinessException("[其它来源]不能为空！");
		}

		if (task.getConsultWayId() == null) {

			throw new BusinessException("[咨询途径]不能为空！");
		} else if (StringManager.isNullOrEmpty(task.getConsultWayOther())) {

			throw new BusinessException("[其它咨询途径]不能为空！");
		}

		NAllocationType allocationType = task.getAllocationType();
		AllocationState allocationState = task.getAllocationState();
		// 分配方式为【手动分配】、分配状态为【待分配】
		if (allocationType == NAllocationType.MANUAL && allocationState == AllocationState.WAIT) {

			Integer supplierId = task.getSupplierId();
			Integer departmentId = task.getDepartmentId();
			Integer ownerId = task.getOwnerId();
			if (supplierId == null) {

				throw new BusinessException("[服务商]不能为空！");
			}
			if (departmentId == null) {

				throw new BusinessException("[部门]不能为空！");
			}
			if (ownerId == null) {

				throw new BusinessException("[业务员]不能为空！");
			}
		}

	}

}

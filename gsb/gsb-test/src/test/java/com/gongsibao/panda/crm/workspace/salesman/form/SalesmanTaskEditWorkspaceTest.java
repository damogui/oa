package com.gongsibao.panda.crm.workspace.salesman.form;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.util.StringManager;

import com.gongsibao.crm.web.NCustomerTaskEditFormPart;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.panda.operation.workspace.crm.form.TaskEditWorkspaceTest;

public class SalesmanTaskEditWorkspaceTest extends TaskEditWorkspaceTest{

	@Before
	public void setup() {
		
		super.setup();
		entity = NCustomerTask.class;
		urlForm = "/crm/salesman/task/edit";
		listPartName = formPartName = "任务信息";
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "CRM_SALESMAN_TASK_EDIT";
		
		List<String> ss = new ArrayList<String>();
		
		
		ss.add("/gsb/crm/platform/js/task-add-form.part.js");
		ss.add("/gsb/crm/base/js/task-base-edit-form.part.js");
		ss.add("/gsb/crm/salesman/js/task-edit-form.part.js");
		ss.add("/gsb/gsb.customer.controls.js");
		formJsImport = StringManager.join("|", ss);

		
		formJsController = NCustomerTaskEditFormPart.class.getName();
		formServiceController = NCustomerTaskEditFormPart.class.getName();
		
		productsDetailResourceNodeCode = "CRM_SALESMAN_Products";
		foolowDetailResourceNodeCode = "CRM_SALESMAN_Foolow";
		notifyDetailResourceNodeCode = "CRM_SALESMAN_Notify";
		changeDetailResourceNodeCode = "CRM_SALESMAN_Change";
		
		taskFollowDetailPart = "com.gongsibao.crm.web.SalesmanTaskFollowDetailPart";
	}
	
	@Test
	public void detailPart() {
		
	}
}

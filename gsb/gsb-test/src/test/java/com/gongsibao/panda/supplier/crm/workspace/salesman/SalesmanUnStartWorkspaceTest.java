package com.gongsibao.panda.supplier.crm.workspace.salesman;

import org.junit.Before;
import org.netsharp.panda.plugin.entity.PToolbar;

import com.gongsibao.crm.web.TaskAllListPart;

public class SalesmanUnStartWorkspaceTest extends SalesmanAllTaskWorkspaceTest{

	@Override
	@Before
	public void setup() {
		
		super.setup();
		
		listPartName = "未启动商机";
		urlList = "/crm/salesman/task/unstart/list";
		resourceNodeCode = "CRM_SALESMAN_TASK_START";
		listFilter = "foolowStatus = 6 and ownerId = '{userId}'";

        listPartServiceController = TaskAllListPart.class.getName();
	}
	
	
	@Override
	public PToolbar createListToolbar() {
		
		return null;
	}
	
	@Override
	public PToolbar createRowToolbar() {
		
		return null;
	}
}

package com.gongsibao.panda.supplier.crm.workspace.salesman;

import org.junit.Before;
import org.netsharp.panda.plugin.entity.PToolbar;

import com.gongsibao.crm.web.TaskAllListPart;

public class SalesmanDefeatedWorkspaceTest extends SalesmanAllTaskWorkspaceTest{

	@Override
	@Before
	public void setup() {
		
		super.setup();
		
		listPartName = "无法签单商机";
		urlList = "/crm/salesman/task/defeated/list";
		resourceNodeCode = "CRM_SALESMAN_TASK_DEFEATED";
		listFilter = "foolowStatus = 4 and ownerId = '{userId}'";

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

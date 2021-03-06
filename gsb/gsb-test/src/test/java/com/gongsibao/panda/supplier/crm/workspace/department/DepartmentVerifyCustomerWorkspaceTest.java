package com.gongsibao.panda.supplier.crm.workspace.department;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;

import com.gongsibao.crm.web.NCustomerVerifyListPart;
import com.gongsibao.entity.crm.NCustomer;
import com.gongsibao.panda.supplier.crm.workspace.salesman.SalesmanVerifyCustomerWorkspaceTest;

public class DepartmentVerifyCustomerWorkspaceTest extends SalesmanVerifyCustomerWorkspaceTest{

	@Before
	public void setup() {

		entity = NCustomer.class;
		urlList = "/crm/department/customer/verify";
		listToolbarPath = "/crm/department/customer/verify/edit";
		listPartName = formPartName = "校验客户 ";
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "CRM_DEPARTMENT_CUSTOMER_ADD";
		listPartImportJs = "/gsb/platform/operation/crm/js/customer-verify-list.part.js";
		listPartJsController = NCustomerVerifyListPart.class.getName();
		listPartServiceController = NCustomerVerifyListPart.class.getName();
	}
	
	@Test
	public void createRowToolbar() {
		
	}
}

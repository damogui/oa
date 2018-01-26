package com.gongsibao.panda.crm.workspace.salesman;

import org.junit.Before;
import org.netsharp.core.MtableManager;

import com.gongsibao.crm.web.CustomerVerifyListPart;
import com.gongsibao.entity.crm.NCustomer;
import com.gongsibao.panda.operation.workspace.crm.CustomerVerifyWorkspaceTest;

public class SalesmanVerifyCustomerWorkspaceTest extends CustomerVerifyWorkspaceTest{

	@Before
	public void setup() {

		entity = NCustomer.class;
		urlList = "/crm/salesman/customer/verify";
		listToolbarPath = "/crm/my/customer/verify/edit";
		listPartName = formPartName = "校验客户";
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "CRM_SALESMAN_CUSTOMER_ADD";
		listPartImportJs = "/gsb/crm/platform/js/customer-verify-list.part.js";
		listPartJsController = CustomerVerifyListPart.class.getName();
		listPartServiceController = CustomerVerifyListPart.class.getName();
	}
}
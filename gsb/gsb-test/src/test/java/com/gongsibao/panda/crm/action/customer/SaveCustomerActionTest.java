package com.gongsibao.panda.crm.action.customer;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.plugin.bean.BeanPath;

import com.gongsibao.crm.service.action.customer.save.ActionSaveCustomerRecordLog;
import com.gongsibao.crm.service.action.customer.save.ActionSaveCustomerPersist;
import com.gongsibao.crm.service.action.customer.save.ActionSaveCustomerVerify;
import com.gongsibao.panda.crm.action.BaseActionTest;

public class SaveCustomerActionTest extends BaseActionTest{

	@Before
	public void setup() {

		resourceNodeCode = "CRM_SALESMAN_CUSTOMER";
		super.setup();
	}

	@Test
	public void save() {

		String pathName = "gsb/crm/customer/save";
		BeanPath beanPath = new BeanPath();
		{
			beanPath.toNew();
			beanPath.setPath(pathName);
			beanPath.setResourceNode(resourceNode);
			beanPath.setName("修改客户");
		}

		createBean(beanPath, "验证", ActionSaveCustomerVerify.class.getName(), resourceNode, 100);
		createBean(beanPath, "保存", ActionSaveCustomerPersist.class.getName(), resourceNode, 200);
		createBean(beanPath, "日志", ActionSaveCustomerRecordLog.class.getName(), resourceNode, 300);
		beanPathService.save(beanPath);
	}
}
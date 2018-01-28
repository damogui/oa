package com.gongsibao.panda.crm.action.task;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.plugin.bean.BeanPath;

import com.gongsibao.crm.service.action.task.save.ActionSaveTaskLog;
import com.gongsibao.crm.service.action.task.save.ActionSaveTaskPersist;
import com.gongsibao.crm.service.action.task.save.ActionSaveTaskVerify;
import com.gongsibao.panda.crm.action.BaseActionTest;

public class SaveActionTest extends BaseActionTest {

	@Before
	public void setup() {

		resourceNodeCode = "CRM_SALESMAN_CUSTOMER";
		super.setup();
	}

	@Test
	public void save() {

		String pathName = "gsb/crm/task/add";
		BeanPath beanPath = new BeanPath();
		{
			beanPath.toNew();
			beanPath.setPath(pathName);
			beanPath.setResourceNode(resourceNode);
			beanPath.setName("任务新增");
		}

		createBean(beanPath, "验证", ActionSaveTaskVerify.class.getName(), resourceNode, 100);
		createBean(beanPath, "保存", ActionSaveTaskPersist.class.getName(), resourceNode, 200);
		createBean(beanPath, "日志", ActionSaveTaskLog.class.getName(), resourceNode, 300);
		beanPathService.save(beanPath);
	}
}

package com.gongsibao.panda.action.crm.task;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.plugin.bean.BeanPath;

import com.gongsibao.crm.service.action.task.abnormal.ActionAbnormalPersist;
import com.gongsibao.crm.service.action.task.abnormal.ActionAbnormalRecordLog;
import com.gongsibao.crm.service.action.task.abnormal.ActionAbnormalVerify;
import com.gongsibao.crm.service.action.task.abnormal.ActionAbnormalWriteBack;
import com.gongsibao.panda.action.BaseActionTest;

/**
 * @author hw
 *
 */
public class AbnormalActionTest extends BaseActionTest {

	@Before
	public void setup() {

		resourceNodeCode = "CRM_SALESMAN_CUSTOMER";
		super.setup();
	}

	@Test
	public void save() {

		String pathName = "gsb/crm/task/abnormal";
		BeanPath beanPath = new BeanPath();
		{
			beanPath.toNew();
			beanPath.setPath(pathName);
			beanPath.setResourceNode(resourceNode);
			beanPath.setName("抽查异常");
		}

		createBean(beanPath, "验证", ActionAbnormalVerify.class.getName(), resourceNode, 100);
		createBean(beanPath, "保存", ActionAbnormalPersist.class.getName(), resourceNode, 200);
		createBean(beanPath, "回写", ActionAbnormalWriteBack.class.getName(), resourceNode, 300);
		createBean(beanPath, "日志", ActionAbnormalRecordLog.class.getName(), resourceNode, 400);
		beanPathService.save(beanPath);
	}
}

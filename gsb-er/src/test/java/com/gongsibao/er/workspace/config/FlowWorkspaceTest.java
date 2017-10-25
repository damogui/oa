package com.gongsibao.er.workspace.config;

import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.organization.base.IOperationService;
import org.netsharp.organization.base.IOperationTypeService;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.panda.base.IPWorkspaceService;
import org.netsharp.panda.entity.PWorkspace;
import org.netsharp.resourcenode.IResourceNodeService;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.er.Order;

public class FlowWorkspaceTest {
	private String url = "/er/config/1/list";
	private String name = "流程管理";
	private String code = "ER_Config_1_" + Order.class.getSimpleName();

	@Test
	public void execute() {

		IResourceNodeService nodeService = ServiceFactory.create(IResourceNodeService.class);
		ResourceNode node = nodeService.byCode(this.code);

		IOperationTypeService operationTypeService = ServiceFactory.create(IOperationTypeService.class);
		OperationType opt = operationTypeService.byCode(OperationTypes.view);

		PWorkspace workspace = new PWorkspace();
		{
			workspace.setUrl(this.url);
			workspace.setName(this.name);
			workspace.setCode(this.code);
			workspace.setResourceNode(node);
			workspace.setRedirectUrl("http://t2.gongsibao.net/erpct2/index.html#/app/cm/flowManage");
			workspace.setOperationTypeId(opt.getId());
			workspace.toNew();
		}

		IPWorkspaceService workspaceService = ServiceFactory.create(IPWorkspaceService.class);
		workspaceService.save(workspace);
	}

	@Test
	public void operation() {

		IResourceNodeService resourceService = ServiceFactory.create(IResourceNodeService.class);
		ResourceNode node = resourceService.byCode(this.code);
		IOperationService service = ServiceFactory.create(IOperationService.class);
		service.addOperation(node, OperationTypes.view);
	}
}

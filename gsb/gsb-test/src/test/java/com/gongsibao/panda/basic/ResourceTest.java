package com.gongsibao.panda.basic;

import org.junit.Before;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.meta.base.ResourceCreationBase;
import org.netsharp.resourcenode.IResourceNodeService;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.u8.SetOfBooks;
import com.gongsibao.entity.u8.U8Bank;
import com.gongsibao.entity.u8.VoucherLog;
import com.gongsibao.u8.base.ISetOfBooksService;
import com.gongsibao.u8.base.IU8BankService;
import com.gongsibao.u8.base.IVoucherLogService;

public class ResourceTest extends ResourceCreationBase {

	public static String resourcePrefix = "GSB_Basic";

	IResourceNodeService service = ServiceFactory.create(IResourceNodeService.class);

	// 初始化资源
	@Before
	public void setup() {
		parentNodeName = "基础配置";
		parentNodeCode = resourcePrefix;
		pluginName = "基础配置";
		seq = 3;
		entityClass = ResourceNode.class;
	}

	// 创建资源节点
	@Override
	protected void createResourceNodeVouchers(ResourceNode node) {

		String prefix = ResourceTest.resourcePrefix;
		ResourceNode node1 = null;
		node1 = this.createResourceNodeCategory("U8配置", prefix + "_U8", node.getId());
		{
			this.createResourceNodeVoucher(SetOfBooks.class.getName(), "账套列表", node1.getCode() + "_" + SetOfBooks.class.getSimpleName(), ISetOfBooksService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(U8Bank.class.getName(), "科目银行列表", node1.getCode() + "_" + U8Bank.class.getSimpleName(), IU8BankService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(VoucherLog.class.getName(), "凭证日志记录", node1.getCode() + "_" + VoucherLog.class.getSimpleName(), IVoucherLogService.class.getName(), node1.getId());
		}
	}
}
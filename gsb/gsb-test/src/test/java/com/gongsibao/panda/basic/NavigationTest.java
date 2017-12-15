package com.gongsibao.panda.basic;

import org.junit.Before;
import org.netsharp.meta.base.NavigationBase;
import org.netsharp.panda.plugin.entity.PNavigation;

import com.gongsibao.entity.trade.dto.ManualVoucherOrderDTO;
import com.gongsibao.entity.trade.dto.PayReceiptCheckDTO;
import com.gongsibao.entity.u8.SetOfBooks;
import com.gongsibao.entity.u8.U8Bank;
import com.gongsibao.entity.u8.VoucherLog;

public class NavigationTest extends NavigationBase {

	//初始化导航信息
	@Before
	public void setup() {
		this.treeName = "基础配置";
		this.treePath = "panda/gsb/u8";
		this.resourceNode = "GSB_Basic";
	}

	public void createAccodions() {

		this.doCreateAccodions("GSB_Basic", "基础配置", "fa fa-users fa-fw", 1);//顶部和右侧顶部（配置菜单名称和图标、个数等）
	}

	//创建菜单树节点
	@Override
	protected void doCreateTree(PNavigation tree) {

		createPTreeNode(tree, null, "fa fa-users fa-fw", "GSB_Basic_U8", "U8配置", "", 1);//一级菜单
		{
			createPTreeNode(tree, "GSB_Basic_U8", null, "GSB_Basic_U8_" + SetOfBooks.class.getSimpleName(), "账套列表", "/basic/u8/setofBbooks/list", 1);//二级菜单
			createPTreeNode(tree, "GSB_Basic_U8", null, "GSB_Basic_U8_" + U8Bank.class.getSimpleName(), "科目银行", "/basic/u8/bank/list", 2);//二级菜单
			createPTreeNode(tree, "GSB_Basic_U8", null, "GSB_Basic_U8_" + VoucherLog.class.getSimpleName(), "凭证日志", "/basic/u8/voucherLog/list", 3);//二级菜单
			createPTreeNode(tree, "GSB_Basic_U8", null, "GSB_Basic_U8_" + PayReceiptCheckDTO.class.getSimpleName(), "回单管理", "/basic/u8/receiptcheck/list", 3);//二级菜单
			createPTreeNode(tree, "GSB_Basic_U8", null, "GSB_Basic_U8_" + ManualVoucherOrderDTO.class.getSimpleName(), "手动凭证订单列表", "/basic/u8/manualvoucherorder/list", 3);//二级菜单
		}
	}
}
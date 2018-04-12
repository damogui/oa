package com.gongsibao.panda.supplier.order.workspace.salesman;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DatagridAlign;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.plugin.dic.ToolbarType;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.trade.OrderProd;
import com.gongsibao.trade.web.SalesmanOrderDetailListPart;

/**
 * 业务员/我的明细订单
 * @author Administrator
 *
 */
public class SalesmanOrderDetailWorkspaceTest extends WorkspaceCreationBase {
	@Override
	@Before
	public void setup() {
		super.setup();
		entity = OrderProd.class;
		urlList = "/crm/order/salesman/detail/list";
		listPartName = "我的明细订单";
		meta = MtableManager.getMtable(entity);
		resourceNodeCode = "Gsb_Supplier_Order_Salesman_OrderProd";
		
		List<String> ss = new ArrayList<String>();
		ss.add("/gsb/platform/trade/js/salesman-order-detail-list.part.js");
		ss.add("/gsb/panda-extend/gsb.custom.query.controls.js");
		ss.add("/gsb/panda-extend/gsb.pubcontrol.js");
		listPartImportJs = StringManager.join("|", ss);
		
		listPartJsController = SalesmanOrderDetailListPart.class.getName();
		listPartServiceController = SalesmanOrderDetailListPart.class.getName();
		listFilter = "owner_id = '{userId}'";
		
	}
	

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.setName("我的明细订单");
			datagrid.setToolbar("");
			datagrid.setAutoQuery (true);
		}

		PDatagridColumn column = null;
		column = addColumn(datagrid, "id", "操作", ControlTypes.TEXT_BOX, 120, true);{
			column.setFormatter("return controllerorderProdList.operateFormatter(value,row,index)");
		}
		column = addColumn(datagrid, "no", "订单明细编号", ControlTypes.TEXT_BOX, 80);
		{
			column.setAlign(DatagridAlign.CENTER);
		}
		column = addColumn(datagrid, "soOrder.no", "订单编号", ControlTypes.TEXT_BOX, 80);
		{
			column.setAlign(DatagridAlign.CENTER);
		}
		column = addColumn(datagrid, "beginOption", "是否显示开始操作", ControlTypes.BOOLCOMBO_BOX, 80);
		{
			column.setSystem(true);
			column.setVisible(false);
		}
		column = addColumn(datagrid, "orderId", "订单主键", ControlTypes.TEXT_BOX, 80);
		{
			column.setSystem(true);
			column.setVisible(false);
		}
		column = addColumn(datagrid, "soOrder.companyId", "订单签单公司Id", ControlTypes.TEXT_BOX, 80);
		{
			column.setSystem(true);
			column.setVisible(false);
		}
		column = addColumn(datagrid, "productId", "产品主键", ControlTypes.TEXT_BOX, 80);
		{
			column.setSystem(true);
			column.setVisible(false);
		}
		column = addColumn(datagrid, "productName", "产品名称", ControlTypes.TEXT_BOX, 200);{
			column.setFormatter("return '<span title='+value+'>'+value+'</span>'");
		}
		column = addColumn(datagrid, "cityName", "产品地区", ControlTypes.TEXT_BOX, 200);{
			column.setFormatter("return '<span title='+value+'>'+value+'</span>'");
		}
		addColumn(datagrid, "handleName", "办理名称", ControlTypes.TEXT_BOX, 250);
		addColumn(datagrid, "companyIntention.name", "操作公司", ControlTypes.TEXT_BOX, 250);
		
		addColumn(datagrid, "soOrder.customerName", "下单人", ControlTypes.TEXT_BOX, 100);
        column=addColumn(datagrid, "soOrder.accountMobile", "下单人电话", ControlTypes.TEXT_BOX, 100);{
            column.setFormatter(" var ctrl=workspace.parts.byIndex(0).key; return eval(ctrl+'.contactFormatter(value,row,index,\\'手机号\\')');");
        }
		
        addColumn(datagrid, "soOrder.createTime", "下单时间", ControlTypes.DATETIME_BOX, 100);
		addColumn(datagrid, "processStatus", "办理进度", ControlTypes.ENUM_BOX, 100);
		addColumn(datagrid, "owner.name", "当前操作员", ControlTypes.TEXT_BOX, 80);	
        
		return datagrid;
	}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		PQueryItem item = null;
		queryProject.setColumnCount(3);

		item = addQueryItem(queryProject, "keyword", "关键字", ControlTypes.TEXT_BOX);
		{
			item.setTooltip("订单明细编号、订单编号、下单人、下单人电话、操作公司");
			item.setWidth(350);
		}
		addQueryItem(queryProject, "productName", "产品名称", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "cityName", "产品地区", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "owner.name", "当前操作员", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "soOrder.createTime", "订单创建日期", ControlTypes.DATE_BOX);
		return queryProject;
	}

	@Override
	protected void doOperation() {
		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node, OperationTypes.view);
	}
}
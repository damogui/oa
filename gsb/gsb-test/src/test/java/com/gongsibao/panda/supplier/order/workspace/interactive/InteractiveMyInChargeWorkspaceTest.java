package com.gongsibao.panda.supplier.order.workspace.interactive;

import com.gongsibao.entity.u8.SetOfBooks;
import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.OrderbyMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.plugin.dic.ToolbarType;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.trade.OrderProd;
import com.gongsibao.entity.trade.dic.OrderProdUserMapStatus;
import com.gongsibao.entity.trade.dic.OrderProdUserMapType;
import com.gongsibao.tools.PToolbarHelper;
import com.gongsibao.trade.web.interactive.MyInChargeListPart;

public class InteractiveMyInChargeWorkspaceTest extends WorkspaceCreationBase {

    protected Boolean isSingleSelect = true;

    @Before
    public void setup() {
        super.setup();
        entity = OrderProd.class;
        urlList = "/crm/order/interactive/myincharge/list";
        listPartName = formPartName = "我负责的订单";
        meta = MtableManager.getMtable(entity);
        listToolbarPath = "/crm/order/myincharge/list";
        resourceNodeCode = "Gsb_Supplier_Order_Interactive_My_In_Charge";
        listPartImportJs = "/gsb/platform/trade/js/prod/interactive-myincharge-list.part.js|/gsb/panda-extend/gsb.custom.query.controls.js";
        listPartServiceController = MyInChargeListPart.class.getName();
        listPartJsController = MyInChargeListPart.class.getName();
        listFilter = "OrderProd.pkid IN(SELECT distinct order_prod_id FROM so_order_prod_user_map WHERE type_id=" + OrderProdUserMapType.Czy.getValue() + " AND user_id = '{userId}')";
    }

    @Test
    public void createListToolbar() {

        ResourceNode node = resourceService.byCode(resourceNodeCode);
        OperationType ot1 = operationTypeService.byCode(OperationTypes.view);
        PToolbar toolbar = new PToolbar();
        {
            toolbar.toNew();
            toolbar.setPath(listToolbarPath);
            toolbar.setName("我负责的订单操作");
            toolbar.setResourceNode(node);
            toolbar.setToolbarType(ToolbarType.BASE);
        }

        PToolbarItem item = new PToolbarItem();
        {
            item.toNew();
            item.setCode("addFollowUp");
            item.setIcon(PToolbarHelper.iconAdd);
            item.setName("跟进");
            item.setOperationType(ot1);
            item.setSeq(3);
            item.setCommand("{controller}.addFollowUp();");
            toolbar.getItems().add(item);
        }

        toolbarService.save(toolbar);
    }

    @Override
    protected PDatagrid createDatagrid(ResourceNode node) {

        PDatagrid datagrid = super.createDatagrid(node);
        {
            datagrid.setName("我负责的订单");
            datagrid.setToolbar("panda/datagrid/row/edit");
            datagrid.setAutoQuery(false);
            if (!isSingleSelect) {
                datagrid.setShowCheckbox(true);
                datagrid.setSingleSelect(false);
            }
        }
        PDatagridColumn column = null;
        addColumn(datagrid, "no", "操作", ControlTypes.OPERATION_COLUMN, 100, true);
        column = addColumn(datagrid, "orderId", "订单id", ControlTypes.OPERATION_COLUMN, 100, true);
        {
            column.setVisible(false);
        }
        addColumn(datagrid, "soOrder.no", "订单编号", ControlTypes.TEXT_BOX, 80);
        addColumn(datagrid, "id", "订单明细号", ControlTypes.TEXT_BOX, 80);
        addColumn(datagrid, "processStatus.name", "办理状态", ControlTypes.TEXT_BOX, 80);
        addColumn(datagrid, "soOrder.companyIntention.companyName", "订单关联公司", ControlTypes.TEXT_BOX, 200);
        addColumn(datagrid, "companyIntention.companyName", "明细订单公司", ControlTypes.TEXT_BOX, 200);
        addColumn(datagrid, "soOrder.customer.realName", "联系人", ControlTypes.TEXT_BOX, 100);
        column = addColumn(datagrid, "soOrder.accountMobile", "联系人电话", ControlTypes.TEXT_BOX, 100);
        {
            column.setFormatter(" var ctrl=workspace.parts.byIndex(0).key; return eval(ctrl+'.contactFormatter(value,row,index,\\'手机号\\')');");
        }
        addColumn(datagrid, "productName", "产品名称", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "cityName", "产品地区", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "soOrder.payablePrice", "订单金额", ControlTypes.DECIMAL_FEN_BOX, 100);
        addColumn(datagrid, "soOrder.balance", "订单余额", ControlTypes.DECIMAL_FEN_BOX, 100);
        column = addColumn(datagrid, "soOrder.createTime", "下单日期", ControlTypes.DATETIME_BOX, 100);
        {
            column.setOrderbyMode(OrderbyMode.DESC);
        }
        addColumn(datagrid, "owner.name", "业务员", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "operator", "操作员", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "soOrder.refundStatus", "退款状态", ControlTypes.ENUM_BOX, 80);
        addColumn(datagrid, "isUrgent", "是否加急", ControlTypes.BOOLCOMBO_BOX, 80);
        addColumn(datagrid, "allocationOperatorDate", "分配日期", ControlTypes.DATETIME_BOX, 100);
        addColumn(datagrid, "surplusDays", "剩余天数", ControlTypes.DECIMAL_BOX, 80);
        addColumn(datagrid, "handleName", "办理名称", ControlTypes.TEXT_BOX, 100);
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
            item.setTooltip("订单编号、订单明细号、联系人姓名、联系人电话、关联企业");
            item.setWidth(350);
        }
        addQueryItem(queryProject, "productName", "产品名称", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "handleName", "办理名称", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "owner.name", "业务员", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "operator", "操作员", ControlTypes.TEXT_BOX);
        //addRefrenceQueryItem(queryProject, "operatorData.name", "操作员1", "CRM_Employee");
        addQueryItem(queryProject, "cityName", "产品地区", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "processStatus.name", "办理状态", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "soOrder.createTime", "下单日期", ControlTypes.DATE_BOX);
        addQueryItem(queryProject, "soOrder.processStatus", "订单总体进度", ControlTypes.ENUM_BOX);
        item = addQueryItem(queryProject, "inChargeStatus", "负责状态", ControlTypes.ENUM_BOX);
        {
            item.setAppconfigCondition(OrderProdUserMapStatus.class.getName());
        }

        return queryProject;
    }

    @Override
    protected void doOperation() {
        ResourceNode node = this.getResourceNode();
        operationService.addOperation(node, OperationTypes.view);
    }
}

package com.gongsibao.panda.supplier.order.workspace.salesman;

import com.gongsibao.entity.trade.NDepReceivable;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.tools.PToolbarHelper;
import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.EntityState;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DatagridAlign;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.plugin.dic.ToolbarType;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.entity.ResourceNode;

/*订单业绩*/
public class SalesmanOrderPerformanceWorspaceTest extends WorkspaceCreationBase {
    @Before
    public void setup() {
        super.setup();
        entity = NDepReceivable.class;
        urlList = "/crm/order/salesman/performance/list";
        listPartName = formPartName = "订单业绩";
        meta = MtableManager.getMtable(entity);
        formPartName = listPartName = meta.getName();
        resourceNodeCode = "Gsb_Supplier_Order_Salesman_Performance";
        //listToolbarPath = "crm/order/orderperformance/edit";
        formOpenMode = OpenMode.WINDOW;
        openWindowHeight = 700;
        openWindowWidth = 900;
        listPartImportJs = "/gsb/panda-extend/gsb.custom.query.controls.js";
    }


    /*public PToolbar createListToolbar() {

        ResourceNode node = this.resourceService.byCode (resourceNodeCode);
       // OperationType ot1 = operationTypeService.byCode (OperationTypes.add);
        PToolbar toolbar = new PToolbar ();
        {
            toolbar.toNew ();
            toolbar.setPath (listToolbarPath);
            toolbar.setName ("订单业绩");
            toolbar.setResourceNode (node);
            toolbar.setToolbarType (ToolbarType.BASE);
        }
        //详情进行跳转双击操作
        PToolbarItem item = PToolbarHelper.getPToolbarItem (EntityState.New, "addAudit", PToolbarHelper.iconAdd,
                "审批流", null, 1, "{controller}.add();");
        toolbar.getItems ().add (item);
        return toolbar;
    }



    *//*进行设置工具栏*//*
    @Test
    public void saveListToolbar() {

        PToolbar toolbar = createListToolbar ();
        if (toolbar != null) {

            toolbarService.save (toolbar);
        }
    }*/


    @Override
    protected PDatagrid createDatagrid(ResourceNode node) {

        PDatagrid datagrid = super.createDatagrid(node);
        {
            datagrid.setName("订单业绩");
            datagrid.setToolbar("panda/datagrid/row/edit");
            datagrid.setAutoQuery(true);
        }
        PDatagridColumn column = null;
        addColumn(datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 100, true);
        addColumn(datagrid, "order.no", "订单编号", ControlTypes.TEXT_BOX, 80);
        addColumn(datagrid, "order.channelOrderNo", "渠道订单编号", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "order.prodName", "产品名称", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "order.companyIntention.companyName", "签单公司", ControlTypes.TEXT_BOX, 100);
        column = addColumn(datagrid, "order.totalPrice", "原价金额", ControlTypes.DECIMAL_FEN_BOX, 100);
        {
            column.setAlign(DatagridAlign.RIGHT);
        }
        column = addColumn(datagrid, "order.payablePrice", "应付金额", ControlTypes.DECIMAL_FEN_BOX, 100);
        {
            column.setAlign(DatagridAlign.RIGHT);
        }
        column = addColumn(datagrid, "order.paidPrice", "已付金额", ControlTypes.DECIMAL_FEN_BOX, 100);
        {
            column.setAlign(DatagridAlign.RIGHT);
        }
        addColumn(datagrid, "order.payStatus", "付款状态", ControlTypes.ENUM_BOX, 100);
        column = addColumn(datagrid, "amount", "订单业绩分配金额", ControlTypes.DECIMAL_FEN_BOX, 100);
        {
            column.setAlign(DatagridAlign.RIGHT);
        }
        addColumn(datagrid, "statusType", "审核状态", ControlTypes.ENUM_BOX, 80);
        addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATETIME_BOX, 100);
        addColumn(datagrid, "order.createTime", "订单创建时间", ControlTypes.DATETIME_BOX, 100);
        addColumn(datagrid, "creator", "订单业绩创建人", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "order.owner.name", "业务员", ControlTypes.TEXT_BOX, 100);

        return datagrid;
    }

    @Override
    protected PQueryProject createQueryProject(ResourceNode node) {

        PQueryProject queryProject = super.createQueryProject(node);
        queryProject.toNew();
        queryProject.setColumnCount(6);

        addQueryItem(queryProject, "no", "编号", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "channelOrderNo", "渠道订单编号", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "prodName", "产品名称", ControlTypes.TEXT_BOX);
        /*addQueryItem(queryProject, "no", "办理名称", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "no", "客户创建人", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "no", "业务员", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "owner.name", "下单人", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "no", "下单人电话", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "no", "关联企业", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "sourceType.name", "订单来源", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "payStatus.name", "订单状态", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "type", "订单类型", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "no", "组织机构", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "no", "产品分类", ControlTypes.TEXT_BOX);

        addQueryItem(queryProject, "no", "下单方式", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "addTime", "回款日期", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "no", "分期付款", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "no", "开发票", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "addTime", "创建日期", ControlTypes.TEXT_BOX);*/
//        addQueryItem (queryProject, "no", "组织机构", ControlTypes.TEXT_BOX);
//        addQueryItem (queryProject, "no", "组织机构", ControlTypes.TEXT_BOX);
        //今天 昨天 本周 本月


        return queryProject;
    }

    @Override
    protected void doOperation() {
        ResourceNode node = this.getResourceNode();
        operationService.addOperation(node, OperationTypes.view);
        /*operationService.addOperation (node, OperationTypes.add);
        operationService.addOperation (node, OperationTypes.update);
        operationService.addOperation (node, OperationTypes.delete);*/
    }

}

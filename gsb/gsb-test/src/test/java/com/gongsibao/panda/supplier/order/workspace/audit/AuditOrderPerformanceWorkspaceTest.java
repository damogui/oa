package com.gongsibao.panda.supplier.order.workspace.audit;

import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.tools.PToolbarHelper;
import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.EntityState;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.plugin.dic.ToolbarType;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.entity.ResourceNode;

/**
 * Created by win on 2018/3/20.
 */
/*订单业绩审核*/
public class AuditOrderPerformanceWorkspaceTest  extends WorkspaceCreationBase {
    @Before
    public void setup() {
        super.setup ();
        entity = SoOrder.class;
        urlList = "/crm/order/audit/orderp/list";
        listPartName = formPartName = "订单业绩审核";
        meta = MtableManager.getMtable (entity);
        resourceNodeCode = "Gsb_Supplier_Order_Audit_Performance";
        listToolbarPath = "crm/audit/orderp/edit";
        listPartImportJs = "/gsb/panda-extend/gsb.custom.query.controls.js";
    }

    public PToolbar createListToolbar() {

        ResourceNode node = this.resourceService.byCode (resourceNodeCode);
        // OperationType ot1 = operationTypeService.byCode (OperationTypes.add);
        PToolbar toolbar = new PToolbar ();
        {
            toolbar.toNew ();
            toolbar.setPath (listToolbarPath);
            toolbar.setName ("订单业绩审核");
            toolbar.setResourceNode (node);
            toolbar.setToolbarType (ToolbarType.BASE);
        }

        PToolbarItem item = PToolbarHelper.getPToolbarItem (EntityState.New, "addAudit", PToolbarHelper.iconExtr,
                "查看审核记录", null, 1, "{controller}.add();");
        toolbar.getItems ().add (item);
        item = PToolbarHelper.getPToolbarItem (EntityState.New, "addAudit", PToolbarHelper.iconCheck,
                "审核", null, 2, "{controller}.add();");
        toolbar.getItems ().add (item);
        return toolbar;
    }



    /*进行设置工具栏*/
    @Test
    public void saveListToolbar() {

        PToolbar toolbar = createListToolbar ();
        if (toolbar != null) {

            toolbarService.save (toolbar);
        }
    }

    @Override
    protected PDatagrid createDatagrid(ResourceNode node) {

        PDatagrid datagrid = super.createDatagrid (node);
        {
            datagrid.setName ("结转订单");
            datagrid.setToolbar ("panda/datagrid/row/edit");
            datagrid.setAutoQuery (true);
            datagrid.setShowCheckbox (true);
            datagrid.setSingleSelect (false);
        }
        PDatagridColumn column = null;
        addColumn (datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 100, true);
        addColumn (datagrid, "no", "订单编号", ControlTypes.TEXT_BOX, 80);
        addColumn (datagrid, "channelOrderNo", "渠道订单编号", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "addTime", "回款日期", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "prodName", "产品名称", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "no", "办理名称", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "payStatus.name", "订单状态", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "no", "关联企业", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "refundStatus.name", "退单状态", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "totalPrice", "原价金额", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "totalPrice", "应付金额", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "paidPrice", "已付金额", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "no", "分期付款", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "no", "开发票", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "owner.name", "业务员", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "no", "操作员", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "no", "下单人", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "sourceType.name", "订单来源", ControlTypes.TEXT_BOX, 100);

        return datagrid;
    }

    @Override
    protected PQueryProject createQueryProject(ResourceNode node) {

        PQueryProject queryProject = super.createQueryProject (node);
        queryProject.toNew ();
        queryProject.setColumnCount (3);
        PQueryItem item = null;
        item = addQueryItem(queryProject, "keyword", "关键字", ControlTypes.TEXT_BOX);
        {
            item.setTooltip("订单编号、渠道订单编号、下单人、下单人电话、签单企业");
            item.setWidth(350);
        }

        addQueryItem (queryProject, "no", "产品名称", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "channelOrderNo", "审核状态", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "channelOrderNo", "付款状态", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "channelOrderNo", "业务员状态", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "channelOrderNo", "订单业绩创建人", ControlTypes.TEXT_BOX);

        addQueryItem (queryProject, "addTime", "订单业绩创建时间", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "addTime", "订单创建时间", ControlTypes.TEXT_BOX);




        return queryProject;
    }

    @Override
    protected void doOperation() {
        ResourceNode node = this.getResourceNode ();
        operationService.addOperation (node, OperationTypes.view);
        operationService.addOperation (node, OperationTypes.add);
        operationService.addOperation (node, OperationTypes.update);
        operationService.addOperation (node, OperationTypes.delete);
    }
}

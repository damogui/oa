package com.gongsibao.panda.supplier.order.workspace.audit;

import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.bd.dic.AuditLogType;
import com.gongsibao.entity.trade.NDepPay;
import com.gongsibao.entity.trade.Pay;
import com.gongsibao.entity.u8.SetOfBooks;
import com.gongsibao.tools.PToolbarHelper;
import com.gongsibao.trade.web.AuditPayListPart;
import com.gongsibao.trade.web.AuditPayPerformanceListPart;
import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.EntityState;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DatagridAlign;
import org.netsharp.panda.dic.OrderbyMode;
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
/*回款业绩审核*/
public class AuditPayPerformanceWorkspaceTest extends WorkspaceCreationBase {
    //private String listrowToolbarPath="/crm/order/audit/roworderrec/toolbar";
    @Before
    public void setup() {
        super.setup ();
        entity = AuditLog.class;
        urlList = "/crm/order/audit/payper/list";
        listPartName = formPartName = "回款业绩审核";//回款业绩审核
        meta = MtableManager.getMtable (entity);
        resourceNodeCode = "Gsb_Supplier_Pay_Audit_Performance";
        listToolbarPath = "";
        listPartImportJs = "/gsb/platform/trade/js/audit/audit-pay-performance-list.js";
        listPartJsController = AuditPayPerformanceListPart.class.getName ();
        listPartServiceController = AuditPayPerformanceListPart.class.getName ();
        listFilter = "type_id=" + AuditLogType.Skyjsh.getValue() + " and add_user_id = '{userId}' ";

    }





//    @Test
//    public void createRowToolbar() {
//
//        ResourceNode node = this.resourceService.byCode(resourceNodeCode);
//        PToolbar toolbar = new PToolbar();
//        {
//            toolbar.toNew();
//            toolbar.setPath(listrowToolbarPath);
//            toolbar.setName("审核");
//            toolbar.setResourceNode(node);
//            toolbar.setToolbarType(ToolbarType.BASE);
//        }
//        PToolbarItem item = new PToolbarItem();
//        {
//            item.toNew();
//            item.setCode("audit");
//            item.setName("审核");
//            item.setSeq(1);
//            item.setCommand("{controller}.audit();");
//            toolbar.getItems().add(item);
//        }
//
//
//        toolbarService.save(toolbar);
//    }


    @Override
    protected PDatagrid createDatagrid(ResourceNode node) {

        PDatagrid datagrid = super.createDatagrid (node);
        {
            datagrid.setName ("回款业绩");
           // datagrid.setToolbar (listrowToolbarPath);
            datagrid.setAutoQuery (true);
            datagrid.setShowCheckbox (true);
            datagrid.setSingleSelect (true);
        }
        PDatagridColumn column = null;
        column=addColumn (datagrid, "id", "操作", ControlTypes.TEXT_BOX, 60, true);{
            column.setFormatter("return controllerauditLogList.optionFormatter(value,row,index);");
            column.setAlign(DatagridAlign.CENTER);
        }
        column = addColumn (datagrid, "soOrder.id", "订单Id", ControlTypes.TEXT_BOX, 120);//需要拼接
        {
            column.setSystem (true);
            column.setVisible (false);

        }
        column = addColumn (datagrid, "soOrder.no", "订单编号", ControlTypes.TEXT_BOX, 120);//需要拼接
        addColumn (datagrid, "soOrder.channelOrderNo", "渠道订单编号", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "soOrder.totalPrice", "原价金额", ControlTypes.DECIMAL_FEN_BOX, 100);
        addColumn (datagrid, "soOrder.payablePrice", "应付金额", ControlTypes.DECIMAL_FEN_BOX, 100);
        addColumn (datagrid, "soOrder.paidPrice", "已经付金额", ControlTypes.DECIMAL_FEN_BOX, 100);
//        addColumn (datagrid, "amount", "我的回款业绩额", ControlTypes.DECIMAL_FEN_BOX, 100);
        addColumn (datagrid, "status", "审核状态", ControlTypes.ENUM_BOX, 100);
        //添加付款账套名称
        addColumn (datagrid, "booksName", "付款账套", ControlTypes.TEXT_BOX, 150);
        //
        addColumn (datagrid, "createTime", "回款业绩创建时间", ControlTypes.DATETIME_BOX, 100);
        addColumn (datagrid, "updateTime", "审核通过时间", ControlTypes.DATETIME_BOX, 100);

        addColumn (datagrid, "soOrder.createTime", "订单创建时间", ControlTypes.DATETIME_BOX, 100);
        addColumn (datagrid, "employee.name", "回款业绩创建人", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "soOrder.owner.name", "业务员", ControlTypes.TEXT_BOX, 100);
        column=addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATETIME_BOX, 60);{

            column.setSystem (true);
            column.setVisible (false);
            column.setOrderbyMode (OrderbyMode.DESC);


        }
        addColumn (datagrid, "remark", "说明", ControlTypes.TEXT_BOX, 100);

        return datagrid;
    }

    @Override
    protected PQueryProject createQueryProject(ResourceNode node) {

        PQueryProject queryProject = super.createQueryProject (node);
        queryProject.toNew ();
        queryProject.setColumnCount (3);

        PQueryItem item = null;
        item = addQueryItem (queryProject, "keyword", "关键字", ControlTypes.TEXT_BOX);
        {
            item.setTooltip ("订单编号");
            item.setWidth (350);
        }


        addQueryItem (queryProject, "status", "审核状态", ControlTypes.ENUM_BOX);
        // addQueryItem (queryProject, "payForOrderCount", "是否一笔多单", ControlTypes.ENUM_BOX);//已经去掉中间表
       // addQueryItem (queryProject, "soOrder.isOnlinePay", "是否在线支付", ControlTypes.BOOLCOMBO_BOX);
        addRefrenceQueryItem(queryProject, "pay.setOfBooks.name", "付款账套", SetOfBooks.class.getSimpleName());//新添加 pay.setOfBooks.name
        addQueryItem (queryProject, "creator", "回款业绩创建人", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "createTime", "回款业绩创建时间", ControlTypes.DATE_BOX);


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

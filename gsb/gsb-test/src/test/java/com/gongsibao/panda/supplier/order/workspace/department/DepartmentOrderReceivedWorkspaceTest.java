package com.gongsibao.panda.supplier.order.workspace.department;

import com.gongsibao.entity.trade.Pay;
import com.gongsibao.trade.web.AuditPayListPart;
import com.gongsibao.trade.web.SalesmanOrderReceivedListPart;
import org.junit.Before;

import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderReceivedWorkspaceTest;
import com.gongsibao.trade.web.department.DepartmentOrderReceivedListPart;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;

/*部门回款业绩*/
public class DepartmentOrderReceivedWorkspaceTest extends WorkspaceCreationBase {
    @Before
    public void setup() {
        super.setup();
        entity = Pay.class;
        urlList = "/crm/order/department/received/list";
        resourceNodeCode = "Gsb_Supplier_Order_Department_Received";
        listPartName = formPartName = "回款业绩列表";//回款业绩
        meta = MtableManager.getMtable (entity);
        //listPartImportJs = "/gsb/platform/trade/js/salesman-order-payperformance-list.js";
        //listFilter = "salesman_id = '{userId}' ";//and deId in(select deid from sp_salesman where empyleId='{}')

        listToolbarPath="";

       // listPartJsController = SalesmanOrderReceivedListPart.class.getName ();
        listPartServiceController = DepartmentOrderReceivedListPart.class.getName();


    }

    @Override
    protected PDatagrid createDatagrid(ResourceNode node) {

        PDatagrid datagrid = super.createDatagrid (node);
        {
            datagrid.setName ("回款业绩");
            datagrid.setToolbar ("panda/datagrid/row/edit");
            datagrid.setAutoQuery (true);
            datagrid.setShowCheckbox (true);
            datagrid.setSingleSelect (false);
        }
        PDatagridColumn column = null;
        addColumn (datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 60, true);
        // addColumn (datagrid, "u8Bank.name", "姓名", ControlTypes.TEXT_BOX, 100);
        column = addColumn (datagrid, "orderIds", "订单编号", ControlTypes.TEXT_BOX, 120);//需要拼接
        {
            // column.setFormatter("return controllerpayList.orderNameFormatter(value,row,index);");

        }
        addColumn (datagrid, "payForOrderCount", "是否一笔多单", ControlTypes.TEXT_BOX, 100);
        addColumn (datagrid, "payWayType", "是否在线支付", ControlTypes.ENUM_BOX, 100);
        addColumn (datagrid, "amount", "付款金额", ControlTypes.DECIMAL_FEN_BOX, 100);
        addColumn (datagrid, "offlineAuditStatus", "审核状态", ControlTypes.ENUM_BOX, 100);
        addColumn (datagrid, "createTime", "回款创建时间", ControlTypes.DATETIME_BOX, 100);
        addColumn (datagrid, "creator", "回款业绩创建人", ControlTypes.TEXT_BOX, 100);


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


        addQueryItem (queryProject, "offlineAuditStatus", "审核状态", ControlTypes.ENUM_BOX);
        addQueryItem (queryProject, "payForOrderCount", "是否一笔多单", ControlTypes.ENUM_BOX);
        addQueryItem (queryProject, "payWayType", "是否在线支付", ControlTypes.ENUM_BOX);

        addQueryItem (queryProject, "creator", "回款业绩创建人", ControlTypes.TEXT_BOX);
        addQueryItem (queryProject, "createTime", "回款业绩创建时间", ControlTypes.DATE_BOX);


        return queryProject;
    }
    @Override
    protected void doOperation() {
        ResourceNode node = this.getResourceNode();
        operationService.addOperation(node, OperationTypes.view);
    }
}

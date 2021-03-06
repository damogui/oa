package com.gongsibao.panda.supplier.order.workspace.salesman;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.EntityState;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DatagridAlign;
import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.dic.PartType;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.panda.entity.PPart;
import org.netsharp.panda.entity.PWorkspace;
import org.netsharp.panda.plugin.dic.ToolbarType;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.ReflectManager;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.tools.PToolbarHelper;
import com.gongsibao.trade.web.OrderPerformanceDetailPart;
import com.gongsibao.trade.web.SoCreatOrderPerformanceFormPart;
import com.gongsibao.trade.web.SoCreatOrderPerformanceListPart;

/**
 * Created by win on 2018/3/5.
 */
/*创建订单业绩*/
public class SoCreatOrderPerformanceWorkspaceTest extends WorkspaceCreationBase {

    @Before
    public void setup() {
        super.setup ();
        entity = SoOrder.class;
        urlForm = "/crm/order/salesman/coperformance";
        listPartName = formPartName = "创建订单业绩";
        meta = MtableManager.getMtable (entity);
        resourceNodeCode = "Gsb_Supplier_Order_Salesman_CoPerformance";
        listToolbarPath = "/crm/roworderaddperformance/toolbar";
        formToolbarPath = "";
        List<String> ss = new ArrayList<String> ();
        //ss.add ("/gsb/platform/trade/js/order/order-performance-form.part.js");
        ss.add ("/gsb/platform/trade/js/salesman-order-performance-add.part.js");
        ss.add ("/gsb/panda-extend/gsb.customer.controls.js");
        formJsImport = StringManager.join ("|", ss);
        listPartJsController = SoCreatOrderPerformanceListPart.class.getName ();
        formServiceController = SoCreatOrderPerformanceFormPart.class.getName ();
    }

    @Test
    public void run() {

        createFormWorkspace ();
    }

    @Test
    public void createListToolbar() {

        ResourceNode node = this.resourceService.byCode (resourceNodeCode);
        //OperationType ot1 = operationTypeService.byCode (OperationTypes.add);
        PToolbar toolbar = new PToolbar ();
        {
            toolbar.toNew ();
            toolbar.setPath (listToolbarPath);
            toolbar.setName ("所有订单操作");
            toolbar.setResourceNode (node);
            toolbar.setToolbarType (ToolbarType.BASE);

        }


        PToolbarItem item = PToolbarHelper.getPToolbarItem (EntityState.New, "performanceDetailAdd", PToolbarHelper.iconAdd,
                "新增", null, 1, "{controller}.add();");//allocation
        toolbar.getItems ().add (item);
        {

        }
        item = PToolbarHelper.getPToolbarItem (EntityState.New, "performanceDetailDel", PToolbarHelper.iconDel,
                "删除", null, 1, "{controller}.remove();");
        toolbar.getItems ().add (item);


        toolbarService.save (toolbar);

    }


    // 默认的表单配置信息
    protected PForm createForm(ResourceNode node) {

        PForm form = super.createForm (node);
        form.setColumnCount (3);
        PFormField formField = null;

        String groupName = null;

        formField = addFormField (form, "no", "订单编号", groupName, ControlTypes.TEXT_BOX, false);
        {
            formField.setReadonly (true);

        }

        formField = addFormField (form, "payablePrice", "订单金额", groupName, ControlTypes.DECIMAL_FEN_BOX, false);
        {
            formField.setReadonly (true);

        }
        /*beg*/
        formField = addFormField (form, "paidPrice", "已付金额", groupName, ControlTypes.DECIMAL_FEN_BOX, false);
        {
            formField.setReadonly (true);

        }
        formField = addFormField (form, "accountName", "客户", groupName, ControlTypes.TEXT_BOX, false);
        {
            formField.setReadonly (true);

        }
        formField = addFormField (form, "accountMobile", "客户电话", groupName, ControlTypes.TEXT_BOX, false);
        {
            formField.setReadonly (true);

        }
        formField = addFormField (form, "createTime", "下单时间", groupName, ControlTypes.TEXT_BOX, false);
        {
            formField.setReadonly (true);

        }
        formField = addFormField (form, "platformSource", "订单来源", groupName, ControlTypes.ENUM_BOX, false);
        {
            formField.setReadonly (true);

        }
        formField = addFormField (form, "payStatus", "付款状态", groupName, ControlTypes.ENUM_BOX, false);
        {
            formField.setReadonly (true);

        }
        formField = addFormField (form, "stageNum", "分期次数", groupName, ControlTypes.ENUM_BOX, false);
        {
            formField.setReadonly (true);

        }
        formField = addFormField (form, "channelOrderNo", "渠道订单号", groupName, ControlTypes.TEXT_BOX, false);
        {
            formField.setReadonly (true);

        }
     /*end*/


        formField = addFormField (form, "unPerformance", "未划分业绩额", groupName, ControlTypes.DECIMAL_FEN_BOX, false);
        {
            formField.setReadonly (true);

        }
        formField = addFormField (form, "remark", "备注", groupName, ControlTypes.TEXT_BOX, false);
        {
            formField.setReadonly (true);
        }

        return form;
    }

    protected void addDetailGridPart(PWorkspace workspace) {

        // 业绩划分
        performancePart (workspace);
    }


    // 业绩划分
    public void performancePart(PWorkspace workspace) {

        ResourceNode node = this.resourceService.byCode ("Gsb_Supplier_Order_Salesman_performance");
        PDatagrid datagrid = new PDatagrid (node, "业绩划分");
        {
            datagrid.setShowCheckbox (true);
            datagrid.setSingleSelect (false);
            datagrid.setReadOnly (false);
            datagrid.setShowTitle (true);
            datagrid.setToolbar (listToolbarPath);//新增和删除
            datagrid.setFit (true);
            PDatagridColumn column = null;


            column = addColumn (datagrid, "supplier.name", "服务商", ControlTypes.TEXT_BOX, 150);

            column = addColumn (datagrid, "department.name", "部门", ControlTypes.TEXT_BOX, 150);
            {

                column.setAlign (DatagridAlign.CENTER);
            }

            column = addColumn (datagrid, "salesman.name", "业务员", ControlTypes.TEXT_BOX, 150);
            column = addColumn (datagrid, "amount", "分配金额", ControlTypes.DECIMAL_FEN_BOX, 150);

        }
/*表单beg*/
        PForm form = new PForm ();
        {
            form.toNew ();
            form.setResourceNode (node);
            form.setColumnCount (1);
            form.setName ("业绩划分金额");

            // form.setTag ();//设置提示
            form.setLabelWidth (100);

            PFormField formField = null;
            formField = addFormFieldRefrence (form, "supplier.name", "服务商", null,"OrderSupplier", true, false);
            {
                formField.setTroikaTrigger ("controllerdepReceivable.supplierChange(newValue,oldValue);");
            }

            formField = addFormFieldRefrence (form, "department.name", "部门", null, "OrderSupplierDepartment", true, false);
            {
                formField.setTroikaTrigger ("controllerdepReceivable.departmentChange(newValue,oldValue);");
            }

            formField = addFormFieldRefrence (form, "salesman.name", "业务员", null, "OrderSalesman", true, false);
            {
                formField.setTroikaTrigger("controllerdepReceivable.salesmanChange(newValue,oldValue);");
            }

            formField = addFormField (form, "amount", "分配金额", null, ControlTypes.DECIMAL_FEN_BOX, true, false);

            {
//                //formField.setWidth (300);
//              formField.setHeight (100);

            }

        }

            /*表单end*/
        PPart part = new PPart ();
        {
            part.toNew ();
            part.setName ("业绩划分");
            part.setCode ("depReceivable");
            part.setParentCode (ReflectManager.getFieldName (meta.getCode ()));
            part.setRelationRole ("depReceivable");
            part.setResourceNode (node);
            part.setPartTypeId (PartType.DETAIL_PART.getId ());
            part.setDatagrid (datagrid);
            part.setDockStyle (DockType.DOCUMENTHOST);
            part.setToolbar (listToolbarPath);
            part.setForm (form);
            part.setWindowWidth (400);
            part.setWindowHeight (300);
           // part.setStyle ("  width: 50%;height60%");

//         part.setServiceController (OrderPerformanceDetailPart.class.getName ());
            part.setJsController (OrderPerformanceDetailPart.class.getName ());
        }
        workspace.getParts ().add (part);

        part = workspace.getParts ().get (0);
        {
            part.setName ("创建订单业绩");
            part.setDockStyle (DockType.TOP);
            part.setHeight (500);
            part.setHeight (450);
        }
    }

    // 默认的表单操作
    @Override
    protected void doOperation() {

        ResourceNode node = this.getResourceNode ();
        operationService.addOperation (node, OperationTypes.view);
        operationService.addOperation (node, OperationTypes.add);
    }
}

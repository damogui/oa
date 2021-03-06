package com.gongsibao.panda.supplier.sys.workspace;

import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.base.IOperationTypeService;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.organization.entity.Role;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DatagridAlign;
import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.dic.PartType;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.panda.entity.PPart;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.entity.PWorkspace;
import org.netsharp.panda.plugin.dic.ToolbarType;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.ReflectManager;

import com.gongsibao.controls.DictComboBox;
import com.gongsibao.crm.web.SalesmanProductDetailPart;
import com.gongsibao.crm.web.SysSalesmanListPart;
import com.gongsibao.crm.web.SysSalesmanTreePart;
import com.gongsibao.entity.product.Product;
import com.gongsibao.entity.supplier.Salesman;

//员工管理
public class SysSalesmanWorkspaceTest extends WorkspaceCreationBase {

    protected String treeResourceNodeCode = "GSB_CRM_SYS_DEPARTMENT";

    protected String productDetailResourceNodeCode = "GSB_CRM_SYS_Department_Product";

    protected String roleDetailResourceNodeCode = "GSB_CRM_SYS_SALESMAN_ADDROLE";

    private String listrowToolbarPath = "/crm/rowsalesman/toolbar";//员工rowbar

    public void setup() {
        super.setup();
        urlList = "/crm/sys/salesman/list";
        urlForm = "/crm/sys/salesman/form";
        entity = Salesman.class;
        meta = MtableManager.getMtable(entity);
        formPartName = "员工信息";
        listPartName = "员工管理";
        resourceNodeCode = "GSB_CRM_SYS_SALESMAN";
        formOpenMode = OpenMode.WINDOW;
        openWindowHeight = 550;
        openWindowWidth = 1020;
        listPartImportJs = "/gsb/supplier/sys/organization/js/sys-salesman-list-part.js|/gsb/panda-extend/gsb.custom.query.controls.js";
        listPartJsController = SysSalesmanListPart.class.getName();
        listPartServiceController = SysSalesmanListPart.class.getName();
        formJsController = "com.gongsibao.crm.web.SalesmanFormPart";
        formJsImport = "/gsb/supplier/sys/organization/js/salesman-form.part.js|/gsb/panda-extend/gsb.customer.controls.js";
    }

    @Test
    public void createRowToolbar() {

        ResourceNode node = this.resourceService.byCode(resourceNodeCode);
        PToolbar toolbar = new PToolbar();
        {
            toolbar.toNew();
            toolbar.setPath(listrowToolbarPath);
            toolbar.setName("操作");
            toolbar.setResourceNode(node);
            toolbar.setToolbarType(ToolbarType.BASE);
        }
        PToolbarItem item = new PToolbarItem();
        {
            item.toNew();
            item.setCode("detail");
            item.setName("查看");
            item.setSeq(1);
            item.setCommand("{controller}.detail();");
            toolbar.getItems().add(item);
        }
         item = new PToolbarItem();
        {
            item.toNew();
            item.setCode("edit");
            item.setName("修改");
            item.setSeq(1);
            item.setCommand("{controller}.edit();");
            toolbar.getItems().add(item);
        }
        toolbarService.save(toolbar);
    }

    @Override
    protected PDatagrid createDatagrid(ResourceNode node) {

        PDatagrid datagrid = super.createDatagrid(node);
        {
            datagrid.setName("员工管理");
            datagrid.setToolbar(listrowToolbarPath);
            datagrid.setAutoQuery(false);
        }
        PDatagridColumn column = null;
        addColumn(datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 100, true);
        addColumn(datagrid, "employee.name", "姓名", ControlTypes.TEXT_BOX, 80);
        addColumn(datagrid, "employee.loginName", "帐号", ControlTypes.TEXT_BOX, 100);
        column = addColumn(datagrid, "disabled", "状态", ControlTypes.TEXT_BOX, 80);
        {
            column.setAlign(DatagridAlign.CENTER);
            column.setFormatter("return controllersalesman.disabledFormatter(value,row,index);");
        }
        column = addColumn(datagrid, "receiving", "接单", ControlTypes.TEXT_BOX, 80);
        {
            column.setAlign(DatagridAlign.CENTER);
            column.setFormatter("return controllersalesman.receivingFormatter(value,row,index);");
        }

        addColumn(datagrid, "department.name", "部门", ControlTypes.TEXT_BOX, 120);
        column = addColumn(datagrid, "employee.loginNum", "登录次数", ControlTypes.TEXT_BOX, 100);
        {
            column.setAlign(DatagridAlign.CENTER);
        }

        addColumn(datagrid, "isLeader", "主管", ControlTypes.BOOLCOMBO_BOX, 80);
        column = addColumn(datagrid, "isNotify", "接收消息", ControlTypes.TEXT_BOX, 80);
        {
            column.setAlign(DatagridAlign.CENTER);
            column.setFormatter("return controllersalesman.isNotifyFormatter(value,row,index);");
        }
        addColumn(datagrid, "dayMax", "日分配上限", ControlTypes.TEXT_BOX, 80);
        addColumn(datagrid, "weekMax", "周分配上限", ControlTypes.TEXT_BOX, 80);
        addColumn(datagrid, "xabMax", "XAB类上限", ControlTypes.TEXT_BOX, 80);
        addColumn(datagrid, "creator", "创建人", ControlTypes.TEXT_BOX, 80);
        addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATETIME_BOX, 130);
        addColumn(datagrid, "updator", "最后修改人", ControlTypes.TEXT_BOX, 80);
        addColumn(datagrid, "updateTime", "修改时间", ControlTypes.DATETIME_BOX, 130);

        return datagrid;
    }

    @Override
    protected PQueryProject createQueryProject(ResourceNode node) {

        PQueryProject queryProject = super.createQueryProject(node);
        queryProject.toNew();

        addQueryItem(queryProject, "employee.name", "姓名", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "employee.mobile", "手机号", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "employee.loginName", "帐号", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "creator", "创建人", ControlTypes.TEXT_BOX);
        addQueryItem(queryProject, "disabled", "停用状态", ControlTypes.BOOLCOMBO_BOX);
        return queryProject;
    }

    // 表单填充字段
    protected PForm createForm(ResourceNode node) {

        PForm form = super.createForm(node);
        form.setColumnCount(3);

        String groupName = "基本信息 ";
        PFormField formField = addFormField(form, "name", "姓名", groupName, ControlTypes.TEXT_BOX, true);
        {

            formField.setTroikaValidation("['maxLength[50]']");
        }
        addFormField(form, "mobile", "手机号", groupName, ControlTypes.TEXT_BOX, true);
        formField = addFormField(form, "loginName", "帐号", groupName, ControlTypes.TEXT_BOX, false);
        {

            formField.setReadonly(true);
            formField.setTooltip("根据手机号自动生成");
        }
        addFormField(form, "email", "邮箱", groupName, ControlTypes.TEXT_BOX, false);
        addFormField(form, "entryDate", "入职日期", groupName, ControlTypes.DATE_BOX, false);
        addFormField(form, "quitDate", "离职日期", groupName, ControlTypes.DATE_BOX, false);

        groupName = "属性设置";
        addFormField(form, "dayMax", "日分配上限", groupName, ControlTypes.NUMBER_BOX, false, false);

        addFormField(form, "weekMax", "周分配上限", groupName, ControlTypes.NUMBER_BOX, false, false);

        addFormField(form, "xabMax", "XAB类上限", groupName, ControlTypes.NUMBER_BOX, false, false);

        addFormField(form, "receiving", "自动接受商机", groupName, ControlTypes.SWITCH_BUTTON, false, false);

        addFormField(form, "isLeader", "主管", groupName, ControlTypes.SWITCH_BUTTON, false, false);
        
        addFormField(form, "isNotify", "接收消息", groupName, ControlTypes.SWITCH_BUTTON, false, false);
        
        addFormField(form, "disabled", "停用", groupName, ControlTypes.SWITCH_BUTTON, false, true);
        
        groupName = "设置密码";
        formField = addFormField(form, "newPassword", "设置密码", groupName, ControlTypes.PASSWORDTEXT_BOX, false, false);
        {

            formField.setTroikaValidation("['minLength[6]','maxLength[10]']");
        }

        formField = addFormField(form, "confirmPassword", "确认密码", groupName, ControlTypes.PASSWORDTEXT_BOX, false, false);
        {

            formField.setTroikaValidation("['equals[\\'#newPassword\\']']");
        }


        // 这里还有很多属性，

        return form;
    }

    @Test
    @Override
    public void run() {

        this.createTreeWorkspace();
        this.createFormWorkspace();
    }

    // 配置树状结构
    public void createTreeWorkspace() {

        ResourceNode node = resourceService.byCode(treeResourceNodeCode);// 树状的节点
        IOperationTypeService operationTypeService = ServiceFactory.create(IOperationTypeService.class);
        OperationType operationType = operationTypeService.byCode(OperationTypes.view);

        PWorkspace workspace = new PWorkspace();
        {
            workspace.toNew();
            workspace.setResourceNode(node);
            workspace.setOperationType(operationType);
            workspace.setOperationTypeId(operationType.getId());
            workspace.setName("员工管理");
            workspace.setUrl(urlList);
        }

        PPart part = new PPart();// 创建部分
        {
            part.toNew();
            part.setCode("GsbCrmSysDepartmentTree");// 树名
            part.setPartTypeId(org.netsharp.panda.dic.PartType.TREE_PART.getId());
            part.setDockStyle(DockType.LEFT);
            part.setStyle("width:250px;");
            part.setResourceNode(node);
            part.setName("部门");
            part.setHeaderVisible(true);
            // 进行扩展
            // part.setToolbar(listToolbarPath);

            part.setJsController(SysSalesmanTreePart.class.getName());
            part.setServiceController(SysSalesmanTreePart.class.getName());
            part.setImports("/gsb/supplier/sys/organization/js/sys-salesman-tree-part.js");
            part.setAutoQuery(false);
        }
        workspace.getParts().add(part);

        ResourceNode node2 = resourceService.byCode(resourceNodeCode);
        PDatagrid datagrid = this.createDatagrid(node2);
        part = new PPart();
        {
            part.toNew();
            part.setCode("salesman");
            part.setParentCode("GsbCrmSysDepartmentTree");// 点击父之后，刷新自己
            part.setPartTypeId(org.netsharp.panda.dic.PartType.DATAGRID_PART.getId());
            part.setDatagrid(datagrid);
            part.setDockStyle(DockType.DOCUMENTHOST);
            part.setRelationRole("departmentId");// 点击父之后，刷新自己所传的参数
            part.setResourceNode(node2);
            part.setUrl(urlForm);
            part.setOpenMode(formOpenMode);
            part.setWindowHeight(550);
            part.setWindowWidth(1020);
            part.setToolbar(listToolbarPath);
            part.setJsController(listPartJsController);
            part.setServiceController(listPartServiceController);
            part.setImports(listPartImportJs);
        }

        workspace.getParts().add(part);

        workspaceService.save(workspace);
    }

    // 创建明细里面的弹窗操作
    @Override
    protected void addDetailGridPart(PWorkspace workspace) {

        // 服务范围
        addScopesDetailPart(workspace);

        // 添加角色
        addRolesDetailPart(workspace);
    }

    // 添加角色
    private void addRolesDetailPart(PWorkspace workspace) {

        ResourceNode node = this.resourceService.byCode(roleDetailResourceNodeCode);
        PDatagrid datagrid = new PDatagrid(node, "角色信息");
        datagrid.setShowCheckbox(true);
        datagrid.setSingleSelect(false);
        datagrid.setReadOnly(true);

        PDatagridColumn column = null;

        addColumn(datagrid, "role.name", "角色", ControlTypes.TEXT_BOX, 100);
        column = addColumn(datagrid, "creator", "创建人", ControlTypes.TEXT_BOX, 100, false, null, null, null);
        {
            column.setAlign(DatagridAlign.CENTER);
        }
        addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATETIME_BOX, 130, false, null, null, null);
        column = addColumn(datagrid, "updator", "最后修改人", ControlTypes.TEXT_BOX, 100, false, null, null, null);
        {
            column.setAlign(DatagridAlign.CENTER);
        }
        addColumn(datagrid, "updateTime", "修改时间", ControlTypes.DATETIME_BOX, 130, false, null, null, null);

        PForm form = new PForm();// 添加表单
        {
            form.setResourceNode(node);
            form.toNew();
            form.setColumnCount(2);
            form.setName("角色信息");

            PFormField field = null;
            field = addFormFieldRefrence(form, "role.name", "角色", null, Role.class.getSimpleName(), true, false);
            {

            }
        }

        PPart part = new PPart();
        {
            part.toNew();
            part.setName("角色信息");
            part.setCode("roles");
            part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
            part.setRelationRole("roles");
            part.setResourceNode(node);
            part.setPartTypeId(PartType.DETAIL_PART.getId());
            part.setDatagrid(datagrid);
            part.setDockStyle(DockType.DOCUMENTHOST);
            part.setToolbar("panda/datagrid/detail");
            part.setWindowWidth(400);
            part.setWindowHeight(200);
            part.setForm(form);
        }
        workspace.getParts().add(part);

        part = workspace.getParts().get(0);
        {
            part.setName("基本信息");
//			part.setDockStyle(DockType.TOP);
//			part.setHeight(350);
        }
    }

    private void addScopesDetailPart(PWorkspace workspace) {

        ResourceNode node = this.resourceService.byCode(productDetailResourceNodeCode);
        PDatagrid datagrid = new PDatagrid(node, "服务范围");
        {
            PDatagridColumn column = null;
            addColumn(datagrid, "productCategory1.name", "一级分类", ControlTypes.TEXT_BOX, 100, false);
            addColumn(datagrid, "productCategory2.name", "二级分类", ControlTypes.TEXT_BOX, 100, false);
            addColumn(datagrid, "product.name", "产品", ControlTypes.TEXT_BOX, 200, false);
            addColumn(datagrid, "province.name", "省", ControlTypes.TEXT_BOX, 150, false);
            addColumn(datagrid, "city.name", "市", ControlTypes.TEXT_BOX, 150, false);
            addColumn(datagrid, "county.name", "区", ControlTypes.TEXT_BOX, 150, false);

        }

        PForm form = new PForm();// 添加表单
        {
            form.setResourceNode(node);
            form.toNew();
            form.setColumnCount(1);
            form.setName("服务范围");

            PFormField formField = null;
            formField = addFormField(form, "productCategory1.name", "一级分类", null, ControlTypes.CUSTOM, true, false);
            {
                formField.setWidth(200);
                formField.setCustomControlType(DictComboBox.class.getName());
                formField.setTroikaTrigger("controllerproducts.firstProductCategorySelect(newValue,oldValue);");
                // formField.setRefFilter("type=201 and pid=0");

                // 这里先不查询，前端要根据上级部门进行过滤
                formField.setRefFilter("1=2");
            }

            formField = addFormField(form, "productCategory2.name", "二级分类", null, ControlTypes.CUSTOM, false, false);
            {
                formField.setWidth(200);
                formField.setCustomControlType(DictComboBox.class.getName());
                formField.setTroikaTrigger("controllerproducts.secondProductCategorySelect(newValue,oldValue);");
                // formField.setRefFilter("type=201 and pid<>0");
            }

            formField = addFormFieldRefrence(form, "product.name", "产品", null, "CRM_" + Product.class.getSimpleName(), false, false);
            {
                formField.setWidth(200);
                formField.setRefFilter("enabled=1");
            }
            formField = addFormField(form, "province.name", "省份", ControlTypes.CUSTOM, false, false);
            {
                formField.setWidth(200);
                formField.setCustomControlType(DictComboBox.class.getName());
                formField.setTroikaTrigger("controllerproducts.provinceSelect(newValue,oldValue);");
                // formField.setDataOptions("level:1,changeCtrlId:'city_name'");
            }
            formField = addFormField(form, "city.name", "城市", ControlTypes.CUSTOM, false, false);
            {
                formField.setWidth(200);
                formField.setCustomControlType(DictComboBox.class.getName());
                formField.setTroikaTrigger("controllerproducts.citySelect(newValue,oldValue);");
                // formField.setDataOptions("level:2,changeCtrlId:'county_name'");
            }
            formField = addFormField(form, "county.name", "区/县", ControlTypes.CUSTOM, false, false);
            {
                formField.setWidth(200);
                formField.setCustomControlType(DictComboBox.class.getName());
                // formField.setDataOptions("level:3");
            }

        }

        PPart part = new PPart();
        {
            part.toNew();
            part.setName("服务范围");
            part.setCode("products");
            part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
            part.setRelationRole("products");
            part.setResourceNode(node);
            part.setPartTypeId(PartType.DETAIL_PART.getId());
            part.setDatagrid(datagrid);
            part.setDockStyle(DockType.DOCUMENTHOST);
            part.setToolbar("panda/datagrid/detail");
            part.setJsController(SalesmanProductDetailPart.class.getName());
            part.setServiceController(SalesmanProductDetailPart.class.getName());
            part.setWindowWidth(400);
            part.setWindowHeight(450);
            part.setForm(form);
        }
        workspace.getParts().add(part);

    }

    @Override
    protected void doOperation() {
        ResourceNode node = this.getResourceNode();
        operationService.addOperation(node, OperationTypes.view);
        operationService.addOperation(node, OperationTypes.add);
        operationService.addOperation(node, OperationTypes.update);
    }
}

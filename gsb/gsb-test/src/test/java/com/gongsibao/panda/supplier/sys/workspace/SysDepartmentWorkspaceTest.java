package com.gongsibao.panda.supplier.sys.workspace;

import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
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
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.ReflectManager;

import com.gongsibao.controls.DictComboBox;
import com.gongsibao.crm.web.DepartmentProductDetailPart;
import com.gongsibao.crm.web.SysDepartmentTreeGridPart;
import com.gongsibao.entity.product.Product;
import com.gongsibao.entity.supplier.SupplierDepartment;

//部门管理工作空间
public class SysDepartmentWorkspaceTest extends WorkspaceCreationBase {
	

	protected String productDetailResourceNodeCode = "GSB_CRM_SYS_Department_Product";
	
	public void setup() {
		super.setup();
		urlList = "/crm/sys/department/list";
		urlForm = "/crm/sys/department/form";
		entity = SupplierDepartment.class;
		meta = MtableManager.getMtable(entity);
		listPartType = PartType.TREEGRID_PART.getId();
		formPartName = "部门信息";
		listPartName = "部门管理";
		resourceNodeCode = "GSB_CRM_SYS_DEPARTMENT";
		formOpenMode = OpenMode.WINDOW;
		openWindowHeight = 650;
		openWindowWidth = 900;
		listPartImportJs="/gsb/supplier/sys/organization/js/sys-department-list-part.js|/gsb/panda-extend/gsb.custom.query.controls.js";
		listPartJsController = SysDepartmentTreeGridPart.class.getName();
		listPartServiceController = SysDepartmentTreeGridPart.class.getName();
        formJsImport = "/gsb/supplier/sys/organization/js/department-form.part.js|/gsb/panda-extend/gsb.customer.controls.js";
        
        listToolbarPath = "/crm/sys/supplier/department/toolbar";
	}
	
	@Test
	public void createToolbar() {
		ResourceNode node = this.getResourceNode();
		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setBasePath("panda/datagrid/edit");
			toolbar.setPath(listToolbarPath);
			toolbar.setName("部门管理");
			toolbar.setResourceNode(node);
		}
		addToolbarItem(toolbar, "reload", "刷新", "fa-refresh", "reload()", null, 5);
		toolbarService.save(toolbar);
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.setName("部门管理");
			datagrid.setAutoQuery(false);
		}
		PDatagridColumn column = null;

		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "memoto", "备注", ControlTypes.TEXT_BOX, 300);
		addColumn(datagrid, "creator", "创建人", ControlTypes.TEXT_BOX, 80);
		addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATETIME_BOX, 130);
		column = addColumn(datagrid, "parentId", "parentId", ControlTypes.TEXT_BOX, 100);
		{
			column.setSystem(true);
			column.setVisible(false);
		}
		column = addColumn(datagrid, "isLeaf", "isLeaf", ControlTypes.TEXT_BOX, 100);
		{
			column.setSystem(true);
			column.setVisible(false);
		}
		return datagrid;
	}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "name", "部门名称", ControlTypes.TEXT_BOX);
		return queryProject;
	}

	// 表单填充字段
	protected PForm createForm(ResourceNode node) {

		PForm form = super.createForm(node);
		form.setColumnCount(2);
		PFormField formField = addFormField(form, "name", "名称", null, ControlTypes.TEXT_BOX, true);{
			formField.setTroikaValidation("['maxLength[50]']");
		}
		addFormField(form, "customerType", "分组类别", null, ControlTypes.ENUM_BOX, true);
		
		 formField = addFormField(form, "memoto", "备注", ControlTypes.TEXTAREA, false, false);{
			
			formField.setHeight(100);
			formField.setWidth(500);
			formField.setFullColumn(false);
			formField.setTroikaValidation("[\'maxLength[500]\']");
		}
		return form;
	}



//    @Test
//    @Override
//    public void run() {
//
//
//        this.createFormWorkspace();
//    }
    // 创建明细里面的弹窗操作
    @Override
    protected void addDetailGridPart(PWorkspace workspace) {
        // 服务范围
        addScopesDetailPart(workspace);
    }

    private void addScopesDetailPart(PWorkspace workspace) {

        ResourceNode node = this.resourceService.byCode(productDetailResourceNodeCode);
        PDatagrid datagrid = new PDatagrid(node, "服务范围");
        {
        	datagrid.setShowTitle(true);
            PDatagridColumn column = null;
            addColumn(datagrid, "productCategory1.name", "一级分类", ControlTypes.TEXT_BOX, 100, false);
            addColumn(datagrid, "productCategory2.name", "二级分类", ControlTypes.TEXT_BOX, 100, false);
            addColumn(datagrid, "product.name", "产品", ControlTypes.TEXT_BOX, 200, false);
            addColumn(datagrid, "province.name", "省", ControlTypes.TEXT_BOX, 100, false);
            addColumn(datagrid, "city.name", "市", ControlTypes.TEXT_BOX, 100, false);
            addColumn(datagrid, "county.name", "区", ControlTypes.TEXT_BOX, 100, false);

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
                formField.setTroikaTrigger("controllerserviceProducts.firstProductCategorySelect(newValue,oldValue);");
//                formField.setRefFilter("type=201 and pid=0");
                
                //这里先不查询，前端要根据上级部门进行过滤
                formField.setRefFilter("1=2");
            }

            formField = addFormField(form, "productCategory2.name", "二级分类", null, ControlTypes.CUSTOM, false, false);
            {
                formField.setWidth(200);
                formField.setCustomControlType(DictComboBox.class.getName());
                formField.setTroikaTrigger("controllerserviceProducts.secondProductCategorySelect(newValue,oldValue);");
                formField.setRefFilter("type=201 and pid<>0");
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
                formField.setTroikaTrigger("controllerserviceProducts.provinceSelect(newValue,oldValue);");
                //formField.setDataOptions("level:1,changeCtrlId:'city_name'");
            }
            formField = addFormField(form, "city.name", "城市", ControlTypes.CUSTOM, false, false);
            {
                formField.setWidth(200);
                formField.setCustomControlType(DictComboBox.class.getName());
                formField.setTroikaTrigger("controllerserviceProducts.citySelect(newValue,oldValue);");
                //formField.setDataOptions("level:2,changeCtrlId:'county_name'");
            }
            formField = addFormField(form, "county.name", "区/县", ControlTypes.CUSTOM, false, false);
            {
                formField.setWidth(200);
                formField.setCustomControlType(DictComboBox.class.getName());
                //formField.setDataOptions("level:3");
            }

        }

        PPart part = new PPart();
        {
            part.toNew();
            part.setName("服务范围");
            part.setCode("serviceProducts");
            part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
            part.setRelationRole("serviceProducts");
            part.setResourceNode(node);
            part.setPartTypeId(PartType.DETAIL_PART.getId());
            part.setDatagrid(datagrid);
            part.setDockStyle(DockType.DOCUMENTHOST);
            part.setToolbar("panda/datagrid/detail");
            part.setJsController(DepartmentProductDetailPart.class.getName());
            part.setServiceController(DepartmentProductDetailPart.class.getName());
            part.setWindowWidth(400);
            part.setWindowHeight(450);
            part.setForm(form);
        }
        workspace.getParts().add(part);
        part = workspace.getParts().get(0);
        {
            part.setName("基本信息");
            part.setDockStyle(DockType.TOP);
        }
    }



    @Override
	protected void doOperation() {
		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node, OperationTypes.view);
		operationService.addOperation(node, OperationTypes.add);
		operationService.addOperation(node, OperationTypes.update);
		operationService.addOperation(node, OperationTypes.delete);
	}

}

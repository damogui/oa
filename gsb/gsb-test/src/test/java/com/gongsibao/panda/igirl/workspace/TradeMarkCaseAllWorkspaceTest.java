package com.gongsibao.panda.igirl.workspace;
import com.gongsibao.entity.igirl.TradeMarkCase;
import com.gongsibao.igirl.web.TradeMarkCasePart;
import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.resourcenode.entity.ResourceNode;
public class TradeMarkCaseAllWorkspaceTest extends WorkspaceCreationBase {

	@Override
	@Before
	public void setup() {

		formToolbarPath = "igirl/trademarkcase/form";
		urlList = "/igirl/trademarkcase/all/list";
		urlForm = "/igirl/trademarkcase/all/form";
		entity = TradeMarkCase.class;
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = "商标方案";
		resourceNodeCode = "IGIRL_All_TradeMarkCase";
		formServiceController = TradeMarkCasePart.class.getName();
		formJsController = TradeMarkCasePart.class.getName();
		formJsImport = "/gsb/igirl/js/markcase.form.part.js";
	}
//
//	@Test
//	public void fromToolbar() {
//
//		ResourceNode node = this.resourceService.byCode(CustomerFollow.class.getSimpleName());
//		OperationType ot1 = operationTypeService.byCode(OperationTypes.add);
//
//		PToolbar toolbar = new PToolbar();
//		{
//			toolbar.toNew();
//			toolbar.setBasePath("panda/form/edit");
//			toolbar.setPath("crm/customer/form");
//			toolbar.setName("客户表单");
//			toolbar.setResourceNode(node);
//			toolbar.setToolbarType(ToolbarType.BASE);
//		}
//
//		PToolbarItem item = new PToolbarItem();
//		{
//			item.toNew();
//			item.setCode("edit");
//			item.setIcon("fa fa-edit");
//			item.setName("编辑");
//			item.setCommand(null);
//			item.setOperationType(ot1);
//			item.setSeq(3000);
//			item.setCommand("{controller}.edit();");
//			toolbar.getItems().add(item);
//		}
//		item = new PToolbarItem();
//		{
//			item.toNew();
//			item.setCode("matching");
//			item.setIcon("fa fa-drivers-license");
//			item.setName("客户ID");
//			item.setCommand(null);
//			item.setOperationType(ot1);
//			item.setSeq(4000);
//			item.setCommand("{controller}.matching();");
//			toolbar.getItems().add(item);
//		}
//		item = new PToolbarItem();
//		{
//			item.toNew();
//			item.setCode("follow");
//			item.setIcon("fa fa-mail-reply-all");
//			item.setName("跟进");
//			item.setCommand(null);
//			item.setOperationType(ot1);
//			item.setSeq(5000);
//			item.setCommand("{controller}.follow();");
//			toolbar.getItems().add(item);
//		}
//
//		toolbarService.save(toolbar);
//	}
//
	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		datagrid.setToolbar("panda/datagrid/row/edit");
		PDatagridColumn column = null;

		addColumn(datagrid, "code", "编码", ControlTypes.TEXT_BOX, 100, true);
		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 80);
		addColumn(datagrid, "accountId", "是否会员", ControlTypes.BOOLCOMBO_BOX, 100);

		column = addColumn(datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 100, true);
		return datagrid;
	}
//
//	@Override
//	protected PForm createForm(ResourceNode node) {
//
//		PForm form = new PForm();
//		{
//			form.toNew();
//			form.setResourceNode(node);
//			form.setName(this.meta.getName() + "表单");
//			form.setColumnCount(3);
//		}
//
//		PFormField formField = null;
//
//		String groupName = null;
//		addFormField(form, "realName", "姓名", groupName, ControlTypes.TEXT_BOX, false, false);
//		addFormField(form, "sex", "性别", groupName, ControlTypes.RADIO_BOX_GROUP, false, false);
//		formField = addFormField(form, "mobile", "手机", groupName, ControlTypes.TEXT_BOX, true, false);
//		{
//			formField.setTroikaTrigger("controllercustomer.contactWayChange(this);");
//			formField.setTroikaValidation("validationContactWay['mobile','手机']");
//		}
//		formField = addFormField(form, "telephone", "座机", groupName, ControlTypes.TEXT_BOX, true, false);
//		{
//			formField.setTroikaTrigger("controllercustomer.contactWayChange(this);");
//			formField.setTroikaValidation("validationContactWay['telephone','座机']");
//		}
//		formField = addFormField(form, "weixin", "微信", groupName, ControlTypes.TEXT_BOX, true, false);
//		{
//
//			formField.setTroikaTrigger("controllercustomer.contactWayChange(this);");
//			formField.setTroikaValidation("validationContactWay['weixin','微信']");
//		}
//		formField = addFormField(form, "qq", "QQ", groupName, ControlTypes.TEXT_BOX, true, false);
//		{
//
//			formField.setTroikaTrigger("controllercustomer.contactWayChange(this);");
//			formField.setTroikaValidation("validationContactWay['qq','QQ']");
//		}
//		addFormField(form, "email", "Email", groupName, ControlTypes.TEXT_BOX, false, false);
//		addFormField(form, "birdthday", "生日", groupName, ControlTypes.DATE_BOX, false, false);
//
//		formField = addFormField(form, "allocationType", "分配方式", groupName, ControlTypes.RADIO_BOX_GROUP, true, false);
//		{
//			formField.setTroikaTrigger("controllercustomer.allocationTypeChange(newValue, oldValue);");
//		}
//
//		formField = addFormField(form, "customerSource.name", "客户来源", groupName, ControlTypes.CUSTOM, true, false);
//		{
//			formField.setCustomControlType(DictComboBox.class.getName());
//			formField.setRefFilter("type=411");
//		}
//
//		formField = addFormField(form, "consultWay", "咨询途径", groupName, ControlTypes.ENUM_BOX, true, false);
//		{
//
//			formField.setTroikaTrigger("controllercustomer.consultWayChange(newValue, oldValue);");
//		}
//
//		formField = addFormField(form, "allocationOrgId", "分配部门", groupName, ControlTypes.CUSTOM, true, true);
//		{
//
//			formField.setCustomControlType(OrganizationComboBox.class.getName());
//		}
//
//		formField = addFormField(form, "customerSourceOther", "其它客户来源", groupName, ControlTypes.TEXT_BOX, false, true);
//		{
//
//		}
//
//		formField = addFormField(form, "consultWayOther", "其它咨询途径", groupName, ControlTypes.TEXT_BOX, false, true);
//		{
//
//		}
//
//		formField = addFormField(form, "fProvince.name", "所在省份", groupName, ControlTypes.CUSTOM, false, false);
//		{
//
//			formField.setCustomControlType(CityComboBox.class.getName());
//			formField.setDataOptions("level:1,changeCtrlId:'fCity_name'");
//		}
//
//		formField = addFormField(form, "fCity.name", "所在城市", groupName, ControlTypes.CUSTOM, false, false);
//		{
//
//			formField.setCustomControlType(CityComboBox.class.getName());
//			formField.setDataOptions("level:2,changeCtrlId:'fCounty_name'");
//		}
//
//		formField = addFormField(form, "fCounty.name", "所在区/县", groupName, ControlTypes.CUSTOM, false, false);
//		{
//
//			formField.setCustomControlType(CityComboBox.class.getName());
//			formField.setDataOptions("level:3");
//		}
//
//		addFormField(form, "addr", "地址", groupName, ControlTypes.TEXT_BOX, false, false);
//		addFormField(form, "important", "客户等级", groupName, ControlTypes.ENUM_BOX, false, false);
//		formField = addFormField(form, "followStatus", "沟通状态", groupName, ControlTypes.ENUM_BOX, true, false);
//		{
//
//			formField.setTroikaTrigger("controllercustomer.followStatusChange(newValue, oldValue);");
//		}
//		addFormField(form, "unvalidRemark", "沟通无效原因", groupName, ControlTypes.TEXT_BOX, false, true);
//		addFormField(form, "maybeRemark", "潜在原因", groupName, ControlTypes.TEXT_BOX, false, true);
//		formField = addFormField(form, "remark", "售前备注", groupName, ControlTypes.TEXTAREA, true, false);
//		{
//
//			formField.setFullColumn(false);
//			formField.setWidth(180);
//		}
//		formField = addFormField(form, "smsRemark", "短信备注", groupName, ControlTypes.TEXTAREA, false, false);
//		{
//			formField.setFullColumn(false);
//			formField.setWidth(180);
//		}
//		formField = addFormField(form, "swtCustomerId", "商务通Id", groupName, ControlTypes.TEXT_BOX, false, false);
//		{
//
//			formField.setReadonly(true);
//		}
//		formField = addFormField(form, "id", "客户Id", groupName, ControlTypes.TEXT_BOX, false, false);
//		{
//
//			formField.setReadonly(true);
//		}
//		return form;
//	}
//
//	protected void addDetailGridPart(PWorkspace workspace) {
//
//		createCustomerProdMapDetailDetailPart(workspace);
//		createCompanysDetailPart(workspace);
//		createOrderDetailPart(workspace);
//		createFlowDetailPart(workspace);
//	}
//
//	/**
//	 * @Title: createSubdiaryieCompanyDetailPart
//	 * @Description: TODO(创建子公司明细)
//	 * @param: @param workspace
//	 * @return: void
//	 * @throws
//	 */
//	private void createCustomerProdMapDetailDetailPart(PWorkspace workspace) {
//
//		ResourceNode node = this.resourceService.byCode(CustomerProdMap.class.getSimpleName());
//		PDatagrid datagrid = new PDatagrid(node, "意向产品");
//		{
//			addColumn(datagrid, "product.name", "产品", ControlTypes.TEXT_BOX, 300);
//			addColumn(datagrid, "dProvince.name", "省份", ControlTypes.TEXT_BOX, 150);
//			addColumn(datagrid, "dCity.name", "城市", ControlTypes.TEXT_BOX, 150);
//			addColumn(datagrid, "dCounty.name", "区/县", ControlTypes.TEXT_BOX, 150);
//		}
//		PForm form = new PForm();
//		{
//			form.toNew();
//			form.setResourceNode(node);
//			form.setColumnCount(1);
//			form.setName("意向产品");
//
//			PFormField formField = null;
//			formField = addFormFieldRefrence(form, "product.name", "意向产品", null, "CRM_" + Product.class.getSimpleName(), true, false);
//			{
//				formField.setTroikaTrigger("controllerprodDetails.productChange(newValue,oldValue);");
//				formField.setWidth(300);
//			}
//			formField = addFormField(form, "dProvince.name", "省份", ControlTypes.CUSTOM, false, false);
//			{
//				formField.setCustomControlType(CityComboBox.class.getName());
//				formField.setDataOptions("level:1,changeCtrlId:'dCity_name'");
//				formField.setWidth(300);
//			}
//			formField = addFormField(form, "dCity.name", "城市", ControlTypes.CUSTOM, false, false);
//			{
//				formField.setCustomControlType(CityComboBox.class.getName());
//				formField.setDataOptions("level:2,changeCtrlId:'dCounty_name'");
//				formField.setWidth(300);
//			}
//			formField = addFormField(form, "dCounty.name", "区/县", ControlTypes.CUSTOM, false, false);
//			{
//				formField.setCustomControlType(CityComboBox.class.getName());
//				formField.setDataOptions("level:3");
//				formField.setWidth(300);
//			}
//		}
//
//		PPart part = new PPart();
//		{
//			part.toNew();
//			part.setName("意向产品");
//			part.setCode("prodDetails");
//			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
//			part.setRelationRole("prodDetails");
//			part.setResourceNode(node);
//			part.setPartTypeId(PartType.DETAIL_PART.getId());
//			part.setDatagrid(datagrid);
//			part.setDockStyle(DockType.DOCUMENTHOST);
//			part.setToolbar("panda/datagrid/detail");
//			part.setJsController("com.gongsibao.crm.web.ProdMapDetailPart");
//			part.setWindowWidth(550);
//			part.setWindowHeight(350);
//			part.setForm(form);
//		}
//		workspace.getParts().add(part);
//
//		part = workspace.getParts().get(0);
//		{
//			part.setName("基本信息");
//			part.setStyle("height:500px;");
//			part.setDockStyle(DockType.DOCUMENTHOST);
//		}
//	}
//
//	/**
//	 * @Title: createSubdiaryieCompanyDetailPart
//	 * @Description: TODO(创建匹配明细)
//	 * @param: @param workspace
//	 * @return: void
//	 * @throws
//	 */
//	private void createCompanysDetailPart(PWorkspace workspace) {
//
//		ResourceNode node = this.resourceService.byCode(CustomerCompanyMap.class.getSimpleName());
//		PDatagrid datagrid = new PDatagrid(node, "关联企业");
//		{
//			addColumn(datagrid, "company.companyName", "公司名称", ControlTypes.TEXT_BOX, 300);
//		}
//
//		PForm form = new PForm();
//		{
//			form.toNew();
//			form.setResourceNode(node);
//			form.setColumnCount(1);
//			form.setName("关联企业");
//
//			PFormField formField = null;
//			formField = addFormFieldRefrence(form, "company.companyName", "公司名称", null, CompanyIntention.class.getSimpleName(), true, false);
//			{
//				formField.setWidth(300);
//			}
//		}
//
//		PPart part = new PPart();
//		{
//			part.toNew();
//			part.setName("关联企业");
//			part.setCode("companys");
//			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
//			part.setRelationRole("companys");
//			part.setResourceNode(node);
//			part.setPartTypeId(PartType.DETAIL_PART.getId());
//			part.setDatagrid(datagrid);
//			part.setDockStyle(DockType.DOCUMENTHOST);
//			part.setToolbar("panda/datagrid/detail");
//			part.setWindowWidth(550);
//			part.setWindowHeight(350);
//			part.setForm(form);
//		}
//
//		workspace.getParts().add(part);
//	}
//
//	private void createOrderDetailPart(PWorkspace workspace) {
//
//		ResourceNode node = this.resourceService.byCode(SoOrder.class.getSimpleName());
//		PDatagrid datagrid = new PDatagrid(node, "订单历史");
//		{
//			addColumn(datagrid, "no", "订单号", ControlTypes.TEXT_BOX, 100);
//			addColumn(datagrid, "createTime", "下单时间", ControlTypes.DATETIME_BOX, 130);
//			addColumn(datagrid, "totalPrice", "订单金额", ControlTypes.DECIMAL_BOX, 100);
//			addColumn(datagrid, "paidPrice", "已付金额", ControlTypes.DECIMAL_BOX, 150);
//			// addColumn(datagrid, "no", "订单号", ControlTypes.TEXT_BOX, 150);
//		}
//
//		// PForm form = new PForm();
//		// {
//		// form.toNew();
//		// form.setResourceNode(node);
//		// form.setColumnCount(1);
//		// form.setName("订单历史");
//		//
//		// PFormField formField = null;
//		// formField = addFormFieldRefrence(form, "no", "公司名称",null,
//		// CompanyIntention.class.getSimpleName(), true, false);{
//		//
//		// formField.setWidth(300);
//		// }
//		// }
//
//		PPart part = new PPart();
//		{
//			part.toNew();
//			part.setName("订单历史");
//			part.setCode("orders");
//			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
//			part.setRelationRole("orders");
//			part.setResourceNode(node);
//			part.setPartTypeId(PartType.DETAIL_PART.getId());
//			part.setDatagrid(datagrid);
//			part.setDockStyle(DockType.DOCUMENTHOST);
//			part.setJsController("com.gongsibao.crm.web.OrderDetailPart");
//			// part.setToolbar("panda/datagrid/detail");
//			// part.setWindowWidth(550);
//			// part.setWindowHeight(350);
//			// part.setForm(form);
//		}
//
//		workspace.getParts().add(part);
//	}
//
//	@Test
//	public void detailPart() {
//
//		ResourceNode node = this.resourceService.byCode(CustomerFollow.class.getSimpleName());
//		OperationType ot1 = operationTypeService.byCode(OperationTypes.add);
//
//		PToolbar toolbar = new PToolbar();
//		{
//			toolbar.toNew();
//			toolbar.setPath("crm/customer/flow/detail");
//			toolbar.setName("子表");
//			toolbar.setResourceNode(node);
//			toolbar.setToolbarType(ToolbarType.BASE);
//		}
//
//		PToolbarItem item = new PToolbarItem();
//		{
//			item.toNew();
//			item.setCode("add");
//			item.setIcon("fa fa-mail-reply-all");
//			item.setName("跟进");
//			item.setCommand(null);
//			item.setOperationType(ot1);
//			item.setSeq(1);
//			item.setCommand("{controller}.add();");
//			toolbar.getItems().add(item);
//		}
//
//		toolbarService.save(toolbar);
//	}
//
//	private void createFlowDetailPart(PWorkspace workspace) {
//
//		ResourceNode node = this.resourceService.byCode(CustomerFollow.class.getSimpleName());
//		PDatagrid datagrid = new PDatagrid(node, "沟通日志");
//		{
//			addColumn(datagrid, "content", "内容", ControlTypes.TEXT_BOX, 500);
//			addColumn(datagrid, "creator", "沟通人", ControlTypes.DATETIME_BOX, 130);
//			addColumn(datagrid, "createTime", "沟通时间", ControlTypes.DATETIME_BOX, 130);
//		}
//
//		PForm form = new PForm();
//		{
//			form.toNew();
//			form.setResourceNode(node);
//			form.setColumnCount(1);
//			form.setName("沟通日志");
//
//			PFormField formField = null;
//			formField = addFormField(form, "content", "内容", ControlTypes.TEXTAREA, true, false);
//			{
//				formField.setReadonly(true);
//				formField.setWidth(400);
//				formField.setHeight(200);
//				formField.setFullColumn(false);
//			}
//		}
//
//		PPart part = new PPart();
//		{
//			part.toNew();
//			part.setName("沟通日志");
//			part.setCode("follows");
//			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
//			part.setRelationRole("follows");
//			part.setResourceNode(node);
//			part.setPartTypeId(PartType.DETAIL_PART.getId());
//			part.setDatagrid(datagrid);
//			part.setDockStyle(DockType.DOCUMENTHOST);
//			part.setToolbar("crm/customer/flow/detail");
//			part.setJsController(FlowDetailPart.class.getName());
//			part.setServiceController(FlowDetailPart.class.getName());
//			part.setWindowWidth(550);
//			part.setWindowHeight(400);
//			part.setForm(form);
//		}
//
//		workspace.getParts().add(part);
//	}
//
//	@Override
//	protected PQueryProject createQueryProject(ResourceNode node) {
//
//		PQueryProject queryProject = super.createQueryProject(node);
//		queryProject.toNew();
//		addQueryItem(queryProject, "realName", "客户名称", ControlTypes.TEXT_BOX);
//		addQueryItem(queryProject, "mobile", "手机", ControlTypes.TEXT_BOX);
//		return queryProject;
//	}

	public void doOperation() {

		ResourceNode node = resourceService.byCode(resourceNodeCode);
		operationService.addOperation(node, OperationTypes.view);
		operationService.addOperation(node, OperationTypes.add);
		operationService.addOperation(node, OperationTypes.update);
	}
}
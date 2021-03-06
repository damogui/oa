package com.gongsibao.panda.supplier.igirl.workspace.tm.apply;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.dic.PartType;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.panda.entity.PPart;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.entity.PWorkspace;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.panda.utils.enums.EnumUtil;
import org.netsharp.panda.utils.plupload.MimeType;
import org.netsharp.panda.utils.plupload.UploaderFilters;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.JsonManage;
import org.netsharp.util.ReflectManager;

import com.gongsibao.entity.igirl.tm.TradeMarkCase;
import com.gongsibao.entity.igirl.tm.dict.AttachmentCat;
import com.gongsibao.entity.igirl.tm.dict.FileType;
import com.gongsibao.entity.igirl.tm.dict.MarkState;
import com.gongsibao.entity.igirl.tm.dict.ShareGroup;
import com.gongsibao.igirl.tm.web.DepartmentTradeMarkCaseListPart;
import com.gongsibao.igirl.tm.web.TradeMarkCasePart;
import com.gongsibao.igirl.tm.web.TradeMarkDetailPart;
import com.gongsibao.igirl.tm.web.UploadAttachmentDetailPart;

public class DpAllTradeMarkCaseWorkspaceTest extends WorkspaceCreationBase {

	@Override
	@Before
	public void setup() {
		urlList = "/igirl/dp/case/list";
		urlForm = "/igirl/dp/case/form";
		entity = TradeMarkCase.class;
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = "商标方案";
		resourceNodeCode = "IGIRL_Dp_TradeMarkCase";
		listPartServiceController=DepartmentTradeMarkCaseListPart.class.getName();
		formServiceController = TradeMarkCasePart.class.getName();
		formJsController = TradeMarkCasePart.class.getName();
		formJsImport = "/gsb/igirl/js/markcase.form.part.js";
		listToolbarPath = "/igirl/dp/list/toolbar";
	}

	public static final String trademarkToolbarPath = "/igirl/tm/toolbar";
	public static final String uploadloadToolbarPath = "/igirl/upload/toolbar";
	public static final String downloadToolbarPath = "/igirl/download/toolbar";
	 @Test
	 public void fromToolbar() {
	
	 ResourceNode node =	 this.resourceService.byCode(resourceNodeCode);
	 OperationType ot1 = operationTypeService.byCode(OperationTypes.add);
	
	 PToolbar toolbar = new PToolbar();
	 {
		 toolbar.toNew();
		 //toolbar.setBasePath("panda/datagrid/edit");
		 toolbar.setPath(listToolbarPath);
		 toolbar.setName("案件工具栏");
		 toolbar.setResourceNode(node);
	
	 }
	 
	 PToolbarItem item = new PToolbarItem();
	 {
	 item.toNew();
	 item.setCode("add");
	 item.setIcon("fa fa-plus");
	 item.setName("新增");
	 item.setCommand(null);
	 item.setOperationType(ot1);
	 item.setSeq(3000);
	 item.setCommand("{controller}.add();");
	 toolbar.getItems().add(item);
	 }
	
	 item = new PToolbarItem();
	 {
	 item.toNew();
	 item.setCode("edit");
	 item.setIcon("fa fa-edit");
	 item.setName("编辑");
	 item.setCommand(null);
	 item.setOperationType(ot1);
	 item.setSeq(3000);
	 item.setCommand("{controller}.edit();");
	 toolbar.getItems().add(item);
	 }
//	 item = new PToolbarItem();
//	 {
//	 item.toNew();
//	 item.setCode("remove");
//	 item.setIcon("fa fa-trash-o");
//	 item.setName("删除");
//	 item.setCommand(null);
//	 item.setOperationType(ot1);
//	 item.setSeq(4000);
//	 item.setCommand("{controller}.remove();");
//	 toolbar.getItems().add(item);
//	 }
	
	
	 toolbarService.save(toolbar);
	 }
	
	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		datagrid.setToolbar("panda/datagrid/row/edit");
		PDatagridColumn column = null;
		addColumn(datagrid, "createTime", "日期", ControlTypes.DATETIME_BOX, 100, true);
		addColumn(datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 100, true);
		addColumn(datagrid, "code", "编号", ControlTypes.TEXT_BOX, 120, true);
		addColumn(datagrid, "companyName", "公司名称", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "applier", "申请人", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "urgency", "紧急(小时)", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "ownerName", "业务员", ControlTypes.TEXT_BOX, 50);
		addColumn(datagrid, "orderCode", "订单号", ControlTypes.TEXT_BOX, 80);
		addColumn(datagrid, "caseAmount", "金额", ControlTypes.DECIMAL_BOX, 100);
		addColumn(datagrid, "tmcState", "状态", ControlTypes.ENUM_BOX, 100);
		addColumn(datagrid, "tradeOptions", "商标选项", ControlTypes.TEXT_BOX, 100);
		datagrid.setSortName("createTime DESC");
		return datagrid;
	}

	//
	@Override
	protected PForm createForm(ResourceNode node) {
		PForm form = new PForm();
		{
			form.toNew();
			form.setResourceNode(node);
			form.setName(this.meta.getName() + "表单");
			form.setColumnCount(3);
		}

		PFormField formField = null;

		String groupName = null;
		formField = addFormField(form, "applierType", "申请人类型", "类型信息", ControlTypes.ENUM_BOX, false, false);
		{
			formField.setTroikaTrigger("controllertradeMarkCase.applierTypeChange(newValue, oldValue);");
		}
		addFormField(form, "writeType", "书式类型", "类型信息", ControlTypes.ENUM_BOX, false, false);

		formField = addFormField(form, "companyName", "公司名称", "申请人基本信息", ControlTypes.TEXT_BOX, true, false);
		{
			formField.setTroikaTrigger("controllertradeMarkCase.companyNameChange(this);");
			formField.setWidth(200);
		}
		addFormField(form, "creditCode", "信用代码", "申请人基本信息", ControlTypes.TEXT_BOX, false, false).setWidth(200);

		formField = addFormField(form, "applier", "申请人", "申请人基本信息", ControlTypes.TEXT_BOX, true, false);
		{
			// formField.setWidth(350);
			formField.setWidth(200);
		}
	
		addFormField(form, "applierAddress", "地址", "申请人基本信息", ControlTypes.TEXT_BOX, true, false).setWidth(300);
		formField = addFormField(form, "certificateType", "证件名称", "自然人信息", ControlTypes.ENUM_BOX, false, false);
		{
			//formField.setTroikaTrigger("controllertradeMarkCase.applierTypeChange(newValue, oldValue);");
		}
		addFormField(form, "identityCode", "证件号码", "自然人信息", ControlTypes.TEXT_BOX, false, false).setWidth(200);
		addFormField(form, "contactName", "留言姓名", "客户留言", ControlTypes.TEXT_BOX, true, false);
		formField = addFormField(form, "mobile", "留言电话", "客户留言", ControlTypes.NUMBER_BOX, true, false);
		{
			formField.setTroikaTrigger("controllertradeMarkCase.mobileChange(this);");
		}
		addFormField(form, "urgency", "紧急程度(小时)", "客户留言", ControlTypes.NUMBER_BOX, false, false);
		// addFormField(form, "ownedMarks", "已有商标", groupName, ControlTypes.TEXT_BOX,
		// false, false);
		addFormField(form, "momo", "交流记录", "客户留言", ControlTypes.TEXTAREA, false, false).setFullColumn(true);
		addFormField(form, "advice", "客户异议", "客户留言", ControlTypes.TEXTAREA, false, true).setFullColumn(true);
	
		addFormField(form, "caseProxyContactPerson", "联系人", "代理人信息", ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "ywPhone", "电话", "代理人信息", ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "mailCode", "邮编",  "代理人信息", ControlTypes.NUMBER_BOX, true, false);
		addFormField(form, "fax", "传真",  "代理人信息", ControlTypes.TEXT_BOX, true, false);
		
		
		addFormField(form, "orderCode", "订单号", "案件信息", ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "caseAmount", "方案金额", "案件信息", ControlTypes.DECIMAL_BOX, true, false);
		addFormField(form, "tokenImgUrl", "二维码", "案件信息", ControlTypes.IMAGE, false, true);
		addFormField(form, "tmcState", "方案状态", "案件信息", ControlTypes.ENUM_BOX, true, false).setVisible(false);
		
		
		addFormField(form, "token", "token", "案件信息", ControlTypes.TEXT_BOX, false, true).setVisible(false);
		addFormField(form, "code", "方案编号", "案件信息", ControlTypes.TEXT_BOX, false, true).setVisible(false);
		addFormField(form, "tradeOptions", "商标选项", "案件信息", ControlTypes.TEXT_BOX, false, true).setVisible(false);
		addFormFieldRefrence(form, "product.name", "商标产品", "案件信息", "RegisterTypeTradeMarkList", true, false);

		return form;
	}

	//
	protected void addDetailGridPart(PWorkspace workspace) {

		createTradeMarkDetailPart(workspace);
		createUploadAttamentDetailPart(workspace);
		createDownloadAttamentDetailPart(workspace);
		// createOrderDetailPart(workspace);
		// createFlowDetailPart(workspace);
	}

	//
	// /**
	// * @Title: createSubdiaryieCompanyDetailPart
	// * @Description: TODO(创建子公司明细)
	// * @param: @param workspace
	// * @return: void
	// * @throws
	// */
	private void createTradeMarkDetailPart(PWorkspace workspace) {

		ResourceNode node = this.resourceService.byCode("IGIRL_All_TradeMark");
		PDatagridColumn column = null;
		PDatagrid datagrid = new PDatagrid(node, "商标选项");
		{
			addColumn(datagrid, "proxyCode", "代理号", ControlTypes.TEXT_BOX, 180);
			addColumn(datagrid, "nclOne.code", "编码", ControlTypes.TEXT_BOX, 100);
			addColumn(datagrid, "nclOne.name", "商标大类", ControlTypes.TEXT_BOX, 150);
			addColumn(datagrid, "selectedTwoStr", "商标小类", ControlTypes.TEXTAREA, 150);
			column = addColumn(datagrid, "markState", "申请状态", ControlTypes.ENUM_BOX, 150);
			{
				String formatter = EnumUtil.getColumnFormatter(MarkState.class);
				column.setFormatter(formatter);
			}
			column = addColumn(datagrid, "shareGroup", "附件共享", ControlTypes.ENUM_BOX, 150);
			{
				String formatter = EnumUtil.getColumnFormatter(ShareGroup.class);
				column.setFormatter(formatter);
			}
			addColumn(datagrid, "cost", "成本", ControlTypes.DECIMAL_BOX, 150);
			addColumn(datagrid, "charge", "服务费", ControlTypes.DECIMAL_BOX, 150);
		}
		PForm form = new PForm();
		{
			form.toNew();
			form.setResourceNode(node);
			form.setColumnCount(3);
			form.setName("商标选项");
			form.setColumnCount(2);
			String groupName = null;
			PFormField formField = null;
			addFormField(form, "tradeMarkType", "商标类型", groupName, ControlTypes.ENUM_BOX, false, true);
			addFormField(form, "whetherThirdSpace", "三维商标", groupName, ControlTypes.SWITCH_BUTTON, true, true);
			addFormField(form, "whetherColorGroup", "颜色组合", groupName, ControlTypes.SWITCH_BUTTON, true, true);
			addFormField(form, "hasColor", "彩色商标", groupName, ControlTypes.SWITCH_BUTTON, true, false);
			addFormField(form, "whetherSound", "声音商标", groupName, ControlTypes.SWITCH_BUTTON, true, false);
			addFormField(form, "whetherPersonPhoto", "以肖像注册", groupName, ControlTypes.SWITCH_BUTTON, true, false);
			formField = addFormField(form, "memo", "商标说明", groupName, ControlTypes.TEXT_BOX, true, false);
	        {
	            formField.setTroikaTrigger("controllertradeMarks.checkMemo(this);");
	        }
	        formField = addFormField(form, "shareGroup", "附件共享", groupName, ControlTypes.ENUM_BOX, true, false);
//	        {
//            	formField.setTroikaTrigger("controllertradeMarks.checkShareGroup();");
//            }
			formField = addFormField(form, "whetherShare", "是否共同申请", groupName, ControlTypes.SWITCH_BUTTON, false,
					false);
			formField.setVisible(false);
			formField = addFormField(form, "priorityType", "是否优先权", groupName, ControlTypes.ENUM_BOX, false, false);
			formField.setVisible(false);

			// 成本
			addFormField(form, "cost", "成本", groupName, ControlTypes.DECIMAL_BOX, true, true);
			// 服务费
			addFormField(form, "charge", "服务费", groupName, ControlTypes.DECIMAL_BOX, true, false);

			formField = addFormFieldRefrence(form, "nclOne.name", "商标大类", null, "NCLOne", true, false);
			{
				formField.setTroikaTrigger("controllertradeMarks.nclOneChange(newValue,oldValue);");
				formField.setWidth(150);
			}
			formField = addFormField(form, "selectedTwoStr", "所选小类", groupName, ControlTypes.TEXTAREA, false, true);
			{
				formField.setHeight(150);
				formField.setFullColumn(true);
			}

		}

		PPart part = new PPart();
		{
			part.toNew();
			part.setName("商标选项");
			part.setCode("tradeMarks");
			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
			part.setRelationRole("tradeMarks");
			part.setResourceNode(node);
			part.setPartTypeId(PartType.DETAIL_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setToolbar(trademarkToolbarPath);
			part.setJsController("com.gongsibao.igirl.tm.web.TradeMarkDetailPart");
			part.setServiceController(TradeMarkDetailPart.class.getName());
			part.setWindowWidth(1024);
			part.setWindowHeight(600);
			part.setForm(form);

		}
		workspace.getParts().add(part);
		part = workspace.getParts().get(0);
		{
			part.setName("基本信息");
			part.setStyle("height:500px;");
			part.setDockStyle(DockType.DOCUMENTHOST);
		}

	}

	private void createUploadAttamentDetailPart(PWorkspace workspace) {

		ResourceNode node = this.resourceService.byCode("IGIRL_UPLOAD_UploadAttachment");
		PDatagridColumn column = null;
		PDatagrid datagrid = new PDatagrid(node, "上传盖章附件");
		{
			datagrid.setShowCheckbox(true);
			datagrid.setSingleSelect(true);

			column = addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 150);
			{
				// column.setFormatter("return '<a
				// href=\"url\">name</a>'.replace('name',row.name).replace('url',row.fileUrl)");
			}
			addColumn(datagrid, "tradeMark.proxyCode", "代理号", ControlTypes.TEXT_BOX, 180);
			column = addColumn(datagrid, "shareGroup", "共享组", ControlTypes.ENUM_BOX, 150);{
			String formatter = EnumUtil.getColumnFormatter(ShareGroup.class);
			column.setFormatter(formatter);
		}
			column = addColumn(datagrid, "needed", "是否需要上传", ControlTypes.TEXT_BOX, 150);
			{
				column.setFormatter("if( row.needed==1  ){ return '需要上传' } else{ return '无需上传' }");
			}
			column = addColumn(datagrid, "attachmentCat", "附件类别", ControlTypes.TEXT_BOX, 100);
			{
				String formatter = EnumUtil.getColumnFormatter(AttachmentCat.class);
				column.setFormatter(formatter);
			}
			column = addColumn(datagrid, "fileType", "文件类型", ControlTypes.TEXT_BOX, 150);
			{
				String formatter = EnumUtil.getColumnFormatter(FileType.class);
				column.setFormatter(formatter);
			}
			column = addColumn(datagrid, "toFileType", "目标文件类型", ControlTypes.TEXT_BOX, 150);
			{
				String formatter = EnumUtil.getColumnFormatter(FileType.class);
				column.setFormatter(formatter);
			}
			addColumn(datagrid, "minPx", "最小像素数", ControlTypes.TEXT_BOX, 100);
			addColumn(datagrid, "maxPx", "最大像素数", ControlTypes.TEXT_BOX, 100);
			addColumn(datagrid, "minBytes", "最小文件大小（KB）", ControlTypes.TEXT_BOX, 150);
			addColumn(datagrid, "maxBytes", "最大文件大小（KB）", ControlTypes.TEXT_BOX, 150);
			column = addColumn(datagrid, "fileUrl", "状态", ControlTypes.TEXT_BOX, 150);
			{
				column.setFormatter("if( row.fileUrl=='' || row.fileUrl==null ){ return '待上传' } else{ return '已上传' }");
			}


		}
		PForm form = new PForm();
		{
			form.toNew();
			form.setResourceNode(node);
			form.setColumnCount(2);
			form.setName("上传盖章附件");
			String groupName = null;
			PFormField formField = null;
			addFormField(form, "shareGroup", "共享组", groupName, ControlTypes.ENUM_BOX, true, false);
			addFormField(form, "attachmentCat", "附件类别", groupName, ControlTypes.ENUM_BOX, true, false);
			addFormField(form, "fileType", "文件类型", groupName, ControlTypes.ENUM_BOX, true, false);
			addFormField(form, "toFileType", "目标文件类型", groupName, ControlTypes.ENUM_BOX, true, false);
			addFormField(form, "name", "附件名称", groupName, ControlTypes.TEXT_BOX, true, false);
			addFormField(form, "needed", "是否需上传", groupName, ControlTypes.SWITCH_BUTTON, true, false);
			formField=addFormField(form, "fileUrl", "上传", groupName, ControlTypes.OSS_UPLOAD, false, false);
			{
				UploaderFilters uf = new UploaderFilters();{

					uf.setMaxFileSize("2048kb");//文件大小
					uf.setPreventDuplicates(false);//是否可选择重复文件
					MimeType mt = new MimeType();
					mt.setTitle("Image files");//标题
					mt.setExtensions("jpg,pdf");//文件类型
					
					List<MimeType> mimeTypes = new ArrayList<MimeType>();
					mimeTypes.add(mt);
					uf.setMimeTypes(mimeTypes);
				}
				String filters = JsonManage.serialize2(uf).replaceAll("\"", "'");
				formField.setDataOptions(filters);
			}

		}

		PPart part = new PPart();
		{
			part.toNew();
			part.setName("上传盖章附件");
			part.setCode("uploadAttachments");
			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
			part.setRelationRole("uploadAttachments");
			part.setResourceNode(node);
			part.setPartTypeId(PartType.DETAIL_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setToolbar(uploadloadToolbarPath);
			part.setJsController("com.gongsibao.igirl.tm.web.UploadAttachmentDetailPart");
			part.setServiceController(UploadAttachmentDetailPart.class.getName());
			part.setWindowWidth(800);
			part.setWindowHeight(600);
			part.setForm(form);

		}
		workspace.getParts().add(part);

	}

	private void createDownloadAttamentDetailPart(PWorkspace workspace) {

		ResourceNode node = this.resourceService.byCode("IGIRL_DOWNLOAD_DownloadAttachment");
		PDatagridColumn column = null;
		PDatagrid datagrid = new PDatagrid(node, "下载待盖章附件");
		{

			column = addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 150);
			{
				// column.setFormatter("return '<a
				// href=\"url\">name</a>'.replace('name',row.name).replace('url',row.fileUrl)");
			}
			addColumn(datagrid, "tradeMark.proxyCode", "代理号", ControlTypes.TEXT_BOX, 180);
			column = addColumn(datagrid, "shareGroup", "共享组", ControlTypes.ENUM_BOX, 150);{
			String formatter = EnumUtil.getColumnFormatter(ShareGroup.class);
			column.setFormatter(formatter);
		}
			column = addColumn(datagrid, "attachmentCat", "附件类别", ControlTypes.TEXT_BOX, 100);{
				String formatter=EnumUtil.getColumnFormatter(AttachmentCat.class);
				column.setFormatter(formatter);
			}
			column=addColumn(datagrid, "fileType", "文件类型", ControlTypes.TEXT_BOX, 150);
			{
				String formatter=EnumUtil.getColumnFormatter(FileType.class);
				column.setFormatter(formatter);
			}
			column=addColumn(datagrid, "toFileType", "目标文件类型", ControlTypes.TEXT_BOX, 150);
			{
				String formatter=EnumUtil.getColumnFormatter(FileType.class);
				column.setFormatter(formatter);
			}
			addColumn(datagrid, "minPx", "最小像素数", ControlTypes.TEXT_BOX, 100);
			addColumn(datagrid, "maxPx", "最大像素数", ControlTypes.TEXT_BOX, 100);
			addColumn(datagrid, "minBytes", "最小文件大小（KB）", ControlTypes.TEXT_BOX, 150);
			addColumn(datagrid, "maxBytes", "最大文件大小（KB）", ControlTypes.TEXT_BOX, 150);
			column=addColumn(datagrid, "fileUrl", "状态", ControlTypes.TEXT_BOX, 150);
			{
				column.setFormatter("if(row.fileUrl=='' || row.fileUrl==null ){return '待生成' } else{ return '可下载' }");
			}
			

		}
		PForm form = new PForm();
		{
			form.toNew();
			form.setResourceNode(node);
			form.setColumnCount(2);
			form.setName("下载待盖章附件");
			String groupName = null;
			PFormField formField = null;
			addFormField(form, "attachmentCat", "附件类别", groupName, ControlTypes.ENUM_BOX, true, false);
			addFormField(form, "fileType", "文件类型", groupName, ControlTypes.ENUM_BOX, true, false);
			addFormField(form, "toFileType", "目标文件类型", groupName, ControlTypes.ENUM_BOX, true, false);
			addFormField(form, "name", "附件名称", groupName, ControlTypes.TEXT_BOX, true, false);
			addFormField(form, "fileUrl", "上传", groupName, ControlTypes.OSS_UPLOAD, true, false);

		}

		PPart part = new PPart();
		{
			part.toNew();
			part.setName("下载待盖章附件");
			part.setCode("downLoadAttaments");
			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
			part.setRelationRole("downLoadAttaments");
			part.setResourceNode(node);
			part.setPartTypeId(PartType.DETAIL_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setToolbar(downloadToolbarPath);
			part.setJsController("com.gongsibao.igirl.tm.web.DownloadAttachmentDetailPart");
			part.setServiceController(UploadAttachmentDetailPart.class.getName());
			part.setWindowWidth(800);
			part.setWindowHeight(600);
			part.setForm(form);

		}
		workspace.getParts().add(part);

	}
	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "code", "案件编号", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "orderCode", "订单号", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "companyName", "公司名称", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "applier", "申请人", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "ownerName", "业务员", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "TMCState", "状态", ControlTypes.ENUM_BOX);
		addQueryItem(queryProject, "createTime", "时间", ControlTypes.DATE_BOX).setWidth(400);
//		PQueryItem item =addQueryItem(queryProject, "mobilePhone", "销售方式", ControlTypes.CUSTOMER);{
//			
//			item.setCustomerControlType(DictComboBox.class.getName());
//			item.setRefFilter("type=8");
//		}
		//addQueryItem(queryProject, "enabled", "启用/禁用", ControlTypes.BOOLCOMBO_BOX);
		return queryProject;
	}

	public void doOperation() {

		ResourceNode node = resourceService.byCode(resourceNodeCode);
		operationService.addOperation(node, OperationTypes.view);
		operationService.addOperation(node, OperationTypes.add);
		operationService.addOperation(node, OperationTypes.update);
	}
}

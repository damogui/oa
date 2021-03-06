package com.gongsibao.panda.supplier.crm.workspace.report;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.Employee;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.PartType;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.controls.PropertyQueryDictComboBox;
import com.gongsibao.crm.web.report.ComprehenReportPart;
import com.gongsibao.entity.crm.report.ComprehenReportEntity;


/**
 * 综合统计
 * @author Administrator
 *
 */
public class ComprehenStatisticalWorkspaceTest extends WorkspaceCreationBase{

	@Override
	@Before
	public void setup() {
		entity = ComprehenReportEntity.class;
		urlList = "/crm/statistical/comprehen/list";
		listPartName = formPartName = "综合统计";
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "CRM_STATISTICAL_COMPREHEN";
		listPartType = PartType.TREEGRID_PART.getId();
		listPartServiceController = ComprehenReportPart.class.getName();
		listPartImportJs = "/gsb/panda-extend/gsb.custom.query.controls.js";
		//统计级别""-平台；1-服务商；
		listFilter ="1";
	}
	
	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		datagrid.setAutoQuery(false);
		datagrid.setTreeField("departmentName");
		datagrid.setLazy(true);
		PDatagridColumn column = null;

		column = addColumn(datagrid, "departmentName", "部门", ControlTypes.TEXT_BOX, 300, true);
		column = addColumn(datagrid, "customerCount", "全部客户数", ControlTypes.NUMBER_BOX, 100);
		column = addColumn(datagrid, "taskCount", "全部商机数", ControlTypes.NUMBER_BOX, 100);
		
		/*column = addColumn(datagrid, "selfCustomerCount", "自拓客户数", ControlTypes.NUMBER_BOX, 100);
		column = addColumn(datagrid, "selfTaskCount", "自拓商机数", ControlTypes.NUMBER_BOX, 100);*/
		column = addColumn(datagrid, "allocationTaskCount", "分配商机数", ControlTypes.NUMBER_BOX, 100);
		column = addColumn(datagrid, "intoTaskCount", "转入商机数", ControlTypes.NUMBER_BOX, 100);
		column = addColumn(datagrid, "rollOutTaskCount", "转出商机数", ControlTypes.NUMBER_BOX, 100);
		column = addColumn(datagrid, "returnTaskCount", "释放商机数", ControlTypes.NUMBER_BOX, 100);
		column = addColumn(datagrid, "withdrawTaskCount", "收回商机数", ControlTypes.NUMBER_BOX, 100);
		column = addColumn(datagrid, "followTaskCount", "跟进商机数", ControlTypes.NUMBER_BOX, 100);
		column = addColumn(datagrid, "unSignTaskCount", "无法签单商机数", ControlTypes.NUMBER_BOX, 100);
		column = addColumn(datagrid, "checkAbnormalTaskCount", "抽查异常商机数", ControlTypes.NUMBER_BOX, 100);
		column = addColumn(datagrid, "signingAmount", "预估签单额", ControlTypes.NUMBER_BOX, 100);
		column = addColumn(datagrid, "returnedAmount", "预估回款额", ControlTypes.NUMBER_BOX, 100);
		
		column = addColumn(datagrid, "parentId", "parentId", ControlTypes.TEXT_BOX, 100);
		{
			column.setVisible(false);
			column.setSystem(true);
		}
		column = addColumn(datagrid, "isLeaf", "isLeaf", ControlTypes.TEXT_BOX, 100);
		{
			column.setVisible(false);
			column.setSystem(true);
		}
		return datagrid;
	}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {
		
		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		PQueryItem item = null;
		item = addQueryItem(queryProject, "date", "日期", ControlTypes.DATE_BOX);{
			item.setRequired(true);
		}
		
		addRefrenceQueryItem(queryProject, "owner.name", "业务员", Employee.class.getSimpleName());
		item = addQueryItem(queryProject, "source.name", "商机来源", ControlTypes.CUSTOM);{
			
			item.setCustomControlType(PropertyQueryDictComboBox.class.getName());
			item.setRefFilter("type=411");
		}
		return queryProject;
	}
	public void doOperation() {

		ResourceNode node = resourceService.byCode(resourceNodeCode);
		operationService.addOperation(node, OperationTypes.view);
	}
}

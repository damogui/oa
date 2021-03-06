package com.gongsibao.igirl.tm.web;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.ListPart;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.igirl.tm.ChangeTradeMark;
import com.gongsibao.igirl.tm.base.IChangeTradeMarkService;
import com.gongsibao.utils.SupplierSessionManager;

/**
 * 我的任务列表操作功能集合
 * @author Administrator
 *
 */
public class DepartmentChangeTradeMarkListPart extends ListPart{
	IChangeTradeMarkService service = ServiceFactory.create(IChangeTradeMarkService.class);
	@Override
	protected String getExtraFilter() {
		List<String> ss = new ArrayList<String>();
		//父类过滤条件
		String filter = super.getExtraFilter();
		if(!StringManager.isNullOrEmpty(filter)){
			ss.add(filter);
		}
		//过滤部门Id
		String departmentIds = SupplierSessionManager.getSubDepartmentIdsStr();
		if (!StringManager.isNullOrEmpty(departmentIds)) {
			ss.add(" department_id in (" + departmentIds+")");
		}else {
			//非服务商内部人员看不到数据
			ss.add("1=2");
		}
		return StringManager.join(" and ", ss);
	}
	public ChangeTradeMark updateOwner(Integer ctmId, Integer ownerId){
		return service.updateOwner(ctmId,ownerId);
	}

	public Integer getTradeMarkCaseSupplierId(){
		return SupplierSessionManager.getSupplierId();
	}
}
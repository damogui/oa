package com.gongsibao.trade.web.platform;

import java.util.List;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.persistence.session.SessionManager;
import org.netsharp.util.StringManager;

import com.gongsibao.supplier.base.ISupplierService;
import com.gongsibao.trade.web.SalesmanStagingListPart;

public class StagingListPart extends SalesmanStagingListPart{

	@Override
	public String getDefaultFilter() {

		// 当前登录人为运营专员时，过滤服务商Id
		String defaultFilter = null;
		ISupplierService supplierService = ServiceFactory.create(ISupplierService.class);
		List<Integer> supplierIdList = supplierService.getSupplierIdListByOwnerId(SessionManager.getUserId());
		if (supplierIdList != null && supplierIdList.size() > 0) {

			defaultFilter =  " supplier_id in (" + StringManager.join(",", supplierIdList) + ")";
		}
		System.out.println("分期订单：" +defaultFilter);
		return defaultFilter;
	}
}

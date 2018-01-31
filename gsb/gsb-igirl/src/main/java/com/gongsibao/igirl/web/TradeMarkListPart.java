package com.gongsibao.igirl.web;
import com.gongsibao.igirl.base.ITradeMarkService;
import com.gongsibao.utils.SupplierSessionManager;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.annotation.Authorization;
import org.netsharp.panda.commerce.ListPart;
/**
 * 我的任务列表操作功能集合
 * @author Administrator
 *
 */
public class TradeMarkListPart extends ListPart{
    ITradeMarkService service = ServiceFactory.create(ITradeMarkService.class);

    public String updateMarkState(String[] ids,String type){
        return service.updateMarkState(String.join(",", ids),type);
    }
   @Override
	 protected String getExtraFilter() {
			// TODO Auto-generated method stub
	   String filter=" supplierId = "+SupplierSessionManager.getSupplierId();
		 return filter;
	 }
}
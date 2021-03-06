package com.gongsibao.igirl.tm.web;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.ListPart;

import com.gongsibao.entity.igirl.tm.TransferTradeMark;
import com.gongsibao.igirl.tm.base.ITransferTradeMarkService;
import com.gongsibao.utils.SupplierSessionManager;

/**
 * 我的任务列表操作功能集合
 * @author Administrator
 *
 */
public class MyTransferTradeMarkListPart extends ListPart{
	ITransferTradeMarkService service = ServiceFactory.create(ITransferTradeMarkService.class);
 
   @Override
	 protected String getExtraFilter() {
	   String filter=" supplierId = "+ SupplierSessionManager.getSupplierId();
	   return filter;
	 }
	public TransferTradeMark updateOwner(Integer ttmId, Integer ownerId){
		return this.service.updateOwner(ttmId,ownerId);
	}

	public Integer getTradeMarkCaseSupplierId(){
		return SupplierSessionManager.getSupplierId();
	}
}
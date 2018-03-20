package com.gongsibao.trade.base;

import java.util.Map;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.trade.Invoice;

public interface IInvoiceService extends IPersistableService<Invoice> {
	
	/**
	 * ���뷢Ʊ
 	 * @Title: applyInvoice  
	 * @Description: TODO
	 * @param @return    ����  
	 * @return Boolean    ��������  
	 * @throws
	 */
	public Boolean applyInvoice(Invoice invoice,Map<String,Object> paraMap);
}
package com.gongsibao.trade.service.action.order.invoice;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;

import com.gongsibao.entity.trade.Invoice;

public class ActionApplyInvoiceVerify  implements IAction{

	@Override
	public void execute(ActionContext ctx) {
		//��֤��Ʊ���ܴ��ڿ�Ʊ���
		Invoice  invoice = (Invoice) ctx.getItem();
	}

}

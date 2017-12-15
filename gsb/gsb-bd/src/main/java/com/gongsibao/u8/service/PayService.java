package com.gongsibao.u8.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.entity.trade.Pay;
import com.gongsibao.entity.trade.dic.PayReceiptStatus;
import com.gongsibao.u8.base.IPayService;

@Service
public class PayService extends PersistableService<Pay> implements IPayService {

	public PayService() {
		super();
		this.type = Pay.class;
	}

	@Override
	public Boolean changeReceiptStatus(int payId, PayReceiptStatus receiptStatus) {
		UpdateBuilder updateSql = UpdateBuilder.getInstance();
		{
			updateSql.update("so_pay");
			updateSql.set("receipt_status",receiptStatus.getValue());
			updateSql.where("pkid="+payId);
		}
		String cmdText = updateSql.toSQL();
		return this.pm.executeNonQuery(cmdText, null) > 0;
	}

}

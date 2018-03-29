package com.gongsibao.trade.web;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.core.QueryParameters;
import org.netsharp.panda.json.EnumResultJson;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;

import com.gongsibao.entity.trade.OrderPayMap;
import com.gongsibao.entity.trade.Pay;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.entity.u8.SetOfBooks;
import com.gongsibao.entity.u8.U8Bank;
import com.gongsibao.trade.base.IOrderPayMapService;
import com.gongsibao.trade.base.IPayService;
import com.gongsibao.u8.base.ISetOfBooksService;
import com.gongsibao.u8.base.ISoOrderService;
import com.gongsibao.u8.base.IU8BankService;

public class OrderPayController {

	/**
	 * @return
	 */
	public List<EnumResultJson> querySetOfBooksList() {

		Oql oql = new Oql();
		{
			oql.setType(SetOfBooks.class);
			oql.setSelects("id,name");
		}

		ISetOfBooksService setOfBooksService = ServiceFactory.create(ISetOfBooksService.class);
		List<SetOfBooks> list = setOfBooksService.queryList(oql);

		List<EnumResultJson> enumList = new ArrayList<EnumResultJson>();
		for (SetOfBooks sob : list) {

			EnumResultJson enumItem = new EnumResultJson();
			enumItem.setText(sob.getName());
			enumItem.setValue(sob.getId().toString());
			enumList.add(enumItem);
		}
		return enumList;
	}

	/**
	 * @param setOfBooksId
	 * @return
	 */
	public List<EnumResultJson> queryU8BankList(Integer setOfBooksId) {

		IU8BankService bankService = ServiceFactory.create(IU8BankService.class);
		Oql oql = new Oql();
		{
			oql.setType(U8Bank.class);
			oql.setSelects("id,name");
			oql.setFilter("setOfBooksId=? and enabled=1");
			oql.getParameters().add("setOfBooksId", setOfBooksId, Types.INTEGER);
		}
		List<U8Bank> list = bankService.queryList(oql);
		List<EnumResultJson> enumList = new ArrayList<EnumResultJson>();
		for (U8Bank bank : list) {

			EnumResultJson enumItem = new EnumResultJson();
			enumItem.setText(bank.getName());
			enumItem.setValue(bank.getId().toString());
			enumList.add(enumItem);
		}
		return enumList;
	}

	// U8Bank

	/**
	 * @Title: getOnlinePayInfoBySoderOId
	 * @Description: TODO(根据订单号获取订单的支付信息针对线上支付)
	 * @param: @param orderId
	 * @param: @return
	 * @return: int
	 * @throws
	 */
	public OrderPayMap getOnlinePayInfoByOrderId(Integer orderId) {

		IOrderPayMapService service = ServiceFactory.create(IOrderPayMapService.class);
		Oql oql = new Oql();
		{
			oql.setType(OrderPayMap.class);
			oql.setSelects("OrderPayMap.{id,payId,orderId},pay.*");
			oql.setFilter("orderId=?");
			oql.getParameters().add("orderId", orderId, Types.INTEGER);
		}

		OrderPayMap payMap = service.queryFirst(oql);
		if (payMap != null) {

			// 注意是否判断已经划分回款业绩金额 未创建业绩总额 付款金额
			return payMap;
		}

		return null;
	}

	/**
	 * @Title: checkOrderId
	 * @Description: TODO(校验订单是否存在且付款金额小于订单金额)
	 * @param: @param orderId
	 * @param: @return
	 * @return: int
	 * @throws
	 */
	public int checkOrderId(Integer orderNo) {

		IPersister<SoOrder> orderService = PersisterFactory.create();
		String sql = "SELECT  COUNT(1)  FROM  so_order  WHERE   `no`=?  AND  paid_price<total_price;";
		QueryParameters qps = new QueryParameters();
		qps.add("@no", orderNo, Types.INTEGER);
		int num = orderService.executeInt(sql, qps);// 1存在符合条件的订单0不存在
		return num;
	}

	public SoOrder getOrderByNo(String orderNo) {

		Oql oql = new Oql();
		{
			oql.setType(SoOrder.class);
			oql.setSelects("id,no,totalPrice,payablePrice,refundPrice,paidPrice,carryAmount");
			oql.setFilter("no=? and paid_price < payable_price");
			oql.getParameters().add("no", orderNo, Types.INTEGER);
		}
		ISoOrderService service = ServiceFactory.create(ISoOrderService.class);
		return service.queryFirst(oql);
	}

	/**   
	 * @Title: save   
	 * @Description: TODO(创建回款)   
	 * @param: @param pay
	 * @param: @return      
	 * @return: Boolean      
	 * @throws   
	 */
	public Boolean applyPay(Pay pay) {

		IPayService service = ServiceFactory.create(IPayService.class);
		return service.applyPay(pay);
	}
}

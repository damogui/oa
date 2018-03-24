package com.gongsibao.trade.service.action.order.pay;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.base.IPersistableService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.util.ReflectManager;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.bd.File;
import com.gongsibao.entity.trade.NU8BankSoPayMap;
import com.gongsibao.entity.trade.OrderPayMap;
import com.gongsibao.entity.trade.Pay;
import com.gongsibao.trade.base.INU8BankSoPayMapService;
import com.gongsibao.trade.service.OrderService;

/**
 * @ClassName: ActionApplyPayPersist
 * @Description:TODO 持久化
 * @author: 韩伟
 * @date: 2018年3月22日 下午5:50:26
 * 
 * @Copyright: 2018 www.yikuaxiu.com Inc. All rights reserved.
 */
public class ActionApplyPayPersist implements IAction {

	@SuppressWarnings("unchecked")
	@Override
	public void execute(ActionContext ctx) {

		Pay pay = (Pay) ctx.getItem();
		pay.toNew();

		// 处理付款凭证
		String tableName = "so_pay";
		List<File> fileList = pay.getFiles();
		for (File file : fileList) {

			file.toNew();
			file.setTabName(tableName);
		}

		List<String> orderNoList = new ArrayList<String>();
		List<OrderPayMap> orderPayMaps = pay.getOrderPayMaps();
		for (OrderPayMap map : orderPayMaps) {

			map.toNew();
			map.setU8BankId(pay.getU8BankId());
			orderNoList.add(map.getSoOrder().getNo());
		}

		String orderNoStr = StringManager.join(",", orderNoList);
		pay.setOrderNo(orderNoStr);
		
		IPersistableService<Pay> service = (IPersistableService<Pay>) ReflectManager.newInstance(OrderService.class.getSuperclass());
		pay = service.save(pay);
		
		//转递给后续Action处理
		ctx.setItem(pay);
		
		this.saveU8BankSoPayMap(pay);
	}

	private void saveU8BankSoPayMap(Pay pay) {

		NU8BankSoPayMap nU8BankSoPayMap = new NU8BankSoPayMap();
		{
			nU8BankSoPayMap.setPayId(pay.getId());
			nU8BankSoPayMap.setSetOfBooksId(pay.getSetOfBooksId());
			nU8BankSoPayMap.setType(0);
			nU8BankSoPayMap.setU8BankId(pay.getU8BankId());
			nU8BankSoPayMap.setPrice(pay.getAmount());
			nU8BankSoPayMap.toNew();
		}
		INU8BankSoPayMapService nU8BankSoPayMapService = ServiceFactory.create(INU8BankSoPayMapService.class);
		nU8BankSoPayMapService.save(nU8BankSoPayMap);
	}
}
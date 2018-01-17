package com.gongsibao.igirl.web;
import com.gongsibao.entity.igirl.baseinfo.NCLTwo;
import com.gongsibao.igirl.base.INCLTwoService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.DetailPart;

import java.util.List;

public class TradeMarkDetailPart extends DetailPart{
	INCLTwoService nclTwoService= ServiceFactory.create(INCLTwoService.class);
	public List<NCLTwo> findSubsByNclOneId(int ncloneId){
		List<NCLTwo> ncls=nclTwoService.findSubsByNclOneId(ncloneId);
		return  ncls;
	}

//	ICustomerFollowService service = ServiceFactory.create(ICustomerFollowService.class);
//	public CustomerFollow save(CustomerFollow entity){
//
//		entity = service.save(entity);
//		return entity;
//	}

}
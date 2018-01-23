package com.gongsibao.igirl.web;
import com.gongsibao.entity.igirl.TradeMarkCase;
import com.gongsibao.igirl.base.ITradeMarkCaseService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.annotation.Authorization;


public class TradeMarkCaseController {
     ITradeMarkCaseService tradeMarkCaseService = ServiceFactory.create(ITradeMarkCaseService.class); 
     @Authorization(is = false)
     public TradeMarkCase getTradeMarkCaseModelByMobile(String mobile) {  
    	 TradeMarkCase tmc=tradeMarkCaseService.getTradeMarkCaseModelByMobile(mobile);  
    	 return 	tmc; 
     }
}
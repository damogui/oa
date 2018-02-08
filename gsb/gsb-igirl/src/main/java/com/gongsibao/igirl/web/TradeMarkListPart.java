package com.gongsibao.igirl.web;
import com.gongsibao.entity.igirl.TradeMark;
import com.gongsibao.entity.igirl.TradeMarkCase;
import com.gongsibao.entity.igirl.UploadAttachment;
import com.gongsibao.entity.igirl.baseinfo.IGirlConfig;
import com.gongsibao.entity.igirl.dict.AttachmentCat;
import com.gongsibao.entity.igirl.dict.ConfigType;
import com.gongsibao.igirl.base.ITradeMarkService;
import com.gongsibao.utils.SupplierSessionManager;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
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
    
  private Map<Integer,String> buildShareGroupToTMAttachment(TradeMark tm) {
	   Map<Integer,String> map=new HashMap<Integer,String>();
	   for(UploadAttachment ua : tm.getTradeMarkCase().getUploadAttachments()) {
		      if(ua.getAttachmentCat()==AttachmentCat.TRADEMARK_PICT && tm.getShareGroup()==ua.getShareGroup()) {
		    	  Integer key=tm.getShareGroup().getValue();
		    	  if(!map.containsKey(key)) {
		    		    map.put(key, ua.getFileUrl());
		    	         }
		             }
	      }
	   return map;
    }
    
  public String getTradeMarkPicUrl(Integer markId) {
	  String url="";
	  Oql oql=new Oql();{
			oql.setType(TradeMark.class);
			oql.setSelects("TradeMark.*,TradeMark.tradeMarkCase.*,TradeMark.tradeMarkCase.uploadAttachments.*");
			oql.setFilter(" id=? ");
			oql.getParameters().add("id",markId,Types.INTEGER);
		}
	  TradeMark tm=service.queryFirst(oql);
	  Map<Integer,String> map=buildShareGroupToTMAttachment(tm);
	  url=map.get(tm.getShareGroup().getValue());
    return url;
    }
   @Override
	 protected String getExtraFilter() {
			// TODO Auto-generated method stub
	   String filter=" supplierId = "+SupplierSessionManager.getSupplierId();
		 return filter;
	 }
}
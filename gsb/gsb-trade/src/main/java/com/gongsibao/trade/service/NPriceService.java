package com.gongsibao.trade.service;

import com.gongsibao.entity.trade.NPrice;
import com.gongsibao.entity.trade.OrderCps;
import com.gongsibao.trade.base.INPriceService;
import com.gongsibao.trade.base.IOrderCpsService;
import org.netsharp.service.PersistableService;

/**
 * Created by win on 2018/2/27.
 */
public class NPriceService extends PersistableService<NPrice> implements INPriceService {

    public NPriceService(){
        super();
        this.type=NPrice.class;
    }
}
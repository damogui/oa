package com.gongsibao.trade.base.settle;

import com.gongsibao.entity.Result;
import com.gongsibao.entity.trade.settle.Settle;
import com.gongsibao.entity.trade.settle.dict.SettleHandleStatus;
import org.netsharp.base.IPersistableService;
import org.netsharp.core.annotations.Transaction;

import java.util.List;

public interface ISettleService extends IPersistableService<Settle> {

    @Transaction
    Result<Settle> saveSettle(List<Integer> orderProdCaseIds);

    @Transaction
    Boolean updateStatusHandleStatus(int settleId, SettleHandleStatus handleStatus);
}

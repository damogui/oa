package com.gongsibao.trade.service.action.order.contract;

import com.gongsibao.entity.crm.NCustomer;
import com.gongsibao.entity.trade.Contract;
import com.gongsibao.entity.trade.dic.AuditStatusType;
import com.gongsibao.trade.base.IContractService;
import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.base.IPersistableService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.util.ReflectManager;

/**
 * Created by zhangchao on 2018/3/21.
 */
public class ActionApplyContractPersist implements IAction {

    IContractService contractService = ServiceFactory.create(IContractService.class);

    @Override
    public void execute(ActionContext ctx) {
        //合同
        Contract contract = (Contract) ctx.getItem();
        //初始状态为【待审核】
        contract.setAuditStatusId(AuditStatusType.Dsh);
        contract = contractService.save(contract);
        //更新合同
        ctx.setItem(contract);



    }
}

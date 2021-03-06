package com.gongsibao.trade.service.action.order.performance;

import com.gongsibao.entity.bd.dic.AuditLogType;
import com.gongsibao.entity.trade.NDepReceivable;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.entity.trade.dic.AuditStatusType;
import com.gongsibao.trade.service.action.order.utils.AuditHelper;
import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.core.BusinessException;
import org.netsharp.core.EntityState;

import java.util.List;

/*创建订单业绩校验*/
public class ActionApplyOrderPerformanceVerify implements IAction {

    @Override
    public void execute(ActionContext ctx) {
        // TODO Auto-generated method stub

        SoOrder entity = (SoOrder) ctx.getItem ();//进行校验金额
        //根据订单Id获取订单实体

        Integer execNum = AuditHelper.getRecode (entity.getId (), AuditLogType.DdYjSq.getValue ());
        if (execNum > 0) {

            throw new BusinessException ("订单业绩已经创建");//execNum

        }
        //判断是不是存在订单结转转入
        Integer execNum2 = AuditHelper.getCarryRecode (entity.getId (),AuditStatusType.Dsh);
        if (execNum2 > 0) {

            throw new BusinessException ("【该订单有笔结转转入额还在审核中，请审核通过后再创建】");//execNum

        }


        List<NDepReceivable> depList = entity.getDepReceivable ();
        int totalAmount = 0;
        for (NDepReceivable item : depList
                ) {
            if (!item.getEntityState ().equals (EntityState.Deleted)) {

                totalAmount += item.getAmount ();
            }
            item.setStatusType (AuditStatusType.Dsh);

        }
        //totalAmount = totalAmount / 100;//数据库以分进行保存

        if (totalAmount == 0) {
            throw new BusinessException ("订单业绩必须分配！");
        }

        if ((entity.getPayablePrice()-entity.getCarryIntoAmount())<=0) {//


            throw new BusinessException ("【该订单无业绩可创建，请核实】");
        }
        //entity.setPerformancePrice (totalAmount);
        ctx.setItem (entity);


    }

}

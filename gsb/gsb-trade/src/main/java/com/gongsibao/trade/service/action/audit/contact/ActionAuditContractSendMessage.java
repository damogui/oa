package com.gongsibao.trade.service.action.audit.contact;

import com.gongsibao.bd.service.auditLog.AuditContext;
import com.gongsibao.bd.service.auditLog.AuditState;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.er.Order;
import com.gongsibao.entity.trade.Contract;
import com.gongsibao.entity.trade.NDepReceivable;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.base.INDepReceivableService;
import com.gongsibao.trade.service.action.order.utils.AuditHelper;
import com.gongsibao.trade.service.action.order.utils.UserHelper;
import com.gongsibao.utils.sms.SmsHelper;
import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;

import java.util.List;
import java.util.Map;

/*合同消息*/
public class ActionAuditContractSendMessage implements IAction {

    @Override
    public void execute(ActionContext ctx) {
        AuditContext auditContext = (AuditContext) ctx.getItem();
        Map<String, Object> objectMap = ctx.getStatus();
        AuditLog auditLog = (AuditLog) objectMap.get("auditLog");
        Contract contract = (Contract) objectMap.get("contract");
        SoOrder soOrder = AuditHelper.getOrderById(contract.getOrderId());
        //本次审核通过或驳回
        AuditState state = auditContext.getState();
        //审核
        //审核意见
        String remark = auditContext.getremark();
        auditSend(state, auditLog, soOrder, remark);
    }

    /*进行发送消息*/
    private void auditSend(AuditState state, AuditLog auditLog, SoOrder soOrder, String remark) {

        if (soOrder == null || auditLog == null) {

            return;
        }
        switch (state.getValue()) {
            case 0://驳回审核
                if (soOrder.getOwner() != null) {

                    String content = String.format("【合同审核提醒】您好，您有1个订单提交的合同申请审核不通过，订单编号为【%s】，原因为【%s】,请知悉", soOrder.getNo(), remark);
                    SmsHelper.send(soOrder.getOwner().getMobile(), content);//订单业务员
                }
                break;
            case 1://通过审核
                if (auditLog.getLevel().equals(auditLog.getMaxLevel())) {
                    //通过审核
                    String content = String.format("【合同审核提醒】您好，您有1个订单提交的合同申请已审核通过，订单编号为【%s】，请知悉", soOrder.getNo());
                    SmsHelper.send(soOrder.getOwner().getMobile(), content);//订单业务员

                } else {
                    //通知下一级
                    List<Integer> userIds = AuditHelper.getNextLevelUserIds(auditLog.getFormId(), auditLog.getType().getValue(), auditLog.getLevel() + 1);//获取下一级要通知的人 fromId
                    sendNextAudit(userIds, soOrder.getNo());//下一级审核

                }
                break;
        }

    }

    /*下一级审核*/
    private void sendNextAudit(List<Integer> userIds, String no) {
        for (Integer item : userIds
                ) {
            String content = String.format("【合同待审核提醒】您好，有N个合同申请待您审核，请及时审核");
            SmsHelper.send(UserHelper.getEmployeTelById(item), content);//电话和内容
        }

    }
}

package com.gongsibao.trade.service.action.audit.contact;

import java.util.HashMap;
import java.util.Map;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;
import org.netsharp.util.StringManager;

import com.gongsibao.bd.base.IAuditLogService;
import com.gongsibao.bd.service.auditLog.AuditContext;
import com.gongsibao.bd.service.auditLog.AuditState;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.bd.dic.AuditLogStatusType;
import com.gongsibao.entity.bd.dic.AuditLogType;
import com.gongsibao.entity.trade.Contract;
import com.gongsibao.entity.trade.dic.AuditStatusType;
import com.gongsibao.trade.base.IContractService;

public class ActionAuditContractVerify implements IAction {

	IAuditLogService auditService = ServiceFactory.create(IAuditLogService.class);

    IContractService contractService = ServiceFactory.create(IContractService.class);


    @Override
    public void execute(ActionContext ctx) {

        AuditContext auditContext = (AuditContext) ctx.getItem();

        if (auditContext == null) {
            throw new BusinessException("审核信息不能为空");
        }

        AuditLog auditLog = auditService.getById(auditContext.getAuditLogId());

        if (auditLog == null) {
            throw new BusinessException("审核信息不能为空");
        }

        if (auditContext.getState().equals(AuditState.NOTPASS) && StringManager.isNullOrEmpty(auditContext.getremark())) {
            throw new BusinessException("当审核失败时，审批意见不能为空");
        }

        if (!auditLog.getStatus().equals(AuditLogStatusType.TOAUDIT)) {
            throw new BusinessException("该审核状态不是【" + AuditLogStatusType.TOAUDIT.getText() + "】,禁止审核");
        }

        if (!auditLog.getType().equals(AuditLogType.Htsq)) {
            throw new BusinessException("该审核类别不是【" + AuditLogType.Htsq.getText() + "】,禁止审核");
        }

        Contract contract = contractService.getById(auditLog.getFormId());
        if (contract == null) {
            throw new BusinessException("该合同信息不存在");
        }

        if (!contract.getAuditStatusId().equals(AuditStatusType.Dsh)) {
            throw new BusinessException("该合同不是【" + AuditStatusType.Dsh.getText() + "】,禁止审核");
        }
        Map<String, Object> statusMap = new HashMap<String, Object>();
        statusMap.put("auditLog", auditLog);
        statusMap.put("contract", contract);
        ctx.setStatus(statusMap);
    }

}

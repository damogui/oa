package com.gongsibao.trade.service.action.audit.invoice;

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
import com.gongsibao.entity.trade.Invoice;
import com.gongsibao.entity.trade.dic.AuditStatusType;
import com.gongsibao.trade.base.IInvoiceService;

public class ActionAuditInvoiceVerify implements IAction {

	IAuditLogService auditService = ServiceFactory.create(IAuditLogService.class);

    IInvoiceService invoiceService = ServiceFactory.create(IInvoiceService.class);

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

        if (!auditLog.getType().equals(AuditLogType.Fbsq)) {
            throw new BusinessException("该审核类别不是【" + AuditLogType.Fbsq.getText() + "】,禁止审核");
        }

        Integer formId = auditLog.getFormId();
        Invoice invoice = invoiceService.getById(formId);
        if (invoice == null) {
            throw new BusinessException("该发票信息不存在");
        }
        if (!invoice.getAuditStatusId().equals(AuditStatusType.Dsh)) {
            throw new BusinessException("该发票不是【" + AuditStatusType.Dsh.getText() + "】,禁止审核");
        }

        Map<String, Object> statusMap = new HashMap<String, Object>();
        statusMap.put("auditLog", auditLog);
        statusMap.put("invoice", invoice);
        ctx.setStatus(statusMap);

    }

}

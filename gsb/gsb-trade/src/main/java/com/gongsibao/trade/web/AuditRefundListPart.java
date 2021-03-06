package com.gongsibao.trade.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.gongsibao.trade.web.audithelper.SetOfBooksNameHelper;
import org.netsharp.core.Oql;
import org.netsharp.panda.commerce.AdvancedListPart;
import org.netsharp.panda.commerce.FilterParameter;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.utils.NumberUtils;

public class AuditRefundListPart extends AdvancedListPart {

    @Override
    public String getFilterByParameter(FilterParameter parameter) {

        ArrayList<String> filters = new ArrayList<String>();
        //当是关键字时(订单编号、渠道订单编号、下单人、下单人电话、关联公司)
        String keyword = parameter.getValue1().toString();

        if (parameter.getKey().equals("keyword")) {
            filters.add("no ='" + keyword + "'");
            filters.add("channel_order_no = '" + keyword + "'");
            filters.add("company_id in( select pkid from crm_company_intention where (name like '%" + keyword + "%' or full_name like '%" + keyword + "%' or company_name like '%" + keyword + "%' )  )");
            return " form_id in (select pkid from so_refund where order_id in ( select pkid from so_order where " + StringManager.join(" or ", filters) + "))";
        }
        if (parameter.getKey().equals("pay.setOfBooksId")) {//付款账套的筛选

            return String.format("fefund.orderId  IN ( SELECT order_id FROM so_order_pay_map WHERE pay_id IN ( SELECT pkid FROM so_pay WHERE set_of_books_id='%s'))", keyword);

        }
        return parameter.getFilter();
    }

    @Override
    public List<?> doQuery(Oql oql) {
        oql.setSelects("auditLog.*,fefund.*,fefund.soOrder.*,fefund.soOrder.companyIntention.*");
        oql.setOrderby("add_time DESC");
        List<AuditLog> resList = (List<AuditLog>) super.doQuery(oql);
        return resList;
    }

    @Override
    protected Object serialize(List<?> list, Oql oql) {
        HashMap<String, Object> json = (HashMap<String, Object>) super.serialize(list, oql);
        ArrayList<HashMap<String, Object>> ob2 = (ArrayList<HashMap<String, Object>>) json.get("rows");
        for (int i = 0; i < ob2.size(); i++) {

            AuditLog auditLog = ((AuditLog) list.get(i));
            SoOrder soOrder = auditLog.getFefund().getSoOrder();
            //this.balance = paidPrice + carryIntoAmount - refundPrice - carryAmount;
            Integer balance = NumberUtils.toInt(soOrder.getPaidPrice()) + NumberUtils.toInt(soOrder.getCarryIntoAmount()) - NumberUtils.toInt(soOrder.getRefundPrice()) - NumberUtils.toInt(soOrder.getCarryAmount());
            Integer toBePaidPrice = soOrder.getPayablePrice().intValue() - balance;
            ob2.get(i).put("fefund_soOrder_toBePaidPrice", toBePaidPrice);
            if (soOrder.getCompanyIntention() != null) {
                ob2.get(i).put("fefund_soOrder_companyIntention_companyName", soOrder.getCompanyIntention().getCompanyName());
            } else {
                ob2.get(i).put("fefund_soOrder_companyIntention_companyName", "");
            }

            String setOfBooksName = SetOfBooksNameHelper.getSetOfBooksNameByOrderId(soOrder.getId());
            ob2.get(i).put("booksName", setOfBooksName);

        }
        return json;
    }
}

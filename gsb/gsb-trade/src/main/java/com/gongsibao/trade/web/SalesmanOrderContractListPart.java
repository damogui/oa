package com.gongsibao.trade.web;

import java.util.ArrayList;

import org.netsharp.panda.commerce.AdvancedListPart;
import org.netsharp.panda.commerce.FilterParameter;
import org.netsharp.util.StringManager;

/**
 * Created by zhangchao on 2018/3/15.
 */
public class SalesmanOrderContractListPart extends AdvancedListPart {

    @Override
    public String getFilterByParameter(FilterParameter parameter) {

        ArrayList<String> filters = new ArrayList<String>();
        //当是关键字时(订单编号、渠道订单编号、下单人、下单人电话、关联公司)
        String keyword = parameter.getValue1().toString();
        if (parameter.getKey().equals("keyword")) {

            filters.add("soOrder.no like '%" + keyword + "%'");
            filters.add("soOrder.channel_order_no like '%" + keyword + "%'");
            filters.add("soOrder.account_mobile like '%" + keyword + "%'");
            filters.add("soOrder.account_name like '%" + keyword + "%'");
            filters.add("company_id in( select pkid from crm_company_intention where (name like '%" + keyword + "%' or full_name like '%" + keyword + "%' or company_name like '%" + keyword + "%' )  )");

            return "(" + StringManager.join(" or ", filters) + ") ";
        }

        return parameter.getFilter();
    }
}
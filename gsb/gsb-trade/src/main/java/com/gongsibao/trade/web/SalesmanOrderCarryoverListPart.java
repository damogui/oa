package com.gongsibao.trade.web;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.core.Oql;
import org.netsharp.panda.commerce.AdvancedListPart;
import org.netsharp.panda.commerce.FilterParameter;
import org.netsharp.util.StringManager;

/**
 * Created by zhangchao on 2018/3/15.
 */
public class SalesmanOrderCarryoverListPart extends AdvancedListPart {

    @Override
    public String getFilterByParameter(FilterParameter parameter) {

        ArrayList<String> filters = new ArrayList<String>();
        //当是关键字时(订单编号、渠道订单编号、下单人、下单人电话、关联公司)
        String keyword = parameter.getValue1().toString();
        if (parameter.getKey().equals("keyword")) {

            filters.add("form_order_no = '" + keyword + "'");
            filters.add("to_order_no = '" + keyword + "'");
            return "(" + StringManager.join(" or ", filters) + ")";
        }

        return parameter.getFilter();
    }

    @Override
    public List<?> doQuery(Oql oql) {
        oql.setOrderby("create_time DESC");
        return super.doQuery(oql);
    }
}

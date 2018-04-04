package com.gongsibao.igirl.settle.web;

import com.gongsibao.entity.trade.dic.SettleStatus;
import org.netsharp.panda.commerce.ListPart;
import org.netsharp.util.StringManager;

import java.util.ArrayList;
import java.util.List;

/**
 * 待结算订单列表
 */
public class UnSettleListPart extends ListPart {

    @Override
    protected String getExtraFilter() {
        List<String> filterList = new ArrayList<>();

        // 产品id过滤, 目前只支持商标结算
        filterList.add(" orderProd.productId IN (1137, 1177, 1823, 1514) ");

        // 结算状态
//        filterList.add(" orderProd.settleStatus = " + SettleStatus.NO_SETTLEMENT.getValue());
        return StringManager.join(" and ", filterList);
    }

    public Object submitSettle(List<Integer> orderProdIds) {
        return null;
    }
}
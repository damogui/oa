package com.gongsibao.taurus.api;

import com.gongsibao.taurus.message.response.ItemCountResponseMessage;

import java.util.List;

/**
 * 年报数量统计
 */
public class YearReportCountApi extends AbstractApi<ItemCountResponseMessage> {

    /**
     * 公司名称
     */
    private String names;

    @Override
    protected void setExtendParameter(List<String> parameters) {
        if (null != parameters) {
            parameters.add("names=" + this.getNames());
        }
    }

    @Override
    protected String getUrl() {
        return "/dataapi/v3/ic/getBusinessReport";
    }

    @Override
    public Class<?> getResponseType() {
        return ItemCountResponseMessage.class;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }
}
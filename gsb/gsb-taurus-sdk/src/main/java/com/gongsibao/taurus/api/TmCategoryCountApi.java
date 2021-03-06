package com.gongsibao.taurus.api;

import com.gongsibao.taurus.message.response.CountResponseMessage;

import java.util.List;

/**
 * 查询公司详情api
 */
public class TmCategoryCountApi extends AbstractApi<CountResponseMessage> {

    /**
     * 公司名称
     */
    private String name;

    @Override
    protected void setExtendParameter(List<String> parameters) {
        if (null != parameters) {
            parameters.add("name=" + this.getName());
        }
    }

    @Override
    protected String getUrl() {
        return "/dataapi/v3/ic/getTmClassCountByCompanyName";
    }
    @Override
    public int getInterfaceType() {
        return 1;
    }

    @Override
    public Class<?> getResponseType() {
        return CountResponseMessage.class;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

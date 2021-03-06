package com.gongsibao.taurus.api;

import com.gongsibao.taurus.message.response.TmAssembleResponseMessage;

import java.util.List;

/**
 * 已有商标（聚合）
 */
public class TmRegAssembleListApi extends AbstractApi<TmAssembleResponseMessage> {

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
        return "/dataapi/v3/ic/getTmNameListByCompanyName";
    }
    @Override
    public int getInterfaceType() {
        return 1;
    }

    @Override
    public Class<?> getResponseType() {
        return TmAssembleResponseMessage.class;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package com.gongsibao.account.service;

import com.gongsibao.account.base.IAccountService;
import com.gongsibao.account.base.IAccountWeiXinService;
import com.gongsibao.entity.acount.Account;
import com.gongsibao.entity.acount.AccountWeiXin;
import com.gongsibao.entity.acount.AccountWxMsg;
import org.apache.commons.lang3.StringUtils;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.core.QueryParameters;
import org.netsharp.panda.controls.utility.UrlHelper;
import org.netsharp.service.PersistableService;
import org.netsharp.util.sqlbuilder.UpdateBuilder;
import org.netsharp.wx.mp.api.accesstoken.AccessToken;
import org.netsharp.wx.mp.api.accesstoken.AccessTokenManage;
import org.netsharp.wx.mp.api.messagetemplate.*;
import org.netsharp.wx.pa.WeixinMessageListener;
import org.netsharp.wx.pa.base.ICustomService;
import org.netsharp.wx.pa.base.IFansService;
import org.netsharp.wx.pa.base.IPublicAccountService;
import org.netsharp.wx.pa.entity.Fans;
import org.netsharp.wx.pa.entity.PublicAccount;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

@Service
public class AccountWeiXinService extends PersistableService<AccountWeiXin> implements IAccountWeiXinService {
    IFansService fansService = ServiceFactory.create(IFansService.class);
    ICustomService customService = ServiceFactory.create(ICustomService.class);
    IAccountService accountService = ServiceFactory.create(IAccountService.class);
    /**
     * 微信回调url前缀
     */
    public final static String SYSINQUIRY_CONTINUE_CALLBACK_URL_PREFIX = "https://open.weixin.qq.com/connect/oauth2/authorize?from=weixin&appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s#wechat_redirect";
    public static final String ORDER_CHANGE_STATE_MSG = "您购买的服务\"%s\" 办理进度变更为 \"%s\" \n\r" + "<a href=\"%s\">点此查看详情>></a>";
    public static final String OID = "gh_29f5a8b8da16";

    public AccountWeiXinService() {
        super();
        this.type = AccountWeiXin.class;
    }

    /**
     * @param accountId, openId
     * @return void
     * @Description:TODO 绑定手机号
     * @author hbpeng <hbpeng@gongsibao.com>
     * @date 2018/4/16 13:44
     */
    @Override
    public Boolean bandMobile(int accountId, String openId) {
        UpdateBuilder updateBuilder = UpdateBuilder.getInstance();
        {
            updateBuilder.update("wx_pa_fans");
            updateBuilder.set("user_id", accountId);
            updateBuilder.where("openid='" + openId + "'");
        }
        String cmdText = updateBuilder.toSQL();
        return this.pm.executeNonQuery(cmdText, null) > 0;
    }

    @Override
    public Boolean unBandUserId(int accountId) {
        UpdateBuilder updateBuilder = UpdateBuilder.getInstance();
        {
            updateBuilder.update("wx_pa_fans");
            updateBuilder.set("user_id", 0);
            updateBuilder.where("user_id = " + accountId);
        }
        String cmdText = updateBuilder.toSQL();
        return this.pm.executeNonQuery(cmdText, null) > 0;
    }

    @Override
    public Account queryByOpenId(String openId) {
        Fans fans = this.queryFansByOpenId(openId);
        if (null != fans && null != fans.getUserId()) {
            Oql oql = new Oql();
            {
                oql.setType(Account.class);
                oql.setSelects("*");
                oql.setFilter("pkid=?");
                oql.getParameters().add("pkid", fans.getUserId(), Types.INTEGER);
            }
            return accountService.queryFirst(oql);
        }
        return null;
    }

    @Override
    public Fans queryFansByOpenId(String openId) {
        Oql oql = new Oql();
        {
            oql.setType(Fans.class);
            oql.setSelects("*");
            oql.setFilter("openId=?");
            oql.getParameters().add("openId", openId, Types.VARCHAR);
        }
        return fansService.queryFirst(oql);
    }

    /**
     * @param openId
     * @return org.netsharp.wx.pa.entity.Fans
     * @Description:TODO 新增粉丝
     * @author hbpeng <hbpeng@gongsibao.com>
     * @date 2018/4/19 11:43
     */
    @Override
    public Fans createFans(String openId) {
        Fans fans = new Fans();
        {
            fans.toNew();
            fans.setOpenId(openId);
        }
        return fansService.save(fans);
    }

    @Override
    public Fans queryFansByUserId(Integer userId) {
        Oql oql = new Oql();
        {
            oql.setType(Fans.class);
            oql.setSelects("Fans.*");
            oql.setFilter("user_id=?");
            oql.getParameters().add("user_id", userId, Types.INTEGER);
        }
        return fansService.queryFirst(oql);
    }

    /**
     * @param mobile, orderPorudctId
     * @return void
     * @Description:TODO
     * @author hbpeng <hbpeng@gongsibao.com>
     * @date 2018/4/18 16:57
     */
    @Override
    public void pushOrderStateMsg(String originalId, String mobile, Integer orderPorudctId) {
        String sql = "select * from so_order_prod where pkid=? ";
        QueryParameters qps = new QueryParameters();
        qps.add("@pkid", orderPorudctId, Types.INTEGER);
        ResultSet rs = this.pm.executeReader(sql, qps);


        String sqlTrace = "select * from so_order_prod_trace where order_prod_id=? order by add_time desc limit 1";
        QueryParameters qpsTrace = new QueryParameters();
        qpsTrace.add("@order_prod_id", orderPorudctId, Types.INTEGER);
        ResultSet rsTrace = this.pm.executeReader(sqlTrace, qpsTrace);
        String proName = null;
        Integer orderId = null;
        String proTrace = null;
        String orderNo = null;
        try {
            if (rs.next()) {
                proName = rs.getString("product_name");
                orderId = rs.getInt("order_id");
            }
            if (rsTrace.next()) {
                proTrace = rsTrace.getString("info");
            }
            //查询订单
            String sqlOrder = "select no from so_order where pkid=? ";
            QueryParameters qpsOrder = new QueryParameters();
            qpsOrder.add("@pkid", orderId, Types.INTEGER);
            ResultSet rsOrder = this.pm.executeReader(sqlOrder, qpsOrder);
            if (rsOrder.next()) {
                orderNo = rsOrder.getString("no");
            }
            if (null != proName && null != proTrace) {
                //取用户信息
                Account account = accountService.byMobile(mobile);
                //取微信用户openid
                Fans accountWeiXin = this.queryFansByUserId(account.getId());
                this.pushTextMsgByOriginalId(originalId, account.getId(), "尊敬的" + accountWeiXin.getNickname() + ":", orderNo, proTrace, null, "/index.html#/order/details/" + orderPorudctId, null, AccountWxMsg.ORDER_STATE_CHANGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param accountId 账户id,
     *                  first 模板消息对应字段,
     *                  keyword1 模板消息按顺序第一个字段,
     *                  keyword2 模板消息按顺序第二个字段,
     *                  date 时间,
     *                  remark 备注,
     *                  tmpId 消息模板id
     * @return void
     * @Description:TODO 发送模板消息
     * @author hbpeng <hbpeng@gongsibao.com>
     * @date 2018/4/19 14:14
     */
    @Override
    public void pushTextMsg(Integer accountId,
                            String first,
                            String keyword1,
                            String keyword2,
                            String date,
                            String url,
                            String remark,
                            AccountWxMsg tmpId) {
        IPublicAccountService publicAccountService = ServiceFactory.create(IPublicAccountService.class);
        //取公众号配置
        PublicAccount weixinConfig = publicAccountService.byOriginalId(OID);
        //取微信用户openid
        Fans fans = this.queryFansByUserId(accountId);
        //获取token
        AccessToken token = token(weixinConfig);
        String template_id = this.getTemplateId(token, tmpId.getEmpId());
        SendTemplateData data = new SendTemplateData();
        {
            if (tmpId.getEmpId().equals(AccountWxMsg.BUY_SUCCESS.getEmpId())) {
                data.getFirst().setValue(first);
                data.getKeynotes().put("orderMoneySum", new KeyNote(keyword1));
                data.getKeynotes().put("orderProductName", new KeyNote(keyword2));
                if (StringUtils.isNotEmpty(remark))
                    data.getKeynotes().put("Remark", new KeyNote(remark));
                else
                    data.getKeynotes().put("Remark", new KeyNote(""));
            } else if (tmpId.getEmpId().equals(AccountWxMsg.ORDER_STATE_CHANGE.getEmpId())) {
                data.getFirst().setValue(first);
                data.getKeynotes().put("OrderSn", new KeyNote(keyword1));
                data.getKeynotes().put("OrderStatus", new KeyNote(keyword2));
                if (StringUtils.isNotEmpty(remark)) {
                    data.getKeynotes().put("remark", new KeyNote(remark));
                } else {
                    data.getKeynotes().put("remark", new KeyNote(""));
                    data.setRemark(new KeyNote(""));
                }
            } else if (tmpId.getEmpId().equals(AccountWxMsg.WORK_PROCESS_CHANGE.getEmpId())) {
                data.getFirst().setValue(first);
                data.getKeynotes().put("keyword1", new KeyNote(keyword1));
                data.getKeynotes().put("keyword2", new KeyNote(keyword2));
                data.getKeynotes().put("keyword3", new KeyNote(date));
                if (StringUtils.isNotEmpty(remark)) {
                    data.getKeynotes().put("remark", new KeyNote(remark));
                } else {
                    data.getKeynotes().put("remark", new KeyNote(""));
                    data.setRemark(new KeyNote(""));
                }
            }
        }
        //拼接消息内容
        String redirectUrl = UrlHelper.encode("http://" + weixinConfig.getHost() + UrlHelper.join(url, "originalId=" + OID));
        SendTemplateMessageRequest request = new SendTemplateMessageRequest();
        {
            request.setTokenInfo(token);
            request.setTouser(fans.getOpenId());
            request.setTemplate_id(template_id);
            request.setPageUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + weixinConfig.getAppId() + "&redirect_uri=" + redirectUrl + "&response_type=code&scope=snsapi_base&state=111echat_redirect&connect_redirect=1#wechat_redirect");
            request.setData(data);
        }
        request.getResponse();
    }

    @Override
    public void pushTextMsgByOriginalId(String originalId, Integer accountId, String first, String keyword1, String keyword2, String date, String url, String remark, AccountWxMsg tmpId) {
        IPublicAccountService publicAccountService = ServiceFactory.create(IPublicAccountService.class);
        //取公众号配置
        PublicAccount weixinConfig = publicAccountService.byOriginalId(originalId);
        //取微信用户openid
        Fans fans = this.queryFansByUserId(accountId);
        //获取token
        AccessToken token = token(weixinConfig);
        String template_id = tmpId.getEmpId();
        SendTemplateData data = new SendTemplateData();
        {
            if (tmpId.getEmpId().equals(AccountWxMsg.BUY_SUCCESS.getEmpId())) {
                data.getFirst().setValue(first);
                data.getKeynotes().put("orderMoneySum", new KeyNote(keyword1));
                data.getKeynotes().put("orderProductName", new KeyNote(keyword2));
                if (StringUtils.isNotEmpty(remark))
                    data.getKeynotes().put("Remark", new KeyNote(remark));
                else
                    data.getKeynotes().put("Remark", new KeyNote(""));
            } else if (tmpId.getEmpId().equals(AccountWxMsg.ORDER_STATE_CHANGE.getEmpId())) {
                data.getFirst().setValue(first);
                data.getKeynotes().put("OrderSn", new KeyNote(keyword1));
                data.getKeynotes().put("OrderStatus", new KeyNote(keyword2));
                if (StringUtils.isNotEmpty(remark)) {
                    data.getKeynotes().put("remark", new KeyNote(remark));
                } else {
                    data.getKeynotes().put("remark", new KeyNote(""));
                    data.setRemark(new KeyNote(""));
                }
            } else if (tmpId.getEmpId().equals(AccountWxMsg.WORK_PROCESS_CHANGE.getEmpId())) {
                data.getFirst().setValue(first);
                data.getKeynotes().put("keyword1", new KeyNote(keyword1));
                data.getKeynotes().put("keyword2", new KeyNote(keyword2));
                data.getKeynotes().put("keyword3", new KeyNote(date));
                if (StringUtils.isNotEmpty(remark)) {
                    data.getKeynotes().put("remark", new KeyNote(remark));
                } else {
                    data.getKeynotes().put("remark", new KeyNote(""));
                    data.setRemark(new KeyNote(""));
                }
            }
        }
        //拼接消息内容
        String redirectUrl = UrlHelper.encode("http://" + weixinConfig.getHost() + UrlHelper.join(url, "originalId=" + OID));
        SendTemplateMessageRequest request = new SendTemplateMessageRequest();
        {
            request.setTokenInfo(token);
            request.setTouser(fans.getOpenId());
            request.setTemplate_id(template_id);
            request.setPageUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + weixinConfig.getAppId() + "&redirect_uri=" + redirectUrl + "&response_type=code&scope=snsapi_base&state=111echat_redirect&connect_redirect=1#wechat_redirect");
            request.setData(data);
        }
        request.getResponse();
    }

    private AccessToken token(PublicAccount publicAccount) {
        //获取token
        WeixinMessageListener listner = new WeixinMessageListener();
        AccessTokenManage.refreshToken(publicAccount.getAppId());
        return listner.getAccessToken(publicAccount.getOriginalId());
    }

    /**
     * @param token, shortTmpId
     * @return java.lang.String
     * @Description:TODO 根据短id 获取模板id
     * @author hbpeng <hbpeng@gongsibao.com>
     * @date 2018/4/19 13:54
     */
    private String getTemplateId(AccessToken token, String shortTmpId) {
        AddTemplateRequest request = new AddTemplateRequest();
        {
            request.setTokenInfo(token);
            //OPENTM415261101 办理进度提醒
            request.setTemplate_id_short(shortTmpId);
        }
        AddTemplateResponse response = request.getResponse();
        System.out.println(response.getTemplate_id());
        return response.getTemplate_id();
    }
}
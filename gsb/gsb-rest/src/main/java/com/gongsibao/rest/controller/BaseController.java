package com.gongsibao.rest.controller;

import com.gongsibao.entity.acount.Account;
import com.netsharp.rest.base.user.IAccountService;
import com.netsharp.rest.controller.result.UserHeaders;
import com.netsharp.rest.utils.Assert;
import com.netsharp.rest.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ffli <ffli@gongsibao.com>
 * @Description: TODO
 * @date 2018/4/17 16:54
 */
public class BaseController {

    @Autowired
    IAccountService $accountService;

    protected String openId(HttpServletRequest request){
        return StringUtils.trimToEmpty(request.getHeader(UserHeaders.openId));
    }
    protected String originalId(HttpServletRequest request){
        return StringUtils.trimToEmpty(request.getHeader(UserHeaders.originalId));
    }

    protected Account accountByOpenId(HttpServletRequest request){
        return accountByOpenId(openId(request));
    }

    protected Integer accountIdByOpenId(HttpServletRequest request){
        return accountIdByOpenId(openId(request));
    }

    protected Account accountByOpenId(String openId){
        Assert.hasText(openId,"尚未登录!");
        Account account = $accountService.queryByOpenId(openId);
        Assert.notNull(account,"账号尚未绑定!");
        return account;
    }

    protected Integer accountIdByOpenId(String openId){
        Account account = accountByOpenId(openId);
        Assert.notNull(account.getId(),"账号尚未绑定!");
        return account.getId();
    }

    protected   String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}

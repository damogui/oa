package org.netsharp.wx.pa.response.subscribe;

import org.netsharp.wx.mp.message.ResponseMessage;
import org.netsharp.wx.mp.message.request.event.EventRequest;
import org.netsharp.wx.mp.message.response.TextResponse;
import org.netsharp.wx.pa.entity.Fans;
import org.netsharp.wx.pa.entity.PublicAccount;

public class SubscribeUnhint implements IWeixinSubscriber {

    public boolean validate(EventRequest request,Fans fans,PublicAccount publicAccount){
        return true;
    }

    public ResponseMessage reply(EventRequest request,Fans fans,PublicAccount publicAccount,int sceneId){
        TextResponse message = new TextResponse();
        message.setToUserName(request.getFromUserName());
        message.setFromUserName( request.getToUserName());

        String content = "亲，您终于来了，" + publicAccount.getName() + "欢迎你!";

        message.setContent(content);

        return message;
    }

}

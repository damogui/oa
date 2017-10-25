package org.netsharp.wx.mp.api.sendmessage;

import org.netsharp.wx.mp.message.WeixinValidation;
import org.netsharp.wx.mp.util.StringHelper;

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//[Api("发送消息到用户组.图片")]
public class ImageMessageToGroupRequest extends SendMessageToGroupRequest
{
	private String privateMediaId;
	public final String getMediaId()
	{
		return privateMediaId;
	}
	public final void setMediaId(String value)
	{
		privateMediaId = value;
	}

	@Override
	protected WeixinValidation doValidate()
	{
		if (StringHelper.isNullOrEmpty(this.getMediaId()))
		{
			return WeixinValidation.create(false, "MediaId is null");
		}

		return super.doValidate();
	}

	@Override
	protected Object GetMessageObject()
	{
		return null;
//		var jsonData = new { filter = new { group_id = this.getGroupId() }, image = new { media_id = this.getMediaId() }, msgtype = "image" };
//		
//		return jsonData;
	}
}
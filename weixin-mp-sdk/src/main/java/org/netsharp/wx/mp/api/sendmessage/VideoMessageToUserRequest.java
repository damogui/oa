package org.netsharp.wx.mp.api.sendmessage;

import java.util.HashMap;

import org.netsharp.wx.mp.api.uploadnews.UpLoadNewsResponse;
import org.netsharp.wx.mp.api.uploadvideo.UploadVideoRequest;
import org.netsharp.wx.mp.message.WeixinValidation;
import org.netsharp.wx.mp.util.StringHelper;

//[Api("发送消息到用户.视频")]
public class VideoMessageToUserRequest extends SendMessageToUserRequest
{
	/*这里的Media直接通过上传下载多媒体文件得到  不再需要通过上传视频接口得到*/
	private String mediaId;
	private String title;
	private String description;

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
	protected Object getMessageObject()
	{
		HashMap<String,Object> jsonData=new HashMap<String,Object>();
		
		HashMap<String,Object> mpvideo=new HashMap<String,Object>();
		mpvideo.put("media_id", this.GetMediaId());
		
		jsonData.put("touser", this.getOpenIds());
		jsonData.put("mpvideo", mpvideo);
		jsonData.put("msgtype", "mpvideo");

		return jsonData;
	}

	private String GetMediaId()
	{
		UploadVideoRequest uvr = new UploadVideoRequest();
		uvr.setMediaId(this.getMediaId());
		uvr.setTokenInfo(this.getTokenInfo());
		uvr.setTitle(this.getTitle());
		uvr.setDescription(this.getDescription());

		UpLoadNewsResponse rsp = uvr.getResponse();

		return rsp.getMedia_id();
	}
	
	public final String getMediaId()
	{
		return mediaId;
	}
	public final void setMediaId(String value)
	{
		mediaId = value;
	}
	public final String getTitle()
	{
		return title;
	}
	public final void setTitle(String value)
	{
		title = value;
	}
	public final String getDescription()
	{
		return description;
	}
	public final void setDescription(String value)
	{
		description = value;
	}
}
package org.netsharp.wx.mp.api.group;

import org.netsharp.core.NetsharpException;
import org.netsharp.wx.mp.api.Request;

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//[Api("查询所有分组")]
public class GroupQueryRequest extends Request<GroupQueryResponse>
{

	@Override
	public String getUrl()
	{
		return String.format("https://api.weixin.qq.com/cgi-bin/groups/get?access_token=%1$s", this.getAccessToken());
	}

	@Override
	protected GroupQueryResponse doResponse() throws NetsharpException
	{
		return this.executeHttpGet();
	}
}
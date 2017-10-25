package org.netsharp.wx.qy;

import java.util.HashMap;
import java.util.Map;

public class ResponseCodes {
	
	public static Map<String,String> codes = null;
	
	public static String get(String code){
		return codes.get(code);
	}
	
	static{

		codes = new HashMap<String,String>();
		
		codes.put("0","请求成功");
		codes.put("40001","获取access_token时Secret错误，或者access_token无效");
		codes.put("40002","不合法的凭证类型");
		codes.put("40003","不合法的UserID");
		codes.put("40004","不合法的媒体文件类型");
		codes.put("40005","不合法的文件类型");
		codes.put("40006","不合法的文件大小");
		codes.put("40007","不合法的媒体文件id");
		codes.put("40008","不合法的消息类型");
		codes.put("40013","不合法的corpid");
		codes.put("40014","不合法的access_token");
		codes.put("40015","不合法的菜单类型");
		codes.put("40016","不合法的按钮个数");
		codes.put("40017","不合法的按钮类型");
		codes.put("40018","不合法的按钮名字长度");
		codes.put("40019","不合法的按钮KEY长度");
		codes.put("40020","不合法的按钮URL长度");
		codes.put("40021","不合法的菜单版本号");
		codes.put("40022","不合法的子菜单级数");
		codes.put("40023","不合法的子菜单按钮个数");
		codes.put("40024","不合法的子菜单按钮类型");
		codes.put("40025","不合法的子菜单按钮名字长度");
		codes.put("40026","不合法的子菜单按钮KEY长度");
		codes.put("40027","不合法的子菜单按钮URL长度");
		codes.put("40028","不合法的自定义菜单使用成员");
		codes.put("40029","不合法的oauth_code");
		codes.put("40031","不合法的UserID列表");
		codes.put("40032","不合法的UserID列表长度");
		codes.put("40033","不合法的请求字符，不能包含\\uxxxx格式的字符");
		codes.put("40035","不合法的参数");
		codes.put("40038","不合法的请求格式");
		codes.put("40039","不合法的URL长度");
		codes.put("40040","不合法的插件token");
		codes.put("40041","不合法的插件id");
		codes.put("40042","不合法的插件会话");
		codes.put("40048","url中包含不合法domain");
		codes.put("40054","不合法的子菜单url域名");
		codes.put("40055","不合法的按钮url域名");
		codes.put("40056","不合法的agentid");
		codes.put("40057","不合法的callbackurl");
		codes.put("40058","不合法的红包参数");
		codes.put("40059","不合法的上报地理位置标志位");
		codes.put("40060","设置上报地理位置标志位时没有设置callbackurl");
		codes.put("40061","设置应用头像失败");
		codes.put("40062","不合法的应用模式");
		codes.put("40063","参数为空");
		codes.put("40064","管理组名字已存在");
		codes.put("40065","不合法的管理组名字长度");
		codes.put("40066","不合法的部门列表");
		codes.put("40067","标题长度不合法");
		codes.put("40068","不合法的标签ID");
		codes.put("40069","不合法的标签ID列表");
		codes.put("40070","列表中所有标签（成员）ID都不合法");
		codes.put("40071","不合法的标签名字，标签名字已经存在");
		codes.put("40072","不合法的标签名字长度");
		codes.put("40073","不合法的openid");
		codes.put("40074","news消息不支持指定为高保密消息");
		codes.put("40077","不合法的预授权码");
		codes.put("40078","不合法的临时授权码");
		codes.put("40079","不合法的授权信息");
		codes.put("40080","不合法的suitesecret");
		codes.put("40082","不合法的suitetoken");
		codes.put("40083","不合法的suiteid");
		codes.put("40084","不合法的永久授权码");
		codes.put("40085","不合法的suiteticket");
		codes.put("40086","不合法的第三方应用appid");
		codes.put("41001","缺少access_token参数");
		codes.put("41002","缺少corpid参数");
		codes.put("41003","缺少refresh_token参数");
		codes.put("41004","缺少secret参数");
		codes.put("41005","缺少多媒体文件数据");
		codes.put("41006","缺少media_id参数");
		codes.put("41007","缺少子菜单数据");
		codes.put("41008","缺少oauth code");
		codes.put("41009","缺少UserID");
		codes.put("41010","缺少url");
		codes.put("41011","缺少agentid");
		codes.put("41012","缺少应用头像mediaid");
		codes.put("41013","缺少应用名字");
		codes.put("41014","缺少应用描述");
		codes.put("41015","缺少Content");
		codes.put("41016","缺少标题");
		codes.put("41017","缺少标签ID");
		codes.put("41018","缺少标签名字");
		codes.put("41021","缺少suiteid");
		codes.put("41022","缺少suitetoken");
		codes.put("41023","缺少suiteticket");
		codes.put("41024","缺少suitesecret");
		codes.put("41025","缺少永久授权码");
		codes.put("42001","access_token超时");
		codes.put("42002","refresh_token超时");
		codes.put("42003","oauth_code超时");
		codes.put("42004","插件token超时");
		codes.put("42007","预授权码失效");
		codes.put("42008","临时授权码失效");
		codes.put("42009","suitetoken失效");
		codes.put("43001","需要GET请求");
		codes.put("43002","需要POST请求");
		codes.put("43003","需要HTTPS");
		codes.put("43004","需要成员已关注");
		codes.put("43005","需要好友关系");
		codes.put("43006","需要订阅");
		codes.put("43007","需要授权");
		codes.put("43008","需要支付授权");
		codes.put("43010","需要处于回调模式");
		codes.put("43011","需要企业授权");
		codes.put("43013","应用对成员不可见");
		codes.put("44001","多媒体文件为空");
		codes.put("44002","POST的数据包为空");
		codes.put("44003","图文消息内容为空");
		codes.put("44004","文本消息内容为空");
		codes.put("45001","多媒体文件大小超过限制");
		codes.put("45002","消息内容超过限制");
		codes.put("45003","标题字段超过限制");
		codes.put("45004","描述字段超过限制");
		codes.put("45005","链接字段超过限制");
		codes.put("45006","图片链接字段超过限制");
		codes.put("45007","语音播放时间超过限制");
		codes.put("45008","图文消息的文章数量不能超过10条");
		codes.put("45009","接口调用超过限制");
		codes.put("45010","创建菜单个数超过限制");
		codes.put("45015","回复时间超过限制");
		codes.put("45016","系统分组，不允许修改");
		codes.put("45017","分组名字过长");
		codes.put("45018","分组数量超过上限");
		codes.put("45024","账号数量超过上限");
		codes.put("45025","同一个成员每周只能邀请一次");
		codes.put("45026","触发删除用户数的保护");
		codes.put("45027","mpnews每天只能发送100次");
		codes.put("45028","素材数量超过上限");
		codes.put("45029","media_id对该应用不可见");
		codes.put("46001","不存在媒体数据");
		codes.put("46002","不存在的菜单版本");
		codes.put("46003","不存在的菜单数据");
		codes.put("46004","不存在的成员");
		codes.put("47001","解析JSON/XML内容错误");
		codes.put("48001","Api未授权");
		codes.put("48002","Api禁用");
		codes.put("48003","suitetoken无效");
		codes.put("48004","授权关系无效");
		codes.put("50001","redirect_uri未授权");
		codes.put("50002","成员不在权限范围");
		codes.put("50003","应用已停用");
		codes.put("50004","成员状态不正确，需要成员为企业验证中状态");
		codes.put("50005","企业已禁用");
		codes.put("60001","部门长度不符合限制");
		codes.put("60002","部门层级深度超过限制");
		codes.put("60003","部门不存在");
		codes.put("60004","父亲部门不存在");
		codes.put("60005","不允许删除有成员的部门");
		codes.put("60006","不允许删除有子部门的部门");
		codes.put("60007","不允许删除根部门");
		codes.put("60008","部门名称已存在");
		codes.put("60009","部门名称含有非法字符");
		codes.put("60010","部门存在循环关系");
		codes.put("60011","管理组权限不足，（user/department/agent）无权限");
		codes.put("60012","不允许删除默认应用");
		codes.put("60013","不允许关闭应用");
		codes.put("60014","不允许开启应用");
		codes.put("60015","不允许修改默认应用可见范围");
		codes.put("60016","不允许删除存在成员的标签");
		codes.put("60017","不允许设置企业");
		codes.put("60019","不允许设置应用地理位置上报开关");
		codes.put("60020","访问ip不在白名单之中");
		codes.put("60023","应用已授权予第三方，不允许通过分级管理员修改菜单");
		codes.put("60102","UserID已存在");
		codes.put("60103","手机号码不合法");
		codes.put("60104","手机号码已存在");
		codes.put("60105","邮箱不合法");
		codes.put("60106","邮箱已存在");
		codes.put("60107","微信号不合法");
		codes.put("60108","微信号已存在");
		codes.put("60109","QQ号已存在");
		codes.put("60110","用户同时归属部门超过20个");
		codes.put("60111","UserID不存在");
		codes.put("60112","成员姓名不合法");
		codes.put("60113","身份认证信息（微信号/手机/邮箱）不能同时为空");
		codes.put("60114","性别不合法");
		codes.put("60115","已关注成员微信不能修改");
		codes.put("60116","扩展属性已存在");
		codes.put("60118","成员无有效邀请字段，详情参考(邀请成员关注)的接口说明");
		codes.put("60119","成员已关注");
		codes.put("60120","成员已禁用");
		codes.put("60121","找不到该成员");
		codes.put("60122","邮箱已被外部管理员使用");
		codes.put("60123","无效的部门id");
		codes.put("60124","无效的父部门id");
		codes.put("60125","非法部门名字，长度超过限制、重名等");
		codes.put("60126","创建部门失败");
		codes.put("60127","缺少部门id");
		codes.put("60128","字段不合法，可能存在主键冲突或者格式错误");
		codes.put("80001","可信域名没有IPC备案，后续将不能在该域名下正常使用jssdk");
		codes.put("82001","发送消息或者邀请的参数全部为空或者全部不合法");
		codes.put("82002","不合法的PartyID列表长度");
		codes.put("82003","不合法的TagID列表长度");
		codes.put("82004","微信版本号过低");
		codes.put("85002","包含不合法的词语");
		codes.put("86001","不合法的会话ID");
		codes.put("86003","不存在的会话ID");
		codes.put("86004","不合法的会话名");
		codes.put("86005","不合法的会话管理员");
		codes.put("86006","不合法的成员列表大小");
		codes.put("86007","不存在的成员");
		codes.put("86101","需要会话管理员权限");
		codes.put("86201","缺少会话ID");
		codes.put("86202","缺少会话名");
		codes.put("86203","缺少会话管理员");
		codes.put("86204","缺少成员");
		codes.put("86205","非法的会话ID长度");
		codes.put("86206","非法的会话ID数值");
		codes.put("86207","会话管理员不在用户列表中");
		codes.put("86208","消息服务未开启");
		codes.put("86209","缺少操作者");
		codes.put("86210","缺少会话参数");
		codes.put("86211","缺少会话类型（单聊或者群聊）");
		codes.put("86213","缺少发件人");
		codes.put("86214","非法的会话类型");
		codes.put("86215","会话已存在");
		codes.put("86216","非法会话成员");
		codes.put("86217","会话操作者不在成员列表中");
		codes.put("86218","非法会话发件人");
		codes.put("86219","非法会话收件人");
		codes.put("86220","非法会话操作者");
		codes.put("86221","单聊模式下，发件人与收件人不能为同一人");
		codes.put("86222","不允许消息服务访问的API");
		
	}
}

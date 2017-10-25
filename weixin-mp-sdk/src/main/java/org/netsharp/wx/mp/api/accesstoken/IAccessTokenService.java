package org.netsharp.wx.mp.api.accesstoken;

public interface IAccessTokenService {
	
	AccessToken getTokenByAppId(String appId) ;
	void clearTokenCache(String appId);
	AccessToken refreshToken(String appId);
	PAccount getPAccount(String originalId);
}

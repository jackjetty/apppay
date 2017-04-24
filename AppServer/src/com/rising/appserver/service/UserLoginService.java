package com.rising.appserver.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rising.appserver.common.BaseService;

@Service("userLoginService")
public class UserLoginService extends BaseService {

	private String userLoginURL;

	public String getUserLoginURL() {
		return userLoginURL;
	}

	@Value("#{propertiesReader[userLoginURL]}")
	public void setUserLoginURL(String userLoginURL) {
		this.userLoginURL = userLoginURL;
	}

	public String userLogin(String parameter, HttpServletRequest request) {
		String responseGson = redirectToMoblilePayMentService(userLoginURL,
				parameter,request);
		return responseGson;
	}

}

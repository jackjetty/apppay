package com.rising.appserver.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rising.appserver.common.BaseService;

@Service("userRegisterService")
public class UserRegisterService extends BaseService {

	private String userRegisterURL;

	public String getUserRegisterURL() {
		return userRegisterURL;
	}

	@Value("#{propertiesReader[userRegisterURL]}")
	public void setUserRegisterURL(String userRegisterURL) {
		this.userRegisterURL = userRegisterURL;
	}

	public String userRegister(String parameter, HttpServletRequest request) {
		String responseGson = redirectToMoblilePayMentService(userRegisterURL,
				parameter,request);
		return responseGson;
	}

}

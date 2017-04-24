package com.rising.appserver.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rising.appserver.common.BaseService;

@Service("userChangePasswordService")
public class UserChangePasswordService extends BaseService {

	private String userChangePasswordURL;

	public String getUserChangePasswordURL() {
		return userChangePasswordURL;
	}
	@Value("#{propertiesReader[userChangePasswordURL]}")
	public void setUserChangePasswordURL(String userChangePasswordURL) {
		this.userChangePasswordURL = userChangePasswordURL;
	}

	public String userChangePassword(String parameter, HttpServletRequest request) {
		String responseGson = redirectToMoblilePayMentService(userChangePasswordURL,
				parameter,request);
		return responseGson;
	}

}

package com.rising.appserver.service;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rising.appserver.common.BaseService;

@Service("userRebateService")
public class UserRebateService extends BaseService {

	private String userRebateURL;

	public String getUserRebateURL() {
		return userRebateURL;
	}

	@Value("#{propertiesReader[userRebateURL]}")
	public void setUserRebateURL(String userRebateURL) {
		this.userRebateURL = userRebateURL;
	}

	public String userRegister(String parameter, HttpServletRequest request) {
		String responseGson = redirectToMoblilePayMentService(userRebateURL,
				parameter,request);
		return responseGson;
	}

}

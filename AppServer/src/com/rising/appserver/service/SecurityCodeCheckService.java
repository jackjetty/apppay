package com.rising.appserver.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rising.appserver.common.BaseService;
@Service("securityCodeCheckService")
public class SecurityCodeCheckService extends BaseService {

	private String securityCodeCheckURL;

	public String getSecurityCodeCheckURL() {
		return securityCodeCheckURL;
	}
	@Value("#{propertiesReader[securityCodeCheckURL]}")
	public void setSecurityCodeCheckURL(String securityCodeCheckURL) {
		this.securityCodeCheckURL = securityCodeCheckURL;
	}

	public String checkCode(String parameter, HttpServletRequest request) {
		String responseGson = redirectToMoblilePayMentService(
				securityCodeCheckURL, parameter,request);
		return responseGson;
	}

}

package com.rising.general.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rising.general.common.BaseService;

@Service("gUserService")
public class UserService extends BaseService {

	private String registerURL;

	public String getRegisterURL() {
		return registerURL;
	}

	@Value("#{propertiesReader[registerURL]}")
	public void setRegisterURL(String registerURL) {
		this.registerURL = registerURL;
	}

	public String register(String xMLString,String ipAddress) {
		String resultGson = redirectToMoblilePayMentService(registerURL,
				xMLString,ipAddress);
		return resultGson;
	}

}

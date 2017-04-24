package com.rising.appserver.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rising.appserver.common.BaseService;

@Service("getAndroidHomePageService")
public class GetAndroidHomePageService extends BaseService {
	private String getAndroidHomePageURL;

	public String getGetAndroidHomePageURL() {
		return getAndroidHomePageURL;
	}

	@Value("#{propertiesReader[getAndroidHomePageURL]}")
	public void setGetAndroidHomePageURL(String getAndroidHomePageURL) {
		this.getAndroidHomePageURL = getAndroidHomePageURL;
	}

	public String getAndroidHomePage(String parameter, HttpServletRequest request) {
		String responseGson = redirectToMoblilePayMentService(
				getAndroidHomePageURL, parameter,request);
		return responseGson;
	}

}

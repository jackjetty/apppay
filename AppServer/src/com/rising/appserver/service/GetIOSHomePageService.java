package com.rising.appserver.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rising.appserver.common.BaseService;


@Service
public class GetIOSHomePageService extends BaseService {

	private String getIOSHomePageURL;

	public String getGetIOSHomePageURL() {
		return getIOSHomePageURL;
	}

	@Value("#{propertiesReader[getIOSHomePageURL]}")
	public void setGetIOSHomePageURL(String getIOSHomePageURL) {
		this.getIOSHomePageURL = getIOSHomePageURL;
	}

	public String getIOSHomePage(String parameter, HttpServletRequest request) {
		String responseGson = redirectToMoblilePayMentService(
				getIOSHomePageURL, parameter,request);
		return responseGson;
	}

}

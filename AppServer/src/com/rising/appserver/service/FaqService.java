package com.rising.appserver.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rising.appserver.common.BaseService;

@Service("faqService")
public class FaqService extends BaseService {
	private String getFaqURL;

	public String getGetFaqURL() {
		return getFaqURL;
	}
	
	@Value("#{propertiesReader[getFaqURL]}")
	public void setGetFaqURL(String getFaqURL) {
		this.getFaqURL = getFaqURL;
	}

	public String getFaq(String parameter, HttpServletRequest request) {
		String responseGson = redirectToMoblilePayMentService(
				getFaqURL, parameter,request);
		return responseGson;
	}
}

package com.rising.appserver.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rising.appserver.common.BaseService;

@Service("publicInfoService")
public class PublicInfoService extends BaseService {

	private String getPublicInfoURL;

	public String getGetPublicInfoURL() {
		return getPublicInfoURL;
	}

	@Value("#{propertiesReader[getPublicInfoURL]}")
	public void setGetPublicInfoURL(String getPublicInfoURL) {
		this.getPublicInfoURL = getPublicInfoURL;
	}

	private String getMessageURL;

	public String getGetMessageURL() {
		return getMessageURL;
	}

	@Value("#{propertiesReader[getMessageURL]}")
	public void setGetMessageURL(String getMessageURL) {
		this.getMessageURL = getMessageURL;
	}

	private String setPublicInfoReadedURL;

	public String getSetPublicInfoReadedURL() {
		return setPublicInfoReadedURL;
	}

	@Value("#{propertiesReader[setPublicInfoReadedURL]}")
	public void setSetPublicInfoReadedURL(String setPublicInfoReadedURL) {
		this.setPublicInfoReadedURL = setPublicInfoReadedURL;
	}
	
	private String setAnswerFeedBackReadedURL;
	
	

	public String getSetAnswerFeedBackReadedURL() {
		return setAnswerFeedBackReadedURL;
	}

	@Value("#{propertiesReader[setAnswerFeedBackReadedURL]}")
	public void setSetAnswerFeedBackReadedURL(String setAnswerFeedBackReadedURL) {
		this.setAnswerFeedBackReadedURL = setAnswerFeedBackReadedURL;
	}

	private String getMoreMessageURL;
	
	
	public String getGetMoreMessageURL() {
		return getMoreMessageURL;
	}
	
	@Value("#{propertiesReader[getMoreMessageURL]}")
	public void setGetMoreMessageURL(String getMoreMessageURL) {
		this.getMoreMessageURL = getMoreMessageURL;
	}

	public String getPublicInfo(String parameter, HttpServletRequest request) {
		String responseGson = redirectToMoblilePayMentService(getPublicInfoURL,
				parameter,request);
		return responseGson;
	}

	public String getMessage(String parameter,HttpServletRequest request) {
		String responseGson = redirectToMoblilePayMentService(getMessageURL,
				parameter,request);
		return responseGson;
	}

//	public String setPublicInfoReaded(String parameter) {
//		String responseGson = redirectToMoblilePayMentService(
//				setPublicInfoReadedURL, parameter,request);
//		return responseGson;
//	}
//
//	public String setAnswerFeedBackReaded(String parameter) {
//		String responseGson = redirectToMoblilePayMentService(
//				setAnswerFeedBackReadedURL, parameter,request);
//		return responseGson;
//	}
//
//	public String getMoreMessage(String parameter) {
//		String responseGson = redirectToMoblilePayMentService(
//				getMoreMessageURL, parameter,request);
//		return responseGson;
//	}

}

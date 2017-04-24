package com.rising.appserver.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rising.appserver.common.BaseService;

@Service("feedBackService")
public class FeedBackService extends BaseService {

	public String feedBackURL;

	public String getFeedBackURL() {
		return feedBackURL;
	}

	@Value("#{propertiesReader[feedBackURL]}")
	public void setFeedBackURL(String feedBackURL) {
		this.feedBackURL = feedBackURL;
	}

	public String addRecord(String parameter, HttpServletRequest request) {
		String responseGson = redirectToMoblilePayMentService(
				feedBackURL, parameter,request);
		return responseGson;
	}

}

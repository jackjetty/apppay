package com.rising.appserver.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rising.appserver.common.BaseService;

@Service("checkService")
public class CheckService extends BaseService {

	private String checkInURL;

	public String getCheckInURL() {
		return checkInURL;
	}

	@Value("#{propertiesReader[checkInURL]}")
	public void setCheckInURL(String checkInURL) {
		this.checkInURL = checkInURL;
	}

	private String getLeftMoneyURL;

	public String getGetLeftMoneyURL() {
		return getLeftMoneyURL;
	}

	@Value("#{propertiesReader[getLeftMoneyURL]}")
	public void setGetLeftMoneyURL(String getLeftMoneyURL) {
		this.getLeftMoneyURL = getLeftMoneyURL;
	}

	private String checkRecommendURL;

	public String getCheckRecommendURL() {
		return checkRecommendURL;
	}

	@Value("#{propertiesReader[checkRecommendURL]}")
	public void setCheckRecommendURL(String checkRecommendURL) {
		this.checkRecommendURL = checkRecommendURL;
	}

	private String recommendURL;

	public String getRecommendURL() {
		return recommendURL;
	}
	@Value("#{propertiesReader[recommendURL]}")
	public void setRecommendURL(String recommendURL) {
		this.recommendURL = recommendURL;
	}

	public String checkIn(String parameter, HttpServletRequest request) {
		String responseGson = redirectToMoblilePayMentService(checkInURL,
				parameter, request);
		return responseGson;
	}

	public String getLeftMoney(String parameter, HttpServletRequest request) {
		String responseGson = redirectToMoblilePayMentService(getLeftMoneyURL,
				parameter, request);
		return responseGson;
	}

	public String checkRecommend(String parameter, HttpServletRequest request) {
		String responseGson = redirectToMoblilePayMentService(
				checkRecommendURL, parameter, request);
		return responseGson;
	}

	public String recommend(String parameter, HttpServletRequest request) {
		String responseGson = redirectToMoblilePayMentService(
				recommendURL, parameter, request);
		return responseGson;
	}

}

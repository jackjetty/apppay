package com.rising.appserver.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rising.appserver.common.BaseService;

@Service("getSalesInformationService")
public class GetSalesInformationService extends BaseService {

	private String getSalesInfoDetailURL;

	public String getGetSalesInfoDetailURL() {
		return getSalesInfoDetailURL;
	}

	@Value("#{propertiesReader[getSalesInfoDetailURL]}")
	public void setGetSalesInfoDetailURL(String getSalesInfoDetailURL) {
		this.getSalesInfoDetailURL = getSalesInfoDetailURL;
	}

	public String getSalesInfoDetail(String parameter, HttpServletRequest request) {
		String responseGson = redirectToMoblilePayMentService(
				getSalesInfoDetailURL, parameter,request);
		return responseGson;
	}

}

package com.rising.appserver.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rising.appserver.common.BaseService;

@Service("getBusinessDetailService")
public class GetBusinessDetailService extends BaseService {
	
	private String getBusinessDetailURL;

	public String getGetBusinessDetailURL() {
		return getBusinessDetailURL;
	}

	@Value("#{propertiesReader[getBusinessDetailURL]}")
	public void setGetBusinessDetailURL(String getBusinessDetailURL) {
		this.getBusinessDetailURL = getBusinessDetailURL;
	}

	public String getBusinessDetail(String parameter, HttpServletRequest request) {
		String responseGson = redirectToMoblilePayMentService(
				getBusinessDetailURL, parameter,request);
		return responseGson;
	}

}

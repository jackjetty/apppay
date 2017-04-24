package com.rising.appserver.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rising.appserver.common.BaseService;
@Service("queryTradeSimpleService")
public class QueryTradeSimpleService extends BaseService {
	private String queryTradeSimpleURL;

	public String getQueryTradeSimpleURL() {
		return queryTradeSimpleURL;
	}

	@Value("#{propertiesReader[queryTradeSimpleURL]}")
	public void setQueryTradeSimpleURL(String queryTradeSimpleURL) {
		this.queryTradeSimpleURL = queryTradeSimpleURL;
	}

	public String queryTradeSimple(String parameter, HttpServletRequest request) {
		String responseGson = redirectToMoblilePayMentService(
				queryTradeSimpleURL, parameter,request);
		return responseGson;
	}

}

package com.rising.appserver.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rising.appserver.common.BaseService;
@Service("queryTradeDetailService")
public class QueryTradeDetailService extends BaseService {
	private String queryTradeDetailURL;

	public String getQueryTradeDetailURL() {
		return queryTradeDetailURL;
	}

	@Value("#{propertiesReader[queryTradeDetailURL]}")
	public void setQueryTradeDetailURL(String queryTradeDetailURL) {
		this.queryTradeDetailURL = queryTradeDetailURL;
	}

	public String queryTradeDetail(String parameter,HttpServletRequest request) {
		String responseGson = redirectToMoblilePayMentService(
				queryTradeDetailURL, parameter,request);
		return responseGson;
	}

}

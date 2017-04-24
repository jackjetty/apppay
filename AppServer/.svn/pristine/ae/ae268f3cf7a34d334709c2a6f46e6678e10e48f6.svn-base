package com.rising.appserver.service;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rising.appserver.common.BaseService;

@Service("flowService")
public class FlowService extends BaseService {

	private String queryFlowUrl;
	private String recommendFlowUrl;
	private String applyFlowUrl;
	private String handleFlowUrl;
	private String orderQueryFlowUrl;
	
    
	 
	@Value("#{propertiesReader[queryFlowUrl]}")
	public void setQueryFlowUrl(String queryFlowUrl) {
		this.queryFlowUrl = queryFlowUrl;
	}
	@Value("#{propertiesReader[recommendFlowUrl]}") 
	public void setRecommendFlowUrl(String recommendFlowUrl) {
		this.recommendFlowUrl = recommendFlowUrl;
	}
	@Value("#{propertiesReader[applyFlowUrl]}")
	public void setApplyFlowUrl(String applyFlowUrl) {
		this.applyFlowUrl = applyFlowUrl;
	}
	@Value("#{propertiesReader[handleFlowUrl]}")
	public void setHandleFlowUrl(String handleFlowUrl) {
		this.handleFlowUrl = handleFlowUrl;
	}
	@Value("#{propertiesReader[orderQueryFlowUrl]}")
	public void setOrderQueryFlowUrl(String orderQueryFlowUrl) {
		this.orderQueryFlowUrl = orderQueryFlowUrl;
	} 

	public String query(String parameter, HttpServletRequest request) {
		String responseGson = redirectToMoblilePayMentService(
				this.queryFlowUrl, parameter,request);
		return responseGson;
	}
	public String recommend(String parameter, HttpServletRequest request) {
		String responseGson = redirectToMoblilePayMentService(
				this.recommendFlowUrl, parameter,request);
		return responseGson;
	}
	public String apply(String parameter, HttpServletRequest request) {
		String responseGson = redirectToMoblilePayMentService(
				this.applyFlowUrl, parameter,request);
		return responseGson;
	}
	public String handle(String parameter, HttpServletRequest request) {
		String responseGson = redirectToMoblilePayMentService(
				this.handleFlowUrl, parameter,request);
		return responseGson;
	}
	public String orderQuery(String parameter, HttpServletRequest request) {
		String responseGson = redirectToMoblilePayMentService(
				this.orderQueryFlowUrl, parameter,request);
		return responseGson;
	}
}
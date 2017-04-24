package com.rising.appserver.service;
 
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rising.appserver.common.BaseService;

@Service("qqAgentService")
public class QQAgentService extends BaseService {

	private String qqServiceConfigUrl;
    private String qqPayNoticeUrl;
    private String qqUnsubmitRefundUrl;
    private String qqRefundNoticeUrl;
    private String qqGameConfigUrl;
    
    
    
    
	 

	public String getQqGameConfigUrl() {
		return qqGameConfigUrl;
	}
	@Value("#{propertiesReader[qqGameConfigUrl]}")
	public void setQqGameConfigUrl(String qqGameConfigUrl) {
		this.qqGameConfigUrl = qqGameConfigUrl;
	}
	
	public String getQqRefundNoticeUrl() {
		return qqRefundNoticeUrl;
	}
	@Value("#{propertiesReader[qqRefundNoticeUrl]}")
	public void setQqRefundNoticeUrl(String qqRefundNoticeUrl) {
		this.qqRefundNoticeUrl = qqRefundNoticeUrl;
	}
	
	
	public String getQqUnsubmitRefundUrl() {
		return qqUnsubmitRefundUrl;
	}
	@Value("#{propertiesReader[qqUnsubmitRefundUrl]}")
	public void setQqUnsubmitRefundUrl(String qqUnsubmitRefundUrl) {
		this.qqUnsubmitRefundUrl = qqUnsubmitRefundUrl;
	}

	public String getQqPayNoticeUrl() {
		return qqPayNoticeUrl;
	}
	
	@Value("#{propertiesReader[qqPayNoticeUrl]}")
	public void setQqPayNoticeUrl(String qqPayNoticeUrl) {
		this.qqPayNoticeUrl = qqPayNoticeUrl;
	}

	public String getQqServiceConfigUrl() {
		return qqServiceConfigUrl;
	}

	@Value("#{propertiesReader[qqServiceConfigUrl]}")
	public void setQqServiceConfigUrl(String qqServiceConfigUrl) {
		this.qqServiceConfigUrl = qqServiceConfigUrl;
	}

	public String qqServiceConfig(String parameter, HttpServletRequest request) {
		String responseGson = redirectToMoblilePayMentService(qqServiceConfigUrl,
				parameter,request);
		return responseGson;
	}
	public String qqGameConfig(String parameter, HttpServletRequest request) {
		String responseGson = redirectToMoblilePayMentService(qqGameConfigUrl,
				parameter,request);
		return responseGson;
	}
	
	
	
	public String qqPayNotice(String parameter, HttpServletRequest request){ 
		String responseGson = redirectToMoblilePayMentService(qqPayNoticeUrl,
				parameter,request); 
		return responseGson;
	}
	
	
	public String qqUnsubmitRefund(String parameter, HttpServletRequest request){
		String responseGson = redirectToMoblilePayMentService(qqUnsubmitRefundUrl,
				parameter,request);
		return responseGson;
	}
	public String qqRefundNotice(String parameter, HttpServletRequest request){
		String responseGson = redirectToMoblilePayMentService(qqRefundNoticeUrl,
				parameter,request);
		return responseGson;
	}

}

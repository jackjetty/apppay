package com.rising.appserver.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rising.appserver.common.BaseService;

@Service("chargeService")
public class ChargeService extends BaseService {

	private String takeChargeURL;

	private String takeQQPerMonthChargeURL;

	private String takeQQVIPChargeURL;

	public String getTakeChargeURL() {
		return takeChargeURL;
	}

	@Value("#{propertiesReader[takeChargeURL]}")
	public void setTakeChargeURL(String takeChargeURL) {
		this.takeChargeURL = takeChargeURL;
	}

	public String getTakeQQPerMonthChargeURL() {
		return takeQQPerMonthChargeURL;
	}
	
	@Value("#{propertiesReader[takeQQPerMonthChargeURL]}")
	public void setTakeQQPerMonthChargeURL(String takeQQPerMonthChargeURL) {
		this.takeQQPerMonthChargeURL = takeQQPerMonthChargeURL;
	}

	public String getTakeQQVIPChargeURL() {
		return takeQQVIPChargeURL;
	}

	@Value("#{propertiesReader[takeQQVIPChargeURL]}")
	public void setTakeQQVIPChargeURL(String takeQQVIPChargeURL) {
		this.takeQQVIPChargeURL = takeQQVIPChargeURL;
	}

	public String takeCharge(String parameter, HttpServletRequest request) {
		String responseGson = redirectToMoblilePayMentService(takeChargeURL,
				parameter,request);
		return responseGson;
	}

	public String takeQQPerMonthCharge(String parameter, HttpServletRequest request) {
		String responseGson = redirectToMoblilePayMentService(
				takeQQPerMonthChargeURL, parameter,request);
		return responseGson;
	}

	public String takeQQVIPCharge(String parameter, HttpServletRequest request) {
		String responseGson = redirectToMoblilePayMentService(takeQQVIPChargeURL,
				parameter,request);
		return responseGson;
	}

}

package com.rising.appserver.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rising.appserver.common.BaseService;

@Service("takeOrderService")
public class TakeOrderService extends BaseService {

	private String takeOrderURL;

	private String takeQQPerMonthOrderURL;

	private String takeQQVIPOrderURL;

	private String cancelQQPerMonthOrderURL;

	private String checkQQPerMonthOrderURL;

	private String checkQQPerMonthOrderWithSecurityCodeURL;

	public String getTakeOrderURL() {
		return takeOrderURL;
	}

	@Value("#{propertiesReader[takeOrderURL]}")
	public void setTakeOrderURL(String takeOrderURL) {
		this.takeOrderURL = takeOrderURL;
	}

	public String getTakeQQPerMonthOrderURL() {
		return takeQQPerMonthOrderURL;
	}

	@Value("#{propertiesReader[takeQQPerMonthOrderURL]}")
	public void setTakeQQPerMonthOrderURL(String takeQQPerMonthOrderURL) {
		this.takeQQPerMonthOrderURL = takeQQPerMonthOrderURL;
	}

	public String getTakeQQVIPOrderURL() {
		return takeQQVIPOrderURL;
	}

	@Value("#{propertiesReader[takeQQVIPOrderURL]}")
	public void setTakeQQVIPOrderURL(String takeQQVIPOrderURL) {
		this.takeQQVIPOrderURL = takeQQVIPOrderURL;
	}

	public String getCancelQQPerMonthOrderURL() {
		return cancelQQPerMonthOrderURL;
	}

	@Value("#{propertiesReader[cancelQQPerMonthOrderURL]}")
	public void setCancelQQPerMonthOrderURL(String cancelQQPerMonthOrderURL) {
		this.cancelQQPerMonthOrderURL = cancelQQPerMonthOrderURL;
	}

	public String getCheckQQPerMonthOrderURL() {
		return checkQQPerMonthOrderURL;
	}

	@Value("#{propertiesReader[checkQQPerMonthOrderURL]}")
	public void setCheckQQPerMonthOrderURL(String checkQQPerMonthOrderURL) {
		this.checkQQPerMonthOrderURL = checkQQPerMonthOrderURL;
	}

	public String getCheckQQPerMonthOrderWithSecurityCodeURL() {
		return checkQQPerMonthOrderWithSecurityCodeURL;
	}

	@Value("#{propertiesReader[checkQQPerMonthOrderWithSecurityCodeURL]}")
	public void setCheckQQPerMonthOrderWithSecurityCodeURL(
			String checkQQPerMonthOrderWithSecurityCodeURL) {
		this.checkQQPerMonthOrderWithSecurityCodeURL = checkQQPerMonthOrderWithSecurityCodeURL;
	}

	public String takeOrder(String parameter, HttpServletRequest request) {
		String responseGson = redirectToMoblilePayMentService(takeOrderURL,
				parameter,request);
		return responseGson;
	}

	public String takeQQPerMonthOrder(String parameter, HttpServletRequest request) {
		String responseGson = redirectToMoblilePayMentService(
				takeQQPerMonthOrderURL, parameter,request);
		return responseGson;
	}

	public String takeQQVIPOrder(String parameter,HttpServletRequest request) {
		String responseGson = redirectToMoblilePayMentService(
				takeQQVIPOrderURL, parameter,request);
		return responseGson;
	}

	public String cancelQQPerMonthOrder(String parameter, HttpServletRequest request) {
		String responseGson = redirectToMoblilePayMentService(
				cancelQQPerMonthOrderURL, parameter,request);
		return responseGson;
	}

	public String checkQQPerMonthOrder(String parameter, HttpServletRequest request) {
		String responseGson = redirectToMoblilePayMentService(
				checkQQPerMonthOrderURL, parameter,request);
		return responseGson;
	}

	public String checkQQPerMonthOrderWithSecurityCode(String parameter, HttpServletRequest request) {
		String responseGson = redirectToMoblilePayMentService(
				checkQQPerMonthOrderWithSecurityCodeURL, parameter,request);
		return responseGson;
	}

}

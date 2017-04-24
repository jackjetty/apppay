package com.rising.appserver.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rising.appserver.common.BaseService;
@Service("sendShortMessageService")
public class SendShortMessageService extends BaseService {

	private String sendShortMessageURL;

	public String getSendShortMessageURL() {
		return sendShortMessageURL;
	}

	@Value("#{propertiesReader[sendShortMessageURL]}")
	public void setSendShortMessageURL(String sendShortMessageURL) {
		this.sendShortMessageURL = sendShortMessageURL;
	}

	public String sendShortMessage(String parameter, HttpServletRequest request) {
		String responseGson = redirectToMoblilePayMentService(sendShortMessageURL,
				parameter,request);
		return responseGson;
	}

}

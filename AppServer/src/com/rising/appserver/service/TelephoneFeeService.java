package com.rising.appserver.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rising.appserver.common.BaseService;

@Service("telephoneFeeService")
public class TelephoneFeeService extends BaseService {

	private String recordTelephoneFeeUrl;

	public String getRecordTelephoneFeeUrl() {
		return recordTelephoneFeeUrl;
	}

	@Value("#{propertiesReader[recordTelephoneFeeUrl]}")
	public void setRecordTelephoneFeeUrl(String recordTelephoneFeeUrl) {
		this.recordTelephoneFeeUrl = recordTelephoneFeeUrl;
	}

	public String addRecord(String parameter,HttpServletRequest request) {
		String responseGson = redirectToMoblilePayMentService(
				recordTelephoneFeeUrl, parameter,request);
		return responseGson;
	}

}

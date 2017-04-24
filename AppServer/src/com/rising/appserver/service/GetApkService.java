package com.rising.appserver.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rising.appserver.common.BaseService;

@Service("getApkService")
public class GetApkService extends BaseService {

	private String getApkURL;

	public String getGetApkURL() {
		return getApkURL;
	}

	@Value("#{propertiesReader[getApkURL]}")
	public void setGetApkURL(String getApkURL) {
		this.getApkURL = getApkURL;
	}

	public void writeDownload(String parameter, HttpServletRequest request) {
		redirectToMoblilePayMentService(getApkURL, parameter,request);

	}

}

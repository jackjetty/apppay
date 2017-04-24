package com.rising.appserver.service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rising.appserver.common.BaseService;


@Service("startImageService")
public class StartImageService extends BaseService {

	private String getStartImageURL;

	public String getGetStartImageURL() {
		return getStartImageURL;
	}

	@Value("#{propertiesReader[getStartImageURL]}")
	public void setGetStartImageURL(String getStartImageURL) {
		this.getStartImageURL = getStartImageURL;
	}

	public byte[] getImage(String parameter, HttpServletRequest request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		byte[] image = redirectToMoblilePayMentService3(getStartImageURL,
				parameter, map);
		return image;
	}

}

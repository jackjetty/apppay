package com.rising.appserver.service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rising.appserver.common.BaseService;

@Service("hotGameService")
public class HotGameService extends BaseService {

	private String getHotGameInfoURL;

	private String getHotGameImageURL;

	public String getGetHotGameInfoURL() {
		return getHotGameInfoURL;
	}
	
	@Value("#{propertiesReader[getHotGameInfoURL]}")
	public void setGetHotGameInfoURL(String getHotGameInfoURL) {
		this.getHotGameInfoURL = getHotGameInfoURL;
	}

	public String getGetHotGameImageURL() {
		return getHotGameImageURL;
	}
	
	@Value("#{propertiesReader[getHotGameImageURL]}")
	public void setGetHotGameImageURL(String getHotGameImageURL) {
		this.getHotGameImageURL = getHotGameImageURL;
	}

	public byte[] getImage(String parameter) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		byte[] image = redirectToMoblilePayMentService3(getHotGameImageURL,
				parameter, map);
		return image;
	}

	public String getInfo(String parameter,HttpServletRequest request) {
		String responseGson = redirectToMoblilePayMentService(
				getHotGameInfoURL, parameter,request);
		return responseGson;
	}

}

package com.rising.appserver.service;

//import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rising.appserver.common.BaseService;


@Service("getImageService")
public class GetImageService extends BaseService {

	
	
	private String getImageURL;

	public String getGetImageURL() {
		return getImageURL;
	}
	@Value("#{propertiesReader[getImageURL]}")
	public void setGetImageURL(String getImageURL) {
		this.getImageURL = getImageURL;
	}

//	public BufferedImage getImage(String parameter) {
//		HashMap<String,Object> map = new HashMap<String,Object>();
//		BufferedImage image = redirectToMoblilePayMentService2(getImageURL, parameter,map);
//		return image;
//	}
	public byte [] getImage(String parameter, HttpServletRequest request) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		byte[] image = redirectToMoblilePayMentService3(getImageURL, parameter,map);
		return image;
	}

	
	
}

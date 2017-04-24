package com.rising.general.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rising.general.common.BaseService;

@Service("gProductService")
public class ProductService extends BaseService {

	private String getProductInfoURL;

	public String getGetProductInfoURL() {
		return getProductInfoURL;
	}

	@Value("#{propertiesReader[getProductInfoURL]}")
	public void setGetProductInfoURL(String getProductInfoURL) {
		this.getProductInfoURL = getProductInfoURL;
	}

	public String getInfo(String xMLString,String ipAddress) {
		String resultGson = redirectToMoblilePayMentService(getProductInfoURL,
				xMLString,ipAddress);
		return resultGson;
	}

}

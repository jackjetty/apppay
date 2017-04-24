package com.rising.general.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rising.general.common.BaseService;

@Service("gOrderService")
public class OrderService extends BaseService {

	private String orderApplyURL;

	public String getOrderApplyURL() {
		return orderApplyURL;
	}

	@Value("#{propertiesReader[orderApplyURL]}")
	public void setOrderApplyURL(String orderApplyURL) {
		this.orderApplyURL = orderApplyURL;
	}

	public String apply(String xMLString,String ipAddress) {
		String resultGson = redirectToMoblilePayMentService(orderApplyURL,
				xMLString,ipAddress);
		return resultGson;
	}

}

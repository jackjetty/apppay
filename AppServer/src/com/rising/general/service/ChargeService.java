package com.rising.general.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rising.general.common.BaseService;

@Service("gChargeService")
public class ChargeService extends BaseService {

	private String chargeApplyURL;

	public String getChargeApplyURL() {
		return chargeApplyURL;
	}

	@Value("#{propertiesReader[chargeApplyURL]}")
	public void setChargeApplyURL(String chargeApplyURL) {
		this.chargeApplyURL = chargeApplyURL;
	}

	public String Apply(String xMLString,String ipAddress) {
		String resultGson = redirectToMoblilePayMentService(chargeApplyURL,
				xMLString,ipAddress);
		return resultGson;
	}

}

package com.rising.appserver.service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rising.appserver.common.BaseService;

@Service("lotteryService")
public class LotteryService extends BaseService {

	private String getLotteryInfoURL;

	private String addWinningInfoURL;

	private String checkWinningURL;

	private String getLotteryImageURL;

	public String getGetLotteryInfoURL() {
		return getLotteryInfoURL;
	}

	@Value("#{propertiesReader[getLotteryInfoURL]}")
	public void setGetLotteryInfoURL(String getLotteryInfoURL) {
		this.getLotteryInfoURL = getLotteryInfoURL;
	}

	public String getAddWinningInfoURL() {
		return addWinningInfoURL;
	}

	@Value("#{propertiesReader[addWinningInfoURL]}")
	public void setAddWinningInfoURL(String addWinningInfoURL) {
		this.addWinningInfoURL = addWinningInfoURL;
	}

	public String getCheckWinningURL() {
		return checkWinningURL;
	}

	@Value("#{propertiesReader[checkWinningURL]}")
	public void setCheckWinningURL(String checkWinningURL) {
		this.checkWinningURL = checkWinningURL;
	}

	public String getGetLotteryImageURL() {
		return getLotteryImageURL;
	}

	@Value("#{propertiesReader[getLotteryImageURL]}")
	public void setGetLotteryImageURL(String getLotteryImageURL) {
		this.getLotteryImageURL = getLotteryImageURL;
	}

	private String getActiveImageURL;

	public String getGetActiveImageURL() {
		return getActiveImageURL;
	}

	@Value("#{propertiesReader[getActiveImageURL]}")
	public void setGetActiveImageURL(String getActiveImageURL) {
		this.getActiveImageURL = getActiveImageURL;
	}

	public String getInfo(String parameter, HttpServletRequest request) {
		String responseGson = redirectToMoblilePayMentService(
				getLotteryInfoURL, parameter,request);
		return responseGson;
	}

	public String addWinningInfo(String parameter, HttpServletRequest request) {
		String responseGson = redirectToMoblilePayMentService(
				addWinningInfoURL, parameter,request);
		return responseGson;
	}

	public String checkWinning(String parameter, HttpServletRequest request) {
		String responseGson = redirectToMoblilePayMentService(
				checkWinningURL, parameter,request);
		return responseGson;
	}

	public byte[] getImage(String parameter, HttpServletRequest request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		byte[] image = redirectToMoblilePayMentService3(getLotteryImageURL,
				parameter, map);
		return image;
	}

	public byte[] getActiveImage(String parameter, HttpServletRequest request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		byte[] image = redirectToMoblilePayMentService3(getActiveImageURL,
				parameter, map);
		return image;
	}

}

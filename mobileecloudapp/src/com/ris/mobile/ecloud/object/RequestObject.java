package com.ris.mobile.ecloud.object;
 

import java.util.*;
import java.io.Serializable;

import com.ris.mobile.ecloud.parser.BaseParser;

import android.content.Context;
public class RequestObject  implements Serializable {


	private static final long serialVersionUID = -482970398096108520L;
	 
	/**
	 * 
	 */
	public int requestUrl;
	public Context context;
	public HashMap<String, String> requestDataMap;
	public BaseParser<?> jsonParser;
	public String majorKey;
	public String objectUrl;
	public void setMajorKey(String majorKey) {
		this.majorKey = majorKey;
	}

	public RequestObject() {
	}

	public RequestObject(int requestUrl, Context context, HashMap<String, String> requestDataMap, BaseParser<?> jsonParser) {
		super();
		this.requestUrl = requestUrl;
		this.context = context;
		this.requestDataMap = requestDataMap;
		this.jsonParser = jsonParser;
	}
	 
	public RequestObject(String objectUrl, Context context, HashMap<String, String> requestDataMap, BaseParser<?> jsonParser) {
		super();
		this.objectUrl = objectUrl;
		this.context = context;
		this.requestDataMap = requestDataMap;
		this.jsonParser = jsonParser;
	}
}
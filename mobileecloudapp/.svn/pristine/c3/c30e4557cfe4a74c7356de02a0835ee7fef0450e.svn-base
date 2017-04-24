package com.ris.mobile.ecloud.parser; 

import org.json.JSONException;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;  
import com.ris.mobile.ecloud.log.CommonLog;
import com.ris.mobile.ecloud.log.LogFactory;
import com.ris.mobile.ecloud.object.ConnectErrorObject; 
import com.ris.mobile.ecloud.util.CommonUtil;
public abstract class BaseParser<T> {
	protected  ConnectErrorObject connectErrorObject; 
    protected String resultData; 
    
    protected static final String TAG = "BaseParser";
    protected static final CommonLog log = LogFactory.createLog(TAG);
	public abstract T parseJSON(String paramString) ; 
	/**
	 * 
	 * @param res
	 * @throws JSONException
	 */  
	public void  checkResponse(String paramString)     {
		log.d(CommonUtil.trim(paramString));
		resultData="";
		connectErrorObject=new ConnectErrorObject();
		connectErrorObject.setErrCode(0);
		connectErrorObject.setErrInfo(""); 
		if (CommonUtil.trim(paramString).equalsIgnoreCase("")) { 
			connectErrorObject.setErrCode (99);
			connectErrorObject.setErrInfo("未返回数据！！"); 
		}  
		
		
		
		else {
			try{
				JSONObject jsonObject = new JSONObject(paramString);
				int errCode = jsonObject.getInt("result");
				String errInfo = CommonUtil.trim(jsonObject.getString("message"));
				connectErrorObject.setErrInfo(errInfo); 
				if (errCode!=1){
					connectErrorObject.setErrCode(errCode);
					
				}else{
					resultData= CommonUtil.trim(jsonObject.getString("data"));
				}
				       // connectErrorObject = JSON.parseObject(paramString, ConnectErrorObject.class);  
			}catch(JSONException ex){
				
			}
			
			 

		}
	}
	public ConnectErrorObject getConnectErrorObject() {
		return connectErrorObject;
	}

	 
	
}
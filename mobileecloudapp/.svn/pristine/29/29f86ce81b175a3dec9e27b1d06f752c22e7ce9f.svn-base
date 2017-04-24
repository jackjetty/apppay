package com.ris.mobile.ecloud.util; 
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.ris.mobile.ecloud.object.AdvertObject;
import com.ris.mobile.ecloud.object.ParObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
public class SharedPreferenceUtil{
	private    SharedPreferences sp;
	private    Editor ed ;
	 
	
	 
	private    Context mContext;
	public SharedPreferenceUtil(Context context ){
		mContext=context;
	}
	private void initSp(){
		if(sp==null)
			sp = mContext.getSharedPreferences("userinfo", mContext.MODE_PRIVATE);
		ed = sp.edit();
	}
	
	
   public List<AdvertObject> getAdvertList() {
		
	    this.initSp();
	    List<AdvertObject> advertList =JSON.parseArray(sp.getString("advert_list", ""), AdvertObject.class);
		 
		return advertList;
	}

	public void setAdvertList(List<AdvertObject> advertList) {
		this.initSp();  
		ed.putString("advert_list", JSON.toJSONString(advertList));  
		ed.commit(); 
	}
    public List<ParObject> getParList() {
		
	    this.initSp();
	    List<ParObject> parList =JSON.parseArray(sp.getString("par_list", ""), ParObject.class);
		 
		return parList;
	}

	public void setParList(List<ParObject> parList) {
		this.initSp();  
		ed.putString("par_list", JSON.toJSONString(parList));  
		ed.commit(); 
	}
	
	public void setToken(String token) {
		this.initSp();  
		ed.putString("token",  token);  
		ed.commit(); 
	}
	
	public String getToken() {
		this.initSp(); 
		return sp.getString("token", "");
	}
	
	
	public String getPortraitUrl() {
		this.initSp(); 
		return sp.getString("portrait_url", "");
	}

	public void setPortraitUrl(String portraitUrl) {
		this.initSp();  
		ed.putString("portrait_url",  portraitUrl);  
		ed.commit(); 
	}
	public String getUserId() {
		this.initSp(); 
		return sp.getString("user_id", "");
	}
	
	
	
	 

	public void setUserId(String userId) {
		this.initSp();  
		ed.putString("user_id",  userId);  
		ed.commit(); 
	}
	
	
	
	
	
	public String getCardNo() {
		this.initSp(); 
		return sp.getString("card_no", "");
	}
	
	
	
	 

	public void setCardNo(String cardNo) {
		this.initSp();  
		ed.putString("card_no",  cardNo);  
		ed.commit(); 
	}
	
	
	public String getUserAccount() {
		this.initSp(); 
		return sp.getString("user_account", "");
	}

	public void setUserAccount(String userAccount) {
		this.initSp();  
		ed.putString("user_account",  userAccount);  
		ed.commit(); 
	}

	public String getUserPassword() {
		this.initSp(); 
		return sp.getString("user_password", "");
	}

	public void setUserPassword(String userPassword) {
		this.initSp();  
		ed.putString("user_password",  userPassword);  
		ed.commit(); 
	}
	
	public String getUserSex() {
		this.initSp(); 
		return sp.getString("user_sex", "");
	}

	public void setUserSex(String userSex) {
		this.initSp();  
		ed.putString("user_sex",  userSex);  
		ed.commit(); 
	}
	
	
	public String getUserName() {
		this.initSp(); 
		return sp.getString("user_name", "");
	}

	public void setUserName(String userName) {
		this.initSp();  
		ed.putString("user_name",  userName);  
		ed.commit(); 
	}
	
	
	public int getUserType() {
		this.initSp(); 
		return sp.getInt("user_type", 0);
	}

	public void setUserType(int userType) {
		this.initSp();  
		ed.putInt("user_type",  userType);  
		ed.commit(); 
	}
	 
	
	
	public String getRechargeAgreeUrl() {
		this.initSp(); 
		return sp.getString("recharge_agreeurl", "");
	}

	public void setRechargeAgreeUrl(String rechargeAgreeUrl) {
		this.initSp();  
		ed.putString("recharge_agreeurl",  rechargeAgreeUrl);  
		ed.commit(); 
	}
	
	public String getRegisterAgreeUrl() {
		this.initSp(); 
		return sp.getString("register_agreeurl", "");
	}

	public void setRegisterAgreeUrl(String registerAgreeUrl) {
		this.initSp();  
		ed.putString("register_agreeurl",  registerAgreeUrl);  
		ed.commit(); 
	}
	
	public boolean getGuideSign() {
		this.initSp(); 
		return sp.getBoolean("guide_sign", false);
	}

	public void setGuideSign(boolean guideSign) {
		this.initSp();  
		ed.putBoolean("guide_sign",  guideSign);  
		ed.commit(); 
	}
	
	
	public void setPasswodRemeber(boolean passwodRemeber){
		this.initSp();  
		ed.putBoolean ("password_remeber",  passwodRemeber);  
		ed.commit(); 
	} 
	
	public boolean getPasswodRemeber(){
		this.initSp(); 
		return sp.getBoolean("password_remeber", false);
	}
	
	public void setICAgree(boolean icAgree){
		this.initSp();  
		ed.putBoolean ("ic_agree",  icAgree);  
		ed.commit(); 
	} 
	
	public boolean getICAgree(){
		this.initSp(); 
		return sp.getBoolean("ic_agree", false);
	}
	
}
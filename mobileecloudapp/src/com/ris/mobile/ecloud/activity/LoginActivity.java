package com.ris.mobile.ecloud.activity;
 

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Vector; 

import roboguice.inject.InjectView;

import com.alibaba.fastjson.JSON;
import com.ris.mobile.ecloud.BaseActivity;
import com.ris.mobile.ecloud.R; 
import com.ris.mobile.ecloud.LApplication;
import com.ris.mobile.ecloud.log.CommonLog;
import com.ris.mobile.ecloud.log.LogFactory;
import com.ris.mobile.ecloud.object.ConnectErrorObject;
import com.ris.mobile.ecloud.object.RequestObject;
import com.ris.mobile.ecloud.object.ResponseObject;
import com.ris.mobile.ecloud.object.AdvertObject;
import com.ris.mobile.ecloud.object.UserObject;
import com.ris.mobile.ecloud.parser.BaseParser;
import com.ris.mobile.ecloud.parser.LoginParser;
import com.ris.mobile.ecloud.parser.ResponseParser;
import com.ris.mobile.ecloud.util.CommonUtil;
import com.ris.mobile.ecloud.util.Constant; 
import com.ris.mobile.ecloud.util.Md5;
import com.ris.mobile.ecloud.util.SharedPreferenceUtil;
import com.ris.mobile.ecloud.widget.DelEditText;
import com.ris.mobile.ecloud.widget.LoadingDialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast; 
import android.widget.LinearLayout.LayoutParams;
public class LoginActivity  extends BaseActivity  implements  OnClickListener {
	 
	public static LApplication lApplication=LApplication.getInstance();
	private SharedPreferenceUtil sharedPreferenceUtil;
	
	@InjectView (R.id.et_Account) 
	private DelEditText etAccount;
	
	@InjectView (R.id.et_Password) 
	private DelEditText etPassword;
	
	@InjectView (R.id.btn_Login) 
	private Button btnLogin; 
	
	@InjectView (R.id.cb_Remeber) 
	private CheckBox cbRemeber;
	
	@InjectView (R.id.tv_Register) 
	private TextView tvRegister;
	
	@InjectView (R.id.tv_PasswordForget) 
	private TextView tvPasswordForget;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initData(getIntent()); 
		initView();
		setListener(); 
	}
	public  void initData(Intent intent){
		    
		   sharedPreferenceUtil=new SharedPreferenceUtil(LoginActivity.this);
		
		  
	}
	
	 
	
	public void initView(){
		/*etAccount.setFocusable(true);   
		etAccount.setFocusableInTouchMode(true);   
		etPassword.setFocusable(true);   
		etPassword.setFocusableInTouchMode(true); */  
		cbRemeber.setChecked(sharedPreferenceUtil.getPasswodRemeber());
		etAccount.setText(sharedPreferenceUtil.getUserAccount());
		if(sharedPreferenceUtil.getPasswodRemeber()){
			etPassword.setText(sharedPreferenceUtil.getUserPassword());
		}
		
	}
	public void setListener(){
		btnLogin.setOnClickListener(this);  
		tvRegister.setOnClickListener(this);  
		tvPasswordForget.setOnClickListener(this);  
	}
	@Override
	public void onConfigurationChanged(Configuration config) {
			 super.onConfigurationChanged(config); 
			if (config.orientation == Configuration.ORIENTATION_PORTRAIT) {
			 
			} 
			if (config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
			 
			 
			}
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		  case R.id.btn_Login:
			  String userAccount=CommonUtil.trim(etAccount.getText().toString());
			  String userPassword=CommonUtil.trim(etPassword.getText().toString());
			  if(!CommonUtil.isInteger(userAccount) ){
				  DisPlay("error","请输入正确的帐号！！");
				  etAccount.requestFocus();
				  return;
			  }
			  if(userPassword.equals("")){
				  DisPlay("error","请输入您的用户密码！！");
				  etPassword.requestFocus();
				  return;
			  }
			  
			   
			  HashMap<String, String> requestDataMap=new HashMap<String, String>(); 
			  requestDataMap.put("loginName", userAccount);
			  requestDataMap.put("loginPwd",Md5.digest( userPassword));	 
			  //log.e(Md5.digest( userPassword));
			  BaseParser<UserObject> responseParser = new LoginParser();
			  
			  
			  	 

		      RequestObject vo = new RequestObject(R.string.url_login, this, requestDataMap, responseParser);
		      getDataFromServer(vo, new DataCallback<UserObject>() {
					@Override
					public void processData(UserObject paramObject, boolean paramBoolean) { 
						 
					 
						 sharedPreferenceUtil.setUserAccount(CommonUtil.trim(etAccount.getText().toString()));
						 sharedPreferenceUtil.setUserPassword(CommonUtil.trim(etPassword.getText().toString()));
						 sharedPreferenceUtil.setPasswodRemeber(cbRemeber.isChecked()); 
						 sharedPreferenceUtil.setUserName(paramObject.getUserName());
						 sharedPreferenceUtil.setUserSex(paramObject.getUserSex());
						  
						 sharedPreferenceUtil.setUserType(paramObject.getUserType());
						 sharedPreferenceUtil.setUserId(paramObject.getUserId());
						 sharedPreferenceUtil.setToken(paramObject.getToken());
						 sharedPreferenceUtil.setPortraitUrl(paramObject.getPortraitUrl());
						 sharedPreferenceUtil.setCardNo(CommonUtil.trim(paramObject.getCardNo()));
						 
						 lApplication.setLoginStatus(true); 
						 
						 Bundle myBundel=new Bundle(); 
						 myBundel.putString("firstBind","true"); 
						 if(paramObject.isBindPhone()){
							 openActivity(BindPhoneNumberActivity.class,myBundel);
						 }
						 else{
							 openActivity(MainActivity.class);
							 LoginActivity.this.finish();
						 } 
						 
						
						 
					}
					@Override
		    		public void processError(ConnectErrorObject responseErrorVo){
						
						DisPlay("error",responseErrorVo.getErrInfo());  
		    		}
				});
			  
			  break;
			  
		  case R.id.tv_Register:
			   openActivity(RegisterActivity.class);
		       break;
		  case R.id.tv_PasswordForget:
			   openActivity(FindPasswordActivity.class);
		       break;
		  
	    } 
	}
	
}
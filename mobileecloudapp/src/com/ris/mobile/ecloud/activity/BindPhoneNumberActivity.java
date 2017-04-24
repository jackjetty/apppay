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

import com.ris.mobile.ecloud.AppManager;
import com.ris.mobile.ecloud.BaseActivity;
import com.ris.mobile.ecloud.R; 
import com.ris.mobile.ecloud.LApplication; 
import com.ris.mobile.ecloud.log.CommonLog;
import com.ris.mobile.ecloud.log.LogFactory;
import com.ris.mobile.ecloud.object.ConnectErrorObject; 
import com.ris.mobile.ecloud.object.RequestObject;
import com.ris.mobile.ecloud.object.ResponseObject; 
import com.ris.mobile.ecloud.parser.BaseParser;
import com.ris.mobile.ecloud.parser.ResponseParser;
import com.ris.mobile.ecloud.util.CommonUtil;
import com.ris.mobile.ecloud.util.Constant;
import com.ris.mobile.ecloud.util.SharedPreferenceUtil;

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
import android.os.Message;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;  
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
public class BindPhoneNumberActivity extends BaseActivity  implements  OnClickListener  {
	 
	private String firstBind;
	
	@InjectView (R.id.tv_Back)
	private TextView tvBack;
	
	@InjectView (R.id.tv_Title)
	private TextView tvTitle;
	
	@InjectView (R.id.tv_Right)
	private TextView tvRight; 
	
	@InjectView (R.id.et_PhoneNumber)
	private EditText etPhoneNumber;
	
	@InjectView (R.id.et_VerifyCode)
	private EditText etVerifyCode;
	
	@InjectView (R.id.btn_Verify)
	private Button  btnVerify; 
	
	@InjectView (R.id.btn_BindPN)
	private Button  btnBindPN;
	
	public static LApplication lApplication=LApplication.getInstance();
	private SharedPreferenceUtil sharedPreferenceUtil;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bindphonenumber);
		initData(getIntent());
		 
		initView();
		setListener();
		 
	}
	 
	public    void initData(Intent intent){ 
		sharedPreferenceUtil=new SharedPreferenceUtil(BindPhoneNumberActivity.this);
		firstBind = CommonUtil.trim( intent.getStringExtra("firstBind"));
		 
		
	}
	public void initView(){ 
		tvBack.setVisibility(View.VISIBLE);
		tvTitle.setText(R.string.title_bindphonenumber);
		if(!TextUtils.isEmpty(firstBind)){
			//tvBack.setVisibility(View.INVISIBLE);
			tvTitle.setText("绑定手机");
		}
    	tvTitle.setVisibility(View.VISIBLE);
    	
    	tvRight.setVisibility(View.INVISIBLE); 
	}
	public void setListener(){
		tvBack.setOnClickListener(this); 
		btnVerify.setOnClickListener(this);
		btnBindPN.setOnClickListener(this); 
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
		HashMap<String, String> requestDataMap;
		String phoneNumber;
		switch (v.getId()) {
		  case R.id.tv_Back:
			  BindPhoneNumberActivity.this.finish();
			   
			  
			  
			 break;
		  case R.id.btn_Verify:
			  if(!btnVerify.isEnabled()){
					return;
				}
			  phoneNumber=CommonUtil.trim(etPhoneNumber.getText().toString());
			  if(!CommonUtil.isValidMobiNumber(phoneNumber)){
				  DisPlay("error","请输入正确的手机号码！！");
				  etPhoneNumber.requestFocus();
				  return;
			  }
			  requestDataMap=new HashMap<String, String>();
			  requestDataMap.put("mobile",  phoneNumber);
			  
              BaseParser<ResponseObject> responseParser1 = new ResponseParser();
			  
		      RequestObject vo1 = new RequestObject(R.string.url_verifybindphonenumber, this, requestDataMap, responseParser1);
		      
			  getDataFromServer(vo1, new DataCallback<ResponseObject>() {  
					@Override
					public void processData(ResponseObject paramObject, boolean paramBoolean) {
						if(paramObject.isSuccess()){
							new Thread(new Times()).start();
							  
						  }else{
							  DisPlay("error", CommonUtil.trim(paramObject.getInfo()));
						  }
						return; 

					}
					@Override
		    		public void processError(ConnectErrorObject responseErrorVo){
						  
						 DisPlay("error",responseErrorVo.getErrInfo());
						 
		    		}
				},true);
				
				break;
		  
		  case R.id.btn_BindPN:  
			  phoneNumber=CommonUtil.trim(etPhoneNumber.getText().toString());
			  String verifyCode=CommonUtil.trim(etVerifyCode.getText().toString()); 
			  if(!CommonUtil.isValidMobiNumber(phoneNumber)){
				  DisPlay("error","请输入正确的手机号码！！");
				  etPhoneNumber.requestFocus();
				  return;
			  }
			  if(!CommonUtil.isInteger(verifyCode)){
				  DisPlay("error","请输入正确验证码！！");
				  etVerifyCode.requestFocus();
				  return;
			  }
			  requestDataMap=new HashMap<String, String>();  

			  requestDataMap.put("mobile", phoneNumber);
			  requestDataMap.put("verifyCode",verifyCode );
			  requestDataMap.put("userId",  sharedPreferenceUtil.getUserId() );
              BaseParser<ResponseObject> responseParser = new ResponseParser();
			  
		      RequestObject vo = new RequestObject(R.string.url_bindphonenumber, this, requestDataMap, responseParser);
		      getDataFromServer(vo, new DataCallback<ResponseObject>() {
					@Override
					public void processData(ResponseObject paramObject, boolean paramBoolean) {
						  if(paramObject.isSuccess()){
							  DisPlay("correct",CommonUtil.trim(paramObject.getInfo())); 
							  if(!TextUtils.isEmpty(firstBind)){
								  openActivity(MainActivity.class);
								  BindPhoneNumberActivity.this.finish();
								  AppManager.getInstance().killActivity(LoginActivity.class);
								 
								  return;
							  }  
							  Intent intent=new Intent();  
				              intent.putExtra("PHONENUMBER", CommonUtil.trim(etPhoneNumber.getText().toString()));   
				              setResult(RESULT_OK, intent);    
							  BindPhoneNumberActivity.this.finish();
						  }else{
							  DisPlay("error", CommonUtil.trim(paramObject.getInfo()));
						  } 
					}
					@Override
		    		public void processError(ConnectErrorObject responseErrorVo){
						 
						DisPlay("error",responseErrorVo.getErrInfo());
		    		}
				}); 
			  break;
		}
	}
		Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				switch (msg.arg2) {
				case 0: 
					btnVerify.setEnabled(false);
					btnVerify.setTextColor(getResources().getColor(R.color.text_blue));
					btnVerify.setText(new Integer(msg.arg1).toString() ); 
					btnVerify.setText("    "+new Integer(msg.arg1).toString()+"秒    " ); 
					btnVerify.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_verify_disable));   
					 
					if (msg.arg1 == 1) {
						 btnVerify.setEnabled(true);
						 btnVerify.setText("发送验证码"); 
						 btnVerify.setTextColor(getResources().getColor(R.color.white));
						 btnVerify.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_verify));
						 					 				 
					}
					break; 
				default:
					break;
				}
				super.handleMessage(msg);
			}
		};
		
		private class Times implements Runnable{
			@Override
			public void run() {

				int a = 60;
				while(true){
					try {
						
						Message message = new Message();
						message.arg2 = 0;
						message.arg1 = a;
						handler.sendMessage(message);
						if (a == 1) {
							break;
						}
						a = a-1;
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
						
			}
		}
}
	

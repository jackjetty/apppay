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
import com.ris.mobile.ecloud.object.PersonObject;
import com.ris.mobile.ecloud.object.RequestObject;
import com.ris.mobile.ecloud.object.ResponseObject; 
import com.ris.mobile.ecloud.parser.BaseParser;
import com.ris.mobile.ecloud.parser.PersonParser;
import com.ris.mobile.ecloud.parser.ResponseParser;
import com.ris.mobile.ecloud.util.CommonUtil; 
import com.ris.mobile.ecloud.util.Constant;
import com.ris.mobile.ecloud.util.SharedPreferenceUtil;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent; 
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
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;  
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;  
public class PersonActivity  extends BaseActivity  implements  OnClickListener,RadioGroup.OnCheckedChangeListener {
	
	
 
	public  static LApplication lApplication=LApplication.getInstance();
	private SharedPreferenceUtil sharedPreferenceUtil;
	
	@InjectView (R.id.tv_Back) 
	private TextView tvBack;
	
	@InjectView (R.id.tv_Title) 
	private TextView tvTitle;
	
	@InjectView (R.id.tv_Right) 
	private TextView tvRight;
	
	@InjectView (R.id.tv_UserId) 
	private TextView tvUserId;
	
	@InjectView (R.id.tv_UserName) 
	private TextView tvUserName;
	
	@InjectView (R.id.tv_IDNum) 
	private TextView tvIDNum;
	
	@InjectView (R.id.tv_UserKind) 
	private TextView tvUserKind;
	
	@InjectView (R.id.tv_PhoneNumber) 
	private TextView tvPhoneNumber;
	
	@InjectView (R.id.ll_PhoneNumber) 
	private View llPhoneNumber;
	
	@InjectView (R.id.iv_PNEdit) 
	private ImageView  ivPNEdit;
	
	@InjectView (R.id.rg_UserSex) 
	private RadioGroup rgUserSex;
	
	@InjectView (R.id.rb_Male) 
	private RadioButton rbMale;
	
	@InjectView (R.id.rb_Female) 
	private RadioButton rbFemale;
	
	@InjectView (R.id.btn_Exit) 
	private Button btnExit;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_person);
		initData(getIntent()); 
		initView();
		setListener();
		 
	}
	public    void initData(Intent intent){ 
		sharedPreferenceUtil=new SharedPreferenceUtil(PersonActivity.this);
	}
	 
	public void initView(){
	  	  
		tvBack.setVisibility(View.VISIBLE);
    	tvTitle.setVisibility(View.VISIBLE);
    	tvTitle.setText(R.string.title_person);
    	tvRight.setVisibility(View.VISIBLE);
    	
    	
    	
    	tvRight.setText("保存");
    	if(sharedPreferenceUtil.getUserType()== Constant.USERTYPE_OUT){
    		ivPNEdit.setVisibility(View.GONE);
    	}else{
    		ivPNEdit.setVisibility(View.VISIBLE);
    	}
    	
    	 
    	HashMap<String, String> requestDataMap=new HashMap<String, String>(); 
		requestDataMap.put("userId",  sharedPreferenceUtil.getUserId() );
			
        BaseParser<PersonObject> personParser = new PersonParser();
		  
	    RequestObject vo = new RequestObject(R.string.url_person, this, requestDataMap, personParser);
	    getDataFromServer(vo, new DataCallback<PersonObject>() {
				@Override
				public void processData(PersonObject paramObject, boolean paramBoolean) { 
					  
					tvUserId.setText(CommonUtil.trim(paramObject.getAccount()));
					tvUserName.setText(CommonUtil.trim(paramObject.getUserName()));
					tvIDNum.setText(CommonUtil.trim(paramObject.getIdNum()));
					switch (paramObject.getUserType()){
					   case Constant.USERTYPE_TEACHER:
						     tvUserKind.setText("教师");
						      break;
					   case Constant.USERTYPE_STUDENT:
						     tvUserKind.setText("学生");
						      break;  
					   case Constant.USERTYPE_OUT:
						     tvUserKind.setText("校外人员");
						      break; 
					   default:
						   tvUserKind.setText("校外人员");
						     break;
					}
					 
					  
					
					
					tvPhoneNumber.setText(CommonUtil.trim(paramObject.getMobile()));
					
					String userSex=CommonUtil.trim(paramObject.getUserSex());
					if(userSex.equalsIgnoreCase("女")){
						rbFemale.setChecked(true);
						rbMale.setChecked(false);
					}else{
						rbMale.setChecked(true);
						rbFemale.setChecked(false);
					}
					 
				}
				@Override
	    		public void processError(ConnectErrorObject responseErrorVo){
					   DisPlay("error",responseErrorVo.getErrInfo());
					 
	    		}
			});
	      
	      
	      
    	
    	
    	 
	} 

	public void setListener(){
		tvBack.setOnClickListener(this); 
		tvRight.setOnClickListener(this); 
		btnExit.setOnClickListener(this);
		rgUserSex.setOnCheckedChangeListener(this);
		
		if(sharedPreferenceUtil.getUserType()== Constant.USERTYPE_OUT){
    		 
    	}else{
    		llPhoneNumber.setOnClickListener(this);
    	}
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
     public void onCheckedChanged(RadioGroup group, int checkedId) {  
         // TODO Auto-generated method stub  
         if(checkedId==R.id.rb_Male){                    
                
         }  
         if(checkedId==R.id.rb_Female){  
              
         }  
         
     } 
	 @Override
	 protected void onActivityResult(int requestCode, int resultCode, Intent mIntent) {
			if (resultCode != RESULT_OK){
				return;
			}
			switch (requestCode) {
				case Constant.RESC_BINDPHONE: 
					String phoneNumber =CommonUtil.trim(mIntent.getStringExtra("PHONENUMBER")); 
					tvPhoneNumber.setText(phoneNumber);
					break;
			}
		}
	 
    @Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		  case R.id.tv_Back:
			  PersonActivity.this.finish();
			  break;
		  case R.id.ll_PhoneNumber:
			    Intent intent = new Intent(PersonActivity.this,
						BindPhoneNumberActivity.class); 				
				startActivityForResult(intent,Constant.RESC_BINDPHONE);
				break;
		  case R.id.tv_Right:
			  
			  String userSex="1";
			  if(rbFemale.isChecked()){
				  userSex="2";
			  } 
			  HashMap<String, String> requestDataMap=new HashMap<String, String>();  
			  requestDataMap.put("userSex",userSex );  
			  requestDataMap.put("userId",  sharedPreferenceUtil.getUserId() ); 
              BaseParser<ResponseObject> responseParser = new ResponseParser();
              

		      RequestObject vo = new RequestObject(R.string.url_saveperson, this, requestDataMap, responseParser);
		      getDataFromServer(vo, new DataCallback<ResponseObject>() {
					@Override
					public void processData(ResponseObject paramObject, boolean paramBoolean) { 
						  if(paramObject.isSuccess()){
							  DisPlay("correct", paramObject.getInfo()); 
							  if(rbMale.isChecked())
								  sharedPreferenceUtil.setUserSex("男");
							  else 
								  sharedPreferenceUtil.setUserSex("女");
							   
						  }else{
							  DisPlay("error", paramObject.getInfo());  
						  }  
					}
					@Override
		    		public void processError(ConnectErrorObject responseErrorVo){
						 DisPlay("error",responseErrorVo.getErrInfo());
						 
		    		}
				}); 
			  break;
		  case R.id.btn_Exit: 
			  AppManager.getInstance().killActivity(MainActivity.class);
			  lApplication.setLoginStatus(false);
			  openActivity(LoginActivity.class);
			  this.finish();
			  break;  
		}
	}
	
	
	
}
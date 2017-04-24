package com.ris.mobile.ecloud.activity; 

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import roboguice.inject.InjectView;

import com.bestpay.plugin.Plugin;
import com.ris.mobile.ecloud.BaseActivity;
import com.ris.mobile.ecloud.R;
import com.ris.mobile.ecloud.LApplication; 
import com.ris.mobile.ecloud.BaseActivity.DataCallback;
import com.ris.mobile.ecloud.log.CommonLog;
import com.ris.mobile.ecloud.log.LogFactory;  
import com.ris.mobile.ecloud.object.ConnectErrorObject;
   
import com.ris.mobile.ecloud.object.ApplyPayOrderObject;
import com.ris.mobile.ecloud.object.ApplySignDetailObject; 
import com.ris.mobile.ecloud.object.ApplySignOrderObject;
import com.ris.mobile.ecloud.object.RequestObject;
import com.ris.mobile.ecloud.object.ResponseObject;
   
import com.ris.mobile.ecloud.parser.ApplyPayOrderParser;
import com.ris.mobile.ecloud.parser.ApplySignOrderParser;
import com.ris.mobile.ecloud.parser.ApplySignResParser;
import com.ris.mobile.ecloud.parser.BaseParser;  
import com.ris.mobile.ecloud.parser.ResponseParser;
import com.ris.mobile.ecloud.util.CommonUtil;
import com.ris.mobile.ecloud.util.Constant;
import com.ris.mobile.ecloud.util.NetUtil;
import com.ris.mobile.ecloud.util.SharedPreferenceUtil;  

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.MotionEvent;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
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

public class ApplySignRedActivity extends BaseActivity implements OnClickListener  {

 
	
	 
    private String tradeNo; 
    
    @InjectView (R.id.tv_Back) 
    private TextView tvBack;
    
    @InjectView (R.id.tv_Title) 
	private TextView tvTitle;
	
    @InjectView (R.id.tv_Right) 
	private TextView tvRight;
	
    @InjectView (R.id.tv_DealNumber) 
	private TextView tvDealNumber;
	
    @InjectView (R.id.tv_ApplyTitle) 
	private TextView tvApplyTitle;
	
    @InjectView (R.id.tv_SkuLabel) 
	private TextView tvSkuLabel;
	
    @InjectView (R.id.tv_SkuName) 
	private TextView tvSkuName;
	
    @InjectView (R.id.tv_Status) 
	private TextView tvStatus;
	
    @InjectView (R.id.tv_EnterTime) 
	private TextView tvEnterTime;
	
    @InjectView (R.id.tv_StudNo) 
	private TextView tvStudNo;
	
    @InjectView (R.id.tv_UserName) 
	private TextView tvUserName;
	
    @InjectView (R.id.tv_IDNum) 
	private TextView tvIDNum;
	
    @InjectView (R.id.tv_PhoneNumber) 
	private TextView tvPhoneNumber;
	
    @InjectView (R.id.ll_Other1) 
	private LinearLayout llOther1;
	
    @InjectView (R.id.ll_Other2) 
	private LinearLayout llOther2;
	
    @InjectView (R.id.tv_LabelOther1) 
	private TextView tvLabelOther1;
	
    @InjectView (R.id.tv_ValueOther1) 
	private TextView tvValueOther1;
	
    @InjectView (R.id.tv_LabelOther2) 
	private TextView tvLabelOther2;
	
    @InjectView (R.id.tv_ValueOther2) 
	private TextView tvValueOther2;
	
    @InjectView (R.id.tv_Remark) 
	private TextView tvRemark; 
	
    @InjectView (R.id.tv_ResInstruction) 
	private TextView tvResInstruction;
	
    @InjectView (R.id.ll_Btn) 
	private LinearLayout llBtn;
	
    @InjectView (R.id.btn_Pay) 
	private Button btnPay;
	
    @InjectView (R.id.btn_Cancel) 
	private Button btnCancel; 
	 
	  
	 
	 
	public static LApplication lApplication = LApplication.getInstance();
	private SharedPreferenceUtil sharedPreferenceUtil;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_applysignred);
		initData(getIntent()); 
		initView();
		setListener();

	}

	 

	public void initData(Intent intent) {
		sharedPreferenceUtil = new SharedPreferenceUtil(ApplySignRedActivity.this); 
		tradeNo = intent.getStringExtra("tradeNo");  
		tradeNo=CommonUtil.trim(tradeNo); 
		
	 
		 

	}

	public void initView() {

		tvBack.setVisibility(View.VISIBLE);
		tvTitle.setVisibility(View.VISIBLE);
		tvTitle.setText(R.string.title_applydetail);
		tvRight.setVisibility(View.INVISIBLE); 
		llBtn.setVisibility(View.GONE);
		btnPay.setVisibility(View.GONE);
		btnCancel.setVisibility(View.GONE);
		
		tvDealNumber.setText("");
		tvUserName.setText("");
		tvEnterTime.setText("");
		tvApplyTitle.setText("");
		tvSkuLabel.setText("");
		tvSkuName.setText("");
		tvStudNo.setText("");
		tvIDNum.setText("");
		tvStatus.setText("");
		tvPhoneNumber.setText("");
		tvLabelOther1.setText("");
		tvValueOther1.setText("");
		tvLabelOther2.setText("");
		tvValueOther2.setText("");
		tvRemark.setText("");
		tvResInstruction.setText("");
		 
		
		 
		
		BaseParser<ApplySignDetailObject> applySignResParser = new ApplySignResParser();
		HashMap<String, String> requestDataMap = new HashMap<String, String>();
		requestDataMap.put("userId", sharedPreferenceUtil.getUserId() );
		requestDataMap.put("dealNumber", tradeNo); 
		 
		RequestObject vo = new RequestObject(R.string.url_applysigndetail,
				ApplySignRedActivity.this, requestDataMap, applySignResParser);
		 
		getDataFromServer(vo, new DataCallback<ApplySignDetailObject>() {
			@Override
			public void processData(ApplySignDetailObject paramObject,
					boolean paramBoolean) {
				
				
				tvDealNumber.setText(CommonUtil.trim(paramObject.getDealNumber()));
				tvApplyTitle.setText(CommonUtil.trim(paramObject.getApplyTitle()));
				tvSkuLabel.setText(CommonUtil.trim(paramObject.getSkuLabel()));
				tvSkuName.setText(CommonUtil.trim(paramObject.getSkuName()));
				tvStudNo.setText(CommonUtil.trim(paramObject.getStudNo()));
				tvIDNum.setText(CommonUtil.trim(paramObject.getIdNum()));
				if(CommonUtil.trim(paramObject.getStatus()).equalsIgnoreCase(Constant.STATUS_SUCCESS))
					tvStatus.setText("报名成功");
				if(CommonUtil.trim(paramObject.getStatus()).equalsIgnoreCase(Constant.STATUS_FAIL))
					tvStatus.setText("报名失败");
				if(CommonUtil.trim(paramObject.getStatus()).equalsIgnoreCase(Constant.STATUS_TOPAY))
					tvStatus.setText("待支付确认");
				if(CommonUtil.trim(paramObject.getStatus()).equalsIgnoreCase(Constant.STATUS_CANCEL))
					tvStatus.setText("取消报名");
				tvRemark.setText(CommonUtil.trim(paramObject.getRemark()));
				tvResInstruction.setText(CommonUtil.trim(paramObject.getInstruction())); 
				tvEnterTime.setText(CommonUtil.trim(paramObject.getEnterTime())); 
				tvUserName.setText(CommonUtil.trim(paramObject.getUserName()));
				tvPhoneNumber.setText(CommonUtil.trim(paramObject.getMobile()));
				tvLabelOther1.setText(CommonUtil.trim(paramObject.getLabelOther1()));
				tvLabelOther2.setText(CommonUtil.trim(paramObject.getLabelOther2()));
				tvValueOther1.setText(CommonUtil.trim(paramObject.getHintOther1())); 
				tvValueOther2.setText(CommonUtil.trim(paramObject.getHintOther2())); 
				if(CommonUtil.trim(paramObject.getLabelOther1()).equals("")){
					tvLabelOther1.setVisibility(View.GONE);
					tvValueOther1.setVisibility(View.GONE);
					llOther1.setVisibility(View.GONE);
				}
				if(CommonUtil.trim(paramObject.getLabelOther2()).equals("")){
					tvLabelOther2.setVisibility(View.GONE);
					tvValueOther2.setVisibility(View.GONE);
					llOther2.setVisibility(View.GONE);
				}
				
				if(paramObject.isShowCancel()){
					llBtn.setVisibility(View.VISIBLE);
					btnCancel.setVisibility(View.VISIBLE);
				}
				
				if(paramObject.isShowPay()){
					llBtn.setVisibility(View.VISIBLE);
					btnPay.setVisibility(View.VISIBLE);
				}  
				 
				 
				  

			}

			@Override
			public void processError(ConnectErrorObject responseErrorVo) {
				 
				DisPlay("error", responseErrorVo.getErrInfo());

			}
		});
		 

	}

	public void setListener() {
		tvBack.setOnClickListener(this); 
		btnCancel.setOnClickListener(this); 
		btnPay.setOnClickListener(this);  
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
			case R.id.tv_Back:
				this.finish();
				break;
			case R.id.btn_Cancel:
				cancelDeal();
				break;
			case R.id.btn_Pay:
				 
				break;
		

		}
	}
	
	private void cancelDeal(){
		BaseParser<ResponseObject> responseParser = new ResponseParser();
		HashMap<String, String> requestDataMap = new HashMap<String, String>();
		requestDataMap.put("userId", sharedPreferenceUtil.getUserId() );
		requestDataMap.put("tradeNo", tradeNo); 
		 
		RequestObject vo = new RequestObject(R.string.url_dealcancel,
				ApplySignRedActivity.this, requestDataMap, responseParser);
		 
		getDataFromServer(vo, new DataCallback<ResponseObject>() {
			@Override
			public void processData(ResponseObject paramObject,
					boolean paramBoolean) {
				
				llBtn.setVisibility(View.GONE);
				DisPlay("info", paramObject.getInfo()); 
				Bundle myBundel=new Bundle();  
				myBundel.putString("tradeNo", ApplySignRedActivity.this.tradeNo);
				ApplySignRedActivity.this.openActivity(
						ApplySignResActivity.class, myBundel);
				ApplySignRedActivity.this.finish();
			}

			@Override
			public void processError(ConnectErrorObject responseErrorVo) {
				 
				DisPlay("error", responseErrorVo.getErrInfo());

			}
		});
	}
	
	
	 
	
}
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
import com.ris.mobile.ecloud.object.ApplySignObject;
import com.ris.mobile.ecloud.object.ConnectErrorObject;
 
import com.ris.mobile.ecloud.object.ApplyPayOrderObject;
import com.ris.mobile.ecloud.object.ApplySignOrderObject;
import com.ris.mobile.ecloud.object.ItemObject;
import com.ris.mobile.ecloud.object.ParObject;
import com.ris.mobile.ecloud.object.RequestObject;
import com.ris.mobile.ecloud.object.ResponseObject;
import com.ris.mobile.ecloud.object.SkuObject;
import com.ris.mobile.ecloud.parser.ApplyPayOrderParser;
import com.ris.mobile.ecloud.parser.ApplySignOrderParser;
import com.ris.mobile.ecloud.parser.ApplySignParser;
import com.ris.mobile.ecloud.parser.BaseParser; 
import com.ris.mobile.ecloud.parser.ResponseParser;
import com.ris.mobile.ecloud.util.CommonUtil;
import com.ris.mobile.ecloud.util.Constant;
import com.ris.mobile.ecloud.util.NetUtil;
import com.ris.mobile.ecloud.util.SharedPreferenceUtil;
import com.ris.mobile.ecloud.widget.AlertDialog;
import com.ris.mobile.ecloud.widget.ApplyPayPromptDialog;
import com.ris.mobile.ecloud.widget.ApplySignPromptDialog;
import com.ris.mobile.ecloud.widget.ParDialog;
import com.ris.mobile.ecloud.widget.ParDialog.OnAddressCListener;

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

public class ApplySignActivity extends BaseActivity implements OnClickListener,OnTouchListener  {

	 
	
	
	private String applyId;
	private String applyType; 
	private String tradeNo;
	private List<SkuObject> skuList; 
	private SkuObject selSkuObject;
	
	@InjectView (R.id.tv_Back) 
	private TextView tvBack;
	
	@InjectView (R.id.tv_Title) 
	private TextView tvTitle;
	
	@InjectView (R.id.tv_Right) 
	private TextView tvRight;
	
	@InjectView (R.id.tv_ApplyTitle) 
	private TextView tvApplyTitle; 
	
	@InjectView (R.id.tv_UserId) 
	private TextView tvUserId;
	
	@InjectView (R.id.tv_UserName) 
	private TextView tvUserName;
	
	@InjectView (R.id.tv_IDNum) 
	private TextView tvIDNum;
	
	@InjectView (R.id.tv_StudNo) 
	private TextView tvStudNo;
	
	@InjectView (R.id.tv_PhoneNumber) 
	private TextView tvPhoneNumber;
	
	@InjectView (R.id.tv_SkuLabel) 
	private TextView tvSkuLabel;
	
	@InjectView (R.id.et_SkuName) 
	private EditText etSkuName;
	
	@InjectView (R.id.rg_UserSex) 
	private RadioGroup rgUserSex;
	
	@InjectView (R.id.rb_Male) 
	private RadioButton rbMale;
	
	@InjectView (R.id.rb_Female) 
	private RadioButton rbFemale;
	
	@InjectView (R.id.tv_LabelOther1) 
	private TextView tvLabelOther1;
	
	@InjectView (R.id.et_ValueOther1) 
	private EditText etValueOther1;
	
	@InjectView (R.id.tv_LabelOther2) 
	private TextView tvLabelOther2;
	
	@InjectView (R.id.et_ValueOther2) 
	private EditText etValueOther2;
	
	@InjectView (R.id.ll_Other1) 
	private LinearLayout llOther1;
	
	@InjectView (R.id.ll_Other2) 
	private LinearLayout llOther2;
	
	@InjectView (R.id.et_Remark) 
	private EditText etRemark;
	
	@InjectView (R.id.btn_Apply) 
	private Button btnApply;
	  
	 
	 
	public static LApplication lApplication = LApplication.getInstance();
	private SharedPreferenceUtil sharedPreferenceUtil;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_applysign);
		initData(getIntent()); 
		initView();
		setListener();

	}

	 

	public void initData(Intent intent) {
		sharedPreferenceUtil = new SharedPreferenceUtil(ApplySignActivity.this); 
		applyId = intent.getStringExtra("applyId");  
		applyId=CommonUtil.trim(applyId); 
		
		applyType=CommonUtil.trim(intent.getStringExtra("applyType")); 
		
		skuList=null; 
		selSkuObject=null;
		 

	}

	public void initView() {

		tvBack.setVisibility(View.VISIBLE);
		tvTitle.setVisibility(View.VISIBLE);
		tvTitle.setText(R.string.title_applysign);
		tvRight.setVisibility(View.INVISIBLE); 
		
		
	 
		
		tvApplyTitle.setText(""); 
		tvUserId.setText("");
		tvUserName.setText("");
		tvIDNum.setText("");
		tvStudNo.setText("");
		tvSkuLabel.setText("");
		etSkuName.setText("");
		tvPhoneNumber.setText("");
		tvLabelOther1.setText("");
		etValueOther1.setText("");
		tvLabelOther2.setText("");
		etValueOther2.setText("");
		etRemark.setText("");
		btnApply.setVisibility(View.VISIBLE); 
		 
		
		BaseParser<ApplySignObject> applySignParser = new ApplySignParser();
		HashMap<String, String> requestDataMap = new HashMap<String, String>();
		requestDataMap.put("userType", new Integer(sharedPreferenceUtil.getUserType()).toString() );
		requestDataMap.put("applyId", applyId);
		requestDataMap.put("applyType", applyType);
		RequestObject vo = new RequestObject(R.string.url_applysign,
				ApplySignActivity.this, requestDataMap, applySignParser);
		 
		getDataFromServer(vo, new DataCallback<ApplySignObject>() {
			@Override
			public void processData(ApplySignObject paramObject,
					boolean paramBoolean) {
				 
				tvApplyTitle.setText(CommonUtil.trim(paramObject.getApplyTitle())); 
				tvUserId.setText( sharedPreferenceUtil.getUserAccount());
				tvUserName.setText(CommonUtil.trim(paramObject.getUserName()));
				tvIDNum.setText(CommonUtil.trim(paramObject.getIdNum()));
				tvStudNo.setText(CommonUtil.trim(paramObject.getStudNo()));
				tvPhoneNumber.setText(CommonUtil.trim(paramObject.getMobile()));
				
				
				
				tvLabelOther1.setText(CommonUtil.trim(paramObject.getLabelOther1()));
				tvLabelOther2.setText(CommonUtil.trim(paramObject.getLabelOther2()));
				etValueOther1.setHint(CommonUtil.trim(paramObject.getHintOther1()));
				etValueOther2.setHint(CommonUtil.trim(paramObject.getHintOther2()));
				tvSkuLabel.setText(CommonUtil.trim(paramObject.getSkuLabel()));
				
				if(CommonUtil.trim(paramObject.getLabelOther1()).equals("")){
					tvLabelOther1.setVisibility(View.GONE);
					etValueOther1.setVisibility(View.GONE);
					llOther1.setVisibility(View.GONE);
				}
				if(CommonUtil.trim(paramObject.getLabelOther2()).equals("")){
					tvLabelOther2.setVisibility(View.GONE);
					etValueOther2.setVisibility(View.GONE);
					llOther2.setVisibility(View.GONE);
				}
				
				etRemark.setHint(CommonUtil.trim(paramObject.getRemark()));
				String sex= CommonUtil.trim(paramObject.getSex()) ;
				if(sex.equalsIgnoreCase("女")){
					rbFemale.setChecked(true);
					rbMale.setChecked(false);
				}else{
					rbMale.setChecked(true);
					rbFemale.setChecked(false);
				}
				skuList= paramObject.getSkuList();
				if(skuList!=null&&skuList.size()==1){
					selSkuObject=skuList.get(0); 
					etSkuName.setText(CommonUtil.trim(selSkuObject.getSkuName() ));
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
		btnApply.setOnClickListener(this); 
		tvRight.setOnClickListener(this);
		etSkuName.setOnTouchListener(this);
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
		case R.id.btn_Apply:
			//下单
			String applyTitle = CommonUtil.trim(tvApplyTitle.getText().toString());  
			String userId = CommonUtil.trim(tvUserId.getText().toString()); 
			String userName = CommonUtil.trim(tvUserName.getText().toString()); 
			String idNum = CommonUtil.trim(tvIDNum.getText().toString()); 
			String phoneNumber = CommonUtil.trim(tvPhoneNumber.getText().toString()); 
			String skuLabel = CommonUtil.trim(tvSkuLabel.getText().toString()); 
			String skuName = CommonUtil.trim(etSkuName.getText().toString());  
			
			String labelOther1 = CommonUtil.trim(tvLabelOther1.getText().toString()); 
			String valueOther1 = CommonUtil.trim(etValueOther1.getText().toString());
			
			if(skuName.equalsIgnoreCase("")){
				 DisPlay("error","请选择输入"+skuLabel);
				 etSkuName.requestFocus();
				 return;
			}
			
			if(llOther1.getVisibility()==View.VISIBLE){
				
				if (valueOther1.equals("")) {
					 DisPlay("error","请输入"+labelOther1);
					 etValueOther1.requestFocus();
					 return;
				}
			}
			String labelOther2= CommonUtil.trim(tvLabelOther2.getText().toString()); 
			String valueOther2 = CommonUtil.trim(etValueOther2.getText().toString());
			if(llOther2.getVisibility()==View.VISIBLE){
				
				if (valueOther2.equals("")) {
					 DisPlay("error","请输入"+labelOther2);
					 etValueOther2.requestFocus();
					 return;
				}
			}
			String remark=CommonUtil.trim(etRemark.getText());
			ApplySignPromptDialog dialog = new com.ris.mobile.ecloud.widget.ApplySignPromptDialog.Builder (ApplySignActivity.this)
			.setTitle("报名确认")
			.setApplyTitle(applyTitle)
			.setSkuLabel(skuLabel)
			.setSkuName(skuName)
			.setApplyAmount("")
			.setUserId(userId)
			.setUserName(userName)
			.setIDNum(idNum)
			.setPhoneNumber(phoneNumber)
			.setLabelOther1(labelOther1)
			.setValueOther1(valueOther1)
			.setLabelOther2(labelOther2)
			.setValueOther2(valueOther2)
			.setRemark(remark)
			.setButton1("取消", new ApplySignPromptDialog.OnClickListener() {
				@Override
				public void onClick(Dialog dialog, int which) { 
					dialog.dismiss();
				}
			})
			.setButton2("确认", new ApplySignPromptDialog.OnClickListener() {
				@Override
				public void onClick(Dialog dialog, int which) { 
					goPay(); 
					dialog.dismiss();
					
				}
			}).show(); 
			break;
		

		}
	}
	
	private void goPay(){ 
		String hintOther1=CommonUtil.trim(etValueOther1.getText());
		String hintOther2=CommonUtil.trim(etValueOther2.getText());
		String remark=CommonUtil.trim(etRemark.getText());
		String skuName = CommonUtil.trim(etSkuName.getText().toString()); 
		HashMap<String, String> requestDataMap = new HashMap<String, String>();
		requestDataMap.put("applyId", applyId);
		requestDataMap.put("skuName", skuName); 
		requestDataMap.put("hintOther1", hintOther1);
		requestDataMap.put("hintOther2", hintOther2);
		requestDataMap.put("remark", remark);
		requestDataMap.put("userId", sharedPreferenceUtil.getUserId() );
		
		if(rbMale.isChecked()){
			requestDataMap.put("sex", "男");
		}else{
			requestDataMap.put("sex", "女");
		}
		  
		 
		 
		 
 
		 





		BaseParser<ApplySignOrderObject> applySignOrderParser = new ApplySignOrderParser();

		RequestObject vo = new RequestObject(R.string.url_applysignorder,
				ApplySignActivity.this, requestDataMap, applySignOrderParser);
		getDataFromServer(vo, new DataCallback<ApplySignOrderObject>() {
			@Override
			public void processData(ApplySignOrderObject paramObject,
					boolean paramBoolean) {
				
			 
				 Bundle myBundel=new Bundle(); 
				 myBundel.putString("tradeNo", CommonUtil.trim(paramObject.getDealNumber())); 
				 openActivity(ApplySignResActivity.class,myBundel);
				 ApplySignActivity.this.finish();
				 //myBundel.putString("cardNo",CommonUtil.trim(etICCardNum.getText().toString())); 
				
				 
				
			}

			@Override
			public void processError(ConnectErrorObject responseErrorVo) {
				 
				DisPlay("error", responseErrorVo.getErrInfo());

			}
		});
		
		
		
		
		        

	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (data != null) {
			/**
			 * 获取返回结果，以data中result数据为准
			 */
			 
			if(resultCode==Constant.RESULT_OK){

				 return;
			}
				
				
			String resMsg = data.getStringExtra("result");
			
			
			AlertDialog dialog = new AlertDialog(this)
			.builder()
			.setTitle("支付结果")
			.setMsg(resMsg) 
			.setPositiveButton("确定", new OnClickListener() {
				@Override
				public void onClick(View v) {
					 
				}
			});
			dialog.show();
			 
		}
	}

	/* (non-Javadoc)
	 * @see android.view.View.OnTouchListener#onTouch(android.view.View, android.view.MotionEvent)
	 */
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		if (event.getAction() != MotionEvent.ACTION_UP) {
			return true;
		}
		switch (v.getId()) {
		case R.id.et_SkuName: 
			
			List<ItemObject> itemList=new ArrayList<ItemObject>();
			if(skuList==null||skuList.size()<=1){
				break;
			}
			ItemObject aItemObject=null;
			for(SkuObject aSkuObject:skuList){
				aItemObject=new ItemObject();
				aItemObject.setItemText(aSkuObject.getSkuName());
				aItemObject.setItemValue(aSkuObject.getSkuName());
				itemList.add(aItemObject);
			}
			String skuLabel = CommonUtil.trim(tvSkuLabel.getText().toString()); 
			
			ParDialog mParDialog = new ParDialog(ApplySignActivity.this, "请选择"+skuLabel,itemList,
					itemList.get(0));
			mParDialog.setCanceledOnTouchOutside(false);
			 
			mParDialog.show();
			
			mParDialog.setAddresskListener(new OnAddressCListener() {

				@Override
				public void onClick(ItemObject itemObject) {
					// TODO Auto-generated method stub
					
					
					for(SkuObject aSkuObject:skuList){
						if(aSkuObject.getSkuName().equalsIgnoreCase(itemObject.getItemValue())){
							selSkuObject=aSkuObject;
							 
							etSkuName.setText(CommonUtil.trim(selSkuObject.getSkuName() ));
							break;
						}
						
					}
					 
					
					 
				}
			});
			break;
		}
		return true;
	}

	 
}
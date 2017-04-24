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

import com.bestpay.app.PaymentTask;
import com.bestpay.plugin.Plugin;
import com.ris.mobile.ecloud.BaseActivity;
import com.ris.mobile.ecloud.R;
import com.ris.mobile.ecloud.LApplication; 
import com.ris.mobile.ecloud.BaseActivity.DataCallback;
import com.ris.mobile.ecloud.log.CommonLog;
import com.ris.mobile.ecloud.log.LogFactory;  
import com.ris.mobile.ecloud.object.ConnectErrorObject;
   
import com.ris.mobile.ecloud.object.ApplyPayOrderObject;
import com.ris.mobile.ecloud.object.ApplySPDetailObject; 
import com.ris.mobile.ecloud.object.ApplySPOrderObject;
import com.ris.mobile.ecloud.object.RequestObject; 
import com.ris.mobile.ecloud.object.ResponseObject;
   
import com.ris.mobile.ecloud.parser.ApplyPayOrderParser;
import com.ris.mobile.ecloud.parser.ApplySPOrderParser;
import com.ris.mobile.ecloud.parser.ApplySPResParser;
import com.ris.mobile.ecloud.parser.BaseParser;  
import com.ris.mobile.ecloud.parser.ResponseParser;
import com.ris.mobile.ecloud.util.CommonUtil;
import com.ris.mobile.ecloud.util.Constant;
import com.ris.mobile.ecloud.util.CryptTool;
import com.ris.mobile.ecloud.util.NetUtil;
import com.ris.mobile.ecloud.util.SharedPreferenceUtil;  
import com.ris.mobile.ecloud.widget.AlertDialog;

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

public class ApplySPRedActivity extends BaseActivity implements OnClickListener  {

	 
	
	 
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
	
    @InjectView (R.id.tv_ApplyAmountName) 
	private TextView tvApplyAmountName;
	
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
	PaymentTask task  ;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_applyspred);
		initData(getIntent());
		 
		initView();
		setListener();

	}

	 

	public void initData(Intent intent) {
		sharedPreferenceUtil = new SharedPreferenceUtil(ApplySPRedActivity.this);
		task = new PaymentTask(ApplySPRedActivity.this);
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
		tvSkuLabel.setText("");
		tvSkuName.setText("");
		tvApplyAmountName.setText("");
		tvDealNumber.setText("");
		tvUserName.setText("");
		tvEnterTime.setText("");
		tvApplyTitle.setText("");
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
		 
		
		 
		
		BaseParser<ApplySPDetailObject> applySPResParser = new ApplySPResParser();
		HashMap<String, String> requestDataMap = new HashMap<String, String>();
		requestDataMap.put("userId", sharedPreferenceUtil.getUserId() );
		requestDataMap.put("dealNumber", tradeNo); 
		 
		RequestObject vo = new RequestObject(R.string.url_applyspdetail,
				ApplySPRedActivity.this, requestDataMap, applySPResParser);
		 
		getDataFromServer(vo, new DataCallback<ApplySPDetailObject>() {
			@Override
			public void processData(ApplySPDetailObject paramObject,
					boolean paramBoolean) {
				
				
				tvDealNumber.setText(CommonUtil.trim(paramObject.getDealNumber()));
				tvApplyTitle.setText(CommonUtil.trim(paramObject.getApplyTitle()));
				tvSkuLabel.setText(CommonUtil.trim(paramObject.getSkuLabel()));
				tvSkuName.setText(CommonUtil.trim(paramObject.getSkuName()));
				tvApplyAmountName.setText(CommonUtil.trim(paramObject.getApplyAmountName()));
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
			payDeal();
			break;
		

		}
	}
	private void cancelDeal(){
		BaseParser<ResponseObject> responseParser = new ResponseParser();
		HashMap<String, String> requestDataMap = new HashMap<String, String>();
		requestDataMap.put("userId", sharedPreferenceUtil.getUserId() );
		requestDataMap.put("tradeNo", tradeNo); 
		 
		RequestObject vo = new RequestObject(R.string.url_dealcancel,
				ApplySPRedActivity.this, requestDataMap, responseParser);
		 
		getDataFromServer(vo, new DataCallback<ResponseObject>() {
			@Override
			public void processData(ResponseObject paramObject,
					boolean paramBoolean) {
				
				llBtn.setVisibility(View.GONE);
				DisPlay("info", paramObject.getInfo()); 
				Bundle myBundel=new Bundle();  
				myBundel.putString("tradeNo", ApplySPRedActivity.this.tradeNo);
				ApplySPRedActivity.this.openActivity(
						ApplySPResActivity.class, myBundel);
				ApplySPRedActivity.this.finish();
			}

			@Override
			public void processError(ConnectErrorObject responseErrorVo) {
				 
				DisPlay("error", responseErrorVo.getErrInfo());

			}
		});
	}
	
	private void payDeal(){
		BaseParser<ApplySPOrderObject> applySPOrderParser = new ApplySPOrderParser();
		HashMap<String, String> requestDataMap = new HashMap<String, String>();
		requestDataMap.put("userId", sharedPreferenceUtil.getUserId() );
		requestDataMap.put("tradeNo", tradeNo); 
		 
		RequestObject vo = new RequestObject(R.string.url_dealpay,
				ApplySPRedActivity.this, requestDataMap, applySPOrderParser);
		 
		getDataFromServer(vo, new DataCallback<ApplySPOrderObject>() {
			@Override
			public void processData(ApplySPOrderObject paramObject,
					boolean paramBoolean) {
				
				llBtn.setVisibility(View.GONE);
				
				tradeNo=CommonUtil.trim(paramObject.getOrderReqTranSeq());
				
				String merchantId = Constant.MERCHANTID;
				String orderseq = paramObject.getOrderSeq();
				String orderTranseq =  paramObject.getOrderReqTranSeq() ;
				String orderTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System.currentTimeMillis()));
				String ordervalidityTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System.currentTimeMillis()+ 60* 1000 * 60 * 24));
				
				
				//16个参数；
				StringBuffer md5Buffer = new StringBuffer();
				  md5Buffer.append("SERVICE=").append("mobile.security.pay")
                    .append("&MERCHANTID=").append(merchantId)
                    .append("&MERCHANTPWD=").append(Constant.MERCHANTPWD)
                    .append("&SUBMERCHANTID=").append("")
                    .append("&BACKMERCHANTURL=").append(paramObject.getBackMerchantUrl())
                    .append("&ORDERSEQ=").append(orderseq)
                    .append("&ORDERREQTRANSEQ=").append(orderTranseq)
                    .append("&ORDERTIME=").append(orderTime)
                    .append("&ORDERVALIDITYTIME=").append(ordervalidityTime)
                    .append("&CURTYPE=").append("RMB")
                    .append("&ORDERAMOUNT=").append(paramObject.getOrderAmount())
                    .append("&SUBJECT=").append("报名缴费")
                    .append("&PRODUCTID=").append("04")
                    .append("&PRODUCTDESC=").append(paramObject.getProductDesc())
                    .append("&CUSTOMERID=").append(paramObject.getCustomerId())
                    .append("&SWTICHACC=").append("true")
				    .append("&KEY=").append(Constant.MERCHANTKEY);//添加key是用于key校验改造；
				
				 
				  String sign = null;
				 
				try {
					 sign = CryptTool.md5Digest(md5Buffer.toString());
					 
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				final String paramsStr = "MERCHANTID="+merchantId
						+"&SUBMERCHANTID="+""
						+"&MERCHANTPWD="+Constant.MERCHANTPWD
						+"&ORDERSEQ="+orderseq
						+"&ORDERAMOUNT="+paramObject.getOrderAmount()
						+"&ORDERTIME="+orderTime
						+"&ORDERVALIDITYTIME="+ordervalidityTime
						+"&PRODUCTDESC="+paramObject.getProductDesc()
						+"&CUSTOMERID="+paramObject.getCustomerId()
						+"&PRODUCTAMOUNT="+paramObject.getProductAmount()
						+"&ATTACHAMOUNT=" +"0.00"
						+"&CURTYPE="+ "RMB"
						+"&BACKMERCHANTURL=" +paramObject.getBackMerchantUrl()
						+"&ATTACH=" +""
						+"&PRODUCTID=" +"04"
						+"&USERIP=" +""
						+"&DIVDETAILS=" +""
						
						+"&ACCOUNTID=" +""
						+"&BUSITYPE=" +"04"
						+"&ORDERREQTRANSEQ=" +orderTranseq
						+"&SERVICE=" +"mobile.security.pay"
						+"&SIGNTYPE=" + "MD5"
					
						+"&SIGN="+sign
						+"&SUBJECT="+"报名缴费"
						+"&SWTICHACC="+"true"
						+"&SESSIONKEY="+"asdfasdfahskfjalsdfkajsdfljasdlfjsjfkj"
						+"&OTHERFLOW="+"01"
						+"ACCESSTOKEN＝"+"lajsfsdjfaljdsflajdsfjalkjslaajdlsjfaldjf";
				
				
				task.pay(paramsStr);  
				
				
				 
				 
			}

			@Override
			public void processError(ConnectErrorObject responseErrorVo) {
				 
				DisPlay("error", responseErrorVo.getErrInfo());

			}
		});
	}
	
	/**
	 * 接收支付返回结果
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (data != null) {
			/**
			 * 获取返回结果，以data中result数据为准
			 */
			//2）	resultCode值为Context.RESULT_OK(-1表示成功)时表示受理成功，否则受理失败。
			
			
			if(resultCode==Constant.RESULT_OK){ 
				ApplySPRedActivity.this.showProgressDialog();  
				new Handler().postDelayed(  new Runnable(){  
					   @Override  
					   public void run() { 
						   ApplySPRedActivity.this.closeProgressDialog();
						     Bundle myBundel=new Bundle();  
							 myBundel.putString("tradeNo", ApplySPRedActivity.this.tradeNo); 
							 ApplySPRedActivity.this.openActivity(ApplySPResActivity.class,myBundel);
							 ApplySPRedActivity.this.finish();
					   }   
					} , 3000); // 延迟2秒，再运行splashhandler的run()

				
				 
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
	
	
}
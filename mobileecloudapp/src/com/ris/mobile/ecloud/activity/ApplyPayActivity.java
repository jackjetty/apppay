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
import com.ris.mobile.ecloud.BaseDialog;
import com.ris.mobile.ecloud.R;
import com.ris.mobile.ecloud.LApplication;
import com.ris.mobile.ecloud.BaseActivity.DataCallback;
import com.ris.mobile.ecloud.listener.UpdateApkClickListener;
import com.ris.mobile.ecloud.log.CommonLog;
import com.ris.mobile.ecloud.log.LogFactory; 
import com.ris.mobile.ecloud.object.ApplyPayObject; 
import com.ris.mobile.ecloud.object.ConnectErrorObject;
 
import com.ris.mobile.ecloud.object.ApplyPayOrderObject; 
import com.ris.mobile.ecloud.object.ItemObject;
import com.ris.mobile.ecloud.object.ParObject;
import com.ris.mobile.ecloud.object.RequestObject;
import com.ris.mobile.ecloud.object.ResponseObject;
import com.ris.mobile.ecloud.object.SkuObject;
import com.ris.mobile.ecloud.parser.ApplyPayOrderParser;
import com.ris.mobile.ecloud.parser.ApplyPayParser; 
import com.ris.mobile.ecloud.parser.BaseParser;  
import com.ris.mobile.ecloud.parser.ResponseParser;
import com.ris.mobile.ecloud.util.CommonUtil;
import com.ris.mobile.ecloud.util.Constant;
import com.ris.mobile.ecloud.util.CryptTool;
import com.ris.mobile.ecloud.util.NetUtil;
import com.ris.mobile.ecloud.util.SharedPreferenceUtil;
import com.ris.mobile.ecloud.widget.AlertDialog;
import com.ris.mobile.ecloud.widget.ApplyPayPromptDialog;
import com.ris.mobile.ecloud.widget.ICCardPromptDialog;
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

public class ApplyPayActivity extends BaseActivity implements OnClickListener,OnTouchListener  {

 
	
	
	private String applyId;
	 
	private List<SkuObject> skuList; 
	private SkuObject selSkuObject;
	private String tradeNo;  
	
	@InjectView (R.id.tv_Back) 
	private TextView tvBack;
	
	@InjectView (R.id.tv_Title) 
	private TextView tvTitle;
	
	@InjectView (R.id.tv_Right) 
	private TextView tvRight;
	
	@InjectView (R.id.tv_ApplyTitle) 
	private TextView tvApplyTitle;
	
	@InjectView (R.id.tv_SkuLabel) 
	private TextView tvSkuLabel;
	
	@InjectView (R.id.et_SkuName) 
	private EditText etSkuName;
	
	@InjectView (R.id.tv_ApplyAmount) 
	private TextView tvApplyAmount;
	
	@InjectView (R.id.et_PayNum) 
	private EditText etPayNum;
	
	@InjectView (R.id.et_UserName) 
	private EditText etUserName;
	
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
	
	
	private AlertDialog mAlertDialog;
	 
	 
	public static LApplication lApplication = LApplication.getInstance();
	private SharedPreferenceUtil sharedPreferenceUtil;
	PaymentTask task  ;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_applypay);
		initData(getIntent());
	 
		initView();
		setListener();

	}

	 
	public void initData(Intent intent) {
		sharedPreferenceUtil = new SharedPreferenceUtil(ApplyPayActivity.this);
		task = new PaymentTask(ApplyPayActivity.this);
		applyId = intent.getStringExtra("applyId");  
		applyId=CommonUtil.trim(applyId); 
		 
		skuList=null; 
		selSkuObject=null;

	}

	public void initView() {

		tvBack.setVisibility(View.VISIBLE);
		tvTitle.setVisibility(View.VISIBLE);
		tvTitle.setText(R.string.title_applypay);
		tvRight.setVisibility(View.INVISIBLE); 
		
		
	 
		
		tvApplyTitle.setText("");
		tvSkuLabel.setText("");
		etSkuName.setText("");
		tvApplyAmount.setText("");
		 
		tvLabelOther1.setText("");
		etValueOther1.setText("");
		tvLabelOther2.setText("");
		etValueOther2.setText("");
		
		etRemark.setText("");
		//btnApply.setVisibility(View.VISIBLE); 
		 
		
		BaseParser<ApplyPayObject> applyPayParser = new ApplyPayParser();
		HashMap<String, String> requestDataMap = new HashMap<String, String>();
		requestDataMap.put("userType", new Integer(sharedPreferenceUtil.getUserType()).toString() );
		requestDataMap.put("applyId", applyId);
		 

		RequestObject vo = new RequestObject(R.string.url_applypay,
				ApplyPayActivity.this, requestDataMap, applyPayParser);
		 
		getDataFromServer(vo, new DataCallback<ApplyPayObject>() {
			@Override
			public void processData(ApplyPayObject paramObject,
					boolean paramBoolean) {
				
				//applyAmount=CommonUtil.trim(paramObject.getApplyAmountValue()); 
				tvApplyTitle.setText(CommonUtil.trim(paramObject.getApplyTitle()));
				
				
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
				/* 
				if(paramObject.isSex()==false){
					rbFemale.setChecked(true);
					rbMale.setChecked(false);
				}else{
					rbMale.setChecked(true);
					rbFemale.setChecked(false);
				}*/
				skuList= paramObject.getSkuList();
				if(skuList!=null&&skuList.size()==1){
					selSkuObject=skuList.get(0);
					tvApplyAmount.setText(CommonUtil.trim(selSkuObject.getPrice()));
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
			String applyAmount = CommonUtil.trim(tvApplyAmount.getText().toString()); 
			String payNum = CommonUtil.trim(etPayNum.getText().toString()); 
			String skuLabel = CommonUtil.trim(tvSkuLabel.getText().toString()); 
			String skuName = CommonUtil.trim(etSkuName.getText().toString()); 
			
			if(skuName.equalsIgnoreCase("")){
				 DisPlay("error","请选择输入"+skuLabel);
				 etSkuName.requestFocus();
				 return;
			}
			if (payNum.equals("")) {
				 DisPlay("error","请输入缴费单号");
				 etPayNum.requestFocus();
				 return;
			}
			String userName = CommonUtil.trim(etUserName.getText().toString()); 
			if (userName.equals("")) {
				 DisPlay("error","请输入报名人的姓名");
				 etUserName.requestFocus();
				 return;
			}
			String labelOther1 = CommonUtil.trim(tvLabelOther1.getText().toString()); 
			String valueOther1 = CommonUtil.trim(etValueOther1.getText().toString());
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
			ApplyPayPromptDialog dialog = new com.ris.mobile.ecloud.widget.ApplyPayPromptDialog.Builder (ApplyPayActivity.this)
			.setTitle("缴费支付确认")
			.setApplyTitle(applyTitle)
			.setSkuLabel(skuLabel)
			.setSkuName(skuName)
			.setApplyAmount(applyAmount)
			.setPayNum(payNum)
			.setUserName(userName)
			.setLabelOther1(labelOther1)
			.setValueOther1(valueOther1)
			.setLabelOther2(labelOther2)
			.setValueOther2(valueOther2)
			.setRemark(remark)
			.setButton1("取消", new ApplyPayPromptDialog.OnClickListener() {
				@Override
				public void onClick(Dialog dialog, int which) { 
					dialog.dismiss();
				}
			})
			.setButton2("确认", new ApplyPayPromptDialog.OnClickListener() {
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
		String payNo = CommonUtil.trim(etPayNum.getText().toString());
		String userName = CommonUtil.trim(etUserName.getText().toString()); 
		String skuName = CommonUtil.trim(etSkuName.getText().toString()); 
		String remark=CommonUtil.trim(etRemark.getText());
		HashMap<String, String> requestDataMap = new HashMap<String, String>();
		requestDataMap.put("applyId", applyId);
		requestDataMap.put("skuName", skuName); 
		requestDataMap.put("hintOther1", hintOther1);
		requestDataMap.put("hintOther2", hintOther2);
		
		requestDataMap.put("payNo", payNo);
		requestDataMap.put("userName", userName);
		
		
		
		requestDataMap.put("remark", remark);
		requestDataMap.put("userId", sharedPreferenceUtil.getUserId() );
		requestDataMap.put("sex", "");
		/*if(rbMale.isChecked()){
			requestDataMap.put("sex", "男");
		}else{
			requestDataMap.put("sex", "女");
		}*/ 



		BaseParser<ApplyPayOrderObject> applyPayOrderParser = new ApplyPayOrderParser();

		RequestObject vo = new RequestObject(R.string.url_applypayorder,
				ApplyPayActivity.this, requestDataMap, applyPayOrderParser);
		getDataFromServer(vo, new DataCallback<ApplyPayOrderObject>() {
			@Override
			public void processData(ApplyPayOrderObject paramObject,
					boolean paramBoolean) {
				
				tradeNo=CommonUtil.trim(paramObject.getOrderReqTranSeq());
				
				
				
				
				
				
				//confirmPay(paramObject.getInstruction()) ;
				
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
	
	private void confirmPay(String instruction){
		mAlertDialog=new AlertDialog(ApplyPayActivity.this).builder()
				.setTitle("提示")
				.setCancelable(false) 
		        .setMsg(instruction )
		        .setNegativeButton("取消缴费", new OnClickListener() {
					 	@Override
						public void onClick(View v) {
					 		cancelDeal();
 
						}
					})
				.setPositiveButton("立即缴费", new OnClickListener() {
			 	@Override
				public void onClick(View v) {
			 		 
			 		payDeal();
				  }
			  });
		 
		mAlertDialog.show();
	}
	
	private void cancelDeal(){
		BaseParser<ResponseObject> responseParser = new ResponseParser();
		HashMap<String, String> requestDataMap = new HashMap<String, String>();
		requestDataMap.put("userId", sharedPreferenceUtil.getUserId() );
		requestDataMap.put("tradeNo", tradeNo); 
		 
		RequestObject vo = new RequestObject(R.string.url_dealcancel,
				ApplyPayActivity.this, requestDataMap, responseParser);
		 
		getDataFromServer(vo, new DataCallback<ResponseObject>() {
			@Override
			public void processData(ResponseObject paramObject,
					boolean paramBoolean) {
				 
				DisPlay("info", paramObject.getInfo()); 
				 
				ApplyPayActivity.this.finish();
			}

			@Override
			public void processError(ConnectErrorObject responseErrorVo) {
				 
				DisPlay("error", responseErrorVo.getErrInfo());

			}
		});
	}
	private void payDeal(){
		BaseParser<ApplyPayOrderObject> applyPayOrderParser = new ApplyPayOrderParser();
		HashMap<String, String> requestDataMap = new HashMap<String, String>();
		requestDataMap.put("userId", sharedPreferenceUtil.getUserId() );
		requestDataMap.put("tradeNo", tradeNo); 
		 
		RequestObject vo = new RequestObject(R.string.url_dealpay,
				ApplyPayActivity.this, requestDataMap, applyPayOrderParser);
		 
		getDataFromServer(vo, new DataCallback<ApplyPayOrderObject>() {
			@Override
			public void processData(ApplyPayOrderObject paramObject,
					boolean paramBoolean) {
				
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
				
				ApplyPayActivity.this.showProgressDialog();
				
				
				 
				new Handler().postDelayed(  new Runnable(){  
					   @Override  
					   public void run() { 
						   ApplyPayActivity.this.closeProgressDialog();
						     Bundle myBundel=new Bundle();  
							 myBundel.putString("tradeNo", ApplyPayActivity.this.tradeNo); 
							 ApplyPayActivity.this.openActivity(ApplyPayResActivity.class,myBundel);
							 ApplyPayActivity.this.finish();
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
			
			ParDialog mParDialog = new ParDialog(ApplyPayActivity.this, "请选择"+skuLabel,itemList,
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
							tvApplyAmount.setText(CommonUtil.trim(selSkuObject.getPrice()));
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
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

import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;

import com.bestpay.app.PaymentTask;
import com.bestpay.plugin.Plugin;

import com.ris.mobile.ecloud.BaseActivity;
import com.ris.mobile.ecloud.R;
import com.ris.mobile.ecloud.LApplication;
import com.ris.mobile.ecloud.log.CommonLog;
import com.ris.mobile.ecloud.log.LogFactory;
import com.ris.mobile.ecloud.object.AccountObject;
import com.ris.mobile.ecloud.object.ConnectErrorObject;
import com.ris.mobile.ecloud.object.ICDealInitObject;
import com.ris.mobile.ecloud.object.ICDealOrderObject;
import com.ris.mobile.ecloud.object.ItemObject;
import com.ris.mobile.ecloud.object.ParObject;
import com.ris.mobile.ecloud.object.RequestObject;
import com.ris.mobile.ecloud.object.ResponseObject;
import com.ris.mobile.ecloud.parser.AccountParser;
import com.ris.mobile.ecloud.parser.BaseParser;
import com.ris.mobile.ecloud.parser.ICDealInitParser;
import com.ris.mobile.ecloud.parser.ICDealOrderParser;
import com.ris.mobile.ecloud.parser.ResponseParser;
import com.ris.mobile.ecloud.util.CommonUtil;
import com.ris.mobile.ecloud.util.Constant;
import com.ris.mobile.ecloud.util.CryptTool;
import com.ris.mobile.ecloud.util.NetUtil;
import com.ris.mobile.ecloud.util.SharedPreferenceUtil;
import com.ris.mobile.ecloud.widget.AlertDialog;
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
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.text.Editable;
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
import android.widget.CompoundButton; 
public class ICCardActivity extends BaseActivity implements OnClickListener,
		OnTouchListener,CompoundButton.OnCheckedChangeListener {

 
	
	@InjectView (R.id.tv_Back)
	private TextView tvBack;
	
	@InjectView (R.id.tv_Title)
	private TextView tvTitle;
	
	@InjectView (R.id.tv_Right)
	private TextView tvRight;
	
	@InjectView (R.id.et_ICCardNum)
	private EditText etICCardNum;
	
	@InjectView (R.id.et_AccountName)
	private EditText etAccountName;
	
	@InjectView (R.id.cb_ObjPhone)
	private CheckBox cbObjPhone;
	
	@InjectView (R.id.cb_ObjCard)
	private CheckBox cbObjCard;
	
	@InjectView (R.id.cb_Agree) 
	private CheckBox cbAgree;
	
	@InjectView (R.id.tv_Prompt)
	private TextView tvPrompt;
	
	@InjectView (R.id.tv_RechargeInstruction)
	private TextView tvRechargeInstruction;
	
	@InjectView (R.id.et_Amount)
	private EditText etAmount;
	
	@InjectView (R.id.btn_Recharge)
	private Button btnRecharge;
	
	private String tradeNo;
	private List<ItemObject> itemList;
	private ItemObject aItemObject;
	public static LApplication lApplication = LApplication.getInstance();
	private SharedPreferenceUtil sharedPreferenceUtil;
	
	
	 
	PaymentTask task  ;
	 

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_iccard);
		initData(getIntent()); 
		initView();
		setListener();

	}

	 
	
	/** 
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素) 
     */  
    public static int dip2px(Context context, float dpValue) {  
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (dpValue * scale + 0.5f);  
    }  
/*
	// 获取点击事件
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View view = getCurrentFocus();
            if (isHideInput(view, ev)) {
                HideSoftInput(view.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }
    // 判定是否需要隐藏
    private boolean isHideInput(View v, MotionEvent ev) {
        if (v != null && (v instanceof EditText)) {
            int[] l = { 0, 0 };
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left
                    + v.getWidth();
            if (ev.getX() > left && ev.getX() < right && ev.getY() > top
                    && ev.getY() < bottom) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
    // 隐藏软键盘
    private void HideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(token,
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }*/
	
	
	public void initData(Intent intent) {
		sharedPreferenceUtil = new SharedPreferenceUtil(ICCardActivity.this);
		task = new PaymentTask(ICCardActivity.this);
		List<ParObject> parList=sharedPreferenceUtil.getParList();
		ItemObject itemObject;
		itemList = new ArrayList<ItemObject>();
		for (ParObject mParObject : parList) {
			itemObject = new ItemObject();
			itemObject.setItemText(mParObject.getParName());
			itemObject.setItemValue(mParObject.getParCode());
			itemList.add(itemObject);
		}
		aItemObject = itemList.get(0);

	}

	public void initView() {

		tvBack.setVisibility(View.VISIBLE);
		tvTitle.setVisibility(View.VISIBLE);
		tvTitle.setText(R.string.title_iccard);
		tvRight.setVisibility(View.INVISIBLE);
		cbAgree.setChecked(sharedPreferenceUtil.getICAgree());
		cbObjPhone.setChecked(true);
		cbObjCard.setChecked(false);
		if(sharedPreferenceUtil.getUserType()!=Constant.USERTYPE_OUT ){
			etAccountName.setText(sharedPreferenceUtil.getUserName());
			etICCardNum.setText(sharedPreferenceUtil.getCardNo() );
			 
		}
		tvPrompt.setText("");
		btnRecharge.setEnabled(true);
		//textView.setCompoundDrawablePadding(dip2px(5)) ; 
		if(cbObjPhone.getPaddingLeft()==0){ 
	    	cbObjPhone.setPadding(dip2px(this,5.0f), cbObjPhone.getPaddingTop(), cbObjPhone.getPaddingRight(), cbObjPhone.getPaddingBottom());
			cbObjCard.setPadding(dip2px(this,5.0f), cbObjCard.getPaddingTop(), cbObjCard.getPaddingRight(), cbObjCard.getPaddingBottom());
	    }else{
	    	cbObjPhone.setPadding(dip2px(this,28.0f), cbObjPhone.getPaddingTop(), cbObjPhone.getPaddingRight(), cbObjPhone.getPaddingBottom());
			cbObjCard.setPadding(dip2px(this,28.0f), cbObjCard.getPaddingTop(), cbObjCard.getPaddingRight(), cbObjCard.getPaddingBottom());
	    }
		
		HashMap<String, String> requestDataMap = new HashMap<String, String>(); 
		BaseParser<ICDealInitObject> icDealInitParser = new ICDealInitParser();

		RequestObject vo = new RequestObject(R.string.url_icdealinit,
				ICCardActivity.this, requestDataMap, icDealInitParser);
		getDataFromServer(vo, new DataCallback<ICDealInitObject>() {
			@Override
			public void processData(ICDealInitObject paramObject,
					boolean paramBoolean) {
				tvPrompt.setText(paramObject.getPrompt());
				btnRecharge.setEnabled( paramObject.isApplyAble());
				if(paramObject.isApplyAble()){
					tvPrompt.setTextColor( getResources().getColor(R.color.silver) ) ;
					 
					 
				}else{
					tvPrompt.setTextColor(getResources().getColor(R.color.text_red)) ;
				}

			}

			@Override
			public void processError(ConnectErrorObject responseErrorVo) {
				tvPrompt.setText(""); 
				btnRecharge.setEnabled(false);
				DisPlay("error", responseErrorVo.getErrInfo());

			}
		});
		
	}

	public void setListener() {
		tvBack.setOnClickListener(this);
		etAmount.setOnTouchListener(this);
		btnRecharge.setOnClickListener(this); 
		tvRechargeInstruction.setOnClickListener(this); 
		cbObjPhone.setOnClickListener(this);
		cbObjCard.setOnClickListener(this);
		//cbObjCard.setOnCheckedChangeListener(this); 
		/*
		etICCardNum
				.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {

					@Override
					public void onFocusChange(View v, boolean hasFocus) {

						if (hasFocus) {

							// 此处为得到焦点时的处理内容

						} else {

							getAccountInfo();

						}

					}

				});
		
		llICCard.setOnTouchListener(new OnTouchListener() {
            
            public boolean onTouch(View v, MotionEvent event) {
            	if (event.getAction() != MotionEvent.ACTION_UP) {
        			return true;
        		} 
            	llICCard.setFocusable(true);
            	llICCard.setFocusableInTouchMode(true);
            	llICCard.requestFocus();
            	((InputMethodManager)getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(llICCard.getWindowToken(), 0);; 
                 return false;
            }
        });*/

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
			ICCardActivity.this.finish();
			break;
		case R.id.cb_ObjPhone:
			cbObjPhone.setChecked(true);
			cbObjCard.setChecked(false);
			break;
		case R.id.cb_ObjCard:
			cbObjPhone.setChecked(false);
			cbObjCard.setChecked(true);
			break;
		case R.id.btn_Recharge:
			String accountId = CommonUtil.trim(etICCardNum.getText().toString()); 
			if (accountId.equals("")) {
				 DisPlay("error","请输入一卡通卡号");
				 etICCardNum.requestFocus();
				 return;
			}
			String accountName= CommonUtil.trim(etAccountName.getText().toString()); 
			if (accountName.equals("")) {
				 DisPlay("error","请输入持卡人姓名");
				 etICCardNum.requestFocus();
				 return;
			}
			String amount=CommonUtil.trim(etAmount.getText().toString()); 
			if (amount.equals("")) {
				 DisPlay("error","请选择充值金额"); 
				 return;
			}
			if(!cbAgree.isChecked()){
				  DisPlay("error","请同意服务协议！！"); 
				  return;
				  
			  }
			String chargeObj=CommonUtil.trim(cbObjPhone.getText().toString()); 
			if(!cbObjPhone.isChecked()){
				chargeObj=CommonUtil.trim(cbObjCard.getText().toString()); 
			}
			 
		 
			ICCardPromptDialog dialog = new com.ris.mobile.ecloud.widget.ICCardPromptDialog.Builder (ICCardActivity.this)
			.setTitle("一卡通充值确认")
			.setAccountName(accountName)
			.setICCardNum(accountId)
			.setParName(amount)
			.setChargeObj(chargeObj)
			.setButton1("取消", new ICCardPromptDialog.OnClickListener() {
				@Override
				public void onClick(Dialog dialog, int which) { 
					
					dialog.dismiss();
				}
			})
			.setButton2("确认", new ICCardPromptDialog.OnClickListener() {
				@Override
				public void onClick(Dialog dialog, int which) {
					
					
					 
					goPay();
					dialog.dismiss();
				}
			}).show();
			break;
		case R.id.tv_RechargeInstruction:
			 Bundle myBundel=new Bundle(); 
			 myBundel.putString("wapUrl", sharedPreferenceUtil.getRechargeAgreeUrl()); 
			 myBundel.putString("wapTitle","充值说明"); 
			 openActivity(WebViewActivity.class,myBundel);
			 break;
		}
	}
	
	
	private void goPay(){
		String accountId = CommonUtil.trim(etICCardNum.getText().toString()); 
		String accountName = CommonUtil.trim(etAccountName.getText().toString()); 
		String parCode=CommonUtil.trim(aItemObject.getItemValue());
		
		String cardType=Constant.IC_TYPE_CARD;
		if(cbObjPhone.isChecked()){
			cardType=Constant.IC_TYPE_PHONE;
		}
		
		
		HashMap<String, String> requestDataMap = new HashMap<String, String>();
		
		
		
		
		requestDataMap.put("cardNo", accountId);
		requestDataMap.put("parCode", parCode);
		requestDataMap.put("cardHolder", accountName);
		requestDataMap.put("userId", sharedPreferenceUtil.getUserId() );
		requestDataMap.put("cardType",cardType);
		 


		BaseParser<ICDealOrderObject> icDealOrderParser = new ICDealOrderParser();

		RequestObject vo = new RequestObject(R.string.url_icdealorder,
				ICCardActivity.this, requestDataMap, icDealOrderParser);
		getDataFromServer(vo, new DataCallback<ICDealOrderObject>() {
			@Override
			public void processData(ICDealOrderObject paramObject,
					boolean paramBoolean) {
				 
				 
				String merchantId = Constant.MERCHANTID;
				String orderseq = paramObject.getOrderSeq();
				String orderTranseq =  paramObject.getOrderReqTranSeq() ;
				String orderTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System.currentTimeMillis()));
				String ordervalidityTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System.currentTimeMillis()+ 60* 1000 * 60 * 24));
				tradeNo=CommonUtil.trim(paramObject.getOrderReqTranSeq());
				
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
                    .append("&SUBJECT=").append("一卡通")
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
			 
				
//				String mac = "MERCHANTID="+ merchantId
//						+ "&ORDERSEQ="+ orderseq
//						+ "&ORDERREQTRNSEQ="+ orderTranseq
//						+ "&ORDERTIME="+ orderTime
//						+ "&KEY=" + TestConstant.CKEY;
//
//				try {
//					mac = CryptTool.md5Digest(mac);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
				
				//CommonUtil.trim(NetUtil.getLocalIpAddress(ICCardActivity.this))
				//29+1《类似session的字段》个参数；
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
						+"&SUBJECT="+"一卡通"
						+"&SWTICHACC="+"true"
						+"&SESSIONKEY="+"asdfasdfahskfjalsdfkajsdfljasdlfjsjfkj"
						+"&OTHERFLOW="+"01"
						+"ACCESSTOKEN＝"+"" ;
				
				//+"tid＝"+""
				//+"key_index＝"+""
				//+"key_tid＝"+""
//				SIGN//上传后台参数值；
//APPID//无此参数；
//APPENV//无此参数；
//				NOTIFYURL //BACKMERCHANTURL后台通知地址；
//				ORDERAMT //ORDERAMOUNT 支付订单金额；
//				SUBJECT  //商品简称；
//SWTICHACC//大厅需要，本地判断，与后台没关系；true表示大厅进入可切换账户；
//EXTERNTOKEN//无此参数；
//SDKVERSIONCODE//sdk中固定写死；
//				        SESSIONKEY//大厅中使用； 
				
				task.pay(paramsStr);   
				
				//++++++++++++++++++++++插件
				/*
				Hashtable<String, String>paramsHashtable = new Hashtable<String, String>();
				// 商户 ID，必填
				paramsHashtable.put(Plugin.MERCHANTID, Constant.MERCHANTID);
				// 子商户 ID，选填
				paramsHashtable.put(Plugin.SUBMERCHANTID, "");
				// 交易 key,选填
				paramsHashtable.put(Plugin.MERCHANTPWD, Constant.MERCHANTPWD);
				 
				// 订单编号，必填
				paramsHashtable.put(Plugin.ORDERSEQ,
						paramObject.getOrderSeq());
				
				paramsHashtable.put(Plugin.ORDERREQTRANSEQ, paramObject.getOrderReqTranSeq() );
				tradeNo=CommonUtil.trim(paramObject.getOrderReqTranSeq());
				// 订单金额，单位元，小数点后取两位订单金额= 产品金额 + 附加金额，必填
				paramsHashtable.put(Plugin.ORDERAMOUNT, paramObject.getOrderAmount());
				//
				// 产品金额，单位元，小数点后取两位，必填
				paramsHashtable.put(Plugin.PRODUCTAMOUNT, paramObject.getProductAmount());
				//
				// 附加金额，单位元，小数点后取两位，必填
				paramsHashtable.put(Plugin.ATTACHAMOUNT, "0.00");
				// 订单时间，格式yyyyMMddhhmmss，必填
				
				
				//"backMerchantUrl":"http://121.40.149.140:9999/rising-cc//recharge_bestpay_notify","orderAmount":"2000","productAmount":"2000","customerId":"18608672404","productDesc":"校园IC卡充值20.00元（卡号：1000010102）","orderReqTranSeq":"C160408132719035703","orderSeq":"C160408132719035703"
				paramsHashtable.put(Plugin.ORDERTIME, new SimpleDateFormat( "yyyyMMddhhmmss").format(new Date(System.currentTimeMillis())));
				// 订单有效时间，格式yyyyMMddhhmmss,选填
				paramsHashtable.put(Plugin.ORDERVALIDITYTIME,
				new SimpleDateFormat("yyyyMMddhhmmss").format( new Date(System.currentTimeMillis() + 60 * 1000 * 60 * 24)));
				// 产品名称，必填
				paramsHashtable.put(Plugin.PRODUCTDESC, paramObject.getProductDesc());
				// 用户 ID，必填
				paramsHashtable.put(Plugin.CUSTOMERID, paramObject.getCustomerId());
				// 币种，固定填“RMB”，必填
				paramsHashtable.put(Plugin.CURTYPE, "RMB");
				paramsHashtable.put(Plugin.KEY,
						Constant.MERCHANTKEY);
				// 后台通知地址，必填
				paramsHashtable.put(Plugin.BACKMERCHANTURL,
						paramObject.getBackMerchantUrl());
				// 附加信息，选填
				paramsHashtable.put(Plugin.ATTACH, "");
				// 产品 ID，选填
				paramsHashtable.put(Plugin.PRODUCTID, "01");
				// 用户 IP，选填
				paramsHashtable.put(Plugin.USERIP, CommonUtil.trim(NetUtil.getLocalIpAddress(ICCardActivity.this)));
				// 分账明细，选填
				paramsHashtable.put(Plugin.DIVDETAILS, "");
				
				//业务类型，必填
				paramsHashtable.put(Plugin.BUSITYPE, "04");
				 
				//MAC加密串，必填
				paramsHashtable.put(Plugin.MAC, paramObject.getMac());
				//调用pay，启动支付插件
				Plugin.pay(ICCardActivity.this, paramsHashtable); */
				
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
				
				ICCardActivity.this.showProgressDialog();
				
				
				 
				new Handler().postDelayed(  new Runnable(){  
					   @Override  
					   public void run() { 
						    ICCardActivity.this.closeProgressDialog();
						    Bundle myBundel=new Bundle(); 
							 myBundel.putString("tradeNo", tradeNo); 
							 myBundel.putString("cardNo",CommonUtil.trim(etICCardNum.getText().toString())); 
							 openActivity(ICCardResActivity.class,myBundel);
							 ICCardActivity.this.finish();
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
		case R.id.et_Amount: 
			ParDialog mParDialog = new ParDialog(ICCardActivity.this,"选择充值金额", itemList,
					aItemObject);
			mParDialog.setCanceledOnTouchOutside(false);
			 
			mParDialog.show();
			
			mParDialog.setAddresskListener(new OnAddressCListener() {

				@Override
				public void onClick(ItemObject itemObject) {
					// TODO Auto-generated method stub
					aItemObject = itemObject;
					etAmount.setText(aItemObject.getItemText());
				}
			});
			break;
		}

		return true;
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		
		 
		/*
		switch (buttonView.getId()) {
		
		case R.id.cb_ObjPhone:
			 if(!isChecked){
				 cbObjPhone.setChecked(true);
				
			 }  
			 cbObjCard.setChecked(false);
			//cbObjPhone.setChecked(true);
			//if (isChecked) { 
				//cbObjCard.setChecked(false);
	        //  } 
	        //  else { 
				
	         // } 
			break;
			
		case R.id.cb_ObjCard:
			 if(!isChecked){
				
				 cbObjCard.setChecked(true);
			 } 
			 cbObjPhone.setChecked(false);
			// cbObjCard.setChecked(true);
			// cbObjPhone.setChecked(false);
			// System.out.println(isChecked); 
			 
			break;
		default:
			break;
			
		}*/
			
		    
		
	}
}
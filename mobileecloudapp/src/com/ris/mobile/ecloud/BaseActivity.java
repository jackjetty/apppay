package com.ris.mobile.ecloud;
 

import java.util.List;
import java.util.Vector;

import roboguice.activity.RoboActivity;

import com.ris.mobile.ecloud.LApplication;
import com.ris.mobile.ecloud.activity.AboutActivity;
import com.ris.mobile.ecloud.activity.ICCardActivity;
import com.ris.mobile.ecloud.activity.LoginActivity;
import com.ris.mobile.ecloud.log.CommonLog;
import com.ris.mobile.ecloud.log.LogFactory;
import com.ris.mobile.ecloud.object.ConnectErrorObject;
import com.ris.mobile.ecloud.object.RequestObject;
import com.ris.mobile.ecloud.util.CommonUtil;
import com.ris.mobile.ecloud.util.Constant;
import com.ris.mobile.ecloud.util.NetUtil;
import com.ris.mobile.ecloud.util.ThreadPoolManager;
import com.ris.mobile.ecloud.widget.AlertDialog;
import com.ris.mobile.ecloud.widget.LoadingDialog;
import com.umeng.analytics.MobclickAgent;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast; 
 
import android.app.Activity;
import android.os.Bundle; 

public abstract class BaseActivity extends RoboActivity { 
	protected  static   String TAG = BaseActivity.class.getSimpleName(); ; 
	protected  static   CommonLog log = LogFactory.createLog(TAG);
	 
	private ThreadPoolManager threadPoolManager;
    private List<BaseTask> record = new Vector<BaseTask>();
    protected LoadingDialog progressDialog;
    
    
    public BaseActivity() {
		threadPoolManager = ThreadPoolManager.getInstance();
		TAG=this.getClass().getSimpleName();
		log = LogFactory.createLog(TAG);
	} 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getInstance().addActivity(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE); 
        LApplication.onCatchError(this);
    } 
    @Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		LApplication.onPause(this);
		MobclickAgent.onPause(this);
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
	}

	
	
	 
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		LApplication.onResume(this);
		MobclickAgent.onResume(this);
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	 
	//protected abstract void findViewById();
 
	protected abstract void initView(); 
	
	
	protected abstract void initData(Intent intent);
	
	
	
	protected abstract void setListener();

	 
	protected void openActivity(Class<?> pClass) {
		openActivity(pClass, null);
	}

 
	protected void openActivity(Class<?> pClass, Bundle pBundle) {
		Intent intent = new Intent(this, pClass);
		if (pBundle != null) {
			intent.putExtras(pBundle);
		}
		startActivity(intent);
	}
 
	protected void openActivity(String pAction) {
		openActivity(pAction, null);
	}

	 
	protected void openActivity(String pAction, Bundle pBundle) {
		Intent intent = new Intent(pAction);
		if (pBundle != null) {
			intent.putExtras(pBundle);
		}
		startActivity(intent);
	}
	
	protected void DisPlay(String tipType,String content){
    	
  	  LayoutInflater inflater = getLayoutInflater();
  	   View layout = inflater.inflate(R.layout.toast,
  	     (ViewGroup) findViewById(R.id.toast_layout)); 
  	   ImageView image = (ImageView) layout
  	     .findViewById(R.id.iv_Toast);
  	   image.setImageResource(R.drawable.hub_info);
  	   if(tipType.equalsIgnoreCase("error"))
  		   image.setImageResource(R.drawable.hub_error);  
  	   if(tipType.equalsIgnoreCase("correct"))
  		   image.setImageResource(R.drawable.hub_correct);  
  	   TextView text = (TextView) layout.findViewById(R.id.tv_Toast);
  	   text.setText(content);
  	   Toast toast = new Toast(getApplicationContext());
  	   toast.setGravity(Gravity.CENTER, 0, 0);  
  	   toast.setDuration(Toast.LENGTH_SHORT);
  	   toast.setView(layout);
  	   toast.show();
		 
  	 
	}
	
	
	 @SuppressWarnings("unchecked")
		class BaseHandler extends Handler {
	 		private Context context;
			private DataCallback callBack;
			private RequestObject reqVo;

			public BaseHandler(Context context, DataCallback callBack, RequestObject reqVo) {
				this.context = context;
				this.callBack = callBack;
				this.reqVo = reqVo;
			}

			public void handleMessage(Message msg) {
				closeProgressDialog();
				if(context==null){
					return;
				}
				if (msg.what == Constant.SUCCESS) { 
					if (msg.obj == null) {
						AlertDialog dialog = new AlertDialog(context)
						.builder()
						.setTitle(getString(R.string.net_error))
						.setPositiveButton("确定", new OnClickListener() {
							@Override
							public void onClick(View v) {
								if(TAG.equalsIgnoreCase("LaunchActivity")){
									BaseActivity.this.finish();
								}
								 
							}
						});
						dialog.show();
					    return ;
					} 
					if(msg.obj instanceof ConnectErrorObject ){
						
						ConnectErrorObject connectErrorObject=(ConnectErrorObject)msg.obj;
						/*
						if(connectErrorObject.getErrCode()==-3){
							AlertDialog dialog = new AlertDialog(context)
							.builder()
							.setTitle( connectErrorObject.getErrInfo())
							.setPositiveButton("确定", new OnClickListener() {
								@Override
								public void onClick(View v) {
									openActivity(LoginActivity.class ); 
									 
								}
							});
							dialog.show();
						    return ;
						}
						*/
						if(connectErrorObject.getErrCode()==-3){
							DisPlay("error", connectErrorObject.getErrInfo());
							BaseActivity.this.finish();
							openActivity(LoginActivity.class ); 
						    return ;
						} 
						
						callBack.processError((ConnectErrorObject)msg.obj);
						return;
					}
	                  
					callBack.processData(msg.obj, true);
					 
				} else
				if (msg.what == Constant.NET_FAILED) {
					 
					
					AlertDialog dialog = new AlertDialog(context)
					.builder()
					.setTitle(getString(R.string.net_error))
					.setPositiveButton("确定", new OnClickListener() {
						@Override
						public void onClick(View v) {
							/*if(android.os.Build.VERSION.SDK_INT > 13 ){     
								//3.2以上打开设置界面，也可以直接用ACTION_WIRELESS_SETTINGS打开到wifi界面  
								startActivity(new Intent(android.provider.Settings.ACTION_SETTINGS));
								} else
								{    
									startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
									}	*/ 
							if(TAG.equalsIgnoreCase("LaunchActivity")){
								BaseActivity.this.finish();
							}
						 

						 
						}
					});
					dialog.show();
					
				}
				
				 
			}
		}
	 
	class BaseTask implements Runnable {
		private Context context;
		private RequestObject reqVo;
		private Handler handler;

		public BaseTask(Context context, RequestObject reqVo, Handler handler) {
			this.context = context;
			this.reqVo = reqVo;
			this.handler = handler;
		}

		@Override
		public void run() {
			Object obj = null;
			Message msg = Message.obtain();
			try {
				if (NetUtil.hasNetwork(context)) {
					obj = NetUtil.post(reqVo); 
					msg.what = Constant.SUCCESS;
					msg.obj = obj;
					handler.sendMessage(msg);
					record.remove(this);
					 
				} else {
					msg.what = Constant.NET_FAILED;
					msg.obj = obj;
					handler.sendMessage(msg);
					record.remove(this);
				}
			} catch (Exception e) {
				record.remove(this);
				throw new RuntimeException(e);
			}
		}

	}
	public abstract interface DataCallback<T> {
		public abstract void processData(T paramObject, boolean paramBoolean);
		public abstract void processError(ConnectErrorObject responseErrorVo);
	}
	/**
	 * 从服务器上获取数据，并回调处理
	 * 
	 * @param reqVo
	 * @param callBack
	 */
	
	public void getDataFromServer(RequestObject reqVo, DataCallback callBack,boolean showDialog) {
		if(showDialog)
			  showProgressDialog();
		BaseHandler handler = new BaseHandler(this, callBack, reqVo);
		BaseTask taskThread = new BaseTask(this, reqVo, handler);
		record.add(taskThread);
		this.threadPoolManager.addTask(taskThread);
	}
	
	public void getDataFromServer(RequestObject reqVo, DataCallback callBack) {
		showProgressDialog();
		BaseHandler handler = new BaseHandler(this, callBack, reqVo);
		BaseTask taskThread = new BaseTask(this, reqVo, handler);
		record.add(taskThread);
		this.threadPoolManager.addTask(taskThread);
	}
	/**
	 * 显示提示框
	 */
	protected void showProgressDialog() {
		if ((!isFinishing()) && (this.progressDialog == null)) {
			this.progressDialog = new LoadingDialog(this,"加载中...");
		}  
		this.progressDialog.show();
	}
	
	/**
	 * 关闭提示框
	 */
	protected void closeProgressDialog() {
		if (this.progressDialog != null)
			this.progressDialog.dismiss();
	}

}
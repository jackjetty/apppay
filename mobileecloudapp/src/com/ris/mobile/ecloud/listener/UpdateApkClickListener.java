package com.ris.mobile.ecloud.listener;
  

import java.io.File;

import com.ris.mobile.ecloud.BaseDialog;
import com.ris.mobile.ecloud.R;
import com.ris.mobile.ecloud.LApplication;
import com.ris.mobile.ecloud.engine.DownLoadManager;
import com.ris.mobile.ecloud.engine.DownLoadTask;
import com.ris.mobile.ecloud.util.ThreadPoolManager;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


 

public class UpdateApkClickListener  implements   DownLoadManager.DownlaodListener{
	/** 进度条当前的值 */
	/** 提示用户更新 */
	private static final int SHOW_UPDATE_DIALOG = 11;
	/** 下载失败 */
	private static final int DOWN_ERROR = 12; 
	private static final int DOWN_UPDATE = 13; 
	private ProgressBar pbDown;
	private TextView tvPersent;
	private TextView tvSize;
	private int progressVaue=0; 
	private  File file;
	private DownLoadTask downLoadTask;
	private boolean flag=true;
	private BaseDialog baseDialog;
	private Activity activity;
	private String apkUrl;
	public UpdateApkClickListener(Activity activity,BaseDialog baseDialog,String apkUrl){
		this.activity=activity;
		this.baseDialog=baseDialog;
		this.apkUrl=apkUrl;
		updateApk( );
	}
	
	
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case DOWN_ERROR:
				baseDialog.dismiss();
				Toast.makeText(activity, R.string.down_error, Toast.LENGTH_SHORT).show(); 
				break;
			case DOWN_UPDATE: 
				 int persent=100*msg.arg1/msg.arg2;
				 pbDown.setProgress(persent);
				 tvPersent.setText(persent+"%");
				 tvSize.setText(msg.arg1+"/"+msg.arg2);
				 break; 
			default:
				break;
			}
		};
	};
	
	
	public void updateApk( ) {
		baseDialog.setTitle("下载中...");  
		baseDialog.setDialogContentView(R.layout.download_layout); 
		baseDialog.setButton1(null, null);
		baseDialog.setButton2(null, null); 
		pbDown = (ProgressBar)baseDialog.findViewById(R.id.pb_Down); 
		pbDown.setMax(100);
		pbDown.setProgress(0);
		tvPersent = (TextView)baseDialog.findViewById(R.id.tv_Persent); 
		tvSize = (TextView)baseDialog.findViewById(R.id.tv_Size); 
		 
		tvSize.setText("");
		baseDialog.setCancelable(false);
		baseDialog.show();
		
		 
		 
		//开始线程
		file = new File(LApplication.getCacheDirPath(), "mobileecloudapp.apk"); 
 
		new Thread(){  
	        @Override  
	        public void run() {  
	            try {  
	                 DownLoadManager.getFileFromServer(apkUrl,new File(LApplication.getCacheDirPath(), "mobileecloudapp.apk"), UpdateApkClickListener.this);  
	               
	                //pd.dismiss(); //结束掉进度条对话框  
	            } catch (Exception e) {  
	                Message msg = new Message();  
	                msg.what = DOWN_ERROR;  
	                handler.sendMessage(msg);  
	                e.printStackTrace();  
	            }  
	        }}.start();  
		//downLoadTask = new DownLoadTask(apkUrl, file.getAbsolutePath(), 5);
		//downLoadTask.setListener(this);
		//ThreadPoolManager.getInstance().addTask(downLoadTask); 
	}
	/**
	 * 安装Apk
	 */
	private void installApk() {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
		activity.startActivity(intent);
		activity.finish();
	}

	@Override
	public void update(int total, int len) { 
		progressVaue += len;
		Message message=new Message();
		message.what=DOWN_UPDATE;
		message.arg1=progressVaue;
		message.arg2=total; 
		handler.sendMessage(message);
		 
	}

	@Override
	public void downLoadFinish() {
		baseDialog.dismiss();
		//if (totalSucess == 5)
			installApk();
		
	}

	@Override
	public void downLoadError() {
		 Message.obtain(handler, DOWN_ERROR).sendToTarget();
	}

}
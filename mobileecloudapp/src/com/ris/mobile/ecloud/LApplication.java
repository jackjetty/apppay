package com.ris.mobile.ecloud; 
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap; 
import java.util.List;
 

import android.app.Activity;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;  
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Environment; 



import android.os.IBinder; 
import com.ris.mobile.ecloud.log.CommonLog;
import com.ris.mobile.ecloud.log.LogFactory; 
import com.ris.mobile.ecloud.util.StreamUtil;
 
public class LApplication extends  Application   {

	private static final String TAG = "LApplication";
	private static final CommonLog log = LogFactory.createLog(TAG);
	
	 
	private static String cacheDir;
	private List<Activity> records = new ArrayList<Activity>(); 
	private static LApplication mInstance;  
	
	 
	private boolean isLogin = false; 
	public synchronized static LApplication getInstance(){
		return mInstance;
	}
	
	 
	
	private void initCacheDirPath() {
		File f;
		 
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			f = new File(Environment.getExternalStorageDirectory() + "/ecloud/");
			if (!f.exists()) {
				f.mkdirs();
			}
 		} else {
			f = getApplicationContext().getCacheDir();
		}
		cacheDir = f.getAbsolutePath();  
	}
	
	public static String getCacheDirPath() { 
		return cacheDir;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		log.e("LApplication  onCreate!!!");
		mInstance = this;
		 
		initCacheDirPath();
  
		 
	} 
	 
	public void setLoginStatus(boolean flag){
		isLogin = flag;
	} 
	public boolean getLoginStatus(){
	
		return isLogin;
	}
	
	
	 
	 
	
	
	
	
	 

	public void addActvity(Activity activity) {
		records.add(activity);
		log.d(  "Current Acitvity Size :" + getCurrentActivitySize());
	}
	
	public void removeActvity(Activity activity) {
		records.remove(activity);
		log.d(  "Current Acitvity Size :" + getCurrentActivitySize());
	} 
	public void exit() {
		for (Activity activity : records) {
			activity.finish();
		}
 	}
	
	public int getCurrentActivitySize() {
		return records.size();
 	}
	
	public static void onPause(Activity context){
		 
	}
	
	public static void onResume(Activity context){
		 
	}
	
	public static void onCatchError(Context context){
		 
	}
}
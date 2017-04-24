 
package com.ris.mobile.ecloud.engine;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.ris.mobile.ecloud.engine.DownLoadTask.DownlaodListener;

import android.app.ProgressDialog;
import android.os.Environment;
public class DownLoadManager {
	public static void getFileFromServer(String path,  File file, DownlaodListener listener) {
		//如果相等的话表示当前的sdcard挂载在手机上并且是可用的
		InputStream is=null;
		FileOutputStream fos=null;
		BufferedInputStream bis =null;
		try{
			URL url = new URL(path);
			HttpURLConnection conn =  (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			//获取到文件的大小 
			int size = conn.getContentLength();
			is = conn.getInputStream();

			 fos = new FileOutputStream(file);
			 bis = new BufferedInputStream(is);
			byte[] buffer = new byte[1024];
			int len ;
			int total=0;
			while((len =bis.read(buffer))!=-1){
				fos.write(buffer, 0, len);
				total+= len;
				//获取当前下载量
				listener.update( size,  len);
			}
			listener.downLoadFinish();
			
		}catch(Exception ex){
			ex.printStackTrace();
			listener.downLoadError();
			
		}finally{
			try {
				if(fos!=null)
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if(bis!=null)
				bis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if(is!=null)
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
		
	}
	
	public static interface DownlaodListener {
		void update(int total, int len);

		void downLoadFinish();

		void downLoadError( );
	}
}
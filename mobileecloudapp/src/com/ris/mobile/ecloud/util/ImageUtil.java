package com.ris.mobile.ecloud.util;
 

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.util.HashMap; 

import com.ris.mobile.ecloud.R;
import com.ris.mobile.ecloud.LApplication; 
import com.ris.mobile.ecloud.object.RequestObject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.os.Message; 
public class ImageUtil {
	private static final String SDCARD_CACHE_IMG_PATH = LApplication.getCacheDirPath()
			+ "/images/";
	protected static final String TAG = "ImageUtil";
	
	public static boolean exists(String fileName){
		File file=new File(SDCARD_CACHE_IMG_PATH,fileName);
        return file.exists();	
	}
	
	public static void saveImage(Bitmap bmp, String fileName){
		String extension = IOHelper.getExtension(fileName);
		FileOutputStream fos = null;
		File file = new File(fileName); 
		if (file.exists()) {
			return;
		}  
		try{ 
		    File parentFile = file.getParentFile();
			if (!parentFile.exists()) {
				parentFile.mkdirs();
			}
			file.createNewFile();
			fos = new FileOutputStream(file);
			bmp.compress(Bitmap.CompressFormat.JPEG, 75, fos);
			fos.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(null != fos){
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}  
		
		/*
		try {
			
			
			if(!file.exists()){
				file.createNewFile();
			}
			fos = new FileOutputStream(file);
			
			if("png".equalsIgnoreCase(extension)){
				bmp.compress(Bitmap.CompressFormat.PNG, 75, fos);
			}else if("jpg".equalsIgnoreCase(extension) || "jpeg".equalsIgnoreCase(extension)){
				bmp.compress(Bitmap.CompressFormat.JPEG, 75, fos);
			}
			
			fos.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(null != fos){
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}*/
	}
	/**
	 * 保存图片到SD卡
	 * 
	 * @param imagePath
	 * @param buffer
	 * @throws IOException
	 */
	public static void saveImage(String imagePath, byte[] buffer) throws IOException {
		File f = new File(imagePath);
		if (f.exists()) {
			return;
		} else {
			File parentFile = f.getParentFile();
			if (!parentFile.exists()) {
				parentFile.mkdir();
			}
			f.createNewFile();
			FileOutputStream fos = new FileOutputStream(imagePath);
			fos.write(buffer);
			fos.flush();
			fos.close();
		}
	}

	/**
	 * 从SD卡加载图片
	 * 
	 * @param imagePath
	 * @return
	 */
	public static Bitmap getImageFromLocal(String imagePath) {
		File file = new File(imagePath);
		if (file.exists()) {
			Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
			file.setLastModified(System.currentTimeMillis());
			return bitmap;
		}
		return null;
	}
	public static Bitmap loadImage( final String imagePath,final RequestObject vo,  final ImageCallback callback) {
		Bitmap bitmap = getImageFromLocal(imagePath); 
		final String imgUrl =  vo.objectUrl;
		
		if (bitmap != null) {
			return bitmap;
		} else {// 从网上加载 
			final Handler handler = new Handler() {
				@Override
				public void handleMessage(Message msg) {
					if (msg.obj != null&&callback!=null) {
						Bitmap bitmap = (Bitmap) msg.obj;
						callback.loadImage(bitmap, imagePath);
					}
				}
			};

			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					try {
						URL url = new URL(imgUrl);
						//Proxy proxy=new Proxy(java.net.Proxy.Type.HTTP,new InetSocketAddress("115.239.134.175", 81));
						//URLConnection conn = url.openConnection(proxy); 
						URLConnection conn = url.openConnection();
						//HttpURLConnection httpUrlConnection = (HttpURLConnection) conn;  
						conn.setRequestProperty("User-Agent", "");
						
						
						OutputStream os = null; 
						/*if(vo.requestDataMap!=null){
							((HttpURLConnection)conn).setRequestMethod("POST"); 
							os = conn.getOutputStream(); 
							String xmlStr=NetUtil.getXmlStr(vo.requestDataMap);
							OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
							osw.write(new String(xmlStr.getBytes("utf-8"),"UTF-8"));
							osw.flush();
							osw.close();
							osw=null;  
						} else{
							conn.connect();
						}*/
						
						
						conn.connect();
						//osw.write(encodeMsg);
						
						BufferedInputStream bis = new BufferedInputStream(conn.getInputStream(), 8192) {};
						 
						Bitmap bitmap = BitmapFactory.decodeStream(bis);
						ImageUtil.saveImage(bitmap,imagePath);
						Message msg = handler.obtainMessage();
						msg.obj = bitmap;
						handler.sendMessage(msg);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			};
			ThreadPoolManager.getInstance().addTask(runnable);
		}
		return null;
	}  
	
	
	
	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	public static Bitmap loadImage(final String imagePath, final String imgUrl, final ImageCallback callback) {
		Bitmap bitmap = getImageFromLocal(imagePath);
		if (bitmap != null) {
			return bitmap;
		} else {// 从网上加载
			final Handler handler = new Handler() {
				@Override
				public void handleMessage(Message msg) {
					if (msg.obj != null) {
						Bitmap bitmap = (Bitmap) msg.obj;
						callback.loadImage(bitmap, imagePath);
					}
				}
			};

			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					try {
						URL url = new URL(imgUrl);
						//Proxy proxy=new Proxy(java.net.Proxy.Type.HTTP,new InetSocketAddress("115.239.134.175", 81));
						//URLConnection conn = url.openConnection(proxy); 
						URLConnection conn = url.openConnection();
						conn.connect();
						BufferedInputStream bis = new BufferedInputStream(conn.getInputStream(), 8192) {
						};
						Bitmap bitmap = BitmapFactory.decodeStream(bis);
						Message msg = handler.obtainMessage();
						msg.obj = bitmap;
						handler.sendMessage(msg);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			};
			ThreadPoolManager.getInstance().addTask(runnable);
		}
		return null;
	}

	// 返回图片存到sd卡的路径
	public static String getCacheImgPath() {
		return SDCARD_CACHE_IMG_PATH;
	}

	public static String md5(String paramString) {
		String returnStr;
		try {
			MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
			localMessageDigest.update(paramString.getBytes());
			returnStr = byteToHexString(localMessageDigest.digest());
			return returnStr;
		} catch (Exception e) {
			return paramString;
		}
	}

	/**
	 * 将指定byte数组转换成16进制字符串
	 * 
	 * @param b
	 * @return
	 */
	public static String byteToHexString(byte[] b) {
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			hexString.append(hex.toUpperCase());
		}
		return hexString.toString();
	}

	/**
	 * 
	 * @author Mathew
	 * 
	 */
	public interface ImageCallback {
		public void loadImage(Bitmap bitmap, String imagePath);
	}

	
}

package com.ris.mobile.ecloud.engine;
 

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.ref.SoftReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;

import com.ris.mobile.ecloud.LApplication;
import com.ris.mobile.ecloud.R; 
import com.ris.mobile.ecloud.log.CommonLog;
import com.ris.mobile.ecloud.log.LogFactory;
import com.ris.mobile.ecloud.object.RequestObject;
import com.ris.mobile.ecloud.util.Constant; 
 
import com.ris.mobile.ecloud.util.Md5;
import com.ris.mobile.ecloud.util.NetUtil;
import com.ris.mobile.ecloud.util.StreamUtil;
import com.ris.mobile.ecloud.util.ThreadPoolManager; 
public class SyncImageLoader {
	private static final String TAG = "SyncImageLoader";
	private static final CommonLog log = LogFactory.createLog(TAG);

	private Object lock = new Object();

	private boolean mAllowLoad = true;

	private boolean firstLoad = true;

	private int mStartLoadLimit = 0;

	private int mStopLoadLimit = 0;

	private static final int BUFFER_SIZE = 8129;

	final Handler handler = new Handler();
	
	private ThreadPoolManager threadPool = ThreadPoolManager.getInstance();

	private Map<String, SoftReference<Drawable>> imageCache = new ConcurrentHashMap<String, SoftReference<Drawable>>();

	public interface OnImageLoadListener {
		public void onImageLoad(Integer t, Drawable drawable);

		public void onError(Integer t);
	}

	public void setLoadLimit(int startLoadLimit, int stopLoadLimit) {
		if (startLoadLimit > stopLoadLimit) {
			return;
		}
		mStartLoadLimit = startLoadLimit;
		mStopLoadLimit = stopLoadLimit;
	}

	public void restore() {
		mAllowLoad = true;
		firstLoad = true;
	}

	public void lock() {
		mAllowLoad = false;
		firstLoad = false;
	}

	public void unlock() {
		mAllowLoad = true;
		synchronized (lock) {
			lock.notifyAll();
		}
	}
	
	public   void loadImage( final Integer position,final RequestObject vo,  final OnImageLoadListener listener) {
		 
		final String imgUrl =  vo.objectUrl;
		
		
		threadPool.addTask(new Runnable() {

			@Override
			public void run() {
				if (!mAllowLoad) {
					// Logger.d(TAG, "prepare to load");
					synchronized (lock) {
						try {
							lock.wait();
						} catch (InterruptedException e) {
							log.e(e); 
						}
					}
				}
				if (mAllowLoad && firstLoad) {
					loadImage(vo, position, listener);
				} else if (mAllowLoad && position <= mStopLoadLimit && position >= mStartLoadLimit) {
					loadImage(vo, position, listener);
				}
			}

		});
	} 
	private void loadImage(final RequestObject vo, final Integer mt, final OnImageLoadListener mListener) {
		final String majorKey =  vo.majorKey;
		if (imageCache.containsKey(majorKey)) {
			SoftReference<Drawable> softReference = imageCache.get(majorKey);
			final Drawable d = softReference.get();
			if (d != null) {
				handler.post(new Runnable() {
					@Override
					public void run() {
						if (mAllowLoad) {
							mListener.onImageLoad(mt, d);
						}
					}
				});
				return;
			}
		}
		try {
			final Drawable d = loadImageFromVo(vo);
			if (d != null) {
				imageCache.put(majorKey, new SoftReference<Drawable>(d));
			}
			handler.post(new Runnable() {
				@Override
				public void run() {
					if (mAllowLoad) {
						mListener.onImageLoad(mt, d);
					}
				}
			});
		} catch (IOException e) {
			handler.post(new Runnable() {
				@Override
				public void run() {
					mListener.onError(mt);
				}
			});
			log.e(  e);
		}
	}
	
	public static Drawable loadImageFromVo(RequestObject vo) throws IOException {
		InputStream in = null;
		OutputStream out = null;
		final String imgUrl =  vo.objectUrl;
		try {
			File f = new File(LApplication.getCacheDirPath(), Md5.digest(imgUrl+vo.majorKey));
			if (f.exists()) {
				log.d(  "缓存　" + f.getAbsolutePath());
				return Drawable.createFromPath(f.getAbsolutePath());
			} 
			URL url = new URL(imgUrl);
			URLConnection conn = url.openConnection(); 
			HttpURLConnection httpUrlConnection = (HttpURLConnection) conn;  
			conn.setRequestProperty("User-Agent",  "");
			 
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
			in = new BufferedInputStream(conn.getInputStream(), BUFFER_SIZE) ; 
			out = new BufferedOutputStream(new FileOutputStream(f), BUFFER_SIZE);
			byte[] buffer = new byte[BUFFER_SIZE];
			int len = 0;
			while ((len = in.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
		} finally {
			StreamUtil.Release(in, out);
		}
		return loadImageFromVo(vo);
	}
	
	 
	

	public void loadImage(Integer position, String imageUrl, OnImageLoadListener listener) {
		final OnImageLoadListener mListener = listener;
		final String mImageUrl = imageUrl;
		final Integer mt = position;
		threadPool.addTask(new Runnable() {

			@Override
			public void run() {
				if (!mAllowLoad) {
					// Logger.d(TAG, "prepare to load");
					synchronized (lock) {
						try {
							lock.wait();
						} catch (InterruptedException e) {
							log.e(  e);
						}
					}
				}
				if (mAllowLoad && firstLoad) {
					loadImage(mImageUrl, mt, mListener);
				} else if (mAllowLoad && mt <= mStopLoadLimit && mt >= mStartLoadLimit) {
					loadImage(mImageUrl, mt, mListener);
				}
			}

		});
	}

	private void loadImage(final String mImageUrl, final Integer mt, final OnImageLoadListener mListener) {

		if (imageCache.containsKey(mImageUrl)) {
			SoftReference<Drawable> softReference = imageCache.get(mImageUrl);
			final Drawable d = softReference.get();
			if (d != null) {
				handler.post(new Runnable() {
					@Override
					public void run() {
						if (mAllowLoad) {
							mListener.onImageLoad(mt, d);
						}
					}
				});
				return;
			}
		}
		try {
			final Drawable d = loadImageFromUrl(mImageUrl);
			if (d != null) {
				imageCache.put(mImageUrl, new SoftReference<Drawable>(d));
			}
			handler.post(new Runnable() {
				@Override
				public void run() {
					if (mAllowLoad) {
						mListener.onImageLoad(mt, d);
					}
				}
			});
		} catch (IOException e) {
			handler.post(new Runnable() {
				@Override
				public void run() {
					mListener.onError(mt);
				}
			});
			log.e(  e);
		}
	}
	
	
	
	public static Drawable loadImageFromUrl(String url) throws IOException {
		InputStream in = null;
		OutputStream out = null;
		try {
			File f = new File(LApplication.getCacheDirPath(), Md5.digest(url));
			if (f.exists()) {
				log.d(  "缓存　" + f.getAbsolutePath());
				return Drawable.createFromPath(f.getAbsolutePath());
			}
			log.d(  "网络　" + url);
			URL m = new URL(url);
			in = new BufferedInputStream((InputStream) m.getContent(), BUFFER_SIZE);
			out = new BufferedOutputStream(new FileOutputStream(f), BUFFER_SIZE);
			byte[] buffer = new byte[BUFFER_SIZE];
			int len = 0;
			while ((len = in.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
		} finally {
			StreamUtil.Release(in, out);
		}
		return loadImageFromUrl(url);
	}
}

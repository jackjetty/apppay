package com.ris.mobile.ecloud.util; 

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface; 
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.format.DateUtils;
import android.util.TypedValue;
public class CommonUtil {

	public static void showInfoDialog(Context context, String message) {
		showInfoDialog(context, message, "提示", "确定", null);
	}
	
	public static String trim(Object obj) {
		if (obj == null) {
			return "";
		}
		return trim(obj.toString());
	}

	public static String trim(String str) {
		if (str == null) {
			return "";
		}
		try {
			str = java.net.URLDecoder.decode(str, "UTF-8");
		} catch (Exception ex) {
		}
		return str.trim();
	}
	
	public static PackageInfo  getSoftVersion(Context context){ 
		 PackageManager manager = context.getPackageManager();
		 PackageInfo info=null; 
		 try {
				info = manager.getPackageInfo(context.getPackageName(), 0);  
		 } catch (NameNotFoundException e) {			
				e.printStackTrace();
		} 
		 return info; 
	}
	
	
	public static long getAmrDuration(File file) throws IOException {  
        long duration = -1;  
        int[] packedSize = { 12, 13, 15, 17, 19, 20, 26, 31, 5, 0, 0, 0, 0, 0, 0, 0 };  
        RandomAccessFile randomAccessFile = null;  
        try {  
            randomAccessFile = new RandomAccessFile(file, "rw");  
            long length = file.length();//文件的长度  
            int pos = 6;//设置初始位置  
            int frameCount = 0;//初始帧数  
            int packedPos = -1;  
            /////////////////////////////////////////////////////  
            byte[] datas = new byte[1];//初始数据值  
            while (pos <= length) {  
                randomAccessFile.seek(pos);  
                if (randomAccessFile.read(datas, 0, 1) != 1) {  
                    duration = length > 0 ? ((length - 6) / 650) : 0;  
                    break;  
                }  
                packedPos = (datas[0] >> 3) & 0x0F;  
                pos += packedSize[packedPos] + 1;  
                frameCount++;  
            }  
            /////////////////////////////////////////////////////  
            duration += frameCount * 20;//帧数*20  
        } finally {  
            if (randomAccessFile != null) {  
                randomAccessFile.close();  
            }  
        }  
        return duration;  
    }   
	 
	public static boolean isValidEmail(String paramString) {

		String regex = "[a-zA-Z0-9_\\.]{1,}@(([a-zA-z0-9]-*){1,}\\.){1,3}[a-zA-z\\-]{1,}";
		if (paramString.matches(regex)) {
			return true;
		} else {
			return false;
		}
	}
	
	
	public static boolean isIDNum(String paramString) {

		String regex = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
		if (paramString.matches(regex)) {
			return true;
		} 
		regex="^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X|x)$";
		if (paramString.matches(regex)) {
			return true;
		}
		return false;
	}
	
	public static boolean isRealNumber(String paramString){ 
		String regex="^(0|[1-9][0-9]*)(\\.[0-9]+)?$";
		if (paramString.matches(regex)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static  boolean isInteger(String Stemp) {
        Stemp = Stemp.trim();
        String reg = ("\\d+");
        Pattern pattern = Pattern.compile(reg, Pattern.UNICODE_CASE);
        Matcher matcher;
        matcher = pattern.matcher(Stemp);
        return matcher.matches();
    }
	
	 public static String getDateForm(java.util.Date date) {
	        if (date == null) {
	            return "";
	        }
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        return sdf.format(date);

	    }
	    public static String getDateForm(java.util.Date date,String dateform) {
	        if (date == null) {
	            return "";
	        }
	        SimpleDateFormat sdf = new SimpleDateFormat(dateform);
	        return sdf.format(date);

	    }
	    //yyyy-M-dd HH:mm
	    public static java.util.Date getDateByForm(String str,String dateform){
	        if(str==null||dateform==null)
	             return null;
	        SimpleDateFormat sdf = new SimpleDateFormat(dateform);
	        try{
	            java.util.Date date = sdf.parse(str);
	            return date;
	        }catch (Exception e) {
	            return null;
	        }


	    }
	
	public static String formatDate(Context context, long date) {
		int format_flags = DateUtils.FORMAT_NO_NOON_MIDNIGHT
				| DateUtils.FORMAT_ABBREV_ALL | DateUtils.FORMAT_CAP_AMPM
				| DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_DATE
				| DateUtils.FORMAT_SHOW_TIME;
		return DateUtils.formatDateTime(context, date, format_flags);
	}

	public static boolean isValidMobiNumber(String paramString) {
		String regex = "^1\\d{10}$";
		if (paramString.matches(regex)) {
			return true;
		}
		return false;
	}

	public static void showInfoDialog(Context context, String message, String titleStr, String positiveStr,
			DialogInterface.OnClickListener onClickListener) {
		AlertDialog.Builder localBuilder = new AlertDialog.Builder(context);
		localBuilder.setTitle(titleStr);
		localBuilder.setMessage(message);
		if (onClickListener == null)
			onClickListener = new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {

				}
			};
		localBuilder.setPositiveButton(positiveStr, onClickListener);
		localBuilder.show();
	}
}
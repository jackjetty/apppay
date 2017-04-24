package com.rising.appserver.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class CommonUtil {
	private static CommonUtil commonUtil;

	public static CommonUtil getInstance() {
		if (commonUtil == null) {
			commonUtil = new CommonUtil();
		}
		return commonUtil;
	}

	public static String trim(Object obj) {
		if (obj == null) {
			return "";
		}
		return trim(obj.toString());
	}

	public static boolean booleanValue(Boolean bb) {
		if (bb == null)
			return false;
		return bb.booleanValue();

	}

	

	public static String getRandom() {
		Random rand = new Random();
		Double randDouble = rand.nextDouble();
		return CommonUtil.castString(randDouble, "0.0000000000000000");
	}

	public static String mapToString(HashMap<String, Object> map) {
		String parameter = "";
		Set<String> set = map.keySet();
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			String key = it.next();
			String value = String.valueOf(map.get(key));
			parameter = parameter + key + "=" + value + "$";
		}
		return parameter;
	}

	public static HashMap<String, Object> stringToLowerMap(String result) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Pattern responsePtn = Pattern.compile("\\$+");
		String[] strs = responsePtn.split(result);
		Pattern cellPtn = Pattern.compile("^(.+)=(.*?)$");
		for (String cellRst : strs) {
			Matcher matcher = cellPtn.matcher(cellRst);
			if (matcher.find()) {
				map.put(CommonUtil.trim(matcher.group(1)).toLowerCase(),
						CommonUtil.trim(matcher.group(2)));

			}

		}
		return map;

	}
	public static void appendLog(String logName,String logText){
		File file=new File(Constant.LOG_PATH+ logName+"\\") ;
		if(!file.exists())
			file.mkdirs();
		FileWriter writer = null;
        try {
            writer = new FileWriter(Constant.LOG_PATH+ logName+"\\log_" + CommonUtil.getDateForm()+".log",true);
            writer.write(logText+"\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
	}

	public static java.util.Date getDateByForm(String str, String dateform) {
		if (str == null || dateform == null || str.equals("")
				|| dateform.equals(""))
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat(dateform);
		try {
			java.util.Date date = sdf.parse(str);
			return date;
		} catch (Exception e) {
			return null;
		}

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

	public static boolean isInteger(String str) {
		if (str == null) {
			return false;
		}
		str = trim(str);
		Pattern pattern;
		Matcher matcher;
		pattern = Pattern.compile("^(\\+|-){0,1}\\d+$");
		matcher = pattern.matcher(str);
		return matcher.matches();
	}

	public static String firstMonthDateForm() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return CommonUtil.doDateForm(calendar.getTime());
	}

	public static Date firstMonthDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	public static String lastMonthDateForm() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return CommonUtil.doDateForm(calendar.getTime());
	}

	public static String doDateForm(java.util.Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);

	}

	public static String doDateForm(java.util.Date date, String dateform) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(dateform);
		return sdf.format(date);

	}

	public static boolean isNumeric(String Stemp) {
		Stemp = Stemp.trim();
		String reg = ("(-|\\+)?\\d+(\\.\\d+)?");
		Pattern pattern = Pattern.compile(reg, Pattern.UNICODE_CASE);
		Matcher matcher;
		matcher = pattern.matcher(Stemp);
		return matcher.matches();
	}

	public static String getCurrentDateStrForm() {
		Calendar c = Calendar.getInstance();
		return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
	}

	public static String getYesterDateStrForm() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_YEAR, -1);
		return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
	}

	public static String getBeforeWeekDateStrForm() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_YEAR, -7);
		return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
	}

	public static String getDateForm() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date());
	}

	public static String getDateForm(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	public static String getDateForm(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date());
	}

	public static String getDateForm(Object obj, String format) {
		if (obj == null)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format((Date) obj);
	}

	public static int getCurrentNum(String currentStr, String prefixStr) {
		Pattern pattern;
		Matcher matcher;
		pattern = Pattern.compile("^" + prefixStr + "(0*)(\\d+)$");
		int currentNum = 0;
		matcher = pattern.matcher(currentStr);
		if (matcher.find()) {
			currentNum = Integer.parseInt(matcher.group(2));
		}
		return currentNum;
	}

	public static String getSQLInString(List<String> list) {
		if (list != null && list.size() > 0) {
			String r = "";
			for (String t : list) {
				r += "'" + t + "',";
			}
			return r.substring(0, r.length() - 1);
		} else {
			return null;
		}
	}

	public static boolean isNullOrEmpty(String s) {
		if (s != null && !"".equals(s)) {
			return false;
		}
		return true;
	}

	public static String getWeekDay(Calendar c) {
		if (c == null) {
			return "星期一";
		}

		if (Calendar.MONDAY == c.get(Calendar.DAY_OF_WEEK)) {
			return "星期一";
		}
		if (Calendar.TUESDAY == c.get(Calendar.DAY_OF_WEEK)) {
			return "星期二";
		}
		if (Calendar.WEDNESDAY == c.get(Calendar.DAY_OF_WEEK)) {
			return "星期三";
		}
		if (Calendar.THURSDAY == c.get(Calendar.DAY_OF_WEEK)) {
			return "星期四";
		}
		if (Calendar.FRIDAY == c.get(Calendar.DAY_OF_WEEK)) {
			return "星期五";
		}
		if (Calendar.SATURDAY == c.get(Calendar.DAY_OF_WEEK)) {
			return "星期六";
		}
		if (Calendar.SUNDAY == c.get(Calendar.DAY_OF_WEEK)) {
			return "星期日";
		}

		return "星期一";
	}

	public static boolean isNum(String Stemp) {
		Stemp = Stemp.trim();
		String reg = ("(-|\\+)?\\d+(\\.\\d+)?");
		Pattern pattern = Pattern.compile(reg, Pattern.UNICODE_CASE);
		Matcher matcher;
		matcher = pattern.matcher(Stemp);
		return matcher.matches();
	}

	public static boolean isPhoneNum(String Stemp) {
		String reg = ("\\d{11}");
		Pattern pattern = Pattern.compile(reg, Pattern.UNICODE_CASE);
		Matcher matcher;
		matcher = pattern.matcher(Stemp);
		return matcher.matches();
	}

	public static boolean isTelePhoneNumber(String Stemp) {
		String reg = ("^(133|153|180|181|189|177|170)\\d{8}$");
		Pattern pattern = Pattern.compile(reg, Pattern.UNICODE_CASE);
		Matcher matcher;
		matcher = pattern.matcher(Stemp);
		return matcher.matches();
	}

	public static void main(String[] args) {
		System.out.println(castInt("210.21"));
	}

	public static double castDouble(String StrVal) {
		double dval = 0;
		if (StrVal == null)
			return dval;
		StrVal = StrVal.trim();
		if (!isNum(StrVal))
			return dval;

		try {
			dval = (new Double(StrVal)).doubleValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dval;
	}

	public static String castString(double DblVal, String Stemp) {

		DecimalFormat aDecimalFormat = (DecimalFormat) DecimalFormat
				.getInstance();
		aDecimalFormat.applyPattern(Stemp);
		return aDecimalFormat.format(DblVal);
	}

	public static int castInt(String StrVal) {
		StrVal = StrVal.trim();
		Pattern pattern;
		Matcher matcher;
		
		int ival = 0;
		if (!isNum(StrVal))
			return ival;
		
		pattern = Pattern.compile("^((-|\\+)?\\d+)(\\.\\d*)$");
		int currentNum = 0;
		matcher = pattern.matcher(StrVal);
		if (matcher.find()) {
			StrVal =  matcher.group(1) ;
		} 
		 
		
		
		try {
			ival = (new Integer(StrVal)).intValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ival;
	}
	
	
	public static String escape(String src) {  
        int i;  
        char j;  
        StringBuffer tmp = new StringBuffer();  
        tmp.ensureCapacity(src.length() * 6);  
        for (i = 0; i < src.length(); i++) {  
            j = src.charAt(i);  
            if (Character.isDigit(j) || Character.isLowerCase(j)  
                    || Character.isUpperCase(j))  
                tmp.append(j);  
            else if (j < 256) {  
                tmp.append("%");  
                if (j < 16)  
                    tmp.append("0");  
                tmp.append(Integer.toString(j, 16));  
            } else {  
                tmp.append("%u");  
                tmp.append(Integer.toString(j, 16));  
            }  
        }  
        return tmp.toString();  
    }  
  
    public static String unescape(String src) {  
        StringBuffer tmp = new StringBuffer();  
        tmp.ensureCapacity(src.length());  
        int lastPos = 0, pos = 0;  
        char ch;  
        while (lastPos < src.length()) {  
            pos = src.indexOf("%", lastPos);  
            if (pos == lastPos) {  
                if (src.charAt(pos + 1) == 'u') {  
                    ch = (char) Integer.parseInt(src  
                            .substring(pos + 2, pos + 6), 16);  
                    tmp.append(ch);  
                    lastPos = pos + 6;  
                } else {  
                    ch = (char) Integer.parseInt(src  
                            .substring(pos + 1, pos + 3), 16);  
                    tmp.append(ch);  
                    lastPos = pos + 3;  
                }  
            } else {  
                if (pos == -1) {  
                    tmp.append(src.substring(lastPos));  
                    lastPos = src.length();  
                } else {  
                    tmp.append(src.substring(lastPos, pos));  
                    lastPos = pos;  
                }  
            }  
        }  
        return tmp.toString();  
    } 
    
    
    public static String readTxtFile(String filePath,String encoding){
    	StringBuffer contentBuffer=new StringBuffer("");
        try {
                
                File file=new File(filePath);
                if(file.isFile() && file.exists()){ //判断文件是否存在
                    InputStreamReader read = new InputStreamReader(
                    new FileInputStream(file),encoding);//考虑到编码格式
                    BufferedReader bufferedReader = new BufferedReader(read);
                    String lineTxt = null;
                    while((lineTxt = bufferedReader.readLine()) != null){
                         
                        contentBuffer.append(lineTxt);
                    }
                    read.close();
        }else{
             
        }
        } catch (Exception e) { 
            e.printStackTrace();
        }
        return  contentBuffer.toString();
     
    }

}
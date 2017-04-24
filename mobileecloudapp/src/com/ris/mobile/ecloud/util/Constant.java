package com.ris.mobile.ecloud.util; 

import android.os.Environment;

public class Constant {
	public static String ACTIVE_UID;
	public static final String ADDRESS_ID = "addressId";
	public static final String APPKEY = "appkey";
	public static final String APPKEYVALUE = "2011060847";
	public static final String APPSECRET = "appSecret";
	public static final String APPSECRETVALUE = "2d0debf0d3828a08751f2de0a7f82f21";
	public static final String BALANCE = "balance";
	public static final String CLASS = "class";
	public static final int CLASSIFY = 2;
	public static final String CLIENT_VERSION = "clientv";
	public static final String CN_OPERATOR = "cn_operator";
	public static final String CONSUMER_KEY = "1865272664";
	public static final String CONSUMER_SECRET = "29d6f40ec5ed74a735b4ad8aaa0a75e4";
	public static final String CONSUMER_URL = "http://api.t.sina.com.cn/oauth/access_token";
	public static final String DENSITY = "density";
	public static final String FORMAT = "format";
	public static final String FORMATVALUE = "json";
	public static final String HMAC_SHA1 = "HmacSHA1";
	public static final int HOME = 1;
	public static final String IMEI = "imei";
	public static final String IMSI = "imsi";
	public static final String LANGUAGE = "language";
	public static final String LOCAL_PATH_HOME_CLASSICS = "/ecloud/home/classics/";
	public static final String LOCAL_PATH_HOME_POSTER = "/ecloud/home/poster/";
	public static final String LOCAL_PATH_RECENT_BROWSE = "/ecloud/recentbrowse/";
	public static final String LOCAL_PATH_SAVE_PRODUCT_BIG_PIC = "/ecloud/saveimage/";
	public static final String LOCAL_PATH_SHOPCAR = "/ecloud/shopcar/";
	public static final int MIN_SPACE_FOR_VERSION_UPDATA = 10485760;
	public static final String MODEL = "model";
	public static final int MORE = 5;
	public static final String NAME = "name";
	public static final int NEED_SYNCHRO_SHOPCAR = 0;
	public static final String NICK_NAME = "nick_name";
	public static final int NO_NEED_SYNCHRO_SHOPCAR = 1;
	public static final String PAGESIZE = "10";
	public static final String PLATFORM_NAME = "platformn";
	public static final String POINT = "point";
	public static final String SDPATH;
	public static final int SEARCH = 3;
	public static final int SHOPCAR = 4;
	public static int SHOPCAR_NUM = 0;
	public static final String SIGN = "sign";
	public static final String SINGMETHOD = "sign_method";
	public static final String SINGMETHODVALUE = "md5";
	public static final String SMS_CENTER_NUMBER = "sms_center_number";
	public static final String SOURCE = "source";
	public static final String SOURCE_CODE = "source";
	public static final String SYNCHRO_SHOPCAR_FLAG = "synchroShopcarFlag";
	public static final String T = "t";
	public static final String TEMP_LOCAL_PATH_CATEGORY = "/ecloud/temp/category/";
	public static final String TEMP_LOCAL_PATH_FAVORITE = "/ecloud/temp/favorite/";
	public static final String TEMP_LOCAL_PATH_PRODUCTBIGPIC = "/ecloud/temp/productbigpic/";
	public static final String TEMP_LOCAL_PATH_PRODUCTDETAIL = "/ecloud/temp/productdetail/";
	public static final String TEMP_LOCAL_PATH_PRODUCTLIST = "/ecloud/temp/productlist/";
	public static final String TEMP_LOCAL_PATH_PRODUCTSMALLPIC = "/ecloud/temp/productsmallpic/";
	public static final String TEMP_LOCAL_PATH_SEARCHLIST = "/ecloud/temp/searchlist/";
	public static final String TEMP_LOCAL_PATH_WELCOME = "/ecloud/welcome/";
	public static final int TIMEOUT_TIME = 30000;
	public static final String TTID = "ttid";
	public static final String UID = "uid";
	public static final String USER = "user";
	public static final String USERTOKEN = "usertoken";
	public static final String USER_ID = "userId";
	public static final String USER_TOKEN = "usertoken";
	public static final String VER = "ver";
	public static final String VERVALUE = "1.0";
	public static final String WELCOME_IMG_NAME = "welcomeImgName";
	public static final String X_RESOLUTION = "xResolution";
	public static final String Y_RESOLUTION = "yResolution";
	public final static int FAILED = 1;
	public final static int SUCCESS = 1;
	public final static int NET_FAILED = 2;
	public final static int TIME_OUT = 3;
	public static final int USERTYPE_TEACHER=0;
	public static final int USERTYPE_STUDENT=1;
	public static final int USERTYPE_OUT=2;
	
	public static final int RESC_BINDPHONE = 2123;
	public static final int RESULT_OK=-1;
	
	public static final String MERCHANTID="01330105039831000";
	public static final String MERCHANTPWD="675800";
	public static final String MERCHANTKEY="75C06CB9A454253A86E1CF0E90216516B24B01C05F13953D";
	
	public final static int faqVersionCode=10;
	 
	public static final String STATUS_SUCCESS = "1";  
	public static final String STATUS_FAIL = "2";  
	public static final String STATUS_TOPAY= "0"; 
	public static final String STATUS_CANCEL= "3";
 
	
	public static int defaultIndex;
	public static boolean exit = true;
	public static int selectedHome;
	public static String selectedNum;
	public static String FLAG;
	public static String LIMIT_BUY = "limitbuy";
	public static String NEW_PRODUCT = "newproduct";
	public static String BRAND = "brand";
	public static String CATEGORY = "category";
	public static String HOT_PRODUCT = "hotproduct";
	public static String FILTER = "filter";
	public static String SEARCHURL = "search"; 
	
	//报名项类型（1：报名缴费；2：报名；3：缴费；）
	public static String APPLY_JUSTSIGN = "2";  
	public static String APPLY_JUSTPAY = "3";  
	public static String APPLY_SIGNPAY = "1"; 
	
	//充值对象（0：卡；1：手机；）
	public static String IC_TYPE_CARD = "0";  
	public static String IC_TYPE_PHONE = "1";   
	
	public static final class BundleKey {
		public static final String PAGE_TAG_ID = "PAGE_TAG_ID";
		public static final String SPECIAL_LIST = "SPECIAL_LIST";
	}
	 
	 
	static {
		int i = 0;
		defaultIndex = 1;
		selectedHome = i;
		selectedNum = "0";
		SDPATH = Environment.getExternalStorageDirectory().getPath();
		ACTIVE_UID = "uid";
		SHOPCAR_NUM = i;
	}
}
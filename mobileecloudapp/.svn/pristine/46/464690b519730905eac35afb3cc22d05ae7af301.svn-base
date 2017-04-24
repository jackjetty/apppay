package com.ris.mobile.ecloud.fragment;
 


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap; 
import org.apache.http.message.BasicNameValuePair; 
import roboguice.fragment.RoboFragment;
import roboguice.inject.InjectView;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ris.mobile.ecloud.BaseFragment;
import com.ris.mobile.ecloud.LApplication;
import com.ris.mobile.ecloud.R;
import com.ris.mobile.ecloud.activity.ApplyBillActivity;
import com.ris.mobile.ecloud.activity.CropImageActivity;
import com.ris.mobile.ecloud.activity.FeedBackActivity;
import com.ris.mobile.ecloud.activity.GetPicActivity;
import com.ris.mobile.ecloud.activity.ICCardActivity;
import com.ris.mobile.ecloud.activity.ICCardBillActivity;
import com.ris.mobile.ecloud.activity.MainActivity;
import com.ris.mobile.ecloud.activity.PersonActivity; 
import com.ris.mobile.ecloud.activity.UpdatePasswordActivity;
import com.ris.mobile.ecloud.engine.SyncImageLoader;
import com.ris.mobile.ecloud.engine.SyncImageLoader.OnImageLoadListener;
import com.ris.mobile.ecloud.log.CommonLog;
import com.ris.mobile.ecloud.log.LogFactory;
import com.ris.mobile.ecloud.object.AccountObject;
import com.ris.mobile.ecloud.object.ConnectErrorObject;
import com.ris.mobile.ecloud.object.RequestObject;
import com.ris.mobile.ecloud.object.ResponseObject;
import com.ris.mobile.ecloud.parser.AccountParser;
import com.ris.mobile.ecloud.parser.BaseParser;
import com.ris.mobile.ecloud.parser.ResponseParser;
import com.ris.mobile.ecloud.util.AbStrUtil;
import com.ris.mobile.ecloud.util.CommonUtil;
import com.ris.mobile.ecloud.util.Constant;
import com.ris.mobile.ecloud.util.ImageUtil;
import com.ris.mobile.ecloud.util.Md5;
import com.ris.mobile.ecloud.util.OkHttpClientManager;
import com.ris.mobile.ecloud.util.OkHttpClientManager.Param;
import com.ris.mobile.ecloud.util.SharedPreferenceUtil;
import com.ris.mobile.ecloud.widget.ActionSheetDialog;
import com.ris.mobile.ecloud.widget.ActionSheetDialog.OnSheetItemClickListener;
import com.ris.mobile.ecloud.widget.ActionSheetDialog.SheetItemColor;
import com.ris.mobile.ecloud.widget.CircularImage;
import com.squareup.okhttp.Request;
 
      
public class MineFragment  extends  BaseFragment implements  OnClickListener  , OnImageLoadListener{ 
 
	private View fragmentView;
	
	
	private MainActivity mainActivity;
	private LApplication lApplication; 
	private Context mContext;
	private SharedPreferenceUtil sharedPreferenceUtil;
	
	@InjectView (R.id.tv_Back)
	private TextView tvBack;
	 
	@InjectView (R.id.tv_Title)
	private TextView tvTitle;  
	
	@InjectView (R.id.iv_Head)
	private CircularImage ivHead;
	
	@InjectView (R.id.tv_UserName)
	private TextView tvUserName;
	
	@InjectView (R.id.tv_UserId)
	private TextView tvUserId;
	
	@InjectView (R.id.tv_Balance)
	private TextView tvBalance;
	
	@InjectView (R.id.ll_Balance)
	private LinearLayout llBalance;
	
	@InjectView (R.id.rl_Person)
	private RelativeLayout rlPerson; 
	
	@InjectView (R.id.rl_Update)
	private RelativeLayout rlUpdate;
	
	@InjectView (R.id.rl_Apply)
	private RelativeLayout rlApply;
	
	@InjectView (R.id.rl_ICCard)
	private RelativeLayout rlICCard;
	
	@InjectView (R.id.rl_FeedBack)
	private RelativeLayout rlFeedBack;
	
	private String mFileName;
	private SyncImageLoader syncImageLoader; 
 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setRetainInstance(true); 
	} 
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {        
    	//fragmentView=inflater.inflate(R.layout.fragment_mine, null); 
    	fragmentView= inflater.inflate(R.layout.fragment_mine, container, false);
    	mContext=this.getActivity();
    	mainActivity=(MainActivity)this.getActivity();
    	lApplication=LApplication.getInstance();
    	sharedPreferenceUtil=new SharedPreferenceUtil(mContext);
    	syncImageLoader = new SyncImageLoader(); 
    	return   fragmentView;      
    }
    
    
    

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    	setListener();
    }
    
    @Override
	public void onImageLoad(Integer t, Drawable drawable) {
	 
		
        	ivHead.setImageDrawable(drawable); 
           
		
	}

	@Override
	public void onError(Integer t) {

	}  
   
    private void initView(){
    	tvBack.setVisibility(View.INVISIBLE);
    	tvTitle.setVisibility(View.VISIBLE);
    	tvTitle.setText(R.string.title_mine);  
    	
     
    	
    	
    	if(sharedPreferenceUtil.getUserSex().equalsIgnoreCase("女"))
    		ivHead.setImageDrawable(mContext
    				.getResources().getDrawable(R.drawable.head_female));
    	else
    		ivHead.setImageDrawable(mContext
    				.getResources().getDrawable(R.drawable.head_male));
    	tvBalance.setText("");
    	
    	if(!sharedPreferenceUtil.getPortraitUrl().equals("")){
    		HashMap<String, String> requestDataMap;
    		RequestObject reqObject;
    		requestDataMap=new HashMap<String, String>(); 
    		reqObject = new RequestObject(sharedPreferenceUtil.getPortraitUrl(), this.mContext, requestDataMap, null);
    		reqObject.setMajorKey(sharedPreferenceUtil.getPortraitUrl());
    		syncImageLoader.loadImage(0,reqObject,this); 
    	}
    	
    	if(sharedPreferenceUtil.getUserType()==Constant.USERTYPE_OUT ){
    		llBalance.setVisibility(View.GONE);
    		tvUserId.setText(sharedPreferenceUtil.getUserAccount()); 
    	}
    		     
    	else{
    		llBalance.setVisibility(View.VISIBLE);
    		tvUserId.setText(sharedPreferenceUtil.getCardNo()); 
    	}
    		     
    	//
    	 //Bitmap bm = BitmapFactory.decodeFile(); 
    	 //
    	//ivHead.setImageBitmap(bm);
    	
    	//Bitmap mBitmap = AbFileUtil.getBitmapFromSD(new File("), AbImageUtil.SCALEIMG, 80, 80);
    	
    	
    	tvUserName.setText(sharedPreferenceUtil.getUserName()); 
    	
    	
    	refreshBalance();
    	 
    
    	
    }
    
    private void uploadImage(File file){
    	mainActivity.showProgressDialog();
    	String url =mContext.getString(R.string.app_host).concat(
    			mContext.getString(R.string.url_uploadportraitimage));
		log.d("Post " + url);
		BasicNameValuePair pair;
		String timestamp =   Long.valueOf (System.currentTimeMillis()).toString();
		
		String token = sharedPreferenceUtil.getToken();
		String userId = sharedPreferenceUtil.getUserId();
		try {
			ArrayList<BasicNameValuePair> pairList = new ArrayList<BasicNameValuePair>();
			pair = new BasicNameValuePair("timestamp", timestamp);
			pairList.add(pair);
			pair = new BasicNameValuePair("attach","Android"+"@"+ CommonUtil.trim(android.os.Build.VERSION.RELEASE)+"@"+ CommonUtil.trim(android.os.Build.MODEL));
			pairList.add(pair);
			pair = new BasicNameValuePair("userId", userId);
			pairList.add(pair);
			pair = new BasicNameValuePair("sign", Md5.digest("token=" + token
					+ "&timestamp=" + timestamp + "&userId=" + userId));
			pairList.add(pair);
			Param[] params = new Param[pairList.size()];
			Param param;
			for (int i = 0; i < pairList.size(); i++) {
				param = new Param();
				param.key = pairList.get(i).getName();
				param.value = pairList.get(i).getValue();
				params[i] = param;

			}
			OkHttpClientManager.postAsyn(url,
        		    new OkHttpClientManager.ResultCallback<String>()
        		    {
        		     

        		        @Override
        		        public void onResponse(String result)
        		        {
        		        	mainActivity.closeProgressDialog();
        		        	BaseParser<ResponseObject> responseParser1 = new ResponseParser();
        		        	ResponseObject responseObject=(ResponseObject)responseParser1.parseJSON(result);
        		        	if(responseObject==null){
        		        		mainActivity.DisPlay("error", responseParser1.getConnectErrorObject().getErrInfo());
        		        	}else{
        		        		mainActivity.DisPlay("success", responseObject.getInfo());
        		        	}
        		        	
        		        }

						@Override
						public void onError(Request request, Exception e) {
							// TODO Auto-generated method stub
							mainActivity.closeProgressDialog();
							mainActivity.DisPlay("error", "上传出错！");
						}

    					
        		    },//
        		    file,//
        		    "imageFile",//
        		    params
        		       );
			
		}
		catch(Exception ex){
			mainActivity.closeProgressDialog();
			mainActivity.DisPlay("error", "上传出错");
			
		}
		
    	
    }
    private void setListener(){
    	tvBack.setOnClickListener(this); 
    	rlPerson.setOnClickListener(this); 
    	llBalance.setOnClickListener(this); 
    	rlUpdate.setOnClickListener(this); 
    	rlApply.setOnClickListener(this); 
    	rlICCard.setOnClickListener(this); 
    	rlFeedBack.setOnClickListener(this); 
    	ivHead.setOnClickListener(this); 
    }
    private void refreshBalance(){
    	
    	
    	if(sharedPreferenceUtil.getUserType()==Constant.USERTYPE_OUT )
    		 return;
    	BaseParser<AccountObject> accountParser = new AccountParser(); 
	    HashMap<String, String> requestDataMap=new HashMap<String, String>();
	    
	    requestDataMap.put("cardNo",  sharedPreferenceUtil.getCardNo() );
	    requestDataMap.put("userId", sharedPreferenceUtil.getUserId() ); 
 
	    

		RequestObject vo = new RequestObject(R.string.url_account, mContext, requestDataMap, accountParser); 
	    mainActivity.getDataFromServer(vo, new MainActivity.DataCallback<AccountObject>() {
				@Override
				public void processData(AccountObject paramObject, boolean paramBoolean) {
					 
					 
					 String balance="";
					 
					 if(!CommonUtil.trim(paramObject.getCardBalance()).equals("")){
						 
						 balance+="卡："+paramObject.getCardBalance();
					 }
                     if(!CommonUtil.trim(paramObject.getMobileBalance() ).equals("")){
						 
						 balance+="  手机："+paramObject.getMobileBalance();
					 }
					 
					 SpannableString sp = new SpannableString(balance);
					 if(balance.indexOf("卡")>=0){
						 sp.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.grey)), balance.indexOf("卡") ,balance.indexOf("卡")+2,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
					 }
					 if(balance.indexOf("手机")>=0){
						 sp.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.grey)), balance.indexOf("手") ,balance.indexOf("手")+3,Spannable.SPAN_EXCLUSIVE_INCLUSIVE); 
					 }
					 
					
					  tvBalance.setText(sp);  
					  return;
				}
				@Override
	    		public void processError(ConnectErrorObject responseErrorVo){ 
					
					mainActivity.DisPlay("error", responseErrorVo.getErrInfo());
					 
					 
	    		}
			},false); 
    }
    
    
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		  case R.id.tv_Back:
			 //this.finish();
			 break;
		  case R.id.rl_Person:
			     mainActivity.openActivity(PersonActivity.class );
				 break;
		   
		  case R.id.rl_Update:
			     mainActivity.openActivity(UpdatePasswordActivity.class ); 
				 break;
		  case R.id.ll_Balance:
			     mainActivity.openActivity(ICCardActivity.class ); 
				 break;
		  case R.id.rl_Apply:
				 //this.finish();
			     mainActivity.openActivity(ApplyBillActivity.class ); 
				 break;
		  case R.id.rl_ICCard:
				 //this.finish();
			  // 
			     mainActivity.openActivity(ICCardBillActivity.class ); 
				 break;
		  case R.id.rl_FeedBack:
			     mainActivity.openActivity(FeedBackActivity.class );
				 break;
				 
		  case R.id.iv_Head:
			  new ActionSheetDialog(mContext)
				.builder()
				.setCancelable(false)
				.setCanceledOnTouchOutside(false)
				.addSheetItem("相册", SheetItemColor.Blue,
						new OnSheetItemClickListener() {
							@Override
							public void onClick(int which) {
								
								
								//Intent intent1 = new Intent(mContext, CropImageActivity.class);
								//intent1.putExtra("PATH", "/storage/sdcard0/ecloud/1461297641585.jpg");
								
								//startActivityForResult(intent1, CAMERA_CROP_DATA);
								Intent intent = new Intent(mContext, GetPicActivity.class);
								startActivityForResult(intent,ALBUM_WITH_DATA );
							}
						})
				.addSheetItem("拍照", SheetItemColor.Blue,
						new OnSheetItemClickListener() {
							@Override
							public void onClick(int which) {
								doTakePhoto();
							}
						})
				.show(); 
			  break;
		 
	    }
		
		
	} 
	
	private File mCurrentPhotoFile;
	
	/* 用来标识请求照相功能的activity */
	private static final int CAMERA_WITH_DATA = 3023;
	/* 用来标识请求gallery的activity */
	private static final int PHOTO_PICKED_WITH_DATA = 3021;
	/* 用来标识请求裁剪图片后的activity */
	private static final int CAMERA_CROP_DATA = 3022;
	
	/* 用来标识请求照相功能的activity */
	private static final int ALBUM_WITH_DATA = 3024;
	
	protected void doTakePhoto() {
		try {
			mFileName = System.currentTimeMillis() + ".jpg";
			mCurrentPhotoFile = new File(LApplication.getCacheDirPath(), mFileName);
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE, null);
			intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mCurrentPhotoFile));
			startActivityForResult(intent, CAMERA_WITH_DATA);
		} catch (Exception e) {
		}
	}
	
	/**
	 * 描述：因为调用了Camera和Gally所以要判断他们各自的返回情况,
	 * 他们启动时是这样的startActivityForResult
	 */
	public void onActivityResult(int requestCode, int resultCode, Intent mIntent) {
		if (resultCode != MainActivity.RESULT_OK){
			return;
		}
		switch (requestCode) {
			case PHOTO_PICKED_WITH_DATA:
				Uri uri = mIntent.getData();
				String currentFilePath = getPath(uri);
				if(!AbStrUtil.isEmpty(currentFilePath)){
					Intent intent1 = new Intent(mContext, CropImageActivity.class);
					intent1.putExtra("PATH", currentFilePath);
					
					
					startActivityForResult(intent1, CAMERA_CROP_DATA);
		        }else{
		        	//AbToastUtil.showToast(AddPhotoActivity.this,"未在存储卡中找到这个文件");
		        }
				break;
			case CAMERA_WITH_DATA:
				//AbLogUtil.d(AddPhotoActivity.class, "将要进行裁剪的图片的路径是 = " + mCurrentPhotoFile.getPath());
				String currentFilePath2 = mCurrentPhotoFile.getPath();
				Intent intent2 = new Intent(mContext, CropImageActivity.class);
				intent2.putExtra("PATH",currentFilePath2);
				startActivityForResult(intent2,CAMERA_CROP_DATA);
				break;
			case CAMERA_CROP_DATA:
				String path = mIntent.getStringExtra("PATH");
				ivHead.setImageBitmap(ImageUtil.getImageFromLocal(path));
				
				uploadImage(new File(path));
				//上传了啊
				//ivHead.setImageBitmap(ImageUtil.getImageFromLocal("/storage/sdcard0/ecloud/1461297641585.jpg"));
		    	//AbLogUtil.d(AddPhotoActivity.class, "裁剪后得到的图片的路径是 = " + path);
		    	//mImagePathAdapter.addItem(mImagePathAdapter.getCount()-1,path);
		     	//camIndex++;
				
				
				break;
				
			case ALBUM_WITH_DATA:
				String imagePath = mIntent.getStringExtra("IMAGEPATH");
				Intent intent3 = new Intent(mContext, CropImageActivity.class);
				intent3.putExtra("PATH",imagePath);
				startActivityForResult(intent3,CAMERA_CROP_DATA);
				break;
		}
	}
	
	/**
	 * 从相册得到的url转换为SD卡中图片路径
	 */
	public String getPath(Uri uri) {
		if(AbStrUtil.isEmpty(uri.getAuthority())){
			return null;
		}
		String[] projection = { MediaStore.Images.Media.DATA };
		Cursor cursor = mainActivity.managedQuery(uri, projection, null, null, null);
		int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		cursor.moveToFirst();
		String path = cursor.getString(column_index);
		return path;
	}
	 
	@Override
	public void onStart() {
		
		super.onStart();
	
	}
	@Override
	public void onResume() {
		/*if(sharedPreferenceUtil.getUserSex().equalsIgnoreCase("女"))
    		ivHead.setImageDrawable(mContext
    				.getResources().getDrawable(R.drawable.head_female));
    	else
    		ivHead.setImageDrawable(mContext
    				.getResources().getDrawable(R.drawable.head_male));*/
		super.onResume();
	
	}
	@Override

	public void onHiddenChanged(boolean hidden) {

	// TODO Auto-generated method stub

	super.onHiddenChanged(hidden);

	if (hidden) {
		 
	} else {
		refreshBalance();

	}

	}
}  
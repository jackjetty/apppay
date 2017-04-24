package com.ris.mobile.ecloud.fragment;
 


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import roboguice.fragment.RoboFragment;
import roboguice.inject.InjectView;

import com.ris.mobile.ecloud.R;
import com.ris.mobile.ecloud.activity.CategoryActivity;
import com.ris.mobile.ecloud.activity.ApplyActivity;
import com.ris.mobile.ecloud.activity.ICCardActivity;
import com.ris.mobile.ecloud.activity.MainActivity;
import com.ris.mobile.ecloud.activity.WebViewActivity;
import com.ris.mobile.ecloud.adapter.AdvertViewAdaper; 
import com.ris.mobile.ecloud.LApplication;
import com.ris.mobile.ecloud.log.CommonLog;
import com.ris.mobile.ecloud.log.LogFactory;
import com.ris.mobile.ecloud.object.AdvertObject;
import com.ris.mobile.ecloud.object.AnnounceObject;
import com.ris.mobile.ecloud.object.ConnectErrorObject;
import com.ris.mobile.ecloud.object.RequestObject;
import com.ris.mobile.ecloud.object.ResponseObject;
import com.ris.mobile.ecloud.parser.BaseParser;
import com.ris.mobile.ecloud.parser.ResponseParser;
import com.ris.mobile.ecloud.util.CommonUtil;
import com.ris.mobile.ecloud.util.SharedPreferenceUtil;
import com.ris.mobile.ecloud.widget.MyGallery;
import com.ris.mobile.ecloud.widget.PullLoadMoreRecyclerView;

import android.content.Context;
import android.os.Bundle;  
import android.support.v4.app.Fragment;  
import android.view.LayoutInflater;  
import android.view.View;  
import android.view.ViewGroup;  
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView; 
import android.widget.Toast;
      
public class HomeFragment  extends RoboFragment implements  OnClickListener{  
	
	private static final String TAG = HomeFragment.class.getSimpleName(); ;
	private static final CommonLog log = LogFactory.createLog(TAG); 
	private View fragmentView;
	  
	private MainActivity mainActivity;
	private LApplication lApplication; 
	private Context mContext;
	private SharedPreferenceUtil sharedPreferenceUtil;
	private List<AdvertObject> advertList;
	
	
	@InjectView (R.id.gl_Banner)
	private MyGallery glBanner;	 
	
	@InjectView (R.id.ll_focus_container)
	private LinearLayout ll_focus_container ;
	
	@InjectView (R.id.rl_Announce)
	private RelativeLayout rlAnnounce;
	
	@InjectView (R.id.rl_ICCard)
	private RelativeLayout rlICCard; 
	
	@InjectView (R.id.rl_Recruit)
	private RelativeLayout rlRecruit; 
	
	@InjectView (R.id.rl_New)
	private RelativeLayout rlNew; 
	
	@InjectView (R.id.rl_Apply)
	private RelativeLayout rlApply; 
	
	@InjectView (R.id.rl_Employ)
	private RelativeLayout rlEmploy; 
	
	
	
	private int preSelImgIndex = 0; 
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setRetainInstance(true); 
	}
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {        
    
    	fragmentView= inflater.inflate(R.layout.fragment_home, container, false);
    	mContext=this.getActivity();
    	mainActivity=(MainActivity)this.getActivity();
    	lApplication=LApplication.getInstance();
    	sharedPreferenceUtil=new SharedPreferenceUtil(mContext);
    	
    	return   fragmentView;      
    }
    
    
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData(); 
    	initView();
    	setListener(); 
    }
    private void initData(){
    	/*advertList=new ArrayList<AdvertObject>();
    	AdvertObject advertObject;
    	advertObject=new AdvertObject();
    	advertObject.setAdvertName("tt.jgp");
    	advertObject.setImageUrl("http://112.17.8.198:8008/citrus/temp/tt.jpg");
    	advertObject.setTargetUrl("");
    	 
    	advertList.add(advertObject);
    	advertObject=new AdvertObject();
    	advertObject.setImgName("ad_2");
    	advertObject.setImgUrl("http://112.17.8.198:8008/citrus/temp/ad_2.jpg");
    	advertList.add(advertObject);*/
        advertList=sharedPreferenceUtil.getAdvertList();
    	
    	
    }
   
    private void initView(){
    	initFocusIndicatorContainer();
    	glBanner.setAdapter(new AdvertViewAdaper(mContext,advertList));
    	glBanner.setFocusable(true);
    	glBanner.setOnItemSelectedListener(new OnItemSelectedListener() {

		    public void onItemSelected(AdapterView<?> arg0, View arg1,
		    										int selIndex, long arg3) {
				//修改上一次选中项的背景
			    selIndex = selIndex % advertList.size(); 	
				ImageView preSelImg = (ImageView) ll_focus_container
							.findViewById(preSelImgIndex);
				
				if(isAdded()){
					preSelImg.setImageDrawable(mContext
							.getResources().getDrawable(R.drawable.point_focus_select));
					//修改当前选中项的背景
					ImageView curSelImg = (ImageView) ll_focus_container
								.findViewById(selIndex);
					curSelImg
						.setImageDrawable(mContext
							.getResources().getDrawable(
								R.drawable.point_focus));
				}
				
				preSelImgIndex = selIndex;
		    } 
		    public void onNothingSelected(AdapterView<?> arg0) {
		    }
		});
    	 
    	
    	
    }
    
    
      private void initFocusIndicatorContainer() {
    	
		for (int i = 0; i < advertList.size(); i++) {
		    ImageView localImageView = new ImageView(mContext);
		    localImageView.setId(i);
		    ImageView.ScaleType localScaleType = ImageView.ScaleType.FIT_XY;
		    localImageView.setScaleType(localScaleType);
		    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(
			    30, 30);
		    localLayoutParams.setMargins(3,0,3,0);
		  //4个参数按顺序分别是左上右下
		    localImageView.setLayoutParams(localLayoutParams);
		    localImageView.setPadding(7, 7, 7, 7);
		    localImageView.setImageResource(R.drawable.point_focus_select);
		    this.ll_focus_container.addView(localImageView);
		}
    }
    private void setListener(){
    	rlAnnounce.setOnClickListener(this);
    	rlICCard.setOnClickListener(this);
    	rlRecruit.setOnClickListener(this);
    	rlNew.setOnClickListener(this);
    	rlApply.setOnClickListener(this);
    	rlEmploy.setOnClickListener(this); 
    	
    	
    	glBanner.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id)
			{
				
			 
				
				
				AdvertObject advertObject=(AdvertObject)parent.getAdapter().getItem(position); 
				Bundle myBundel=new Bundle(); 
				myBundel.putString("wapUrl",CommonUtil.trim(advertObject.getTargetUrl())); 
				myBundel.putString("wapTitle", CommonUtil.trim(advertObject.getAdvertName())); 
				mainActivity.openActivity(WebViewActivity.class,myBundel); 
			}
		}); 
    }
    
    
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Bundle myBundel=new Bundle(); 
		switch (v.getId()) {
		  case R.id.tv_Back:
			 //this.finish();
			 break;
		  case R.id.rl_Announce:
			  
			    myBundel=new Bundle(); 
				myBundel.putString("articleTag","Announce");  
			     mainActivity.openActivity(CategoryActivity.class ,myBundel); 
				 break;
		  case R.id.rl_Recruit:
			     myBundel=new Bundle(); 
				 myBundel.putString("articleTag","Recruit");  
			     mainActivity.openActivity(CategoryActivity.class ,myBundel); 
				 break;
		  case R.id.rl_New:
			  myBundel=new Bundle(); 
				 myBundel.putString("articleTag","New");  
			     mainActivity.openActivity(CategoryActivity.class ,myBundel); 
				 break;
		  case R.id.rl_Employ:
			  myBundel=new Bundle(); 
				 myBundel.putString("articleTag","Employ");  
			     mainActivity.openActivity(CategoryActivity.class ,myBundel); 
				 break;
		  case R.id.rl_ICCard:
			    mainActivity.openActivity(ICCardActivity.class ); 
			  
				 break;
		  case R.id.rl_Apply:		 
			    mainActivity.openActivity(ApplyActivity.class );   
				 break;
	    }
		
		
	} 
	 
	@Override
	public void onStart() {
		super.onStart();
	
	}
}  
package com.ris.mobile.ecloud.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.ris.mobile.ecloud.BaseActivity;
import com.ris.mobile.ecloud.BaseFragmentActivity;
import com.ris.mobile.ecloud.R; 
import com.ris.mobile.ecloud.LApplication;
import com.ris.mobile.ecloud.engine.FragmentCallback;
import com.ris.mobile.ecloud.fragment.HomeFragment;
import com.ris.mobile.ecloud.fragment.MineFragment;
import com.ris.mobile.ecloud.fragment.SettingFragment;
import com.ris.mobile.ecloud.object.ConnectErrorObject;
import com.ris.mobile.ecloud.object.RequestObject;
import com.ris.mobile.ecloud.util.CommonUtil;
import com.ris.mobile.ecloud.util.Constant;
import com.ris.mobile.ecloud.util.ExitUtil;
import com.ris.mobile.ecloud.util.FragmentUtil;
import com.ris.mobile.ecloud.util.NetUtil;
import com.ris.mobile.ecloud.util.ThreadPoolManager;
import com.ris.mobile.ecloud.widget.LoadingDialog;
import com.ris.mobile.ecloud.widget.TabView;

import android.app.ActionBar; 
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.app.Fragment; 
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public   class MainActivity   extends BaseFragmentActivity 
implements TabView.OnTabChangeListener, FragmentCallback {
	 
	private FragmentManager mFragmentManager;
    private Fragment mCurrentFragment;
    private TabView mTabView;
    private TextView mTitleTextView;

    /** 上一次的状态 */
    private int mPreviousTabIndex = 0;
    /** 当前状态 */
    private int mCurrentTabIndex = 0;

    /** 再按一次退出程序*/
    private long exitTime = 0;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentManager = getSupportFragmentManager();
        mCurrentTabIndex = 0;
        mPreviousTabIndex = 0;
        setupViews();
    }
    private void setupViews()
    {
        setContentView(R.layout.activity_main); 
        mTabView = (TabView) findViewById(R.id.view_tab);
        mTabView.setOnTabChangeListener(this);
        mTabView.setCurrentTab(mCurrentTabIndex);
        //mTabView.setCurrentTab(0);
        mCurrentFragment = new HomeFragment();
        FragmentUtil.replaceFragment(mFragmentManager, R.id.layout_content,HomeFragment.class, null, false);
    }
    
    
   
    
    
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        
       
        //Fragment f = mFragmentManager.findFragmentByTag(curFragmentTag);
        /*然后在碎片中调用重写的onActivityResult方法*/
        mCurrentFragment.onActivityResult(requestCode, resultCode, data);
        
        switch (requestCode) {/*
            case BaseActivity.REQUEST_OK_LOGIN:
                if (resultCode == RESULT_OK) {
                    ApplicationUtils.showToast(this, R.string.text_loginsuccess);
                    mTitleTextView.setText(R.string.text_tab_profile);
                    final ProfileFragment profileFragment =
                            (ProfileFragment) mFragmentManager.findFragmentByTag(ProfileFragment.class.getSimpleName());
                    if (profileFragment != null) {
                        Log.d(TAG, "ProfileFragment is refreshing.");
                        profileFragment.refreshViews();
                    } else {
                        Log.e(TAG, "ProfileFragment is null.");
                    }
                } else {
                    // 返回原来的页面
                    mTabView.setCurrentTab(mPreviousTabIndex);
                    ApplicationUtils.showToast(this, R.string.toast_login_failed);
                }
                break;

            default:
                break;
        */}
    }
    
    
    
    
    @Override
    public void onFragmentCallback(Fragment fragment, int id, Bundle args) {
        mTabView.setCurrentTab(1);
    }
    @Override
    public void onTabChange(String tag) {
        
    	if(tag==null)
    		 tag="home";
		 
        if (tag != null) {
            if (tag.equals("home")) {
                mPreviousTabIndex = mCurrentTabIndex;
                mCurrentTabIndex = 0; 
                replaceFragment(HomeFragment.class);
                // 检查，如果没有登录则跳转到登录界面
              /*  final UserConfigManager manager = UserConfigManager.getInstance();
                if (manager.getId() <= 0) {
                    startActivityForResult(new Intent(this, LoginActivity.class),
                            BaseActivity.REQUEST_OK_LOGIN);
                }*/
            }  
            if ("mine".equals(tag)) {
                mPreviousTabIndex = mCurrentTabIndex;
                mCurrentTabIndex = 1; 
                replaceFragment(MineFragment.class);
            }  
            if (tag.equals("setting")) {
                mPreviousTabIndex = mCurrentTabIndex;
                mCurrentTabIndex = 2; 
                replaceFragment(SettingFragment.class);
                // 检查，如果没有登录则跳转到登录界面
              /*  final UserConfigManager manager = UserConfigManager.getInstance();
                if (manager.getId() <= 0) {
                    startActivityForResult(new Intent(this, LoginActivity.class),
                            BaseActivity.REQUEST_OK_LOGIN);
                }*/
            }  
        }
    }
    
    private void replaceFragment(Class<? extends Fragment> newFragment) {

        mCurrentFragment = FragmentUtil.switchFragment(mFragmentManager,
                R.id.layout_content, mCurrentFragment,
                newFragment, null, false);
    }
    
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
            	Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_LONG).show();
                
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
	 
	 
	 
}

package com.ris.mobile.ecloud.fragment;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.Header;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.SimpleOnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.gson.reflect.TypeToken;
import com.ris.mobile.ecloud.BaseFragment;
import com.ris.mobile.ecloud.BaseFragmentActivity;
import com.ris.mobile.ecloud.R;
import com.ris.mobile.ecloud.BaseActivity.DataCallback;
import com.ris.mobile.ecloud.activity.ICCardActivity;
import com.ris.mobile.ecloud.activity.ICCardResActivity;
import com.ris.mobile.ecloud.activity.MainActivity;
import com.ris.mobile.ecloud.activity.WebViewActivity;
import com.ris.mobile.ecloud.adapter.ArticleAdapter;
import com.ris.mobile.ecloud.log.CommonLog;
import com.ris.mobile.ecloud.log.LogFactory;
import com.ris.mobile.ecloud.object.ArticleObject;
import com.ris.mobile.ecloud.object.ConnectErrorObject;
import com.ris.mobile.ecloud.object.RequestObject;
import com.ris.mobile.ecloud.parser.ArticleListParser;
import com.ris.mobile.ecloud.parser.BaseParser;
import com.ris.mobile.ecloud.util.CommonUtil;
import com.ris.mobile.ecloud.util.Constant;
import com.ris.mobile.ecloud.widget.PullToRefreshLayout;
import com.ris.mobile.ecloud.widget.PullLoadMoreRecyclerView;
import com.ris.mobile.ecloud.widget.PullToRefreshLayout.OnRefreshListener;
import com.ris.mobile.ecloud.widget.pullableview.PullableListView;

public class ArticlePageFragment extends  BaseFragment implements OnRefreshListener {
 

	private LayoutInflater mLayoutInflater;
	private String mTag_id;
	private View mContentView;
	private PullToRefreshLayout refreshView;
	private LinearLayout llNoRecord;
	private PullableListView lvArticle;
	private List<ArticleObject> data;
	private ArticleAdapter mAdapter;
	private int pageIndex = 1;
	private int pageSize = 15;
	private ViewPager head_img;
	private Context mContext;
	private BaseFragmentActivity mainActivity;

	private String selfTag;

	public String getSelfTag() {
		return selfTag;
	}

	public void setSelfTag(String selfTag) {
		this.selfTag = selfTag;
	} 
	@Override
	public void onAttach(Activity activity) {
		log.e("onAttach");
		super.onAttach(activity);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		log.e("onActivityCreated");
		super.onActivityCreated(savedInstanceState);
	}
	@Override
	public void onViewStateRestored(Bundle savedInstanceState) {
		log.e("onViewStateRestored");
		super.onActivityCreated(savedInstanceState);
	}
	
	@Override
	public void onStart() {
		log.e("onStart");
		super.onStart();
	}
	
	@Override
	public void onResume() {
		log.e("onResume");
		 super.onResume();
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		log.e("onCreate");
		super.onCreate(savedInstanceState);
	}
	@Override
	public void onSaveInstanceState(Bundle outState) {
		log.e("onSaveInstanceState");
		
	}
	
	public void onPause() {
		log.e("onPause");
		 super.onPause();
	}

	public void onStop() {
		log.e("onStop");
		 super.onStop();
	}

	public void onLowMemory() {
		log.e("onLowMemory");
		super.onLowMemory();
	}

	public void onDestroyView() {
		log.e("onDestroyView");
		super.onDestroyView();
	}
	public void onDestroy() {
		log.e("onDestroy");
		super.onDestroy();
	}
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		log.e("onConfigurationChanged");
		 super.onConfigurationChanged(newConfig);
	}
	public static ArticlePageFragment newInstance(String tag_id) {
		log.e("newInstance");
		ArticlePageFragment articleFragment = new ArticlePageFragment();
		Bundle args = new Bundle();
		args.putString(Constant.BundleKey.PAGE_TAG_ID, tag_id);
		articleFragment.setArguments(args);
		return articleFragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		 
		log.e("onCreateView");
		
		if(mContentView==null){
			Bundle bundle = getArguments();
			mLayoutInflater = inflater;
			mTag_id = bundle.getString(Constant.BundleKey.PAGE_TAG_ID);
			//mContentView = inflater.inflate(R.layout.fragment_article, null);
			mContentView=  inflater.inflate(R.layout.fragment_article, container,false);;
			mContext = this.getActivity();
			mainActivity = (BaseFragmentActivity) this.getActivity();
			initView(savedInstanceState);
			setListener();
		 }
	 
		 
		  ViewGroup parent = (ViewGroup) mContentView.getParent();
		 
		  if (parent != null) {
		    parent.removeView(mContentView);
		  } 
		
		return mContentView;
	}

	@SuppressWarnings("unchecked")
	private void initView(Bundle savedInstanceState) {

		log.e("initView");
		lvArticle = (PullableListView) mContentView
				.findViewById(R.id.lv_Article);
		refreshView = ((PullToRefreshLayout) mContentView
				.findViewById(R.id.refresh_view));
		llNoRecord = (LinearLayout) mContentView.findViewById(R.id.ll_NoRecord);
		llNoRecord.setVisibility(View.GONE);

		data = new ArrayList<ArticleObject>();
		mAdapter = new ArticleAdapter(mContext, data);
		lvArticle.setAdapter(mAdapter);
		 
			new Handler().postDelayed(  new Runnable(){  
				   @Override  
				   public void run() { 
					   
					   refreshView.autoRefresh();
				   }   
				} , 500); // 延迟2秒，再运行splashhandler的run()
			
		 
		

		/*
		 * if (savedInstanceState != null) { ArrayList<ArticleItme> data =
		 * (ArrayList<ArticleItme>) savedInstanceState .getSerializable("data");
		 * page = savedInstanceState.getInt("page"); articleAdapter = new
		 * ArticleAdapter(getActivity());
		 * mPullListView.setAdapter(articleAdapter);
		 * articleAdapter.addData(data); } else { getdata(true, true); }
		 */
	}

	/*
	 * @Override public void onSaveInstanceState(Bundle outState) {
	 * super.onSaveInstanceState(outState); if (articleAdapter != null) {
	 * ArrayList<ArticleItme> data = articleAdapter.getData();
	 * outState.putSerializable("data", data); outState.putInt("page", page); }
	 * 
	 * }
	 */
	public void setListener() {
		refreshView.setOnRefreshListener(this);
		lvArticle.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				ArticleObject recruitObject = (ArticleObject) parent
						.getAdapter().getItem(position);
				Bundle myBundel = new Bundle();
				myBundel.putString("wapUrl",
						CommonUtil.trim(recruitObject.getUrl()));
				myBundel.putString("wapTitle",
						CommonUtil.trim(recruitObject.getTitle()));

				mainActivity.openActivity(WebViewActivity.class, myBundel);
			}
		});

	}

	 

	public boolean onLoadData(final PullToRefreshLayout pullToRefreshLayout,
			final boolean isRefresh) {

		if (isRefresh) {
			pageIndex = 1;
			data.clear();
		} else {
			pageIndex++;
		}
		pageIndex = pageIndex < 1 ? 1 : pageIndex;

		BaseParser<List<ArticleObject>> responseParser = new ArticleListParser();

		HashMap<String, String> requestDataMap = new HashMap<String, String>();

		requestDataMap.put("pageNo", new Integer(pageIndex).toString());
		requestDataMap.put("pageSize", new Integer(pageSize).toString());
		requestDataMap.put("categoryId", mTag_id);

		RequestObject vo = new RequestObject(R.string.url_articlelist,
				mContext, requestDataMap, responseParser);
		mainActivity.getDataFromServer(vo,
				new BaseFragmentActivity.DataCallback<List<ArticleObject>>() {
					@Override
					public void processData(List<ArticleObject> paramObject,
							boolean paramBoolean) {

						if (paramObject != null && paramObject.size() > 0)
							data.addAll(paramObject);
						else
							pageIndex--;
						if (data.size() > 0)
							llNoRecord.setVisibility(View.GONE);
						else
							llNoRecord.setVisibility(View.VISIBLE);
						mAdapter.notifyDataSetChanged();
						if (isRefresh)
							pullToRefreshLayout
									.refreshFinish(PullToRefreshLayout.SUCCEED);
						else
							pullToRefreshLayout
									.loadmoreFinish(PullToRefreshLayout.SUCCEED);
						return;
					}

					@Override
					public void processError(ConnectErrorObject responseErrorVo) {
						if (isRefresh)
							pullToRefreshLayout
									.refreshFinish(PullToRefreshLayout.SUCCEED);
						else
							pullToRefreshLayout
									.loadmoreFinish(PullToRefreshLayout.SUCCEED);
						mainActivity.DisPlay("error",
								responseErrorVo.getErrInfo());

					}
				}, false);

		return false;
	}

	@Override
	public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
		onLoadData(pullToRefreshLayout, true);
		// pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
	}

	@Override
	public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout) {

		onLoadData(pullToRefreshLayout, false);
		// pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
	}

	 

}

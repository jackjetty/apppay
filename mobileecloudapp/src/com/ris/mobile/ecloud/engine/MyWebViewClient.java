package com.ris.mobile.ecloud.engine;
 

import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class MyWebViewClient extends WebViewClient{

	ProgressBar mProgressBar;
	
	public MyWebViewClient(ProgressBar mProgressBar) {
		super();
		this.mProgressBar = mProgressBar;
	}
	@Override
	public WebResourceResponse shouldInterceptRequest(WebView view,
			String url) {
		WebResourceResponse response = null;
		response = super.shouldInterceptRequest(view, url);
		return response;
	}
	@Override
	public boolean shouldOverrideUrlLoading(WebView view, String url) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void onPageFinished(WebView view, String url) {
		// TODO Auto-generated method stub
		mProgressBar.setVisibility(View.GONE);
		super.onPageFinished(view, url);
	}
	@Override
	public void onPageStarted(WebView view, String url, Bitmap favicon) {
		// TODO Auto-generated method stub
		mProgressBar.setVisibility(View.VISIBLE);
		super.onPageStarted(view, url, favicon);
	}
}

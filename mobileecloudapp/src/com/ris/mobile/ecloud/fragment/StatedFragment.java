package com.ris.mobile.ecloud.fragment; 
import java.util.ArrayList;
import java.util.Random; 

import android.content.ContentValues;
import android.content.Context; 
import android.content.Intent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.ris.mobile.ecloud.R;   
import com.ris.mobile.ecloud.activity.WebViewActivity;
import com.ris.mobile.ecloud.adapter.EmployAdapter;
import com.ris.mobile.ecloud.BaseActivity;
import com.ris.mobile.ecloud.LApplication;
 
import com.ris.mobile.ecloud.log.CommonLog;
import com.ris.mobile.ecloud.log.LogFactory; 
import com.ris.mobile.ecloud.object.ConnectErrorObject;
 
import com.ris.mobile.ecloud.object.EmployObject;
import com.ris.mobile.ecloud.object.RequestObject; 
import com.ris.mobile.ecloud.parser.BaseParser; 
import com.ris.mobile.ecloud.parser.EmployListParser;
import com.ris.mobile.ecloud.util.CommonUtil;
import com.ris.mobile.ecloud.util.SharedPreferenceUtil; 
import com.ris.mobile.ecloud.widget.PullToRefreshLayout;
import com.ris.mobile.ecloud.widget.PullToRefreshLayout.OnRefreshListener; 
import com.ris.mobile.ecloud.widget.pullableview.PullableListView;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;  
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;  
import android.text.InputType;
import android.view.LayoutInflater;  
import android.view.MotionEvent;
import android.view.View;  
import android.view.View.OnTouchListener;
import android.view.ViewGroup;  
import android.view.View.OnClickListener; 
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView; 
import android.widget.Toast;
      
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
  
 
/**
 * Created by nuuneoi on 11/16/2014.
 */
public class StatedFragment extends Fragment {
 
    Bundle savedState;
 
    public StatedFragment() {
        super();
    }
 
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Restore State Here
        if (!restoreStateFromArguments()) {
            // First Time, Initialize something here
            onFirstTimeLaunched();
        }
    }
 
    protected void onFirstTimeLaunched() {
 
    }
 
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save State Here
        saveStateToArguments();
    }
 
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Save State Here
        saveStateToArguments();
    }
 
    ////////////////////
    // Don't Touch !!
    ////////////////////
 
    private void saveStateToArguments() {
        if (getView() != null)
            savedState = saveState();
        if (savedState != null) {
            Bundle b = getArguments();
            b.putBundle("tt", savedState);
        }
    }
 
    ////////////////////
    // Don't Touch !!
    ////////////////////
 
    private boolean restoreStateFromArguments() {
        Bundle b = getArguments();
        savedState = b.getBundle("tt");
        if (savedState != null) {
            restoreState();
            return true;
        }
        return false;
    }
 
    /////////////////////////////////
    // Restore Instance State Here
    /////////////////////////////////
 
    private void restoreState() {
        if (savedState != null) {
            // For Example
            //tv1.setText(savedState.getString(text));
            onRestoreState(savedState);
        }
    }
 
    protected void onRestoreState(Bundle savedInstanceState) {
 
    }
 
    //////////////////////////////
    // Save Instance State Here
    //////////////////////////////
 
    private Bundle saveState() {
        Bundle state = new Bundle();
        // For Example
        //state.putString(text, tv1.getText().toString());
        onSaveState(state);
        return state;
    }
 
    protected void onSaveState(Bundle outState) {
 
    }
}
	
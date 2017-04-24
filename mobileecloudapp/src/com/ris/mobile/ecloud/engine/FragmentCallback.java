package com.ris.mobile.ecloud.engine;

import android.os.Bundle;
import android.support.v4.app.Fragment;

 
public interface FragmentCallback {
    public void onFragmentCallback(Fragment fragment, int id, Bundle args);
}
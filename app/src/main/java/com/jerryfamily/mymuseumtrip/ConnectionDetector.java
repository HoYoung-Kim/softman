package com.jerryfamily.mymuseumtrip;

import android.app.Service;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 네트워크 체크 기능
 */

public class ConnectionDetector {

    Context mContext;

    public ConnectionDetector(Context mContext) {
        this.mContext = mContext;
    }

    public boolean isConnected(){

        ConnectivityManager connectivityManager = (ConnectivityManager)
                mContext.getSystemService(Service.CONNECTIVITY_SERVICE);

        if(connectivityManager != null){
            NetworkInfo info = connectivityManager.getActiveNetworkInfo();
            if(info != null){
                if(info.getState() == NetworkInfo.State.CONNECTED){
                    return true;
                }
            }
        }

        return false;
    }
}

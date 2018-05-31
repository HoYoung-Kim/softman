package com.jerryfamily.mymuseumtrip;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * 네트워크 상태 체크
 */

public class NetWorkManager {

    public static Boolean isNetWork(Context context){

        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean isMobileAvailable = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isAvailable();
        boolean isMobileConnect = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();
        boolean isWifiAvailable = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isAvailable();
        boolean isWifiConnect = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();

        if ((isWifiAvailable && isWifiConnect) || (isMobileAvailable && isMobileConnect)){
            return true;
        }else{
            return false;
        }
    }
}

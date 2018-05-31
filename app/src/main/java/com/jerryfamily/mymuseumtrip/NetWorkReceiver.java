package com.jerryfamily.mymuseumtrip;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;
import android.widget.Toast;

/*
* 네크워크 브로드캐스트 리시버
* */
public class NetWorkReceiver extends BroadcastReceiver {

    private static final String TAG = NetWorkErrorActivity.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {

        String broadcastName = intent.getAction();

        if(broadcastName.equals(ConnectivityManager.CONNECTIVITY_ACTION)){
            if(NetWorkManager.isNetWork(context)){
                // Toast.makeText(context, "네트워크에 연결 됨 1", Toast.LENGTH_SHORT).show();

                /*
                Intent i = new Intent(context, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP |
                            Intent.FLAG_ACTIVITY_CLEAR_TOP |
                            Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
                */
            }
            else {
                // Toast.makeText(context, "네트워크에 연결 안 됨 1", Toast.LENGTH_SHORT).show();

                /*
                Intent i = new Intent(context, NetWorkErrorActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP |
                            Intent.FLAG_ACTIVITY_CLEAR_TOP |
                            Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
                */
            }
        }
    }
}

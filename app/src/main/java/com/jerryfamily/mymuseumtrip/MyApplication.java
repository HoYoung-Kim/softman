package com.jerryfamily.mymuseumtrip;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

/**
 * Created by 호영 on 2018-05-27.
 */

public class MyApplication extends Application {

    public static boolean isDEBUG;
    //public boolean isDEBUG = true;

    @Override
    public void onCreate() {
        super.onCreate();

        this.isDEBUG = isDebuggable(this);
    }

    /**
     * 현재 디버그모드여부를 리턴
     *
     * @param context
     * @return
     */
    private boolean isDebuggable(Context context) {
        boolean debuggable = false;

        PackageManager pm = context.getPackageManager();
        try {
            ApplicationInfo appinfo = pm.getApplicationInfo(context.getPackageName(), 0);
            debuggable = (0 != (appinfo.flags & ApplicationInfo.FLAG_DEBUGGABLE));
        } catch (PackageManager.NameNotFoundException e) {
            /* debuggable variable will remain false */
        }

        return debuggable;
    }
}

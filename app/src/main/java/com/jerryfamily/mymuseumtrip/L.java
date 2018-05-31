package com.jerryfamily.mymuseumtrip;

import android.util.Log;

/**
 * 로그 클래스
 * http://gun0912.tistory.com/12
 */

public class L {

    /** Log Level Error **/
    public static final void e(String TAG, String message) {
        if (MyApplication.isDEBUG)
            Log.e(TAG, buildLogMsg(message));
    }

    /** Log Level Warning **/
    public static final void w(String TAG, String message) {
        if (MyApplication.isDEBUG)
            Log.w(TAG, buildLogMsg(message));
    }

    /** Log Level Information **/
    public static final void i(String TAG, String message) {
        if (MyApplication.isDEBUG)
            Log.i(TAG, buildLogMsg(message));
    }

    /** Log Level Debug **/
    public static final void d(String TAG, String message) {
        if (MyApplication.isDEBUG)
            Log.d(TAG, buildLogMsg(message));
    }

    /** Log Level Verbose **/
    public static final void v(String TAG, String message) {
        if (MyApplication.isDEBUG)
            Log.v(TAG, buildLogMsg(message));
    }

    public static String buildLogMsg(String message) {
        StackTraceElement ste = Thread.currentThread().getStackTrace()[4];

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(ste.getFileName().replace(".java", ""));
        sb.append("::");
        sb.append(ste.getMethodName()); sb.append("]");
        sb.append(message);

        return sb.toString();
    }
}

package com.jerryfamily.mymuseumtrip;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/*
* firebase 토큰 생성하는 서비스
* */
public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "FirebaseIIDService";

    public MyFirebaseInstanceIDService() {
    }

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        L.d(TAG, "Refreshed token: " + refreshedToken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        // sendRegistrationToServer(refreshedToken);
    }
}

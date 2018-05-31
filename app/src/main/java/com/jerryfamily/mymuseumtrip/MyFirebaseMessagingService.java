package com.jerryfamily.mymuseumtrip;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/*
* firebase 메시지 수신하는 서비스
* */
public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "FirebaseMSGService";

    public MyFirebaseMessagingService() {
    }

    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        // L.d(TAG, "getFrom = " + remoteMessage.getFrom());

        if (remoteMessage.getData().size() > 0){
            L.d(TAG, "Message data = " + remoteMessage.getData().toString());
        }

        if(remoteMessage.getNotification() != null) {
            L.d(TAG, "Message body = " + remoteMessage.getNotification().getBody());

            //sendNotification(remoteMessage.getData().get("message"));
            sendNotification(remoteMessage.getNotification().getBody());
        }
    }

    private void sendNotification(String messageBody) {
        Intent intent = new Intent(this, Main2Activity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, getString(R.string.default_notification_channel_id))
                        .setSmallIcon(R.drawable.ic_stat_ic_notification)
                        .setContentTitle(getString(R.string.app_name)) // 이부분은 어플 켜놓은 상태에서 알림 메세지 받으면 저 텍스트로 띄워준다.
                        .setTicker(getString(R.string.app_name))
                        .setContentText(messageBody)
                        .setAutoCancel(true)
                        .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE)
                        .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }
}

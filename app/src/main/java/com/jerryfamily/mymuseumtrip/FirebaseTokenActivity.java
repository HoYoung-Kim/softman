package com.jerryfamily.mymuseumtrip;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.iid.FirebaseInstanceId;

public class FirebaseTokenActivity extends AppCompatActivity {

    private static final String TAG = "FirebaseTokenActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_token);

        Button button = findViewById(R.id.button_firebase_token);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String token = FirebaseInstanceId.getInstance().getToken();

                L.d(TAG, "token=" + token);
            }
        });
    }
}

package com.jerryfamily.mymuseumtrip;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class NetWorkErrorActivity extends MyBaseActivity {

    private static final String TAG = NetWorkErrorActivity.class.getSimpleName();

    private Toolbar toolbar;
    private Button button_network_retry;
    private ConnectionDetector connectionDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_work_error);

        // 메뉴바 변수에 할당...
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null){
            // 타이틀바에 프로젝트명 제거하기
            getSupportActionBar().setTitle("");
            getSupportActionBar().setSubtitle("");

            // 상단 왼쪽에 백버튼 달기
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_back);
        }

        // toolbar text 변수
        TextView toolbarText = (TextView) findViewById(R.id.toolbartext);
        toolbarText.setText(getString(R.string.network_error_title));

        // 재시도 버튼 활성화 시키기
        setOnClickListener();
    }

    private void setOnClickListener() {
        button_network_retry = findViewById(R.id.button_network_retry);
        button_network_retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 1. 네트워크 체크하기...
                connectionDetector = new ConnectionDetector(getApplicationContext());
                if(connectionDetector.isConnected()){
                    Toast.makeText(getApplicationContext(), getString(R.string.network_connect), Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), getString(R.string.network_disconnect), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

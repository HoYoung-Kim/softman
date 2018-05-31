package com.jerryfamily.mymuseumtrip;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class VersionActivity extends MyBaseActivity {

    private static final String TAG = VersionActivity.class.getSimpleName();

    private Toolbar toolbar;
    private String store_version = "";
    private String device_version;
    private Button button_version_update;
    private TextView textView_current_version;
    private TextView textView_latest_version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_version);

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
        toolbarText.setText("버전정보");

        // 변수 정의
        textView_current_version = findViewById(R.id.textView_current_version);
        textView_latest_version = findViewById(R.id.textView_latest_version);

        // 디바이스 버전정보 가져오기
        device_version = MarketVersionChecker.getDeviceVersion(this, getPackageName());
        textView_current_version.setText("v" + device_version);

        // 플레이구글 등록 앱 버전 가져오기
        new Thread() {
            public void run() {
                String version = MarketVersionChecker.getMarketVersion(getPackageName());

                Bundle bun = new Bundle();
                bun.putString("store_version", version);
                Message msg = handler.obtainMessage();
                msg.setData(bun);
                handler.sendMessage(msg);
            }
        }.start();
    }

    //
    // 버튼 클릭 리스너
    //
    private void initButton() {

        button_version_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }
            }
        });
    }

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            Bundle bun = msg.getData();
            store_version = bun.getString("store_version");

            // 플레이 구글 버전 설정
            textView_latest_version.setText("v" + store_version);

            // 버튼 클릭 리스너
            button_version_update = findViewById(R.id.button_version_update);
            if (store_version.compareTo(device_version) > 0) {  // 업데이트 필요
                initButton();
            }
            else {        // 업데이트 불필요
                button_version_update.setBackgroundResource(R.color.colorGrey);
                button_version_update.setText("현재 최신 버전입니다.");
                button_version_update.setEnabled(false);
            }
        }
    };
}

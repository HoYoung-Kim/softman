package com.jerryfamily.mymuseumtrip;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.widget.TextView;


public class ProjectInquiryActivity extends MyBaseActivity {

    private static final String TAG = ProjectInquiryActivity.class.getSimpleName();

    private Toolbar toolbar;
    private WebView webView_projectInquiry;
    private TextView projectContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_inquiry);

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
        toolbarText.setText(getString(R.string.menu_project_inquiry_title));

    }
}

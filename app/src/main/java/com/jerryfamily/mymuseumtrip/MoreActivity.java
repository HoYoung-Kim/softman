package com.jerryfamily.mymuseumtrip;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kakao.kakaolink.KakaoLink;
import com.kakao.kakaolink.KakaoTalkLinkMessageBuilder;
import com.kakao.util.KakaoParameterException;

import org.w3c.dom.Text;

public class MoreActivity extends MyBaseActivity {

    private static final String TAG = MoreActivity.class.getSimpleName();

    private Toolbar toolbar;
    private LinearLayout firstLinearlayout;
    private LinearLayout inquiryLinearlayout;
    private LinearLayout kakaoTalkLinearlayout;
    private LinearLayout directionLinearlayout;
    private LinearLayout projectLinearlayout;
    private TextView textView_version;
    private String device_version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);

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
        toolbarText.setText(getString(R.string.menu_more_title));

        // 디바이스에 설치된 버전 보이기
        try {
            device_version = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            device_version = "";
            e.printStackTrace();
        }

        // 버전 정보 설정
        TextView textView_version = findViewById(R.id.textView_version);
        textView_version.setText(device_version);

        // 링크 활성화
        initLinkCreate();
    }

    private void initLinkCreate() {

        // 버전정보
        firstLinearlayout = findViewById(R.id.firstLinearlayout);
        firstLinearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MoreActivity.this, VersionActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intent);
            }
        });

        // 문의 건의
        inquiryLinearlayout = findViewById(R.id.inquiryLinearlayout);
        inquiryLinearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MoreActivity.this, InquiryActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intent);
            }
        });

        // 사용법
        directionLinearlayout = findViewById(R.id.directionLinearlayout);
        directionLinearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MoreActivity.this, DirectionActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intent);
            }
        });

        // 카카오톡으로 공유
        kakaoTalkLinearlayout = findViewById(R.id.kakaoTalkLinearlayout);
        kakaoTalkLinearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    KakaoLink kakaoLink = KakaoLink.getKakaoLink(getApplicationContext());
                    KakaoTalkLinkMessageBuilder kakaoTalkLinkMessageBuilder = kakaoLink.createKakaoTalkLinkMessageBuilder();
                    kakaoTalkLinkMessageBuilder.addText(getString(R.string.app_name));
                    kakaoTalkLinkMessageBuilder.addImage(ConstantManager.KAKAO_IMGSRC, 192, 192);
                    kakaoTalkLinkMessageBuilder.addWebButton(getString(R.string.kakao_message), ConstantManager.KAKAO_SITEURL);
                    kakaoLink.sendMessage(kakaoTalkLinkMessageBuilder.build(), MoreActivity.this);
                } catch (KakaoParameterException e) {
                    e.printStackTrace();
                }
            }
        });

        // 프로젝트 문의
        projectLinearlayout = findViewById(R.id.projectLinearlayout);
        projectLinearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MoreActivity.this, ProjectInquiryActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intent);
            }
        });
    }
}

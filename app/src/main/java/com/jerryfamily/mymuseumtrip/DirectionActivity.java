package com.jerryfamily.mymuseumtrip;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;
import android.widget.Toast;

public class DirectionActivity extends MyBaseActivity {

    private static final String TAG = DirectionActivity.class.getSimpleName();

    private Toolbar toolbar;
    private TextView textView_direction1, textView_direction2, textView_direction3, textView_direction4;
    private SpannableStringBuilder spannableStringBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direction);

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
        toolbarText.setText(getString(R.string.menu_direction_title));

        // 본문 내용 입력하기
        textView_direction1 = findViewById(R.id.textView_direction1);
        textView_direction2 = findViewById(R.id.textView_direction2);
        textView_direction3 = findViewById(R.id.textView_direction3);
        textView_direction4 = findViewById(R.id.textView_direction4);

        spannableStringBuilder = new SpannableStringBuilder(getResources().getString(R.string.direction_num_1));
        spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#"+Integer.toHexString(ContextCompat.getColor(this, R.color.colorBlueLight)))), 11, 19, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView_direction1.setText(spannableStringBuilder);

        spannableStringBuilder = new SpannableStringBuilder(getResources().getString(R.string.direction_num_2));
        spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#"+Integer.toHexString(ContextCompat.getColor(this, R.color.colorBlueLight)))), 32, 37, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView_direction2.setText(spannableStringBuilder);

        spannableStringBuilder = new SpannableStringBuilder(getResources().getString(R.string.direction_num_3));
        spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#"+Integer.toHexString(ContextCompat.getColor(this, R.color.colorBlueLight)))), 23, 28, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView_direction3.setText(spannableStringBuilder);

        textView_direction4.setText(getResources().getString(R.string.direction_num_4));

    }
}

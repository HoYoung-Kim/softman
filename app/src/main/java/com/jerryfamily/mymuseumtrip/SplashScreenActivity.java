package com.jerryfamily.mymuseumtrip;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

public class SplashScreenActivity extends AppCompatActivity {

    private static final String TAG = SplashScreenActivity.class.getSimpleName();

    private SpannableStringBuilder spannableStringBuilder;
    private TextView textView_splash_text_1, textView_splash_text_2, textView_splash_text_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // 초기 데이타 받기
        new AsyncTaskInit(this).execute();

        // 문구 입력 및 색 변경
        textView_splash_text_1 = findViewById(R.id.textView_splash_text_1);
        textView_splash_text_2 = findViewById(R.id.textView_splash_text_2);
        textView_splash_text_3 = findViewById(R.id.textView_splash_text_3);

        textView_splash_text_1.setText(getString(R.string.splash_text_1));

        spannableStringBuilder = new SpannableStringBuilder(getString(R.string.splash_text_2));
        spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#"+Integer.toHexString(ContextCompat.getColor(this, R.color.colorYellow)))), 0, 22, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView_splash_text_2.setText(spannableStringBuilder);

        textView_splash_text_3.setText(getString(R.string.splash_text_3));

        // splash screen 을 4초 동안 띄우고 메인 화면으로 이동함...
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, Main2Activity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP |
                                Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

                // 화면 전환 효과
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);

                finish();
            }
        }, ConstantManager.SPLASH_TIME_OUT);
    }
}

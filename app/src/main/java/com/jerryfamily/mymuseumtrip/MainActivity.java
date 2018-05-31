package com.jerryfamily.mymuseumtrip;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends MyBaseActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private Toolbar toolbar;
    private ImageView button_category1, button_category2, button_category3, button_category4 ;
    private ImageView button_category5, button_category6, button_category7;
    private TextView textView_num_1, textView_num_2, textView_num_3, textView_num_4 ;
    private TextView textView_num_5, textView_num_6, textView_num_7 ;

    private AlphaAnimation imageViewAlphaClick = new AlphaAnimation(1F, 0.6F);  // 이미지 뷰 클릭 효과

    private IntentFilter filter;
    private NetWorkReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 2. 메뉴바 변수에 할당...
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 타이틀바에 프로젝트명 제거하기
        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle("");
            getSupportActionBar().setSubtitle("");
        }

        // Toolbar 에 타이틀 입력하기...
        TextView toolbarText = (TextView) findViewById(R.id.toolbartext);
        toolbarText.setText(getString(R.string.app_name));

        // 3. 버튼 링크 활성화 시키기....
        setOnClickListener();

        // 4. 초기 데이타 받기 - 버전 정보
        new AsyncTaskInit(this).execute();

        // 5. 해쉬키 값 받기 - kakaolink 를 위한 키값 생성
        // getKeyHash();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // 1. 네트워크 브로드캐스트에 등록함
//        filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
//        receiver = new NetWorkReceiver();
//        registerReceiver(receiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // 2. 네트워크 브로드캐스트에서 해제함
//        if (receiver != null){
//            unregisterReceiver(receiver);
//        }
    }

    //
    // get hash key
    //
    private void getKeyHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                                    "com.jerryfamily.mymuseumtrip",
                                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }

    public void onTaskComplete(String version){
//        SharedPrefer sf = new SharedPrefer(getApplicationContext());
//        sf.sharedPreference_write(ConstantManager.SF_VERSION_TAG, version);
    }

    /*
    *  버튼 클릭시 각각의 카테고리 리스트로 화면 전환하기
    *  예) 국립1, 국립2, 공립, 법인/사립, 학교, 기타, 외국
    * */
    private void setOnClickListener(){

        // 국립 1 버튼
        textView_num_1 = findViewById(R.id.textView_num_1);
        textView_num_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 인턴트로 화면 전화
                Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("category_code", getString(R.string.category1_code));
                startActivity(intent);
            }
        });

        // 국립 2 버튼
        textView_num_2 = findViewById(R.id.textView_num_2);
        textView_num_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 인턴트로 화면 전화
                Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("category_code", getString(R.string.category2_code));
                startActivity(intent);
            }
        });

        // 공립 버튼
        textView_num_3 = findViewById(R.id.textView_num_3);
        textView_num_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 인턴트로 화면 전화
                Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("category_code", getString(R.string.category3_code));
                startActivity(intent);
            }
        });

        // 법인/사립 버튼
        textView_num_4 = findViewById(R.id.textView_num_4);
        textView_num_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 인턴트로 화면 전화
                Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("category_code", getString(R.string.category4_code));
                startActivity(intent);
            }
        });

        // 학교 버튼
        textView_num_5 = findViewById(R.id.textView_num_5);
        textView_num_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 인턴트로 화면 전화
                Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("category_code", getString(R.string.category5_code));
                startActivity(intent);
            }
        });

        // 기타 버튼
        textView_num_6 = findViewById(R.id.textView_num_6);
        textView_num_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 인턴트로 화면 전화
                Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("category_code", getString(R.string.category6_code));
                startActivity(intent);
            }
        });

        // 외국 버튼
        textView_num_7 = findViewById(R.id.textView_num_7);
        textView_num_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 인턴트로 화면 전화
                Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("category_code", getString(R.string.category7_code));
                startActivity(intent);
            }
        });

        /*
        // 국립 1 버튼
        button_category1 = (ImageView) findViewById(R.id.button_category1);
        button_category1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 클릭 효과
                v.startAnimation(imageViewAlphaClick);

                // 인턴트로 화면 전환
                Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("category_code", getString(R.string.category1_code));
                startActivity(intent);
            }
        });

        // 국립 2 버튼
        button_category2 = (ImageView) findViewById(R.id.button_category2);
        button_category2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 클릭 효과
                v.startAnimation(imageViewAlphaClick);

                // 인턴트로 화면 전화
                Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("category_code", getString(R.string.category2_code));
                startActivity(intent);
            }
        });

        // 공립 버튼
        button_category3 = (ImageView) findViewById(R.id.button_category3);
        button_category3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 클릭 효과
                v.startAnimation(imageViewAlphaClick);

                // 인턴트로 화면 전화
                Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("category_code", getString(R.string.category3_code));
                startActivity(intent);
            }
        });

        // 법인/사립 버튼
        button_category4 = (ImageView) findViewById(R.id.button_category4);
        button_category4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 클릭 효과
                v.startAnimation(imageViewAlphaClick);

                // 인턴트로 화면 전화
                Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("category_code", getString(R.string.category4_code));
                startActivity(intent);
            }
        });

        // 학교 버튼
        button_category5 = (ImageView) findViewById(R.id.button_category5);
        button_category5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 클릭 효과
                v.startAnimation(imageViewAlphaClick);

                // 인턴트로 화면 전화
                Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("category_code", getString(R.string.category5_code));
                startActivity(intent);
            }
        });

        // 기타 버튼
        button_category6 = (ImageView) findViewById(R.id.button_category6);
        button_category6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 클릭 효과
                v.startAnimation(imageViewAlphaClick);

                // 인턴트로 화면 전화
                Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("category_code", getString(R.string.category6_code));
                startActivity(intent);
            }
        });

        // 외국 버튼
        button_category7 = (ImageView) findViewById(R.id.button_category7);
        button_category7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 클릭 효과
                v.startAnimation(imageViewAlphaClick);

                // 인턴트로 화면 전화
                Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("category_code", getString(R.string.category7_code));
                startActivity(intent);
            }
        });
        */
    }

    /*
    * 백 버튼을 2번 누룰때 앱 종료하는 method...
    * 2초간의 인터벌을 줌
    *
    * */
    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - ConstantManager.backPressedTime;

        if (0 <= intervalTime && ConstantManager.FINISH_INTERVAL_TIME >= intervalTime)
        {
            super.onBackPressed();
        }
        else
        {
            ConstantManager.backPressedTime = tempTime;
            Toast.makeText(getApplicationContext(), getString(R.string.finish_message), Toast.LENGTH_SHORT).show();
        }
    }
}

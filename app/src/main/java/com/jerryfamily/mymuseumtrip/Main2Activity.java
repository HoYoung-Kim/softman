package com.jerryfamily.mymuseumtrip;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends MyBaseActivity {

    private static final String TAG = Main2Activity.class.getSimpleName();

    // set widgets
    private Toolbar toolbar;
    private TextView textView_category1, textView_category2, textView_category3, textView_category4;
    private TextView textView_category5, textView_category6, textView_category7;
    private TextView textView_strange1, textView_strange2, textView_strange3;
    private TextView textView_strange4, textView_strange5;
    private TextView textView_strange_more, textView_exhibition_more;
    private ListView listView_exhibition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // 1. 메뉴바 변수에 할당...
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 타이틀바에 프로젝트명 제거하기
        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle("");
            getSupportActionBar().setSubtitle("");
        }

        // 2. Toolbar 에 타이틀 입력하기...
        TextView toolbarText = (TextView) findViewById(R.id.toolbartext);
        toolbarText.setText(getString(R.string.app_name));

        // 3. 데이타 받아서 링크 걸기
        initDataProcess();

        // 4. 이색 박물관 더보기
        setOnClickListenerStrangeMore();

        // 5. 전시회 정보 보여주기
        displayExhibition();

        // 6. 전시회 정보 더보기
        setOnClickListenerExhibitonMore();
    }

    //
    // 전시회 정보 더보기
    //
    private void setOnClickListenerExhibitonMore() {
        textView_exhibition_more = findViewById(R.id.textView_exhibition_more);
        textView_exhibition_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, ExhibitionActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    //
    // 전시회 정보 보여주기
    //
    private void displayExhibition() {
        List<ExhibitionInfo> exhibition_display = new ArrayList<>();
        if(ConstantManager.exhibitionInfos != null){
            exhibition_display = ConstantManager.exhibitionInfos.subList(0, 5);
        }

        MainExhibitionAdapter adapter = new MainExhibitionAdapter(this, exhibition_display);
        listView_exhibition = findViewById(R.id.listView_exhibition);
        listView_exhibition.setAdapter(adapter);

        listView_exhibition.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                FragmentManager manager = getFragmentManager();
                DialogFragment dialogFragment = ExhibitionInfoDialog.newInstance(position);
                dialogFragment.setCancelable(false);
                dialogFragment.show(manager, "ExhibitionDialog");
            }
        });
    }

    //
    // 이색 박물관 더보기
    //
    private void setOnClickListenerStrangeMore() {
        textView_strange_more = findViewById(R.id.textView_strange_more);
        textView_strange_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, StrangeMuseumActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    //
    // 위젯 찾기 및 데이타 입력
    //
    private void initDataProcess() {
        textView_category1 = findViewById(R.id.textView_category1);
        textView_category2 = findViewById(R.id.textView_category2);
        textView_category3 = findViewById(R.id.textView_category3);
        textView_category4 = findViewById(R.id.textView_category4);
        textView_category5 = findViewById(R.id.textView_category5);
        textView_category6 = findViewById(R.id.textView_category6);
        textView_category7 = findViewById(R.id.textView_category7);

        textView_strange1 = findViewById(R.id.textView_strange1);
        textView_strange2 = findViewById(R.id.textView_strange2);
        textView_strange3 = findViewById(R.id.textView_strange3);
        textView_strange4 = findViewById(R.id.textView_strange4);
        textView_strange5 = findViewById(R.id.textView_strange5);

        // 박물관 클릭 리스너 생성
        textView_category1.setOnClickListener(mClickListener);
        textView_category2.setOnClickListener(mClickListener);
        textView_category3.setOnClickListener(mClickListener);
        textView_category4.setOnClickListener(mClickListener);
        textView_category5.setOnClickListener(mClickListener);
        textView_category6.setOnClickListener(mClickListener);
        textView_category7.setOnClickListener(mClickListener);

        // 박물관 클릭 리스너 생성
        textView_strange1.setOnClickListener(smClickListener);
        textView_strange2.setOnClickListener(smClickListener);
        textView_strange3.setOnClickListener(smClickListener);
        textView_strange4.setOnClickListener(smClickListener);
        textView_strange5.setOnClickListener(smClickListener);

        // 이색 박물관 데이타 -> 변수에 할당
        if(ConstantManager.strangeMuseums != null && ConstantManager.strangeMuseums.size() >= 5) {

            textView_strange1.setText(ConstantManager.strangeMuseums.get(0).getTitle_display());
            textView_strange2.setText(ConstantManager.strangeMuseums.get(1).getTitle_display());
            textView_strange3.setText(ConstantManager.strangeMuseums.get(2).getTitle_display());
            textView_strange4.setText(ConstantManager.strangeMuseums.get(3).getTitle_display());
            textView_strange5.setText(ConstantManager.strangeMuseums.get(4).getTitle_display());

        }
    }

    //
    // 박물관 클릭 리스너
    //
    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Main2Activity.this, CategoryActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);

            switch (v.getId()){
                case R.id.textView_category1:
                    intent.putExtra("category_code", getString(R.string.category1_code));
                    break;
                case R.id.textView_category2:
                    intent.putExtra("category_code", getString(R.string.category2_code));
                    break;
                case R.id.textView_category3:
                    intent.putExtra("category_code", getString(R.string.category3_code));
                    break;
                case R.id.textView_category4:
                    intent.putExtra("category_code", getString(R.string.category4_code));
                    break;
                case R.id.textView_category5:
                    intent.putExtra("category_code", getString(R.string.category5_code));
                    break;
                case R.id.textView_category6:
                    intent.putExtra("category_code", getString(R.string.category6_code));
                    break;
                case R.id.textView_category7:
                    intent.putExtra("category_code", getString(R.string.category7_code));
                    break;
            }
            startActivity(intent);
        }
    };

    //
    // 이색 박물관 클릭 리스너
    //
    View.OnClickListener smClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            int position = 0;
            switch (v.getId()){
                case R.id.textView_strange1:
                    position = 0;
                    break;
                case R.id.textView_strange2:
                    position = 1;
                    break;
                case R.id.textView_strange3:
                    position = 2;
                    break;
                case R.id.textView_strange4:
                    position = 3;
                    break;
                case R.id.textView_strange5:
                    position = 4;
                    break;
            }

            FragmentManager manager = getFragmentManager();
            DialogFragment dialogFragment = StrangeMuseumDialog.newInstance(position);
            dialogFragment.setCancelable(false);
            dialogFragment.show(manager, "StrangeMuseumDialog");
        }
    };

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

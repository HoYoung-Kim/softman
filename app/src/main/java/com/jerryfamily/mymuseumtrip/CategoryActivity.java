package com.jerryfamily.mymuseumtrip;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


public class CategoryActivity extends MyBaseActivity {

    private static final String TAG = CategoryActivity.class.getSimpleName();

    private String category_code = "";
    private ViewPager viewPager = null;
    private Toolbar toolbar;
    private CategoryFragmentPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

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

        // 메인에서 넘겨 준 카테고리 코드 받기
        Intent intent = getIntent();
        if(intent != null) {
            category_code = intent.getStringExtra("category_code");
        }

        // Find the view pager that will allow the user to swipe between fragments
        viewPager = findViewById(R.id.viewpager);

        // Create an adapter that knows which fragment should be shown on each page
        adapter = new CategoryFragmentPagerAdapter(getSupportFragmentManager(), this);

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        // viewpager 에 addOnPageChangeListener 추가
        // tablayout 이 움직일때마다 상단 메뉴 변경
        addOnPageChangeListener();

        // 파라미터로 넘어 온 값에 따라 pageviewer 설정
        setCurrentFragmentNameTitle();
    }

    //
    // 파라미터로 넘어 온 값에 따라 pageviewer 설정
    //
    private void setCurrentFragmentNameTitle() {
        // toolbar text 변수
        TextView toolbarText = (TextView) findViewById(R.id.toolbartext);

        // Activate Tab with parameter received...
        if (category_code.equals(getString(R.string.category1_code))){
            viewPager.setCurrentItem(0);
            toolbarText.setText(getString(R.string.category1_name));
        } else if (category_code.equals(getString(R.string.category2_code))){
            viewPager.setCurrentItem(1);
            toolbarText.setText(getString(R.string.category2_name));
        } else if (category_code.equals(getString(R.string.category3_code))){
            viewPager.setCurrentItem(2);
            toolbarText.setText(getString(R.string.category3_name));
        } else if (category_code.equals(getString(R.string.category4_code))){
            viewPager.setCurrentItem(3);
            toolbarText.setText(getString(R.string.category4_name));
        } else if (category_code.equals(getString(R.string.category5_code))){
            viewPager.setCurrentItem(4);
            toolbarText.setText(getString(R.string.category5_name));
        } else if (category_code.equals(getString(R.string.category6_code))){
            viewPager.setCurrentItem(5);
            toolbarText.setText(getString(R.string.category6_name));
        } else if (category_code.equals(getString(R.string.category7_code))){
            viewPager.setCurrentItem(7);
            toolbarText.setText(getString(R.string.category7_name));
        }
    }

    //
    // viewpager 에 addOnPageChangeListener 추가
    // 현재 fragment에서 제목 가져오기
    //
    private void addOnPageChangeListener() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                CategoryFragmentPagerAdapter adapter = (CategoryFragmentPagerAdapter) viewPager.getAdapter();
                CharSequence pageTitle = adapter.getPageTitle(position);

                // toolbar text 변수
                TextView toolbarText = (TextView) findViewById(R.id.toolbartext);
                toolbarText.setText(pageTitle);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}

package com.jerryfamily.mymuseumtrip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.util.ArrayList;

public class ItemImageActivity extends MyBaseActivity
            implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener{

    private static final String TAG = ItemImageActivity.class.getSimpleName();

    private Toolbar toolbar;
    private String item_name;
    private ArrayList<String> imageListL = new ArrayList<>();      // 큰 사이즈 이미지 리스트

    private SliderLayout imageSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_image);

        Intent intent = getIntent();
        if(intent != null) {
            item_name = intent.getStringExtra("item_name");
            imageListL.addAll(intent.getStringArrayListExtra("imageListL"));
        }

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
        toolbarText.setText(item_name);

        // android image slider setting
        settingImageSlider();
    }

    //
    // android image slider setting
    //
    private void settingImageSlider() {
        imageSlider = (SliderLayout)findViewById(R.id.slider);

        if(imageListL.size() == 0){
            ImageView noImageView = findViewById(R.id.noImageView);

            noImageView.setVisibility(View.VISIBLE);
            imageSlider.setVisibility(View.GONE);
        } else {
            for (String imageUrl : imageListL) {
                TextSliderView textSliderView = new TextSliderView(this);
                // initialize a SliderLayout
                textSliderView
                        .description("")
                        .image(imageUrl)
                        .setScaleType(BaseSliderView.ScaleType.Fit)
                        .setOnSliderClickListener(this);

                //add your extra information
                textSliderView.bundle(new Bundle());
                textSliderView.getBundle().putString("extra", imageUrl);

                imageSlider.addSlider(textSliderView);
            }

            imageSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
            imageSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
            imageSlider.setCustomAnimation(new DescriptionAnimation());
            imageSlider.setDuration(2000);
            imageSlider.addOnPageChangeListener(this);
        }
    }

    //
    // 이미지 슬라이드 함수들
    //
    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    protected void onDestroy() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        if(imageSlider != null) {
            imageSlider.stopAutoCycle();
        }

        super.onDestroy();
    }
}

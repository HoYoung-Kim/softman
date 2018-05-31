package com.jerryfamily.mymuseumtrip;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
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

public class ItemDetailActivity extends MyBaseActivity
        implements TaskItemDetailCompleted,
                    BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    private static final String TAG = ItemDetailActivity.class.getSimpleName();

    private Toolbar toolbar;
    private String item_name;
    private String item_id;
    private ArrayList<String> imageListM;      // 중간 사이즈 이미지 리스트
    private ArrayList<String> imageListL;      // 큰 사이즈 이미지 리스트

    private SliderLayout imageSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        Intent intent = getIntent();
        if (intent != null){
            item_name = intent.getStringExtra("item_name");
            item_id = intent.getStringExtra("item_id");
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

        // 상세 정보 조회 - 테스트
        // item_id = "PS0100100100100840400000";
        new AsyncTaskItemDetail(this).execute(item_id);
    }

    //
    // android image slider setting
    //
    private void settingImageSlider() {
        imageSlider = (SliderLayout)findViewById(R.id.slider);

        if(imageListM.size() == 0){
            ImageView noImageView = findViewById(R.id.noImageView);

            noImageView.setVisibility(View.VISIBLE);
            imageSlider.setVisibility(View.GONE);
        } else {
            for (String imageUrl : imageListM) {
                TextSliderView textSliderView = new TextSliderView(this);
                // initialize a SliderLayout
                textSliderView
                        .description("")
                        .image(imageUrl)
                        .setScaleType(BaseSliderView.ScaleType.CenterInside)
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
    // return value from AsyncTask(AsyncTaskItemDetail)....
    //
    @Override
    public void onTaskComplete(ItemDetailInfo itemDetailInfo, APIResultData apiResultData) {

        if(apiResultData.getResultCode().equals("0000")) {
            TextView desc_nameKr        = findViewById(R.id.desc_nameKr);
            TextView desc_author        = findViewById(R.id.desc_author);
            TextView desc_purposeName2  = findViewById(R.id.desc_purposeName2);
            TextView desc_materialName1 = findViewById(R.id.desc_materialName1);
            TextView desc_sizeInfo      = findViewById(R.id.desc_sizeInfo);
            TextView desc_museumName2   = findViewById(R.id.desc_museumName2);
            TextView desc_desc          = findViewById(R.id.desc_desc);

            desc_nameKr.setText(itemDetailInfo.getNameKr());
            desc_author.setText(itemDetailInfo.getAuthor());
            desc_purposeName2.setText(itemDetailInfo.getPurposeName2());
            desc_materialName1.setText(itemDetailInfo.getMaterialName1());
            desc_sizeInfo.setText(itemDetailInfo.getSizeInfo());
            desc_museumName2.setText(itemDetailInfo.getMuseumName2());

            if(itemDetailInfo.getDesc() == null){
                desc_desc.setText(getString(R.string.field_no_desc));
            }
            else {
                desc_desc.setText(itemDetailInfo.getDesc());
            }

            // 이미지 할당
            imageListM = new ArrayList<>();
            imageListL = new ArrayList<>();

            imageListM.addAll(itemDetailInfo.getImageListM());
            imageListL.addAll(itemDetailInfo.getImageListL());

            for(int index = 0; index <imageListM.size(); index++){
                if(imageListM.get(index).indexOf("http://") == -1){
                    imageListM.set(index, "http://".concat(imageListM.get(index)));
                }
            }

            for(int index = 0; index <imageListL.size(); index++){
                if(imageListL.get(index).indexOf("http://") == -1){
                    imageListL.set(index, "http://".concat(imageListL.get(index)));
                }
            }

            settingImageSlider();
        }
        else {
            Toast.makeText(getApplicationContext(), apiResultData.getResultMsg(), Toast.LENGTH_SHORT).show();
        }
    }

    //
    // 이하는 이미지 슬라이더에서 사용하는 함수
    //
    @Override
    public void onSliderClick(BaseSliderView slider) {
        //Toast.makeText(this,slider.getBundle().get("extra") + "",Toast.LENGTH_SHORT).show();

        if(imageListL.size() > 0){
            Intent intent = new Intent(ItemDetailActivity.this, ItemImageActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("item_name", item_name);
            intent.putStringArrayListExtra("imageListL", imageListL);
            startActivity(intent);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);
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

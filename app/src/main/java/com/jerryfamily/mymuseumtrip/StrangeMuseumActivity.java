package com.jerryfamily.mymuseumtrip;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class StrangeMuseumActivity extends MyBaseActivity {

    private static final String TAG = StrangeMuseumActivity.class.getSimpleName();

    private Toolbar toolbar;
    private List<StrangeMuseum> strangeMuseums;
    private GridView gridView_thumbnail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strange_museum);

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

        // toolbar 에 제목 달기
        TextView toolbarText = (TextView) findViewById(R.id.toolbartext);
        toolbarText.setText(getString(R.string.strange_museum_title));

        // 그리드뷰에 데이타 보여주기
        initLoadData();
    }

    private void initLoadData() {

        gridView_thumbnail = findViewById(R.id.gridView_thumbnail);
        StrangeMuseumAdapter adapter = new StrangeMuseumAdapter(this, ConstantManager.strangeMuseums);
        gridView_thumbnail.setAdapter(adapter);

        gridView_thumbnail.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                // Toast.makeText(StrangeMuseumActivity.this, "" + position, Toast.LENGTH_SHORT).show();

                FragmentManager manager = getFragmentManager();
                DialogFragment newFragment = StrangeMuseumDialog.newInstance(position);
                newFragment.show(manager, "StrangeMuseumDialog");
            }
        });
    }
}

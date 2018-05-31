package com.jerryfamily.mymuseumtrip;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/*
* 전시회 정보 메인 화면
* */
public class ExhibitionActivity extends MyBaseActivity implements RowItemClickListener {

    private static final String TAG = ExhibitionActivity.class.getSimpleName();

    private Toolbar toolbar;
    private RecyclerView mRecyclerView;
    private ExhibitionSubAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exhibition);

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
        toolbarText.setText(getString(R.string.exhibition_info_title));

        // setting Recyclerview
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerItemView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new ExhibitionSubAdapter(ConstantManager.exhibitionInfos, R.layout.submain_exhibition_item, this);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setClickListener(this);     // 아이템 클릭 리스너
    }

    //
    // 아이템 클릭 이벤트
    //
    @Override
    public void onClick(View view, int position) {
        FragmentManager manager = getFragmentManager();
        DialogFragment dialogFragment = ExhibitionInfoDialog.newInstance(position);
        dialogFragment.setCancelable(false);
        dialogFragment.show(manager, "ExhibitionDialog");
    }
}

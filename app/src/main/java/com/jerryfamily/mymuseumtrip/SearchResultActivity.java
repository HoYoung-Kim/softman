package com.jerryfamily.mymuseumtrip;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.endlessrecyclerview.android.EndlessRecyclerOnScrollListener;

import java.util.ArrayList;

public class SearchResultActivity extends MyBaseActivity implements TaskItemCompleted, RowItemClickListener{

    private static final String TAG = SearchResultActivity.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private Toolbar toolbar;
    private ItemAdapter mAdapter;
    private String searchText = null;
    private ArrayList<ItemInfo> itemInfos;
    private int pageNo = 1;
    private APIResultData apiResultData;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

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

        // 파라미터 받기
        Intent intent = getIntent();
        if (intent != null){
            searchText = intent.getStringExtra("searchText");
        }

        // toolbar text 변수
        TextView toolbarText = (TextView) findViewById(R.id.toolbartext);
        toolbarText.setText(searchText);

        // Toast.makeText(getApplicationContext(), "searchText="+ searchText, Toast.LENGTH_SHORT).show();

        // 변수 초기화
        if (itemInfos == null) {
            itemInfos = new ArrayList<>();
        }

        if (apiResultData == null){
            apiResultData = new APIResultData();
        }

        // 부모 컨테이너의 프로그레스바 셋팅
        progressBar = (ProgressBar) findViewById(R.id.item_progress_bar);

        // setting Recyclerview
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerItemView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new ItemAdapter(this.itemInfos, R.layout.activity_item_list_row, this);
        mRecyclerView.setAdapter(mAdapter);
        addDataToList();

        mRecyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                addDataToList();
            }
        });

        mAdapter.setClickListener(this);     // 아이템 클릭 리스너
    }

    //
    // reload dataset to recyclerview...
    //
    private void addDataToList() {
        if (pageNo == 1){
            new AsyncTaskSearchList(this).execute(searchText, Integer.toString(pageNo++));

            progressBar.setVisibility(View.VISIBLE);
        } else {
            if(apiResultData != null &&
                    apiResultData.getPageNo() * apiResultData.getNumOfRows() < apiResultData.getTotalCount()) {
                new AsyncTaskSearchList(this).execute(searchText, Integer.toString(pageNo++));

                progressBar.setVisibility(View.VISIBLE);
            }
        }
    }

    //
    // click event from RecyclerView item
    //
    @Override
    public void onClick(View view, int position) {
        if(itemInfos != null) {
            final ItemInfo itemInfo = itemInfos.get(position);

            Intent intent = new Intent(getApplicationContext(), ItemDetailActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("item_name", itemInfo.getNameKr());
            intent.putExtra("item_id", itemInfo.getId());
            startActivity(intent);
        }
    }

    @Override
    public void onTaskComplete(ArrayList<ItemInfo> itemInfos, APIResultData apiResultData) {

        if (itemInfos != null){
            this.itemInfos.addAll(itemInfos);
        }

        if (apiResultData != null) {
            this.apiResultData.setNumOfRows(apiResultData.getNumOfRows());
            this.apiResultData.setPageNo(apiResultData.getPageNo());
            this.apiResultData.setResultCode(apiResultData.getResultCode());
            this.apiResultData.setResultMsg(apiResultData.getResultMsg());
            this.apiResultData.setTotalCount(apiResultData.getTotalCount());
        }

        mAdapter.notifyDataSetChanged();

        // display result message
        TextView tv_display_summay_info = findViewById(R.id.tv_display_summay_info);
        String result_message = String.format(ConstantManager.ITEM_RESULT_MESSAGE,
                                            Utils.toStringFormat(this.apiResultData.getTotalCount()));

        tv_display_summay_info.setText(result_message);

        // set progressbar GONE
        progressBar.setVisibility(View.GONE);
    }
}

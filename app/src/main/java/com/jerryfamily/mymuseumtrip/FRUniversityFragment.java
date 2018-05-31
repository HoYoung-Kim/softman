package com.jerryfamily.mymuseumtrip;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.endlessrecyclerview.android.EndlessRecyclerOnScrollListener;

import java.util.ArrayList;

/**
 * 학교 fragment.
 * category1_code = PS01005
 */
public class FRUniversityFragment extends Fragment implements RowItemClickListener{

    private static final String TAG = FRUniversityFragment.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private CategoryAdapter mAdapter;
    private static ArrayList<CategoryInfo> categoryInfos;
    private static APIResultData apiResultData;
    private static int pageNo = 1;
    private static View rootView = null;
    private ProgressBar progressBar;

    public FRUniversityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // 부모 컨테이너의 프로그레스바 셋팅
        progressBar = (ProgressBar) getActivity().findViewById(R.id.item_progress_bar);

        if (categoryInfos == null) {
            categoryInfos = new ArrayList<>();
        }

        if (apiResultData == null){
            apiResultData = new APIResultData();
        }

        // 본문 내용 데이타 가져오기...
        rootView = inflater.inflate(R.layout.activity_category_list, container, false);

        //getting the recyclerview from xml
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerCategoryView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAdapter = new CategoryAdapter(this.categoryInfos, R.layout.activity_category_list_row, getActivity());
        mRecyclerView.setAdapter(mAdapter);
        addDataToList(rootView);

        mRecyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                addDataToList(rootView);
            }
        });

        mAdapter.setClickListener(this);     // 아이템 클릭 리스너

        return rootView;
    }

    //
    // return value from AsyncTask(AsyncTaskNational1)....
    //
    public void onTaskComplete(ArrayList<CategoryInfo> categoryInfos, APIResultData apiResultData, View view) {

        if (categoryInfos != null){
            this.categoryInfos.addAll(categoryInfos);
        }

        if (apiResultData != null) {
            this.apiResultData.setNumOfRows(apiResultData.getNumOfRows());
            this.apiResultData.setPageNo(apiResultData.getPageNo());
            this.apiResultData.setResultCode(apiResultData.getResultCode());
            this.apiResultData.setResultMsg(apiResultData.getResultMsg());
            this.apiResultData.setTotalCount(apiResultData.getTotalCount());
        }

        mAdapter.notifyDataSetChanged();

        progressBar.setVisibility(View.GONE);
    }

    //
    // reload dataset to recyclerview...
    //
    private void addDataToList(View rootView) {

        if (pageNo == 1){
            new AsyncTaskUniversity(this, rootView)
                    .execute(getActivity().getString(R.string.category5_code), Integer.toString(pageNo++));

            progressBar.setVisibility(View.VISIBLE);
        } else {
            if(apiResultData != null &&
                    apiResultData.getPageNo() * apiResultData.getNumOfRows() < apiResultData.getTotalCount()) {
                new AsyncTaskUniversity(this, rootView)
                        .execute(getActivity().getString(R.string.category5_code), Integer.toString(pageNo++));

                progressBar.setVisibility(View.VISIBLE);
            }
        }
    }

    //
    // click event from RecyclerView
    //
    @Override
    public void onClick(View view, int position) {

        final CategoryInfo categoryInfo = categoryInfos.get(position);

        Intent intent = new Intent(getActivity(), ItemActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("category_name", categoryInfo.getNameKr());
        intent.putExtra("category_code", categoryInfo.getCode());
        startActivity(intent);
    }
}

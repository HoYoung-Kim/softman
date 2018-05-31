package com.jerryfamily.mymuseumtrip;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import com.endlessrecyclerview.android.EndlessRecyclerOnScrollListener;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.xml.sax.InputSource;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 국립1 fragment.
 * category1_code = PS01001
 */
public class FRNational1Fragment extends Fragment implements RowItemClickListener {

    private static final String TAG = FRNational1Fragment.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private CategoryAdapter mAdapter;
    private static ArrayList<CategoryInfo> categoryInfos;
    private static APIResultData apiResultData;
    private static int pageNo = 1;
    private static View rootView = null;
    private ProgressBar progressBar;

    public FRNational1Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        addDataToList();

        mRecyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                addDataToList();
            }
        });

        mAdapter.setClickListener(this);     // 아이템 클릭 리스너

        return rootView;
    }

    //
    // return value from AsyncTask(AsyncTaskNational1)....
    //
    public void onTaskComplete(ArrayList<CategoryInfo> categoryInfos, APIResultData apiResultData, String status) {

        if(status.equals("OK")) {
            if (categoryInfos != null) {
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
        }
        else{
            Toast.makeText(getActivity(), getString(R.string.fail_text), Toast.LENGTH_SHORT).show();
        }

        progressBar.setVisibility(View.GONE);
    }

    //
    // reload dataset to recyclerview...
    //
    private void addDataToList() {

        if (pageNo == 1){
            new requestURLAsyncTask()
                    .execute(getActivity().getString(R.string.category1_code), Integer.toString(pageNo++));

            progressBar.setVisibility(View.VISIBLE);
        } else {
            if(apiResultData != null &&
                    apiResultData.getPageNo() * apiResultData.getNumOfRows() < apiResultData.getTotalCount()) {
                new requestURLAsyncTask()
                        .execute(getActivity().getString(R.string.category1_code), Integer.toString(pageNo++));

                progressBar.setVisibility(View.VISIBLE);
            }
        }
    }

    //
    // click event from RecyclerView item
    //
    @Override
    public void onClick(View view, int position) {

        if(categoryInfos != null) {
            final CategoryInfo categoryInfo = categoryInfos.get(position);

            Intent intent = new Intent(getActivity(), ItemActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("category_name", categoryInfo.getNameKr());
            intent.putExtra("category_code", categoryInfo.getCode());
            startActivity(intent);
        }
    }

    //
    // AsyncTask call
    //
    private class requestURLAsyncTask extends AsyncTask<String, Void, String>{

        private APIResultData apiResultData;
        private ArrayList<CategoryInfo> categoryInfos = null;
        private OkHttpClient client;
        private String status = "";

        public requestURLAsyncTask() {
        }

        @Override
        protected String doInBackground(String... strings) {

            String codedata_url = String.format(ConstantManager.CODE_LIST_URL,
                    ConstantManager.serviceKey,
                    strings[1],
                    ConstantManager.numOfRows,
                    strings[0]);

            Request request = new Request.Builder()
                    .url(codedata_url)
                    .build();

            Response response = null;
            try {
                client = new OkHttpClient();
                response = client.newCall(request).execute();
                if (!response.isSuccessful()) {
                    return null;
                }

                //
                // 데이타 파싱
                //
                if(response != null) {
                    if (response.code() == 200 && response.message().equals("OK")) {

                        XmlPullParserFactory pullParserFactory;
                        try {
                            pullParserFactory = XmlPullParserFactory.newInstance();
                            XmlPullParser parser = pullParserFactory.newPullParser();

                            String xmlstring = response.body().string();
                            InputStream inputStream = new ByteArrayInputStream(xmlstring.getBytes("UTF-8"));

                            parser.setFeature(Xml.FEATURE_RELAXED, true);   // html tag 허용...
                            parser.setInput(inputStream, "UTF-8");

                            apiResultData = new APIResultData();
                            categoryInfos = MuseumXMLParser.CategoryParseXML(parser, apiResultData);

                            status = "OK";
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (XmlPullParserException e) {
                            e.printStackTrace();
                        }

                    } else {
                        status = "FAIL";
                    }
                } else {
                    status = "FAIL";
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return status;
        }

        @Override
        protected void onPostExecute(String status) {

            //This is where you return data back to caller
            if(categoryInfos != null && status != null){
                onTaskComplete(categoryInfos, apiResultData, status);
            }

        }
    }
}

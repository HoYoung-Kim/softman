package com.jerryfamily.mymuseumtrip;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private static final String TAG = SearchActivity.class.getSimpleName();

    private Toolbar toolbar;
    private EditText searchText;
    private ListView latestListView;
    private ListView recommendListView;
    private LinearLayout latestLayout;
    private LinearLayout horizonLayout;
    private List<String> latest_data = null;
    private List<String> recommend_data = null;

    private SearchLatestAdapter searchLatestAdapter;
    private SearchRecommendAdapter searchRecommendAdapter;

    private AlertDialog alertDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 화면 세로 고정
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_search);

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
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_back_black);
        }


        recommendSearchTextGet();

        // 검색어 위젯 변수 할당
        searchText = findViewById(R.id.searchText);
        setOnKeyListener();
    }

    private void setOnKeyListener() {
        searchText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //Enter key Action
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {

                    if (searchText.getText().toString().length() != 0) {

                        String text = searchText.getText().toString().trim();

                        if(text.length() < 2){
                            alertDialog = AlertDialogUtil.singleSearchErrorButton(SearchActivity.this);
                            alertDialog.show();

                            searchText.requestFocus();

                            return true;
                        }

                        SharedPrefer sf = new SharedPrefer(getApplicationContext());
                        sf.sharedPreference_write(ConstantManager.SF_SEARCH_TAG, text, latest_data);

                        searchResultGo(text);
                    }

                    return true;
                }
                return false;
            }
        });
    }

    private void recommendSearchTextGet() {
        new AsyncTaskRecommend(this).execute();

        recommendListView = findViewById(R.id.recommendListview);
        recommend_data = new ArrayList<>();
    }

    public void onTaskComplete(List<String> recommend_text, String status){

        if(status.equals("OK")) {
            this.recommend_data = recommend_text;

            searchRecommendAdapter = new SearchRecommendAdapter(this, recommend_text);
            recommendListView.setAdapter(searchRecommendAdapter);
        }
        else{
            Toast.makeText(getApplicationContext(), getString(R.string.fail_text), Toast.LENGTH_SHORT).show();
        }
    }

    private void latestSearchTextGet() {
        // 최신 검색어 변수에 할당
        horizonLayout = findViewById(R.id.horizonLayout);
        latestLayout = findViewById(R.id.latestLayout);
        latestListView = findViewById(R.id.latestListview);
        latest_data = new ArrayList<>();

        readSharedPreference();

        if(latest_data.size() == 0){
            latestLayout.setVisibility(View.GONE);
            horizonLayout.setVisibility(View.GONE);
        }

        searchLatestAdapter = new SearchLatestAdapter(this, latest_data);
        latestListView.setAdapter(searchLatestAdapter);
    }

    //
    // 검색어를 검색 결과 페이지로 보내는 method
    //
    private void searchResultGo(String text) {
        Intent intent = new Intent(SearchActivity.this, SearchResultActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        intent.putExtra("searchText", text);

        startActivity(intent);
    }

    //
    // listview 에서 검색
    //
    public void searchItemCallback(String section, int position){
        String text = null;
        if (section.equalsIgnoreCase("latest")) {
            text = latest_data.get(position);
        }
        else if (section.equalsIgnoreCase("recommend")) {
            text = recommend_data.get(position);
        }

        if(!text.equals("")) {
            searchResultGo(text);
        }
    }

    //
    // listview 에서 삭제
    //
    public void removeItemCallback(int position){

        latest_data.remove(position);
        searchLatestAdapter.notifyDataSetChanged();
    }

    //
    // 실행시에 기존 데아타 가져오기기
    //
    private void readSharedPreference() {
        SharedPrefer sf = new SharedPrefer(this);

        String data = sf.sharedPreference_read(ConstantManager.SF_SEARCH_TAG);


        if(data.indexOf("|") != -1){
            String[] texts = data.split("\\|");

            for(String word : texts){
                latest_data.add(word);
            }
        }
    }

    //
    // 상단 메뉴 설정
    //
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu_search, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /*
    *  상단 툴바에 있는 버튼 클릭시 호출되는 메소드...
    * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int menuId = item.getItemId();

        // 백 버튼 클릭시 현재의 activity 종료...
        if(menuId == android.R.id.home){
            finish();
        } else if (menuId == R.id.action_remove){
            searchText.setText("");
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        latestSearchTextGet();
    }
}

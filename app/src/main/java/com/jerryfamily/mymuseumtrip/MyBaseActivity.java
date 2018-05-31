package com.jerryfamily.mymuseumtrip;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * 모든 activity 의 부모 클래스
 */

public class MyBaseActivity extends AppCompatActivity {

    protected final String TAG = getClass().getSimpleName();
    protected ConnectionDetector connectionDetector;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // 1. 네트워크 체크하기...
        connectionDetector = new ConnectionDetector(this);
        if(!connectionDetector.isConnected()){
            Intent intent = new Intent(getApplicationContext(), NetWorkErrorActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }

    //
    // ToolBar에 menu.xml을 인플레이트함
    //
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //return super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        return true;
    }

    //
    // ToolBar에 추가된 항목의 select 이벤트를 처리하는 함수
    //
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuId = item.getItemId();

        if(menuId == android.R.id.home){
            finish();
        }
        else if (menuId == R.id.action_search){
            Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);

            startActivity(intent);

            // 화면 전환 효과
            overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);

        } else if (menuId == R.id.action_setting){
            Intent intent = new Intent(getApplicationContext(), MoreActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);

            startActivity(intent);

            // 화면 전환 효과
            overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
        }

        return super.onOptionsItemSelected(item);
    }
}

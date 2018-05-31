package com.jerryfamily.mymuseumtrip;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * SharedPreference 값 읽기 / 쓰기
 * key 값 : 검색 - searchTextList
 *          버전 - version
 */

public class SharedPrefer {

    private Context mContext;

    public SharedPrefer(Context mContext) {
        this.mContext = mContext;
    }

    //
    // sharedPreference 에서 값 읽기
    //
    public String sharedPreference_read(String key){
        SharedPreferences sf = mContext.getSharedPreferences(ConstantManager.SF_TAG,  MODE_PRIVATE);
        String stored_str = sf.getString(key, ""); // 키값으로 꺼냄

        return stored_str;
    }

    //
    // sharedPreference 에 값 쓰기 - 최신 검색어
    //
    public void sharedPreference_write(String key, String value, List<String> list_data){
        SharedPreferences sf = mContext.getSharedPreferences(ConstantManager.SF_TAG, MODE_PRIVATE);

        String data_str = "";
        if(list_data.size() > 0) {
            data_str = TextUtils.join("|", list_data);
        }

        data_str = value + "|" + data_str;

        SharedPreferences.Editor editor = sf.edit();            //저장하려면 editor가 필요
        editor.putString(key, data_str);     // 입력
        editor.commit();                                        // 파일에 최종 반영함
    }

    //
    // sharedPreference 에 값 쓰기 - 버전 정보
    //
    public void sharedPreference_write(String key, String value){
        SharedPreferences sf = mContext.getSharedPreferences(ConstantManager.SF_TAG, MODE_PRIVATE);
        SharedPreferences.Editor editor = sf.edit();            //저장하려면 editor가 필요
        editor.putString(key, value);     // 입력
        editor.commit();                                        // 파일에 최종 반영함
    }
}

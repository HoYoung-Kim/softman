package com.jerryfamily.mymuseumtrip;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * 초기 데이타 받는 비동기 클래스
 */

public class AsyncTaskInit extends AsyncTask<Void, Void, Void> {

    private static final String TAG = AsyncTaskInit.class.getSimpleName();

    private Context mContext;

    public AsyncTaskInit(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        URL url;
        HttpURLConnection urlConnection = null;
        String initdata_url = ConstantManager.INIT_URL;

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("param", "mymuseumtrip");

            url = new URL(initdata_url);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setConnectTimeout(5000);
            urlConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setRequestProperty("Cache-Control", "no-cache");
            urlConnection.setUseCaches(false);
            urlConnection.setDefaultUseCaches(false);

            // write the parameter
            OutputStream os = urlConnection.getOutputStream();
            os.write(jsonObject.toString().getBytes("UTF-8"));
            os.close();

            if(urlConnection.getResponseCode() >= 200 && urlConnection.getResponseCode() <= 300) {
                // read the response
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                String result = Utils.inputStreamToString(in);

                JSONObject jsonObject_result = new JSONObject(result);
                in.close();


                /// 이색 박물관 / 전시회 정보 데이타 구조체 초기화
                if(ConstantManager.strangeMuseums == null) { ConstantManager.strangeMuseums = new ArrayList<>(); }
                if(ConstantManager.exhibitionInfos == null) { ConstantManager.exhibitionInfos = new ArrayList<>(); }

                if (ConstantManager.strangeMuseums.size() > 0){ ConstantManager.strangeMuseums.clear(); }
                if (ConstantManager.exhibitionInfos.size() > 0){ ConstantManager.exhibitionInfos.clear(); }

                ///
                if (jsonObject_result.getString("status").toString().equals("OK")) {

                    //
                    // 1. 이색 박물관 리스트
                    //
                    JSONArray array = jsonObject_result.getJSONArray("strange_museum");
                    for (int index = 0; index < array.length(); index++){

                        JSONArray jsonObj = (JSONArray)array.get(index);

                        StrangeMuseum strangeMuseum = new StrangeMuseum();
                        for (int index2 = 0; index2 < jsonObj.length(); index2++) {

                            if (index2 == 0) { strangeMuseum.setSeq(jsonObj.getString(index2)); }
                            else if (index2 == 1) { strangeMuseum.setTitle(jsonObj.getString(index2)); }
                            else if (index2 == 2) { strangeMuseum.setTitle_display(jsonObj.getString(index2)); }
                            else if (index2 == 3) { strangeMuseum.setDescription(jsonObj.getString(index2)); }
                            else if (index2 == 4) { strangeMuseum.setAddress(jsonObj.getString(index2)); }
                            else if (index2 == 5) { strangeMuseum.setHomepage_url(jsonObj.getString(index2)); }
                            else if (index2 == 6) { strangeMuseum.setTelephone(jsonObj.getString(index2)); }
                            else if (index2 == 7) { strangeMuseum.setImageM_url(jsonObj.getString(index2)); }
                            else if (index2 == 8) { strangeMuseum.setImageL_url(jsonObj.getString(index2)); }

                        }
                        ConstantManager.strangeMuseums.add(strangeMuseum);
                    }

                    //
                    // 전시회 정보 리스트
                    //
                    JSONArray exhibition_array = jsonObject_result.getJSONArray("exhibition_list");
                    for (int index = 0; index < exhibition_array.length(); index++){

                        JSONArray jsonObj = (JSONArray)exhibition_array.get(index);

                        ExhibitionInfo exhibitionInfo = new ExhibitionInfo();
                        for (int index2 = 0; index2 < jsonObj.length(); index2++) {

                            if (index2 == 0) { exhibitionInfo.setTitle(jsonObj.getString(index2)); }
                            else if (index2 == 1) { exhibitionInfo.setDescription(jsonObj.getString(index2)); }
                            else if (index2 == 2) { exhibitionInfo.setSupervision(jsonObj.getString(index2)); }
                            else if (index2 == 3) { exhibitionInfo.setPlace(jsonObj.getString(index2)); }
                            else if (index2 == 4) { exhibitionInfo.setUrl(jsonObj.getString(index2)); }
                            else if (index2 == 5) { exhibitionInfo.setTelephone(jsonObj.getString(index2)); }
                            else if (index2 == 6) { exhibitionInfo.setDate(jsonObj.getString(index2)); }

                        }
                        ConstantManager.exhibitionInfos.add(exhibitionInfo);
                    }
                }
            }

            urlConnection.disconnect();

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}

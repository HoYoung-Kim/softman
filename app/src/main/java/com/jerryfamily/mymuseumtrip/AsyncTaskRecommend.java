package com.jerryfamily.mymuseumtrip;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 추천 검색어 가져오는 비동기 클래스
 */

public class AsyncTaskRecommend extends AsyncTask<Void, Void, Response> {

    private static final String TAG = AsyncTaskRecommend.class.getSimpleName();

    private View view;
    private JSONArray array = new JSONArray();
    private List<String> recommend_text = new ArrayList<>();

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private OkHttpClient client;

    private Context mContext;

    public AsyncTaskRecommend(Context mContext) {
        this.mContext = mContext;

        client = new OkHttpClient();
    }

    @Override
    protected Response doInBackground(Void... voids) {
        try {
            Gson gson = new Gson();
            JsonObject object = new JsonObject();
            object.addProperty("param", "mymuseumtrip");
            String json = gson.toJson(object);

            RequestBody body = RequestBody.create(JSON, json);

            Request request = new Request.Builder()
                    .url(ConstantManager.RECOMMEND_URL)
                    .post(body)
                    .build();

            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                return null;
            }
            return response;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Response response) {
        String status = "";

        //
        // 데이타 파싱
        //
        if(response != null) {
            if (response.code() == 200 && response.message().equals("OK")) {
                status = "OK";

                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response.body().string());
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    if (jsonObject != null) {
                        if (jsonObject.getString("status").toString().equals("OK")) {

                            array = jsonObject.getJSONArray("recommend_text");

                            for (int index = 0; index < array.length(); index++) {
                                recommend_text.add(array.getString(index));
                            }
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                status = "FAIL";
            }
        } else {
            status = "FAIL";
        }

        //
        // 메인으로 데이타 전달
        //
        if(mContext instanceof SearchActivity)
            ((SearchActivity) mContext).onTaskComplete(recommend_text, status);
    }
}

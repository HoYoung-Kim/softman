package com.jerryfamily.mymuseumtrip;

import android.app.AlertDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ProtocolException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class InquiryActivity extends MyBaseActivity {

    private static final String TAG = InquiryActivity.class.getSimpleName();

    private Toolbar toolbar;
    private Button button_send;
    private EditText editText_content, editText_contact;
    private AlertDialog alertDialog = null;
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inquiry);

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
        toolbarText.setText(getString(R.string.menu_inquiry_title));

        // 변수 초기화 및 저장
        initAndSaveData();
    }

    private void initAndSaveData() {
        editText_content = findViewById(R.id.editText_content);
        editText_contact = findViewById(R.id.editText_contact);

        button_send = findViewById(R.id.button_send);
        button_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String content = editText_content.getText().toString();
                String contact = editText_contact.getText().toString();

                if (content.equals("")){

                    alertDialog = AlertDialogUtil.singleErrorButton(InquiryActivity.this);
                    alertDialog.show();

                    editText_content.requestFocus();
                }
                else {
                    new requestUrlSaveDataAsynctask().execute(content, contact);
                }
            }
        });
    }

    public void onTaskComplete(String status){

        if(status.equals("OK")) {
            Toast.makeText(getApplicationContext(), getString(R.string.success_text), Toast.LENGTH_SHORT).show();

            finish();
        }
        else{
            Toast.makeText(getApplicationContext(), getString(R.string.fail_text), Toast.LENGTH_SHORT).show();
        }
    }

    private class requestUrlSaveDataAsynctask extends AsyncTask<String, Void, Response>{

        private OkHttpClient client;

        @Override
        protected Response doInBackground(String... strings) {

            try {
                Gson gson = new Gson();
                JsonObject object = new JsonObject();
                object.addProperty("content", strings[0]);
                object.addProperty("contact", strings[1]);
                String json = gson.toJson(object);

                client = new OkHttpClient();
                RequestBody body = RequestBody.create(JSON, json);

                Request request = new Request.Builder()
                        .url(ConstantManager.INQUIRY_URL)
                        .post(body)
                        .build();

                Response response = client.newCall(request).execute();
                if (!response.isSuccessful()) {
                    return null;
                }

                return response;

            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
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

                    try {
                        String json = response.body().string();
                        JsonParser parser = new JsonParser();
                        JsonElement element = parser.parse(json);

                        status = element.getAsJsonObject().get("status").getAsString();
                    } catch (IOException e) {
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
            onTaskComplete(status);
        }
    }
}

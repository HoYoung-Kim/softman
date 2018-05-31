package com.jerryfamily.mymuseumtrip;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Xml;
import android.view.View;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * 카테고리 코드를 가져오는 비동기 클래스 - 법인/사립
 */

public class AsyncTaskCorporation extends AsyncTask<String, Void, String> {

    private static final String TAG = AsyncTaskCorporation.class.getSimpleName();

//    private TaskCategoryCompleted mCallback;
    private APIResultData apiResultData;
    private ArrayList<CategoryInfo> categoryInfos = null;
    private View view;

    private FRCorporationFragment frCorporationFragment;

    private Context mContext;

    public AsyncTaskCorporation(FRCorporationFragment frCorporationFragment, View view) {
        this.frCorporationFragment = frCorporationFragment;
        this.view = view;
    }

    @Override
    protected String doInBackground(String... strings) {
        URL url;
        HttpURLConnection urlConnection = null;
        String codedata_url = String.format(ConstantManager.CODE_LIST_URL,
                ConstantManager.serviceKey,
                strings[1],
                ConstantManager.numOfRows,
                strings[0]);

        try {
            url = new URL(codedata_url);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Content-type", "application/xml");
            urlConnection.setDoInput(true);

            if(urlConnection.getResponseCode() >= 200 && urlConnection.getResponseCode() <= 300){

                XmlPullParserFactory pullParserFactory;
                try {
                    pullParserFactory = XmlPullParserFactory.newInstance();
                    XmlPullParser parser = pullParserFactory.newPullParser();

                    parser.setFeature(Xml.FEATURE_RELAXED, true);   // html tag 허용...
                    parser.setInput(urlConnection.getInputStream(), "UTF-8");

                    apiResultData = new APIResultData();
                    categoryInfos = MuseumXMLParser.CategoryParseXML(parser, apiResultData);
                }
                catch (Exception ex){
                    Log.e(TAG, ex.toString());
                }
            } else {
                categoryInfos = null;
            }

            urlConnection.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    //
    // return xml string
    //
    @Override
    protected void onPostExecute(String s) {

        //This is where you return data back to caller
        if(frCorporationFragment != null && categoryInfos != null){
            frCorporationFragment.onTaskComplete(categoryInfos, apiResultData, view);
        }
    }
}

package com.jerryfamily.mymuseumtrip;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * 박물관 소장 리스트를 가져오는 비동기 클래스
 */

public class AsyncTaskItemList extends AsyncTask<String, Void, String> {

    private static final String TAG = AsyncTaskItemList.class.getSimpleName();

    private TaskItemCompleted mCallback;
    private Context mContext;
    private APIResultData apiResultData;
    private ArrayList<ItemInfo> itemInfos = null;

    public AsyncTaskItemList(Context mContext) {
        this.mContext = mContext;
        this.mCallback = (TaskItemCompleted) mContext;

        itemInfos = new ArrayList<>();
        apiResultData = new APIResultData();
    }

    @Override
    protected String doInBackground(String... strings) {

        URL url;
        HttpURLConnection urlConnection = null;
        String itemdata_url = String.format(ConstantManager.ITEM_LIST_URL,
                ConstantManager.serviceKey,
                strings[1],
                ConstantManager.numOfRows,
                strings[0]);

        try {
            url = new URL(itemdata_url);
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
                    itemInfos = MuseumXMLParser.ItemParseXML(parser, apiResultData);
                }
                catch (Exception ex){
                    Log.e(TAG, ex.toString());
                }
            } else {
                itemInfos = null;
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
        if(mCallback != null && itemInfos != null){
            mCallback.onTaskComplete(itemInfos, apiResultData);
        }
    }
}

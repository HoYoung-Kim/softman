package com.jerryfamily.mymuseumtrip;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * 추천 검색어 adapter
 */

public class SearchRecommendAdapter extends BaseAdapter {

    private static final String TAG = SearchRecommendAdapter.class.getSimpleName();

    private static LayoutInflater inflater = null;
    private List<String> recommend_data;
    private Context context;

    public SearchRecommendAdapter(Context context, List<String> recommend_data) {
        this.context = context;
        this.recommend_data = recommend_data;
    }

    @Override
    public int getCount() {
        return recommend_data.size();
    }

    @Override
    public Object getItem(int position) {
        return getItemId(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if(row == null){

            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            row = inflater.inflate(R.layout.activity_search_recommend_layout, null);

        }

        TextView textView_searchText = (TextView) row.findViewById(R.id.textView_searchText2);
        textView_searchText.setText(recommend_data.get(position));

        // 검색어
        textView_searchText.setTag(position);
        textView_searchText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer index = (Integer) v.getTag();

                if(context instanceof SearchActivity)
                    ((SearchActivity) context).searchItemCallback("recommend", index);
            }
        });

        return row;
    }
}

package com.jerryfamily.mymuseumtrip;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 최신 검색어 adapter
 */

public class SearchLatestAdapter extends BaseAdapter {

    private static final String TAG = SearchLatestAdapter.class.getSimpleName();

    private static LayoutInflater inflater = null;
    private List<String> latest_data;
    private Context context;

    public SearchLatestAdapter(Context context, List<String> latest_data) {
        this.context = context;
        this.latest_data = latest_data;
    }

    @Override
    public int getCount() {
        return latest_data.size();
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

            row = inflater.inflate(R.layout.activity_search_latest_layout, null);

        }

        TextView textView_searchText = (TextView) row.findViewById(R.id.textView_searchText);
        ImageView imageView_delete = (ImageView) row.findViewById(R.id.imageView_delete);

        textView_searchText.setText(latest_data.get(position));

        // 검색어
        textView_searchText.setTag(position);
        textView_searchText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer index = (Integer) v.getTag();

                if(context instanceof SearchActivity)
                    ((SearchActivity) context).searchItemCallback("latest", index);
            }
        });

        // 삭제
        imageView_delete.setTag(position);
        imageView_delete.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer index = (Integer) v.getTag();

                if(context instanceof SearchActivity)
                    ((SearchActivity) context).removeItemCallback(index);
            }
        });

        return row;
    }
}

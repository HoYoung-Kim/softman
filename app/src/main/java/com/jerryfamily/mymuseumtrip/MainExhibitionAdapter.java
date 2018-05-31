package com.jerryfamily.mymuseumtrip;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 호영 on 2018-05-23.
 */

public class MainExhibitionAdapter extends BaseAdapter {

    private Context mContext;
    private List<ExhibitionInfo> exhibitionInfos;

    public MainExhibitionAdapter(Context mContext, List<ExhibitionInfo> exhibitionInfos) {
        this.mContext = mContext;
        this.exhibitionInfos = exhibitionInfos;
    }

    @Override
    public int getCount() {
        return exhibitionInfos.size();
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

        View view = convertView;
        ViewHolder viewHolder;

        if(view == null){
            LayoutInflater inflater =  (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.main_exhibition_item, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.title = view.findViewById(R.id.exhibition_title);
            viewHolder.date = view.findViewById(R.id.exhibition_date);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }

        ExhibitionInfo exhibitionInfo = exhibitionInfos.get(position);

        // 데이타 할당
        viewHolder.title.setText(exhibitionInfo.getTitle());
        viewHolder.date.setText(exhibitionInfo.getDate());

        return view;
    }

    private class ViewHolder{
        TextView title;
        TextView date;
    }
}

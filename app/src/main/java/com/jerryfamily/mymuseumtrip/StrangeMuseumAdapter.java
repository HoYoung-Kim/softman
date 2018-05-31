package com.jerryfamily.mymuseumtrip;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

/**
 * Created by 호영 on 2018-05-18.
 */

public class StrangeMuseumAdapter extends BaseAdapter {

    private static final String TAG = StrangeMuseumAdapter.class.getSimpleName();

    private Context mContext;
    private List<StrangeMuseum> strangeMuseums = new ArrayList<>();

    public StrangeMuseumAdapter(Context mContext, List<StrangeMuseum> strangeMuseums) {
        this.mContext = mContext;
        this.strangeMuseums = strangeMuseums;
    }

    @Override
    public int getCount() {
        return strangeMuseums.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final StrangeMuseum strangeMuseum = strangeMuseums.get(position);

        // view holder pattern
        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.strange_museum_item, null);

            final ImageView imageView_thumbnail = (ImageView)convertView.findViewById(R.id.imageView_thumbnail);
            final TextView textView_strange_title = (TextView)convertView.findViewById(R.id.textView_strange_title);

            final ViewHolder viewHolder = new ViewHolder(imageView_thumbnail, textView_strange_title);
            convertView.setTag(viewHolder);
        }

        final ViewHolder viewHolder = (ViewHolder)convertView.getTag();

        if(!strangeMuseum.getImageM_url().equals("")) {
            Picasso.with(mContext)
                    .load(strangeMuseum.getImageM_url())
                    .transform(new RoundedCornersTransformation(20, 5))
                    .placeholder(R.drawable.no_images_placeholder)
                    .error(R.drawable.no_images_placeholder)
                    .into(viewHolder.imageView_thumbnail);
        }

        viewHolder.textView_strange_title.setText(strangeMuseum.getTitle());

        return convertView;
    }

    // Your "view holder" that holds references to each subview
    private class ViewHolder {

        private final ImageView imageView_thumbnail;
        private final TextView textView_strange_title;

        public ViewHolder(ImageView imageView_thumbnail, TextView textView_strange_title) {
            this.imageView_thumbnail = imageView_thumbnail;
            this.textView_strange_title = textView_strange_title;
        }
    }
}

package com.jerryfamily.mymuseumtrip;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

/**
 * 아이템 코드를 가져오는 adapter
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>{

    private static final String TAG = ItemAdapter.class.getSimpleName();

    private ArrayList<ItemInfo> itemInfos;
    private int rowLayout;
    private Context mContext;
    private RowItemClickListener clickListener;

    public ItemAdapter(ArrayList<ItemInfo> itemInfos, int rowLayout, Context mContext) {
        this.itemInfos = itemInfos;
        this.rowLayout = rowLayout;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ItemInfo itemInfo = itemInfos.get(position);
        holder.nameKr.setText(itemInfo.getNameKr());
        holder.author.setText(itemInfo.getAuthor());
        holder.id.setText(itemInfo.getId());
        if (itemInfo.getImgThumUriM() != null) {
            if (itemInfo.getImgThumUriM().indexOf("http://") > -1) {
                Picasso.with(mContext)
                        .load(itemInfo.getImgThumUriM())
                        .transform(new RoundedCornersTransformation(50, 5))
                        .error(R.drawable.no_images_placeholder)
                        .into(holder.imgThumUriM);
            } else {
                Picasso.with(mContext)
                        .load("http://".concat(itemInfo.getImgThumUriM()))
                        .transform(new RoundedCornersTransformation(50, 5))
                        .error(R.drawable.no_images_placeholder)
                        .into(holder.imgThumUriM);
            }
        }
    }

    @Override
    public int getItemCount() {
        return itemInfos == null ? 0 : itemInfos.size();
    }

    //
    // 아이템 클릭 리스너
    //
    public void setClickListener(RowItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView nameKr, author, id;
        public ImageView imgThumUriM;
        public ViewHolder(View itemView) {
            super(itemView);

            nameKr          = itemView.findViewById(R.id.tv_item_nameKr);
            author          = itemView.findViewById(R.id.tv_item_author);
            imgThumUriM    = itemView.findViewById(R.id.imgThumUriM);
            id              = itemView.findViewById(R.id.tv_item_id);

            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) clickListener.onClick(v, getAdapterPosition());
        }
    }
}

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
import java.util.List;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;


/**
 * 전시회 정보 가져오는 adapter
 */

public class ExhibitionSubAdapter extends RecyclerView.Adapter<ExhibitionSubAdapter.ViewHolder> {

    private Context mContext;
    private List<ExhibitionInfo> exhibitionInfos;
    private RowItemClickListener clickListener;
    private int rowLayout;

    public ExhibitionSubAdapter(List<ExhibitionInfo> exhibitionInfos, int rowLayout, Context mContext) {
        this.exhibitionInfos = exhibitionInfos;
        this.rowLayout = rowLayout;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new ExhibitionSubAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ExhibitionSubAdapter.ViewHolder holder, int position) {
        final ExhibitionInfo exhibitionInfo = exhibitionInfos.get(position);
        holder.ex_title.setText(exhibitionInfo.getTitle());
        holder.ex_supervision.setText(exhibitionInfo.getSupervision());
        holder.ex_date.setText(exhibitionInfo.getDate());
    }

    @Override
    public int getItemCount() {
        return exhibitionInfos == null ? 0 : exhibitionInfos.size();
    }

    //
    // 아이템 클릭 리스너
    //
    public void setClickListener(RowItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView ex_title, ex_supervision, ex_date;

        public ViewHolder(View itemView) {
            super(itemView);

            ex_title            = itemView.findViewById(R.id.tv_ex_title);
            ex_supervision     = itemView.findViewById(R.id.tv_ex_supervision);
            ex_date             = itemView.findViewById(R.id.tv_ex_date);

            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) clickListener.onClick(v, getAdapterPosition());
        }
    }
}

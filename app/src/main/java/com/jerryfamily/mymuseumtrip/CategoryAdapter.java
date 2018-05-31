package com.jerryfamily.mymuseumtrip;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * 카테고리 코드를 가져오는 adapter
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private ArrayList<CategoryInfo> categoryInfos;
    private int rowLayout;
    private Context mContext;
    private RowItemClickListener clickListener;

    public CategoryAdapter(ArrayList<CategoryInfo> categoryInfos, int rowLayout, Context mContext) {
        this.categoryInfos = categoryInfos;
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
        final CategoryInfo categoryInfo = categoryInfos.get(position);
        holder.nameKr.setText(categoryInfo.getNameKr());
        holder.nameEn.setText(categoryInfo.getNameEn());
        holder.code.setText(categoryInfo.getCode());
    }

    @Override
    public int getItemCount() {
        return categoryInfos == null ? 0 : categoryInfos.size();
    }

    //
    // 아이템 클릭 리스너
    //
    public void setClickListener(RowItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView nameKr, nameEn, code;

        public ViewHolder(View itemView) {
            super(itemView);

            nameKr       = itemView.findViewById(R.id.tv_category_nameKr);
            nameEn       = itemView.findViewById(R.id.tv_category_nameEn);
            code         = itemView.findViewById(R.id.tv_category_code);

            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) clickListener.onClick(v, getAdapterPosition());
        }
    }
}


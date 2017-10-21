package com.lxltech.paginationinrecyclerview.recycler.viewholder;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.lxltech.paginationinrecyclerview.R;

/**
 * Created by kpradeepkumarreddy on 20/10/17.
 */

public class PageViewHolder extends RecyclerView.ViewHolder {
    private TextView tvPageNumber;

    public PageViewHolder(View itemView) {
        super(itemView);

        tvPageNumber = itemView.findViewById(R.id.tvPageNumber);
        setTvPageNumber(tvPageNumber);
    }

    public TextView getTvPageNumber() {
        return tvPageNumber;
    }

    public void setTvPageNumber(TextView tvPageNumber) {
        this.tvPageNumber = tvPageNumber;
    }
}

package com.lxltech.paginationinrecyclerview.recycler.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lxltech.paginationinrecyclerview.R;
import com.lxltech.paginationinrecyclerview.pojo.Page;
import com.lxltech.paginationinrecyclerview.recycler.viewholder.PageViewHolder;

import java.util.List;

/**
 * Created by kpradeepkumarreddy on 20/10/17.
 */

public class PaginationRecyclerViewAdapter extends RecyclerView.Adapter {
    private List<Page> pageList = null;

    public PaginationRecyclerViewAdapter(List<Page> pageList) {
        this.pageList = pageList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        PageViewHolder pageViewHolder = null;
        try {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.page_row, parent, false);

            pageViewHolder = new PageViewHolder(itemView);
        } catch (Exception ex) {
            Log.d("log", "Exception in onCreateViewHolder() = ", ex);
        }
        return pageViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        try {
            PageViewHolder pageViewHolder = (PageViewHolder) holder;
            pageViewHolder.getTvPageNumber()
                    .setText(String.valueOf(pageList.get(position).getPageNumber()));
        } catch (Exception ex) {
            Log.d("log", "Exception in onBindViewHolder() = ", ex);
        }
    }

    @Override
    public int getItemCount() {
        return pageList.size();
    }

    public List<Page> getPageList() {
        return pageList;
    }

    public void setPageList(List<Page> pageList) {
        this.pageList = pageList;
        this.notifyDataSetChanged();
    }
}

package com.lxltech.paginationinrecyclerview;

import android.app.ProgressDialog;
import android.content.CursorLoader;
import android.database.Cursor;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.lxltech.paginationinrecyclerview.daos.PageDao;
import com.lxltech.paginationinrecyclerview.pojo.Page;
import com.lxltech.paginationinrecyclerview.recycler.adapter.PaginationRecyclerViewAdapter;
import com.lxltech.paginationinrecyclerview.utils.AppUtils;
import com.lxltech.paginationinrecyclerview.utils.QueryConstants;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvPagination;
    private PaginationRecyclerViewAdapter paginationRecyclerViewAdapter;
    private LinearLayoutManager linearLayoutManager;
    private List<Page> pageList = new ArrayList<>();
    private final int PAGE_SIZE = 25;
    private int pageNumber = 1, recordsInDB;
    private boolean isLoading = false, isLastPage = false;
    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            rvPagination = (RecyclerView) findViewById(R.id.rvPagination);
            Log.d("log", "onCreate() of MainActivity got called");
            linearLayoutManager = new LinearLayoutManager(this);
            rvPagination.setLayoutManager(linearLayoutManager);
            rvPagination.addOnScrollListener(new PageScrollListener());

            PageDao pageDao = AppUtils.getInstance().getDaoSession().getPageDao();
            Cursor cursor = pageDao.getDatabase().rawQuery(QueryConstants.QRY_PAGE_TABLE, null);
            recordsInDB = cursor.getCount();
            Log.d("log", "recordsInDB = " + recordsInDB);
            String firstPage = QueryConstants.QRY_PAGE_TABLE + " LIMIT " + PAGE_SIZE;
            cursor = pageDao.getDatabase().rawQuery(firstPage, null);

            getPageList(cursor);
            Log.d("log", "pageList.size() = " + pageList.size());
            paginationRecyclerViewAdapter = new PaginationRecyclerViewAdapter(pageList);

            rvPagination.setAdapter(paginationRecyclerViewAdapter);
        } catch (Exception ex) {
            Log.d("log", "Exception in onCreate() of MainActivity = ", ex);
        }
    }

    private void getPageList(Cursor cursor) {

        if (!cursor.moveToFirst()) {
            Log.d("log", " cursor is empty");
            return;
        }
        // iterate cursor
        Log.d("log", "size of cursor : " + cursor.getCount());

        do {
            Page page = new Page();
            int pageNumber = cursor.getInt(cursor.getColumnIndex(PageDao.Properties.PageNumber.columnName));
            page.setPageNumber(pageNumber);

            pageList.add(page);
        } while (cursor.moveToNext());
    }

    private class PageScrollListener extends RecyclerView.OnScrollListener {
        public PageScrollListener() {
            super();
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            int visibleItemCount = linearLayoutManager.getChildCount();
            int totalItemCount = linearLayoutManager.getItemCount();
            int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
            Log.d("log", "onScrolled() got called, visibleItemCount = " + visibleItemCount
                    + " totalItemCount = " + totalItemCount +
                    " firstVisibleItemPosition = " + firstVisibleItemPosition);
            if (!isLoading && !isLastPage) {
                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0
                        && totalItemCount >= PAGE_SIZE) {

                    loadMoreItems();
                }
            }
        }
    }

    private void loadMoreItems() {
        try {
            isLoading = true;

            final Toast toast = Toast.makeText(this, "Please Wait, Loading . . .", Toast.LENGTH_SHORT);
            toast.show();

            Log.d("log", "loadMoreItems() got called, pageNumber = " + pageNumber);
            int offsetLimit = pageNumber * PAGE_SIZE;

            PageDao pageDao = AppUtils.getInstance().getDaoSession().getPageDao();
            String nextPage = QueryConstants.QRY_PAGE_TABLE + " LIMIT " + offsetLimit + "," + PAGE_SIZE;
            Log.d("log", "nextPage = " + nextPage);
            Cursor cursor = pageDao.getDatabase().rawQuery(nextPage, null);

            getPageList(cursor);
            Log.d("log", "pageList.size() = " + pageList.size());
            paginationRecyclerViewAdapter.setPageList(pageList);

            // check for last page
            if ((offsetLimit + PAGE_SIZE) >= recordsInDB) {
                Log.d("log", "setting last page = true");
                isLastPage = true;
            }

            // cancel the toast after one second
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (toast != null) {
                        toast.cancel();
                    }
                }
            }, 1000);

            pageNumber++;
            isLoading = false;

        } catch (Exception ex) {
            Log.d("log", "Exception in loadMoreItems() = ", ex);
        }
    }
}

package com.lxltech.paginationinrecyclerview;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.lxltech.paginationinrecyclerview.daos.DaoMaster;
import com.lxltech.paginationinrecyclerview.daos.DaoSession;
import com.lxltech.paginationinrecyclerview.daos.PageDao;
import com.lxltech.paginationinrecyclerview.pojo.Page;
import com.lxltech.paginationinrecyclerview.utils.AppUtils;

import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseOpenHelper;

/**
 * Created by kpradeepkumarreddy on 20/10/17.
 */

public class PaginationApplication extends Application {

    private final String IS_RECORDS_INSERTED = "IS_RECORDS_INSERTED";
    SharedPreferences sharedPref;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("log", "onCreate() of PaginationApplication got called");

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this,
                "com.lxltech.paginationinrecyclerview");
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();
        AppUtils.getInstance().setDaoSession(daoSession);

        sharedPref = getSharedPreferences("com.lxltech.paginationinrecyclerview",
                Context.MODE_PRIVATE);
        boolean isRecordsInserted = sharedPref.getBoolean(IS_RECORDS_INSERTED, false);

        if (!isRecordsInserted) {
            insertRecords(daoSession);
        }
    }

    private void insertRecords(DaoSession daoSession) {
        Log.d("log", "inserting records : \n");
        PageDao pageDao = daoSession.getPageDao();
        Page page = new Page();
        for (int i = 1; i < 1000; i++) {
            Log.d("log", "inserting record : " + i);
            page.setPageNumber(i);
            pageDao.insert(page);
        }

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(IS_RECORDS_INSERTED, true);
        editor.apply();
    }
}

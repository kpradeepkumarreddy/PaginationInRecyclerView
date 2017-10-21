package com.lxltech.paginationinrecyclerview.utils;

import com.lxltech.paginationinrecyclerview.daos.DaoSession;

/**
 * Created by kpradeepkumarreddy on 20/10/17.
 */

public class AppUtils {
    private DaoSession daoSession = null;

    private AppUtils() {
    }

    private static class LazyFactoryHolder {
        private static final AppUtils appUtils = new AppUtils();
    }

    public static AppUtils getInstance() {
        return LazyFactoryHolder.appUtils;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public void setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
    }
}

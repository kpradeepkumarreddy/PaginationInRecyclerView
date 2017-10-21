package com.lxltech.paginationinrecyclerview.pojo;

import android.support.annotation.IdRes;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by kpradeepkumarreddy on 20/10/17.
 */

@Entity
public class Page {
    private int pageNumber;

    @Generated(hash = 1943269558)
    public Page(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    @Generated(hash = 1618436667)
    public Page() {
    }

    public int getPageNumber() {
        return this.pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
}

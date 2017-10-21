package com.lxltech.paginationinrecyclerview.daos;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.lxltech.paginationinrecyclerview.pojo.Page;

import com.lxltech.paginationinrecyclerview.daos.PageDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig pageDaoConfig;

    private final PageDao pageDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        pageDaoConfig = daoConfigMap.get(PageDao.class).clone();
        pageDaoConfig.initIdentityScope(type);

        pageDao = new PageDao(pageDaoConfig, this);

        registerDao(Page.class, pageDao);
    }
    
    public void clear() {
        pageDaoConfig.clearIdentityScope();
    }

    public PageDao getPageDao() {
        return pageDao;
    }

}

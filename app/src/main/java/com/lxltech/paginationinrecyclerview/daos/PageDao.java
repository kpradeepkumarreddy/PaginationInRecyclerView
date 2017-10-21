package com.lxltech.paginationinrecyclerview.daos;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.lxltech.paginationinrecyclerview.pojo.Page;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "PAGE".
*/
public class PageDao extends AbstractDao<Page, Void> {

    public static final String TABLENAME = "PAGE";

    /**
     * Properties of entity Page.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property PageNumber = new Property(0, int.class, "pageNumber", false, "PAGE_NUMBER");
    }


    public PageDao(DaoConfig config) {
        super(config);
    }
    
    public PageDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"PAGE\" (" + //
                "\"PAGE_NUMBER\" INTEGER NOT NULL );"); // 0: pageNumber
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PAGE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Page entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getPageNumber());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Page entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getPageNumber());
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public Page readEntity(Cursor cursor, int offset) {
        Page entity = new Page( //
            cursor.getInt(offset + 0) // pageNumber
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Page entity, int offset) {
        entity.setPageNumber(cursor.getInt(offset + 0));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(Page entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(Page entity) {
        return null;
    }

    @Override
    public boolean hasKey(Page entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}

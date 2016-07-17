package com.yan.phoneintercept.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/5/11.
 */
public class SqlHelper extends SQLiteOpenHelper {
    public SqlHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     * 建表 phonenumber是存放黑名单，phoneIS存放拦截记录
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table phonenumber( id integer primary key autoincrement , number varchar(20), name varchar(20) )");
        db.execSQL("create table phoneIS( id integer primary key autoincrement , number varchar(20), name varchar(20) )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

package com.yan.phoneintercept.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.yan.phoneintercept.bean.RecoredListItemData;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据库操作类
 */
public class PhoneNumberDao {
    private SqlHelper sqlHelper;
    private static PhoneNumberDao phoneNumberDao;

    private PhoneNumberDao(Context context) {
        this.sqlHelper = new SqlHelper(context, "phonedb", null, 1);
    }

    public static PhoneNumberDao getPhoneNumberDao(Context context) {
        if (phoneNumberDao == null) {
            synchronized (PhoneNumberDao.class) {
                if (phoneNumberDao == null)
                    phoneNumberDao = new PhoneNumberDao(context);
            }
        }
        return phoneNumberDao;
    }

    public void insertNumberMessage(String number, String name) {
        SQLiteDatabase db = sqlHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("number", number);
        values.put("name", name);
        db.insert("phonenumber", null, values);
        db.close();
    }

    public void insertNumberIS(String number, String name) {
        SQLiteDatabase db = sqlHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("number", number);
        values.put("name", name);
        db.insert("phoneIS", null, values);
        db.close();
    }

    public List<RecoredListItemData> getNumberMessages() {
        SQLiteDatabase db = sqlHelper.getReadableDatabase();
        List<RecoredListItemData> listItemDatas = new ArrayList<RecoredListItemData>();
        //Cursor cursor = db.query("phonenumber", new String[]{"number", "name"}, null, null, null, null, null);
        Cursor cursor = db.rawQuery("select number ,name from phonenumber", new String[]{});

        while (cursor.moveToNext()) {
            listItemDatas.add(new RecoredListItemData(cursor.getString(0), cursor.getString(1), false));
        }
        cursor.close();
        db.close();
        return listItemDatas;
    }

    public List<RecoredListItemData> getNumberIS() {
        SQLiteDatabase db = sqlHelper.getReadableDatabase();
        List<RecoredListItemData> listItemDatas = new ArrayList<RecoredListItemData>();
        //Cursor cursor = db.query("phonenumber", new String[]{"number", "name"}, null, null, null, null, null);
        Cursor cursor = db.rawQuery("select number ,name from phoneIS", new String[]{});

        while (cursor.moveToNext()) {
            listItemDatas.add(new RecoredListItemData(cursor.getString(0), cursor.getString(1), false));
        }
        cursor.close();
        db.close();
        return listItemDatas;
    }

    public boolean deletNumber(String number) {
        SQLiteDatabase db = sqlHelper.getWritableDatabase();
        db.delete("phonenumber", "number=?", new String[]{"" + number});
        // db.execSQL("delete from phonenumber where number = " + number);
        db.close();
        return true;
    }

    public boolean deletNumerIs() {
        SQLiteDatabase db = sqlHelper.getWritableDatabase();
        db.delete("phoneIS", null, new String[]{});
        // db.execSQL("delete from phonenumber where number = " + number);
        db.close();
        return true;
    }
}

package com.yan.phoneintercept.dao;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;

import android.provider.ContactsContract.CommonDataKinds.Phone;

import com.yan.phoneintercept.bean.ListItemFromRecored;
import com.yan.phoneintercept.bean.RecoredListItemData;
import com.yan.phoneintercept.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2016/5/12.
 */
public class PhoneNumberFromSystem {
    private Context context;

    public static PhoneNumberFromSystem Instance(Context context) {
        if (phoneNumberFromSystem == null) {
            synchronized (PhoneNumberFromSystem.class) {
                if (phoneNumberFromSystem == null) {
                    phoneNumberFromSystem = new PhoneNumberFromSystem(context);
                }
            }
        }
        return phoneNumberFromSystem;
    }

    private static PhoneNumberFromSystem phoneNumberFromSystem;


    private PhoneNumberFromSystem(Context context) {
        this.context = context;
    }

    /**
     * 得到通话记录里的数据
     *
     * @return
     */
    public List<ListItemFromRecored> getPhonenumberFormSystem() {

        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = null;
        List<ListItemFromRecored> mRecordList = new ArrayList<ListItemFromRecored>();
        try {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.

                return null;
            }

            cursor = contentResolver.query(
                    CallLog.Calls.CONTENT_URI, null, null, null, null);
            if (cursor == null)
                return null;

            while (cursor.moveToNext()) {
                ListItemFromRecored record = new ListItemFromRecored(
                        StringUtil.filter(cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER)))
                        , cursor.getString(cursor.getColumnIndex(CallLog.Calls.CACHED_NAME))
                        , cursor.getLong(cursor.getColumnIndex(CallLog.Calls.DATE))
                        , false);
                Log.e("record", cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER)));
                Log.e("recordd", cursor.getString(cursor.getColumnIndex(CallLog.Calls.CACHED_NAME)) + "--");

//                record.setName(cursor.getString(cursor.getColumnIndex(CallLog.Calls.CACHED_NAME)));
//                record.number = cursor.getString(cursor
//                        .getColumnIndex(CallLog.Calls.NUMBER));
//                record.type = cursor.getInt(cursor
//                        .getColumnIndex(CallLog.Calls.TYPE));
//                record.lDate = cursor.getLong(cursor
//                        .getColumnIndex(CallLog.Calls.DATE));
//                record.duration = cursor.getLong(cursor
//                        .getColumnIndex(CallLog.Calls.DURATION));
//                record._new = cursor.getInt(cursor
//                        .getColumnIndex(CallLog.Calls.NEW));
//                Log.e(TAG, record.toString());
//                      int photoIdIndex = cursor.getColumnIndex(CACHED_PHOTO_ID);
//                      if (photoIdIndex >= 0) {
//                          record.cachePhotoId = cursor.getLong(photoIdIndex);
//                      }

                mRecordList.add(record);

            }
            Collections.reverse(mRecordList);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return mRecordList;
    }

    /**
     * 得到手机通讯录联系人信息
     **/

    public List<ListItemFromRecored> getPhoneNumberFromMobile() {
        Cursor cursor = context.getContentResolver().query(Phone.CONTENT_URI, null, null, null, null);
        List<ListItemFromRecored> fromRecoreds = new ArrayList<ListItemFromRecored>();

        //moveToNext方法返回的是一个boolean类型的数据
        while (cursor.moveToNext()) {
            //读取通讯录的姓名
            String name = cursor.getString(cursor
                    .getColumnIndex(Phone.DISPLAY_NAME));
            //读取通讯录的号码
            String number = cursor.getString(cursor
                    .getColumnIndex(Phone.NUMBER));
            fromRecoreds.add(new ListItemFromRecored(StringUtil.filter(number), name, -1, false));
        }
        Collections.reverse(fromRecoreds);
        return fromRecoreds;
    }
}

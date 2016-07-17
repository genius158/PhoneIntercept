package com.yan.phoneintercept.service;

import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import com.android.internal.telephony.ITelephony;
import com.yan.phoneintercept.bean.RecoredListItemData;
import com.yan.phoneintercept.brodcast.MyReceiver;
import com.yan.phoneintercept.dao.PhoneNumberDao;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by Administrator on 2016/5/13.
 */
public class InterceptService extends IntentService {
    private PhoneNumberDao phoneNumberDao;
    private TelephonyManager telMgr;
    private Context context;
    private MyReceiver myReceiver;

    public InterceptService() {
        super("InterceptService");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplication();
        myReceiver = new MyReceiver();
        phoneNumberDao = PhoneNumberDao.getPhoneNumberDao(context);
        telMgr = (TelephonyManager) context.getSystemService(Service.TELEPHONY_SERVICE);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        excute(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    private void rigistReciver(Context context) {
    }

    private void excute(Intent intent) {
        String number = intent.getStringExtra("number");
        List<RecoredListItemData> listItemDatas = phoneNumberDao.getNumberMessages();
        for (RecoredListItemData listItemData : listItemDatas) {
            if (listItemData.getNumber().equals(number)) {
                phoneNumberDao.insertNumberIS(listItemData.getNumber(), listItemData.getName());
                endCall();
                break;
            }
        }
    }

    /**
     * 挂断电话
     */
    private void endCall() {
        Class<TelephonyManager> c = TelephonyManager.class;
        try {
            Method getITelephonyMethod = c.getDeclaredMethod("getITelephony", (Class[]) null);
            getITelephonyMethod.setAccessible(true);
            ITelephony iTelephony = null;
            iTelephony = (ITelephony) getITelephonyMethod.invoke(telMgr, (Object[]) null);
            iTelephony.endCall();
        } catch (Exception e) {
        }
    }
}

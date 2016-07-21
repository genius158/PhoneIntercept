package com.yan.phoneintercept.brodcast;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.yan.phoneintercept.dao.PhoneNumberDao;
import com.yan.phoneintercept.service.InterceptService;


public class MyReceiver extends BroadcastReceiver {

    PhoneNumberDao phoneNumberDao;

    public MyReceiver() {
    }

    TelephonyManager telMgr;

    @Override
    public void onReceive(Context context, Intent intent) {
        phoneNumberDao = PhoneNumberDao.getPhoneNumberDao(context);

        telMgr = (TelephonyManager) context.getSystemService(Service.TELEPHONY_SERVICE);
        switch (telMgr.getCallState()) {
            case TelephonyManager.CALL_STATE_RINGING:
                String number = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
                if (!TextUtils.isEmpty(number)) {
                    Intent service = new Intent(context, InterceptService.class);
                    service.putExtra("number", number);
                    context.startService(service);
                }
                break;
            case TelephonyManager.CALL_STATE_OFFHOOK:
                break;
            case TelephonyManager.CALL_STATE_IDLE:
                break;
        }
    }
}
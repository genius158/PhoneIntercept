package com.yan.phoneintercept.view.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.yan.phoneintercept.R;
import com.yan.phoneintercept.adapter.FromRecoredListAdapter;
import com.yan.phoneintercept.bean.ListItemFromRecored;
import com.yan.phoneintercept.dao.PhoneNumberDao;
import com.yan.phoneintercept.dao.PhoneNumberFromSystem;

import java.util.List;

public class InputNumber extends Fragment {

    private EditText editText;
    private PhoneNumberFromSystem phoneNumberFromSystem;
    private PhoneNumberDao phoneNumberDao;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        phoneNumberDao = PhoneNumberDao.getPhoneNumberDao(context);
        phoneNumberFromSystem = PhoneNumberFromSystem.Instance(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_input_number, container, false);
        editText = (EditText) view.findViewById(R.id.input_number);

        return view;
    }

    private String getNumber() {
        String number = "";
        if (!TextUtils.isEmpty(editText.getText())) {
            number = editText.getText().toString();
        }
        return number;
    }

    public boolean addPhoneNumber() {
        String number = "";
        if (!TextUtils.isEmpty(editText.getText())) {
            number = editText.getText().toString();
        }
        if (number.length() != 0) {
            List<ListItemFromRecored> fromRecoreds = phoneNumberFromSystem.getPhoneNumberFromMobile();
            boolean isadd = false;
            for (ListItemFromRecored fromRecored : fromRecoreds) {
                if (fromRecored.getNumber().equals(number)) {
                    phoneNumberDao.insertNumberMessage(fromRecored.getNumber(), fromRecored.getName());
                    isadd = true;
                    break;
                }
            }
            if (!isadd) {
                phoneNumberDao.insertNumberMessage(number, "");
            }
            return true;
        }
        return false;
    }

}

package com.yan.phoneintercept.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.yan.phoneintercept.R;
import com.yan.phoneintercept.adapter.FromRecoredListAdapter;
import com.yan.phoneintercept.bean.ListItemFromRecored;
import com.yan.phoneintercept.dao.PhoneNumberDao;
import com.yan.phoneintercept.dao.PhoneNumberFromSystem;

import java.util.ArrayList;
import java.util.List;

public class NumberFromRecored extends Fragment {
    private PhoneNumberFromSystem phoneNumberFromSystem;
    private List<ListItemFromRecored> itemDatas;
    private FromRecoredListAdapter fromRecoredListAdapter;
    private PhoneNumberDao phoneNumberDao;
    private ListView listView;
    private static int type = 0;

    public static NumberFromRecored instance(int type) {
        NumberFromRecored.type = type;
        return new NumberFromRecored();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        phoneNumberDao = PhoneNumberDao.getPhoneNumberDao(context);
        phoneNumberFromSystem = PhoneNumberFromSystem.Instance(context);
        itemDatas = new ArrayList<ListItemFromRecored>();
        itemDatas.clear();
        if (type == 1)
            itemDatas.addAll(phoneNumberFromSystem.getPhonenumberFormSystem());
        else
            itemDatas.addAll(phoneNumberFromSystem.getPhoneNumberFromMobile());
        fromRecoredListAdapter = new FromRecoredListAdapter(context, itemDatas);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recored_number, container, false);
        listView = (ListView) view.findViewById(R.id.list_number_recored);
        listView.setAdapter(fromRecoredListAdapter);
        listView.setOnItemClickListener(onItemClickListener);
        return view;
    }

    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            itemDatas.get(position).setIschecked(!itemDatas.get(position).ischecked());
            fromRecoredListAdapter.notifyDataSetChanged();
        }
    };

    public void setChecked(boolean ischecked) {
        for (ListItemFromRecored listItemFromRecored : itemDatas) {
            listItemFromRecored.setIschecked(ischecked);
        }
        fromRecoredListAdapter.notifyDataSetInvalidated();
    }

    public boolean addNumberIntercept() {
        boolean isadd = false;
        for (ListItemFromRecored itemFromRecored : itemDatas) {
            if (itemFromRecored.ischecked()) {
                Log.i("aaaaaaaaaa",itemFromRecored.getNumber()+"   "+itemFromRecored.getName());
                phoneNumberDao.insertNumberMessage(itemFromRecored.getNumber(), itemFromRecored.getName());
                isadd = true;
            }
        }
        return isadd;
    }


}

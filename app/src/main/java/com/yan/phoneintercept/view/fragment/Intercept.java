package com.yan.phoneintercept.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.yan.phoneintercept.R;
import com.yan.phoneintercept.adapter.RecoredListAdapter;
import com.yan.phoneintercept.bean.RecoredListItemData;
import com.yan.phoneintercept.dao.PhoneNumberDao;

import java.util.List;


public class Intercept extends Fragment {

    private ListView InterceptView;
    private PhoneNumberDao phoneNumberDao;
    private Context context;
    private Button btnClear;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        phoneNumberDao = PhoneNumberDao.getPhoneNumberDao(context);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.intercept_clear) {
                phoneNumberDao.deletNumerIs();
                InterceptView.setAdapter(new RecoredListAdapter(context, phoneNumberDao.getNumberIS()));
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_intercept, container, false);
        btnClear = (Button) view.findViewById(R.id.intercept_clear);
        InterceptView = (ListView) view.findViewById(R.id.intercept_list);
        List<RecoredListItemData> itemDatas = phoneNumberDao.getNumberIS();
        InterceptView.setAdapter(new RecoredListAdapter(context,  phoneNumberDao.getNumberIS()));
        btnClear.setOnClickListener(onClickListener);
        return view;
    }

}

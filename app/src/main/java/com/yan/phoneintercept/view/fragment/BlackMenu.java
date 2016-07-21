package com.yan.phoneintercept.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.yan.phoneintercept.adapter.RecoredListAdapter;
import com.yan.phoneintercept.bean.RecoredListItemData;
import com.yan.phoneintercept.dao.PhoneNumberDao;
import com.yan.phoneintercept.R;

import java.util.ArrayList;
import java.util.List;


public class BlackMenu extends Fragment {

    private ListView blackMenuView;
    private Context context;
    private Button btnAdd;
    private Button btnDelete;
    private PhoneNumberDao phoneNumberDao;
    private List<RecoredListItemData> itemDatas;
    private AddDalog dialogFragment;
    private RecoredListAdapter recoredListAdapter;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        phoneNumberDao = PhoneNumberDao.getPhoneNumberDao(context);
        dialogFragment = new AddDalog();
        itemDatas = new ArrayList<RecoredListItemData>();
        recoredListAdapter = new RecoredListAdapter(context, itemDatas);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.black_mune_add) {
                dialogFragment.show(BlackMenu.this.getChildFragmentManager(), "DialogFragment");
            } else if (v.getId() == R.id.black_mune_delete) {
                for(RecoredListItemData data:itemDatas){
                    Log.e("data",data.ischecked()+"  "+data.getNumber());
                  if(data.ischecked()){
                      phoneNumberDao.deletNumber(data.getNumber());
                  }
                }
                readyData();
            }
        }
    };

    public void readyData() {
        itemDatas.clear();
        itemDatas.addAll(phoneNumberDao.getNumberMessages());
        recoredListAdapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_blackmenu, container, false);
        blackMenuView = (ListView) view.findViewById(R.id.blackmenu_list);
        btnAdd = (Button) view.findViewById(R.id.black_mune_add);
        btnDelete = (Button) view.findViewById(R.id.black_mune_delete);
        btnAdd.setOnClickListener(onClickListener);
        btnDelete.setOnClickListener(onClickListener);
        blackMenuView.setAdapter(recoredListAdapter);
        blackMenuView.setOnItemClickListener(onItemClickListener);
        readyData();
        return view;
    }

    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            itemDatas.get(position).setIschecked(!itemDatas.get(position).ischecked());
            recoredListAdapter.notifyDataSetChanged();

        }
    };

}

package com.yan.phoneintercept.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.yan.phoneintercept.R;


public class ActivityNextFragment extends Fragment {

    private ListView InterceptView;
    private Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_next, container, false);
        InterceptView = (ListView) view.findViewById(R.id.blackmenu_recored_list);

        return view;
    }

}

package com.yan.phoneintercept.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.yan.phoneintercept.R;


public class TabButton extends Fragment implements View.OnClickListener {

    private Button btnIntercept;
    private Button btnBlackMenu;
    private Context context;
    private OnButtonClickListener onButtonClickListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        if (context instanceof OnButtonClickListener) {
            onButtonClickListener = (OnButtonClickListener) context;

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_button, container, false);
        btnIntercept = (Button) view.findViewById(R.id.btn_intercept);
        btnBlackMenu = (Button) view.findViewById(R.id.btn_blalknume);
        btnIntercept.setOnClickListener(this);
        btnBlackMenu.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        ((Button) v).setBackgroundColor(context.getResources().getColor(R.color.btn_select));
        ((Button) v).setTextColor(context.getResources().getColor(R.color.btn_normal));
        if (onButtonClickListener != null) {
            onButtonClickListener.OnClick(v.getId());
        }

        if (v.getId() == R.id.btn_intercept) {
            btnBlackMenu.setBackgroundColor(context.getResources().getColor(R.color.btn_normal));
            btnBlackMenu.setTextColor(context.getResources().getColor(R.color.btn_select));

        } else {
            btnIntercept.setBackgroundColor(context.getResources().getColor(R.color.btn_normal));
            btnIntercept.setTextColor(context.getResources().getColor(R.color.btn_select));

        }
    }

}

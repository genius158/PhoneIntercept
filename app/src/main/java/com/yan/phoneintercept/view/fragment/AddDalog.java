package com.yan.phoneintercept.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.yan.phoneintercept.R;

/**
 * Created by Administrator on 2016/5/11.
 */
public class AddDalog extends DialogFragment implements View.OnClickListener {

    private TextView textView3;
    private TextView textView2;
    private TextView textView1;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.dialog_add, container, false);
        textView3 = (TextView) view.findViewById(R.id.btn_add_from_number);
        textView3.setOnClickListener(this);
        textView2 = (TextView) view.findViewById(R.id.btn_add_from_call);
        textView2.setOnClickListener(this);
        textView1 = (TextView) view.findViewById(R.id.btn_add_from_call_recoed);
        textView1.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        onButtonClickListener.OnClick(v.getId());
        this.dismiss();
    }
}

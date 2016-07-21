package com.yan.phoneintercept.view.entity;

import android.content.Context;
import android.support.v4.view.ActionProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.yan.phoneintercept.R;

/**
 * Created by Administrator on 2016/5/12.
 */
public class CheckedActionPrevider extends ActionProvider {

    private OnCheckBoxChecked onCheckBoxChecked;
    private Context context;
    private CheckBox checkBox;

    public CheckedActionPrevider(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public View onCreateActionView() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.menu_item_checkedbox, null);
        checkBox = (CheckBox) view.findViewById(R.id.cheked_actionbar_menu_box);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (onCheckBoxChecked != null) {
                    onCheckBoxChecked.onChecked(isChecked);
                }
            }
        });
        return view;
    }

    public boolean isChecked() {
        return checkBox.isChecked();
    }
    public void setOnCheckBoxChecked(OnCheckBoxChecked onCheckBoxChecked) {
        this.onCheckBoxChecked = onCheckBoxChecked;
    }
    public interface OnCheckBoxChecked {
        void onChecked(boolean ischeked);
    }

}

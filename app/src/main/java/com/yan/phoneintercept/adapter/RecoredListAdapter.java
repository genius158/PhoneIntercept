package com.yan.phoneintercept.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.yan.phoneintercept.R;
import com.yan.phoneintercept.bean.RecoredListItemData;

import java.util.List;

/**
 * Created by Administrator on 2016/5/11.
 */
public class RecoredListAdapter extends BaseAdapter {

    private List<RecoredListItemData> itemDatas;
    private Context context;

    public RecoredListAdapter(Context context, List<RecoredListItemData> itemDatas) {
        this.itemDatas = itemDatas;
        this.context = context;

    }

    @Override
    public int getCount() {
        return itemDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return itemDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder {
        public TextView textView;
        public CheckBox checkBox;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.black_menu_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) convertView.findViewById(R.id.phone_number);
            viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.phone_check_box1);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textView.setText(!TextUtils.isEmpty(itemDatas.get(position).getName())
                ? itemDatas.get(position).getName()
                : itemDatas.get(position).getNumber());
        viewHolder.checkBox.setChecked(itemDatas.get(position).ischecked());

        return convertView;
    }
}

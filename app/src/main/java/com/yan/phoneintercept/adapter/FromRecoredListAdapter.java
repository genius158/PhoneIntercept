package com.yan.phoneintercept.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.yan.phoneintercept.R;
import com.yan.phoneintercept.bean.ListItemFromRecored;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Administrator on 2016/5/11.
 */
public class FromRecoredListAdapter extends BaseAdapter {

    private List<ListItemFromRecored> itemDatas;
    private Context context;

    public FromRecoredListAdapter(Context context, List<ListItemFromRecored> itemDatas) {
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
        public TextView textView1;
        public TextView textView2;
        public TextView textView3;
        public CheckBox checkBox;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.from_recored_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textView1 = (TextView) convertView.findViewById(R.id.phone_mssage_name);
            viewHolder.textView2 = (TextView) convertView.findViewById(R.id.phone_mssage_number);
            viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.phone_check_box2);
            viewHolder.textView3 = (TextView) convertView.findViewById(R.id.phone_number_data);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String time = "";
        if (itemDatas.get(position).getTime() != -1) {

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(itemDatas.get(position).getTime());

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            time = simpleDateFormat.format(calendar.getTime());

        }
        viewHolder.textView1.setText(itemDatas.get(position).getName());
        viewHolder.textView2.setText(itemDatas.get(position).getNumber());
        viewHolder.textView3.setText(time);
        viewHolder.checkBox.setChecked(itemDatas.get(position).ischecked());

        return convertView;
    }
}

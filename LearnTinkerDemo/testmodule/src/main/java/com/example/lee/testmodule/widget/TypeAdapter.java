package com.example.lee.testmodule.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.lee.testmodule.R;
import com.example.lee.testmodule.bean.TestListBean;

import java.util.List;

/**
 * Created by Lee on 2017/7/20.
 */

public class TypeAdapter extends BaseAdapter {

    private int select = 0;
    private Context context;
    private List list;
    private LayoutInflater inflater;

    public TypeAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setList(List list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.type_item, null);
            holder.textView = (TextView) convertView.findViewById(R.id.text);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        TestListBean bean = (TestListBean) list.get(position);
        holder.textView.setText(bean.getType());
        if (position == select) {
            holder.textView.setTextColor(context.getResources().getColor(R.color.dish_category_text_color));
            holder.textView.setBackgroundResource(R.color.text_white);
        } else {
            holder.textView.setTextColor(context.getResources().getColor(R.color.text_gray_light));
            holder.textView.setBackgroundResource(R.color.background);
        }

        return convertView;
    }

    public void setSelected(int select) {
        this.select = select;
        notifyDataSetChanged();
    }

    class ViewHolder {

        TextView textView;
    }

}

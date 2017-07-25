package com.example.lee.testmodule.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.lee.testmodule.R;
import com.example.lee.testmodule.bean.TestListBean;

import java.util.List;

/**
 * Created by Lee on 2017/7/20.
 */

public class ContentAdapter extends FatListViewAdapter {


    private Context context;
    private LayoutInflater inflater;
    private LinkScrollListView linkScrollListView;
    private List<TestListBean> list;


    public ContentAdapter(Context context, LinkScrollListView linkScrollListView) {
        this.context = context;
        this.linkScrollListView = linkScrollListView;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public Object getItem(int section, int position) {
//        int listSize = list.get(section).getInners().size();
//        if (listSize % 2 == 0) {
//            return list.get(section).getInners().get(position * 2);
//        } else {
//            return list.get(section).getInners().get(position * 2 + 1);
//        }
         return list.get(section).getInners().get(position);
    }

    @Override
    public long getItemId(int section, int position) {
        return position;
//        int listSize = list.get(section).getInners().size();
//        if (listSize % 2 == 0) {
//            return position * 2;
//        } else {
//            return position * 2 + 1;
//        }
    }

    @Override
    public int getSectionCount() {
        return list.size();
    }

    @Override
    public int getCountForSection(int section) {
        int listSize = list.get(section).getInners().size();
        if (listSize % 2 == 0) {
            return listSize / 2;
        } else {
            return listSize / 2 + 1;
        }
//        return list.get(section).getInners().size();
    }

    @Override
    public View getItemView(int section, int position, View convertView, ViewGroup parent) {
//        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_food_list, null);
//        }
        TextView itemFoodNameTv = (TextView) convertView.findViewById(R.id.item_food_name_tv);
        TextView itemFoodNameTv2 = (TextView) convertView.findViewById(R.id.item_food_name_tv2);
        TestListBean.Inner bean1;
        if (position * 2 < list.get(section).getInners().size()) {

            bean1 = list.get(section).getInners().get(position * 2);
            itemFoodNameTv.setText(bean1.getName());
        }
        if ((position * 2) + 1 < list.get(section).getInners().size()) {
            bean1 = list.get(section).getInners().get(position * 2 + 1);
            itemFoodNameTv2.setText(bean1.getName());
        } else {
            itemFoodNameTv2.setVisibility(View.INVISIBLE);
        }
        return convertView;
    }

    @Override
    public View getSectionHeaderView(int section, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_header_list, null);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.item_food_header_name_tv);
        final TestListBean bean;
        bean = list.get(section);
        if (bean != null) {
            textView.setText(bean.getType());
        }

        return convertView;
    }

    public void setList(List<TestListBean> datas) {
        list = datas;
    }

    private int sel = -1;

    public void setSelected(int position) {
        this.sel = position;
        notifyDataSetChanged();
    }
}

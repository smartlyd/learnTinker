package com.example.lee.learn.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lee.learn.R;
import com.example.lee.learn.bean.TestListBean;

import java.util.List;

/**
 * Created by Lee on 2017/7/20.
 */

public class ContentAdapter extends FatListViewAdapter{


    private Context context;
    private LayoutInflater inflater;
    private LinkScrollListView linkScrollListView;
    private List<TestListBean> list;


    public ContentAdapter(Context context,LinkScrollListView linkScrollListView) {
        this.context = context;
        this.linkScrollListView = linkScrollListView;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public Object getItem(int section, int position) {
        return list.get(section).getInners().get(position);
    }

    @Override
    public long getItemId(int section, int position) {
        return position;
    }

    @Override
    public int getSectionCount() {
        return list.size();
    }

    @Override
    public int getCountForSection(int section) {
        return list.get(section).getInners().size();
    }

    @Override
    public View getItemView(int section, int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_food_list, null);
        }
        TextView itemFoodNameTv = (TextView) convertView.findViewById(R.id.item_food_name_tv);
        final TestListBean.Inner bean = list.get(section).getInners().get(position);
        if(bean != null){
            itemFoodNameTv.setText(bean.getName());
        }
        return convertView;
    }

    @Override
    public View getSectionHeaderView(int section, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_header_list, null);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.item_food_header_name_tv);

        final TestListBean bean = list.get(section);
        if (bean != null) {
            textView.setText(bean.getType());
        }

        return convertView;
    }

    public void setList(List<TestListBean> datas) {
        list = datas;
    }
}

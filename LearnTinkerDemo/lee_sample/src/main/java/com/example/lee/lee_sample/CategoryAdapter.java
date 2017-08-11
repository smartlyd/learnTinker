package com.example.lee.lee_sample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.BindView;
import com.example.lee.lee_api.ViewInjector;

import java.util.List;

/**
 * Created by Lee on 2017/8/11.
 */

class CategoryAdapter extends ArrayAdapter<String> {

    private Context context;
    private List<String> list;

    private LayoutInflater inflater;


    public CategoryAdapter(@NonNull Context context, @NonNull List<String> objects) {
        super(context, -1, objects);
        this.context = context;
        this.list = objects;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_category, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.idTitleTv.setText(getItem(position));

        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.id_icon_iv)
        ImageView idIconIv;
        @BindView(R.id.id_title_tv)
        TextView idTitleTv;

        ViewHolder(View view) {
            ViewInjector.injectView(this, view);
        }
    }
}

package com.example.lee.learn.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lee.testmodule.bean.TestListBean;

import java.util.List;

/**
 * Created by Lee on 2017/7/21.
 */

public class RecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    List<TestListBean> list;
    private Context context;
    private LayoutInflater inflater;

    public RecycleAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {

        } else if (holder instanceof MyViewHolder2) {

        }
    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    /**
     * title holder
     */
    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView title;

        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }

    /**
     * item holder
     */
    class MyViewHolder2 extends RecyclerView.ViewHolder {

        private TextView item;

        public MyViewHolder2(View itemView) {
            super(itemView);
        }
    }

}

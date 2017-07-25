package com.example.lee.testmodule.widget;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Lee on 2017/7/19.
 */
public interface PinnedSectionHeadeInt {

    boolean isSectionHeader(int pos);
    int getSectionForPosition(int pos);
    View getSectionHeaderView(int section, View convertView, ViewGroup parent);
    int getSectionHeaderViewType(int pos);
    int getCount();
}

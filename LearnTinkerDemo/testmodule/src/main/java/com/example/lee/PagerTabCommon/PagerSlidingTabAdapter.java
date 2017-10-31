package com.example.lee.PagerTabCommon;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2016/8/4 0004.
 * tab pager 适配器
 */
public class PagerSlidingTabAdapter extends FragmentPagerAdapter {
    private List<String> tabs;
    private List<Fragment> fragments;

    public PagerSlidingTabAdapter(FragmentManager fm) {
        super(fm);
    }
    public void setPagerSlidingTagDatas(List<String> tabs, List<Fragment> fragments){
        this.tabs = tabs;
        this.fragments = fragments;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs.get(position);
    }

    @Override
    public int getCount() {
        if(null==tabs){
            return 0;
        }
        return tabs.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public Fragment getItem(int position) {
        if(position>=getCount()){
            position = getCount()-1;
        }
        return fragments.get(position);
    }

}
package com.example.lee.lee_api;

import android.app.Activity;
import android.view.View;

/**
 * Created by Lee on 2017/8/11.
 */

public enum Finder {

    VIEW {
        @Override
        public View findView(Object source, int id) {
            return ((View) source).findViewById(id);
        }
    },
    ACTIVITY {
        @Override
        public View findView(Object source, int id) {
            return ((Activity) source).findViewById(id);
        }
    };

    public abstract View findView(Object source, int id);
}

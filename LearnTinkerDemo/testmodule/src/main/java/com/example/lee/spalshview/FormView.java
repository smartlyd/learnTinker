package com.example.lee.spalshview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.lee.testmodule.R;


/**
 * Created by Lee on 2017/8/18.
 */

public class FormView extends LinearLayout {
    private EditText e1, e2;

    public FormView(Context context) {
        super(context);
        loadView();
    }


    public FormView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        loadView();
    }

    public FormView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        loadView();
    }

    private void loadView() {
        setOrientation(VERTICAL);
        LayoutInflater.from(getContext()).inflate(R.layout.form_view, this);
        e1 = (EditText) findViewById(R.id.edit1);
        e2 = (EditText) findViewById(R.id.edit2);
    }

    @Override
    public void setFocusable(boolean focusable) {
        super.setFocusable(focusable);
        e1.setFocusable(focusable);
        e2.setFocusable(focusable);

    }
}

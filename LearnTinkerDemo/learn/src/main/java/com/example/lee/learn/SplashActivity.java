package com.example.lee.learn;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.lee.learn.bean.TestListBean;
import com.example.lee.learn.widget.ContentAdapter;
import com.example.lee.learn.widget.LinkScrollListView;
import com.example.lee.learn.widget.OnSectionChangedListener;
import com.example.lee.learn.widget.TypeAdapter;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends Activity implements View.OnClickListener {


    private static final String TAG = "dddd";

    private ListView type;
    private LinkScrollListView content;
    private List<TestListBean> datas = new ArrayList<>();
    private TypeAdapter typeAdapter;
    private ContentAdapter contentAdapter;
    private int selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        type = (ListView) findViewById(R.id.listType);
        content = (LinkScrollListView) findViewById(R.id.linkList);
        typeAdapter = new TypeAdapter(getApplicationContext());

        contentAdapter = new ContentAdapter(getApplicationContext(), content);

//        TestListBean.Inner inner = new TestListBean.Inner();
        TestListBean listBean = new TestListBean();
        for (int i = 0; i < 8; i++) {
            List<TestListBean.Inner> inners = new ArrayList<>();
            listBean.setId(i);
            listBean.setType("fff" + i + "type");
            for (int j = 0; j < 6; j++) {
                TestListBean.Inner inner = new TestListBean.Inner();
                inner.setId(i);
                inner.setName("abc" + i);
                inners.add(inner);
            }
            listBean.setInners(inners);
            datas.add(listBean);
        }
        typeAdapter.setList(datas);
        contentAdapter.setList(datas);

        type.setAdapter(typeAdapter);
        content.setAdapter(contentAdapter);

        initListener();
    }

    private void initListener() {
        type.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int position_index = contentAdapter.getPositionBySection((int) id);
                selected = position;
                typeAdapter.setSelected(position);
                content.setSelectionFromTop(position_index, -2);
            }
        });
        content.setSectionChangedListener(new OnSectionChangedListener() {
            public void onSectionChanged(final int section) {
                selected = section;
                typeAdapter.setSelected(section);
                content.setSelection(section);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }
}

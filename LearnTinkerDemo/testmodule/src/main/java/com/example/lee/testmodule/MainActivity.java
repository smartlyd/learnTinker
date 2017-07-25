package com.example.lee.testmodule;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.lee.learn.widget.RecycleAdapter;
import com.example.lee.testmodule.bean.TestListBean;
import com.example.lee.testmodule.widget.ContentAdapter;
import com.example.lee.testmodule.widget.LinkScrollGridView;
import com.example.lee.testmodule.widget.LinkScrollListView;
import com.example.lee.testmodule.widget.OnSectionChangedListener;
import com.example.lee.testmodule.widget.TypeAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView type;
    private LinkScrollListView content;
    private List<TestListBean> datas = new ArrayList<>();
    private TypeAdapter typeAdapter;
    private ContentAdapter contentAdapter;
    private int selected;

    private LinkScrollGridView gridView;
    private RecyclerView recycleview;
    private int total;
    private GridLayoutManager layoutManager;
    private RecycleAdapter recycleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        type = (ListView) findViewById(R.id.listType);
        content = (LinkScrollListView) findViewById(R.id.linkList);

        gridView = (LinkScrollGridView) findViewById(R.id.linkGrid);

        recycleview = (RecyclerView) findViewById(R.id.recycleview);

        typeAdapter = new TypeAdapter(getApplicationContext());

        contentAdapter = new ContentAdapter(getApplicationContext(), content);


        layoutManager = new GridLayoutManager(getApplicationContext(),2,GridLayoutManager.VERTICAL,false);
        recycleview.setLayoutManager(layoutManager);




//        TestListBean.Inner inner = new TestListBean.Inner();
        for (int i = 0; i < 40; i++) {
            TestListBean listBean = new TestListBean();
            List<TestListBean.Inner> inners = new ArrayList<>();
            listBean.setId(i);
            listBean.setType("fff" + i + "type");
            for (int j = 0; j < 15; j++) {
                TestListBean.Inner inner = new TestListBean.Inner();
                inner.setId(j);
                inner.setName("abc" + j);
                inners.add(inner);
            }
            listBean.setInners(inners);
            datas.add(listBean);
        }

        typeAdapter.setList(datas);
        contentAdapter.setList(datas);

        type.setAdapter(typeAdapter);
        content.setAdapter(contentAdapter);
        gridView.setAdapter(contentAdapter);

        initListener();
    }


    private void initListener() {
        type.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
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
                type.setSelection(section);
            }
        });
        gridView.setSectionChangedListener(new OnSectionChangedListener() {
            public void onSectionChanged(final int section) {
                selected = section;
                typeAdapter.setSelected(section);
                type.setSelection(section);
            }
        });
    }
}

package com.example.lee.lee_sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.BindView;
import com.example.lee.lee_api.ViewInjector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {


    @BindView(R.id.listview)
    ListView listView;


    private List<String> mData = new ArrayList<>(Arrays.asList("Simple Use", "RecyclerView Use"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ViewInjector.injectView(this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CategoryActivity.this.onItemClick(position);
            }
        });
        listView.setAdapter(new CategoryAdapter(getApplicationContext(),mData));
    }

    private void onItemClick(int position) {
        Toast.makeText(getApplicationContext(), "position = " + position, Toast.LENGTH_LONG).show();
    }
}

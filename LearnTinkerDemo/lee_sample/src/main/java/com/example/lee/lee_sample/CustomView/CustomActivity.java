package com.example.lee.lee_sample.CustomView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lee.lee_sample.R;

public class CustomActivity extends AppCompatActivity {

    CustomView customView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        customView = (CustomView) findViewById(R.id.customview);
    }
}

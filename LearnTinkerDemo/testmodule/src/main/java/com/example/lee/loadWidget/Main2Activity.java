package com.example.lee.loadWidget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lee.testmodule.R;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        AVLoadingIndicatorView avview = (AVLoadingIndicatorView) findViewById(R.id.avview);
//        avview.setIndicator(new Ballpin);
    }
}

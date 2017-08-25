package com.example.lee.route;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.lee.testmodule.R;

public class TestRouteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_route);
        Intent intent = getIntent();
        String key = intent.getStringExtra("key");
        int year = intent.getIntExtra("year", 0);
        Log.e("ddd: ", "platform" + key);
        Log.e("ddd: ", "year" + String.valueOf(year));
        setResult(RESULT_OK);
    }
}

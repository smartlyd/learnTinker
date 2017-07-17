package com.example.lee.learn;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SplashActivity extends Activity implements View.OnClickListener {


    private static final String TAG = "dddd";
    private Button btn1, btn2, btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                Log.d(TAG, "i'm the setting");
                break;
            case R.id.btn2:
                Log.d(TAG, "i'm the calling");
                break;
            case R.id.btn3:
                Log.d(TAG, "i'm the ringing");
                break;
        }
    }
}

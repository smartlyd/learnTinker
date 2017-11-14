package com.example.lee;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.lee.spalshview.MyView;
import com.example.lee.testmodule.R;
import com.example.lee.utils.PhoneUtil;

public class AboutDeviceActivity extends AppCompatActivity implements View.OnClickListener {


    private static final String TAG = "dddddd";
    private TextView tv_isphone, tv_getimei, tv_getimsi, tv_getversion, tv_getandroidId, tv_getproducer, tv_gettype;

    private MyView myview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_device);
        tv_isphone = (TextView) findViewById(R.id.tv_isphone);
        tv_getimei = (TextView) findViewById(R.id.tv_getimei);
        tv_getimsi = (TextView) findViewById(R.id.tv_getimsi);
        tv_getversion = (TextView) findViewById(R.id.tv_getversion);
        tv_getandroidId = (TextView) findViewById(R.id.tv_getandroidId);
        tv_getproducer = (TextView) findViewById(R.id.tv_getproducer);
        tv_gettype = (TextView) findViewById(R.id.tv_gettype);

        myview = (MyView)findViewById(R.id.myview);
        myview.setOnClickListener(this);
        initData();
    }

    private void initData() {
        PhoneUtil putil = new PhoneUtil(getApplicationContext());
        tv_isphone.setText("是否是手机:" + putil.isPhone());
        tv_getimei.setText("imei:" + putil.getIMEI());
        tv_getimsi.setText("imsi:" + putil.getIMSI());
        tv_getversion.setText("系统版本" + putil.getSDKVersion());
        tv_getandroidId.setText("android_id:" + putil.getAndroidID());
        tv_getproducer.setText("设备厂商：" + putil.getManufacturer());
        tv_gettype.setText("设备型号" + putil.getModel());



        Log.d(TAG,"是否是手机:" + putil.isPhone());
        Log.d(TAG,"imei:" + putil.getIMEI());
        Log.d(TAG,"imsi:" + putil.getIMSI());
        Log.d(TAG,"系统版本" + putil.getSDKVersion());
        Log.d(TAG,"android_id:" + putil.getAndroidID());
        Log.d(TAG,"设备厂商：" + putil.getManufacturer());
        Log.d(TAG,"设备型号" + putil.getModel());
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this,SpalshActivity.class));
    }
}

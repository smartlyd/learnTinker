package com.example.lee.wxapi;

import android.app.Activity;
import android.os.Bundle;

import com.example.lee.testmodule.R;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WXEntryActivity extends Activity {


    private IWXAPI iwxapi;
    private final String APPID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxentry);
        iwxapi = WXAPIFactory.createWXAPI(getApplicationContext(), APPID);
    }
}

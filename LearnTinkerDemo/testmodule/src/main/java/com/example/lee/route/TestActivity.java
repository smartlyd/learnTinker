package com.example.lee.route;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.lee.testmodule.R;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn1, btn2;
    private IntentWrapper intentWrapper;
    private Router router;
    private IntentService is;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        router = new Router.Builder().interceptor(new Interceptor() {
            @Override
            public boolean intercept(IntentWrapper intentWrapper) {
                return false;
            }
        }).build();
        is = router.create(IntentService.class, this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                is.intent2TestRouteActivity("android", 100);
                break;
            case R.id.btn2:
                IntentWrapper intentWrapper = is.intent2TestRouteActivityRaw("ios", 10);
                intentWrapper.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                intentWrapper.start();
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.e("ddd: ", "request" + String.valueOf(requestCode));
        Log.e("ddd: ", "result" + String.valueOf(resultCode));
    }
}

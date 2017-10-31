package com.example.lee.loadWidget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.lee.loadWidget.indicators.BallSpinFadeLoaderIndicator;
import com.example.lee.testmodule.R;

/**
 * Created by Jack Wang on 2016/8/5.
 */

public class IndicatorActivity extends AppCompatActivity{

    private AVLoadingIndicatorView avi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indicator);

        String indicator=getIntent().getStringExtra("indicator");
        avi= (AVLoadingIndicatorView) findViewById(R.id.avi);


        BallSpinFadeLoaderIndicator indicator1 = new BallSpinFadeLoaderIndicator();
//        avi.setIndicatorColor(R.color.colorAccent);
//        avi.setIndicator(indicator1);
        avi.setIndicator(indicator);
    }


    public void hideClick(View view) {
        avi.hide();
        // or avi.smoothToHide();
    }

    public void showClick(View view) {
        avi.show();
        // or avi.smoothToShow();
    }
}

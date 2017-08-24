package com.example.lee.mvp;

/**
 * Created by Lee on 2017/8/23.
 */

public class MyPresenter implements IPresenter {
    private IView iView;

    public MyPresenter() {
    }

    public void setiView(IView iView) {
        this.iView = iView;
    }

    @Override
    public boolean requestDate() {
        iView.updateView("Hello world!");
        return false;
    }
}

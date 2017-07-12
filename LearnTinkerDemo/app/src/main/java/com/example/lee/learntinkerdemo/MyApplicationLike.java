package com.example.lee.learntinkerdemo;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.multidex.MultiDex;

import com.tencent.tinker.anno.DefaultLifeCycle;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.loader.app.DefaultApplicationLike;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * Created by Lee on 2017/7/12.
 */

@DefaultLifeCycle(application = "com.example.lee.learntinkerdemo.MyApplication",
        flags = ShareConstants.TINKER_ENABLE_ALL,
        loadVerifyFlag = false)

public class MyApplicationLike extends DefaultApplicationLike {


    private static Application myApp;


    public MyApplicationLike(Application application, int tinkerFlags, boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime, long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        myApp = getApplication();

    }

    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
        MultiDex.install(base);
        TinkerInstaller.install(this);
        //在初始化的时候调用加载补丁的方法,路径是实际补丁放的位置
      //  TinkerInstaller.onReceiveUpgradePatch(this.getApplication(), Environment.getExternalStorageDirectory().getAbsolutePath()+"/patch_signed_7zip.apk");
    }

    public static Context getContext(){
        return myApp;
    }
    public static Application getMyApp() {
        return myApp;
    }
}

package com.example.lee.route;

import com.example.lee.route.annotations.ClassName;
import com.example.lee.route.annotations.Key;
import com.example.lee.route.annotations.RequestCode;

/**
 * Created by Lee on 2017/8/25.
 */
public interface IntentService {
    @ClassName("com.example.lee.route.TestRouteActivity")
    @RequestCode(100)
    void intent2TestRouteActivity(@Key("key") String key, @Key("year") int year);

    @ClassName("com.example.lee.route.TestRouteActivity2")
    IntentWrapper intent2TestRouteActivityRaw(@Key("key") String key, @Key("year") int year);
}

package com.example.lee.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;

/**
 * Created by Lee on 2017/9/23.
 */

public class Utils {
    /**
     * 从Resources中获取图片资源，转化为Bitmap格式返回
     * @param c
     * @param res
     * @return
     */
    public static Bitmap decodeCustomRes(Context c, int res) {
        InputStream is = c.getResources().openRawResource(res);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        options.inSampleSize = 1;//原尺寸加载图片
        Bitmap bmp = BitmapFactory.decodeStream(is, null, options);
        return bmp;
    }
}

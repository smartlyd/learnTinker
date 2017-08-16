package com.example.lee.lee_sample.CustomView;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.example.lee.lee_sample.R;

/**
 * Created by Lee on 2017/8/14.
 */

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class CustomView extends View {
    static Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    static Path path = new Path();

    static {
        paint.setStyle(Paint.Style.STROKE);
        path.lineTo(100, 100);
        path.lineTo(200, 100);

        path.lineTo(70, 300);
    }

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.a);
        Bitmap bitmap3 = BitmapFactory.decodeResource(getResources(), R.drawable.b);
        Camera camera = new Camera();
        canvas.save();

        camera.rotateZ(30); // 旋转 Camera 的三维空间
        camera.applyToCanvas(canvas); // 把旋转投影到 Canvas

        canvas.drawBitmap(bitmap3, 100, 100, paint);
        canvas.restore();
    }

}

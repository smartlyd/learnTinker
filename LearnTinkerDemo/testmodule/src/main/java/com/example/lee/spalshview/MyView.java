package com.example.lee.spalshview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.lee.testmodule.R;

/**
 * Created by Lee on 2017/10/31.
 */

public class MyView extends View {
    private static final String TAG = "MYVIEW";
    private Context context;

    private int minWidth,maxWidth;

    private Paint backPaint,forPaint;

    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MyView, 0, 0);

        minWidth = typedArray.getDimensionPixelSize(R.styleable.MyView_minWidthT,20);
        minWidth = typedArray.getDimensionPixelSize(R.styleable.MyView_maxWidthT,40);
        Log.d(TAG,"min = " +minWidth);
        Log.d(TAG,"max = " +maxWidth);
        init();
    }

    private void init() {
        backPaint = new Paint();
        forPaint = new Paint();
        backPaint.setAntiAlias(true);
        forPaint.setAntiAlias(true);
        backPaint.setColor(Color.BLUE);
        forPaint.setColor(Color.GREEN);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawFor(canvas);
        drawBack(canvas);
    }

    private void drawBack(Canvas canvas) {

        canvas.drawArc(new RectF(100,100,180,150),20,180,false,backPaint);
    }

    private void drawFor(Canvas canvas) {
        canvas.drawCircle(80,80,100,forPaint);
    }
}

package com.example.lee.loadWidget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.example.lee.loadWidget.indicators.BallPulseIndicator;
import com.example.lee.testmodule.R;

public class LoadingView extends View {

    private static final String TAG = "AVLoadingIndicatorView";

    private static final BallPulseIndicator DEFAULT_INDICATOR = new BallPulseIndicator();

    private static final int MIN_SHOW_TIME = 500; // ms
    private static final int MIN_DELAY = 500; // ms

    private long mStartTime = -1;

    private boolean mPostedHide = false;

    private boolean mPostedShow = false;

    private boolean mDismissed = false;

    private final Runnable mDelayedHide = new Runnable() {

        @Override
        public void run() {
            mPostedHide = false;
            mStartTime = -1;
            setVisibility(View.GONE);
        }
    };

    private final Runnable mDelayedShow = new Runnable() {

        @Override
        public void run() {
            mPostedShow = false;
            if (!mDismissed) {
                mStartTime = System.currentTimeMillis();
                setVisibility(View.VISIBLE);
            }
        }
    };

    int mMinWidth;
    int mMaxWidth;
    int mMinHeight;
    int mMaxHeight;


    private boolean mShouldStartAnimationDrawable;

    public LoadingView(Context context) {
        super(context);
        init(context, null, 0, 0);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0, R.style.AVLoadingIndicatorView);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, R.style.AVLoadingIndicatorView);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, R.style.AVLoadingIndicatorView);
    }


    private Context context;
    private View loadingBgView,loadingFgView;

    private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        this.context = context;
        mMinWidth = 24;
        mMaxWidth = 48;
        mMinHeight = 24;
        mMaxHeight = 48;
        final TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.AVLoadingIndicatorView, defStyleAttr, defStyleRes);
        mMinWidth = a.getDimensionPixelSize(R.styleable.AVLoadingIndicatorView_minWidth, mMinWidth);
        mMaxWidth = a.getDimensionPixelSize(R.styleable.AVLoadingIndicatorView_maxWidth, mMaxWidth);
        mMinHeight = a.getDimensionPixelSize(R.styleable.AVLoadingIndicatorView_minHeight, mMinHeight);
        mMaxHeight = a.getDimensionPixelSize(R.styleable.AVLoadingIndicatorView_maxHeight, mMaxHeight);
        a.recycle();


    }

}

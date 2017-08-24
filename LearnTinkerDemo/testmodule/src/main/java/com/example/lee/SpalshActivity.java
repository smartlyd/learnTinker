package com.example.lee;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lee.mvp.IView;
import com.example.lee.mvp.MyPresenter;
import com.example.lee.spalshview.FormView;
import com.example.lee.spalshview.MyVideoView;
import com.example.lee.testmodule.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class SpalshActivity extends AppCompatActivity implements View.OnClickListener, IView {


    public static final String VIDEO_NAME = "welcome.mp4";

    private MyVideoView mVideoView;

    private InputType inputType = InputType.NONE;

    private Button buttonLeft, buttonRight;

    private FormView formView;


    private TextView appName, tv_presenter;
    private MyPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            window.setFlags(
//                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
//                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        getSupportActionBar().hide();

        findView();

        initView();

        File videoFile = getFileStreamPath(VIDEO_NAME);
        if (!videoFile.exists()) {
            videoFile = copyVideoFile();
        }

        playVideo(videoFile);

        playAnim();

    }

    private void findView() {
        mVideoView = (MyVideoView) findViewById(R.id.videoView);
        buttonLeft = (Button) findViewById(R.id.buttonLeft);
        buttonRight = (Button) findViewById(R.id.buttonRight);
        formView = (FormView) findViewById(R.id.formView);
        appName = (TextView) findViewById(R.id.appName);
        tv_presenter = (TextView) findViewById(R.id.tv_presenter);

        formView.post(new Runnable() {
            @Override
            public void run() {
                int delta = formView.getTop() + formView.getHeight();
                formView.setTranslationY(-1 * delta);
            }
        });
    }

    private void initView() {
        presenter = new MyPresenter();

        presenter.setiView(this);
        buttonRight.setOnClickListener(this);
        buttonLeft.setOnClickListener(this);
    }

    private void playVideo(File videoFile) {
        mVideoView.setVideoPath(videoFile.getPath());
        mVideoView.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
                mediaPlayer.start();
            }
        });
    }

    private void playAnim() {
        ObjectAnimator anim = ObjectAnimator.ofFloat(appName, "alpha", 0, 1);
        anim.setDuration(4000);
        anim.setRepeatCount(1);
        anim.setRepeatMode(ObjectAnimator.REVERSE);
        anim.start();
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                appName.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void playAnim2() {
        ObjectAnimator anim = ObjectAnimator.ofFloat(tv_presenter, "alpha", 0, 1);
        anim.setDuration(4000);
        anim.setRepeatCount(1);
        anim.setRepeatMode(ObjectAnimator.REVERSE);
        anim.start();
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                tv_presenter.setVisibility(View.INVISIBLE);
            }
        });
    }

    @NonNull
    private File copyVideoFile() {
        File videoFile;
        try {
            FileOutputStream fos = openFileOutput(VIDEO_NAME, MODE_PRIVATE);
//            InputStream in = getResources().openRawResource(R.);
            InputStream in = getResources().openRawResource(R.raw.welcome);
            byte[] buff = new byte[1024];
            int len = 0;
            while ((len = in.read(buff)) != -1) {
                fos.write(buff, 0, len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        videoFile = getFileStreamPath(VIDEO_NAME);
        if (!videoFile.exists())
            throw new RuntimeException("video file has problem, are you sure you have welcome_video.mp4 in res/raw folder?");
        return videoFile;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mVideoView.stopPlayback();
    }

    @Override
    public void onClick(View view) {
        int delta = formView.getTop() + formView.getHeight();
        switch (inputType) {
            case NONE:

                formView.animate().translationY(0).alpha(1).setDuration(500).start();
                if (view == buttonLeft) {
                    inputType = InputType.LOGIN;
                    buttonLeft.setText(R.string.button_confirm_login);
                    buttonRight.setText(R.string.button_cancel_login);
                } else if (view == buttonRight) {
                    inputType = InputType.SIGN_UP;
                    buttonLeft.setText(R.string.button_confirm_signup);
                    buttonRight.setText(R.string.button_cancel_signup);
                }

                break;
            case LOGIN:

                formView.animate().translationY(-1 * delta).alpha(0).setDuration(500).start();
                if (view == buttonLeft) {

                } else if (view == buttonRight) {

                }
                inputType = InputType.NONE;
                buttonLeft.setText(R.string.button_login);
                buttonRight.setText(R.string.button_signup);

                presenter.requestDate();


                break;
            case SIGN_UP:

                formView.animate().translationY(-1 * delta).alpha(0).setDuration(500).start();
                if (view == buttonLeft) {

                } else if (view == buttonRight) {

                }
                inputType = InputType.NONE;
                buttonLeft.setText(R.string.button_login);
                buttonRight.setText(R.string.button_signup);
                break;
        }
    }

    @Override
    public void updateView(String str) {
        tv_presenter.setText(str);
        playAnim2();
    }

    enum InputType {
        NONE, LOGIN, SIGN_UP;
    }
}

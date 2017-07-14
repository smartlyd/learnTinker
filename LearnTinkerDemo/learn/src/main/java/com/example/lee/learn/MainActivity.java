package com.example.lee.learn;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.tencent.tinker.lib.library.TinkerLoadLibrary;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;

public class MainActivity extends Activity {

    private static final String TAG = "ddd";


    private String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e(TAG, "i am on onCreate classloader:" + MainActivity.class.getClassLoader().toString());
        Log.e(TAG, "i am on patch onCreate");
        Log.e(TAG, "i hope it can success");

        Button loadPatchButton = (Button) findViewById(R.id.loadPatch);

        loadPatchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getPermission()) {

                    TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(),
                            Environment.getExternalStorageDirectory().getAbsolutePath() + "/patch_signed_7zip.apk");
                }
//                TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(), Environment.getExternalStorageDirectory().getAbsolutePath() + "/patch_signed_7zip.b");
            }
        });

        Button loadLibraryButton = (Button) findViewById(R.id.loadLibrary);

        loadLibraryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // #method 1, hack classloader library path
                TinkerLoadLibrary.installNavitveLibraryABI(getApplicationContext(), "armeabi");
                System.loadLibrary("stlport_shared");

                // #method 2, for lib/armeabi, just use TinkerInstaller.loadLibrary
//                TinkerLoadLibrary.loadArmLibrary(getApplicationContext(), "stlport_shared");

                // #method 3, load tinker patch library directly
//                TinkerInstaller.loadLibraryFromTinker(getApplicationContext(), "assets/x86", "stlport_shared");

            }
        });

        Button cleanPatchButton = (Button) findViewById(R.id.cleanPatch);

        cleanPatchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tinker.with(getApplicationContext()).cleanPatch();
            }
        });

        Button killSelfButton = (Button) findViewById(R.id.killSelf);

        killSelfButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareTinkerInternals.killAllOtherProcess(getApplicationContext());
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });

        Button buildInfoButton = (Button) findViewById(R.id.showInfo);

        buildInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInfo(MainActivity.this);
            }
        });
    }

    private boolean getPermission() {

        ActivityCompat.requestPermissions(this, permissions, 321);
        return true;
    }

    private boolean showInfo(Context context) {
        {

            // add more Build Info
            final StringBuilder sb = new StringBuilder();
            Tinker tinker = Tinker.with(getApplicationContext());
            if (tinker.isTinkerLoaded()) {
                sb.append(String.format("[patch is loaded] \n"));
                sb.append(String.format("[buildConfig TINKER_ID] %s \n", BuildInfo.TINKER_ID));
                sb.append(String.format("[buildConfig BASE_TINKER_ID] %s \n", BaseBuildInfo.BASE_TINKER_ID));

                sb.append(String.format("[buildConfig MESSSAGE] %s \n", BuildInfo.MESSAGE));
                sb.append(String.format("[TINKER_ID] %s \n", tinker.getTinkerLoadResultIfPresent().getPackageConfigByName(ShareConstants.TINKER_ID)));
                sb.append(String.format("[packageConfig patchMessage] %s \n", tinker.getTinkerLoadResultIfPresent().getPackageConfigByName("patchMessage")));
                sb.append(String.format("[TINKER_ID Rom Space] %d k \n", tinker.getTinkerRomSpace()));

            } else {
                sb.append(String.format("[patch is not loaded] \n"));
                sb.append(String.format("[buildConfig TINKER_ID] %s \n", BuildInfo.TINKER_ID));
                sb.append(String.format("[buildConfig BASE_TINKER_ID] %s \n", BaseBuildInfo.BASE_TINKER_ID));

                sb.append(String.format("[buildConfig MESSSAGE] %s \n", BuildInfo.MESSAGE));
                sb.append(String.format("[TINKER_ID] %s \n", ShareTinkerInternals.getManifestTinkerID(getApplicationContext())));
            }
            sb.append(String.format("[BaseBuildInfo Message] %s \n", BaseBuildInfo.TEST_MESSAGE));

            final TextView v = new TextView(context);
            v.setText(sb);
            v.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
            v.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 10);
            v.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            v.setTextColor(0xFF000000);
            v.setTypeface(Typeface.MONOSPACE);
            final int padding = 16;
            v.setPadding(padding, padding, padding, padding);

            final AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setCancelable(true);
            builder.setView(v);
            final AlertDialog alert = builder.create();
            alert.show();
            return true;
        }
    }
}

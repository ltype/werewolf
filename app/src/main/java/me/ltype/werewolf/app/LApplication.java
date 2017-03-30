package me.ltype.werewolf.app;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.facebook.drawee.backends.pipeline.Fresco;

import me.ltype.werewolf.controller.LAccountManager;

public class LApplication extends Application {
    private static Context mApplicationContext;

    public static LApplication getInstance(Context context) {
        return (LApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationContext = this;
        LAccountManager.initialize(this);
        Fresco.initialize(this);
    }

    public static boolean isNetworkAvailable() {
        if (mApplicationContext == null) return false;
        ConnectivityManager connectivityManager = (ConnectivityManager) mApplicationContext
                .getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}

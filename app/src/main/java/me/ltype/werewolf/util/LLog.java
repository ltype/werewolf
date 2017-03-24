package me.ltype.werewolf.util;

import android.util.Log;

import me.ltype.werewolf.BuildConfig;

public class LLog {
    public static void i(String tag, String msg) {
        Log.i(tag, msg);
    }

    public static void d(Class clazz, String msg) {
        d(clazz.getSimpleName(), msg);
    }

    public static void d(String tag, String msg) {
        Log.d(tag, msg);
    }

    public static void v(String tag, String msg) {
        Log.v(tag, msg);
    }

    public static void w(String tag, String msg) {
        Log.w(tag, msg);
    }

    public static void e(String tag, String msg) {
        Log.e(tag, msg);
    }
}

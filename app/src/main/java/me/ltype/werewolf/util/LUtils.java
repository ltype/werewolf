package me.ltype.werewolf.util;

import android.os.Handler;
import android.os.Looper;

public class LUtils {

    public static void handlerPost(Runnable r) {
        new Handler(Looper.getMainLooper()).post(r);
    }
}

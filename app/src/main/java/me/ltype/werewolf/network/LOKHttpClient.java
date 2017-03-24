package me.ltype.werewolf.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by ltype on 2017/3/23.
 */

public class LOKHttpClient {
    private static final OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
//            .addInterceptor(new LInterceptor())
            .cookieJar(LCookieJar.getInstance())
            .build();

    public static OkHttpClient getClient() {
        return mOkHttpClient;
    }
}

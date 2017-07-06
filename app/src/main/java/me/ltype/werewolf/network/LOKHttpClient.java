package me.ltype.werewolf.network;

import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;

public class LOKHttpClient {
    private static final OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
//            .addInterceptor(new LInterceptor())
            .cookieJar(LCookieJar.getInstance())
            .build();

    public static OkHttpClient getClient() {
        return mOkHttpClient;
    }
}

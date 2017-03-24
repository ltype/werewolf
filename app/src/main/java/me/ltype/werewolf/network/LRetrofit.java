package me.ltype.werewolf.network;

import me.ltype.werewolf.constant.Constants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ltype on 2017/3/23.
 */

public class LRetrofit {
    private static final Retrofit mRetrofit = new Retrofit.Builder()
            .baseUrl(Constants.DOMAIN)
            .client(LOKHttpClient.getClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static Retrofit getInstance() {
        return mRetrofit;
    }
}

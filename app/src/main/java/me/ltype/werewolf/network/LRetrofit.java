package me.ltype.werewolf.network;

import me.ltype.werewolf.constant.Constants;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class LRetrofit {
    private static final Retrofit mRetrofit = new Retrofit.Builder()
            .baseUrl(Constants.SITE)
            .client(LOKHttpClient.getClient())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

    public static Retrofit getInstance() {
        return mRetrofit;
    }

    private static final APIService mAPIService = mRetrofit.create(APIService.class);

    public static APIService getAPIService() {
        return mAPIService;
    }
}

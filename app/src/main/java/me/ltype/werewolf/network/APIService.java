package me.ltype.werewolf.network;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {

    @GET("/")
    Observable<String> init();

    @GET("/engine.io/default/")
    Observable<String> sid(@Query("uid") String uid, @Query("transport") String transport);

    @GET("/engine.io/default/")
    Observable<String> confirm(@Query("uid") String uid, @Query("sid") String sid, @Query("transport") String transport);


}

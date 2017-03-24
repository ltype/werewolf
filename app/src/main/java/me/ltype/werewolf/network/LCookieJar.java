package me.ltype.werewolf.network;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * Created by ltype on 2017/3/23.
 */

public class LCookieJar implements CookieJar {
    private static LCookieJar mCookieJar;

    private HashMap<String, List<Cookie>> mCookieStore = new HashMap<>();

    private LCookieJar() {}

    public static LCookieJar getInstance() {
        if (mCookieJar == null) mCookieJar = new LCookieJar();
        return mCookieJar;
    }

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        mCookieStore.put(url.toString(), cookies);
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        List<Cookie> cookies = new ArrayList<>();
        cookies = mCookieStore.containsKey(url.toString()) ? mCookieStore.get(url.toString()) : cookies;
        return cookies;
    }
}

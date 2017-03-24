package me.ltype.werewolf.network;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.ws.WebSocket;
import okhttp3.ws.WebSocketCall;
import okhttp3.ws.WebSocketListener;
import okio.Buffer;

public class WebSocketClient implements WebSocketListener {
    private String mDomain;
    private String mUid;
    private String mSid;

    private static WebSocketClient mClient;

    private WebSocketClient(String domain, String uid, String sid) {
        mDomain = domain;
        mUid = uid;
        mSid = sid;
    }

    public static WebSocketClient getInstance(String domain, String uid, String sid) {
        if (mClient == null) mClient = new WebSocketClient(domain, uid, sid);
        return mClient;
    }

    private void connect() {
        Request request = new Request.Builder()
                .url(getFullUrl())
                .build();
         WebSocketCall.create(LOKHttpClient.getClient(), request).enqueue(this);
    }

    @Override
    public void onOpen(WebSocket webSocket, Response response) {

    }

    @Override
    public void onFailure(IOException e, Response response) {

    }

    @Override
    public void onMessage(ResponseBody message) throws IOException {

    }

    @Override
    public void onPong(Buffer payload) {

    }

    @Override
    public void onClose(int code, String reason) {

    }

    private HttpUrl getFullUrl() {
        HttpUrl url = new HttpUrl.Builder()
                .scheme("ws")
                .host(mDomain)
                .addPathSegment("engine.io")
                .addPathSegment("default")
                .addQueryParameter("uid", mUid)
                .addQueryParameter("sid", mSid)
                .addQueryParameter("transport", "websocket")
                .build();
        return url;
    }
}

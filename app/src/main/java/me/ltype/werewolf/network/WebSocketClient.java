package me.ltype.werewolf.network;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import me.ltype.werewolf.util.LLog;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
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

    private WebSocket mWebSocket;
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

    public void connect() {
        Request request = new Request.Builder()
                .url(getFullUrl())
                .build();
         WebSocketCall.create(LOKHttpClient.getClient(), request).enqueue(this);
    }

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        mWebSocket = webSocket;
        initConnection();
    }

    @Override
    public void onFailure(IOException e, Response response) {

    }

    @Override
    public void onMessage(ResponseBody message) throws IOException {
        String resp = message.string();
        LLog.d(this.getClass(), resp);
        if (resp.equals("3probe")) {
            checkConnection();
            holdConnection();
        } else if (resp.equals("3")) {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(this::holdConnection, 25 * 1000);
        }

    }

    @Override
    public void onPong(Buffer payload) {

    }

    @Override
    public void onClose(int code, String reason) {

    }

    private HttpUrl getFullUrl() {
        HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host(mDomain)
                .addPathSegments("engine.io/default/")
                .addQueryParameter("uid", mUid)
                .addQueryParameter("sid", mSid)
                .addQueryParameter("transport", "websocket")
                .build();
        return url;
    }

    private void initConnection() {
        sendMessage("2probe");
    }

    private void checkConnection() {
        sendMessage("5");
    }

    private void holdConnection() {
        sendMessage("2");
    }

    private void sendMessage(String msg) {
        RequestBody body = RequestBody.create(WebSocket.TEXT, msg);
        try {
            mWebSocket.sendMessage(body);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

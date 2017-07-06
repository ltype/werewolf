package me.ltype.werewolf.network;

import me.ltype.werewolf.constant.Constants;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class LWebSocket extends WebSocketListener {
    private static WebSocket mWebSocket;

    public WebSocket getInstance() {
        if (mWebSocket == null) {
            Request request = new Request.Builder()
                    .url(getFullUrl())
                    .build();
            mWebSocket = LOKHttpClient.getClient().newWebSocket(request, this);
        }
        return mWebSocket;
    }
    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        super.onOpen(webSocket, response);
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        super.onMessage(webSocket, text);
    }

    @Override
    public void onMessage(WebSocket webSocket, ByteString bytes) {
        super.onMessage(webSocket, bytes);
    }

    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        super.onClosing(webSocket, code, reason);
    }

    @Override
    public void onClosed(WebSocket webSocket, int code, String reason) {
        super.onClosed(webSocket, code, reason);
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        super.onFailure(webSocket, t, response);
    }

    private static HttpUrl getFullUrl() {
        HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host(Constants.DOMAIN)
                .addPathSegments("engine.io/default/")
//                .addQueryParameter("uid", mUid)
//                .addQueryParameter("sid", mSid)
                .addQueryParameter("transport", "websocket")
                .build();
        return url;
    }
}

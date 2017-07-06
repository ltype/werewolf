package me.ltype.werewolf.network;

import android.os.Handler;
import android.os.Looper;
import android.util.LongSparseArray;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import me.ltype.werewolf.constant.Constants;
import me.ltype.werewolf.model.MsgRequest;
import me.ltype.werewolf.model.MsgResponse;
import me.ltype.werewolf.util.LLog;
import me.ltype.werewolf.util.Utils;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class WebSocketClient extends WebSocketListener{
    private static String mUid = Utils.generateRandomUID(24);
    private static String mSid;

    private static WebSocket mWebSocket;
    private static WebSocketClient mClient;
    private LongSparseArray<WSCallBack> mWSCallBackArr = new LongSparseArray<WSCallBack>();

    private WebSocketClient() {
    }

    public static WebSocketClient getInstance() {
        if (mClient == null) mClient = new WebSocketClient();
        return mClient;
    }
    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        super.onOpen(webSocket, response);
        initConnection();
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        super.onMessage(webSocket, text);
        LLog.d(this.getClass(), text);
        if (text.equals("3probe")) {
            checkConnection();
            holdConnection();
        } else if (text.equals("3")) {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(this::holdConnection, 25 * 1000);
        } else {
            MsgResponse resp = MsgResponse.load(text);
            if (resp == null) return;
            mWSCallBackArr.get(resp.getId()).onSuccess(text);
            mWSCallBackArr.delete(resp.getId());
        }
    }

    @Override
    public void onMessage(WebSocket webSocket, ByteString bytes) {
        super.onMessage(webSocket, bytes);
    }

    public void sendMessage(String msg) {
        mWebSocket.send(msg);
    }

    public void sendMessage(MsgRequest req, WSCallBack callBack) {
        mWSCallBackArr.put(req.getId(), callBack);
        mWebSocket.send(req.toString());
    }

    private void initWebSocket(HttpUrl url) {
        Request request = new Request.Builder().url(url).build();
        mWebSocket = LOKHttpClient.getClient().newWebSocket(request, this);
    }

    private static HttpUrl getFullUrl(String uid, String sid) {
        HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host(Constants.DOMAIN)
                .addPathSegments("engine.io/default/")
                .addQueryParameter("uid", uid)
                .addQueryParameter("sid", sid)
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

    public static void initWSClient() {
        LRetrofit.getAPIService().init()
                .flatMap(s -> {
                    return LRetrofit.getAPIService().sid(mUid, "polling");
                })
                .flatMap(s -> {
                    Pattern p = Pattern.compile("\\{(.*)\\}");
                    Matcher m = p.matcher(s);
                    if (!m.find()) throw new RuntimeException("wrong result");
                    JSONObject json = new JSONObject(m.group());
                    mSid = json.getString("sid");
                    return LRetrofit.getAPIService().confirm(mUid, mSid, "polling");
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<String>() {
                    @Override
                    public void onNext(String value) {
                        LLog.d(this.getClass().getSimpleName(), value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        getInstance().initWebSocket(getFullUrl(mUid, mSid));
                    }
                });
    }
}

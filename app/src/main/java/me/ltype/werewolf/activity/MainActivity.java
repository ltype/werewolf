package me.ltype.werewolf.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import me.ltype.werewolf.R;
import me.ltype.werewolf.constant.Constants;
import me.ltype.werewolf.network.LCookieJar;
import me.ltype.werewolf.network.LOKHttpClient;
import me.ltype.werewolf.util.LLog;
import me.ltype.werewolf.util.Utils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private String mUid = Utils.generateRandomUid(24);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        doReaConnectSid();
    }

    private void doReaConnectSid() {
        Request request = new Request.Builder().url(Constants.SITE).build();
        LOKHttpClient.getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                doReqSid(mUid);
            }
        });
    }

    private void doReqSid(String uid) {
        HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host(Constants.DOMAIN)
                .addEncodedPathSegments("engine.io/default/")
                .addQueryParameter("uid", uid)
                .addQueryParameter("transport", "polling")
                .build();
        final Request request = new Request.Builder()
                .url(url)
                .build();
        LOKHttpClient.getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String resp = response.body().string();
                Pattern p = Pattern.compile("\\{(.*)\\}");
                Matcher m = p.matcher(resp);
                if (!m.find()) return;
                try {
                    JSONObject json = new JSONObject(m.group());
                    doReqConfirm(mUid, json.getString("sid"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void doReqConfirm(String uid, String sid) {
        HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host(Constants.DOMAIN)
                .addEncodedPathSegments("engine.io/default/")
                .addQueryParameter("uid", uid)
                .addQueryParameter("sid", sid)
                .addQueryParameter("transport", "polling")
                .build();
        Request request = new Request.Builder()
                .url(url)
                .build();
        LOKHttpClient.getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                LLog.d(getClass(), response.body().string());
            }
        });
    }
}

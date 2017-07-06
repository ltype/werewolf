package me.ltype.werewolf.model;

import android.graphics.Paint;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MsgResponse<T> {
    private long id;
    private T p;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public T getP() {
        return p;
    }

    public void setP(T p) {
        this.p = p;
    }

    public static MsgResponse load(String str) {
        Pattern p = Pattern.compile("\\{(.*)\\}");
        Matcher m = p.matcher(str);
        if (!m.find()) throw new RuntimeException("can not parse result");
        Type msgPListType = new TypeToken<List<MsgP>>() {}.getType();
        Gson g = new GsonBuilder()
                .registerTypeAdapter(msgPListType, new MsgRoomAdapter())
                .create();
        return g.fromJson(m.group(), MsgResponse.class);
    }
}

package me.ltype.werewolf.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MsgRequest {
    private static int len = 1;
    private long id = len++;
    private String m;
    private String[] p;

    public long getId() {
        return id;
    }

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }

    public String[] getP() {
        return p;
    }

    public void setP(String[] p) {
        this.p = p;
    }

    public String toString() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("m", m);
            obj.put("id", id);
            JSONArray arr = new JSONArray();
            for (String item : p) arr.put(item);
            obj.put("p", arr);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "41|" + obj.toString();
    }
}

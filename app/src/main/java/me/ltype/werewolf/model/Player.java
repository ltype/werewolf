package me.ltype.werewolf.model;

import java.util.List;

public class Player {
    private String userid;
    private String realid;
    private String name;
    private String ip;
    private String icon;
    private boolean start;
    private String mode;
    private List<Prize> nowprize;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getRealid() {
        return realid;
    }

    public void setRealid(String realid) {
        this.realid = realid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public List<Prize> getNowprize() {
        return nowprize;
    }

    public void setNowprize(List<Prize> nowprize) {
        this.nowprize = nowprize;
    }
}

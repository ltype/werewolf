package me.ltype.werewolf.model;

import java.util.List;

public class Room {
    public static final String STATUS_WAITING = "";
    public static final String STATUS_OLD = "old";
    public static final String STATUS_LOG = "log";
    private String id;
    private String blind;
    private String comment;
    private boolean gm;
    private String jobrule;
    private long made;
    private String mode;
    private String name;
    private int number;
    private Player owner;
    private String password;
    private List<Player> players;
    private String theme;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBlind() {
        return blind;
    }

    public void setBlind(String blind) {
        this.blind = blind;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isGm() {
        return gm;
    }

    public void setGm(boolean gm) {
        this.gm = gm;
    }

    public String getJobrule() {
        return jobrule;
    }

    public void setJobrule(String jobrule) {
        this.jobrule = jobrule;
    }

    public long getMade() {
        return made;
    }

    public void setMade(long made) {
        this.made = made;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
}

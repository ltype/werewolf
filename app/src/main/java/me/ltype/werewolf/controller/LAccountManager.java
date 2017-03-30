package me.ltype.werewolf.controller;

import android.content.Context;

import me.ltype.werewolf.app.LApplication;

public class LAccountManager {
    private Context mContext;
    private static LAccountManager mLAccountManager;


    private LAccountManager(LApplication app) {
        mContext = app;
    }

    public static LAccountManager initialize(LApplication app) {
        if (mLAccountManager == null)
            mLAccountManager = new LAccountManager(app);
        return mLAccountManager;
    }
}

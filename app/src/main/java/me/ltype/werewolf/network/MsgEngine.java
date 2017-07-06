package me.ltype.werewolf.network;

public class MsgEngine {
    private static WebSocketClient mWSClient;
    private static MsgEngine mMsgEngine;

    private MsgEngine() {
        mWSClient = WebSocketClient.getInstance();
    }

    public static MsgEngine getInstance() {
        if (mMsgEngine == null) mMsgEngine = new MsgEngine();
        return mMsgEngine;
    }


    public static void sendMessage(String msg) {
        mWSClient.sendMessage(msg);
    }
}

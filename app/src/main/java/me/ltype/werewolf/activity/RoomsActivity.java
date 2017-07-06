package me.ltype.werewolf.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.ltype.werewolf.R;
import me.ltype.werewolf.adapter.RoomsAdapter;
import me.ltype.werewolf.model.MsgRequest;
import me.ltype.werewolf.model.MsgResponse;
import me.ltype.werewolf.model.Room;
import me.ltype.werewolf.network.WSCallBack;
import me.ltype.werewolf.network.WebSocketClient;
import me.ltype.werewolf.util.LUtils;

public class RoomsActivity extends BaseActivity {
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private List<Room> mList = new ArrayList<>();
    private RoomsAdapter mAdapter = new RoomsAdapter(mList);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms);
        ButterKnife.bind(this);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mRecyclerView.getContext(),
                DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);

        mSwipeRefreshLayout.setColorSchemeColors(getResources().getIntArray(R.array.swipe_refresh_color));
        mSwipeRefreshLayout.setOnRefreshListener(this::doReqRoomList);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        doReqRoomList();
    }

    private void doReqRoomList() {
        mSwipeRefreshLayout.setRefreshing(true);
        MsgRequest req = new MsgRequest();
        req.setM("game.rooms.getRooms");
        String[] p = {null, "0"};
        req.setP(p);
        WebSocketClient.getInstance().sendMessage(req, new WSCallBack() {
            @Override
            public void onSuccess(String text) {
                Pattern p = Pattern.compile("\\{(.*)\\}");
                Matcher m = p.matcher(text);
                if (!m.find()) throw new RuntimeException("can not parse result");
                String json = m.group();
                Type type = new TypeToken<MsgResponse<List<List<Room>>>>(){}.getType();
                MsgResponse<List<List<Room>>> msg = new Gson().fromJson(json, type);
                mList.clear();
                mList.addAll(msg.getP().get(0));
                LUtils.handlerPost(() -> mAdapter.notifyDataSetChanged());
            }

            @Override
            public void onFailure() {

            }
        });
        mSwipeRefreshLayout.setRefreshing(false);
    }
}

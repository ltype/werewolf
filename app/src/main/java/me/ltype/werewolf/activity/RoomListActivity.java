package me.ltype.werewolf.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.ltype.werewolf.R;
import me.ltype.werewolf.adapter.RoomListAdapter;
import me.ltype.werewolf.model.Room;

public class RoomListActivity extends BaseActivity {
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private List<Room> mList = new ArrayList<>();
    private RoomListAdapter mAdapter = new RoomListAdapter(mList);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);
        ButterKnife.bind(this);
        mRecyclerView.setAdapter(mAdapter);

        mSwipeRefreshLayout.setColorSchemeColors(getResources().getIntArray(R.array.swipe_refresh_color));
        mSwipeRefreshLayout.setOnRefreshListener(this::doReqRoomList);
    }

    private void doReqRoomList() {
        mSwipeRefreshLayout.setRefreshing(true);
        mSwipeRefreshLayout.setRefreshing(false);
    }
}

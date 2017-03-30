package me.ltype.werewolf.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import me.ltype.werewolf.R;
import me.ltype.werewolf.model.Room;

public class RoomListAdapter extends LRecyclerViewAdapter<RecyclerView.ViewHolder> {
    private static final String TAG = RoomListAdapter.class.getSimpleName();

    private final List<Room> mList;

    public RoomListAdapter(List<Room> list) {
        mList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_room, parent, false);
        LViewHolder vh = new LViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final View v = holder.itemView;
        final Context c = v.getContext();
        final Room room = mList.get(position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}

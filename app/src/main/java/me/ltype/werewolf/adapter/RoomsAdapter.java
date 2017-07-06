package me.ltype.werewolf.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.ButterKnife;
import me.ltype.werewolf.R;
import me.ltype.werewolf.model.Room;

public class RoomsAdapter extends LRecyclerViewAdapter<RecyclerView.ViewHolder> {
    private static final String TAG = RoomsAdapter.class.getSimpleName();

    private final List<Room> mList;

    public RoomsAdapter(List<Room> list) {
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
        final TextView name = ButterKnife.findById(v, R.id.name);
        final TextView status = ButterKnife.findById(v, R.id.status);
        final TextView owner = ButterKnife.findById(v, R.id.owner);
        final TextView date = ButterKnife.findById(v, R.id.date);
        name.setText(room.getName());
        status.setText(room.getMode().equals("waiting") ?
                c.getString(R.string.waiting) : c.getString(R.string.playing));
        owner.setText(room.getOwner() == null ? "???" : room.getOwner().getName());
        date.setText(DateUtils.formatDateTime(c,room.getMade(), DateUtils.FORMAT_SHOW_TIME));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}

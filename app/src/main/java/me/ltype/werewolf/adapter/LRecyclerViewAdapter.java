package me.ltype.werewolf.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class LRecyclerViewAdapter<VH extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter {
    protected OnItemClickListener mItemClickListener;
    protected OnItemLongClickListener mItemLongClickListener;

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClick(View v, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mItemClickListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        mItemLongClickListener = listener;
    }


    protected class LViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnLongClickListener {
        public LViewHolder(View itemView) {
            super(itemView);
            if (mItemClickListener != null) itemView.setOnClickListener(this);
            if (mItemLongClickListener != null) itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null)
                mItemClickListener.onItemClick(v, getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            return mItemLongClickListener != null
                    && mItemLongClickListener.onItemLongClick(v, getAdapterPosition());
        }
    }
}

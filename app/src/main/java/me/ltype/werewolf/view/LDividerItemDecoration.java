package me.ltype.werewolf.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by ltype on 2016/11/12.
 */

public class LDividerItemDecoration extends RecyclerView.ItemDecoration {
    private Drawable mDivider;

    public static LDividerItemDecoration newInstance(Context c, int resId) {
        return newInstance(ContextCompat.getDrawable(c, resId));
    }

    public static LDividerItemDecoration newInstance(Drawable divider) {
        return new LDividerItemDecoration(divider);
    }

    public LDividerItemDecoration(Drawable divider) {
        mDivider = divider;
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + mDivider.getIntrinsicHeight();

            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }
}

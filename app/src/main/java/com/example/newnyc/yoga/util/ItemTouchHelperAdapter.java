package com.example.newnyc.yoga.util;

/**
 * Created by newnyc on 4/10/17.
 */

public interface ItemTouchHelperAdapter {
    boolean onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);
}

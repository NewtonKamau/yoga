package com.example.newnyc.yoga.adapters;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;

import com.example.newnyc.yoga.model.Studio;
import com.example.newnyc.yoga.util.ItemTouchHelperAdapter;
import com.example.newnyc.yoga.util.OnStartDragListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

/**
 * Created by newnyc on 4/10/17.
 */

public class FirebaseStudioListAdapter extends FirebaseRecyclerAdapter<Studio, FirebaseStudioViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;


    public FirebaseStudioListAdapter(Class<Studio> modelClass, int modelLayout,
                                     Class<FirebaseStudioViewHolder> viewHolderClass,
                                     Query ref, OnStartDragListener onStartDragListener, Context context) {

        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;
    }
//on touch listener for drag events
    @Override
    protected void populateViewHolder(final FirebaseStudioViewHolder viewHolder, Studio model, int position) {
        viewHolder.bindStudio(model);
        viewHolder.mStudioImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mOnStartDragListener.onStartDrag(viewHolder);
                }
                return false;
            }
        });
    }


    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        return false;
    }

    @Override
    public void onItemDismiss(int position) {

    }
}

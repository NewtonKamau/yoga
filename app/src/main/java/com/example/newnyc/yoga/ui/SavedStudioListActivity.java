package com.example.newnyc.yoga.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.newnyc.yoga.R;
import com.example.newnyc.yoga.adapters.FirebaseStudioListAdapter;
import com.example.newnyc.yoga.adapters.FirebaseStudioViewHolder;
import com.example.newnyc.yoga.model.Studio;
import com.example.newnyc.yoga.util.OnStartDragListener;
import com.example.newnyc.yoga.util.SimpleItemTouchHelperCallback;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedStudioListActivity extends AppCompatActivity implements OnStartDragListener {
    private DatabaseReference mStudioReference;
    private FirebaseStudioListAdapter mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;
//    private FirebaseRecyclerAdapter mFirebaseAdapter;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_studio);
        ButterKnife.bind(this);
        setUpFirebaseAdapter();

        mStudioReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_STUDIO);
        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        mStudioReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_STUDIO)
                .child(uid);

        mFirebaseAdapter = new FirebaseStudioListAdapter(Studio.class,
                R.layout.studio_list_item_drag, FirebaseStudioViewHolder.class,
                mStudioReference, this, this);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mFirebaseAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
}


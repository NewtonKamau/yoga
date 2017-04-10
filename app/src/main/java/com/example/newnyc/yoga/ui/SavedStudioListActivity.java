package com.example.newnyc.yoga.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.newnyc.yoga.R;
import com.example.newnyc.yoga.adapters.FirebaseStudioViewHolder;
import com.example.newnyc.yoga.model.Studio;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedStudioListActivity extends Activity {
    private DatabaseReference mStudioReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_studio);
        ButterKnife.bind(this);

        mStudioReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_STUDIO);
        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Studio, FirebaseStudioViewHolder>
                (Studio.class, R.layout.studio_list_item_drag, FirebaseStudioViewHolder.class, mStudioReference) {

            @Override
            protected void populateViewHolder(FirebaseStudioViewHolder viewHolder, Studio model, int position) {
                viewHolder.bindStudio(model);
            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}


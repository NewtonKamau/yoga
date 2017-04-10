package com.example.newnyc.yoga.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newnyc.yoga.R;
import com.example.newnyc.yoga.model.Studio;
import com.example.newnyc.yoga.ui.Constants;
import com.example.newnyc.yoga.ui.StudioDetailActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebaseStudioViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public ImageView mStudioImageView;
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    View mView;
    Context mContext;

    public FirebaseStudioViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindStudio (Studio studio) {
        ImageView studioImageView = (ImageView) mView.findViewById(R.id.studioImageView);
        TextView nameTextView = (TextView) mView.findViewById(R.id.studioNameTextView);
        TextView categoryTextView = (TextView) mView.findViewById(R.id.categoryTextView);
        TextView ratingTextView = (TextView) mView.findViewById(R.id.ratingTextView);
        mStudioImageView = (ImageView) mView.findViewById(R.id.studioImageView);

        Picasso.with(mContext)
                .load(studio.getImageUrl())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(mStudioImageView);

        nameTextView.setText(studio.getName());
        categoryTextView.setText(studio.getCategories().get(0));
        ratingTextView.setText("Rating: " + studio.getRating() + "/5");
    }

    @Override
    public void onClick(View view) {
        final ArrayList<Studio> studio = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_STUDIO);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    studio.add(snapshot.getValue(Studio.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, StudioDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("studio", Parcels.wrap(studio));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
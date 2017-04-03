package com.example.newnyc.yoga.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newnyc.yoga.R;
import com.example.newnyc.yoga.model.Studio;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class StudioListAdapters extends RecyclerView.Adapter<StudioListAdapters.StudioViewHolder> {
    private ArrayList<Studio> mStudio = new ArrayList<>();
    private Context mContext;

    public StudioListAdapters(Context context, ArrayList<Studio> studios) {
        mContext = context;
        mStudio= studios;
    }

    @Override
    public StudioListAdapters.StudioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.studio_list_item, parent, false);
        StudioViewHolder viewHolder = new StudioViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(StudioListAdapters.StudioViewHolder holder, int position) {
        holder.bindStudio(mStudio.get(position));
    }

    @Override
    public int getItemCount() {
        return mStudio.size();
    }

    public class StudioViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.studioImageView) ImageView   mStudioImageView;
        @Bind(R.id.studioNameTextView) TextView mNameTextView;
        @Bind(R.id.categoryTextView) TextView mCategoryTextView;
        @Bind(R.id.ratingTextView)
        TextView mRatingTextView;
        private Context mContext;

        public StudioViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindStudio(Studio studio) {
            Picasso.with(mContext).load(studio.getImageUrl()).into(mStudioImageView);
            mNameTextView.setText(studio.getName());
            mCategoryTextView.setText(studio.getCategories().get(0));
            mRatingTextView.setText("Rating: " + studio.getRating() + "/5");
        }
    }
}
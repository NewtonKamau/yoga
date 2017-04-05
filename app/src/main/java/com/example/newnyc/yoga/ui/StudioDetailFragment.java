package com.example.newnyc.yoga.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newnyc.yoga.R;
import com.example.newnyc.yoga.model.Studio;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class StudioDetailFragment extends Fragment {
    public static final String TAG = StudioDetailFragment.class.getSimpleName();

    @Bind(R.id.studioImageView) ImageView mImageLabel;
    @Bind(R.id.studioNameTextView) TextView mNameLabel;
    @Bind(R.id.mottoTextView) TextView mCategoriesLabel;
    @Bind(R.id.ratingTextView) TextView mRatingLabel;
    @Bind(R.id.websiteTextView) TextView mWebsiteLabel;
    @Bind(R.id.phoneTextView) TextView mPhoneLabel;
    @Bind(R.id.addressTextView)
    TextView mAddressLabel;
    @Bind(R.id.saveStudioButton) TextView mSaveStudioButton;

    private Studio mStudio;
    private static final int MAX_WIDTH = 400;
    private static final int MAX_HEIGHT = 300;

    public static StudioDetailFragment newInstance(Studio studio) {
        StudioDetailFragment studioDetailFragment = new StudioDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("studio", Parcels.wrap(studio));
        studioDetailFragment.setArguments(args);
        return studioDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mStudio = Parcels.unwrap(getArguments().getParcelable("studio"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_studio_detail, container, false);
        ButterKnife.bind(this, view);
        Picasso.with(view.getContext())
                .load(mStudio.getImageUrl())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop().into(mImageLabel);
        Log.d(TAG, mStudio.getImageUrl());

        Picasso.with(view.getContext()).load(mStudio.getImageUrl()).into(mImageLabel);

        mNameLabel.setText(mStudio.getName());
        mCategoriesLabel.setText(android.text.TextUtils.join(", ", mStudio.getCategories()));
        mRatingLabel.setText(Double.toString(mStudio.getRating()) + "/5");
        mPhoneLabel.setText(mStudio.getPhone());
        mAddressLabel.setText(android.text.TextUtils.join(", ", mStudio.getAddress()));

        return view;
    }
}
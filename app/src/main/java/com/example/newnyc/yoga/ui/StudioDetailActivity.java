package com.example.newnyc.yoga.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.newnyc.yoga.R;
import com.example.newnyc.yoga.adapters.StudioPagerAdapter;
import com.example.newnyc.yoga.model.Studio;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class StudioDetailActivity extends AppCompatActivity {

    @Bind(R.id.viewPager) ViewPager mViewpager;
    private StudioPagerAdapter adapterViewPager;
    ArrayList<Studio> mStudio = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studio_detail);
        ButterKnife.bind(this);

        mStudio = Parcels.unwrap(getIntent().getParcelableExtra("studio"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        int str = mStudio.size();

//        Toast.makeText(StudioDetailActivity.this, ">>>>>>> " + str, Toast.LENGTH_SHORT).show();
        adapterViewPager = new StudioPagerAdapter(getSupportFragmentManager(), mStudio);
        mViewpager.setAdapter(adapterViewPager);
        mViewpager.setCurrentItem(startingPosition);
    }
}

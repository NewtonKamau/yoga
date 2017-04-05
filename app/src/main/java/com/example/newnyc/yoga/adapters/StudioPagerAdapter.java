package com.example.newnyc.yoga.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.newnyc.yoga.model.Studio;
import com.example.newnyc.yoga.ui.StudioDetailFragment;

import java.util.ArrayList;

public class StudioPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Studio> mStudio;

    public StudioPagerAdapter(FragmentManager fm, ArrayList<Studio> studio){
        super(fm);
        mStudio = studio;
    }
    @Override
    public Fragment getItem(int position) {
        return StudioDetailFragment.newInstance(mStudio.get(position));
    }

    @Override
    public  int getCount() {
        return mStudio.size();
    }
    @Override
    public  CharSequence getPageTitle (int position) {
        return  mStudio.get(position).getName();
    }
}

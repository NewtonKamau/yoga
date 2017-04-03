package com.example.newnyc.yoga;

import android.content.Context;
import android.widget.ArrayAdapter;

public class StudioArrayAdapter extends ArrayAdapter{
    private Context mContext;
    private String[] mStudio;
    private  String[] mMotto;

    public   StudioArrayAdapter(Context mContext, int resource, String[] mStudio, String[] mMotto) {
        super(mContext, resource);
        this.mContext = mContext;
        this.mStudio = mStudio;
        this.mMotto = mMotto;
    }

    @Override
    public  Object getItem(int position) {
        String studio = mStudio[position];
        String motto = mMotto[position];
        return String.format("%s \nTeaches yoga : %s", studio, motto);
    }

    @Override
    public  int getCount() {
        return  mStudio.length;
    }

}

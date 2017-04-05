package com.example.newnyc.yoga.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newnyc.yoga.R;
import com.example.newnyc.yoga.adapters.StudioListAdapters;
import com.example.newnyc.yoga.model.Studio;
import com.example.newnyc.yoga.services.YelpService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class StudioListActivity extends AppCompatActivity {
    public static final String TAG = StudioListActivity.class.getSimpleName();

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private StudioListAdapters mAdapter;

    @Bind(R.id.locationTextView)
    TextView mLocationTextView;
    @Bind(R.id.listView)
    ListView mListView;

    public ArrayList<Studio> mStudio = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studio);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        Toast.makeText(StudioListActivity.this, "studio", Toast.LENGTH_LONG).show();
        String location = intent.getStringExtra("location");

//        mLocationTextView.setText("Here are all the studios near: " + location);

        getStudio(location);
    }

    private void getStudio(String location) {
        final YelpService yelpService = new YelpService();

        yelpService.findStudio(location, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                    mStudio = yelpService.processResults(response);

                    StudioListActivity.this.runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            mAdapter = new StudioListAdapters(getApplicationContext(), mStudio);
                            mRecyclerView.setAdapter(mAdapter);
                            RecyclerView.LayoutManager layoutManager =
                                    new LinearLayoutManager(StudioListActivity.this);
                            mRecyclerView.setLayoutManager(layoutManager);
                            mRecyclerView.setHasFixedSize(true);
                        }
                    });
                }
        });
    }
}

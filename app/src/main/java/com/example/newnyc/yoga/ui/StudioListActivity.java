package com.example.newnyc.yoga.ui;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    private SharedPreferences mSharedPreferences;
    private String mRecentAddress;
    private StudioListAdapters mAdapter;
    private SharedPreferences.Editor mEditor;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    @Bind(R.id.locationTextView) TextView mLocationTextView;
    @Bind(R.id.listView)  ListView mListView;
    public ArrayList<Studio> mStudio = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studio);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        Toast.makeText(StudioListActivity.this, "studios", Toast.LENGTH_SHORT).show();
        String location = intent.getStringExtra("location");
        getStudio(location);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mRecentAddress = mSharedPreferences.getString(Constants.PREFERENCES_LOCATION_KEY, null);
        if(mRecentAddress != null) {
            getStudio(mRecentAddress);
        }
    }

//    this method is for the search option on the mmenu
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_search, menu);
            ButterKnife.bind(this);

            mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            mEditor = mSharedPreferences.edit();

            MenuItem menuItem = menu.findItem(R.id.action_search);
            SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            takes the submitted text on the search and adds it to the preference
                @Override
                public boolean onQueryTextSubmit(String query) {
                    addToSharedPreferences(query);
                    getStudio(query);
                    return false;
                }
//            looks for change on the search text
                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }

            });

            return true;
        }
    //        this helps access from menu_main activity more functionality
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
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

    //        this method is responsible for writing data to our shared preference
    private void addToSharedPreferences(String location) {
        mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, location).apply();
    }






}

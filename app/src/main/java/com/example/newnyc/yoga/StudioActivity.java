package com.example.newnyc.yoga;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import javax.security.auth.callback.Callback;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;


public class StudioActivity extends AppCompatActivity {
    @Bind(R.id.locationTextView) TextView mLocationTextView;
    @Bind(R.id.listView) ListView mListView;
    public static final String TAG = StudioActivity.class.getSimpleName();
    private String[] studio = new String[] {"Mi Mero Mole", "Mother's Bistro",
            "Life of Pie", "Screen Door", "Luc Lac", "Sweet Basil",
            "Slappy Cakes", "Equinox", "Miss Delta's", "Andina",
            "Lardo", "Portland City Grill", "Fat Head's Brewery",
            "Chipotle", "Subway"};
    private String[] motto = new String[] {"Vegan Food", "Breakfast", "Fishs Dishs",
            "Scandinavian", "Coffee", "English Food", "Burgers", "Fast Food", "Noodle Soups",
            "Mexican", "BBQ", "Cuban", "Bar Food", "Sports Bar", "Breakfast", "Mexican" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studio);
        ButterKnife.bind(this);
        StudioArrayAdapter adapter = new StudioArrayAdapter(this,android.R.layout.simple_list_item_1, studio, motto);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String studio = ((TextView)view).getText().toString();
                Toast.makeText(StudioActivity.this, studio, Toast.LENGTH_LONG).show();
            }
        });

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        mLocationTextView.setText("Here are  the studios near: " + location);
        getStudio(location);
    }

    private void getStudio(String location) {
        final YelpService yelpService = new YelpService();

        yelpService.findStudio(location, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String jsonData = response.body().string();
                    Log.v(TAG, jsonData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
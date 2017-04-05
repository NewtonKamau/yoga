package com.example.newnyc.yoga.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newnyc.yoga.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
//    private SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;

    private DatabaseReference mSearchedLocationReference;

    @Bind(R.id.findStudioButton) Button mFindStudioButton;
    @Bind(R.id.locationEditText) EditText mLocationEditText;
    @Bind(R.id.appNametextView) TextView mAppNameTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mEditor = mSharedPreferences.edit();
        mSearchedLocationReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_SEARCHED_LOCATION);

        mFindStudioButton.setOnClickListener(this);

        mAppNameTextView = (TextView) findViewById(R.id.appNametextView);
        Typeface Pacifico = Typeface.createFromAsset(getAssets(), "font/Pacifico.ttf");
        mAppNameTextView.setTypeface(Pacifico);
        mFindStudioButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        if (v == mFindStudioButton){
            String location = mLocationEditText.getText().toString();
            saveLocationToFirebase(location);
//            if(!(location).equals("")) {
////                addTosharedPreferences(location);
//            }
        Intent intent = new Intent(MainActivity.this, StudioListActivity.class);
        intent.putExtra("location", location);
        startActivity(intent);
    }
    }

    public  void saveLocationToFirebase(String location) {
        mSearchedLocationReference.push().setValue(location);

    }
//    private  void addTosharedPreferences(String location) {
//        mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, location).apply();
//
//    }
}

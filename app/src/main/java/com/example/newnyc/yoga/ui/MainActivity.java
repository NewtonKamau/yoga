package com.example.newnyc.yoga.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newnyc.yoga.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
//    private SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;

    private DatabaseReference mSearchedLocationReference;
    private ValueEventListener mSearchedLocationReferenceListener;

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
//    put a change listener to our db
        mSearchedLocationReferenceListener =mSearchedLocationReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot locationSnapshot : dataSnapshot.getChildren()){
                    String location = locationSnapshot.getValue().toString();
                    Log.d("Locations updated", "location : " + location);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

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
//    this will stop/destroy eventlistener() when the user is not interacting with db
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSearchedLocationReference.removeEventListener(mSearchedLocationReferenceListener);
    }
//    private  void addTosharedPreferences(String location) {
//        mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, location).apply();
//
//    }
}

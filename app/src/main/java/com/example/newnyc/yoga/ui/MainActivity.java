package com.example.newnyc.yoga.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.newnyc.yoga.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;


    private DatabaseReference mSearchedLocationReference;
    private ValueEventListener mSearchedLocationReferenceListener;
    //    linking the savedStudiobutton
    @Bind(R.id.savedStudioButton) Button msavedStudioButton;
    @Bind(R.id.findStudioButton) Button mFindStudioButton;
    @Bind(R.id.locationEditText) EditText mLocationEditText;
    @Bind(R.id.appNametextView) TextView mAppNameTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

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
//        putting onclick listener to the savestudiobutton
        msavedStudioButton.setOnClickListener(this);

    }
    //    creating the option menu method

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    //    this method will tell our app what to do when the user clicks on the option button

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //    logout method
    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
    @Override
    public void onClick(View v){
        if (v == mFindStudioButton){
            String location = mLocationEditText.getText().toString();
            saveLocationToFirebase(location);
            if(!(location).equals("")) {
              addTosharedPreferences(location);
            }
        Intent intent = new Intent(MainActivity.this, StudioListActivity.class);
        intent.putExtra("location", location);

        startActivity(intent);
    }

//        An intent to view the saved studio when clicked
        if (v == msavedStudioButton) {
            Intent intent = new Intent(MainActivity.this, SavedStudioListActivity.class);
            startActivity(intent);
        }
    }

    public  void saveLocationToFirebase(String location) {



}

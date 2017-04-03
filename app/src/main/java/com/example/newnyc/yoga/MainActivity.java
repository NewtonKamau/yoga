package com.example.newnyc.yoga;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.findStudioButton) Button mFindStudioButton;
    @Bind(R.id.locationEditText) EditText mLocationEditText;
    @Bind(R.id.appNametextView) TextView mAppNameTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mAppNameTextView = (TextView) findViewById(R.id.appNametextView);
        Typeface Pacifico = Typeface.createFromAsset(getAssets(), "font/Pacifico.ttf");
        mAppNameTextView.setTypeface(Pacifico);
        mFindStudioButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        if (v == mFindStudioButton){
            String location = mLocationEditText.getText().toString();
        Toast.makeText(MainActivity.this, "find a yoga studio", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(MainActivity.this, StudioActivity.class);
        intent.putExtra("location", location);
        startActivity(intent);
    }
    }
}

package com.example.newnyc.yoga.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.newnyc.yoga.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends Activity implements View.OnClickListener {
    @Bind(R.id.registerTextView)
    TextView mRegisterTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mRegisterTextView.setOnClickListener(this);
    }
//    we put an onclickListener for our login button
    public  void onClick(View v) {
        Intent intent = new Intent(LoginActivity.this, CreateAccountActivity.class);
        startActivity(intent);
        finish();
    }
}

package com.kiduyu.njugunaproject.agrifarm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.kiduyu.njugunaproject.agrifarm.StatusBar.StatusBar;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBar.changeStatusBarColor(this);
        setContentView(R.layout.activity_register);
    }
}
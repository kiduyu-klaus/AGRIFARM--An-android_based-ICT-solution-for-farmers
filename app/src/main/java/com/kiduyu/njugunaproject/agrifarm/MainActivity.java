package com.kiduyu.njugunaproject.agrifarm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    protected Typeface mTfRegular;
    TextView app_name,subtitle;
    Button getStarted;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        app_name = (TextView) findViewById(R.id.app_name);
        subtitle = (TextView) findViewById(R.id.app_name_subtitle);
        getStarted =  findViewById(R.id.get_started);


        app_name.startAnimation(AnimationUtils.loadAnimation(this,R.anim.translate));
        subtitle.startAnimation(AnimationUtils.loadAnimation(this,R.anim.right_in));
        getStarted.startAnimation(AnimationUtils.loadAnimation(this,R.anim.button_in));
        getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
            }
        });
    }
}

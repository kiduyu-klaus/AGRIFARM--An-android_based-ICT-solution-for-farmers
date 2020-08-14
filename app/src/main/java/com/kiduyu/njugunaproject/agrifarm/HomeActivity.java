package com.kiduyu.njugunaproject.agrifarm;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kiduyu.njugunaproject.agrifarm.StatusBar.StatusBar;
import com.kiduyu.njugunaproject.agrifarm.UserFargments.HomeFragment;
import com.kiduyu.njugunaproject.agrifarm.UserFargments.NewsFragment;
import com.kiduyu.njugunaproject.agrifarm.UserFargments.ProfileFragment;
import com.kiduyu.njugunaproject.agrifarm.UserFargments.ReportsFragment;
import com.kiduyu.njugunaproject.agrifarm.UserFargments.SpecialistsFragment;
import com.kiduyu.njugunaproject.agrifarm.UserFargments.WeatherFragment;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    TextView txtActiontitle;
    CircleImageView circleImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBar.changeStatusBarColor(this);
        setContentView(R.layout.activity_home);

        txtActiontitle = findViewById(R.id.txt_actiontitle);
        drawerLayout = findViewById(R.id.drawer_layout);
        circleImageView = findViewById(R.id.profile_image_message_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

    }

    public void callFragment(Fragment fragmentClass) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_frame, fragmentClass).addToBackStack("adds").commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }


    public void ClickNavigation(View view) {

        Fragment fragment;
        switch (view.getId()) {
            case R.id.img_close:
                onBackPressedd();
                break;
            case R.id.lvl_home:
                txtActiontitle.setText("Home");
                fragment = new HomeFragment();
                callFragment(fragment);
                break;
            case R.id.myprofile:
                txtActiontitle.setText("Profile");
                fragment = new ProfileFragment();
                callFragment(fragment);
                break;
            case R.id.weather:
                txtActiontitle.setText("Weather Updates");
                fragment = new WeatherFragment();
                callFragment(fragment);
                break;
            case R.id.news:
                txtActiontitle.setText("News Updates");
                fragment = new NewsFragment();
                callFragment(fragment);

                break;
            case R.id.specialists:
                txtActiontitle.setText("Available Specialists");
                fragment = new SpecialistsFragment();
                callFragment(fragment);
                break;
            case R.id.reports:
                txtActiontitle.setText("Generate Reports");
                fragment = new ReportsFragment();
                callFragment(fragment);

                break;
            case R.id.logout:

                break;

            case R.id.img_noti:
                startActivity(new Intent(HomeActivity.this, NotificationActivity.class));
                break;
            case R.id.share:
                shareApp();
                break;

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    private void onBackPressedd() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    private void shareApp() {
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.app_name));
            String shareMessage = "\nLet me recommend you this application\n\n";
            shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            startActivity(Intent.createChooser(shareIntent, "choose one"));
        } catch (Exception e) {
            //e.toString();
        }
    }


}
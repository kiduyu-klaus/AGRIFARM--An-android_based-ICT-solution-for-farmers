package com.kiduyu.njugunaproject.agrifarm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity {
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);

        TextView user = headerView.findViewById(R.id.nav_header_name);
        TextView phone = headerView.findViewById(R.id.nav_header_phone);
        CircleImageView profile_img = headerView.findViewById(R.id.header_image);

        findViewById(R.id.floatingActionButton).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        drawer.openDrawer(GravityCompat.START);
                    }
                });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem Item) {
                switch (Item.getItemId()) {
                    case R.id.user_nav_weather:
                      getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new WeatherFragment()).commit();



                        break;
                    case R.id.user_nav_news:
                       /* getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new Vet_HomeFragment()).commit();

                        */

                        break;

                    case R.id.user_nav_profile:
                    /* getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new Vet_HomeFragment()).commit();

                        */

                        break;

                    case R.id.user_nav_home:
                      /* getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new Vet_HomeFragment()).commit();

                        */

                        break;

                    case R.id.user_nav_reports:
                      /* getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new Vet_HomeFragment()).commit();

                        */

                        break;

                    case R.id.user_nav_specialist:
                        /* getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new Vet_HomeFragment()).commit();

                        */

                        break;

                    case R.id.customer_nav_send:

                        Toast.makeText(HomeActivity.this, "Send this app", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.customer_nav_share:

                        Toast.makeText(HomeActivity.this, "Share this app", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.customer_nav_signout:

                        Toast.makeText(HomeActivity.this, "Sign out of the app", Toast.LENGTH_SHORT).show();
                        break;

                }

                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });


    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}

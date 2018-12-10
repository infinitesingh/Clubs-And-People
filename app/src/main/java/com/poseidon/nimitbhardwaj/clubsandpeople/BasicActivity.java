package com.poseidon.nimitbhardwaj.clubsandpeople;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

public class BasicActivity extends AppCompatActivity {

    private String token;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Home");
        }

        SharedPreferences pref = getSharedPreferences("Login", MODE_PRIVATE);
        token = pref.getString("token", "");

        dl = findViewById(R.id.drawer_basic);
        t = new ActionBarDrawerToggle(this, dl,R.string.Open, R.string.Close);

        dl.addDrawerListener(t);
        t.syncState();

        if (getSupportActionBar() != null)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = findViewById(R.id.nv_basic);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.profile_basic:
                        getProfile();
                        break;
                    case R.id.logout_basic:
                        logoutProfile();
                    default:
                        return true;
                }
                return false;
            }
        });

    }

    void getProfile() {
        Intent i = getIntent();
        startActivity(new Intent(this,
                ProfileActivity.class).putExtra("token", i.getStringExtra("token")));
    }

    void logoutProfile() {
        SharedPreferences pref = getSharedPreferences("Login", MODE_PRIVATE);
        SharedPreferences.Editor ed = pref.edit();
        ed.putBoolean("isLoggedIn", false);
        ed.putString("token", "");
        ed.apply();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
}

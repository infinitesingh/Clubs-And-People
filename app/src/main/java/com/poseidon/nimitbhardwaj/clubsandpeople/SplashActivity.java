package com.poseidon.nimitbhardwaj.clubsandpeople;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class SplashActivity extends AppCompatActivity {
    String tok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*
         * The Splash Screen Activity, do the work of loading the
         * necessary data, such as the previous signed in data state
         */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // The Main Work for loading the data
        Handler handSplash = new Handler();
        long SPLASH_TIME_OUT = 3000;
        handSplash.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!captureDefaults()) {
                    startActivity(new Intent(SplashActivity.this,
                            LoginActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(SplashActivity.this,
                            BasicActivity.class).putExtra("token", tok));
                    finish();
                }
            }
        }, SPLASH_TIME_OUT);
    }
    private Boolean captureDefaults() {
        // Main Work of loading
        SharedPreferences pref = getSharedPreferences("Login", MODE_PRIVATE);
        if (!pref.getBoolean("isLoggedIn", false)) {
            tok = "";
            return false;
        } else {
            tok = pref.getString("token", "");
            return true;
        }
    }
}

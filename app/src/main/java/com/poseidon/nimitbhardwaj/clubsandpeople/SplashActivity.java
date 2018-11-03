package com.poseidon.nimitbhardwaj.clubsandpeople;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class SplashActivity extends AppCompatActivity {
    private long SPLASH_TIME_OUT = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*
         * The Splash Screen Activity, do the work of loading the
         * necessary data, such as the previous signed in data state
         */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final Intent splshIntent = new Intent(this,
                com.poseidon.nimitbhardwaj.clubsandpeople.LoginActivity.class);

        // The Main Work for loading the data
        Handler handSplash = new Handler();
        handSplash.postDelayed(new Runnable() {
            @Override
            public void run() {
                Bundle bundl = new Bundle();
                captureDefaults(bundl);
                splshIntent.putExtra("loginBundle", bundl);
                startActivity(splshIntent);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
    private void captureDefaults(Bundle bundl) {
        // Main Work of loading
    }
}

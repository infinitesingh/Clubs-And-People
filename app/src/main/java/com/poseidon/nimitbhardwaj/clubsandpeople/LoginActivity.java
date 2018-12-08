package com.poseidon.nimitbhardwaj.clubsandpeople;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {
    private Intent myIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        myIntent = getIntent();
    }

    public void login(View view) {

    }

    public void signUpRedirect(View view) {
        Intent signUp = new Intent(this,
                com.poseidon.nimitbhardwaj.clubsandpeople.SignupActivity.class);
        startActivity(signUp);
    }

    @Override
    public void onStop() {
        super.onStop();
        finish();
    }
}

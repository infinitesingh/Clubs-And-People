package com.poseidon.nimitbhardwaj.clubsandpeople.utilities;

import android.support.v7.app.AppCompatActivity;

import org.json.JSONObject;

public abstract class BaseAsyncAbleActivity extends AppCompatActivity{
    abstract public void afterTask(JSONObject x);
}

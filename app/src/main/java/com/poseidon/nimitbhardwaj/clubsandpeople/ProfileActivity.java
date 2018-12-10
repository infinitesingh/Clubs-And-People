package com.poseidon.nimitbhardwaj.clubsandpeople;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.poseidon.nimitbhardwaj.clubsandpeople.utilities.Profile;

public class ProfileActivity extends AppCompatActivity {
    private Profile pro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Intent i = getIntent();
        pro = new Profile(this, i.getStringExtra("token"));
        Log.d("alpha", pro.toString());
        ((TextView)findViewById(R.id.uname_profile)).setText(pro.getFullName());

        addElementsToScrollView();
    }

    private void addElementsToScrollView() {
        ViewGroup scrView = findViewById(R.id.scrl_profile);
        addElement(scrView, "Email", pro.getEmail());
        addElement(scrView, "Roll No.", pro.getRollNo());
        addElement(scrView, "Branch", pro.getBranch());
        if (pro.isMe()) {
            addElement(scrView, "Phone No.", pro.getPhoneNo());
        }
    }

    void addElement(ViewGroup grp, String key, String val) {
        View view = LayoutInflater.from(this).inflate(R.layout.profile_element,null);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
        TextView v1 = view.findViewById(R.id.profile_element_name);
        TextView v2 = view.findViewById(R.id.profile_element_value);
        v1.setText(key);
        v2.setText(val);
        grp.addView(view);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this,
                BasicActivity.class).putExtra("token", pro.getToken()));
    }

}

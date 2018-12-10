package com.poseidon.nimitbhardwaj.clubsandpeople;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.poseidon.nimitbhardwaj.clubsandpeople.utilities.AsyncRequests;
import com.poseidon.nimitbhardwaj.clubsandpeople.utilities.BaseAsyncAbleActivity;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class LoginActivity extends BaseAsyncAbleActivity {
    private AppCompatEditText emailId, pass;
    boolean isValid;
    private String url = "http://csec.nith.ac.in:3003/api/v1/auth/login";

    public void afterTask(JSONObject x) {
        try {
            if (Integer.parseInt(x.get("statusCode").toString()) == 200) {
                Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show();

                // Start the home activity here

                SharedPreferences pref = getSharedPreferences("Login", MODE_PRIVATE);
                SharedPreferences.Editor ed = pref.edit();
                ed.putString("token", x.getJSONObject("data").getString("token"));
                ed.putBoolean("isLoggedIn", true);
                ed.putString("firstName",
                        x.getJSONObject("data").getJSONObject("user").getString("firstName"));
                ed.putString("lastName",
                        x.getJSONObject("data").getJSONObject("user").getString("lastName"));
                ed.putString("email",
                        x.getJSONObject("data").getJSONObject("user").getString("email"));
                ed.putString("branch",
                        x.getJSONObject("data").getJSONObject("user").getString("branch"));
                ed.putString("rollNo",
                        x.getJSONObject("data").getJSONObject("user").getString("rollNo"));
                ed.putString("isStudent",
                        x.getJSONObject("data").getJSONObject("user").getString("isStudent"));
                ed.putString("isPres",
                        x.getJSONObject("data").getJSONObject("user").getString("isPresident"));

                ed.apply();


                Intent i = new Intent(this, BasicActivity.class);
                i.putExtra("token",
                        x.getJSONObject("data").getString("token"));
                startActivity(i);
                finish();

            } else {
                Toast.makeText(this, "Incorrect Password or Username",
                        Toast.LENGTH_SHORT).show();
                ((TextInputLayout) findViewById(R.id.uname_login_layout)).setError("Incorrect Email");
                ((TextInputLayout) findViewById(R.id.pass_login_layout)).setError("Incorrect Pass");
            }
        } catch (JSONException e) {
            Toast.makeText(this, "Some Unexpected Error Occur",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailId = findViewById(R.id.uname_login);
        pass = findViewById(R.id.password_login);
    }

    private String normalizeStr(AppCompatEditText etxt, TextInputLayout layout) {
        if (etxt.getText() == null) {
            isValid = false;
            layout.setError("Can't Be empty");
            return "";
        } else {
            return etxt.getText().toString();
        }
    }

    private void clearErrors() {
        // Initial States of all the EditText views
        ((TextInputLayout)findViewById(R.id.uname_login_layout))
                .setError(null);
        ((TextInputLayout)findViewById(R.id.pass_login_layout))
                .setError(null);
    }

    public void login(View view) {
        clearErrors();
        isValid = true;
        String uname = normalizeStr(emailId,
                (TextInputLayout)findViewById(R.id.uname_login_layout));
        String password = normalizeStr(pass, (TextInputLayout)findViewById(R.id.pass_login_layout));
        if (!isValid) {
            return;
        } else {
            JSONObject obj = new JSONObject();
            try {
                obj.put("email", uname);
                obj.put("password", password);
            } catch (JSONException e) {}
            new AsyncRequests(this).execute(url, obj.toString());
        }

    }

    public void signUpRedirect(View view) {
        Intent signUp = new Intent(this,
                SignupActivity.class);
        startActivity(signUp);
        finish();
    }

    @Override
    public void onStop() {
        super.onStop();
        LoginActivity.this.finish();
    }
}

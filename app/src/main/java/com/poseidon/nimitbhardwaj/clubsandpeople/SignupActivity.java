package com.poseidon.nimitbhardwaj.clubsandpeople;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.View;

import com.poseidon.nimitbhardwaj.clubsandpeople.utilities.AsyncRequests;

import org.json.JSONException;
import org.json.JSONObject;

public class SignupActivity extends AppCompatActivity {
    private AppCompatEditText fullName, emailId, rollNo, passWord, repPassWord;
    private boolean isValidForm;
    private static final String myURL = "http://csec.nith.ac.in:3003/api/v1/auth/signup";


        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isValidForm = false;
        setContentView(R.layout.activity_signup);
        fullName = findViewById(R.id.full_name_signup);
        emailId = findViewById(R.id.email_id_signup);
        rollNo = findViewById(R.id.roll_number_signup);
        passWord = findViewById(R.id.password_signup);
        repPassWord = findViewById(R.id.password_repeat_signup);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(SignupActivity.this,
                com.poseidon.nimitbhardwaj.clubsandpeople.LoginActivity.class));
        finish();
    }

    public void signUp(View view) {
        // Initialization
        clearErrors();
        isValidForm = true;

        // Obtain the data
        StringBuffer strBuf = new StringBuffer();

        String fullName_str = normalizeField(fullName,
                (TextInputLayout) findViewById(R.id.full_name_signup_layout));
        strBuf.append("Name: "+fullName_str);

        String emailId_str = normalizeField(emailId,
                (TextInputLayout) findViewById(R.id.email_id_signup_layout));
        strBuf.append(" emailID: "+emailId_str);

        String rollNo_str = normalizeField(rollNo,
                (TextInputLayout) findViewById(R.id.roll_number_signup_layout));
        strBuf.append(" rollNo: "+rollNo_str);

        String password_str = normalizeField(passWord,
                (TextInputLayout) findViewById(R.id.password_signup_layout));
        strBuf.append(" pass: "+password_str);

        String repPassword_str = normalizeField(repPassWord,
                (TextInputLayout) findViewById(R.id.password_repeat_signup_layout));
        strBuf.append(" repPass: "+repPassword_str);


        String[] nameArr = fullName_str.split(" ");
        String firName = "", midName = "", lastName = "";
        switch(nameArr.length) {
            case 0:
                break;
            case 1:
                firName = nameArr[0];
                break;
            case 2:
                firName = nameArr[0];
                lastName = nameArr[1];
                break;
            case 3:
                firName = nameArr[0];
                midName = nameArr[1];
                lastName = nameArr[2];
                break;
            default:
                firName = nameArr[0];
                StringBuilder tmp = new StringBuilder();
                for (int i = 1; i < nameArr.length-1; i++) {
                    tmp.append(nameArr[i]);
                    if (i != nameArr.length-2) tmp.append(' ');
                }
                midName = tmp.toString();
                lastName = nameArr[nameArr.length-1];
                break;
        }
        strBuf.append(" First Name: "+firName);
        strBuf.append(" Mid Name: "+midName);
        strBuf.append(" Last Name: "+lastName);
        strBuf.append(" IsValid: "+isValidForm);
        Log.d("alpha", strBuf.toString());
        // If Data that is here is valid then its all good
        // We will do work, else errors are already reported on
        // EditTexts
        if (isValidForm) {
            if (password_str.equals(repPassword_str)) {
                // Make HTTPS Request
                // Best thing for today
                JSONObject obj = new JSONObject();
                try {
                    obj.put("firstName", firName);
                    obj.put("midName", midName);
                    obj.put("lastName", lastName);
                    obj.put("rollNo", rollNo_str);
                    obj.put("email", emailId_str);
                    obj.put("password", password_str);
                } catch (JSONException e) {}
                new AsyncRequests().execute(myURL, obj.toString());

            } else {
                ((TextInputLayout)findViewById(R.id.password_repeat_signup_layout))
                        .setError("Passwords don't match");
            }
        }
    }

    private void clearErrors() {
        // Initial States of all the EditText views
        ((TextInputLayout)findViewById(R.id.full_name_signup_layout))
                .setError(null);
        ((TextInputLayout)findViewById(R.id.email_id_signup_layout))
                .setError(null);
        ((TextInputLayout)findViewById(R.id.roll_number_signup_layout))
                .setError(null);
        ((TextInputLayout)findViewById(R.id.password_signup_layout))
                .setError(null);
        ((TextInputLayout)findViewById(R.id.password_repeat_signup_layout))
                .setError(null);

    }

    private String normalizeField(AppCompatEditText value, TextInputLayout layout) {
        // Works use text field data validly
        String tmp = "";
        if (value.getText().toString().equals("")) {
            isValidForm = false;
            layout.setError("Can't Be empty");
        } else {
            tmp = String.valueOf(value.getText());
        }
        return tmp;
    }
}

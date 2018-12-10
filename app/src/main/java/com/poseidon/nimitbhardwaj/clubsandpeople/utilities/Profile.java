package com.poseidon.nimitbhardwaj.clubsandpeople.utilities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.telephony.TelephonyManager;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

public class Profile {
    private String firstName, lastName, branch, rollNo, isStud, isPres, token, phoneNo, email;
    private boolean me;
    @SuppressLint("MissingPermission")
    public Profile(Activity act, String tok) {
        SharedPreferences pref = act.getSharedPreferences("Login", Context.MODE_PRIVATE);
        if (tok.equals(pref.getString("token", ""))) {
            token = tok;
            firstName = pref.getString("firstName", "");
            email = pref.getString("email", "");
            lastName = pref.getString("lastName", "");
            branch = pref.getString("branch", "");
            rollNo = pref.getString("rollNo", "");
            isStud = pref.getString("isStudent", "");
            isPres = pref.getString("isPres", "");
            TelephonyManager tMgr = (TelephonyManager)act.getSystemService(Context.TELEPHONY_SERVICE);
            phoneNo = tMgr.getLine1Number();
            me = true;
        } else {
            try {
                JSONObject obj = getDataFromToken(tok);
                firstName = obj.getString("firstName");
                lastName = obj.getString("lastName");
                branch = obj.getString("branch");
                rollNo = obj.getString("rollNo");
                isStud = obj.getString("isStudent");
                isPres = obj.getString("isPresident");
                email = obj.getString("email");
                phoneNo = "-1";
                me = false;
            } catch (JSONException ignored) {}
        }

    }

    private JSONObject getDataFromToken(String tok) throws JSONException {
        JSONObject obj = new JSONObject();
        obj.put("firstName", "");
        obj.put("lastName", "");
        obj.put("branch", "");
        obj.put("rollNo", "");
        obj.put("isStudent", "");
        obj.put("isPresident", "");
        obj.put("email", "");

        return obj;
    }

    public String getFullName() {
        return firstName + ' ' + lastName;
    }

    public String getBranch() {
        return branch;
    }

    public String getRollNo() {
        return rollNo;
    }

    public String isPresident() {
        return isPres;
    }

    public String isStudent() {
        return isStud;
    }
    public String getPhoneNo() {
        return phoneNo;
    }

    public String getToken() {
        return token;
    }

    public boolean isMe() {
        return me;
    }

    public String getEmail() {
        return email;
    }
    @Override
    @NotNull
    public String toString() {
        return "{"+"Name: " + getFullName()+", Branch: "+getBranch()+", RollNo: "
                +getRollNo()+", isPres: "+isPresident()+", isStudent: "+
                isStudent()+", Phone No: "+getPhoneNo()+"}";
    }
}

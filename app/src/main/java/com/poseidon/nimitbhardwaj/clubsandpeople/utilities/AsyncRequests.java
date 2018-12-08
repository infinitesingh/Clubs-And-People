package com.poseidon.nimitbhardwaj.clubsandpeople.utilities;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class AsyncRequests extends AsyncTask<String, Void, Integer> {
    private static final MediaType JSONTYPE
            = MediaType.parse("application/json; charset=utf-8");

    @Override
    public void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    public Integer doInBackground(String ...arr) {
        String URL = arr[0];
        String data = arr[1];
        OkHttpClient cli = new OkHttpClient();
        RequestBody bdy = RequestBody.create(JSONTYPE, data);
        Request req = new Request.Builder()
                .url(URL)
                .post(bdy)
                .build();
        try {
            String resp = cli.newCall(req).execute().body().string();
            JSONObject obj = new JSONObject(resp);
            return (int) obj.get("statusCode");
        } catch(IOException e) {
            return -1;
        } catch (JSONException e) {
            return -2;
        }
    }

    @Override
    public void onPostExecute(Integer a) {
        super.onPostExecute(a);
        Log.d("alpha", "fuck"+a);
    }
}

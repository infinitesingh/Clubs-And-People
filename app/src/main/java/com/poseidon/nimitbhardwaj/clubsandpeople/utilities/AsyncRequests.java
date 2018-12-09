package com.poseidon.nimitbhardwaj.clubsandpeople.utilities;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class AsyncRequests extends AsyncTask<String, Void, JSONObject> {
    private static final MediaType JSONTYPE
            = MediaType.parse("application/json; charset=utf-8");

    private BaseAsyncAbleActivity act;

    public AsyncRequests(BaseAsyncAbleActivity acti) {
        this.act = acti;
    }


    @Override
    public void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    public JSONObject doInBackground(String ...arr) {

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
            return obj;
        } catch(IOException e) {
            return new JSONObject();
        } catch (JSONException e) {
            return new JSONObject();
        }
    }

    @Override
    public void onPostExecute(JSONObject a) {
        super.onPostExecute(a);
        this.act.afterTask(a);
    }
}

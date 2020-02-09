package com.example.android.sierendeelementen;

import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtils extends AsyncTask<String, Void, String> {
    private String result;

    public NetworkUtils(String result) {
        this.result = result;

    }

    @Override
    protected String doInBackground(String... strings) {
        String result = "";

        try {
            URL url = new URL(strings[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            InputStream stream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder builder = new StringBuilder();

            String inputString;
            while ((inputString = bufferedReader.readLine()) != null) {
                builder.append(inputString);
            }

            result = builder.toString();
            JSONObject topLevel = new JSONObject(builder.toString());
            //JSONObject main = topLevel.getJSONObject("products");
            // result = String.valueOf(main.getDouble("title"));

            urlConnection.disconnect();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(String json) {
        result = json;
    }

}

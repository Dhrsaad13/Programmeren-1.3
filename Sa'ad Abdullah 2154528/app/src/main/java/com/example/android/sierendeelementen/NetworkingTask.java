package com.example.android.sierendeelementen;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class NetworkingTask extends AsyncTask<String, Void, ArrayList<ArtItem>> {
    private static String TAG = NetworkingTask.class.getSimpleName();
    private OnItemApiListener listener;

    public NetworkingTask(OnItemApiListener listener) {
        this.listener = listener;
    }

    @Override
    protected ArrayList<ArtItem> doInBackground(String... inputParams) {
        Log.i(TAG, "doInBackground called");

        String url = inputParams[0];
        Log.i(TAG, "inputParams = " + url);

        String response = null;
       // response = doSendRequestToAPI(url);
        ArrayList<ArtItem> artItems = createItemsFromJson(response);

        Log.i(TAG, "response = " + response);
        Log.i(TAG, "doInBackground finished");

        return artItems;
    }

    private ArrayList<ArtItem> createItemsFromJson(String response) {
        Log.i(TAG, "createItemsFromJson called");
        ArrayList<ArtItem> artItems = new ArrayList<>();

        try {
            JSONObject jsonResponse = new JSONObject(response);
            JSONArray itemList = jsonResponse.getJSONArray("features");

            for(int i = 0; i < itemList.length(); i++) {
                JSONObject item = itemList.getJSONObject(i);

                int objectID = item.getJSONObject("attributes").getInt("OBJECTID");
                String id = item.getJSONObject("attributes").getString("IDENTIFICATIE");
                String title = item.getJSONObject("attributes").getString("AANDUIDINGOBJECT");
                String artist = item.getJSONObject("attributes").getString("KUNSTENAAR");
                String material = item.getJSONObject("attributes").getString("MATERIAAL");
                String description = item.getJSONObject("attributes").getString("OMSCHRIJVING");
                String surface = item.getJSONObject("attributes").getString("ONDERGROND");
                String dateOfPlacement = item.getJSONObject("attributes").getString("PLAATSINGSDATUM");
                String img = item.getJSONObject("attributes").getString("URL");
                String geoLocation = item.getJSONObject("attributes").getString("GEOGRAFISCHELIGGING");

                artItems.add(new ArtItem(objectID, id, title, artist, material, description, surface, dateOfPlacement, img, geoLocation));
            }
        }
        catch (JSONException ex) {
            Log.e(TAG, "error: " + ex.getMessage());
        }
        return artItems;
    }

    @Override
    protected void onPostExecute(ArrayList<ArtItem> response) {
        Log.i(TAG, "onPostExecute called");

        listener.onItemAvailable(response);
    }

    private String doSendRequestToAPI(String urlApiString) {

        InputStream inputStream = null;
        int responseCode = -1;

        // Het resultaat dat we gaan retourneren
        String response = "";

        // Maak verbinding met de urlApiString en haal het response op
        try {
            URL url = new URL(urlApiString);
            URLConnection urlConnection = url.openConnection();

            // Als het niet gelukt is om een URL connection op te zetten moeten we stoppen
            if (!(urlConnection instanceof HttpURLConnection)) {
                Log.e(TAG, "Niet gelukt om een URL connectie te maken!");
                return null;
            }

            // Initiëer de connectie en maak verbinding
            HttpURLConnection httpConnection = (HttpURLConnection) urlConnection;
            httpConnection.setAllowUserInteraction(false);
            httpConnection.setInstanceFollowRedirects(true);
            httpConnection.setRequestMethod("GET");
            httpConnection.connect();

            responseCode = httpConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // De verbinding is gelukt. Lees hier de data.
                inputStream = httpConnection.getInputStream();
                response = getStringFromInputStream(inputStream);
                Log.i(TAG, "response = " + response);
            } else {
                // Verbinding lukte, maar de server geeft een foutcode
                Log.e(TAG, "Fout in responsCode: code = " + responseCode);
            }
        } catch (MalformedURLException e) {
            // De URL was niet correct geformuleerd.
            Log.e(TAG, "MalformedURLEx " + e.getMessage());
            return null;
        } catch (IOException e) {
            // Het lukte niet om verbinding te maken.
            Log.e(TAG, "IOException " + e.getMessage());
            return null;
        }
        // Hier hebben we een resultaat
        return response;
    }

    private static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    // Het lukte niet om verbinding te maken.
                    Log.e(TAG, "doInBackground IOException " + e.getMessage());
                }
            }
        }
        return sb.toString();
    }

    public interface OnItemApiListener {
        void onItemAvailable(ArrayList<ArtItem> persons);
    }
}

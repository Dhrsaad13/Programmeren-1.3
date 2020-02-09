package com.example.android.sierendeelementen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity
        extends AppCompatActivity
        implements View.OnClickListener, NetworkingTask.OnItemApiListener  {

    private final String TAG = this.getClass().getSimpleName();
    private final String apiUrl = "https://services7.arcgis.com/21GdwfcLrnTpiju8/arcgis/rest/services/Sierende_elementen/FeatureServer/0/query?where=1%3D1&outFields=GEOGRAFISCHELIGGING,AANDUIDINGOBJECT,URL,IDENTIFICATIE,OBJECTID&outSR=4326&f=json";

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<ArtItem> allArtItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //obtain a handle to the object
        mRecyclerView = findViewById(R.id.recyclerView);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        //connect it to a layout manager
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        //List<ArtItem> drinkList = Arrays.asList(Drink.drinks);
    }

    @Override
    public void onClick(View view) {
        Log.i(TAG, "onClick is called.");

        String url = "https://randomuser.me/api?results=5";

        //NetworkingTask networkingTask = new NetworkingTask(this);
        //networkingTask.execute(url);

    }

    @Override
    public void onItemAvailable(ArrayList<ArtItem> artItems) {
        Log.i(TAG, "items = " + artItems.toString());

        /*ArtItem i = artItems.get(0);

        String itemAsText = new StringBuilder(i.getTitle())
                .append(" ")
                .append(i.getObjectID())
                .append(" ")
                .append(i.getArtist())
                .toString();

        this.outputText.setText(itemAsText);*/


        mAdapter = new ArtItemAdapter(artItems);
        mRecyclerView.setAdapter(mAdapter);
    }
}

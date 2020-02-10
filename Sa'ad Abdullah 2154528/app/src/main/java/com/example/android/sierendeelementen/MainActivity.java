package com.example.android.sierendeelementen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity
        extends AppCompatActivity
        implements View.OnClickListener, NetworkingTask.OnItemApiListener, ArtItemAdapter.ArtItemClickListener {

    private final String TAG = this.getClass().getSimpleName();
    private final String apiUrl = "https://services7.arcgis.com/21GdwfcLrnTpiju8/arcgis/rest/services/Sierende_elementen/FeatureServer/0/query?where=1%3D1&outFields=*&outSR=4326&f=json";

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<ArtItem> allArtItems = new ArrayList<>();
    ArtItemAdapter adapter = new ArtItemAdapter(allArtItems, this);


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

        mAdapter = new ArtItemAdapter(allArtItems, this);
        mRecyclerView.setAdapter(mAdapter);

        NetworkingTask networkingTask = new NetworkingTask(this);
        networkingTask.execute(apiUrl);
    }

    @Override
    public void onClick(View view) {
        Log.i(TAG, "onClick is called.");


       // String url = "https://randomuser.me/api?results=5";

       // NetworkingTask networkingTask = new NetworkingTask(this);
       // networkingTask.execute(url);

    }

    @Override
    public void onItemAvailable(ArrayList<ArtItem> artItems) {
        Log.i(TAG, "onItemAvailable: num = " + artItems.size());

        allArtItems.clear();
        allArtItems.addAll(artItems);

        ArtItemAdapter adapter = new ArtItemAdapter(allArtItems, this);

        int itemcount = adapter.getItemCount();

//      Toast to let the user know how many items have been loaded

        Toast toast = Toast.makeText(getApplicationContext(), itemcount + " items loaded", Toast.LENGTH_SHORT);
        toast.show();

        mRecyclerView.setAdapter(adapter);
    }
    public  void switchActivity(Intent intent){

}

    @Override
    public void onArtItemClick(int position) {
        Log.d(TAG, "onListItemClick was called - got index " + position);
        Intent intent = new Intent(MainActivity.this, ContentActivity.class);
        intent.putExtra("ARTITEM", allArtItems.get(position));
        startActivity(intent);
    }
}


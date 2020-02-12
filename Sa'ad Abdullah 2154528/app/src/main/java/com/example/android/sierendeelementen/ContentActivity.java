package com.example.android.sierendeelementen;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class ContentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_view);

        // Getting the intent from the previous activity.
        Bundle extras = getIntent().getExtras();

        //Extracting ArtItem object out of the extra's
        ArtItem item = (ArtItem) extras.getSerializable("ARTITEM");

        // Using getters to get the variables for the Activity
        String title = item.getTitle();
        String artist = item.getArtist();
        String material = item.getMaterial();
        String description = item.getDescription();
        String surface = item.getSurface();
        String dateOfPlacement = item.getDateOfPlacement();
        String imgUrl = item.getImgUrl();
        String geoLocation = item.getGeoLocation();

        // Initialising the activity views.
        TextView view_title = findViewById(R.id.titel_id);
        TextView view_artist = findViewById(R.id.kunstenaar_id);
        TextView view_material = findViewById(R.id.matriaal_id);
        TextView view_description = findViewById(R.id.beschrijving_id);
        TextView view_surface = findViewById(R.id.ondergrond_id);
        TextView view_dateOfPlacement = findViewById(R.id.plaatsingsdatum_id);
        TextView view_Geolocatie = findViewById(R.id.geo_id);

        // Setting the text out of the art item for the views.
        view_artist.setText(artist);
        view_dateOfPlacement.setText(dateOfPlacement);
        view_title.setText(title);
        view_material.setText(material);
        view_description.setText(description);
        view_surface.setText(surface);
        view_Geolocatie.setText(geoLocation);

        // Using picasso to load the image onto the view.
        ImageView img = findViewById(R.id.item_img);

        Picasso.get().load(imgUrl).into(img);
    }
}

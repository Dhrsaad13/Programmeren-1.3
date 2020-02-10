package com.example.android.sierendeelementen;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class ContentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_view);
       Intent intent = getIntent();
       Bundle extras = getIntent().getExtras();

         ArtItem item = (ArtItem) extras.getSerializable("ARTITEM");



         String title = item.getTitle();
         String artist = item.getArtist();
         String material = item.getArtist();
         String description = item.getDescription();
         String surface = item.getSurface();
         String dateOfPlacement = item.getSurface();
         String imgUrl = item.getImgUrl();
         String geoLocation = item.getGeoLocation();

        TextView view_title = findViewById(R.id.titel_id);
        TextView view_artist = findViewById(R.id.kunstenaar_id);
        TextView view_material = findViewById(R.id.matriaal_id);
        TextView view_description = findViewById(R.id.beschrijving_id);
        TextView view_surface = findViewById(R.id.ondergrond_id);
        TextView view_dateOfPlacement = findViewById(R.id.plaatsingsdatum_id);
        TextView view_Geolocatie = findViewById(R.id.geo_id);



        view_artist.setText(artist);
        view_dateOfPlacement.setText(dateOfPlacement);
        view_title.setText(title);
        view_material.setText(material);
        view_description.setText(description);
        view_surface.setText(surface);
        view_Geolocatie.setText(geoLocation);



        ImageView img = findViewById(R.id.item_img);

        Picasso.get().load(imgUrl).into(img);


    }
}

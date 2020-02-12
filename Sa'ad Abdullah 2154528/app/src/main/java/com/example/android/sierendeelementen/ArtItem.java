package com.example.android.sierendeelementen;

import java.io.Serializable;

public class ArtItem implements Serializable {
    private int objectID;
    private String identificationNumber;
    private String title;
    private String artist;
    private String material;
    private String description;
    private String surface;
    private String dateOfPlacement;
    private String imgUrl;
    private String geoLocation;

    public ArtItem(int objectID, String identificationNumber, String title, String artist, String material, String description, String surface, String dateOfPlacement, String imgUrl, String geoLocation){
        this.objectID = objectID;
        this.identificationNumber = identificationNumber;
        this.title = title;
        this.artist = artist;
        this.material = material;
        this.description = description;
        this.surface = surface;
        this.dateOfPlacement = dateOfPlacement;
        this.imgUrl = imgUrl;
        this.geoLocation = geoLocation;
    }

//    Getters voor de attributen van de art object
    public int getObjectID() {
        return objectID;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getMaterial() {
        return material;
    }

    public String getDescription() {
        return description;
    }

    public String getSurface() {
        return surface;
    }

    public String getDateOfPlacement() {
        return dateOfPlacement;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getGeoLocation(){
        return geoLocation;
    }
}

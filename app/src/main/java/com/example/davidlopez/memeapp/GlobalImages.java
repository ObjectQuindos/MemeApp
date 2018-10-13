package com.example.davidlopez.memeapp;

import android.widget.ImageView;

public class GlobalImages {

    private static GlobalImages instance;
    private ImageView globalImage;

    private GlobalImages() {}

    public void setImage(ImageView image) {
        this.globalImage = image;
    }

    public ImageView getImage() {
        return this.globalImage;
    }

    public static synchronized GlobalImages getInstance() {
        if (instance == null) {
            instance = new GlobalImages();
        }
        return instance;
    }
}
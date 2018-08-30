package com.example.davidlopez.memeapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

public class PhotoAccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_access);

        String[] imagesNamesArray = {"mainbackground", "mainbackground", "mainbackground", "mainbackground", "mainbackground", "mainbackground", "mainbackground", "mainbackground", "mainbackground", "mainbackground"};

        GridView gridView = (GridView) findViewById(R.id.gridView);
        ImagesAdapter imagesAdapter = new ImagesAdapter(this, imagesNamesArray);
        gridView.setAdapter(imagesAdapter);
    }
}

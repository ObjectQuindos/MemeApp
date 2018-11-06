package com.example.davidlopez.memeapp;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class PhotoAccessActivity extends AppCompatActivity {
    private static int RESULT_LOAD_IMG = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_access);
        setTitle("MemeApp: Elección de foto");

        String[] imagesNamesArray = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m"};

        GridView gridView = (GridView) findViewById(R.id.gridView);
        ImagesAdapter imagesAdapter = new ImagesAdapter(this, imagesNamesArray);
        gridView.setAdapter(imagesAdapter);
    }

    public void accessGallery(View view) {
        Intent galleyIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleyIntent, RESULT_LOAD_IMG);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            if (resultCode == RESULT_OK && null != data) {
                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };
                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String imgDecodableString = cursor.getString(columnIndex);
                cursor.close();

                Intent intent = new Intent(this, MemeEdition.class);
                intent.putExtra("imageBackground", imgDecodableString);

                TextPositions textPositions = TextPositions.getInstance();
                textPositions.backgroundImageBitmap = true;

                startActivity(intent);
            } else {
                Toast.makeText(this, "You have not picked an image", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Something went wrong...", Toast.LENGTH_LONG).show();
        }
    }
}

package com.example.davidlopez.memeapp;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MemeFinal extends AppCompatActivity {

    String imageBackgroundName;
    TextPositions textPositions = TextPositions.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meme_final);

        setTitle("MemeApp: Compartir Meme");
        setImageBackground();
        setAllTexts();
    }

    private void setImageBackground() {
        ImageView imageBackground = (ImageView) findViewById(R.id.backgroundImageViewSelected);
        Intent intent = getIntent();
        imageBackgroundName = intent.getStringExtra("imageBackground");

        Resources resources = getResources();
        int resId = resources.getIdentifier(imageBackgroundName, "drawable", this.getPackageName());
        imageBackground.setImageResource(resId);
    }

    private void setAllTexts() {
        if (textPositions.hasFirstText) {
            setFirstAndSecondText(textPositions.firstTextPosition, textPositions.firstText, textPositions.sizeFirstText);
        }
        if (textPositions.hasSecondText) {
            setFirstAndSecondText(textPositions.secondTextPosition, textPositions.secondText, textPositions.sizeSecondText);
        }
    }

    private void setFirstAndSecondText(String textPosition, String text, float sizeText) {
        switch (textPosition) {
            case "up":
                TextView firstTextView = findViewById(R.id.upperTextView);
                firstTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, sizeText);
                firstTextView.setTextSize(sizeText);
                firstTextView.setVisibility(View.VISIBLE);
                break;
            case "center":
                TextView secondTextView = findViewById(R.id.mediorTextView);
                secondTextView.setText(text);
                secondTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, sizeText);
                secondTextView.setVisibility(View.VISIBLE);
                break;
            case "bottom":
                TextView thirdTextView = findViewById(R.id.bottomTextView);
                thirdTextView.setText(text);
                thirdTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, sizeText);
                thirdTextView.setVisibility(View.VISIBLE);
                break;
        }
    }
}

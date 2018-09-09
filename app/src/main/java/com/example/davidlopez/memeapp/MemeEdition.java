package com.example.davidlopez.memeapp;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;

public class MemeEdition extends AppCompatActivity {

    String firstTextPosition = "up";
    String secondTextPosition = "bottom";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meme_edition);
        setTitle("MemeApp: Edición");

        ImageView imageBackground = (ImageView) findViewById(R.id.backgroundImageViewSelected);
        Intent intent = getIntent();
        String imageName = intent.getStringExtra("imageBackground");

        Resources resources = getResources();
        int resId = resources.getIdentifier(imageName, "drawable", this.getPackageName());
        imageBackground.setImageResource(resId);
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        RadioButton firstRadioButtonUp = (RadioButton) findViewById(R.id.firstTextUp);
        RadioButton firstRadioButtonCenter = (RadioButton) findViewById(R.id.firstTextCenter);
        RadioButton firstRadioButtonBottom = (RadioButton) findViewById(R.id.firstTextBottom);

        RadioButton secondRadioButtonUp = (RadioButton) findViewById(R.id.secondTextup);
        RadioButton secondRadioButtonCenter = (RadioButton) findViewById(R.id.secondTextCenter);
        RadioButton secondRadioButtonBottom = (RadioButton) findViewById(R.id.secondTextBottom);

        switch (view.getId()) {
            case R.id.firstTextUp:
                if (checked) {
                    this.firstTextPosition = "up";
                    secondRadioButtonUp.setEnabled(false);
                    secondRadioButtonCenter.setEnabled(true);
                    secondRadioButtonBottom.setEnabled(true);
                    break;
            }
                break;
            case R.id.firstTextCenter:
                if (checked) {
                    this.firstTextPosition = "center";
                    secondRadioButtonUp.setEnabled(true);
                    secondRadioButtonCenter.setEnabled(false);
                    secondRadioButtonBottom.setEnabled(true);
                    break;
                }
                break;
            case R.id.firstTextBottom:
                if (checked) {
                    secondRadioButtonUp.setEnabled(true);
                    secondRadioButtonCenter.setEnabled(true);
                    secondRadioButtonBottom.setEnabled(false);
                    this.firstTextPosition = "bottom";
                    break;
                }
                break;
            case R.id.secondTextup:
                if(checked) {
                    firstRadioButtonUp.setEnabled(false);
                    firstRadioButtonCenter.setEnabled(true);
                    firstRadioButtonBottom.setEnabled(true);
                    this.secondTextPosition = "up";
                    break;
                }
                break;
            case R.id.secondTextCenter:
                if (checked) {
                    firstRadioButtonUp.setEnabled(true);
                    firstRadioButtonCenter.setEnabled(false);
                    firstRadioButtonBottom.setEnabled(true);
                    this.secondTextPosition = "center";
                    break;
                }
                break;
            case R.id.secondTextBottom:
                if (checked) {
                    firstRadioButtonUp.setEnabled(true);
                    firstRadioButtonCenter.setEnabled(true);
                    firstRadioButtonBottom.setEnabled(false);
                    this.secondTextPosition = "bottom";
                    break;
                }
                break;
        }
    }
}

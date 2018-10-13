package com.example.davidlopez.memeapp;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

public class MemeEdition extends AppCompatActivity {

    String firstTextPosition = "up";
    String secondTextPosition = "bottom";
    String imageBackgroundName;
    TextPositions textPositions = TextPositions.getInstance();

    EditText firstEditText;
    EditText secondEditText;
    Button increaseFontButton;
    Button decreaseFontButton;
    String editTextSelected = "1";

    float minimunSize = 50;
    float maximunSize = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_meme_edition);
        setTitle("MemeApp: Edición");
        setImageBackground();
        setListenersFontSize();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edition_toolbar, menu);
        return true;
    }

    private void setImageBackground() {
        ImageView imageBackground = findViewById(R.id.backgroundImageViewSelected);
        Intent intent = getIntent();
        imageBackgroundName = intent.getStringExtra("imageBackground");

        Resources resources = getResources();
        int resId = resources.getIdentifier(imageBackgroundName, "drawable", this.getPackageName());
        imageBackground.setImageResource(resId);
    }

    private void setListenersFontSize() {
        this.firstEditText = findViewById(R.id.firstText);
        this.secondEditText = findViewById(R.id.secondText);
        this.increaseFontButton = findViewById(R.id.plusButton);
        this.decreaseFontButton = findViewById(R.id.minusButton);

        firstEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                editTextSelected = "1";
                return false;
            }
        });

        secondEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                editTextSelected = "2";
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.plusButton:
                if(editTextSelected.equals("1")) {
                    setSizeFont(firstEditText, false);
                }
                if (editTextSelected.equals("2")) {
                    setSizeFont(secondEditText, false);
                }
                break;
            case R.id.minusButton:
                if(editTextSelected.equals("1")) {
                    setSizeFont(firstEditText, true);
                }
                if (editTextSelected.equals("2")) {
                    setSizeFont(secondEditText, true);
                }
                break;
            default:
                break;
        }
        return true;
    }

    private void setSizeFont(TextView textView, Boolean isMinus) {
        float size = textView.getTextSize();

        if (isMinus) {
            if (size >= minimunSize) {
                size = size - 1;
            }
        } else {
            if (size <= maximunSize) {
                size = size + 1;
            }
        }
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        RadioButton firstRadioButtonUp = findViewById(R.id.firstTextUp);
        RadioButton firstRadioButtonCenter = findViewById(R.id.firstTextCenter);
        RadioButton firstRadioButtonBottom = findViewById(R.id.firstTextBottom);

        RadioButton secondRadioButtonUp = findViewById(R.id.secondTextup);
        RadioButton secondRadioButtonCenter = findViewById(R.id.secondTextCenter);
        RadioButton secondRadioButtonBottom = findViewById(R.id.secondTextBottom);

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

    public void nextButtonAction(View view) {
        EditText firstEditText = findViewById(R.id.firstText);
        EditText secondEditText = findViewById(R.id.secondText);
        String firstText = firstEditText.getText().toString();
        String secondText = secondEditText.getText().toString();

        if (!firstText.equals("")) {
            Intent intent = new Intent(this, MemeFinal.class);
            //intent.putExtra("firstText", firstText);
            //intent.putExtra("firstPosition", this.firstTextPosition);
            intent.putExtra("imageBackground", this.imageBackgroundName);
            textPositions.hasFirstText = true;
            textPositions.firstText = firstText;
            textPositions.firstTextPosition = firstTextPosition;
            textPositions.sizeFirstText = this.firstEditText.getTextSize();

            if (!secondText.equals("")) {
                //intent.putExtra("secondText", secondText);
                //intent.putExtra("secondPosition", this.secondTextPosition);
                textPositions.hasSecondText = true;
                textPositions.secondText = secondText;
                textPositions.secondTextPosition = secondTextPosition;
                textPositions.sizeSecondText = this.secondEditText.getTextSize();
            }
            startActivity(intent);
        } else {
            Log.i("Inserta un primer texto", "Inserta un primer texto");
        }
    }
}

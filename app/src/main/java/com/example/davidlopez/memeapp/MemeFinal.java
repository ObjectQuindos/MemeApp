package com.example.davidlopez.memeapp;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileOutputStream;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.share_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.shareButton:
                Bitmap bitmap = this.getScreenShot(findViewById(R.id.rootView));
                //ImageView imageBackground = (ImageView) findViewById(R.id.bitmapImageView);
                //imageBackground.setImageBitmap(bitmap);
                shareBitmap(bitmap);
                break;
            default:
                break;
        }
        return true;
    }

    private Bitmap getScreenShot(View view) {
        view.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        return bitmap;
    }

    private void shareBitmap(Bitmap bitmap) {
        try {
            File file = new File(this.getExternalCacheDir(), "bitmap.png");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            file.setReadable(true, false);
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
            intent.setType("image/png");
            startActivity(Intent.createChooser(intent, "Share image via"));
            Log.i("sharingggggg", "shareBitmap: HHHHHHHHH");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setImageBackground() {
        ImageView imageBackground = (ImageView) findViewById(R.id.backgroundImageViewSelected);
        Intent intent = getIntent();
        imageBackgroundName = intent.getStringExtra("imageBackground");

        if (textPositions.backgroundImageBitmap == true) {
            imageBackground.setImageBitmap(BitmapFactory.decodeFile(imageBackgroundName));
        } else {
            Resources resources = getResources();
            int resId = resources.getIdentifier(imageBackgroundName, "drawable", this.getPackageName());
            imageBackground.setImageResource(resId);
        }
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
                firstTextView.setText(text);
                firstTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, sizeText);
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

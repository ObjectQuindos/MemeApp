package com.example.davidlopez.memeapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImagesAdapter extends ArrayAdapter<String> {

    private final Context mContext;
    private final String[] images;

    public ImagesAdapter(Context context, String[] images) {
        super(context,0, images);
        this.mContext = context;
        this.images = images;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final String  image = images[position];

        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.linearlayout_images, null);
        }

        final ImageView imageView = (ImageView) convertView.findViewById(R.id.imageViewMeme);

        Resources resources = getContext().getResources();
        int resId = resources.getIdentifier(image, "drawable", getContext().getPackageName());
        imageView.setImageResource(resId);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MemeEdition.class);
                intent.putExtra("imageBackground", images[position]);
                mContext.startActivity(intent);
            }
        });

        return convertView;
    }
}

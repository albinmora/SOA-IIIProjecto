package com.example.albin.sportec;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.albin.sportec.Model.News;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class NewsListAdapter extends ArrayAdapter<News> {

    private List<News> news;

    public NewsListAdapter(Context context, int resource, List<News> objects) {
        super(context, resource, objects);
        news = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).
                    inflate(R.layout.list_item, parent, false);
        }

        News newN = news.get(position);

        TextView nameText = (TextView) convertView.findViewById(R.id.txtViewName);
        nameText.setText(newN.getTitle());


        String date = newN.getDate();
        TextView priceText = (TextView) convertView.findViewById(R.id.txtViewDescription);
        priceText.setText(date);

        ImageView iv = (ImageView) convertView.findViewById(R.id.imgView);
        Bitmap bitmap = getBitmapFromAsset(newN.getImage());
        iv.setImageBitmap(bitmap);

        return convertView;
    }

    private Bitmap getBitmapFromAsset(String pImg) {
        AssetManager assetManager = getContext().getAssets();
        InputStream stream = null;

        try {
            stream = assetManager.open(pImg);
            return BitmapFactory.decodeStream(stream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}

package com.oletob.rssreader.model;

import android.content.Context;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.text.SimpleDateFormat;

import com.koushikdutta.ion.Ion;
import com.oletob.rssreader.R;
import com.oletob.rssreader.ui.FeedActivity;

import java.util.ArrayList;

/**
 * Created by evolquez on 7/15/17.
 */

public class FeedAdapter extends ArrayAdapter<FeedHolder> {

    public FeedAdapter(Context context, ArrayList<FeedHolder> history) {
        super(context, 0, history);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Get the data item for this position
        FeedHolder entry = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.feed, parent, false);
        }
        // Lookup view for data population
        if(entry.imageUrl != ""){

            //Ion.with((ImageView)convertView.findViewById(R.id.imgMain)).load(entry.imageUrl);
        }
        ((ImageView)convertView.findViewById(R.id.imgMain)).setImageResource(R.drawable.imageholder);

        ((TextView) convertView.findViewById(R.id.txtDate)).setText(new SimpleDateFormat("dd/MM/yyyy h:mm a").format(entry.date));
        ((TextView) convertView.findViewById(R.id.txtDesc)).setText(entry.title);

        // Return the completed view to render on screen
        return convertView;
    }
}


package com.example.jeffrey.techretreatapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jeffrey on 2015-08-15.
 */
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder>{
    ArrayList<String> data = new ArrayList();

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView nameLabel;
        public TextView typeLabel;
        public TextView ratingLabel;
        public TextView restuarantDistanceLabel;
        public View cardView;

        public Button viewMapButton;

        public ViewHolder(View v) {
            super(v);
            nameLabel = (TextView) v.findViewById(R.id.nameLabel);
            typeLabel = (TextView) v.findViewById(R.id.typeLabel);
            ratingLabel = (TextView) v.findViewById(R.id.ratingLabel);
            restuarantDistanceLabel = (TextView) v.findViewById(R.id.restuarantDistanceLabel);
            viewMapButton = (Button) v.findViewById(R.id.viewMapButton);
            cardView = v;

        }
    }

    public CardAdapter(String[] data) {
        for(int i = 0; i < data.length; i++){
            this.data.add(data[i]);
        }
    }


    @Override
    public CardAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        // create a new view
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_layout, viewGroup, false);
         //set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final CardAdapter.ViewHolder viewHolder, int i) {
        String[] results = data.get(i).split(",");
        viewHolder.nameLabel.setText(results[0]);
        viewHolder.typeLabel.setText(results[1]);
        viewHolder.ratingLabel.setText(results[2]);
        viewHolder.restuarantDistanceLabel.setText(results[3]);
        final Context context = viewHolder.cardView.getContext();
        final String extraString = results[0] + "," + results[4] + "," + results[5];
        viewHolder.viewMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openMapIntent = new Intent(context, MapsActivity.class);
                openMapIntent.putExtra("map_data", extraString);
                context.startActivity(openMapIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void deleteCard(int pos){
        data.remove(pos);
        notifyDataSetChanged();
    }
}

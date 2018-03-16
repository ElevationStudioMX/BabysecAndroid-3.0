package com.example.desarrollo_elevation.babysec.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.desarrollo_elevation.babysec.MainActivity_add_recordatorio;
import com.example.desarrollo_elevation.viveelite.R;
import com.example.desarrollo_elevation.babysec.model_events;

/**
 * Created by Desarrollo on 05/06/2017.
 */


public class adapter_recordatorio extends RecyclerView.Adapter<adapter_recordatorio.ViewHolder> {
    private model_events[] mDataset;
    private Context mcontext;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public CardView cv;
        public TextView event_title;
        public TextView event_description;
        // each data item is just a string in this case
        public TextView mTextView;
        public ViewHolder(View v) {
            super(v);
            cv = (CardView)itemView.findViewById(R.id.cv);
            cv.setCardBackgroundColor(Color.argb(128,255,255,255));
            event_title = (TextView)itemView.findViewById(R.id.event_title);
            event_description = (TextView)itemView.findViewById(R.id.event_description);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public adapter_recordatorio(model_events[] myDataset, Context context) {
        mcontext = context;
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public adapter_recordatorio.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_events, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
//        holder.mTextView.setText(mDataset[position]);
        holder.event_title.setText(mDataset[position].getTitle());
        holder.event_description.setText(mDataset[position].getDescription());

        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mcontext, MainActivity_add_recordatorio.class);
                Bundle bundle = new Bundle();
                int id = mDataset[position].getId();
                bundle.putInt("idevent",id);
                intent.putExtras(bundle);
                mcontext.startActivity(intent);

            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}


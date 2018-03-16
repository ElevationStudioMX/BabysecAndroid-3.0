package com.example.desarrollo_elevation.babysec;

/**
 * Created by Desarrollo_Elevation on 20/05/17.
 */

import android.view.View;
import android.widget.ProgressBar;

import com.example.desarrollo_elevation.viveelite.R;

public class ProgressViewHolder extends RecyclerViewHolders{
    public ProgressBar progressBar;
    public ProgressViewHolder(View itemView) {
        super(itemView);
        progressBar = (ProgressBar)itemView.findViewById(R.id.progressBar);


    }





}
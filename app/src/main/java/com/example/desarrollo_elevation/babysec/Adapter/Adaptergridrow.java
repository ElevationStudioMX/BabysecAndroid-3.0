package com.example.desarrollo_elevation.babysec.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.desarrollo_elevation.babysec.DB.DBHome;
import com.example.desarrollo_elevation.viveelite.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Desarrollo_Elevation on 16/01/17.
 */

public class Adaptergridrow extends BaseAdapter {
    Context context;
    ArrayList<String> imagen;
    private Cursor cursor;
    private ImageView image;
    LayoutInflater inflater;

    public Adaptergridrow(Context context, ArrayList<String> imagen ) {
        this.context = context;
        this.imagen = imagen;
    }

    public int getCount() {
        if (imagen != null) {
            return imagen.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, View Convertview, ViewGroup parent) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemview = inflater.inflate(R.layout.row_grid, parent, false);
        image = (ImageView) itemview.findViewById(R.id.item_image);
        Picasso.with(context).load(imagen.get(position)).resize(300, 0).into(image, new Callback() {
            @Override
            public void onSuccess() {
            }

            @Override
            public void onError() {
            }
        });
        DBHome home = new DBHome(context);
        SQLiteDatabase database = home.getWritableDatabase();
        String query ="select color_filter from sticker where sticker ='"+imagen.get(position)+"'";
        cursor = database.rawQuery(query, null);

        while (cursor.moveToNext()) {
            int col_filt = cursor.getInt(0);
            Log.v("LINEA 97", "LINEA 97 col_filt "+col_filt);
            if(col_filt == 1) {
                image.setColorFilter(Color.WHITE);
            }
        }
        database.close();
        return itemview;
    }
}
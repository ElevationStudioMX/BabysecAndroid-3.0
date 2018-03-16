package com.example.desarrollo_elevation.babysec.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ViewFlipper;

import com.example.desarrollo_elevation.babysec.MainActivity_Editarphoto;
import com.example.desarrollo_elevation.babysec.Modelselectframe;
import com.example.desarrollo_elevation.viveelite.R;
import com.example.photogesturelibrary.PhotoView;
import com.example.photogesturelibrary.PhotoViewAttacher;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Desarrollo_Elevation on 30/01/17.
 */

public class adapterselectframe extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Modelselectframe> dataSet;
    Context mContext;
    int total_types;
    private static float densityDpi;
    private boolean fabStateVolume = false;
    int pose = -1;
    private ImageView mar;

    public static class ImageTypeViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        ImageView marco;

        public ImageTypeViewHolder(View itemView) {
            super(itemView);
            this.image = itemView.findViewById(R.id.imagemarcosparaseleccionar);
            this.marco = itemView.findViewById(R.id.imagenviewmarco);
        }
    }

    public adapterselectframe(ArrayList<Modelselectframe> data, Context context) {
        this.dataSet = data;
        this.mContext = context;
        total_types = dataSet.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case Modelselectframe.IMAGE_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.marcos, parent, false);
                return new adapterselectframe.ImageTypeViewHolder(view);
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        switch (dataSet.get(position).type) {
            case 0:
                return Modelselectframe.IMAGE_TYPE;
            default:
                return -1;
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int listPosition) {
        final Modelselectframe object = dataSet.get(listPosition);
        if (object != null) {
            switch (object.type) {
                case Modelselectframe.IMAGE_TYPE:
                    Picasso.with(mContext).load(object.img).resize(250, 0).into(((ImageTypeViewHolder) holder).image);
                    ((ImageTypeViewHolder) holder).image.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(final View view) {
                            final MainActivity_Editarphoto editarphoto = new MainActivity_Editarphoto();
                            final ImageView imgenes = editarphoto.retornartext();
                            try {
                                URL newurl = new URL(object.img);
                                if (!newurl.toString().contains("no-marco")) {
                                    Bitmap mIcon_val = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());
                                    imgenes.setImageBitmap(mIcon_val);
                                } else {
                                    Bitmap.Config conf = Bitmap.Config.ARGB_4444;
                                    Bitmap bmp = Bitmap.createBitmap(250, 250, conf);
                                    imgenes.setImageBitmap(bmp);
                                }
                            } catch (IOException ignored) {
                            }
                            editarphoto.retornoposicionmarco(listPosition);
                            final PhotoView photoView = editarphoto.getImageView();
                            final RelativeLayout vista = editarphoto.getCamView();
                            final PhotoViewAttacher attacher = editarphoto.getA();
                            ViewFlipper viewFlipper = editarphoto.getViewFlipper();
                            vista.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
                            int vh = vista.getMeasuredHeight();
                            int vw = vista.getMeasuredWidth();
                            int h = imgenes.getDrawable().getIntrinsicHeight();
                            int w = imgenes.getDrawable().getIntrinsicWidth();
                            DisplayMetrics displayMetrics = new DisplayMetrics();
                            WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
                            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
                            int height = displayMetrics.heightPixels;
                            final int width = displayMetrics.widthPixels;
                            DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
                            densityDpi = metrics.density;
                            final int alturaimagen = photoView.getDrawable().getIntrinsicHeight();
                            final int anchuraimagen = photoView.getDrawable().getIntrinsicWidth();
                            final int heigthview = editarphoto.getHeigthview();

                            if (object.img.equals("http://www.elevation.com.mx/pages/AppElite/admin/assets/images/stickers/no-marco.png")) {
                                int posion = listPosition;
                                imgenes.setVisibility(View.INVISIBLE);
                                viewFlipper.setVisibility(View.INVISIBLE);
                                float proporcionimage = (float) alturaimagen / anchuraimagen;
                                float proporcionview = (float) heigthview / width;
                                float anchoimag = (float) heigthview / proporcionimage;
                                final float alturaimagenes = proporcionimage * width;

                                if (proporcionview < proporcionimage) {
                                    vista.getLayoutParams().height = heigthview;
                                    vista.getLayoutParams().width = (int) anchoimag;
                                    photoView.getLayoutParams().height = heigthview;
                                    int posionsal = listPosition;
                                } else {
                                    photoView.getLayoutParams().height = (int) alturaimagenes;
                                    vista.getLayoutParams().height = (int) alturaimagenes;
                                }
                                int posionsal = listPosition;
                            } else {
                                int posion = listPosition;
                                viewFlipper.setVisibility(View.VISIBLE);
                                imgenes.setVisibility(View.VISIBLE);
                                final float proporcion = (float) h / w;
                                final int alturadispositovo = (int) (proporcion * width);
                                final float proporcion2 = (float) w / h;
                                final float proporcionimagen = (float) anchuraimagen / alturaimagen;
                                vista.getLayoutParams().height = alturadispositovo;
                                ViewTreeObserver b = photoView.getViewTreeObserver();
                                b.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                                    @Override
                                    public void onGlobalLayout() {
                                        photoView.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                                        if (proporcionimagen < proporcion2) {
                                            float segundaanchira = proporcion2 * alturaimagen;
                                            final float scalahorizontal = segundaanchira / anchuraimagen;
                                            PhotoViewAttacher attacher = editarphoto.getA();
                                            attacher.setScale(scalahorizontal);
                                        } else {
                                            float alturareal = proporcion * anchuraimagen;
                                            final float scalvertical = alturareal / alturaimagen;
                                            final PhotoViewAttacher attacher = editarphoto.getA();
                                            attacher.setScale(scalvertical /*true  */);
                                        }
                                    }
                                });
                            }
                        }
                    });
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    private class InsideWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}


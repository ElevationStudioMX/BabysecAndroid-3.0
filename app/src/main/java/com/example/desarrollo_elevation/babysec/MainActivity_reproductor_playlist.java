package com.example.desarrollo_elevation.babysec;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.desarrollo_elevation.viveelite.R;

public class MainActivity_reproductor_playlist extends AppCompatActivity {
    private  static TextView txtTrack;
    private  static TextView txtArtista;
    private  static TextView txtAlbumes;
    private  static TextView txtstarter;
    private  static TextView txtender;
    TextView texto;
    private static ImageView playerstop;
    private static ImageButton playnext;
    private static ImageButton playpreview;
    private static ImageView imagenalbum;
    private  static SeekBar barra;
    private  static int numerotrack;
    private  static  int seleccion_track;
    private static String id;
    private static String toker;
    private static String name;
    private static String tol;
    static final String EXTRA_TOKEN = "EXTRA_TOKEN";
    private static final String KEY_CURRENT_QUERY="CURRENT_QUERY";
    private LinearLayoutManager mLinearLayout = new LinearLayoutManager(this);
    public static Intent CreateIntet(Context context)  {
        return  new Intent(context, MainActivity_reproductor_playlist.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main_reproductor_playlist);
        txtTrack =(TextView)findViewById(R.id.txttrack);
        txtArtista=(TextView)findViewById(R.id.txtArtistas);
        txtAlbumes=(TextView)findViewById(R.id.txtAlbum);
        txtender =(TextView)findViewById(R.id.txtend);
        txtstarter =(TextView)findViewById(R.id.txtstart);
        playerstop =(ImageView) findViewById(R.id.btnplaystop);
        playnext =(ImageButton)findViewById(R.id.btnnext);
        playpreview=(ImageButton)findViewById(R.id.btnpreview);
        barra =(SeekBar)findViewById(R.id.seekBar);

        String font ="fonts/OpenSans-Regular.ttf";
        Typeface typeface = Typeface.createFromAsset(getApplicationContext().getAssets(), font);
        txtTrack.setTypeface(typeface);
        txtArtista.setTypeface(typeface);
        txtAlbumes.setTypeface(typeface);
        txtender.setTypeface(typeface);
        txtstarter.setTypeface(typeface);
        barra.setDrawingCacheBackgroundColor(Color.WHITE);
        imagenalbum = (ImageView)findViewById(R.id.imagentrack);
        playpreview.getBackground().setAlpha(00);
        playnext.getBackground().setAlpha(00);
        Bundle bundle =  getIntent().getExtras();
        toker = bundle.getString("token");
        name = bundle.getString("key");
        id = bundle.getString("id");
        tol = bundle.getString("names");
        String opensans ="fonts/OpenSans-Regular.ttf";
        Typeface t = Typeface.createFromAsset(getApplicationContext().getAssets(), opensans);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarreproductorplaylist);
        texto = (TextView)findViewById(R.id.txttoolbar_title);
        texto.setText(tol);
        texto.setTextSize(22);
        texto.setTypeface(t);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.flecha_retorno);
        RecyclerView resultlist = (RecyclerView)findViewById(R.id.recyclerViewerreproductor);
        resultlist.setHasFixedSize(true);
        resultlist.setNestedScrollingEnabled(false);
        //resultlist.addItemDecoration(new  DividerItemDecoration(this, 1));

        if(savedInstanceState != null) {
            String currentquery = savedInstanceState.getString(KEY_CURRENT_QUERY);
        }

        playerstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        playerstop.setEnabled(false);

        playnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        playnext.setEnabled(false);
        playpreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        playpreview.setEnabled(false);

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("respuesta " , " con el premium");
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public  TextView texttrack(){
        return this.txtTrack;
    }

    public  TextView textArtista(){
        return  this.txtArtista;
    }

    public  TextView textAlbumnes(){

        return this.txtAlbumes;
    }

    public TextView textStart(){return  this.txtstarter;}

    public TextView textEnd(){return  this.txtender;}

    public ImageView imagalbum(){
        return  this.imagenalbum;
    }

    public ImageView imagenplaystop(){
        return  this.playerstop;

    }

    public  ImageButton btnplaynext(){
        return this.playnext;
    }

    public ImageButton btnPlaypreview(){
        return this.playpreview;
    }

    public SeekBar barradeprogreso(){
        return barra;
    }

    public int numtrack(int posision){
        this.numerotrack = posision;
        return this.numerotrack;
    }

    public int seleccionartrack(){
        this.seleccion_track = numerotrack;
        return this.seleccion_track;
    }


    @Override
    public boolean onSupportNavigateUp() {
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        onBackPressed();

                        Log.i("tag", "This'll run 300 milliseconds later");
                    }
                },
                100);


        //mActionListener.pausarback();
       // Log.v("se pauso", ""+2);


        //onBackPressed();
        //mActionListener.pausarback();

        return false;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }
}

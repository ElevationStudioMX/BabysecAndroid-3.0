package com.example.desarrollo_elevation.babysec;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.desarrollo_elevation.viveelite.R;

public class MainActivity_menu_musica extends AppCompatActivity {
    private GridLayoutManager mLayoutManager = new GridLayoutManager(this, 2);
    private static final String KEY_CURRENT_QUERY = "CURRENT_QUERY";
    public static final String EXTRA_TOKEN = "EXTRA_TOKEN" ;
    private  static  String getclas;
    TextView nameplaylist;

    public static Intent createIntent(Context context) {
        return new Intent(context, MainActivity_menu_musica.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_musica);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        String fonter1 = "fonts/OpenSans-Regular.ttf";
        Typeface t = Typeface.createFromAsset(getApplicationContext().getAssets(), fonter1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarmusica);
        nameplaylist = (TextView)findViewById(R.id.txttoolbar_title_playlist);
        toolbar.setTitle("");
        nameplaylist.setText("Playlist");
        nameplaylist.setTextSize(22);
        nameplaylist.setTypeface(t);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.flecha_retorno);

        if (savedInstanceState != null) {
            String currentQuery = savedInstanceState.getString(KEY_CURRENT_QUERY);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
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

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}

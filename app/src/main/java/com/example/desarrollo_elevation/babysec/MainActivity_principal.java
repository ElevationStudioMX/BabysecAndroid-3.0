package com.example.desarrollo_elevation.babysec;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.desarrollo_elevation.viveelite.R;

/**
 * Created by Desarrollo on 01/06/2017.
 */

public class MainActivity_principal extends AppCompatActivity {
    private Toolbar toolbar;
    private ImageView button_memories;
    private ImageView button_recordatorios;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        toolBarConfig();
        setValorButtons();
    }

    public void toolBarConfig() {
        toolbar = (Toolbar) findViewById(R.id.toolbarprincipal);
        setSupportActionBar(toolbar);
        /*if(getSupportActionBar()!=null){
            Drawable drawable= getResources().getDrawable(R.drawable.return_icon);
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            Drawable newdrawable = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 130, 130, true));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(newdrawable);

        }*/
        /*getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.plus_icon);*/
    }

    public void setValorButtons() {
        button_memories = (ImageView) findViewById(R.id.button_memories);
        button_recordatorios = (ImageView) findViewById(R.id.button_recordatorios);
    }

    public void click_memories(View view) {
        Intent intent = new Intent(this, MainActivity_galeria_imagen.class);
        startActivity(intent);
    }

    public void click_recordatorios(View view) {
        Intent intent = new Intent(this, MainActivity_recordatorios.class);
        startActivity(intent);
    }
}

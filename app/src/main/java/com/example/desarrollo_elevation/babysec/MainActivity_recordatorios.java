package com.example.desarrollo_elevation.babysec;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import com.example.desarrollo_elevation.babysec.Adapter.Adapter_galeria;
import com.example.desarrollo_elevation.babysec.Adapter.adapter_recordatorio;
import com.example.desarrollo_elevation.babysec.DB.DBHome;
import com.example.desarrollo_elevation.viveelite.R;

import java.util.List;

/**
 * Created by Desarrollo on 01/06/2017.
 */

public class MainActivity_recordatorios extends AppCompatActivity {
    private static int mId = 1000;
    private Adapter_galeria adapter;
    private Toolbar toolbar;
    private RecyclerView mRecyclerView;
    private adapter_recordatorio mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_recordatorios);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        toolBarConfig();
        get_events();
    }

    public void toolBarConfig()
    {

        toolbar = (Toolbar) findViewById(R.id.toolbarRecordatorios);
        toolbar.setTitle("Eventos");
        toolbar.setTitleTextColor(Color.rgb(255,210,197));

        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            Drawable drawable= getResources().getDrawable(R.drawable.return_icon);
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            int h_logo = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 35 , getResources().getDisplayMetrics());
            Drawable newdrawable = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, h_logo, h_logo, true));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(newdrawable);

        }
        /*getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.plus_icon);*/
    }

    public void first_event()
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Nacimiento");
        builder.setMessage("¿Deseas ingresar la fecha de nacimiento de tu bebe?");
        builder.setIcon(R.drawable.calendar_icon);

        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(MainActivity_recordatorios.this, MainActivity_add_recordatorio.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean("firstevent",true);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.dismiss();

            }
        });
        builder.show();
    }



    public void add_recordatorio()
    {
        Intent intent = new Intent(MainActivity_recordatorios.this, MainActivity_add_recordatorio.class);
        startActivity(intent);
    }

    public void get_events()
    {
        DBHome db = new DBHome(this);
        List<model_events> events = db.getAllEvents();
        model_events [] listas = new model_events[events.size()];
        int i = 0;

        if(listas.length==0)
        {
            first_event();
        }
        for (model_events event : events) {
            String log = "Id: " + event.getId() + " ,Titulo: " + event.getTitle() + " ,Fecha: " + event.getDate() + " ,Descripcion: " + event.getDescription();

            Log.d("SQL", log);
            listas[i]=event;
            i++;
        }

        setList(listas);
    }

    private void setList(model_events[] l)
    {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewerRecordatorios);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new adapter_recordatorio(l, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        Intent intent = new Intent(MainActivity_recordatorios.this, MainActivity_principal.class);
        startActivity(intent);
        // onBackPressed();
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_recordatorios, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.idaddrecordatorio:
                this.add_recordatorio();
            return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }


}

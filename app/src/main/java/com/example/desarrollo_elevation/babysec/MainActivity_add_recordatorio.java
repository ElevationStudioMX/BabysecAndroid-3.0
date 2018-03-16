package com.example.desarrollo_elevation.babysec;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
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
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.desarrollo_elevation.babysec.DB.DBHome;
import com.example.desarrollo_elevation.viveelite.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Desarrollo on 04/06/2017.
 */

public class MainActivity_add_recordatorio extends AppCompatActivity {


    private Toolbar toolbar;
    private Button addButton;
    private MenuItem deleteButton;
    private EditText title, description;
    private model_events event;
    private CalendarView date;
    private int id=0;
    private static final int ALARM_REQUEST_CODE = 1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_add_recordatorio);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        title   = (EditText)findViewById(R.id.titleRecordatorio);
        description   = (EditText)findViewById(R.id.desRecordatorio);
        date   = (CalendarView)findViewById(R.id.dateRecordatorio);
        addButton = (Button)findViewById(R.id.addButton);
        addButton.setBackgroundColor(Color.rgb(255,210,197));

        toolBarConfig();

        Bundle bundle = getIntent().getExtras();
        if(!getIntent().hasExtra("idevent"))
        {
            id=0;
            if(getIntent().hasExtra("firstevent"))
            {
                setInfoFirst();
            }
        }else
        {
            id = bundle.getInt("idevent");
        }

        if(id!=0)
        {
            addButton.setText("Modificar");
            toolbar.setTitle("Modificar Evento");
            getInfo(id);
        }

        addButton.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        if(id!=0)
                        {
                            modifyEvent();
                        }
                        else
                        {
                            addEvent();
                        }
                    }
                });
    }

    public void getInfo(int id)
    {
        DBHome db = new DBHome(this);
        event=db.getEvent(id);
        title.setText(event.getTitle());
        description.setText(event.getDescription());

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date date = sdf.parse(event.getDate());

            long startDate = date.getTime();
            this.date.setDate(startDate);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

    }

    public void setInfoFirst()
    {
        title.setText("Hoy nacio mi bebe");
        description.setText("Nacimiento de mi bebe");
    }

    public void toolBarConfig()
    {

        toolbar = (Toolbar) findViewById(R.id.toolbaraddrecordatorio);
        toolbar.setTitle("Agregar Evento");
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
    }

    private void addEvent()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String selectedDate = sdf.format(new Date(date.getDate()));

        if(title.getText().toString().matches(""))
        {
            Toast.makeText(this, "Ingresar titulo del evento", Toast.LENGTH_LONG).show();
            return;
        }

        if(description.getText().toString().matches(""))
        {
            Toast.makeText(this, "Ingresar descripcion del evento", Toast.LENGTH_LONG).show();
            return;
        }

        DBHome db = new DBHome(this);
        model_events e = new model_events();

        e.setDate(selectedDate);
        e.setTitle(title.getText().toString());
        e.setDescription(description.getText().toString());

        long id = db.addEvent(e);
        establecerAlarmaClick(id);
    }

    private void modifyEvent()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String selectedDate = sdf.format(new Date(date.getDate()));

        if(title.getText().toString().matches(""))
        {
            Toast.makeText(this, "Ingresar titulo del evento", Toast.LENGTH_LONG).show();
            return;
        }

        if(description.getText().toString().matches(""))
        {
            Toast.makeText(this, "Ingresar descripcion del evento", Toast.LENGTH_LONG).show();
            return;
        }

        DBHome db = new DBHome(this);

        event.setId(this.id);
        event.setDate(selectedDate);
        event.setTitle(title.getText().toString());
        event.setDescription(description.getText().toString());

        long id = db.updateEvent(event);
        establecerAlarmaClick(id);
    }

    private void deleteEvent()
    {
        Bundle bundle = getIntent().getExtras();

        final int iddelete = bundle.getInt("idevent");
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Borrar Evento");
        builder.setMessage("Deseas borrar el evento");
        builder.setIcon(R.drawable.warning_new);

        builder.setPositiveButton("Borrar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            DBHome db = new DBHome(MainActivity_add_recordatorio.this);
            db.deleteEvent(event);

            AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);

            Intent intent  = new Intent(MainActivity_add_recordatorio.this, AlarmReceiver.class);
                System.out.println(id+"<<<<<<<<<<<");
            PendingIntent pIntent = PendingIntent.getBroadcast(MainActivity_add_recordatorio.this, iddelete, intent,  PendingIntent.FLAG_CANCEL_CURRENT);
            manager.cancel(pIntent);

            Intent inte = new Intent(MainActivity_add_recordatorio.this, MainActivity_recordatorios.class);
            startActivity(inte);
            Toast.makeText(MainActivity_add_recordatorio.this, "Eliminado exitosamente", Toast.LENGTH_LONG).show();
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.dismiss();

            }
        });
        builder.show();



    }



    private void establecerAlarmaClick(long id){
        AlarmManager manager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);

        Intent intent  = new Intent(this, AlarmReceiver.class);
        Bundle bundle = new Bundle();
        bundle.putLong("idevent",id);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(date.getDate()));

        calendar.add(Calendar.MONTH,1);
//        calendar.add(Calendar.DAY_OF_WEEK,7);
//        calendar.add(Calendar.MINUTE,1);
        Date newDate = calendar.getTime();

        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");

        bundle.putString("date", simpleDateFormat.format(newDate));
        bundle.putString("title",title.getText().toString());
        bundle.putInt("cont", 1);
        intent.putExtras(bundle);

        int interval = 1000 * 60 *60 * 24 * 7;
//        int interval = 1000 * 60;

        PendingIntent pIntent = PendingIntent.getBroadcast(this, ((int) id), intent,  PendingIntent.FLAG_CANCEL_CURRENT);
        manager.cancel(pIntent);
        //manager.setRepeating(AlarmManager.RTC_WAKEUP , calendar.getTimeInMillis(), interval , pIntent);
        manager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis() ,pIntent);

        Intent intent_ = new Intent(this, MainActivity_recordatorios.class);
        startActivity(intent_);
        Toast.makeText(this, "Guardado exitosamente", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        Intent intent = new Intent(this, MainActivity_recordatorios.class);
        startActivity(intent);
        // onBackPressed();
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_recordatorio, menu);
        if (id == 0)
        {
            for (int i = 0; i < menu.size(); i++)
                menu.getItem(i).setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.borraRecordatorios:
                this.deleteEvent();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}

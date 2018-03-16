package com.example.desarrollo_elevation.babysec.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.desarrollo_elevation.babysec.model_events;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Desarrollo_Elevation on 27/03/17.
 */

public class DBHome extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "babysec.db";

    public DBHome(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table bandera(id_tipo integer primary key, Nombre text)");
        sqLiteDatabase.execSQL("create table contenido(id_contenido integer primary key AUTOINCREMENT, id_tipo integer, id_tipo_contenido interger,  id integer not null, titulo text not null, influencer text not null, categoria text, permarlink text, descripcion text not null, descripcion_ejercicio text, contenido text, preparacion text, duracion text, ELEV_TIMESTAMP text,fecha_publicacion text not null,fecha text not null, favoritos integer not null, fecha_favoritos text not null)");
        //  sqLiteDatabase.execSQL("create table contenido(id_contenido integer primary key AUTOINCREMENT, id_tipo integer, id_tipo_contenido interger,  id integer not null, titulo text not null, influencer text not null, categoria text, permarlink text, descripcion text not null, contenido text, preparacion text, duracion text, fecha_publicacion text not null,fecha text not null, favoritos integer not null, fecha_favoritos text not null)");
        sqLiteDatabase.execSQL("create table ingredientes(ingrediente_id integer not null,id integer not null,  cantidad text, ingrediente text)");
        sqLiteDatabase.execSQL("create table carrusel(id integer primary key autoincrement, id_contenido integer, id_tipo_contenido integer)");
        sqLiteDatabase.execSQL("create table tipo_contenido(id_tipo_contenido interger primary key, nombre text not null)");
        sqLiteDatabase.execSQL("create table categoria_receta(id_categoria_receta integer, nombre text, descripcion text,imagen text)");
        sqLiteDatabase.execSQL("create table bandera_popwindow(view integer)");

        sqLiteDatabase.execSQL("create table categoria_sticker(id integer primary key autoincrement, id_categoria_sticker integer, nombre text)");
        sqLiteDatabase.execSQL("create table sticker(id integer primary key autoincrement, id_categoria_sticker integer, color_filter integer, sticker text)");

        String CREATE_EVENTS_TABLE = "CREATE TABLE events ( id INTEGER PRIMARY KEY AUTOINCREMENT, date TEXT, title TEXT, description TEXT )";
        sqLiteDatabase.execSQL(CREATE_EVENTS_TABLE);

        sqLiteDatabase.execSQL("insert into bandera_popwindow values (0)");

        sqLiteDatabase.execSQL("create table fecha_actualizacion(fecha text)");

       sqLiteDatabase.execSQL("insert into  fecha_actualizacion values('')");


        sqLiteDatabase.execSQL("create table fecha_actualizacion_receta(fecha text)");

        sqLiteDatabase.execSQL("insert into  fecha_actualizacion_receta values('')");


        sqLiteDatabase.execSQL("create table fecha_actualizacion_ejercicio(fecha text)");
        sqLiteDatabase.execSQL("insert into  fecha_actualizacion_ejercicio values('')");

        sqLiteDatabase.execSQL("create table fecha_actualizacion_sticker(fecha text)");
        sqLiteDatabase.execSQL("insert into  fecha_actualizacion_sticker values('')");


        sqLiteDatabase.execSQL("insert into bandera values(1, 'Recetas')");
        sqLiteDatabase.execSQL("insert into bandera values(2, 'Ejercicios')");

        sqLiteDatabase.execSQL("insert into tipo_contenido values(1, 'imagenes')");
        sqLiteDatabase.execSQL("insert into tipo_contenido values(2, 'videos')");



 }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


        // Drop older table if existed
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS categoria_sticker" );
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS sticker" );
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS events" );
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS bandera" );
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS contenido" );
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS ingredientes" );
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS carrusel" );
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tipo_contenido" );
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS categoria_receta" );
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS bandera_popwindow" );
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS fecha_actualizacion" );
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS fecha_actualizacion_receta" );
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS fecha_actualizacion_ejercicio" );
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS fecha_actualizacion_sticker" );
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS bandera" );
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tipo_contenido" );

        onCreate(sqLiteDatabase);

        /*sqLiteDatabase.execSQL("create table categoria_sticker(id integer primary key autoincrement, id_categoria_sticker integer, nombre text)");

        sqLiteDatabase.execSQL("create table sticker(id integer primary key autoincrement, id_categoria_sticker integer, color_filter integer, sticker text)");


        sqLiteDatabase.execSQL("insert into  fecha_actualizacion values('')");


        sqLiteDatabase.execSQL("create table fecha_actualizacion_receta(fecha text)");
        sqLiteDatabase.execSQL("insert into  fecha_actualizacion_receta values('')");

        sqLiteDatabase.execSQL("create table fecha_actualizacion_ejercicio(fecha text)");
        sqLiteDatabase.execSQL("insert into  fecha_actualizacion_ejercicio values('')");

        sqLiteDatabase.execSQL("create table fecha_actualizacion_sticker(fecha text)");
        sqLiteDatabase.execSQL("insert into  fecha_actualizacion_sticker values('')");


        sqLiteDatabase.execSQL("insert into bandera values(1, 'Recetas')");
        sqLiteDatabase.execSQL("insert into bandera values(2, 'Ejercicios')");

        sqLiteDatabase.execSQL("insert into tipo_contenido values(1, 'imagenes')");
        sqLiteDatabase.execSQL("insert into tipo_contenido values(2, 'videos')");*/
       // sqLiteDatabase.execSQL("select* from delte datos movliels android sqltie datos guardados");


       /* sqLiteDatabase.execSQL("insert into contenido(0, 'Muffin de platanos', 'como hacer los muffins', 'una taza de azucar" +
                " un kilo de chocolate  dos platanos  mas cosas', 'https://s-media-cache-ak0.pinimg.com/736x/78/5d/27/785d27ee814a904942f4d11cf44437cb.jpg', '', '2017-03-29', 1 )");
    */}

    public long addEvent(model_events evt) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("title", evt.getTitle());
        values.put("date", evt.getDate());
        values.put("description", evt.getDescription());

        long id = db.insert("events", null, values);
        db.close();
        return id;
    }


    public model_events getEvent(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query("events", new String[]{"id",
                "date", "title", "description"}, "id = ?",
        new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        model_events e = new model_events();
        e.setId(Integer.parseInt(cursor.getString(0)));
        e.setDate(cursor.getString(1));
        e.setTitle(cursor.getString(2));
        e.setDescription(cursor.getString(3));
// return shop
        return e;
    }

    public List<model_events> getAllEvents() {
        List<model_events> eventList = new ArrayList<model_events>();

        String selectQuery = "SELECT * FROM events";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                model_events e = new model_events();
                e.setId(Integer.parseInt(cursor.getString(0)));
                e.setDate(cursor.getString(1));
                e.setTitle(cursor.getString(2));
                e.setDescription(cursor.getString(3));
                eventList.add(e);
            } while (cursor.moveToNext());
        }
        return eventList;
    }

    public int getEventsCount() {
        String countQuery = "SELECT * FROM events";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }


    public int updateEvent(model_events evt) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("date", evt.getDate());
        values.put("title", evt.getTitle());
        values.put("description", evt.getDescription());

        return db.update("events", values, "id = ?",
        new String[]{String.valueOf(evt.getId())});
    }

    public void deleteEvent(model_events evt) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("events", "id = ?",
        new String[] { String.valueOf(evt.getId()) });
        db.close();
    }
}

package com.example.desarrollo_elevation.babysec;

/**
 * Created by Desarrollo on 04/06/2017.
 */

public class model_events {
    private int id;
    private String date;
    private String title;
    private String description;


    public model_events()
    {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }
}

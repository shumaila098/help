package com.example.mani.realtim;

/**
 * Created by mani on 23/08/2017.
 */

public class Cordinates {
    String id;
    String lat;
    String longi;
    String status;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public Cordinates(String lat,String lon)
    {
        this.lat=lat;
        this.longi=lon;

    }

    public Cordinates()
    {

    }

    public String getLats() {
        return lat;
    }

    public String getLons() {
        return longi;
    }


    public void setLats(String lat) {
        this.lat=lat;

    }

    public void setLons(String lon) {

        this.longi=lon;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id=id;

    }
}

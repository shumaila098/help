package com.example.mani.realtim;

import android.location.Location;

/**
 * Created by mani on 20/11/2017.
 */

public class Distance {

    double getDistance(double startpointlat,double endpointlat,double startpointlon,double endpointlon) {


    Location startPoint = new Location("locationA");
        startPoint.setLatitude(startpointlat);
        startPoint.setLongitude(startpointlon);


    Location endPoint = new Location("locationB");
        endPoint.setLongitude(endpointlon);
        endPoint.setLatitude(endpointlat);

   double distance ;//= startPoint.distanceTo(endPoint);
     //   return distance;



            double earthRadius = 6371000; //meters
            double dLat = Math.toRadians(endpointlat - startpointlat);
            double dLng = Math.toRadians(endpointlon - startpointlon);
            double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                    Math.cos(Math.toRadians(startpointlat)) * Math.cos(Math.toRadians(endpointlat)) *
                            Math.sin(dLng / 2) * Math.sin(dLng / 2);
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
           float dist = (float) (earthRadius * c);



                 //   distance=dist;
            return dist;

}
}

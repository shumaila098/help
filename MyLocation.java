package com.example.mani.realtim;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by mani on 23/08/2017.
 */

public class MyLocation implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener ,LocationListener {
    Context c;
    GoogleApiClient googleApiClient;
    UserFirebase s;
    FirebaseUser us;
    FirebaseAuth mAuth;


    LocationRequest req;
 public  static  Cordinates cord=new Cordinates("0.0","0.0");
    public Cordinates MyLocations(Context c)
    {
        this.c=c;

        mains();
        return cordin_ret();
    }

public void mains(){
    googleApiClient = new GoogleApiClient.Builder(c).addApi(LocationServices.API).addConnectionCallbacks(this).addOnConnectionFailedListener(this).build();
        googleApiClient.connect();
    if(googleApiClient.isConnected()){

    }
    else{}
}
    public void showmessage(String v) {
        Toast.makeText(c, v, Toast.LENGTH_SHORT).show();
    }




    @Override
    public void onConnected(@Nullable Bundle bundle) {
        req = LocationRequest.create();
        req.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        req.setInterval(500);
        if (ActivityCompat.checkSelfPermission(c, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(c, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, req, this);

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        if(location==null)
        {
            showmessage("Cant get current Location");
        }
        else{
           // showmessage("lat ="+location.getLatitude()+" lon="+location.getLongitude());
            cord=new Cordinates(String.valueOf(location.getLatitude()),String.valueOf(location.getLongitude()));

            mAuth = FirebaseAuth.getInstance();
            us = mAuth.getCurrentUser();
            if(us!=null)
            {
                s = new UserFirebase();
                s.firedatabaseinit(us.getUid());
                s.setLatNode(location.getLatitude());
                s.setLongNode(location.getLongitude());

            }
        }

    }

    public Cordinates cordin_ret()
    {
        return  cord;
    }
}

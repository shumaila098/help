package com.example.mani.realtim;

import android.*;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;


public class Findneedyperson extends AppCompatActivity implements GoogleMap.OnMarkerClickListener,OnMapReadyCallback {
    private FirebaseDatabase fd = FirebaseDatabase.getInstance();

    private DatabaseReference dr = fd.getReference();
    ArrayList<Needy> cordinates = new ArrayList<>();
    private DatabaseReference r = dr.child("data");
    private DatabaseReference  saaas=r.child("Needy");
    private GoogleMap ap;
    private int MY_LOCATION_REQUEST_CODE;
    public  NeedyDataFromId s;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        FirebaseUser us;
        us=FirebaseAuth.getInstance().getCurrentUser();
        if(us!=null)
        { MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                MY_PERMISSIONS_REQUEST_LOCATION);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {

            // Toast.makeText(this, "oncreat   ", Toast.LENGTH_LONG).show();

        } else {
            // Show rationale and request permission.



        }
        mapFragment.getMapAsync(this);
    }
          else{
            startActivity(new Intent(this, Loggin.class));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        ap.setMyLocationEnabled(true);

        if (requestCode == 1) {
            if (permissions.length == 1 &&
                    permissions[0] == android.Manifest.permission.ACCESS_FINE_LOCATION &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                MyLocation oo=new MyLocation();
                Cordinates er=oo.MyLocations(getApplicationContext());
               double lats=Double.parseDouble(er.getLats());
                double lon=Double.parseDouble(er.getLons());

             if(lats!=0.0&&lats!=0.0)
               ap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lats,lon), 120.0f));
                ap.setMyLocationEnabled(true);
                ap.getUiSettings().setMyLocationButtonEnabled(true);

            } else {
                ap.setMyLocationEnabled(true);
                ap.getUiSettings().setMyLocationButtonEnabled(true);

                // Permission was denied. Display an error message.
            }
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {

            case R.id.profile:

                startActivity(new Intent(this,Userdata.class));


                return true;
            case R.id.logout:
                FirebaseAuth fAuth = FirebaseAuth.getInstance();
                fAuth.signOut();
                startActivity(new Intent(this,Loggin.class));


                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        ap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        //ap.setMyLocationEnabled(true);
       // ap.getUiSettings().setMyLocationButtonEnabled(true);

        MyLocation oo=new MyLocation();
        Cordinates er=oo.MyLocations(getApplicationContext());
        double lats=Double.parseDouble(er.getLats());
        double lon=Double.parseDouble(er.getLons());
        ap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lats,lon), 16.0f));
        ap.setOnMarkerClickListener(this);

fp();


       saaas.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
               // if(dataSnapshot.exists()&&!dataSnapshot.equals(null))


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });



        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
    void fp() {
        ReturnCordinates returnCordinates;
        returnCordinates=new ReturnCordinates();
    //    showmessage("fp() is called");
        cordinates = returnCordinates.getneedyCord();

        if(!(cordinates==null||cordinates.equals(null)))
        {
            for (Needy C : cordinates) {
                addmarkers(C);
                //  showmessage("we exists");

            }
        }

        String go=String.valueOf(returnCordinates.getneedynum());



    }
    public void showmessage(String v) {
        Toast.makeText(this,v,Toast.LENGTH_SHORT).show();
    }
    public void addmarkers(Needy id) {
        if (id != null) {

            if (id.getLongi()!= null&&id.getLat()!= null&&id.getStatus()!= null)  {
            String idd = id.getId();



            if (Integer.parseInt(id.getStatus()) == 0) {
                MarkerOptions markopt = new MarkerOptions().title(id.getKoh()).icon(BitmapDescriptorFactory.fromResource(R.drawable.zero)).position(new LatLng(Double.valueOf(id.getLat()), Double.valueOf(id.getLongi()))).snippet(id.getName() + " " + id.getSurname());
              ap.addMarker(markopt).setTag(id);
            }
            if (Integer.parseInt(id.getStatus()) == 1) {
                MarkerOptions markopt = new MarkerOptions().title(id.getKoh()).icon(BitmapDescriptorFactory.fromResource(R.drawable.one)).position(new LatLng(Double.valueOf(id.getLat()), Double.valueOf(id.getLongi()))).snippet(id.getName() + " " + id.getSurname());
            ap.addMarker(markopt).setTag(id);
            }
            if (Integer.parseInt(id.getStatus()) == 2) {
                MarkerOptions markopt = new MarkerOptions().title(id.getKoh()).icon(BitmapDescriptorFactory.fromResource(R.drawable.two)).position(new LatLng(Double.valueOf(id.getLat()), Double.valueOf(id.getLongi()))).snippet(id.getName() + " " + id.getSurname());
                ap.addMarker(markopt).setTag(id);
            }
        }
//showmessage(id.getLats()+"   "+id.getLons());
    }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {

      Needy s= (Needy) marker.getTag();

        Needyshow.s=s;
        startActivity(new Intent(this,Needyshow.class));


        return false;
    }
}

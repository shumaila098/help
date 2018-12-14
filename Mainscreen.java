package com.example.mani.realtim;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by mani on 11/08/2017.
 */

public class Mainscreen extends AppCompatActivity implements AdapterView.OnItemSelectedListener,View.OnClickListener{
    ImageView findneedperson;
    ImageView viewstat;
    ImageView addneedyperson;
   Double lod,lot = null;
    String nam;
    String sur;
    String koh;
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<datamodel> data;
    TextView points;
    private FirebaseDatabase fd = FirebaseDatabase.getInstance();

    private DatabaseReference dr = fd.getReference();

    private DatabaseReference r = dr.child("data");

    private DatabaseReference needloc= r.child("Needy");
        static int kvalue=5;
    static Double pou= Double.valueOf(0);
    static boolean setk=true;
    int i;
    ImageView updateneedyperson;
    static boolean noti=true;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainscreen4);
        FirebaseUser us;
        us=FirebaseAuth.getInstance().getCurrentUser();
        points = (TextView) findViewById(R.id.te);
        findneedperson = (ImageView) findViewById(R.id.findneedy);
        viewstat= (ImageView) findViewById(R.id.Viewstatistics);
     //   helpneed = (Button) findViewById(R.id.needer);
        findneedperson.setOnClickListener(this);
        //Nee= (Button) findViewById(R.id.Needy);
        addneedyperson= (ImageView) findViewById(R.id.addneedy);
        updateneedyperson = (ImageView) findViewById(R.id.updateneedy);
        updateneedyperson.setOnClickListener(this);
        addneedyperson.setOnClickListener(this);
    //   Nee.setOnClickListener(this);
     //   helpneed.setOnClickListener(this);
       viewstat.setOnClickListener(this);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements

        List<String> km = new ArrayList<String>();
        km.add("Not selected");
        for(int i=1;i<30;i++)
        km.add(String.valueOf(i)+ "km");





        kvalue=getenter();
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,km);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        spinner.setSelection(kvalue);

        if(us!=null){
        UserFirebase a = new UserFirebase();
        a.firedatabaseinit(us.getUid());
        points.setText("Your points:"+String.valueOf(a.getPointsNode()));
        //    points.setText("Your instructor: Sarah Ahmed");
//nn


            GetcordinatesFirebae t=new GetcordinatesFirebae();
            t.firedatabaseinit();

            MyLocation myLocation;
        myLocation=new MyLocation();

        myLocation.MyLocations(this);}
        else{
            startActivity(new Intent(this, Loggin.class));
        }
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
       // Query chatQuery = conversationReference.orderByChild("last_time_stamp"). limitToLast(20);
        needloc.addChildEventListener(new ChildEventListener() {
                                          @Override
                                          public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                                             Needy needy=new Needy();
                                              needy  = dataSnapshot.getValue(Needy.class);

                                            if(noti&&setk&&needy.getLat()!=null&&needy.getLongi()!=null) {
                                                  if(LocationDistanceVerify.ChecklocDistanceValid(Double.valueOf(needy.getLat()),Double.valueOf(needy.getLongi()),kvalue)) {

                                                      addNotification(needy.getName() + " " + needy.getSurname(), needy.getKoh());
                                                  }

                                              }

                                              noti=true;
}

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
     i=0;
        needloc.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                data = new ArrayList<datamodel>();

                for (DataSnapshot child : dataSnapshot.getChildren()) {




                  data.add(new datamodel(i,child.getValue(Needy.class),child.getValue(Needy.class).getName(),child.getValue(Needy.class).getSurname(),child.getValue(Needy.class).getStatus(),child.getValue(Needy.class).getKoh(),child.getValue(Needy.class).getDoh()));
                    i++;



                }
                /*
               if(LocationDistanceVerify.ChecklocDistanceValid(lot,lod,kvalue)) {
                    addNotification(nam + " " + sur, koh);
                }

                            showmessage(String.valueOf(pou) + "long=" +lod+ "lat=" +lot);

                if(noti&&setk) {
                    if(LocationDistanceVerify.ChecklocDistanceValid(lot,lod,kvalue)) {
                        addNotification(nam + " " + sur, koh);
                    }

                }

                noti=true;
                */
                Collections.reverse(data);
                adapter = new dataAdapter(data,getApplicationContext());
                recyclerView.setAdapter(adapter);
            }


            @Override
            public void onCancelled(DatabaseError error) {

                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });





    }

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
            case R.id.points:


                startActivity(new Intent(this,MyPoints.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
        startActivity(intent);
        finish();
        System.exit(0);
    }
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.findneedy: {

                startActivity(new Intent(this,Findneedyperson.class));

            }
            break;

            case R.id.Viewstatistics: {


                startActivity(new Intent(this,viewstatistics.class));

            }
            break;
            case R.id.updateneedy: {

                startActivity(new Intent(this,Needies.class));


            }
            break;
            case R.id.addneedy: {


                startActivity(new Intent(this,Addneedyperson.class));

            }
            break;


        }

    }
    private void addNotification(String name,String need) {
        android.support.v4.app.NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.cast_ic_notification_small_icon)
                        .setContentTitle("Help")
                        .setContentText("Check Map, New needy is found around you");


        PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(),  0, new Intent(),0);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());


    }
    public void showmessage(String v) {
        Toast.makeText(this, v, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
       if(position!=0)
       { kvalue=position;
           enter(String.valueOf(kvalue));

        setk=true;
       }
 else
     setk=false;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    int getenter(){
String toret="";
int ui =5;
        try {

            DataBase db=new DataBase(this);
            db.open();

if (db.checkentry())
{  toret = db.getEmail();

                   ui=Integer.valueOf(toret);

}
                     else {
    ui=5;

}
                db.close();


        }
        catch(Exception e) {


        }
        return ui;
    }
void enter(String ent){

    try {

        DataBase db = new DataBase(this);

        db.open();
        String op="\""+ent+"\"";
        if (db.checkentry()) {
            db.deleteentry();

        }
        db.createentry(ent);

        db.close();

    }

    catch(Exception e){

    }
}

}

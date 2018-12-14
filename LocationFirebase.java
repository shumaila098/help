package com.example.mani.realtim;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by mani on 24/08/2017.
 */

public class LocationFirebase {
    ArrayList<String> list_of_users;
    ArrayList<Cordinates> cordin=new ArrayList<>();
    private FirebaseDatabase fd = FirebaseDatabase.getInstance();

    private DatabaseReference dr = fd.getReference();

    private DatabaseReference r = dr.child("data");


    private DatabaseReference ne= r.child("Locations");

    private DatabaseReference needloc= ne.child("Needy");
    DatabaseReference needystatus,useridloc,needyloc,needylat,needylon,needyid;

    String  ni, latNode, longNode,id="0";


    public void firedatabaseinit(String NI) {
     latNode= longNode="0";
        cordin=new ArrayList<>();
        list_of_users=new ArrayList<>();
ni=NI;

        needyloc=needloc.child(NI);
        needylat=needyloc.child("lat");
        needylon=needyloc.child("longi");
        needyid=needyloc.child("id");
        needystatus=needyloc.child("status");

//trying to set the image from filepath





        needylon.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                if(dataSnapshot.exists()&&!dataSnapshot.equals(null))
                    longNode= dataSnapshot.getValue(String.class);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        needylat.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                if(dataSnapshot.exists()&&!dataSnapshot.equals(null))
                    latNode = dataSnapshot.getValue(String.class);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });


    }
    public  String getid()
    {


        return id;
    }
    public Double getLatNode()
    {
        double d = Double.parseDouble(latNode);
        return d;
    }
    public Double getLongNode()
    {
        double d = Double.parseDouble(longNode);
        return d;
    }
    public void setLatNode(Double latNodes)
    {
        String s=String.valueOf(latNodes);
        needyloc.child("id").setValue(ni);
        needylat.setValue(s);

    }
    public void setLongNode(Double longNodea)
    {
        String s=String.valueOf(longNodea);

        needylon.setValue(s);

    }


    public void setstatusNode(String longNodea)
    {


        needystatus.setValue(longNodea);

    }

}

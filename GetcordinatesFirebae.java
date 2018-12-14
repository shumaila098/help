package com.example.mani.realtim;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

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

public class GetcordinatesFirebae {
    ArrayList<String> list_of_users;
  public static  ArrayList<Needy>  cordin=new ArrayList<>();
    private FirebaseDatabase fd = FirebaseDatabase.getInstance();
static int num=80;
    private DatabaseReference dr = fd.getReference();

    private DatabaseReference r = dr.child("data");




    private DatabaseReference needloc= r.child("Needy");

    String  latNode, longNode="0";


    public void firedatabaseinit() {


        list_of_users = new ArrayList<>();



//trying to set the image from filepath




                        needloc.addValueEventListener(new ValueEventListener() {

                            @Override
                            public void onDataChange( DataSnapshot dataSnapshot) {
                             // This method is called once with the initial value and again
                                // whenever data at this location is updated.
                                cordin=new ArrayList<>();
                                    num=90;
                                num=(int)dataSnapshot.getChildrenCount();
                                for (DataSnapshot child : dataSnapshot.getChildren()) {

                                           Needy sp = new Needy();
                                          sp=child.getValue(Needy.class);

                                            cordin.add(sp);
                                        }


                                    }



                            @Override
                            public void onCancelled(DatabaseError error) {
                              num=400;
                                Log.w(TAG, "Failed to read value.", error.toException());
                            }
                        });





                }


    public ArrayList<Needy> getAllCordinates()
    {

        return cordin;
    }

    public int getchildcount()
    {

        return num;
    }

}

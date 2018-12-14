package com.example.mani.realtim;

import android.net.Uri;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by mani on 19/09/2017.
 */

public class NeedyUriFirebase {
    private FirebaseDatabase fd = FirebaseDatabase.getInstance();

    private DatabaseReference dr = fd.getReference();

    private DatabaseReference r = dr.child("data");

    private DatabaseReference needy = r.child("URI");
    private DatabaseReference one,two,three,four;
    DatabaseReference lead;
    public static String oneS="",twoS="",threeS="",fourS="";
    public void firedatabaseinit(String NI) {
   lead = needy.child(NI);
        one=lead.child("one");
        two=lead.child("two");
        three=lead.child("three");
        four=lead.child("four");

        lead.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                oneS=twoS=threeS=fourS="";
                if (dataSnapshot.exists() && !dataSnapshot.equals(null)) {

                   oneS = dataSnapshot.child("one").getValue(String.class);
                    twoS = dataSnapshot.child("two").getValue(String.class);
                   threeS = dataSnapshot.child("three").getValue(String.class);
                    fourS = dataSnapshot.child("four").getValue(String.class);

                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }




    public ArrayList<Uri> getUris(int nodes) {
       ArrayList<Uri>  uriArrayList=new ArrayList<>();
        if(nodes==1)
        {
            uriArrayList.add(getUrione());
        }
        if(nodes==2)
        {
            uriArrayList.add(getUrione());
            uriArrayList.add(getUriotwo());
        }
        if(nodes==3)
        {
            uriArrayList.add(getUrione());
            uriArrayList.add(getUriotwo());
            uriArrayList.add(getUrithree());
        }
        if(nodes==4)
        {
            uriArrayList.add(getUrione());
            uriArrayList.add(getUriotwo());
            uriArrayList.add(getUrithree());
            uriArrayList.add(getUrifour());
        }
        deleteuris(nodes);

        return uriArrayList;
    }

    public Uri getUrione()
    {
       Uri w= Uri.parse(oneS);
        return w;
    }
    public Uri getUriotwo()
    {
        Uri w= Uri.parse(twoS);
        return w;
    }
    public Uri getUrithree()
    {
        Uri w= Uri.parse(threeS);
        return w;
    }
    public Uri getUrifour()
    {
        Uri w= Uri.parse(fourS);
        return w;
    }

    public void deleteone() {
        one.setValue("");
    }
    public void deletetwo() {
        one.setValue("");
        two.setValue("");

    }
    public void deletethree() {
        one.setValue("");
        two.setValue("");
        three.setValue("");

    }
    public void deletefour() {
        one.setValue("");
        two.setValue("");
        three.setValue("");
        four.setValue("");
    }

    public void setUrione(Uri uri)
    {
        String uris=uri.toString();
        one.setValue(uris);

    }
    public void setUritwo(Uri uri)
    {
        String uris=uri.toString();
        two.setValue(uris);

    }
    public void setUrithree(Uri uri)
    {
        String uris=uri.toString();
        three.setValue(uris);

    }
    public void setUrifour(Uri uri)
    {
        String uris=uri.toString();
        four.setValue(uris);

    }
    public void setUrionenull()
    {

        one.setValue("");

    }
    public void setUritwonull()
    {

        two.setValue("");

    }
    public void setUrithreenull()
    {

        three.setValue("");

    }
    public void setUrifournull()
    {

        four.setValue("");

    }
    public void deleteuris(int total)
    {
        if(total==1)
        {
            deleteone();
        }
        if(total==2)
        {
            deleteone();
            deletetwo();
        }
        if(total==3)
        {
            deleteone();
            deletetwo();
            deletethree();
        }
        if(total==4)
        {
           deleteone();
            deletetwo();
            deletethree();
            deletefour();
        }
    }
    public void setUris(ArrayList<Uri> urilist)
    {

        int size=urilist.size();
        if(size==1)
        {
            setUrione(urilist.get(0));
            setUritwonull();
            setUrithreenull();
            setUrifournull();
        }
        if(size==2)
        {
            setUrione(urilist.get(0));
            setUritwo(urilist.get(1));
            setUrithreenull();
            setUrifournull();
        }
        if(size==3)
        {
            setUrione(urilist.get(0));
            setUritwo(urilist.get(1));
            setUrithree(urilist.get(2));
            setUrifournull();
        }
        if(size==4)
        {
            setUrione(urilist.get(0));
            setUritwo(urilist.get(1));
            setUrithree(urilist.get(2));
            setUrifour(urilist.get(3));
        }



    }



}

package com.example.mani.realtim;

import android.text.Editable;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by mani on 26/08/2017.
 */

public class NeedyDataFromId {
    String iD,kohNode,dohNode,codeNode ,nameNode, nationNode, surnameNode, emailNode, genderNode , postalAddressNode, qualiNode, contactNode, fatherNameNode, dobNode, cnicNode, imgPhoneNode,imgStorNode = "";
    int totalpics=0,age=0;
    private FirebaseDatabase fd = FirebaseDatabase.getInstance();
    private DatabaseReference dr = fd.getReference();
    private DatabaseReference r = dr.child("data");
    private DatabaseReference saaas=r.child("Needy");
    private DatabaseReference saaaschild;
    Needy ue;
    public NeedyDataFromId(String id)
    {
        if(id!=null)
        { ue=new Needy();
        iD=kohNode=dohNode=codeNode =nameNode=nationNode=surnameNode=emailNode= genderNode = postalAddressNode= qualiNode=contactNode= fatherNameNode= dobNode= cnicNode= imgPhoneNode=imgStorNode = "";
        saaaschild=saaas.child(id);
        this.iD=id;
        factory();}
    }
    void factory()
    {
        saaas.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                if(dataSnapshot.exists()&&!dataSnapshot.equals(null))
                {
                    ue=dataSnapshot.child(iD).getValue(Needy.class);
                    genderNode = "malerrrrr";

                }}

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });



if(ue!=null) {
    totalpics = 0;//Integer.parseInt(ue.getTotalpics());
    //genderNode = ue.getGender();
    age = 9;//Integer.parseInt(ue.getAge());
    cnicNode = ue.getCnic();
    dohNode = ue.getDoh();
    kohNode = ue.getKoh();
    contactNode = ue.getContact();
    nationNode = ue.getNation();
    surnameNode = ue.getSurname();//ue.getSurname();
    emailNode = ue.getEmail();
    postalAddressNode = ue.getPostaladdress();
    nameNode =ue.getName();
    fatherNameNode = ue.getFathername();
    qualiNode = ue.getQuali();
    dobNode = ue.getDob();
}

    }

    public int getTotalpics() {
        return totalpics;
    }

    public String getDohNode() {
        return dohNode;
    }

    public String getEmailNode() {
        return emailNode;
    }

    public String getCnicNode() {
        return cnicNode;
    }

    public String getGenderNode() {
        return genderNode;
    }

    public String getFatherNameNode() {
        return fatherNameNode;
    }

    public String getImgPhoneNode() {
        return imgPhoneNode;
    }

    public String getKohNode() {
        return kohNode;
    }

    public String getNameNode() {
        return nameNode;
    }

    public String getImgStorNode() {
        return imgStorNode;
    }

    public String getNationNode() {
        return nationNode;
    }

    public String getContactNode() {
        return contactNode;
    }

    public String getPostalAddressNode() {
        return postalAddressNode;
    }

    public String getDobNode() {
        return dobNode;
    }

    public String getiD() {
        return iD;
    }

    public int getAge() {
        return age;
    }

    public String getQualiNode() {
        return qualiNode;
    }

    public String getSurnameNode() {
        return surnameNode;
    }

    public String getCodeNode() {
        return codeNode;
    }
    public Boolean checkcode(String code)
    {
        if (getCodeNode().equals(code))
        {
            return true;
        }
        else {
            return false;
        }
    }
}

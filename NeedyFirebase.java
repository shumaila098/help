package com.example.mani.realtim;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static android.content.ContentValues.TAG;

/**
 * Created by mani on 16/08/2017.
 */

public class NeedyFirebase {
    private FirebaseDatabase fd = FirebaseDatabase.getInstance();

    private DatabaseReference dr = fd.getReference();

    private DatabaseReference r = dr.child("data");
    static int pro = 0;
    private DatabaseReference sasm = r.child("User");



    DatabaseReference IdNode, useridloc, needyloc, needylat, needylon, TotalpicNode, saaas, saas, AgeNode, KohNode, DohNode, CodeNode, sa, NameNode, NationNode, SurnameNode, EmailNode, GenderNode, LatNode, LongNode, PostalAddressNode, QualiNode, ContactNode, FatherNameNode, DobNode, CnicNode, ImgPhoneNode, ImgStorNode, MonthNode, DateNode, YearNode;

    String latNode, longNode = "0";


    public void firedatabaseinit(String UI, int prod) {
        latNode = longNode = "0";

        saas = sasm.child(UI);
        saaas = saas.child("Needy");
        sa = saaas.child(UI + String.valueOf(prod));
        sa.setValue(UI + String.valueOf(prod));


//trying to set the image from filepath

    }
    }


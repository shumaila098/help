package com.example.mani.realtim;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

/**
 * Created by mani on 25/08/2017.
 */

public class ReturnCordinates {
    GetcordinatesFirebae s;
    int num=500;
ArrayList<Needy> cord=new ArrayList<>();
    public ArrayList<Needy> getneedyCord() {

                s=new GetcordinatesFirebae();
        s.firedatabaseinit();
        cord=s.getAllCordinates();
        num=s.getchildcount();
        return cord;
    }
    public int getneedynum() {


        return num;
    }

}

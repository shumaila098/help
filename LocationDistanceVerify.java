package com.example.mani.realtim;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by mani on 21/11/2017.
 */

public class LocationDistanceVerify {

   public static Boolean ChecklocDistanceValid(double endpointlat,double endpointlon,int k) {
        UserFirebase s;
        FirebaseUser us;
        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        us = mAuth.getCurrentUser();
        if (us != null) {


            s = new UserFirebase();
            s.firedatabaseinit(us.getUid());
            CheckDistanceValid w = new CheckDistanceValid();

            Boolean aw = w.checkDistanceValid(s.getLatNode(), endpointlat,s.getLongNode(), endpointlon,k);
            return aw;
        }

        return false;
    }
}

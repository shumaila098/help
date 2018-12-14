package com.example.mani.realtim;

import java.net.InetAddress;

/**
 * Created by mani on 27/11/2018.
 */

public class Intr {
    public static boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com");
            //You can replace it with your name
            return !ipAddr.equals("");

        } catch (Exception e) {
            return false;
        }
    }
}

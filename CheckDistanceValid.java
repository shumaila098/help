package com.example.mani.realtim;

/**
 * Created by mani on 20/11/2017.
 */

public class CheckDistanceValid {


    Boolean checkDistanceValid(double startpointlat,double endpointlat,double startpointlon,double endpointlon,int k)
    {
            int meter=k*1000;


        Distance distance=new Distance();
        Double dis=distance.getDistance(startpointlat,endpointlat,startpointlon,endpointlon);
        Mainscreen.pou=dis;
        if(dis>meter)
        {return false;}
           else
               return true;
    }


}

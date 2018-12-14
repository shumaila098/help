package com.example.mani.realtim;

import com.google.firebase.database.DatabaseReference;

/**
 * Created by mani on 27/11/2018.
 */

public class Statistics {


    private  String KPK;
   private String BALOCHISTAN;
    private  String SINDH;

    private  String PUNJAB;



    private String Clothes;
    private  String Food;
    private  String Money;
    private  String Service;
    private  String Blood;
    private  String Books;
    private  String Emergency;
    private  String Fire;
    // private DatabaseReference gend = helptype.child("Gender");
    private  String Male;
    private   String Female;

    public String getSINDH() {
        return SINDH;
    }

    public void setSINDH(String SINDH) {
        this.SINDH = SINDH;
    }

    public String getService() {
        return Service;
    }

    public void setService(String service) {
        Service = service;
    }

    public String getPUNJAB() {
        return PUNJAB;
    }

    public void setPUNJAB(String PUNJAB) {
        this.PUNJAB = PUNJAB;
    }

    public String getMoney() {
        return Money;
    }

    public void setMoney(String money) {
        Money = money;
    }

    public String getMale() {
        return Male;
    }

    public void setMale(String male) {
        Male = male;
    }

    public String getKPK() {
        return KPK;
    }

    public void setKPK(String KPK) {
        this.KPK = KPK;
    }

    public String getFood() {
        return Food;
    }

    public void setFood(String food) {
        Food = food;
    }

    public String getFire() {
        return Fire;
    }

    public void setFire(String fire) {
        Fire = fire;
    }

    public String getFemale() {
        return Female;
    }

    public void setFemale(String female) {
        Female = female;
    }

    public String getEmergency() {
        return Emergency;
    }

    public void setEmergency(String emergency) {
        Emergency = emergency;
    }

    public String getClothes() {
        return Clothes;
    }

    public void setClothes(String clothes) {
        Clothes = clothes;
    }

    public String getBooks() {
        return Books;
    }

    public void setBooks(String books) {
        Books = books;
    }

    public String getBlood() {
        return Blood;
    }

    public void setBlood(String blood) {
        Blood = blood;
    }

    public String getBALOCHISTAN() {
        return BALOCHISTAN;
    }

    public void setBALOCHISTAN(String BALOCHISTAN) {
        this.BALOCHISTAN = BALOCHISTAN;
    }
}

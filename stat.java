package com.example.mani.realtim;

import com.google.firebase.database.DatabaseReference;

/**
 * Created by mani on 31/12/2017.
 */

public class stat {


   String KPK;
    String BALOCHISTAN;
    String SINDH;

    String PUNJAB;

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

    public String getBooks() {
        return Books;
    }

    public void setBooks(String books) {
        Books = books;
    }

    public String getClothes() {
        return Clothes;
    }

    public void setClothes(String clothes) {
        Clothes = clothes;
    }

    public String getEmergency() {
        return Emergency;
    }

    public void setEmergency(String emergency) {
        Emergency = emergency;
    }

    public String getFemale() {
        return Female;
    }

    public void setFemale(String female) {
        Female = female;
    }

    public String getFire() {
        return Fire;
    }

    public void setFire(String fire) {
        Fire = fire;
    }

    public String getMale() {
        return Male;
    }

    public void setMale(String male) {
        Male = male;
    }

    public String getMoney() {
        return Money;
    }

    public void setMoney(String money) {
        Money = money;
    }

    public String getFood() {
        return Food;
    }

    public void setFood(String food) {
        Food = food;
    }

    public String getKPK() {
        return KPK;
    }

    public void setKPK(String KPK) {
        this.KPK = KPK;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getPUNJAB() {
        return PUNJAB;
    }

    public void setPUNJAB(String PUNJAB) {
        this.PUNJAB = PUNJAB;
    }

    public String getService() {
        return Service;
    }

    public void setService(String service) {
        Service = service;
    }

    public String getSINDH() {
        return SINDH;

    }

    public void setSINDH(String SINDH) {
        this.SINDH = SINDH;
    }

    String Clothes;
    String Food;
    String Money;
    String Service;
    String Blood;
    String Books;
    String Emergency;
    String Fire;
    String Gender;
    String Male;
    String Female;

}

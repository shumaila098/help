package com.example.mani.realtim;

/**
 * Created by mani on 29/12/2017.
 */

public class datamodel {
    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    int i;
Needy n;
        String name;
        String surname;
    String kohh;
    String dohh;

    String status;

    public Needy getN() {
        return n;
    }

    public void setN(Needy n) {
        this.n = n;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSurnames() {
        return surname;
    }

    public void setSurnames(String surname) {
        this.surname = surname;
    }

    public String getNames() {
        return name;
    }

    public String getDohh() {
        return dohh;
    }

    public void setDohh(String dohh) {
        this.dohh = dohh;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKohh() {
        return kohh;
    }

    public void setKohh(String kohh) {
        this.kohh = kohh;
    }

    public datamodel(int i, Needy n, String name, String surname, String status, String kohh, String dohh) {
           this.n=n;
        this.i=i;
            this.name = name;
        this.surname =surname;
        this.dohh = dohh;
        this.kohh =kohh;
        this.status = status;

        }


}


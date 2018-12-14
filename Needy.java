package com.example.mani.realtim;

/**
 * Created by mani on 17/09/2017.
 */

public class Needy {
    Needy needy;
    String name;
    String age;
    String cnic;
    String contact;
    String dob;
    String gender;
    String id;
    String doh;
    String email;
    String fathername;
    String koh;
    String lat;
    String longi;
    String month;

    String date;
    String nation;
    String postaladdress;
    String quali;
    String surname;
    String totalpics;
    String year;
    String status;
    String code;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getYear() {
        return year;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        date = date;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSurname() {
        return surname;

    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLat() {
        return lat;

    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getDoh() {
        return doh;

    }

    public void setDoh(String doh) {
        this.doh = doh;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLongi() {

        return longi;

    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getNation() {
        return nation;

    }

    public String getPostaladdress() {
        return postaladdress;
    }

    public void setPostaladdress(String postaladdress) {
        this.postaladdress = postaladdress;
    }

    public void setNation(String nation) {

        this.nation = nation;
    }

    public String getQuali() {
        return quali;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setQuali(String quali) {
        this.quali = quali;
    }

    public void setLongi(String longi) {
        this.longi = longi;
    }

    public String getId() {
        return id;

    }

    public String getFathername() {
        return fathername;
    }

    public void setFathername(String fathername) {
        this.fathername = fathername;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getKoh() {
        return koh;
    }

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public void setKoh(String koh) {
        this.koh = koh;
    }


    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getTotalpics() {
        return totalpics;
    }

    public void setTotalpics(String totalpics) {
        this.totalpics = totalpics;
    }


    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Needy getNeedy() {
        return needy;
    }

    public void setNeedy(Needy needy) {
        this.needy = needy;
    }

    public String getCodes() {
        return code;
    }

    public void setCodes(String codes) {
        this.code = codes;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Boolean checkcode(String code)
    {
        if (getCodes().equals(code))
        {
            return true;
        }
        else {
            return false;
        }
    }
}

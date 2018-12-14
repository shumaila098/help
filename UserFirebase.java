package com.example.mani.realtim;

import android.util.Log;
import java.lang.Object;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.ContentValues.TAG;

/**
 * Created by mani on 16/08/2017.
 */

public class UserFirebase {
    private FirebaseDatabase fd = FirebaseDatabase.getInstance();

    private DatabaseReference dr = fd.getReference();

    public static Users users=new Users();
    private DatabaseReference r = dr.child("data");
    private DatabaseReference os = r.child("User");
   public static ArrayList<String> list_of_Needy=new ArrayList<>();;
   private DatabaseReference Needi,TotalNeediesNode,TotalrollnoNode,AgeNode,PointsNode,sa, NameNode, NationNode, SurnameNode, EmailNode, GenderNode, LatNode, LongNode, PostalAddressNode, QualiNode, ContactNode, FatherNameNode, DobNode, CnicNode, ImgPhoneNode, ImgStorNode, MonthNode, DateNode, YearNode;
 private  static    String nameNode= "", nationNode= "", surnameNode= "", emailNode= "", genderNode= "", postalAddressNode= "", qualiNode= "", contactNode= "", fatherNameNode= "", dobNode= "", cnicNode= "", imgPhoneNode= "", imgStorNode = "";
    static   String totolrolno="0", needies="0",pointss="0" , monthNode="0", dateNode="0", yearNode="0",latNode="0", longNode="0",ageNode="0";
    public void firedatabaseinit(String UI) {


        sa = os.child(UI);
        NameNode = sa.child("name");
        Needi = sa.child("Needy");
        AgeNode = sa.child("age");
        SurnameNode = sa.child("surname");
        TotalNeediesNode = sa.child("totalneedy");
        TotalrollnoNode = sa.child("totalrollno");
        EmailNode = sa.child("email");
        GenderNode = sa.child("gender");
        LatNode = sa.child("lat");
        LongNode = sa.child("longi");
        ContactNode = sa.child("contact");
        FatherNameNode = sa.child("fathername");
        PostalAddressNode = sa.child("postaladdress");
        NationNode = sa.child("nation");
        CnicNode = sa.child("cnic");
        DobNode = sa.child("dob");
        QualiNode = sa.child("quali");
        PointsNode = sa.child("points");
        MonthNode = sa.child("month");
        DateNode = sa.child("date");
        YearNode = sa.child("year");

//trying to set the image from filepath

        Needi.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                list_of_Needy = new ArrayList<>();
                for (DataSnapshot child : dataSnapshot.getChildren()) {

                    if (child.exists()) {

                        list_of_Needy.add(child.getKey());


                    }


                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });


        sa.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                users =new Users();
               totolrolno= needies = pointss = monthNode = dateNode = yearNode = latNode = longNode = ageNode = "0";
                nameNode = nationNode = surnameNode = emailNode = genderNode = postalAddressNode = qualiNode = contactNode = fatherNameNode = dobNode = cnicNode = imgPhoneNode = imgStorNode = "";
                if (dataSnapshot.exists() && !dataSnapshot.equals(null)) {
                    users=dataSnapshot.getValue(Users.class);
                    ageNode = dataSnapshot.child("age").getValue(String.class);
                    nameNode = dataSnapshot.child("name").getValue(String.class);
                    pointss = dataSnapshot.child("points").getValue(String.class);
                    qualiNode = dataSnapshot.child("quali").getValue(String.class);
                    dobNode = dataSnapshot.child("dob").getValue(String.class);
                    surnameNode = dataSnapshot.child("surname").getValue(String.class);

                    genderNode = dataSnapshot.child("gender").getValue(String.class);
                    longNode = dataSnapshot.child("longi").getValue(String.class);

                    latNode = dataSnapshot.child("lat").getValue(String.class);
                    totolrolno=dataSnapshot.child("totalrollno").getValue(String.class);
                    dateNode = dataSnapshot.child("date").getValue(String.class);
                    yearNode = dataSnapshot.child("year").getValue(String.class);
                    monthNode = dataSnapshot.child("month").getValue(String.class);
                    postalAddressNode = dataSnapshot.child("postaladdress").getValue(String.class);
                    nationNode = dataSnapshot.child("nation").getValue(String.class);
                    emailNode = dataSnapshot.child("email").getValue(String.class);
                    contactNode = dataSnapshot.child("contact").getValue(String.class);
                    cnicNode = dataSnapshot.child("cnic").getValue(String.class);
                    fatherNameNode = dataSnapshot.child("fathername").getValue(String.class);
                    needies = dataSnapshot.child("totalneedy").getValue(String.class);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
    public String getNameNode()
    {

        return nameNode;
    }
    public String getSurnameNode()
    {

        return surnameNode;
    }
    public String getDobNode()
    {

        return dobNode;
    }
    public int getDateNode()
    {
        int s=Integer.parseInt(dateNode);
        return s;
    }
    public int getYearNode()
    {
        int s=Integer.parseInt(yearNode);
        return s;
    }
    public int getMonthNode()
    {
        int s=Integer.parseInt(monthNode);
        return s;
    }
    public String getPostalAddressNode()
    {

        return postalAddressNode;
    }
    public String getNationNode()
    {

        return nationNode;
    }
    public String getEmailNode()
    {

        return emailNode;
    }
    public String getContactNode()
    {

        return contactNode;
    }
    public String getCnicNode()
    {

        return cnicNode;
    }
    public String getFatherNameNode()
    {

        return fatherNameNode;
    }
    public String getImgPhoneNode()
    {

        return imgPhoneNode;
    }
    public String getImgStorNode()
    {

        return imgStorNode;
    }
    public String getGenderNode()
    {

        return genderNode;
    }
    public ArrayList<String> getneedylist()
    {

        return list_of_Needy;
    }
    public Double getLatNode()
    {
        double d = Double.parseDouble(latNode);
        return d;
    }
    public Double getLongNode()
    {
        double d = Double.parseDouble(longNode);
        return d;
    }
    public String getQualiNode()
    {

        return qualiNode;
    }
    public void setNameNode(String nameNode)
    {
        NameNode.setValue(nameNode);

    }
    public void setSurnameNode(String surnameNode)
    {
        sa.child("surname").setValue(surnameNode);

    }
    public void setDobNode(String dobNode)
    {
        sa.child("dob").setValue(dobNode);

    }
    public void setDateNode(int dateNode)
    {
        String s=String.valueOf(dateNode);

        sa.child("date").setValue(s);
    }
    public void setYearNode(int yearNode)
    {
        String s=String.valueOf(yearNode);
        sa.child("year").setValue(s);

    }
    public void setMonthNode(int monthNode)
    {
        String s=String.valueOf(monthNode);
        sa.child("month").setValue(s);
    }
    public void setPostalAddressNode(String postalAddressNode)
    {
        sa.child("postaladdress").setValue(postalAddressNode);

    }
    public void setNationNode(String nationNode)
    {
        sa.child("nation").setValue(nationNode);

    }
    public void setEmailNode(String emailNode)
    {

        sa.child("email").setValue(emailNode);
    }
    public void setContactNode(String contactNode)
    {
        sa.child("contact").setValue(contactNode);

    }
    public void setCnicNode(String cnicNode)
    {
        sa.child("cnic").setValue(cnicNode);

    }
    public void setFatherNameNode(String fatherNameNode)
    {

        sa.child("fathername").setValue(fatherNameNode);
    }
    public void setImgPhoneNode(String imgPhoneNode)
    {
        sa.child("imagepathinphone").setValue(imgPhoneNode);

    }
    public void setImgStorNode(String imgStorNode)
    {
        sa.child("imagepathinstorage").setValue(imgStorNode);

    }
    public void setGenderNode(String genderNode)
    {

        sa.child("gender").setValue(genderNode);
    }
    public void setLatNode(Double latNodes)
    {
        String s=String.valueOf(latNodes);
        sa.child("lat").setValue(s);

    }
    public void setLongNode(Double longNodea)
    {
        String s=String.valueOf(longNodea);
        sa.child("longi").setValue(s);

    }
    public  void setQualiNode(String qualiNode)
    {


         sa.child("quali").setValue(qualiNode);


    }
    public  void setPointNode(int points) {

          String s=String.valueOf(points);
        sa.child("points").setValue(s);


    }
    public  int getPointsNode()
    {

int s=Integer.parseInt(pointss);
         return s;
    }

    public  void setAgeNode(int y,int m ,int d) {

        Calendar dateOfYourBirth = new GregorianCalendar(y,m, d);
        Calendar today = Calendar.getInstance();
        int yourAge = today.get(Calendar.YEAR) - dateOfYourBirth.get(Calendar.YEAR);
        dateOfYourBirth.add(Calendar.YEAR, yourAge);
        if (today.before(dateOfYourBirth)) {
            yourAge--;
        }

        String s=String.valueOf(yourAge);
        sa.child("age").setValue(s);


    }
    public  int getAgeNode()
    {


        int s=Integer.parseInt(ageNode);
        return s;
    }
    public  void setTotalNeediesNode(int points) {


        String s=String.valueOf(points);
        sa.child("totalneedy").setValue(s);


    }
    public  int getTotalNeediesNode()
    {


        int s=Integer.parseInt(needies);
        return s;
    }
    public  void setTotalrollnoNode(int points) {


        String s=String.valueOf(points);
        sa.child("totalrollno").setValue(s);


    }
    public  int getTotalrollnoNode()
    {


        int s=Integer.parseInt(totolrolno);
        return s;
    }

    public Users getUsers()
    {



        return users;
    }

}

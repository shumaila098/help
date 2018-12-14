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

public class NeedyFirebasetwo {
    private FirebaseDatabase fd = FirebaseDatabase.getInstance();

    private DatabaseReference dr = fd.getReference();

    private DatabaseReference r = dr.child("data");
    //private DatabaseReference ne= r.child("Locations");


    static int pro=0;



    DatabaseReference StatusNode,needyloc,needylat,needylon,needyid,IdNode,TotalpicNode,saaas,saas,AgeNode,KohNode,DohNode,CodeNode,sa, NameNode, NationNode, SurnameNode, EmailNode, GenderNode, LatNode, LongNode, PostalAddressNode, QualiNode, ContactNode, FatherNameNode, DobNode, CnicNode, ImgPhoneNode, ImgStorNode, MonthNode, DateNode, YearNode;
    String id,kohNode,dohNode,codeNode ,nameNode, nationNode, surnameNode, emailNode, genderNode , postalAddressNode, qualiNode, contactNode, fatherNameNode, dobNode, cnicNode, imgPhoneNode,imgStorNode = "";
    String statusNode, ni,monthNode, dateNode, yearNode,latNode, longNode,ageNode,totalpic="0";
    LocationFirebase apa=new LocationFirebase();

    public void firedatabaseinit(String NI) {
        statusNode=monthNode=dateNode= yearNode=latNode= longNode=ageNode=totalpic="0";
        kohNode=dohNode=codeNode =nameNode=nationNode=surnameNode= emailNode= genderNode = postalAddressNode=qualiNode= contactNode= fatherNameNode= dobNode=cnicNode= imgPhoneNode=imgStorNode = "";

        saaas=r.child("Needy");
        sa=saaas.child(NI);

ni=NI;
        apa.firedatabaseinit(NI);

        NameNode = sa.child("name");
        AgeNode = sa.child("age");
        IdNode = sa.child("id");

        SurnameNode = sa.child("surname");
        EmailNode = sa.child("email");
        GenderNode = sa.child("gender");
        TotalpicNode = sa.child("totalpics");
        LatNode = sa.child("lat");
        LongNode = sa.child("longi");
        ContactNode = sa.child("contact");
        FatherNameNode = sa.child("fathername");
        PostalAddressNode = sa.child("postaladdress");
        NationNode = sa.child("nation");
        CnicNode = sa.child("cnic");
        DobNode = sa.child("dob");
        QualiNode = sa.child("quali");
        KohNode = sa.child("koh");
        DohNode = sa.child("doh");
        CodeNode = sa.child("code");
        ImgPhoneNode = sa.child("imagepathinphone");
        ImgStorNode = sa.child("imagepathinstorage");
        MonthNode = sa.child("month");
        DateNode = sa.child("date");
        YearNode = sa.child("year");
        StatusNode = sa.child("status");
//trying to set the image from filepath
        IdNode.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                if(dataSnapshot.exists()&&!dataSnapshot.equals(null))
                    id = dataSnapshot.getValue(String.class);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        NameNode.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                if(dataSnapshot.exists()&&!dataSnapshot.equals(null))
                    nameNode = dataSnapshot.getValue(String.class);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        TotalpicNode.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                if(dataSnapshot.exists()&&!dataSnapshot.equals(null))
                    totalpic = dataSnapshot.getValue(String.class);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        KohNode.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                if(dataSnapshot.exists()&&!dataSnapshot.equals(null))
                    kohNode = dataSnapshot.getValue(String.class);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        DohNode.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                if(dataSnapshot.exists()&&!dataSnapshot.equals(null))
                    dohNode = dataSnapshot.getValue(String.class);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        CodeNode.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                if(dataSnapshot.exists()&&!dataSnapshot.equals(null))
                    codeNode = dataSnapshot.getValue(String.class);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        AgeNode.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                if(dataSnapshot.exists()&&!dataSnapshot.equals(null))
                    ageNode = dataSnapshot.getValue(String.class);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        QualiNode.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                if(dataSnapshot.exists()&&!dataSnapshot.equals(null))
                    qualiNode = dataSnapshot.getValue(String.class);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        LatNode.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                if(dataSnapshot.exists()&&!dataSnapshot.equals(null))
                    latNode = dataSnapshot.getValue(String.class);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        LongNode.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                if(dataSnapshot.exists()&&!dataSnapshot.equals(null))
                    longNode = dataSnapshot.getValue(String.class);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        GenderNode.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value avvnd again
                // whenever data at this location is updated.
                if(dataSnapshot.exists()&&!dataSnapshot.equals(null))
                    genderNode = dataSnapshot.getValue(String.class);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        ImgStorNode.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                if(dataSnapshot.exists()&&!dataSnapshot.equals(null))
                    imgStorNode = dataSnapshot.getValue(String.class);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        ImgPhoneNode.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                if(dataSnapshot.exists()&&!dataSnapshot.equals(null))
                    imgPhoneNode = dataSnapshot.getValue(String.class);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        FatherNameNode.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                if(dataSnapshot.exists()&&!dataSnapshot.equals(null))
                    fatherNameNode = dataSnapshot.getValue(String.class);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        CnicNode.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                if(dataSnapshot.exists()&&!dataSnapshot.equals(null))
                    cnicNode = dataSnapshot.getValue(String.class);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        ContactNode.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                if(dataSnapshot.exists()&&!dataSnapshot.equals(null))
                    contactNode = dataSnapshot.getValue(String.class);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        EmailNode.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                if(dataSnapshot.exists()&&!dataSnapshot.equals(null))
                    emailNode = dataSnapshot.getValue(String.class);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        NationNode.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                if(dataSnapshot.exists()&&!dataSnapshot.equals(null))
                    nationNode = dataSnapshot.getValue(String.class);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        PostalAddressNode.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                if(dataSnapshot.exists()&&!dataSnapshot.equals(null))
                    postalAddressNode = dataSnapshot.getValue(String.class);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        StatusNode.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                if(dataSnapshot.exists()&&!dataSnapshot.equals(null))
                    postalAddressNode = dataSnapshot.getValue(String.class);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        MonthNode.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                if(dataSnapshot.exists()&&!dataSnapshot.equals(null))
                    monthNode = dataSnapshot.getValue(String.class);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        YearNode.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                if(dataSnapshot.exists()&&!dataSnapshot.equals(null))
                    yearNode = dataSnapshot.getValue(String.class);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        DateNode.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                if(dataSnapshot.exists()&&!dataSnapshot.equals(null))
                    dateNode = dataSnapshot.getValue(String.class);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        SurnameNode.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                if(dataSnapshot.exists()&&!dataSnapshot.equals(null))
                    surnameNode = dataSnapshot.getValue(String.class);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        DobNode.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                if(dataSnapshot.exists()&&!dataSnapshot.equals(null))
                    dobNode = dataSnapshot.getValue(String.class);

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
        sa.child("name").setValue(nameNode);

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
    public void setstatusNode(int dateNode)
    {
        String s=String.valueOf(dateNode);

        sa.child("status").setValue(s);
        apa.setstatusNode(s);
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
        apa.setLatNode(latNodes);


    }
    public void setLongNode(Double longNodea)
    {
        String s=String.valueOf(longNodea);
        sa.child("longi").setValue(s);
       apa.setLongNode(longNodea);

    }
    public  void setQualiNode(String qualiNode)
    {


        sa.child("quali").setValue(qualiNode);


    }


    public  void setAgeNode(int y,int m ,int d) {

        Calendar dateOfYourBirth = new GregorianCalendar(y, m,d);
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
    public  void setDohNode(String points) {


        sa.child("doh").setValue(points);


    }
    public  String getDohNode()
    {


        return dohNode;
    }

    public  void setKohNode(String points) {


        sa.child("koh").setValue(points);


    }
    public  String getKohNode()
    {


        return kohNode;
    }
    public  void setCodeNode(String points) {


        sa.child("code").setValue(points);


    }
    public  String getCodeNode()
    {


        return codeNode;
    }

    public  String getid()
    {


        return id;
    }
    public int getTotalpicNode()
    {
        int s=Integer.parseInt(totalpic);
        return s;
    }
    public int getstatusnode()
    {
        int s=Integer.parseInt(statusNode);
        return s;
    }
    public void setTotalpicNode(int monthNode)
    {
        String s=String.valueOf(monthNode);
        sa.child("totalpics").setValue(s);
    }
    public void submit()
    {
        sa.child("id").setValue(ni);
    }


}

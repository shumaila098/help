package com.example.mani.realtim;


import android.content.Context;
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



    /**
     * Created by mani on 16/08/2017.
     */

    public class StatisticsCalculationfirebase{
        Context con;
        private FirebaseDatabase fd = FirebaseDatabase.getInstance();
public  static ArrayList<countryvalues> country;
        private DatabaseReference dr = fd.getReference();
Context c;
        private DatabaseReference r = dr.child("data");
        private DatabaseReference os = r.child("Statistics");

       // private DatabaseReference provinces = os.child("Provinces");

        private DatabaseReference KPK =os.child("KPK");
        private DatabaseReference BALOCHISTAN = os.child("BALOCHISTAN");
        private DatabaseReference SINDH =os.child("SINDH");

        private DatabaseReference PUNJAB =os.child("PUNJAB");


      //  private DatabaseReference helptype = os.child("Helptype");
        private DatabaseReference clothes = os.child("Clothes");
        private DatabaseReference food = os.child("Food");
        private DatabaseReference money = os.child("Money");
        private DatabaseReference  service =os.child("Service");
        private DatabaseReference blood = os.child("Blood");
        private DatabaseReference books =os.child("Books");
        private DatabaseReference emergency = os.child("Emergency");
        private DatabaseReference fire = os.child("Fire");
       // private DatabaseReference gend = helptype.child("Gender");
        private DatabaseReference male = os.child("Male");
        private DatabaseReference female = os.child("Female");
      //  static  boolean malenob=false, femalenob=false,clothessb=false, foodsb=false,moneysb=false, servicesb=false,bloodsb=false,booksb=false,emergencysb=false,firesb=false,punjabb=false,kpkb=false,balochistanb=false,sindhb=false ;
        static   String maleno="0", femaleno="0",clothess="0" , foods="0",moneys="0", services="0",bloods="0",bookss="0",emergencys="0",fires="0" ,punjab="0",kpk= "0",balochistan= "0",sindh = "0";
        public void firedatabaseinit(Context cont) {

                 con=cont;
                   getenter();
//trying to set the image from filepath


            os.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.

                    if (dataSnapshot.exists() && !dataSnapshot.equals(null)) {
                        if(dataSnapshot.getValue(Statistics.class).getFemale()!=null)
                        femaleno= dataSnapshot.getValue(Statistics.class).getFemale();
                        if(dataSnapshot.getValue(Statistics.class).getMale()!=null)
                        maleno= dataSnapshot.getValue(Statistics.class).getMale();
                        if(dataSnapshot.getValue(Statistics.class).getKPK()!=null)
                        kpk= dataSnapshot.getValue(Statistics.class).getKPK();
                        if(dataSnapshot.getValue(Statistics.class).getBALOCHISTAN()!=null)
                        balochistan= dataSnapshot.getValue(Statistics.class).getBALOCHISTAN();
                        if(dataSnapshot.getValue(Statistics.class).getPUNJAB()!=null)
                        punjab= dataSnapshot.getValue(Statistics.class).getPUNJAB();
                        if(dataSnapshot.getValue(Statistics.class).getSINDH()!=null)
                        sindh= dataSnapshot.getValue(Statistics.class).getSINDH();

                        if(dataSnapshot.getValue(Statistics.class).getService()!=null)
                        services = dataSnapshot.getValue(Statistics.class).getService();
                        if(dataSnapshot.getValue(Statistics.class).getClothes()!=null)
                        clothess = dataSnapshot.getValue(Statistics.class).getClothes();
                        if(dataSnapshot.getValue(Statistics.class).getMoney()!=null)
                        moneys = dataSnapshot.getValue(Statistics.class).getMoney();

                        if(dataSnapshot.getValue(Statistics.class).getEmergency()!=null)
                        emergencys = dataSnapshot.getValue(Statistics.class).getEmergency();
                        if(dataSnapshot.getValue(Statistics.class).getBooks()!=null)
                        bookss = dataSnapshot.getValue(Statistics.class).getBooks();

                        if(dataSnapshot.getValue(Statistics.class).getBlood()!=null)
                        bloods = dataSnapshot.getValue(Statistics.class).getBlood();
                        if(dataSnapshot.getValue(Statistics.class).getFood()!=null)
                        foods = dataSnapshot.getValue(Statistics.class).getFood();
                        if(dataSnapshot.getValue(Statistics.class).getFire()!=null)
                        fires = dataSnapshot.getValue(Statistics.class).getFire();

                        enter(kpk,balochistan,sindh,punjab,clothess,foods,moneys,services,bloods,bookss,emergencys,fires);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }


            });




            }










        public  void addmale() {

                   int m = Integer.parseInt(maleno);
                   m++;
                   String S = String.valueOf(m);
                   male.setValue(S);


        }

        public  void addfemale() {

          int m = Integer.parseInt(femaleno);
          m++;
          String S = String.valueOf(m);
          female.setValue(S);

        }
        public  void addmoney() {

           int m=Integer.parseInt(moneys);
            m++;
            String S=String.valueOf(m);
            money.setValue(S);

        }
        public  void addsindh() {
            int m=Integer.parseInt(sindh);
            m++;
            String S=String.valueOf(m);
           SINDH.setValue(S);

        }
        public  void addpunjub() {
 int m=Integer.parseInt(punjab);
            m++;
            String S=String.valueOf(m);
           PUNJAB.setValue(S);

        }
        public  void addbaloch() {

            int m=Integer.parseInt(balochistan);
            m++;
            String S=String.valueOf(m);
           BALOCHISTAN.setValue(S);

        }
        public  void addkpk() {
int m=Integer.parseInt(kpk);
            m++;
            String S=String.valueOf(m);
            KPK.setValue(S);

        }

        public  void addservice() {

 int m=Integer.parseInt(services);
            m++;
            String S=String.valueOf(m);
           service.setValue(S);


        }
        public  void addclothes() {


              int m=Integer.parseInt(clothess);
            m++;
            String S=String.valueOf(m);
           clothes.setValue(S);

        }
        public  void addblood() {

int m=Integer.parseInt(bloods);
            m++;
            String S=String.valueOf(m);
            blood.setValue(S);


        }
        public  void addbook() {


            int m=Integer.parseInt(bookss);
            m++;
            String S=String.valueOf(m);
           books.setValue(S);


        }
        public  void addemergency() {
            int m=Integer.parseInt(emergencys);
            m++;
            String S=String.valueOf(m);
            emergency.setValue(S);


        }
        public  void addfood() {



        int m=Integer.parseInt(foods);
            m++;
            String S=String.valueOf(m);
            food.setValue(S);


        }

        public  void addfire() {


    int m = Integer.parseInt(fires);
    m++;
    String S = String.valueOf(m);
    fire.setValue(S);

}

        public  String getmale() {


return maleno;

        }
        public  String getkpk() {


            return kpk;

        }
        public  String getsindh() {


            return sindh;

        }
        public  String getpunjab() {


            return punjab;

        }
        public  String getBalochistan() {


            return balochistan;

        }
        public  String getfemale() {

return femaleno;
        }
        public  String getmoney() {

return  moneys;
        }
        public  String getservice() {


return services;

        }
        public  String getclothes() {

return clothess;
        }
        public  String getblood() {

return bloods;

        }
        public  String getbook() {


return bookss;

        }
        public  ArrayList<countryvalues> getcountrydata() {


            return country;
        }
        public  String getemergency() {


return emergencys;
        }
        public  String getfood() {


          ;
             return foods;

        }
        public String getfire() {


          return fires;


        }

        public  void submale() {

            int m = Integer.parseInt(maleno);
            m--;
            String S = String.valueOf(m);
            male.setValue(S);


        }

        public  void subfemale() {

            int m = Integer.parseInt(femaleno);
            m--;
            String S = String.valueOf(m);
            female.setValue(S);

        }
        public  void submoney() {

            int m=Integer.parseInt(moneys);
            m--;
            String S=String.valueOf(m);
            money.setValue(S);

        }
        public  void subsindh() {
            int m=Integer.parseInt(sindh);
            m--;
            String S=String.valueOf(m);
            SINDH.setValue(S);

        }
        public  void subpunjub() {
            int m=Integer.parseInt(punjab);
            m--;
            String S=String.valueOf(m);
            PUNJAB.setValue(S);

        }
        public  void subbaloch() {

            int m=Integer.parseInt(balochistan);
            m--;
            String S=String.valueOf(m);
            BALOCHISTAN.setValue(S);

        }
        public  void subkpk() {
            int m=Integer.parseInt(kpk);
            m--;
            String S=String.valueOf(m);
            KPK.setValue(S);

        }

        public  void subservice() {

            int m=Integer.parseInt(services);
            m--;
            String S=String.valueOf(m);
            service.setValue(S);


        }
        public  void subclothes() {


            int m=Integer.parseInt(clothess);
            m--;
            String S=String.valueOf(m);
            clothes.setValue(S);

        }
        public  void subblood() {

            int m=Integer.parseInt(bloods);
            m--;
            String S=String.valueOf(m);
            blood.setValue(S);


        }
        public  void subbook() {


            int m=Integer.parseInt(bookss);
            m--;
            String S=String.valueOf(m);
            books.setValue(S);


        }
        public  void subemergency() {
            int m=Integer.parseInt(emergencys);
            m--;
            String S=String.valueOf(m);
            emergency.setValue(S);


        }
        public  void subfood() {



            int m=Integer.parseInt(foods);
            m--;
            String S=String.valueOf(m);
            food.setValue(S);


        }

        public  void subfire() {


            int m = Integer.parseInt(fires);
            m--;
            String S = String.valueOf(m);
            fire.setValue(S);

        }
        void getenter(){
           Statistics toret=new Statistics();
            int ui =5;
            try {

                Database2 db=new Database2(con);
                db.open();

                if (db.checkentry())
                {  toret = db.getEmail();

                        femaleno= toret.getFemale();

                        maleno= toret.getMale();

                        kpk= toret.getKPK();

                        balochistan= toret.getBALOCHISTAN();

                        punjab= toret.getPUNJAB();

                        sindh= toret.getSINDH();


                        services = toret.getService();

                        clothess = toret.getClothes();

                        moneys =toret.getMoney();


                        emergencys = toret.getEmergency();

                        bookss =toret.getBooks();


                        bloods =toret.getBlood();

                        foods = toret.getFood();

                        fires = toret.getFire();


                }
                else {


                }
                db.close();


            }
            catch(Exception e) {


            }

        }
        void enter(String KPKs,String BALOCHISTANs,String SINDHs,String PUNJABs,String Clothess,String Foods,String Moneys,String Servicess,String Bloods,String Bookss,String Emergencys,String Fires){

            try {

                Database2 db = new Database2(con);

                db.open();

                if (db.checkentry()) {
                    db.deleteentry();

                }
                db.createentry(KPKs,BALOCHISTANs,SINDHs,PUNJABs,Clothess,Foods,Moneys,Servicess,Bloods,Bookss,Emergencys,Fires);

                db.close();

            }

            catch(Exception e){

            }
        }


    }

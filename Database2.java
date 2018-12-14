package com.example.mani.realtim;

/**
 * Created by mani on 25/11/2018.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by DeveloperQueen on 21/01/2016.
 */
public class Database2 {

    public static final  String KPK= "KPK";
    public static final String BALOCHISTAN = "BALOCHISTAN";
    public static final  String SINDH= "SINDH";
    public static final  String PUNJAB= "PUNJAB";
    public static final String Clothes= "Clothes";
    public static final  String Food= "Food";
    public static final  String Money= "Money";
    public static final String Services= "Services";
    public static final  String Blood= "Blood";
    public static final  String Books= "Books";
    public static final  String Emergency= "Emergency";
    public static final  String Fire= "Fire";
    // private DatabaseReference gend = helptype.child("Gender");
    private  String Male;
    private   String Female;

    private static final String Table_name = "Table_name";
    private static final String db_name = "db_name";
    private static final int Dataversion = 1;
    private db ourhelp;
    private SQLiteDatabase sqlvar;
    private final Context cons;



    private static class db extends SQLiteOpenHelper {


        public db(Context x) {
            super(x, db_name, null, Dataversion);
        }


        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + Table_name + "("
                    +KPK + " TEXT , " +
                    BALOCHISTAN+ " TEXT, " +
                    SINDH + " TEXT , " +
                    PUNJAB+ " TEXT, " +
                   Clothes + " TEXT , " +
                   Food+ " TEXT, " +
                  Money+ " TEXT , " +
                  Services+ " TEXT, " +
                  Blood+ " TEXT , " +
                  Books+ " TEXT, " +
                 Emergency + " TEXT , " +
                  Fire+ " TEXT );"

            );
        }



        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXIST " +Table_name);
            onCreate(db);


        }

    }
    public Database2( Context c) {
        cons = c;
    }

    public Database2 open(){
        ourhelp=new db(cons);
        sqlvar=ourhelp.getWritableDatabase();
        return this;
    }
    public void close(){
        ourhelp.close();
    }

    public long createentry(String KPKs,String BALOCHISTANs,String SINDHs,String PUNJABs,String Clothess,String Foods,String Moneys,String Servicess,String Bloods,String Bookss,String Emergencys,String Fires){
        ContentValues cv=new ContentValues();
        cv.put(KPK,KPKs);
        cv.put(BALOCHISTAN,BALOCHISTANs);
        cv.put(SINDH,SINDHs);
        cv.put(PUNJAB,PUNJABs);
        cv.put(Clothes,Clothess);
        cv.put(Food,Foods);
        cv.put(Money,Moneys);
        cv.put(Services,Servicess);
        cv.put(Blood,Bloods);
        cv.put(Books,Bookss);
        cv.put(Emergency,Emergencys);
        cv.put(Fire,Fires);
        return sqlvar.insert(Table_name,null,cv);
    }

    public Statistics getEmail(){
        String[] columns=new String[]{ KPK,BALOCHISTAN,SINDH,PUNJAB,Clothes,Food,Money,Services,Blood,Books,Emergency,Fire};
        Cursor c=sqlvar.query(Table_name,columns,null,null,null,null,null);
        if(c!=null&&c.moveToFirst()){
            c.moveToFirst();
            Statistics ok=new Statistics();
            ok.setKPK(c.getString(0));
            ok.setBALOCHISTAN(c.getString(1));
            ok.setSINDH(c.getString(2));
            ok.setPUNJAB(c.getString(3));
            ok.setClothes(c.getString(4));
ok.setFood(c.getString(5));
            ok.setMoney(c.getString(6));
            ok.setService(c.getString(7));
            ok.setBlood(c.getString(8));
            ok.setBooks(c.getString(9));
            ok.setEmergency(c.getString(10));
            ok.setFire(c.getString(11));
            return ok;
        }return null;
    }




    public boolean checkentry(){
        String[] columns=new String[]{KPK};
        Cursor c=sqlvar.query(Table_name,columns,null,null,null,null,null);
        if(c!=null&&c.moveToFirst()){
            c.moveToFirst();

            return true ;
        }
        return false;

    }
    public void deleteentry(){
        sqlvar.delete(Table_name,null,null);
    }
}
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
public class DataBase {
    public static final String email = "email";

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

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + Table_name + "("

                    + email + " TEXT );"

            );
        }



        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXIST " +Table_name);
            onCreate(db);


        }

    }
    public DataBase( Context c) {
        cons = c;
    }

    public DataBase open(){
        ourhelp=new db(cons);
        sqlvar=ourhelp.getWritableDatabase();
        return this;
    }
    public void close(){
        ourhelp.close();
    }

    public long createentry(String emails){
        ContentValues cv=new ContentValues();
        cv.put(email,emails);

        return sqlvar.insert(Table_name,null,cv);
    }
    public String getEmail(){
        String[] columns=new String[]{email};
        Cursor c=sqlvar.query(Table_name,columns,null,null,null,null,null);
        if(c!=null&&c.moveToFirst()){
            c.moveToFirst();
            String message=c.getString(0);
            return message;
        }return null;
    }




    public boolean checkentry(){
        String[] columns=new String[]{email};
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
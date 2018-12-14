package com.example.mani.realtim;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static android.content.ContentValues.TAG;

/**
 * Created by mani on 22/08/2017.
 */

public class Needies extends Activity implements View.OnClickListener{
ListView list;
    FirebaseUser a;
    CustomDialogClass cdd;
    private FirebaseDatabase fd = FirebaseDatabase.getInstance();
    private DatabaseReference dr = fd.getReference();
ArrayList<String> wes;
    ArrayList<String> helptype;
    ArrayList<String> gender;
    ArrayList<String> province;
 public static ArrayList<String> we=new ArrayList<>();;
    DatabaseReference Needi,sa;

public static Needy passobj=new Needy();
    String name,surname,help,prov,gend;
    private DatabaseReference r = dr.child("data");
    private DatabaseReference saaas=r.child("Needy");
    private DatabaseReference xsa;
    private DatabaseReference os = r.child("User");
    private DatabaseReference loc = r.child("Locations");
    private DatabaseReference locus=loc.child("Needy");

    private DatabaseReference locuser;
    UserFirebase s;
    ArrayList<Needy> Needyobj=new ArrayList<>();
    String ui;
        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.needylist);
            list=(ListView)findViewById(R.id.list);
            a= FirebaseAuth.getInstance().getCurrentUser();
            if(a!=null){
           s=new UserFirebase();
            s.firedatabaseinit(a.getUid());
             sa = os.child(a.getUid());
            Needi=sa.child("Needy");

            ui=a.getUid();
           // locuser = locus.child(a.getUid());
           Needi=sa.child("Needy");
            cdd= new CustomDialogClass(Needies.this);
            cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


          wes=new ArrayList<>();

                helptype=new ArrayList<>();
                gender=new ArrayList<>();
                province=new ArrayList<>();
            wes=s.getneedylist();
            saaas.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    we=new ArrayList<>();
                    if(dataSnapshot.exists()&&!dataSnapshot.equals(null))
                    {
                    Needyobj=new ArrayList<>();

                        wes=s.getneedylist();
                        for (String e:wes) {

                            name = dataSnapshot.child(e).getValue(Needy.class).getName();
                            surname = dataSnapshot.child(e).getValue(Needy.class).getSurname();
                            prov= dataSnapshot.child(e).getValue(Needy.class).getNation();
                            gend= dataSnapshot.child(e).getValue(Needy.class).getGender();
                            help = dataSnapshot.child(e).getValue(Needy.class).getKoh();

                            Needy ue = dataSnapshot.child(e).getValue(Needy.class);
                            Needyobj.add(ue);
                          province.add(prov);
                            gender.add(gend);
                            helptype.add(help);
                            we.add(name + " " + surname);
                        }



                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(Needies.this, R.layout.listw, we);


                    list.setAdapter(adapter);

                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w(TAG, "Failed to read value.", error.toException());
                }
            });








            list.setOnItemClickListener(new OnItemClickListener() {


                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    String ind=wes.get(position);

                    UpdateNeedyperson.pre_up_str=ind;
                        passobj=Needyobj.get(position);

                    startActivity(new Intent(Needies.this,UpdateNeedyperson.class));

                }


            });
           list.setOnItemLongClickListener(new OnItemLongClickListener() {
               @Override
               public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                              final int pos, long id) {


                   runOnUiThread(new Runnable() {
                       public void run() {
                           cdd.show();
                       }
                   });

                   //dfg();
                   Timer timer = new Timer();
                   timer.schedule(new TimerTask() {
                       public void run() {
                           if (cdd.pl()) {




                               String ind=wes.get(pos);

                               StatisticsCalculationfirebase fr = new StatisticsCalculationfirebase();
                               fr.firedatabaseinit(getApplicationContext());
                               cdd.plae();
                               cdd.dismiss();
                               String ert=wes.get(pos).toString();
                               wes.remove(pos);
                               String gend=gender.get(pos).toString();
                                       String prov=province.get(pos).toString();
                                       String  kohhh=helptype.get(pos).toString();

                               if (gend == "male") {
                                   fr.submale();
                               }
                               if (gend == "female") {
                                   fr.subfemale();
                               }

                               if (prov == "Kpk") {
                                   fr.subkpk();
                               }
                               if (prov == "Punjab") {
                                   fr.subpunjub();
                               }
                               if (prov == "Balochistan") {
                                   fr.subbaloch();
                               }
                               if (prov == "Sindh") {
                                   fr.subsindh();
                               }

                               if (kohhh == "Food") {
                                   fr.subfood();
                               }
                               if (kohhh == "Emergency") {
                                   fr.subemergency();
                               }
                               if (kohhh == "Fire") {
                                   fr.subfire();
                               }
                               if (kohhh == "Book") {
                                   fr.subbook();
                               }
                               if (kohhh == "Money") {
                                   fr.submoney();
                               }
                               if (kohhh == "Clothes") {
                                   fr.subclothes();
                               }
                               if (kohhh == "Blood") {
                                   fr.subblood();
                               }
                               if (kohhh == "Service") {

                                   fr.subservice();
                               }
                               locus.child(ert).removeValue();

                               Needi.child(ert).removeValue();
                               saaas.child(ert).removeValue();

                               s.setTotalNeediesNode(s.getTotalNeediesNode()-1);

                               }


                           }

                   }, 0, 500);






                   return true;
               }
           });
        }
            else{
                startActivity(new Intent(this, Loggin.class));
            }



        }

    @Override
    public void onClick(View v) {

    }
    public void showmessage(String v) {
        Toast.makeText(this,v,Toast.LENGTH_SHORT).show();
    }
}

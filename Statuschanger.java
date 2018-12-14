package com.example.mani.realtim;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by mani on 23/08/2017.


 import android.app.Activity;
 import android.app.Dialog;
 import android.os.Bundle;
 import android.view.View;
 import android.view.Window;
 import android.widget.Button;
 import android.widget.EditText;
 import android.widget.TextView;

 /**
 * Created by mani on 1/03/2016.
 */
class StatusChanger extends Dialog implements
        android.view.View.OnClickListener {
UserFirebase uf;
    public Activity c;
    public Dialog d;
    public Button yes, no;
    public Button one,two,zero;
    public TextView psss;
    int st=0;
    private FirebaseAuth mAuth;
    public static FirebaseUser user;
    FirebaseUser us;
    public String playnm;
    public String warn;
    public Boolean fl=false;
    public Boolean sl=true;
    public Boolean f2=false;
    public StatusChanger(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.statuschang);
        one = (Button) findViewById(R.id.one);

        two= (Button) findViewById(R.id.two);
        zero = (Button) findViewById(R.id.zero);
        no = (Button) findViewById(R.id.btn_no);
        psss = (TextView) findViewById(R.id.tView);

        no.setOnClickListener(this);
        zero.setOnClickListener(this);
        one.setOnClickListener(this);
        two.setOnClickListener(this);

        fl=false;

        warn="Change status to";
        psss.setText(warn);
        uf=new UserFirebase();

        mAuth = FirebaseAuth.getInstance();
        user=mAuth.getCurrentUser();
        if(user!=null) {
            uf.firedatabaseinit(user.getUid());

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.zero:
                fl = true;
             st=0;


                //  c.finish();
                break;
            case R.id.one:
                fl = true;
                f2 = true;
st=1;
               int po= uf.getPointsNode();
                po++;
                uf.setPointNode(po);
                break;
            case R.id.two:
                fl = true;
                f2 = true;
                st=2;
                int poo= uf.getPointsNode();
                poo++;
                uf.setPointNode(poo);
                break;

            case R.id.btn_no:
                dismiss();
                fl = true;
                f2 = false;
                break;
            default:
                break;
        }

    }
    public  Boolean pls(){
        return f2;
    }
    public  Boolean pl(){
        return fl;
    }
    public  void plae(){
        fl=false;
    }
    public  int getstatus(){
        return st;
    }


}

package com.example.mani.realtim;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by mani on 21/09/2017.
 */

class Codeshow extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button  no;
    public TextView psss;

    public String playnm;
    public String warn;
    public Boolean fl=false;
    public Boolean sl=true;
TextView w;
    public Codeshow(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.codeshoe);


        psss = (TextView) findViewById(R.id.tView);
        w = (TextView) findViewById(R.id.code);
        no = (Button) findViewById(R.id.btn_no);

        no.setOnClickListener(this);
        fl=false;

        warn="Code for validation";
        psss.setText(warn);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_no:
                dismiss();
                break;
            default:
                break;
        }

    }
    public  Boolean pl(){
        return fl;
    }
    public  void setcode(String e){


        w.setText(e);
    }



}

package com.example.mani.realtim;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

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
class CustomDialogClass extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button yes, no;
    public TextView psss;

    public String playnm;
    public String warn;
    public Boolean fl=false;
    public Boolean sl=true;

    public CustomDialogClass(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        yes = (Button) findViewById(R.id.btn_yes);

        psss = (TextView) findViewById(R.id.tView);
        no = (Button) findViewById(R.id.btn_no);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);
        fl=false;

        warn="Delete this Needy person Data";
        psss.setText(warn);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_yes:
                fl = true;



                //  c.finish();
                break;
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
    public  void plae(){
         fl=false;
    }



}

package com.example.mani.realtim;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by mani on 23/12/2017.
 */

public class points extends AppCompatActivity implements View.OnClickListener {
    TextView textView;
    Button buypoints;
    Button sellpoints;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.points);
        FirebaseUser us;
        us= FirebaseAuth.getInstance().getCurrentUser();

        if(us!=null){
            UserFirebase a = new UserFirebase();
            a.firedatabaseinit(us.getUid());




            buypoints= (Button) findViewById(R.id.buypoints);
            sellpoints= (Button) findViewById(R.id.sellpoints);
            textView= (TextView) findViewById(R.id.points);
            GetcordinatesFirebae t=new GetcordinatesFirebae();
            t.firedatabaseinit();

            buypoints.setOnClickListener(this);
            sellpoints.setOnClickListener(this);
            MyLocation myLocation;
            myLocation=new MyLocation();

            myLocation.MyLocations(this);}
        else{
            startActivity(new Intent(this, Loggin.class));
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {

            case R.id.profile:

                startActivity(new Intent(this,Userdata.class));


                return true;
            case R.id.logout:
                FirebaseAuth fAuth = FirebaseAuth.getInstance();
                fAuth.signOut();
                startActivity(new Intent(this,Loggin.class));


                return true;
            case R.id.points:
                startActivity(new Intent(this,Loggin.class));


                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
        startActivity(intent);
        finish();
        System.exit(0);
    }
    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.buypoints: {




            }
            break;

            case R.id.sellpoints: {




            }
            break;

        }

    }

}

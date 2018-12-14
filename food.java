package com.example.mani.realtim;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by mani on 24/12/2017.
 */

public class food  extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food);
        FirebaseUser us;
        us= FirebaseAuth.getInstance().getCurrentUser();

        if(us!=null){
            UserFirebase a = new UserFirebase();
            a.firedatabaseinit(us.getUid());

            GetcordinatesFirebae t=new GetcordinatesFirebae();
            t.firedatabaseinit();


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

                startActivity(new Intent(this, Userdata.class));


                return true;
            case R.id.logout:
                FirebaseAuth fAuth = FirebaseAuth.getInstance();
                fAuth.signOut();
                startActivity(new Intent(this, Loggin.class));


                return true;
            case R.id.points:
                startActivity(new Intent(this, Loggin.class));


                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {

    }
}


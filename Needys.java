package com.example.mani.realtim;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.content.ContentValues.TAG;

/**
 * Created by mani on 11/08/2017.
 */

public class Needys extends AppCompatActivity implements View.OnClickListener{

    Button addneedyperson;
    Button updateneedyperson;
    Button shareneedy;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.needys);
        FirebaseUser us;
        us=FirebaseAuth.getInstance().getCurrentUser();

        if(us!=null){
            UserFirebase a = new UserFirebase();
            a.firedatabaseinit(us.getUid());





            shareneedy = (Button) findViewById(R.id.shareneedy);
            GetcordinatesFirebae t=new GetcordinatesFirebae();
            t.firedatabaseinit();

            shareneedy.setOnClickListener(this);
            addneedyperson= (Button) findViewById(R.id.addneedy);
            updateneedyperson = (Button) findViewById(R.id.updateneedy);
            updateneedyperson.setOnClickListener(this);
            addneedyperson.setOnClickListener(this);
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





        }

    }
}
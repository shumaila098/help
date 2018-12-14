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

public class needhelp extends AppCompatActivity implements View.OnClickListener{

    Button service;
    Button book;
    Button emergency;
    Button food;
    Button clothes;
    Button money;
    Button fire;
    Button blood;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.needhelp);
        FirebaseUser us;
        us=FirebaseAuth.getInstance().getCurrentUser();

        if(us!=null){
            UserFirebase a = new UserFirebase();
            a.firedatabaseinit(us.getUid());

            GetcordinatesFirebae t=new GetcordinatesFirebae();
            t.firedatabaseinit();
            book= (Button) findViewById(R.id.books);
           emergency = (Button) findViewById(R.id.emergency);
            blood= (Button) findViewById(R.id.blood);
            money = (Button) findViewById(R.id.money);
            fire= (Button) findViewById(R.id.fire);
            service= (Button) findViewById(R.id.service);
            food = (Button) findViewById(R.id.food);
            clothes= (Button) findViewById(R.id.clothes);

            book.setOnClickListener(this);
            emergency.setOnClickListener(this);
            blood.setOnClickListener(this);
            money.setOnClickListener(this);
            fire.setOnClickListener(this);
            service.setOnClickListener(this);
            food.setOnClickListener(this);
            clothes.setOnClickListener(this);

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
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.books: {


                startActivity(new Intent(this,Addneedyperson.class));

            }
            break;

            case R.id.blood: {

                startActivity(new Intent(this,Needies.class));


            }
            break;
            case R.id.fire: {

                startActivity(new Intent(this,Needies.class));


            }
            break;
            case R.id.money: {


                startActivity(new Intent(this,Addneedyperson.class));

            }
            break;

            case R.id.food: {

                startActivity(new Intent(this,Needies.class));


            }
            break;
            case R.id.emergency: {

                startActivity(new Intent(this,Needies.class));


            }
            break;
            case R.id.service: {

                startActivity(new Intent(this,Needies.class));


            }
            break;
        }

    }
}
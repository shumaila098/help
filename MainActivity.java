package com.example.mani.realtim;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.content.ContentValues.TAG;
import static com.example.mani.realtim.Loggin.user;

public class MainActivity extends AppCompatActivity {
    public static Boolean check_login = false;

    private FirebaseAuth.AuthStateListener mAuthListener;

    private FirebaseAuth mAuth;
Boolean checker=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

       // startActivity(new Intent(MainActivity.this, Loggin.class));

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            startActivity(new Intent(MainActivity.this, Mainscreen.class));
        } else {
           startActivity(new Intent(MainActivity.this, Loggin.class));
           // startActivity(new Intent(MainActivity.this, Mainscreen.class));
        }

    }
    @Override
    public void onStart() {
        super.onStart();
      //  mAuth.addAuthStateListener(mAuthListener);
    }
    @Override
    public void onStop() {
        super.onStop();

    }
}
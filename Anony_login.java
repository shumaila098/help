package com.example.mani.realtim;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
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

public class Anony_login extends Activity implements View.OnClickListener{
    EditText user;
    EditText password;

    public static Boolean check_login=false;
    private FirebaseDatabase fd=FirebaseDatabase.getInstance();

    private DatabaseReference dr=fd.getReference();

    private DatabaseReference r=dr.child("data");
    private DatabaseReference us=r.child("User");
    private DatabaseReference us_an=us.child("Anonymous");
    private DatabaseReference user_an=us_an.child("username");

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login);
        mAuth = FirebaseAuth.getInstance();
        user=(EditText)findViewById(R.id.username_edit);
        password=(EditText)findViewById(R.id.passord);
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    showmessage("Sign in");
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    showmessage("signed out");
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };



    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
    public void showmessage(String v) {
        Toast.makeText(this,v,Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.logged:
            {
             String users=user.getText().toString();
                String pas=password.getText().toString();
                if(!users.equals("")&&!pas.equals(""))
                {
                    mAuth.signInWithEmailAndPassword(users,pas);
                }
                else
                    {showmessage("Fields are empty");}

            }
                break;
            case R.id.reg:
                break;

        }
    }
}

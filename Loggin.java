package com.example.mani.realtim;

import android.*;
import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.content.ContentValues.TAG;

/**
 * Created by mani on 11/08/2017.
 */

public class Loggin extends Activity implements View.OnClickListener{
     EditText users;
    EditText password;



Button logg,rege;
    private FirebaseAuth mAuth;
    public static FirebaseUser user;
    private FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login);
        mAuth = FirebaseAuth.getInstance();
        user=mAuth.getCurrentUser();
        users=(EditText)findViewById(R.id.username_edit);
        password=(EditText)findViewById(R.id.passord);
        logg=(Button) findViewById(R.id.logged);
        rege=(Button)findViewById(R.id.reg);
        rege.setOnClickListener(this);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 0);



       logg.setOnClickListener(this);

        if (user!= null) {

            startActivity(new Intent(this, Mainscreen.class));
            showmessage("user is not null");
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
    public void onStart() {
        super.onStart();
       // mAuth.addAuthStateListener(mAuthListener);
    }
    @Override
    public void onStop() {
        super.onStop();

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
                String userss=users.getText().toString();
                String pas=password.getText().toString();
                if(!users.equals("")&&!pas.equals(""))
                {
                    mAuth.signInWithEmailAndPassword(userss,pas)
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                                    if (task.isSuccessful()) {
                                        startActivity(new Intent(Loggin.this, Mainscreen.class));

                                    }
                                    // If sign in fails, display a message to the user. If sign in succeeds
                                    // the auth state listener will be notified and logic to handle the
                                    // signed in user can be handled in the listener.
                                    if (!task.isSuccessful()) {

                                      showmessage("password or user invalid");
                                    }

                                    // ...
                                }
                            });


                }
                else
                {showmessage("Fields are empty");}

            }
       break;
            case R.id.reg:
                //startActivity(new Intent(this, Registeration.class));
                //startActivity(new Intent(this, Userdata.class));
                startActivity(new Intent(this,Registeration.class));
                break;

        }
    }
}

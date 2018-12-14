package com.example.mani.realtim;

import android.*;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.net.InetAddress;

import static android.content.ContentValues.TAG;
import static com.example.mani.realtim.Loggin.user;

/**
 * Created by mani on 11/08/2017.
 */


public class Registeration extends Activity implements View.OnClickListener{

    RadioButton m,f;
    EditText name, surname, password, conpassword, email;
    Button cancel, sub;
    Boolean checker=false;
    String gender;
    Double lati,longi;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registeration);
        name = (EditText) findViewById(R.id.nam);
        checker=false;
        surname = (EditText) findViewById(R.id.surnam);
        m = (RadioButton) findViewById(R.id.male);
        f = (RadioButton) findViewById(R.id.female);
        password = (EditText) findViewById(R.id.pass);


        conpassword = (EditText) findViewById(R.id.conpass);
        email = (EditText) findViewById(R.id.email);
        cancel = (Button) findViewById(R.id.can);
        sub = (Button) findViewById(R.id.submit);
       FirebaseUser user;
        mAuth = FirebaseAuth.getInstance();
        user=mAuth.getCurrentUser();
        sub.setOnClickListener(this);

        cancel.setOnClickListener(this);
        if (user!= null) {

            startActivity(new Intent(this, Mainscreen.class));
            showmessage("user is not null");
        }



    }

    @Override
    public void onBackPressed()
    {
        startActivity(new Intent(this,Loggin.class));
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //  updateUI(currentUser);
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.male:
                if (checked)
                {   gender = "male";
                f.setChecked(false);}
                break;
            case R.id.female:

                if (checked) {
                    gender = "female";
                    m.setChecked(false);
                }
                break;
        }
    }

    public void showmessage(String v) {
        Toast.makeText(this, v, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.can: {
                startActivity(new Intent(Registeration.this, Loggin.class));
            }

            break;
            case R.id.submit: {
                final String names = name.getText().toString();
               final String surn = surname.getText().toString();
                String pa = password.getText().toString();
                String conpas = conpassword.getText().toString();
               final String emails = email.getText().toString();

                if(pa.length()>5){
                if (!names.equals("") && !surn.equals("") && !pa.equals("") && !conpas.equals("") && !emails.equals("")) {
                    FirebaseUser s = mAuth.getCurrentUser();
                    if (pa.equals(conpas))

                    {



                        mAuth.createUserWithEmailAndPassword(emails, pa)
                                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            // Sign in success, update UI with the signed-in user's information
                                            Log.d(TAG, "createUserWithEmail:success");
                                            FirebaseUser userr = FirebaseAuth.getInstance().getCurrentUser();
                                            UserFirebase userFirebase=new UserFirebase();
                                            userFirebase.firedatabaseinit(userr.getUid());
                                            userFirebase.setNameNode(names);
                                            userFirebase.setSurnameNode(surn);
                                            userFirebase.setEmailNode(emails);
                                            userFirebase.setGenderNode(gender);
                                            userFirebase.setCnicNode("");
                                            userFirebase.setYearNode(0);
                                            userFirebase.setAgeNode(1995,5,3);
                                            userFirebase.setTotalNeediesNode(0);
                                            userFirebase.setTotalrollnoNode(0);
                                            userFirebase.setLatNode(0.0);
                                            userFirebase.setLongNode(0.0);
                                            userFirebase.setPointNode(0);
                                            userFirebase.setContactNode("");
                                            userFirebase.setNationNode("");
                                            userFirebase.setDobNode("");
                                            userFirebase.setDateNode(0);
                                            userFirebase.setMonthNode(0);



                                            if (userr != null) {

                                               startActivity(new Intent(Registeration.this, Mainscreen.class));
                                                showmessage("user is not null");
                                            } else {
                                                startActivity(new Intent(Registeration.this, Loggin.class));
                                                // startActivity(new Intent(MainActivity.this, Mainscreen.class));
                                            showmessage("user is null");
                                            }


                                            //  updateUI(user);
                                        } else {
                                            // If sign in fails, display a message to the user.
                                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                            showmessage("User with this email exists");


                                            //  updateUI(null);
                                        }

                                        // ...
                                    }
                                });

                    } else {
                        showmessage("password doesnont match");
                    }


                } else {
                    showmessage("Fields are empty");
                }


            }else{
                    showmessage("password must have more than 5 character");
                }

            }

            break;

        }
    }

}

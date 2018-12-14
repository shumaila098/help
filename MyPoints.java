package com.example.mani.realtim;

/**
 * Created by mani on 25/11/2018.
 */

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MyPoints extends AppCompatActivity   implements View.OnClickListener {
    Button btn;

    private TextView poin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.point);

        poin = (TextView) findViewById(R.id.textView10);
        FirebaseUser us;
        us = FirebaseAuth.getInstance().getCurrentUser();

        if (us != null) {
            UserFirebase a = new UserFirebase();
            a.firedatabaseinit(us.getUid());
            poin.setText("Your Points:" + String.valueOf(a.getPointsNode()));
           btn = (Button) findViewById(R.id.btn);

         btn.setOnClickListener(this);
btn.setText("Share on fb");
            btn.setVisibility(View.VISIBLE);

        }


    }
    public void showmessage(String v) {
        Toast.makeText(this, v, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {case R.id.btn:
            btn.setVisibility(View.INVISIBLE);
            Bitmap by = Screenshot.takescreenshotOfRootView(v);

            ArrayList<Bitmap> b = new ArrayList<>();
            b.add(by);

            fbb.bitmaps = b;
//
            startActivity(new Intent(this, fbb.class));
            break;
        }
    }
}
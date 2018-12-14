package com.example.mani.realtim;

import android.*;
import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Created by mani on 19/09/2017.
 */

public class UploadActivity  extends Dialog implements
        android.view.View.OnClickListener {
Button Next,Back,Cancels,Upload;
    Activity c;
    public Boolean fl=false;
    private ProgressDialog progressDialog;
    private final static int wid=512;
    private final static int hig=512;
    private ArrayList<String> pathArray;
private int Arrayposition;
    ImageView image;
    private FirebaseAuth mAuth;
    public  static String pathtoshare;
    public  static Bitmap imagetoshare;

    private StorageReference storageReference;
    public UploadActivity(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uploadactivity);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    Next=(Button)findViewById(R.id.next);
        Back=(Button)findViewById(R.id.back);
        Cancels=(Button)findViewById(R.id.cancels);
        Upload=(Button)findViewById(R.id.upload);
        image=(ImageView) findViewById(R.id.imageV);
        Next.setOnClickListener(this);
        Back.setOnClickListener(this);
        Cancels.setOnClickListener(this);
        Upload.setOnClickListener(this);

        fl=false;

    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.back:
if(Arrayposition>0)
{
    Arrayposition=Arrayposition-1;
    addimagefrompath();
}
                break;
            case R.id.cancels:
                dismiss();

                break;
            case R.id.upload:
                fl = true;

                break;
            case R.id.next:
                if(Arrayposition<pathArray.size()-1)
                {
                    Arrayposition=Arrayposition+1;
                    addimagefrompath();

                break;
        }
    }}


    public  Boolean pl(){
        return fl;
    }
    public  void plae(){
        fl=false;
    }
    private void addfiles(){
        String path=System.getenv("EXTERNAL_STORAGE");


    }
    private void addimagefrompath(){
        String path=pathArray.get(Arrayposition);
        pathtoshare=path;
        File f =new File(path,"");
        try {
            Bitmap b= BitmapFactory.decodeStream(new FileInputStream(f));
            imagetoshare=b;
            image.setImageBitmap(b);
        } catch (FileNotFoundException e) {
            showmessage("file not found");
        }

    }
    public void showmessage(String v) {
        Toast.makeText(c, v, Toast.LENGTH_SHORT).show();
    }

    public  Bitmap getImagetoshare() {
        return imagetoshare;
    }

    public String getPathtoshare() {
        return pathtoshare;
    }
}

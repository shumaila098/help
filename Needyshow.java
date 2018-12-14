package com.example.mani.realtim;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.example.mani.realtim.R;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;
import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO;

/**
 * Created by mani on 14/08/2017.
 */

public class Needyshow extends Activity implements View.OnClickListener{
    ImageView imageview;


    RadioButton m,f;
    private Uri fileUri;
    private Uri fileri;
    File fileone;

    TextView gender,name,surname,fathername,contactno,postaladd,email,naton,cnic,dob,quali,helpTitle,DetailOfhelp;

    StorageReference riversRef;
    private StorageReference mStorageRef;

StatusChanger cdd;
    Button capimag,helped,subb;
    Bitmap photo1;
    String holder,gend="male";
    int count_img=0;
Button share;
    public static Bitmap photo;
    public static  String pathofimage=null;
    public static int pre_up;
    public static String pre_up_str;
    public static String id;
  public static   Needy s;

    int pic_node;


    ArrayList<String> pics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.needyshow);
        show();
        pics=new ArrayList<String>();
        cdd= new StatusChanger(Needyshow.this);
        cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mStorageRef = FirebaseStorage.getInstance().getReference();
        FirebaseUser us;
        imageview = (ImageView) findViewById(R.id.imageView);


        us=FirebaseAuth.getInstance().getCurrentUser();
        if(us!=null) {
            name = (TextView) findViewById(R.id.name);
            surname = (TextView) findViewById(R.id.surnam);
            helpTitle = (TextView) findViewById(R.id.help_title);
            share = (Button) findViewById(R.id.share);
            DetailOfhelp = (TextView) findViewById(R.id.Detail_of_help);
            fathername = (TextView) findViewById(R.id.fathername);
            contactno = (TextView) findViewById(R.id.contactno);
            gender = (TextView) findViewById(R.id.gender);
            postaladd = (TextView) findViewById(R.id.postal);
            email = (TextView) findViewById(R.id.email);
            naton = (TextView) findViewById(R.id.nation);
            cnic = (TextView) findViewById(R.id.cnic);
            subb = (Button) findViewById(R.id.sub);
            subb.setOnClickListener(this);
            share.setOnClickListener(this);
            quali = (TextView) findViewById(R.id.quali);
            share.setVisibility(View.VISIBLE);
            subb.setVisibility(View.VISIBLE);

            if (!(s.getTotalpics() == "" || s.getTotalpics() == null || s.getTotalpics().equals(null))) {
                pic_node = Integer.parseInt(s.getTotalpics());

            } else {

                pic_node = 0;
            }

            name.setText(s.getName());
            surname.setText(s.getSurname());
            fathername.setText(s.getFathername());
            contactno.setText(s.getContact());
            postaladd.setText(s.getPostaladdress());
            email.setText(s.getEmail());
            naton.setText(s.getNation());
            cnic.setText(s.getCnic());
            // dob.setText(s.getDobNode());
            quali.setText(s.getQuali());
            helpTitle.setText(s.getKoh());
            DetailOfhelp.setText(s.getDoh());
            if (s.gender != null) {
                if (s.getGender().equals("male") || s.getGender() == "male") {

                    gend = "male";

                }

                if (s.getGender().equals("female") || s.getGender() == "female") {

                    gend = "female";
                }
            } else {

                gend = "male";
            }
            id = s.getId();

            gender.setText(gend);

            if (pic_node == 1) {
                riversRef = mStorageRef.child("profile/Needy/" + id + "/userpro" + 1 + ".jpg");
                Glide.with(this)
                        .using(new FirebaseImageLoader())
                        .load(riversRef).error(R.drawable.cross)
                        .into(imageview);
                try {
                    fileone = File.createTempFile("imageone", ".jpg");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                final File finalLocalFile = fileone;
                riversRef.getFile(fileone)
                        .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                // Successfully downloaded data to local file


                                try {
                                    photo1 = MediaStore.Images.Media.getBitmap(Needyshow.this.getContentResolver(), Uri.fromFile(finalLocalFile));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                // ...
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle failed download
                        // ...
                    }
                });
            }


        }
        else{
            startActivity(new Intent(this, Loggin.class));
        }

        //    mStorageRef = FirebaseStorage.getInstance().getReference();


//trying to set the image from filepath
/*
            if(photo!=null) {,,,

                imageview.setImageBitmap(photo);
            }
*/

    }

    public void showmessage(final String v) {


        runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(Needyshow.this,v,Toast.LENGTH_SHORT).show();
            }
        });



    }




    public void show()
    {
      // this.s=Findneedyperson.s;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.share:
            {
                share.setVisibility(View.GONE);
                subb.setVisibility(View.GONE);
                Bitmap by = Screenshot.takescreenshotOfRootView(v);

                ArrayList<Bitmap> b = new ArrayList<>();
                b.add(by);

                fbb.bitmaps = b;
//
                startActivity(new Intent(this, fbb.class));
            }
            break;

            case R.id.sub: {
EditText  coder=(EditText)findViewById(R.id.code);

                String code=coder.getText().toString();


                if(!(code.equals("")||code.equals(null)))
                {

                    Boolean checker=s.checkcode(code);

                //    if(checker)
                    if(checker)
                    {





                        runOnUiThread(new Runnable() {
                            public void run() {
                                cdd.show();
                            }
                        });

                        //dfg();
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            public void run() {
                                if (cdd.pl()) {
                                    if (cdd.pls()) {
                                        int fr = cdd.getstatus();

                                        NeedyFirebasetwo fre = new NeedyFirebasetwo();
                                        fre.firedatabaseinit(id);
                                        fre.setstatusNode(fr);

                                    }
                                        cdd.plae();
                                        cdd.dismiss();
                                    showmessage("Done");


                                }


                            }

                        }, 0, 500);

                        //change status
                    }
                    else
                    {
                        showmessage("wrong code");}

                }
                else
                {
                    showmessage("enter code");

                }

              break;
            }
        }    }




}




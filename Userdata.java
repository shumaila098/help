package com.example.mani.realtim;

/**
 * Created by mani on 14/08/2017.
 */


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mani.realtim.R;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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
import java.util.Date;
import java.util.Locale;

import static android.content.ContentValues.TAG;
import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;
import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO;

/**
 * Created by mani on 14/08/2017.
 */

public class Userdata extends AppCompatActivity implements  View.OnClickListener{
    ImageView imageview;
    private Uri fileUri;
    private Uri fileri;
    String holder,gend;
    StorageReference riversRef;
    private StorageReference mStorageRef;
    private static final String IMAGE_DIRECTORY_NAME = "DirName";
    Button capimag,sub,upshow;
    private static final int  CAM_REQUEST=1313;
    public static  Bitmap photo=null;
    public static  String pathofimage=null;
    DatePicker simpleDatePicker;
    UserFirebase s;
    RadioButton m,f;
    Boolean check;

    TextView email;
    private FirebaseAuth mAuth;
    public static FirebaseUser user;
    FirebaseUser us;
    EditText name,surname,fathername,contactno,postaladd,naton,cnic,dob,quali;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.userdata);
        mAuth = FirebaseAuth.getInstance();
        user=mAuth.getCurrentUser();
        if(user!=null){
        s=new UserFirebase();
        us=FirebaseAuth.getInstance().getCurrentUser();
        name=(EditText) findViewById(R.id.name);
        surname=(EditText) findViewById(R.id.surnam);
        fathername=(EditText) findViewById(R.id.fathername);
       contactno=(EditText) findViewById(R.id.contactno);
        postaladd=(EditText) findViewById(R.id.postal);
        upshow=(Button) findViewById(R.id.up_pho);
        upshow.setOnClickListener(this);
        check=false;

        email=(TextView) findViewById(R.id.email);
       naton=(EditText) findViewById(R.id.nation);
        cnic=(EditText) findViewById(R.id.cnic);

        quali=(EditText) findViewById(R.id.quali);
         simpleDatePicker = (DatePicker)findViewById(R.id.datePicker); // initiate a date picker

        simpleDatePicker.setSpinnersShown(false);

        capimag=(Button)findViewById(R.id.cap_phot);
        sub=(Button)findViewById(R.id.sub);
        imageview=(ImageView) findViewById(R.id.imageView);
        capimag.setOnClickListener(this);
        sub.setOnClickListener(this);
       m = (RadioButton) findViewById(R.id.male);
        f = (RadioButton) findViewById(R.id.female);

        mStorageRef = FirebaseStorage.getInstance().getReference();
                 s.firedatabaseinit(us.getUid());
        name.setText(s.getNameNode());
        surname.setText(s.getSurnameNode());
        fathername.setText(s.getFatherNameNode());
      contactno.setText(s.getContactNode());
        postaladd.setText(s.getPostalAddressNode());
        email.setText(s.getEmailNode());
        naton.setText(s.getNationNode());
        cnic.setText(s.getCnicNode());

        quali.setText(s.getQualiNode());

            if(s.getGenderNode()!=null){
            if(s.getGenderNode().equals("male")||s.getGenderNode()=="male")
        {  m.setChecked(true);
        f.setChecked(false);
            gend="male";
        }

            if(s.getGenderNode().equals("female")||s.getGenderNode()=="female")
            {  m.setChecked(false);
                f.setChecked(true);
            gend="female";
            }}
            else
            {
                gend = "male";
            }


        simpleDatePicker.updateDate(s.getYearNode(),s.getMonthNode(),s.getDateNode());

            riversRef=mStorageRef.child("profile/user/"+us.getUid()+"/userpro.jpg");
        Glide.with(this /* context */)
                .using(new FirebaseImageLoader())
                .load(riversRef).error(R.drawable.cross)
                .into(imageview);
        mStorageRef.child("profile/user/"+us.getUid()+"/userpro.jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                // Got the download URL for 'users/me/profile.png'

                check=true;
               // closebut.setVisibility(View.VISIBLE);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // File not found

            }
        });



    }
    else{
            startActivity(new Intent(this, Loggin.class));
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.meni, menu);

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {

            case R.id.logout:
                FirebaseAuth fAuth = FirebaseAuth.getInstance();
                fAuth.signOut();
                startActivity(new Intent(this,Loggin.class));


                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.male:
                if (checked)
                {    gend = "male";

                f.setChecked(false);}
                break;
            case R.id.female:

                if (checked){
                    gend = "female";
                m.setChecked(false);
              }
                break;

        }
    }
    public void showmessage(String v) {
        Toast.makeText(this,v,Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        {
            if(resultCode==RESULT_OK){
            if(requestCode==1313)
            {
                photo = (Bitmap) data.getExtras().get("data");
                imageview.setVisibility(View.VISIBLE);

                imageview.setImageBitmap(photo);
                Uri tempUri = getImageUri(getApplicationContext(), photo);

                fileri =tempUri;
                riversRef = mStorageRef.child("profile/user/" + us.getUid() + "/userpro.jpg");

                holder = "profile/user/" + us.getUid() + "/userpro.jpg";

                //  Uri file = Uri.fromFile(new File("path/to/images/rivers.jpg"));

                // CALL THIS METHOD TO GET THE ACTUAL PATH
                //  File finalFile = new File(getRealPathFromURI(tempUri));

            }
            if(requestCode==1){

                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // Get the path from the Uri

                    try {
                       photo = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
fileri=selectedImageUri;
                        imageview.setVisibility(View.VISIBLE);

                        imageview.setImageBitmap(photo);
                        riversRef = mStorageRef.child("profile/user/" + us.getUid() + "/userpro.jpg");

                        holder = "profile/user/" + us.getUid() + "/userpro.jpg";

                    }  catch (IOException e) {

                    }
                }
            }


        }}
    }
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }



    @Override
    public void onClick(View v) {

        switch(v.getId())
        {

            case R.id.cap_phot:
            {
                Intent capt_phot=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(capt_phot,CAM_REQUEST);
            }
            break;
            case R.id.up_pho:
            {

                Intent intent = new Intent();
// Show only images, no videos or anything else
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
// Always show the chooser (if there are multiple options available)
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
            }
            break;
            case R.id.sub: {
                int day = simpleDatePicker.getDayOfMonth();
                int month = simpleDatePicker.getMonth();
                int year = simpleDatePicker.getYear();
if(holder!=null)
    s.setImgStorNode(holder);
                s.setDateNode(day);
                s.setMonthNode(month);
                s.setYearNode(year);
                s.setGenderNode(gend);
                s.setNameNode(name.getText().toString());
                s.setSurnameNode(surname.getText().toString());
                s.setFatherNameNode(fathername.getText().toString());
                s.setEmailNode(email.getText().toString());
                    s.setContactNode(contactno.getText().toString());
                s.setPostalAddressNode(postaladd.getText().toString());
                s.setNationNode(naton.getText().toString());
                s.setCnicNode(cnic.getText().toString());
                s.setDobNode(String.valueOf(day)+"/"+String.valueOf(month)+"/"+String.valueOf(year));
                s.setAgeNode(year,month,day);
                s.setQualiNode(quali.getText().toString());
                int r=s.getTotalNeediesNode();
                r++;
                s.setTotalNeediesNode(r);

if(holder!=null) {
    riversRef = mStorageRef.child("profile/user/" + us.getUid() + "/userpro.jpg");


    riversRef.putFile(fileri)
            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    // Get a URL to the uploaded content

                    Uri downloadUrl = taskSnapshot.getDownloadUrl();
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle unsuccessful uploads
                    // ...

                }
            });
}
                showmessage("saved");
/*
                File localFile = null;
                try {
                    localFile = File.createTempFile("images", "jpg");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                riversRef.getFile(localFile)
                        .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                // Successfully downloaded data to local file
                                // ...
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle failed download
     i                   // ...
                    }
                });  */
            } break;
        }

    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // save file url in bundle as it will be null on scren orientation
        // changes
        outState.putParcelable("file_uri", fileUri);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // get the file url
        fileUri = savedInstanceState.getParcelable("file_uri");
    }


}


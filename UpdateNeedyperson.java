package com.example.mani.realtim;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
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
import java.util.List;
import java.util.Locale;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;
import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO;

/**
 * Created by mani on 14/08/2017.
 */

public class UpdateNeedyperson extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener {
    ImageView imageview, imageview00;
    DatePicker simpleDatePicker;
    NeedyFirebasetwo s;
    RadioButton m, of;
    public Boolean updating = false;
    EditText name, surname, fathername, contactno, postaladd, email, cnic, dob, quali, DetailOfhelp;
    VideoView videoView;
    //    static final int REQUEST_VIDEO_CAPTURE = 1;
    private Uri fileUri;
    private Uri fileri;
    StorageReference riversRef;
    private StorageReference mStorageRef;
    FirebaseUser us;
    private static final String IMAGE_DIRECTORY_NAME = "DirName";
    Button capimag, sub,upshow;
    Bitmap photo1;
    String holder, gend = "male";
    int count_img = 0;
    String prov;
    private static final int CAM_REQUEST = 1313;
    public static Bitmap photo;
    public static String pathofimage = null;
    public static int pre_up;
    EditText needycod;
    public static String pre_up_str;
    int pic_node;
    ArrayList<Uri> pics;
    String kohhh;
File fileone,filetwo,filethree,filefour;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.shareneedy);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);
        needycod = (EditText) findViewById(R.id.code);
        pics = new ArrayList<Uri>();
        mStorageRef = FirebaseStorage.getInstance().getReference();
        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);

        us = FirebaseAuth.getInstance().getCurrentUser();

if(us!=null) {
    UserFirebase a = new UserFirebase();

    a.firedatabaseinit(us.getUid());
    upshow = (Button) findViewById(R.id.up_pho);
    upshow.setOnClickListener(this);

    imageview = (ImageView) findViewById(R.id.imageView);

    imageview00 = (ImageView) findViewById(R.id.imageView00);

    name = (EditText) findViewById(R.id.name);
    surname = (EditText) findViewById(R.id.surnam);

    m = (RadioButton) findViewById(R.id.male);
    of = (RadioButton) findViewById(R.id.female);
    DetailOfhelp = (EditText) findViewById(R.id.Detail_of_help);
    fathername = (EditText) findViewById(R.id.fathername);
    contactno = (EditText) findViewById(R.id.contactno);
    postaladd = (EditText) findViewById(R.id.postal);
    email = (EditText) findViewById(R.id.email);

    cnic = (EditText) findViewById(R.id.cnic);
    quali = (EditText) findViewById(R.id.quali);
    simpleDatePicker = (DatePicker) findViewById(R.id.datePicker); // initiate a date picker

    simpleDatePicker.setSpinnersShown(false);
    s = new NeedyFirebasetwo();
    s.firedatabaseinit(pre_up_str);
    pic_node = Integer.parseInt(Needies.passobj.getTotalpics());
    count_img = pic_node;
    name.setText(Needies.passobj.getName());
    surname.setText(Needies.passobj.getSurname());
    fathername.setText(Needies.passobj.getFathername());
    contactno.setText(Needies.passobj.getContact());
    postaladd.setText(Needies.passobj.getPostaladdress());
    email.setText(Needies.passobj.getEmail());
   prov= Needies.passobj.getNation();
    cnic.setText(Needies.passobj.getCnic());

    m=(RadioButton)findViewById(R.id.male);
    of=(RadioButton)findViewById(R.id.female);
    RadioGroup r=(RadioGroup)findViewById(R.id.radioGroup);
    if (r.getCheckedRadioButtonId() == R.id.male)
    {
        of.setChecked(false);
    }

    if (r.getCheckedRadioButtonId() == R.id.female)
    {
        m.setChecked(false);
    }
    quali.setText(Needies.passobj.getQuali());
    List<String> categor = new ArrayList<String>();
    categor.add("Kpk");
    categor.add("Balochistan");
    categor.add("Punjab");
    categor.add("Sindh");

    Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);

    // Spinner click listener
    spinner2.setOnItemSelectedListener(this);

    // Creating adapter for spinner
    ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categor);

    // Drop down layout style - list view with radio button
    dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    // attaching data adapter to spinner
    spinner2.setAdapter(dataAdapter2);
    List<String> categories = new ArrayList<String>();
    categories.add("Food");
    categories.add("Book");
    categories.add("Emergency");
    categories.add("Clothes");
    categories.add("Money");
    categories.add("Fire");
    categories.add("Blood");
    categories.add("Service");
    int poss=0;
    // attaching data adapter to spinner

    if(Needies.passobj.getNation()=="Kpk")
    {
        poss=0;
        prov="Kpk";

    }
    if(Needies.passobj.getNation()=="Balochistan")
    {
        prov="Balochistan";
        poss=1;
    }
    if(Needies.passobj.getNation()=="Punjab")
    {
        prov="Punjab";
        poss=2;

    }
    if(Needies.passobj.getNation()=="Sindh")
    {
        prov="Sindh";
        poss=3;

    }

    spinner2.setSelection(poss);
    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

    // Drop down layout style - list view with radio button
    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
int pos=0;
    // attaching data adapter to spinner
    spinner.setAdapter(dataAdapter);
    if(Needies.passobj.getKoh()=="Food")
    {
     pos=0;
        kohhh="Food";
    }
    if(Needies.passobj.getKoh()=="Emergency")
    {
        kohhh="Emergency";
        pos=2;
    }
    if(Needies.passobj.getKoh()=="Book")
    {
        kohhh="Book";
        pos=1;

    }
    if(Needies.passobj.getKoh()=="Service")
    {
        pos=7;
        kohhh="Service";
    }
    if(Needies.passobj.getKoh()=="Clothes")
    {
        pos=3;
        kohhh="Clothes";
    }
    if(Needies.passobj.getKoh()=="Money")
    {
        kohhh="Money";
        pos=4;
    }
    if(Needies.passobj.getKoh()=="Fire")
    {
        pos=5;
        kohhh="Fire";
    }
    if(Needies.passobj.getKoh()=="Blood")
    {
        pos=6;
        kohhh="Blood";
    }

spinner.setSelection(pos);

    DetailOfhelp.setText(Needies.passobj.getDoh());

 if(Needies.passobj.getGender()!=null){
    if (Needies.passobj.getGender().equals("male") || Needies.passobj.getGender() == "male") {
        m.setChecked(true);
        of.setChecked(false);
        gend = "male";
    }

    if (Needies.passobj.getGender().equals("female") || Needies.passobj.getGender() == "female") {
        m.setChecked(false);
        of.setChecked(true);
        gend = "female";
    }}else
 {
     gend = "male";
 }
    if (!(Needies.passobj.getYear() == null || Needies.passobj.getMonth() == null || Needies.passobj.getDate() == null)) {
        int y = Integer.parseInt(Needies.passobj.getYear());

        int m = Integer.parseInt(Needies.passobj.getMonth());
        int d = Integer.parseInt(Needies.passobj.getDate());

        simpleDatePicker.updateDate(y, m, d);
    } else {
        simpleDatePicker.updateDate(0, 0, 2012);
    }

    capimag = (Button) findViewById(R.id.cap_phot);
    sub = (Button) findViewById(R.id.sub);
    imageview = (ImageView) findViewById(R.id.imageView);

    imageview00 = (ImageView) findViewById(R.id.imageView00);


    capimag.setOnClickListener(this);
    sub.setOnClickListener(this);
    imageview00.setOnClickListener(this);

    imageview00.setEnabled(false);

    imageview00.setVisibility(View.INVISIBLE);

    picfact();
}
  else{
            startActivity(new Intent(this, Loggin.class));
        }
       
    }


        //    mStorageRef = FirebaseStorage.getInstance().getReference();


//trying to set the image from filepath
/*
            if(photo!=null) {

                imageview.setImageBitmap(photo);
            }
*/

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
                    gend = "male";
                of.setChecked(false);
                break;
            case R.id.female:

                if (checked)
                    gend = "female";
                m.setChecked(false);
                break;
        }
    }



    public void showmessage(String v) {
        Toast.makeText(this, v, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        {

            if(resultCode==RESULT_OK&& data != null && data.getData() != null){
                if(requestCode==1313) {
            photo = (Bitmap) data.getExtras().get("data");

                imageview.setVisibility(View.VISIBLE);
                imageview.setImageBitmap(photo);


                photo1 = photo;
                    Uri tempUri = getImageUri(getApplicationContext(), photo);

                    fileri =tempUri;
                    riversRef = mStorageRef.child("profile/Needy/" + pre_up_str + "/userpro" + 1 + ".jpg");

                    holder = "profile/Needy/" + pre_up_str + "/userpro" + 1 + ".jpg";


            //  Uri file = Uri.fromFile(new File("path/to/images/rivers.jpg"));

            // CALL THIS METHOD TO GET THE ACTUAL PATH
            //  File finalFile = new File(getRealPathFromURI(tempUri));
        }

        if(requestCode==1){

                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // Get the path from the Uri

                    String path = getPathFromURI(selectedImageUri);
                                      fileri=selectedImageUri;
                    riversRef = mStorageRef.child("profile/Needy/" + pre_up_str + "/userpro" + 1 + ".jpg");

                    holder = "profile/Needy/" + pre_up_str + "/userpro" + 1 + ".jpg";
                    try {
                        Bitmap b = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);

                        onBackIntent(b,path,selectedImageUri);
                    }  catch (IOException e) {

                    }
                }
            }
        }
}}
    public String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
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



    public void onBackIntent(Bitmap s, String p, Uri selectedImageUri){

        photo =s;

            imageview.setVisibility(View.VISIBLE);
            imageview.setImageBitmap(photo);



            photo1=photo;
            imageview00.setEnabled(true);
            imageview00.setVisibility(View.VISIBLE);


        //  Uri file = Uri.fromFile(new File("path/to/images/rivers.jpg"));

        // CALL THIS METHOD TO GET THE ACTUAL PATH
        //  File finalFile = new File(getRealPathFromURI(tempUri));





    }





    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
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
            case R.id.cap_phot:
            {

                Intent capt_phot=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(capt_phot,CAM_REQUEST);
            }
            break;
            case R.id.imageView00:
            {
                delete(0);


            }
            break;



            case R.id.sub: {

                final String codes = needycod.getText().toString();
                if(codes!=""&&(!codes.equals(""))&&(!codes.equals(null))) {

                    s.setCodeNode(codes);
                    int day = simpleDatePicker.getDayOfMonth();
                    int month = simpleDatePicker.getMonth();
                    int year = simpleDatePicker.getYear();
                    if (holder != null)
                        s.setImgStorNode(holder);
                    s.setDateNode(day);
                    s.setMonthNode(month);
                    s.setYearNode(year);
                    s.setGenderNode(gend);
                    s.setDohNode(DetailOfhelp.getText().toString());
                    s.setKohNode(kohhh);
                    s.setNameNode(name.getText().toString());
                    s.setSurnameNode(surname.getText().toString());
                    s.setFatherNameNode(fathername.getText().toString());
                    s.setEmailNode(email.getText().toString());
                    s.setContactNode(contactno.getText().toString());
                    s.setPostalAddressNode(postaladd.getText().toString());

                    s.setCnicNode(cnic.getText().toString());
                    s.setDobNode(String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year));
                    s.setAgeNode(year, month, day);
                    s.setQualiNode(quali.getText().toString());


                    int i = 1;
                    if(fileri!=null) {

                        riversRef = mStorageRef.child("profile/Needy/" + pre_up_str + "/userpro" + i + ".jpg");
                        i++;
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

                    if (pic_node > 0)
                        for (int y = i; y < pic_node + 1; y++) {
                            riversRef = mStorageRef.child("profile/Needy/" + pre_up_str + "/userpro1.jpg");
                            riversRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    // File deleted successfullys


                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception exception) {
                                    // Uh-oh, an error occurred!

                                }
                            });
                        }
                    s.setTotalpicNode(1);


                }

                    showmessage("updated");
                startActivity(new Intent(this,Mainscreen.class));
            } break;
        }

    }
public void picfact(){
    if (pic_node == 1) {
        imageview00.setEnabled(true);
        imageview00.setVisibility(View.VISIBLE);
        riversRef = mStorageRef.child("profile/Needy/" + pre_up_str + "/userpro" + 1 + ".jpg");
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

                        pics.add(Uri.fromFile(finalLocalFile));
                        try {
                            photo1= MediaStore.Images.Media.getBitmap(UpdateNeedyperson.this.getContentResolver(),Uri.fromFile(finalLocalFile));
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

    public void delete (int i)
    {
        count_img=count_img-1;
        pics.remove(i);
        if(i==0)
        {
            if(count_img==0) {
                imageview.setVisibility(View.INVISIBLE);
                imageview00.setEnabled(false);
                imageview00.setVisibility(View.INVISIBLE);
            }

    }}
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch(parent.getId()) {
            case R.id.spinner:
            {

                if(position==0)
                    kohhh= "Food";
                if(position==1)
                    kohhh="Book";
                if(position==2)
                    kohhh="Emergency";
                if(position==3)
                    kohhh="Clothes";
                if(position==4)
                    kohhh="Money";
                if(position==5)
                    kohhh="Fire";
                if(position==6)
                    kohhh="Blood";
                if(position==7)
                    kohhh="Service";
            }



            break;
            case R.id.spinner2:
                if(position==0)
                    prov= "Kpk";
                if(position==1)
                    prov="Balochistan";
                if(position==2)
                    prov="Punjab";
                if(position==3)
                    prov="Sindh";


        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}







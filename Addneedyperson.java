package com.example.mani.realtim;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;
import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO;

    /**
     * Created by mani on 14/08/2017.
     */

    public class Addneedyperson extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
        ImageView imageview, imageview00;
        DatePicker simpleDatePicker;
        String kohhh, prov;
        //  NeedyFirebasetwwo s;
        EditText needycod;
        UploadActivity upl;
        NeedyFirebasetwo s;
        RadioButton m, of;
        private FirebaseDatabase fd = FirebaseDatabase.getInstance();

        private DatabaseReference dr = fd.getReference();

        private DatabaseReference r = dr.child("data");


        private DatabaseReference ne = r.child("Locations");

        private DatabaseReference needloc = ne.child("Needy");
        private DatabaseReference needyloc;
        private DatabaseReference needystatus;

        public Boolean updating = false;
        EditText name, surname, fathername, contactno, postaladd, email, cnic, dob, quali, DetailOfhelp;
        VideoView videoView;
        //    static final int REQUEST_VIDEO_CAPTURE = 1;
        private Uri fileUri;
        private Uri fileri;
        Codeshow codeshow;
        Cordinates f = new Cordinates();
        StorageReference riversRef;
        private StorageReference mStorageRef;
        FirebaseUser us;
        private static final String IMAGE_DIRECTORY_NAME = "DirName";
        Button capimag, sub, upshow;
        Bitmap photo1, photo2, photo3, photo4;
        String holder, gend = "male";
        LocationFirebase locationFirebase;
        MyLocation myLocation;
        int count_img = 0;
        private static final int CAM_REQUEST = 1313;
        public static Bitmap photo;
        public static String pathofimage = null;
        public static int pre = 1;
        ArrayList<Uri> pics = new ArrayList<Uri>();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.addneedy);
            Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
             needycod = (EditText) findViewById(R.id.code);
            // Spinner click listener
            spinner2.setOnItemSelectedListener(this);
            prov = "Kpk";
            Spinner spinner = (Spinner) findViewById(R.id.spinner);

            // Spinner click listener
            spinner.setOnItemSelectedListener(this);
            kohhh = "Food";
            // Spinner Drop down elements
            List<String> categories = new ArrayList<String>();
            categories.add("Food");
            categories.add("Book");
            categories.add("Emergency");
            categories.add("Clothes");
            categories.add("Money");
            categories.add("Fire");
            categories.add("Blood");
            categories.add("Service");

            // Creating adapter for spinner
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            // attaching data adapter to spinner
            spinner.setAdapter(dataAdapter);
            List<String> categor = new ArrayList<String>();
            categor.add("Kpk");
            categor.add("Balochistan");
            categor.add("Punjab");
            categor.add("Sindh");


            // Creating adapter for spinner
            ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categor);

            // Drop down layout style - list view with radio button
            dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            // attaching data adapter to spinner
            spinner2.setAdapter(dataAdapter2);

            upl = new UploadActivity(Addneedyperson.this);
            upl.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            mStorageRef = FirebaseStorage.getInstance().getReference();
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
            us = FirebaseAuth.getInstance().getCurrentUser();
            codeshow = new Codeshow(this);
            codeshow.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            if (us != null) {
                UserFirebase a = new UserFirebase();
                a.firedatabaseinit(us.getUid());

                pre = a.getTotalrollnoNode() + 1;

                name = (EditText) findViewById(R.id.name);
                upshow = (Button) findViewById(R.id.up_pho);
                upshow.setOnClickListener(this);

                surname = (EditText) findViewById(R.id.surnam);

                locationFirebase = new LocationFirebase();
                DetailOfhelp = (EditText) findViewById(R.id.Detail_of_help);
                fathername = (EditText) findViewById(R.id.fathername);
                contactno = (EditText) findViewById(R.id.contactno);
                postaladd = (EditText) findViewById(R.id.postal);
                email = (EditText) findViewById(R.id.email);
                //   naton = (EditText) findViewById(R.id.nation);
                cnic = (EditText) findViewById(R.id.cnic);

                quali = (EditText) findViewById(R.id.quali);
                simpleDatePicker = (DatePicker) findViewById(R.id.datePicker); // initiate a date picker

                simpleDatePicker.setSpinnersShown(false);
                s = new NeedyFirebasetwo();
                s.firedatabaseinit(us.getUid() + String.valueOf(pre));
                needyloc = needloc.child(us.getUid() + String.valueOf(pre));
                needystatus = needyloc.child("status");
                locationFirebase.firedatabaseinit(us.getUid() + String.valueOf(pre));
                name.setText(s.getNameNode());
                surname.setText(s.getSurnameNode());
                fathername.setText(s.getFatherNameNode());
                contactno.setText(s.getContactNode());
                postaladd.setText(s.getPostalAddressNode());
                email.setText(s.getEmailNode());
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
                cnic.setText(s.getCnicNode());

                quali.setText(s.getQualiNode());

                DetailOfhelp.setText(s.getDohNode());
            /*
            if(s.getGenderNode().equals("male")||s.getGenderNode()=="male")
            {  m.setChecked(true);
                f.setChecked(false);
                gend="male";
            }

            if(s.getGenderNode().equals("female")||s.getGenderNode()=="female")
            {  m.setChecked(false);
                f.setChecked(true);
                gend="female";
            }
            simpleDatePicker.updateDate(s.getYearNode(),s.getMonthNode(),s.getDateNode());
            if(!(s.getImgStorNode()==""||s.getImgStorNode().equals("")))
            {
                riversRef=mStorageRef.child("profile/user/"+Loggin.user.getUid()+"/userpro.jpg");
                Glide.with(this)
                        .using(new FirebaseImageLoader())
                        .load(riversRef)
                        .into(imageview);
            }
*/

                capimag = (Button) findViewById(R.id.cap_phot);
                sub = (Button) findViewById(R.id.sub);
                imageview = (ImageView) findViewById(R.id.imageView);

                imageview00 = (ImageView) findViewById(R.id.imageView00);


                capimag.setOnClickListener(this);
                sub.setOnClickListener(this);
                imageview00.setOnClickListener(this);

                imageview00.setEnabled(false);

                imageview00.setVisibility(View.INVISIBLE);


            } else {
                startActivity(new Intent(this, Loggin.class));
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

                    startActivity(new Intent(this, Userdata.class));


                    return true;
                case R.id.logout:
                    FirebaseAuth fAuth = FirebaseAuth.getInstance();
                    fAuth.signOut();
                    startActivity(new Intent(this, Loggin.class));


                    return true;

                default:
                    return super.onOptionsItemSelected(item);
            }
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            {
                if (resultCode == RESULT_OK && data != null && data.getData() != null) {
                    if (requestCode == 1313) {
                        photo = (Bitmap) data.getExtras().get("data");

                            imageview.setVisibility(View.VISIBLE);
                            imageview.setImageBitmap(photo);
                            Uri tempUri = getImageUri(getApplicationContext(), photo);
                            fileri =tempUri;
                            riversRef = mStorageRef.child("profile/Needy/" + us.getUid() + String.valueOf(pre) + "/userpro" + 1 + ".jpg");

                            holder = "profile/Needy/" + us.getUid() + String.valueOf(pre) + "/userpro" + 1 + ".jpg";
                            photo1 = photo;
                            imageview00.setEnabled(true);
                            imageview00.setVisibility(View.VISIBLE);


                        //  Uri file = Uri.fromFile(new File("path/to/images/rivers.jpg"));

                        // CALL THIS METHOD TO GET THE ACTUAL PATH
                        //  File finalFile = new File(getRealPathFromURI(tempUri));

                    }
                    if (requestCode == 1) {

                        Uri selectedImageUri = data.getData();
                        if (null != selectedImageUri) {
                            // Get the path from the Uri
                            photo = (Bitmap) data.getExtras().get("data");
                            imageview.setImageBitmap(photo);
                            String path = getPathFromURI(selectedImageUri);
                            fileri=selectedImageUri;
                            riversRef = mStorageRef.child("profile/Needy/" + us.getUid() + String.valueOf(pre) + "/userpro" + 1 + ".jpg");

                            holder = "profile/Needy/" + us.getUid() + String.valueOf(pre) + "/userpro" + 1 + ".jpg";

                            try {
                                Bitmap b = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);

                                onBackIntent(b, path, selectedImageUri);
                            } catch (IOException e) {

                            }
                        }
                    }


                }
            }
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

        public void sharepic(){
            if (count_img== 1) {
                ArrayList<Bitmap> b=new ArrayList<>();
                b.add(photo1);
                fbb.bitmaps =b;

                startActivity(new Intent(this,fbb.class));
            }
            if (count_img== 2) {
                ArrayList<Bitmap> b=new ArrayList<>();

                b.add(photo1);
                b.add(photo2);
                fbb.bitmaps =b;

                startActivity(new Intent(this,fbb.class));
            }
            if (count_img== 3) {
                ArrayList<Bitmap> b=new ArrayList<>();
                b.add(photo1);
                b.add(photo2);
                b.add(photo3);

                fbb.bitmaps =b;

                startActivity(new Intent(this,fbb.class));
            }
            if (count_img== 4) {
                ArrayList<Bitmap> b=new ArrayList<>();

                b.add(photo1);
                b.add(photo2);
                b.add(photo3);
                b.add(photo4);
                fbb.bitmaps =b;

                startActivity(new Intent(this,fbb.class));
            }

        }
        /* Get the real path from the URI */
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


        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.up_pho: {

                    Intent intent = new Intent();
// Show only images, no videos or anything else
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
// Always show the chooser (if there are multiple options available)
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
                }
                break;
                case R.id.cap_phot: {

                    Intent capt_phot = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    startActivityForResult(capt_phot, CAM_REQUEST);
                }
                break;
                case R.id.imageView00: {
                    delete(0);


                }
                break;
/*

                case R.id.share:
                {

                    final String codes = needycod.getText().toString();
                    if(codes!=""&&(!codes.equals(""))&&(!codes.equals(null))){
                        StatisticsCalculationfirebase fr = new StatisticsCalculationfirebase();
                        fr.firedatabaseinit();
                        int day = simpleDatePicker.getDayOfMonth();
                        int month = simpleDatePicker.getMonth();
                        int year = simpleDatePicker.getYear();
                        if (holder != null)
                            s.setImgStorNode(holder);
                        s.setDateNode(day);
                        s.setMonthNode(month);
                        s.setYearNode(year);
                        s.setGenderNode(gend);

                        if (gend == "male") {
                            fr.addmale();
                        }
                        if (gend == "female") {
                            fr.addfemale();
                        }

                        s.setCodeNode(codes);

                        s.setDohNode(DetailOfhelp.getText().toString());
                        if (prov == "Kpk") {
                            fr.addkpk();
                        }
                        if (prov == "Punjab") {
                            fr.addpunjub();
                        }
                        if (prov == "Balochistan") {
                            fr.addbaloch();
                        }
                        if (prov == "Sindh") {
                            fr.addsindh();
                        }

                        if (kohhh == "Food") {
                            fr.addfood();
                        }
                        if (kohhh == "Emergency") {
                            fr.addemergency();
                        }
                        if (kohhh == "Fire") {
                            fr.addfire();
                        }
                        if (kohhh == "Book") {
                            fr.addbook();
                        }
                        if (kohhh == "Money") {
                            fr.addmoney();
                        }
                        if (kohhh == "Clothes") {
                            fr.addclothes();
                        }
                        if (kohhh == "Blood") {
                            fr.addblood();
                        }
                        if (kohhh == "Service") {

                            fr.addservice();
                        }
                        s.setKohNode(kohhh);
                        s.setNameNode(name.getText().toString());
                        s.setSurnameNode(surname.getText().toString());
                        s.setFatherNameNode(fathername.getText().toString());
                        s.setEmailNode(email.getText().toString());
                        s.setContactNode(contactno.getText().toString());
                        s.setPostalAddressNode(postaladd.getText().toString());
                        //   s.setNationNode(naton.getText().toString());

                        s.setNationNode(prov);


                        s.setCnicNode(cnic.getText().toString());
                        s.setDobNode(String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year));
                        s.setAgeNode(year, month, day);
                        s.setQualiNode(quali.getText().toString());
                        s.setstatusNode(0);
                        myLocation = new MyLocation();
                        s.submit();
                        f = myLocation.MyLocations(this);
                        needystatus.setValue("0");
                        s.setLongNode(Double.valueOf(f.getLons()));
                        s.setLatNode(Double.valueOf(f.getLats()));
                        locationFirebase.setLatNode(Double.valueOf(f.getLats()));
                        locationFirebase.setLongNode(Double.valueOf(f.getLons()));
                        Mainscreen.noti=false;

                        int i = 1;
                        for (Uri s : pics) {

                            //  showmessage(s );
                            fileri = s;
                            riversRef = mStorageRef.child("profile/Needy/" + us.getUid() + String.valueOf(pre) + "/userpro" + i + ".jpg");
                            i++;
                            riversRef.putFile(fileri)
                                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                            // Get a URL to the uploaded content
                                            showmessage("uploaded");
                                            Uri downloadUrl = taskSnapshot.getDownloadUrl();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception exception) {
                                            // Handle unsuccessful uploads
                                            // ...
                                            showmessage("upload failed");
                                        }
                                    });
                        }

                        s.setTotalpicNode(i - 1);

                        UserFirebase a = new UserFirebase();
                        a.firedatabaseinit(us.getUid());
                        a.setTotalNeediesNode(pre);
                        a.setTotalrollnoNode(pre);
                        NeedyFirebase saa = new NeedyFirebase();
                        saa.firedatabaseinit(us.getUid(), pre);


sharepic();


                    }
                    else{
                        showmessage("Enter code");
                    }
                }
                break;


                */
                case R.id.sub: {

                    final String codes = needycod.getText().toString();
                    if(codes!=""&&(!codes.equals(""))&&(!codes.equals(null))){
                    StatisticsCalculationfirebase fr = new StatisticsCalculationfirebase();
                    fr.firedatabaseinit(getApplicationContext());
                    int day = simpleDatePicker.getDayOfMonth();
                    int month = simpleDatePicker.getMonth();
                    int year = simpleDatePicker.getYear();
                    if (holder != null)
                        s.setImgStorNode(holder);
                    s.setDateNode(day);
                    s.setMonthNode(month);
                    s.setYearNode(year);
                    s.setGenderNode(gend);

                    if (gend == "male") {
                        fr.addmale();
                    }
                    if (gend == "female") {
                        fr.addfemale();
                    }

                    s.setCodeNode(codes);

                    s.setDohNode(DetailOfhelp.getText().toString());
                    if (prov == "Kpk") {
                        fr.addkpk();
                    }
                    if (prov == "Punjab") {
                        fr.addpunjub();
                    }
                    if (prov == "Balochistan") {
                        fr.addbaloch();
                    }
                    if (prov == "Sindh") {
                        fr.addsindh();
                    }

                    if (kohhh == "Food") {
                        fr.addfood();
                    }
                    if (kohhh == "Emergency") {
                        fr.addemergency();
                    }
                    if (kohhh == "Fire") {
                        fr.addfire();
                    }
                    if (kohhh == "Book") {
                        fr.addbook();
                    }
                    if (kohhh == "Money") {
                        fr.addmoney();
                    }
                    if (kohhh == "Clothes") {
                        fr.addclothes();
                    }
                    if (kohhh == "Blood") {
                        fr.addblood();
                    }
                    if (kohhh == "Service") {

                        fr.addservice();
                    }
                    s.setKohNode(kohhh);
                    s.setNameNode(name.getText().toString());
                    s.setSurnameNode(surname.getText().toString());
                    s.setFatherNameNode(fathername.getText().toString());
                    s.setEmailNode(email.getText().toString());
                    s.setContactNode(contactno.getText().toString());
                    s.setPostalAddressNode(postaladd.getText().toString());
                    //   s.setNationNode(naton.getText().toString());

                    s.setNationNode(prov);


                    s.setCnicNode(cnic.getText().toString());
                    s.setDobNode(String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year));
                    s.setAgeNode(year, month, day);
                    s.setQualiNode(quali.getText().toString());
                    s.setstatusNode(0);
                    myLocation = new MyLocation();
                    s.submit();
                    f = myLocation.MyLocations(this);
                    needystatus.setValue("0");
                    s.setLongNode(Double.valueOf(f.getLons()));
                    s.setLatNode(Double.valueOf(f.getLats()));
                    locationFirebase.setLatNode(Double.valueOf(f.getLats()));
                    locationFirebase.setLongNode(Double.valueOf(f.getLons()));
                     Mainscreen.noti=false;

                    int i = 1;


                        //  showmessage(s );
                        if(fileri!=null){
                        riversRef = mStorageRef.child("profile/Needy/" + us.getUid() + String.valueOf(pre) + "/userpro" + i + ".jpg");
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
                                });}


                    s.setTotalpicNode(i - 1);

                    UserFirebase a = new UserFirebase();
                    a.firedatabaseinit(us.getUid());
                    a.setTotalNeediesNode(pre);
                    a.setTotalrollnoNode(pre);
                    NeedyFirebase saa = new NeedyFirebase();
                    saa.firedatabaseinit(us.getUid(), pre);

                        showmessage("added");

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
                            // ...
                        }
                    });  */



                        startActivity(new Intent(this,Mainscreen.class));

                }
                else{
                   showmessage("Enter code");
                }
                break;
            }

        }}

        public void delete(int i) {
            count_img = count_img - 1;
            pics.remove(i);
            if (i == 0) {
                if (count_img == 0) {
                    imageview.setVisibility(View.INVISIBLE);
                    imageview00.setEnabled(false);
                    imageview00.setVisibility(View.INVISIBLE);
                }

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
  /*  public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));}

    /*
     * returning image / video

    private static File getOutputMediaFile(int type) {

        // External sdcard location
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                IMAGE_DIRECTORY_NAME);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(IMAGE_DIRECTORY_NAME, "Oops! Failed create "
                        + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + timeStamp + ".jpg");
        } else if (type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "VID_" + timeStamp + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }  */

        public void onBackIntent(Bitmap s, String p, Uri selectedImageUri) {

            photo = s;
            if (count_img == 0) {
                imageview.setVisibility(View.VISIBLE);
                imageview.setImageBitmap(photo);


                photo1 = photo;
                imageview00.setEnabled(true);
                imageview00.setVisibility(View.VISIBLE);
            }

            //  Uri file = Uri.fromFile(new File("path/to/images/rivers.jpg"));

            // CALL THIS METHOD TO GET THE ACTUAL PATH
            //  File finalFile = new File(getRealPathFromURI(tempUri));


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




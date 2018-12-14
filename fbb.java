package com.example.mani.realtim;

/**
 * Created by mani on 30/12/2017.
 */
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookDialog;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.ShareApi;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;


public class fbb extends Activity {
    // Create, automatically open (if applicable), save, and restore the
    // Active Session in a way that is similar to Android UI lifecycles.
    CallbackManager callbackManager;
    ShareDialog shareDialog;
    public static ArrayList<Bitmap> bitmaps;
    public static  String captt;

LoginButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fbb);

        // To maintain FB Login session
        FacebookSdk.setApplicationId("211778496062338");
        FacebookSdk.sdkInitialize(getApplicationContext());
/*
         loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");


        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });
*/
        callbackManager = CallbackManager.Factory.create();

        shareDialog = new ShareDialog(this);
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Bitmap bMap = BitmapFactory.decodeResource(getResources(), R.drawable.fire);
                     publish(bitmaps,"fire");
                        Toast.makeText(getApplicationContext(), "Selected: ", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
    void publish(ArrayList<Bitmap> image,String capt)
    {
        SharePhotoContent content = null;
        for(Bitmap v:image)
        {  SharePhoto photo = new SharePhoto.Builder()
                .setBitmap(v).setCaption(capt)
                .build();
        content = new SharePhotoContent.Builder()
                .addPhoto(photo)
                .build();}
        if(content!=null) {
            ShareApi.share(content, null);
            shareDialog.show(content);
        }
        Toast.makeText(getApplicationContext(), "tunni ", Toast.LENGTH_LONG).show();




    }



    @Override
    public void onBackPressed() {
        startActivity(new Intent(this,Mainscreen.class));
    }

}
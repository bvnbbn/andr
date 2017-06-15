package com.example.android.namkeen;

/**
 * Created by vikas on 5/6/17.
 */
import android.content.Intent;
import android.hardware.camera2.params.Face;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FacebookLoginActivity extends AppCompatActivity implements
        View.OnClickListener
{

    private static  final String TAG="FacebookLogin";

    private FirebaseAuth mAuth;

    private CallbackManager mCallbackManager;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.google_sign_in);

        findViewById(R.id.Fblogin_button).setOnClickListener(this);



        mAuth= FirebaseAuth.getInstance();


        //Initialize Firebase Auth
        mCallbackManager=CallbackManager.Factory.create();

        LoginButton loginButton=(LoginButton)findViewById(R.id.Fblogin_button);
        loginButton.setReadPermissions("email","public_profile");
        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() 
        {
            @Override
            public void onSuccess(LoginResult loginResult) 
            {
                
                Log.d(TAG,"facebook:onSuccess:" +loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel()
            {
                Log.d(TAG,"facebook:onCancel");
            }

            @Override
            public void onError(FacebookException error)
            {
                Log.d(TAG, "facebook:onError", error);



            }
        });
    }

    @Override
    public void onStart()
    {
        super.onStart();

        //check if user is signed in (non- null) and update database and UI accordingly
        FirebaseUser currentUser=mAuth.getCurrentUser();

    }


    @Override
    protected  void onActivityResult(int requestCode,int resultCode,Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);

        // Pass the activity result back to the Facebook SDK
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void handleFacebookAccessToken(AccessToken accessToken)
    {
        Log.d(TAG, "handleFacebookAccessToken:" + accessToken);


        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if(task.isSuccessful())
                        {
                            //Sign in Success
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                        }
                        else
                        {
                            //if sign in fails
                            Log.d(TAG,"signInWithCredential:succes");
                            Toast.makeText(FacebookLoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();


                        }
                    }
                });

    }

    public void signOut()
    {
        mAuth.signOut();
        LoginManager.getInstance().logOut();


    }


    @Override
    public void onClick(View v)
    {

        int i = v.getId();
        if(i == R.id.Fblogin_button)
        {
            signOut();
        }

    }
}

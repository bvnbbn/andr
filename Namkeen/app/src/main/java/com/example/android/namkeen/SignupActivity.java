package com.example.android.namkeen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.android.namkeen.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;

/**
 * Created by Vikas on 8/16/2016.
 */
public class SignupActivity extends AppCompatActivity implements View.OnClickListener
{
    private static final String TAG=SignupActivity.class.getSimpleName();
    private EditText inputName,inputAddress,inputMobile_no,inputEmail, inputPassword,inputConfirm_Password,inputDate_of_Birth;
    private Button btnSignIn, btnCreateAccount, btnResetPassword;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private String userId;
 //   private Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create_account);
      /*  if (!FirebaseApp.getApps(this).isEmpty())
        {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }*/


        //Get the Firebase auth inbstance
        auth = FirebaseAuth.getInstance();
        btnSignIn = (Button) findViewById(R.id.sign_in_button);
        btnCreateAccount = (Button) findViewById(R.id.sign_up_button);
        inputName=(EditText)findViewById(R.id.edit_text_username_create);
        inputAddress=(EditText)findViewById(R.id.edit_text_address);
        inputMobile_no=(EditText)findViewById(R.id.edit_text_mobile_no);
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        inputConfirm_Password=(EditText)findViewById(R.id.confirm_password);
        inputDate_of_Birth=(EditText)findViewById(R.id.date_of_birth);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);



       //     date = new Date("dd/mm/yyyy");

     //   btnResetPassword = (Button) findViewById(R.id.btn_reset_password);

       /* btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i =new Intent(SignupActivity.this,ResetPasswordActivity.class);

                startActivity(i);
            }
        });*/

        mFirebaseInstance=FirebaseDatabase.getInstance();

        mFirebaseDatabase=mFirebaseInstance.getReference().child("users");


        btnSignIn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                finish();
            }

        });
        btnCreateAccount.setOnClickListener(this);

    }



    private void createUser(String name,String address,String mobile_no,String email,String Date_of_birth)
    {

        if (TextUtils.isEmpty(userId))
        {
            userId = auth.getCurrentUser().getUid();
        }

        User user = new User(name,address,mobile_no,email,Date_of_birth);
        mFirebaseDatabase.child(userId).setValue(user);
        addUserChangeListener();

    }
    private void addUserChangeListener()
    {
        // User data change listener
        mFirebaseDatabase.child(userId).addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                User user = dataSnapshot.getValue(User.class);

                // Check for null
                if (user == null) {
                    Log.e(TAG, "User data is null!");
                    return;
                }

                Log.e(TAG, "User data" + user.name + ", " + user.email);


                // clear edit text

                inputName.setText("");
                inputAddress.setText("");
                inputMobile_no.setText("");
                inputEmail.setText("");
                inputPassword.setText("");
                inputConfirm_Password.setText("");
                inputDate_of_Birth.setText("");


            }

            @Override
            public void onCancelled(DatabaseError error)
            {
                // Failed to read value
                Log.e(TAG, "Failed to read user", error.toException());
            }
        });
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v)
    {
        if (v == btnCreateAccount)
        {
            registerUser();

        }
    }


    private void registerUser()
    {


        Log.d(TAG, "method called");
        final String name = inputName.getText().toString().trim();
        final String address = inputAddress.getText().toString().trim();
        final String mobile_no = inputMobile_no.getText().toString().trim();
        final String email = inputEmail.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();
        String confirm_password = inputConfirm_Password.getText().toString().trim();
        final String Date_of_birth = inputDate_of_Birth.getText().toString().trim();
        Date date = new Date();


        if ((TextUtils.isEmpty(name)))
        {
            Toast.makeText(getApplicationContext(), " Please Enter Name", Toast.LENGTH_LONG).show();
            return;

        }


        if (name.length()>20)
        {
            Toast.makeText(getApplicationContext(), " Please Enter Name of characters less than 20", Toast.LENGTH_LONG).show();
            return;

        }
        if (TextUtils.isEmpty(address))
        {
            Toast.makeText(getApplicationContext(), " Please Enter Address", Toast.LENGTH_LONG).show();
            return;

        }
        if (TextUtils.isEmpty(address))
        {
            Toast.makeText(getApplicationContext(), " Please Enter Address", Toast.LENGTH_LONG).show();
            return;

        }

        if (TextUtils.isEmpty(mobile_no))
        {
            Toast.makeText(getApplicationContext(), "Please Enter Mobile Number", Toast.LENGTH_LONG).show();
            return;

        }
        if (!mobile_no.matches("\\d+(?:\\.\\d+)?"))
        {
            Toast.makeText(getApplicationContext(), "Please Enter Valid Mobile Number", Toast.LENGTH_LONG).show();
            return;

        }


        if (mobile_no.length()!= 10)
        {
            Toast.makeText(getApplicationContext(), " Please Enter Correct 10 digit Mobile Number", Toast.LENGTH_LONG).show();
            return;

        }


        if (TextUtils.isEmpty(email))
        {
            Toast.makeText(getApplicationContext(), " Please Enter Mail Address", Toast.LENGTH_LONG).show();
            return;

        }


        if (!email.contains("@"))
        {
            Toast.makeText(getApplicationContext(), " Please Enter Valid Email Address", Toast.LENGTH_LONG).show();
            return;

        }

        if (TextUtils.isEmpty(password))
        {
            Toast.makeText(getApplicationContext(), " Please Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6)
        {
            Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(confirm_password))
        {
            Toast.makeText(getApplicationContext(), " Please  Enter Confirm password!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!password.equals(confirm_password))
        {
            Toast.makeText(getApplicationContext(), "Password not matching with confirm Password!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(Date_of_birth))
        {
            Toast.makeText(getApplicationContext(), "Enter Date of Birth in this format dd/mm/yyyy!", Toast.LENGTH_LONG).show();
            return;
        }
      /* if (!Date_of_birth.matches("d+(?://d+//d+)?"))
        {
            Toast.makeText(getApplicationContext(), " Please Enter Date of Birth in this format dd/mm/yyyy!", Toast.LENGTH_SHORT).show();
            return;
        }*/


        progressBar.setVisibility(View.VISIBLE);

        //create user
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>()
                 {
                    @Override
                    public void onComplete(Task<AuthResult> task)
                    {
                        Toast.makeText(SignupActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful())
                        {
                            Toast.makeText(SignupActivity.this, "Authentication failed." + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            createUser(name, address, mobile_no, email, Date_of_birth);
                            Intent i= new Intent(SignupActivity.this,EmailLoginAccountActivity.class);
                            startActivity(i);

                        }
                    }
                });


    }


}

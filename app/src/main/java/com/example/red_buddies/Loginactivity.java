package com.example.red_buddies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

public class Loginactivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private Button butt;
    private EditText email;
    private EditText password;
    private Button newu;
    private TextView forget1;
    private ProgressDialog progressDialog;
    String email_log,pass_log;
// ...
// Initialize Firebase Auth


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

     if (user != null&&firebaseAuth.getCurrentUser().isEmailVerified()==true) {

         Intent i=new Intent(Loginactivity.this,
                 RED_BUDDIES.class);
         startActivity(i);
         //invoke the SecondActivity.

         finish();
         //the current activity will get finished.
        }

     //   if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED) {
      //      ActivityCompat.requestPermissions((Activity) getApplicationContext(), new String[]{Manifest.permission.INTERNET}, 1);
       // }

        forget1=(TextView) findViewById(R.id.forgettext);
        butt = (Button) findViewById(R.id.button);
        newu =(Button)findViewById(R.id.newuser);

        newu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(Loginactivity.this,
                        Registration.class);
                startActivity(i);
                //invoke the SecondActivity.

               // finish();
                //the current activity will get finished.
            }
        });

        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.pawword);
    //   final String e=(String) email.getText().toString();
     //  final String p=(String) password.getText().toString();


        forget1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vi) {
                //    Toast.makeText(Loginactivity.this, email.getText().toString()+"g", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(Loginactivity.this,
                        Forgetpassword.class);

                startActivity(i);
                //invoke the SecondActivity.


                //the current activity will get finished.
            }
        });

        progressDialog = new ProgressDialog(this , R.style.MyAlertDialogStyle);

        progressDialog.setMessage("Please wait ...");

        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vi) {

            //    Toast.makeText(Loginactivity.this, email.getText().toString()+"g", Toast.LENGTH_SHORT).show();
                validates();
            }
        });



    }
    private void validates() {  //email and password validation

       email_log=email.getText().toString().trim();
       pass_log=password.getText().toString().trim();

        if(email_log.isEmpty())
        {
            Toast.makeText(Loginactivity.this,"Email cannot be left blank",Toast.LENGTH_SHORT).show();

        }
        if(pass_log.isEmpty())
        {
            Toast.makeText(Loginactivity.this,"Password cannot be left blank",Toast.LENGTH_SHORT).show();

        }
        else {
            progressDialog.show();
            firebaseAuth.signInWithEmailAndPassword(email_log, pass_log).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        checkEmailVerification();
                        progressDialog.dismiss();

                    }

                    else {
                        progressDialog.dismiss();
                        try
                        {
                            throw task.getException();
                        }
                        // if user enters wrong email.
                        catch (FirebaseAuthInvalidUserException invalidEmail)
                        {
                            Toast.makeText(Loginactivity.this, "Wrong Email", Toast.LENGTH_SHORT).show();

                            // TODO: take your actions!
                        }
                        // if user enters wrong password.
                        catch (FirebaseAuthInvalidCredentialsException wrongPassword)
                        {
                            Toast.makeText(Loginactivity.this, "Wrong Password", Toast.LENGTH_SHORT).show();

                            // TODO: Take your action
                        }
                        catch (Exception e) {
                         //   Log.d(TAG, "onComplete: " + e.getMessage());

                            Toast.makeText(Loginactivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                }

            });
        }
    }

    private void checkEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        Boolean emailflag = firebaseUser.isEmailVerified();

  //      startActivity(new Intent(Loginactivity.this, SecondActivity.class));

        if(emailflag){
            Toast.makeText(this, "Login Sucessfully", Toast.LENGTH_SHORT).show();

            Intent i=new Intent(Loginactivity.this,
                    RED_BUDDIES.class);

            startActivity(i);
            //invoke the SecondActivity.

            finish();
            //the current activity will get finished.
       }
        else{
            Toast.makeText(this, "Verify your email", Toast.LENGTH_SHORT).show();
           firebaseAuth.signOut();
        }
    }

}
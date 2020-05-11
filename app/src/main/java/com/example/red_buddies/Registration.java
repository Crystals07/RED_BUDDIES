package com.example.red_buddies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registration extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private Button butt1;
    private ProgressDialog progressDialog;
    private EditText username,userphone,useremail,userpassword;
     String sname,sphone,semail,spassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        firebaseAuth = FirebaseAuth.getInstance();
        butt1 = (Button) findViewById(R.id.butt_reg);

        useremail = (EditText) findViewById(R.id.emai_reg);
        userpassword = (EditText) findViewById(R.id.pass_reg);

        userphone=(EditText)findViewById(R.id.phone_id);
        username=(EditText)findViewById(R.id.name_id);


        //    final String e = (String) email1.getText().toString();
        //    final String p = (String) password1.getText().toString();

        progressDialog = new ProgressDialog(this , R.style.MyAlertDialogStyle);

        progressDialog.setMessage("Please wait ...");



        butt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Log.d("name",sname);
                validates();
            }
        });

    }


    private void validates() {

        sname=username.getText().toString().trim();
        semail=useremail.getText().toString().trim();
        spassword=userpassword.getText().toString().trim();

//        sage=userage.getText().toString().trim();
        sphone=userphone.getText().toString().trim();

             if(sname.isEmpty()||sphone.isEmpty()||semail.isEmpty()||spassword.isEmpty())
             {
                        Toast.makeText(Registration.this,"Required field cannot be left empty", Toast.LENGTH_SHORT).show();

             }

             else {
            progressDialog.show();
//                 Toast.makeText(Registration.this,sblood, Toast.LENGTH_LONG).show();
            firebaseAuth.createUserWithEmailAndPassword(semail, spassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        progressDialog.dismiss();
                        // checkEmailVerification();
                        Toast.makeText(Registration.this, "Account Created", Toast.LENGTH_SHORT).show();
                        sendEmailVerification();
                        Intent i=new Intent(Registration.this,
                                Loginactivity.class);

                        startActivity(i);
                        //invoke the SecondActivity.

                        finish();
                        //the current activity will get finished.

                    } else {
                        progressDialog.dismiss();

                        try
                        {
                            throw task.getException();
                        }
                        // if user enters wrong email.
                        catch (FirebaseAuthWeakPasswordException weakPassword)
                        {
                            Toast.makeText(Registration.this, "Weak password", Toast.LENGTH_SHORT).show();

                            // TODO: take your actions!
                        }
                        // if user enters wrong password.
                        catch (FirebaseAuthInvalidCredentialsException malformedEmail)
                        {
                            Toast.makeText(Registration.this, "Email is malformed", Toast.LENGTH_SHORT).show();
                            // TODO: Take your action
                        }
                        catch (FirebaseAuthUserCollisionException existEmail)
                        {
                            Toast.makeText(Registration.this, "Email Already exist", Toast.LENGTH_SHORT).show();

                            // TODO: Take your action
                        }
                        catch (Exception e) {
                           // Log.d(TAG, "onComplete: " + e.getMessage());
                            Toast.makeText(Registration.this, "Signup Fail", Toast.LENGTH_SHORT).show();
                        }
                    }
                }


            });
        }
    }

    private void sendEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser!=null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        sendUserdata();
                        Toast.makeText(Registration.this, "Successfully Registered, Verification mail sent!", Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();

                    }else{
                        Toast.makeText(Registration.this, "Verification mail has'nt been sent!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

private void sendUserdata(){
    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    DatabaseReference myref=firebaseDatabase.getReference("Registered");
    Userclass userclass=new Userclass(sname,sphone,semail,spassword,10);
    myref.child(firebaseAuth.getUid()).setValue(userclass);
}

}
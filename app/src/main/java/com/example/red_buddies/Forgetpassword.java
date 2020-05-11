package com.example.red_buddies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forgetpassword extends AppCompatActivity {
 private EditText emailrec;
 private Button buttfor;
 FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);
        emailrec=(EditText)findViewById(R.id.erecv);
        buttfor=(Button)findViewById(R.id.buttrec);
        firebaseAuth=FirebaseAuth.getInstance();

        buttfor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        String useremail=emailrec.getText().toString().trim();
        if(useremail.equals(""))
        {
            Toast.makeText(Forgetpassword.this,"Please enter email",Toast.LENGTH_SHORT).show();
        }

        else {

            firebaseAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(Forgetpassword.this,"Password recover link has been send to your email",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(Forgetpassword.this,Loginactivity.class);
                    startActivity(i);
                    finish();

                    }

                    else {
                        Toast.makeText(Forgetpassword.this,"Failed to send recovery email",Toast.LENGTH_SHORT).show();

                    }
                }
            });

        }

            }
        });
    }
}

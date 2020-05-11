package com.example.red_buddies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RED_BUDDIES extends AppCompatActivity {
Button logout;
ProgressDialog progressDialog;
FirebaseAuth firebaseAuth;
Button button1,view_donar;
String suid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_red__buddies);

        firebaseAuth = FirebaseAuth.getInstance();

        suid=firebaseAuth.getUid();

        view_donar=(Button)findViewById(R.id.viewdonar);
        logout = (Button) findViewById(R.id.logout);
        button1 = (Button) findViewById(R.id.insert);

        progressDialog = new ProgressDialog(this , R.style.MyAlertDialogStyle);

        progressDialog.setMessage("Please wait ...");


        view_donar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i=new Intent(RED_BUDDIES.this,Donarviews.class);
                startActivity(i);

            }
        });

        button1.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                progressDialog.show();

                if(firebaseAuth.getCurrentUser().isEmailVerified()==true) {
                    FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
                    DatabaseReference jrefsrc = firebaseDatabase.getReference().child("Registered").child(suid);


                    // Read from the database

                    jrefsrc.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.child("blood").exists()) {
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Already Registered", Toast.LENGTH_SHORT).show();
                            } else {
                                progressDialog.dismiss();
                                startActivity(new Intent(getApplicationContext(), Registerdonar.class));
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }

                else { progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(),"Verify your Email",Toast.LENGTH_SHORT).show();
                }
                //  finish();
             // fun();
            }
        });



        logout.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                firebaseAuth.signOut();

                Intent i=new Intent(RED_BUDDIES.this,Loginactivity.class);
                progressDialog.dismiss();
                startActivity(i);
                finish();
            }

        });
    }

}

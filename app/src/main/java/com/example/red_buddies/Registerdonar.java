package com.example.red_buddies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class Registerdonar extends AppCompatActivity {
    ProgressDialog progressDialog;
    Button register_donar;
    EditText editText;
    String sphone,sname,sblood,scity,suid;
    Spinner spinner;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference jrefsrc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerdonar);

        firebaseAuth=FirebaseAuth.getInstance();

        spinner=(Spinner) findViewById(R.id.blood_spinner2);


        editText=(EditText) findViewById(R.id.city_donar);

        register_donar = (Button) findViewById(R.id.butt_donar);

        progressDialog = new ProgressDialog(this , R.style.MyAlertDialogStyle);

        progressDialog.setMessage("Please wait ...");



        firebaseDatabase = FirebaseDatabase.getInstance();
        //search user value

        suid=firebaseAuth.getUid();

        jrefsrc=firebaseDatabase.getReference().child("Registered").child(suid);




           spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
               @Override
               public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                   sblood=spinner.getSelectedItem().toString();
                   Toast.makeText(Registerdonar.this,sblood+"Selected",Toast.LENGTH_SHORT).show();

               }

               @Override
               public void onNothingSelected(AdapterView<?> parent) {
                   Toast.makeText(Registerdonar.this,"Please select something",Toast.LENGTH_LONG).show();
               }
           });




        register_donar.setOnClickListener(new View.OnClickListener() {


            @Override

            public void onClick(View v) {

             validate();

        }
    });

    }
    private void validate()
        {

            scity=editText.getText().toString();

            if(scity.isEmpty()||sblood.equals("Blood Group"))
            {
                Toast.makeText(Registerdonar.this,"Please fill all details",Toast.LENGTH_SHORT).show();
            }
            else
            {   progressDialog.show();
                jai();

            }
        }





    private void jai(){


        //search user valu


        // Read from the database
        jrefsrc.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                sname=dataSnapshot.child("name").getValue().toString();
                sphone=dataSnapshot.child("phone").getValue().toString();
                reg();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
             //   Toast.makeText(Registerdonar.this,"Failed",Toast.LENGTH_LONG).show();

            }
        });

    }


    private void reg() {

           // Toast.makeText(Registerdonar.this,sname+"-"+sphone,Toast.LENGTH_SHORT).show();

            DatabaseReference myrefd = firebaseDatabase.getReference().child("Donars");
            Userclass userclass=new Userclass(sname,sphone);
            myrefd.child(scity).child(sblood).child(suid).setValue(userclass);
            Userclass userclass1=new Userclass(sname,sphone,sblood,scity);
            jrefsrc.setValue(userclass1);
            progressDialog.dismiss();
            Toast.makeText(Registerdonar.this, "Sucessfully Registered", Toast.LENGTH_SHORT).show();
            Intent i=new Intent(Registerdonar.this,RED_BUDDIES.class);
            startActivity(i);
            finish();

        }
}

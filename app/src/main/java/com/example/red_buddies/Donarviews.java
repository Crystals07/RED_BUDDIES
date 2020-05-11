package com.example.red_buddies;

//import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
//import android.view.View;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
//import android.widget.TextView;
//import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;

public class Donarviews extends AppCompatActivity  {

    Button searchbutton;
    Spinner spinner;
    EditText citysearch;
    String dsblood,dscity;


   /*
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    DatabaseReference myRef;
    String name,phone,blood,city,filtercity;
//TextView TEXT1,TEXT2, TEXT3,TEXT4, TEXT5,TEXT6;
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donarviews);
        searchbutton=(Button)findViewById(R.id.search);
        citysearch=(EditText)findViewById(R.id.viewcity);
        spinner=findViewById(R.id.spinner_search);

//        dscity=citysearch.getText().toString();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dsblood=spinner.getSelectedItem().toString();
              //  Toast.makeText(getApplicationContext(),dsblood+"Selected",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(),"Please select something",Toast.LENGTH_LONG).show();
            }
        });


        searchbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(validate()) {

                    Intent intent = new Intent(Donarviews.this, Long_list.class);

                    intent.putExtra("Citys",dscity);

                    intent.putExtra("Bloods",dsblood);

                    startActivity(intent);
                }

                else{

                    Toast.makeText(getApplicationContext(),"Please fill all details",Toast.LENGTH_LONG).show();

                }


                //progressDialog.show();
                // checkdonar();
            }
        });
    }

    boolean validate()
    {
        dscity=citysearch.getText().toString();

        if(dscity.isEmpty()||dsblood.equals("Blood Group"))
        {
            return false;
        }

        else {
            return true;
        }
    }




  /*      TEXT1=(TextView)findViewById(R.id.textView1);
        TEXT2=(TextView)findViewById(R.id.textView2);
        TEXT3=(TextView)findViewById(R.id.textView3);
        TEXT4=(TextView)findViewById(R.id.textView4);
        TEXT5=(TextView)findViewById(R.id.textView5);
        TEXT6=(TextView)findViewById(R.id.textView6);
 //       TEXT1=(TextView)findViewById(R.id.textView);

        progressDialog = new ProgressDialog(this , R.style.MyAlertDialogStyle);

        progressDialog.setMessage("Please wait ...");

        searchbutton.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            progressDialog.show();
    checkdonar();
        }
    });

    }

    private void checkdonar() {

        String suid="eNbXOfLJLdN72e8WZUfPQqoGCng2";

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        filtercity=citysearch.getText().toString().trim();

        String k="Donar -"+filtercity;


        myRef = firebaseDatabase.getReference().child(k).child(suid);
        // Read from the database
       myRef.addValueEventListener(new ValueEventListener() {

           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                name=dataSnapshot.child("name").getValue().toString();
                city=dataSnapshot.child("city").getValue().toString();
                phone=dataSnapshot.child("phone").getValue().toString();
                blood=dataSnapshot.child("blood").getValue().toString();

               progressDialog.dismiss();

                TEXT2.setText("Name - "+name);
                TEXT3.setText("City - "+city);
                TEXT4.setText("Phone - "+phone);
                TEXT5.setText("Blood - "+blood);

           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {
               progressDialog.dismiss();

    }
       });
    */

}





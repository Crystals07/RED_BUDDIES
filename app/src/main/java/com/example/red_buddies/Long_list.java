package com.example.red_buddies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.Manifest;
import android.app.ProgressDialog;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
//import java.util.ConcurrentModificationException;


public class Long_list extends AppCompatActivity  {

    ArrayList<String> list = new ArrayList<String>();

    ArrayList<String> phone=new ArrayList<>();

    Adapterviewdonar adapterviewdonar;

    FirebaseAuth firebaseAuth;


   Context context;


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_long_list);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.longlist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.MyAlertDialogStyle);

     context=Long_list.this;


        Intent intent=getIntent();

        String city=intent.getStringExtra("Citys");

        String blood=intent.getStringExtra("Bloods");

      //  Toast.makeText(getApplicationContext(),city+"-"+blood,Toast.LENGTH_SHORT).show();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog.setMessage("Please wait ...");

        progressDialog.show();

        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        //    RecyclerView.LayoutManager layoutManager=recyclerView.getLayoutManager();

        adapterviewdonar = new Adapterviewdonar(list, phone,context);

        recyclerView.setAdapter(adapterviewdonar);


        if(ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED)
        {
               ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.CALL_PHONE},1);

        }



        DatabaseReference mref = firebaseDatabase.getReference().child("Donars").child(city).child(blood);



             mref.addValueEventListener(new ValueEventListener() {


                 @Override

                 public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                     list.clear();
                     phone.clear();

                     if (dataSnapshot.exists())
                     {
                         for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                             String s1 = dataSnapshot1.child("name").getValue().toString();
                             String s2 = dataSnapshot1.child("phone").getValue().toString();
                             list.add(s1);
                             phone.add(s2);
                             progressDialog.dismiss();


                         }

                 }

                     else {

                         Toast.makeText(getApplicationContext(),"No result",Toast.LENGTH_SHORT).show();
                          list.clear();
                          phone.clear();
                          progressDialog.dismiss();
                          list.add("No Result found");
                          phone.add("");
                     }

                     adapterviewdonar.notifyDataSetChanged();

                 }

                 @Override
                 public void onCancelled(@NonNull DatabaseError databaseError) {

                 }
             });

         }

    }


package com.my_sokhra.selibrah.mysokhra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Mycmd extends AppCompatActivity {

    KindAdapter adapter;
    private DatabaseReference mDatabase;
    RecyclerView recyclerView;
    public static String menuname;
    public static String restoname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycmd);
        Bundle extras = getIntent().getExtras();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        restoname = prefs.getString("Rname", "no id");
        menuname = extras.getString("Mname");


        mDatabase = FirebaseDatabase.getInstance().getReference("RestoList").child(restoname).child("MenuList").child(menuname).child("kind");

        // set up the RecyclerView
        final List<Kind> kindList = new ArrayList<>();
        recyclerView = findViewById(R.id.kindrecylvi);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull com.google.firebase.database.DataSnapshot dataSnapshot) {
                kindList.clear();
                for (DataSnapshot npsnapshot : dataSnapshot.getChildren()) {

                    Kind l = npsnapshot.getValue(Kind.class);
                    kindList.add(l);
                }
                adapter = new KindAdapter(kindList);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}

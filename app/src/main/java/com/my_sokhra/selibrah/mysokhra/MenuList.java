package com.my_sokhra.selibrah.mysokhra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MenuList extends AppCompatActivity {

    MenuAdapter adapter;
    private DatabaseReference mDatabase;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuview);


        Bundle extras = getIntent().getExtras();

        String name = extras.getString("Rname");


        mDatabase = FirebaseDatabase.getInstance().getReference("RestoList").child(name).child("MenuList");


        // set up the RecyclerView
        final List<Menuitem> menuList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclvii);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull com.google.firebase.database.DataSnapshot dataSnapshot) {
                menuList.clear();
                for (DataSnapshot npsnapshot : dataSnapshot.getChildren()) {

                    Menuitem l = npsnapshot.getValue(Menuitem.class);
                    menuList.add(l);
                }
                adapter = new MenuAdapter(menuList);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}

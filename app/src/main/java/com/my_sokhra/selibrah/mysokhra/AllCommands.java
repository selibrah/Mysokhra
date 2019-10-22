package com.my_sokhra.selibrah.mysokhra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.my_sokhra.selibrah.mysokhra.CartList.Key;

public class AllCommands extends AppCompatActivity {
    CommandesAdapter adapter;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private DatabaseReference jDatabase;
    private DatabaseReference uDatabase;
    private DatabaseReference gDatabase;

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_commands);

        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = mAuth.getCurrentUser();
        gDatabase = FirebaseDatabase.getInstance().getReference("usersData");
        uDatabase = gDatabase.child(user.getUid());
        jDatabase = uDatabase.child("cmd");
        mDatabase = jDatabase.child("items");
        findViewById(R.id.floatingActionButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CartList.class);
                startActivity(intent);
                finish();
            }
        });


        // set up the RecyclerView
        final List<Commandes> commandeslist = new ArrayList<>();
        recyclerView = findViewById(R.id.recycleviecommandes);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        jDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull com.google.firebase.database.DataSnapshot dataSnapshot) {
                commandeslist.clear();
                for (DataSnapshot npsnapshot : dataSnapshot.getChildren()) {

                    Commandes l = npsnapshot.getValue(Commandes.class);
                    commandeslist.add(l);
                }
                adapter = new CommandesAdapter(commandeslist);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

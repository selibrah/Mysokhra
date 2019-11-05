package com.my_sokhra.selibrah.mysokhra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CartList extends AppCompatActivity {
    CartAdapter adapter;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private DatabaseReference jDatabase;
    private DatabaseReference uDatabase;
    private DatabaseReference gDatabase;

    RecyclerView recyclerView;
    private int total;
    TextView Carttotal;
    TextView totalprice;
    static String Key;
    private FusedLocationProviderClient fusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        Carttotal = (TextView) findViewById(R.id.carttt);
        totalprice = (TextView) findViewById(R.id.total);
        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = mAuth.getCurrentUser();
        gDatabase = FirebaseDatabase.getInstance().getReference("usersData");
        uDatabase = gDatabase.child(user.getUid());
        jDatabase = uDatabase.child("Cartitem");
        mDatabase = jDatabase.child("items");
        findViewById(R.id.home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), profActivity.class);
                startActivity(intent);
                finish();
            }
        });
        findViewById(R.id.rtrn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        // set up the RecyclerView
        final List<itemCart> cartlist = new ArrayList<>();
        recyclerView = findViewById(R.id.recycleviecart);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull com.google.firebase.database.DataSnapshot dataSnapshot) {
                cartlist.clear();
                total = 0;
                for (DataSnapshot npsnapshot : dataSnapshot.getChildren()) {

                    itemCart l = npsnapshot.getValue(itemCart.class);
                    total = total + Integer.valueOf(l.prix)*l.nbr;
                    l.total = total;
                    cartlist.add(l);
                }
                jDatabase.child("total").setValue(total);
                Carttotal.setText(String.valueOf(total)+" Dh");
                totalprice.setText(String.valueOf(total + 10) + " Dh");
                //Toast.makeText(getApplicationContext(), "Total "+total, Toast.LENGTH_SHORT).show();
                if(total == 0)
                {
                    findViewById(R.id.cmdbtn).setVisibility(View.GONE);
                }
                else
                {
                    findViewById(R.id.cmdbtn).setVisibility(View.VISIBLE);
                }
                adapter = new CartAdapter(cartlist);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        findViewById(R.id.cmdbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getlocation();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = new Date();
                Key = uDatabase.child("cmd").push().getKey();
                    uDatabase.child("cmd").child(Key).setValue(cartlist);
                    uDatabase.child("cmd").child(Key).child("total").setValue(total);
                    uDatabase.child("cmd").child(Key).child("name").setValue(formatter.format(date));
                    uDatabase.child("cmd").child(Key).child("ttodlv").setValue("25");
                    gDatabase.child("Commandes").child(Key).setValue(cartlist);
                    gDatabase.child("Commandes").child(Key).child("total").setValue(total);
                    gDatabase.child("Commandes").child(Key).child("name").setValue(formatter.format(date));
                    gDatabase.child(user.getUid()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull com.google.firebase.database.DataSnapshot dataSnapshot) {

                        DataSnapshot ds = dataSnapshot;
                        gDatabase.child("Commandes").child(Key).child("num").setValue(ds.child("num").getValue());
                        gDatabase.child("Commandes").child(Key).child("Clientname").setValue(ds.child("name").getValue());
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                mDatabase.removeValue();
                Intent intent = new Intent(CartList.this, AllCommands.class);
                startActivity(intent);



            }
        });


    }
    public void getlocation()
    {
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        Toast.makeText(getApplicationContext(), "Done perfectly", Toast.LENGTH_SHORT).show();
                        if (location != null) {
                            // Logic to handle location object
                            uDatabase.child("location").child("latitude").setValue(location.getLatitude());
                            uDatabase.child("location").child("longitude").setValue(location.getLongitude());
                            uDatabase.child("cmd").child(Key).child("location").child("latitude").setValue(location.getLatitude());
                            uDatabase.child("cmd").child(Key).child("location").child("longitude").setValue(location.getLongitude());
                            uDatabase.child("Commandes").child(Key).child("location").child("latitude").setValue(location.getLatitude());
                            uDatabase.child("Commandes").child(Key).child("location").child("longitude").setValue(location.getLongitude());

                        }
                    }
                });
    }
}
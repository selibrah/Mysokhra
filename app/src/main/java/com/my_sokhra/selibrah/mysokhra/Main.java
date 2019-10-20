package com.my_sokhra.selibrah.mysokhra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class Main extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commandes);
        mAuth = FirebaseAuth.getInstance();

      //  Toast.makeText(Main.this,mAuth.getCurrentUser().getDisplayName() + mAuth.getCurrentUser().getPhoneNumber(), Toast.LENGTH_SHORT).show();


        findViewById(R.id. btnfood).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Main.this, RestoLis.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.signout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Intent intent = new Intent(Main.this, LoginActivity.class);
                startActivity(intent);
                finish();

            }
        });
        findViewById(R.id.cart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main.this, CartList.class);
                startActivity(intent);
            }
        });

    }
    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "Tap to exit", Toast.LENGTH_SHORT).show();
        finish();
        System.exit(0);


    }
}

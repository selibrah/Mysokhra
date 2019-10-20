package com.my_sokhra.selibrah.mysokhra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {

    private FirebaseAuth mAuth;
    String TAG = "Signup";
    EditText name ;
    EditText numberphone;
    EditText editemail;
    EditText editpassword;
    private DatabaseReference mDatabase;
    User myuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        editemail = findViewById(R.id.emailsign);
        editpassword = findViewById(R.id.mdpsign);
        name = findViewById(R.id.Fullnm);
        numberphone = findViewById(R.id.phonenm);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("usersData");
        myuser = new User();


        findViewById(R.id.signupbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = editemail.getText().toString();
                final String password = editpassword.getText().toString();
                final String username = name.getText().toString();
                final String mobile = numberphone.getText().toString();

                myuser.init();
                myuser.setName(username);
                myuser.setEmail(email);


                createAccount(mobile, username,email , password);
            }
        });
        findViewById(R.id.signinbtnup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Signup.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void createAccount(final String mobile, final String username, String email, String password) {
        Log.d(TAG, "createAccount:" + email);
        if (!validateForm(email, password)) {
            return;
        }

        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            mDatabase.child(user.getUid()).setValue(myuser);
                            Intent intent = new Intent(Signup.this, PhoneLogin.class);
                            intent.putExtra("mobile",mobile);
                            startActivity(intent);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Signup.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
        // [END create_user_with_email]
    }

    private boolean validateForm(String email, String password) {
        boolean valid = true;

        if (TextUtils.isEmpty(email)) {
            editemail.setError("Required.");
            valid = false;
        } else {
            editemail.setError(null);
        }

        if (TextUtils.isEmpty(password)) {
            editpassword.setError("Required.");
            valid = false;
        } else {
            editpassword.setError(null);
        }

        return valid;
    }



}




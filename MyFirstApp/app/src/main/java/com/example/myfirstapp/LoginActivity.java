package com.example.myfirstapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button loginBtn;
    private Button regBtn;
    private Button guestBtn;
    private EditText emailText;
    private EditText passwordText;
    public boolean isLogged;

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference ref = database.getReference("users");

    protected User user = User.getInstance();
    protected Shop shop = Shop.getInstance();

    void addNewUser(String email) {
        user.resetUser();
        shop.resetShop();
        String username = email.split("@")[0];
        user.setEmail(email);
        DatabaseReference localRef = ref.child(username);
        user.setClicks(0);
        int rez[] = user.getAllUserInfo();
        localRef.child("goldBars").setValue(rez[0]);
        localRef.child("totalMoneyThisAscension").setValue(rez[1]);
        localRef.child("clicks").setValue(rez[2]);
        localRef.child("currentMoneyIncrease").setValue(rez[3]);
        localRef.child("currentMoneyAmount").setValue(rez[4]);
        localRef.child("currentMoneyPerSecond").setValue(rez[5]);
        localRef.child("shop").child("priceClick").setValue(shop.getListClickPrice());
        localRef.child("shop").child("priceSecond").setValue(shop.getListPriceSecond());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        loginBtn = (Button) findViewById(R.id.loginBtn);
        regBtn = (Button) findViewById(R.id.regBtn);
        guestBtn = (Button) findViewById(R.id.guestBtn);
        emailText = (EditText) findViewById(R.id.emailEditText);
        passwordText = (EditText) findViewById(R.id.passwordEditText);

        if ( mAuth.getCurrentUser() == null){
            isLogged = false;
        }

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this,
                        "Logging in...", Toast.LENGTH_LONG).show();

                startLogin();
            }
        });

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this,
                        "Registering...", Toast.LENGTH_LONG).show();
                addNewUser(emailText.getEditableText().toString());
                startRegister();
            }
        });

        guestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isLogged = false;
                Toast.makeText(LoginActivity.this,
                        "Playing as guest...", Toast.LENGTH_LONG).show();
                // TODO - get data from file for guest user
                startActivity(new Intent(LoginActivity.this, Gameplay.class));
            }
        });
    }

    private void startLogin() {
        final String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {

            Toast.makeText(LoginActivity.this,
                    "Enter E-mail and password", Toast.LENGTH_LONG).show();

        } else {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                //Success
                                Toast.makeText(LoginActivity.this,
                                        "Login Successful", Toast.LENGTH_LONG).show();

                                String username = email.split("@")[0];
                                user.setEmail(email);
                                DatabaseReference localRef = ref.child(username);
                                if (localRef != null) {
                                    localRef.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            user.getDataFromDatabase(dataSnapshot);
                                            shop.getDataFromDatabase(dataSnapshot);
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                }
                                isLogged = true;
                                startActivity(new Intent(LoginActivity.this, Gameplay.class));

                            } else {
                                //Fail
                                Toast.makeText(LoginActivity.this,
                                        "Login Error", Toast.LENGTH_LONG).show();

                            }
                        }
                    });
        }

    }

    private void startRegister() {
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {

            Toast.makeText(LoginActivity.this,
                    "Enter E-mail and password", Toast.LENGTH_LONG).show();

        } else {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this,
                                        "Register successful, you may now log in", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(LoginActivity.this,
                                        "E-mail already in use or invalid", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Toast.makeText(LoginActivity.this,
                    "Already logged in...", Toast.LENGTH_LONG).show();
            String username = currentUser.getEmail().split("@")[0];
            user.setEmail(currentUser.getEmail());
            DatabaseReference localRef = ref.child(username);
            localRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    user.getDataFromDatabase(dataSnapshot);
                    shop.getDataFromDatabase(dataSnapshot);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            isLogged = true;
            startActivity(new Intent(LoginActivity.this, Gameplay.class));
        } else {
            // TODO - get data from file for guest user
            user.resetUser();
            shop.resetShop();
        }
    }

}


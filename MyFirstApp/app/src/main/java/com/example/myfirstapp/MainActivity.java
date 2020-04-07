package com.example.myfirstapp;

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

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button loginBtn;
    private Button regBtn;
    private EditText emailText;
    private EditText passwordText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        loginBtn = (Button) findViewById(R.id.loginBtn);
        regBtn = (Button) findViewById(R.id.regBtn);
        emailText = (EditText) findViewById(R.id.emailEditText);
        passwordText = (EditText) findViewById(R.id.passwordEditText);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLogin();
            }
        });

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRegister();
            }
        });

    }

    private void startLogin(){

        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){

            Toast.makeText(MainActivity.this,
                    "Enter E-mail and password", Toast.LENGTH_LONG).show();

        }else{
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                //Success
                                Toast.makeText(MainActivity.this,
                                        "Login Successful", Toast.LENGTH_LONG).show();

                            } else {
                                //Fail
                                Toast.makeText(MainActivity.this,
                                        "Login Error", Toast.LENGTH_LONG).show();

                            }
                        }
                    });
        }

    }

    private void startRegister(){
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){

            Toast.makeText(MainActivity.this,
                    "Enter E-mail and password", Toast.LENGTH_LONG).show();

        }else{
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(MainActivity.this,
                                        "Register successful, you may now log in", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(MainActivity.this,
                                        "E-mail already in use or invalid", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
    }
}


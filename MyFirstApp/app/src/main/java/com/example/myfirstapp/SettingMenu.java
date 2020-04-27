package com.example.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class SettingMenu extends AppCompatActivity {

    private ImageButton backBtn;
    private Button helpBtn;
    private Button statsBtn;
    private Button settingsBtn;
    private Button logoutBtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_menu);
        mAuth = FirebaseAuth.getInstance();

        backBtn = (ImageButton) findViewById(R.id.backBtn);
        helpBtn = (Button) findViewById(R.id.helpBtn);
        statsBtn = (Button) findViewById(R.id.statsBtn);
        settingsBtn = (Button) findViewById(R.id.settingsBtn);
        logoutBtn = (Button) findViewById(R.id.logoutBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingMenu.this, Gameplay.class));
            }
        });

        helpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SettingMenu.this,
                        "Help btn works", Toast.LENGTH_LONG).show();
            }
        });

        statsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SettingMenu.this,
                        "Stats btn works", Toast.LENGTH_LONG).show();
            }
        });

        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SettingMenu.this,
                        "Settings btn works", Toast.LENGTH_LONG).show();
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mAuth.getCurrentUser() != null) {
                    FirebaseAuth.getInstance().signOut();
                    Toast.makeText(SettingMenu.this,
                            "Logging out", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(SettingMenu.this, LoginActivity.class));
                }
                else{
                    Toast.makeText(SettingMenu.this,
                            "You are currently a Guest", Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}

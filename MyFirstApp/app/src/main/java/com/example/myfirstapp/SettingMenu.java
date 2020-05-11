package com.example.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SettingMenu extends AppCompatActivity {

    private ImageButton backBtn;
    private Button helpBtn;
    private Button statsBtn;
    private Button settingsBtn;
    private Button logoutBtn;
    private FirebaseAuth mAuth;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference ref = database.getReference("users");

    private User user = User.getInstance();

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
                    String username = mAuth.getCurrentUser().getEmail().split("@")[0];
                    ref.child(username).child("clicks").setValue(user.getClicks());
                    ref.child(username).child("currentMoneyAmount").setValue(user.getCurrentMoneyAmount());
                    ref.child(username).child("currentMoneyIncrease").setValue(user.getCurrentMoneyIncrease());
                    ref.child(username).child("currentMoneyPerSecond").setValue(user.getCurrentMoneyPerSecond());
                    ref.child(username).child("goldBars").setValue(user.getGoldBars());
                    ref.child(username).child("totalMoneyThisAscension").setValue(user.getTotalMoneyThisAscension());
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

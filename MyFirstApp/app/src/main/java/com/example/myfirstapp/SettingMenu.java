package com.example.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;

public class SettingMenu extends AppCompatActivity {

    private ImageView backBtn;
    private ImageView helpBtn;
    private ImageView statsBtn;
    private ImageView settingsBtn;
    private ImageView logoutBtn;
    private ImageView leaderboardBtn;

    private FirebaseAuth mAuth;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference ref = database.getReference("users");

    private User user = User.getInstance();
    private Shop shop = Shop.getInstance();
    private Leaderboard leaderboard = Leaderboard.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_menu);
        mAuth = FirebaseAuth.getInstance();

        backBtn = (ImageView) findViewById(R.id.backBtn);
        helpBtn = (ImageView) findViewById(R.id.helpBtn);
        statsBtn = (ImageView) findViewById(R.id.statsBtn);
        settingsBtn = (ImageView) findViewById(R.id.settingsBtn);
        logoutBtn = (ImageView) findViewById(R.id.logoutBtn);
        leaderboardBtn = (ImageView) findViewById(R.id.leaderboardBtn);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingMenu.this, Gameplay.class));
            }
        });

        helpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Toast.makeText(SettingMenu.this,
                        "Help btn works", Toast.LENGTH_LONG).show();*/
                startActivity(new Intent(SettingMenu.this, HelpActivity.class));
            }
        });

        statsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingMenu.this, InfoActivity.class));
            }
        });

        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingMenu.this, SActivity.class));
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
                    ref.child(username).child("totalMoneyEver").setValue(user.getTotalMoneyEver());
                    ref.child(username).child("shop").child("priceClick").setValue(shop.getListClickPrice());
                    ref.child(username).child("shop").child("priceSecond").setValue(shop.getListPriceSecond());

                    database.getReference("leaderboard").child(username).setValue(user.getTotalMoneyEver());
                    user.resetUser();
                    shop.resetShop();

                    FirebaseAuth.getInstance().signOut();
                    Toast.makeText(SettingMenu.this,
                            "Logging out", Toast.LENGTH_LONG).show();

                    startActivity(new Intent(SettingMenu.this, LoginActivity.class));
                }
                else {
                    Toast.makeText(SettingMenu.this,
                            "You are currently a Guest", Toast.LENGTH_LONG).show();
                }

            }
        });

        leaderboardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAuth.getCurrentUser() != null) {
                    database.getReference("leaderboard").addValueEventListener(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            leaderboard.getDataFromDatabase(dataSnapshot);
                            startActivity(new Intent(SettingMenu.this, LeaderboardActivity.class));
                       }

                       @Override
                       public void onCancelled(@NonNull DatabaseError databaseError) {

                       }
                    });
                }
                else {
                    Toast.makeText(SettingMenu.this,
                            "You are currently a Guest", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}

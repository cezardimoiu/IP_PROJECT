package com.example.myfirstapp;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

public class SActivity extends AppCompatActivity {

    private Button resetAllBtn;
    private CheckBox resetAllCheck;
    private static Switch muteSwitch;

    private ImageView backBtn;

    User myUser = User.getInstance();
    Shop myShop = Shop.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        backBtn = (ImageView) findViewById(R.id.backBtn);

        resetAllBtn = (Button) findViewById(R.id.resetAllBtn);
        resetAllCheck = (CheckBox) findViewById(R.id.resetAllCheck);
        muteSwitch = (Switch) findViewById(R.id.muteSwitch);
        if (myUser.getVolume() == 0f){
            muteSwitch.setChecked(true);
        }

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SActivity.this, SettingMenu.class));
            }
        });

        resetAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(resetAllCheck.isChecked()){
                    Toast.makeText(SActivity.this,
                            "All progress reset",
                            Toast.LENGTH_LONG).show();
                            String email = myUser.getEmail();
                            myUser.resetUser();
                            myShop.resetShop();
                            myUser.setEmail(email);
                            startActivity(new Intent(SActivity.this, Gameplay.class));
                }
                else{
                    Toast.makeText(SActivity.this,
                            "You must check the checkbox first",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        muteSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(muteSwitch.isChecked()){
                    myUser.setVolume(0f);
                }
                else{
                    myUser.setVolume(1f);
                }
            }
        });

    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
        }
    }
}
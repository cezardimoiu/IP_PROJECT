package com.example.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class Gameplay extends AppCompatActivity {

    private TextView moneyText;
    private Button clickBtn;
    private ImageButton settingMenuBtn;
    public static int currentMoneyAmount;
    public static int currentMoneyIncrease = 1;
    public int currentMoneyPerSecond = 1;
    public int currentRefreshTime = 1000; //in ms
    private static boolean timerNotSet = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay);

        clickBtn = (Button) findViewById(R.id.clickBtn);
        settingMenuBtn = (ImageButton) findViewById(R.id.settingMenuBtn);
        moneyText = (TextView) findViewById(R.id.moneyView);
        moneyText.setText(currentMoneyAmount + "$");

        //currentMoneyIncrease = 1;
        //currentMoneyPerSecond = 1;

        clickBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMoney();
                moneyText.setText(currentMoneyAmount + "$");
            }
        });

        settingMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Gameplay.this, SettingMenu.class));
            }
        });

        Timer timer = new Timer();
        if ( timerNotSet ) {
            timerNotSet = false;
            timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    currentMoneyAmount += currentMoneyPerSecond;
                    //moneyText.setText(currentMoneyAmount + "$");
                }
            }, 0, currentRefreshTime);
        }

        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                moneyText.setText(currentMoneyAmount + "$");
            }
        }, 0, currentRefreshTime / 5);

    }

    private void addMoney(){
        currentMoneyAmount += currentMoneyIncrease;
    }
}

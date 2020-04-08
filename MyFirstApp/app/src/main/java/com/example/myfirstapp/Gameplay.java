package com.example.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Gameplay extends AppCompatActivity {

    private TextView moneyText;
    private Button clickBtn;
    private ImageButton settingMenuBtn;
    public static int currentMoneyAmmount;
    public static int currentMoneyIncrease;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay);

        clickBtn = (Button) findViewById(R.id.clickBtn);
        settingMenuBtn = (ImageButton) findViewById(R.id.settingMenuBtn);
        moneyText = (TextView) findViewById(R.id.moneyView);
        moneyText.setText(currentMoneyAmmount + "$");

        currentMoneyIncrease = 1;

        clickBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMoney();
                moneyText.setText(currentMoneyAmmount + "$");
            }
        });

        settingMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Gameplay.this, SettingMenu.class));
            }
        });
    }

    private void addMoney(){
        currentMoneyAmmount += currentMoneyIncrease;
    }
}

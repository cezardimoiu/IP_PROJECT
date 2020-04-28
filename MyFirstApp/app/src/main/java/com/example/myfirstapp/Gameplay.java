package com.example.myfirstapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

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

        ImageView img = (ImageView) findViewById(R.id.clicker);
        //clickBtn = (Button) findViewById(R.id.one);
        settingMenuBtn = (ImageButton) findViewById(R.id.settingMenuBtn);
        moneyText = (TextView) findViewById(R.id.moneyView);
        moneyText.setText(currentMoneyAmount + "$");

        //currentMoneyIncrease = 1;
        //currentMoneyPerSecond = 1;

        /*settingMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Gameplay.this, SettingMenu.class));
                plus_one();
            }
        });*/

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Gameplay.this, Gameplay.class);
                //clickBtn.setVisibility(View.VISIBLE);
                Animation a = AnimationUtils.loadAnimation(Gameplay.this, R.anim.coockie_animation);
                a.setAnimationListener(new SimpleAnimationListener() {
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        addMoney(R.string.clicked);
                        moneyText.setText(currentMoneyAmount + "$");
                    }
                });
                v.startAnimation(a);

                //clickBtn.setVisibility(View.VISIBLE);
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

    @SuppressLint("WrongConstant")
    private void addMoney(int stringID){
        currentMoneyAmount += currentMoneyIncrease;
        Toast toast = new Toast(this);
        toast.setGravity(Gravity.CENTER, 100,100);
        toast.setDuration(0);
        TextView textView = new TextView(this);
        textView.setText(stringID);
        textView.setTextSize(30f);
        textView.setTextColor(Color.BLACK);
        toast.setView(textView);
        toast.show();


    }

}

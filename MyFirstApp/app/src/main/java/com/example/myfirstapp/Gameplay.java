package com.example.myfirstapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Timer;
import java.util.TimerTask;

public class Gameplay extends AppCompatActivity {
    private TextView moneyText;
    private TextView moneyPerSecText;
    private Button clickBtn;
    private Button shopMenuBtn;
    private ImageButton settingMenuBtn;
    private ImageButton powerClickBtn;
    private ImageButton powerSecondBtn;
    private static boolean timerNotSet = true;
    int animX, animY;
    private User myUser = User.getInstance();
    private Shop myShop = Shop.getInstance();
    private DataManipulator dataMan = DataManipulator.getInstance();
    private FirebaseAuth mAuth;
    private static final String SHARED_PREFS =  "Prefs";
    private int data[][];

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay);
        mAuth = FirebaseAuth.getInstance();

        ImageView img = (ImageView) findViewById(R.id.clicker);
        //clickBtn = (Button) findViewById(R.id.one);
        settingMenuBtn = (ImageButton) findViewById(R.id.settingMenuBtn);
        shopMenuBtn = (Button) findViewById(R.id.shopMenuBtn);
        moneyText = (TextView) findViewById(R.id.moneyView);
        moneyText.setText(myUser.getCurrentMoneyAmount() + "$");
        moneyPerSecText = (TextView) findViewById((R.id.moneyPerSecView));
        moneyPerSecText.setText(myUser.getMoneyPerSecond() + "$/sec");

        powerClickBtn = (ImageButton) findViewById(R.id.powerClickBtn);
        powerSecondBtn = (ImageButton) findViewById(R.id.powerSecondBtn);

        img.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    animX = (int) event.getX() + (int) v.getX();
                    animY = (int) event.getY() + (int) v.getY();
                    Intent i = new Intent(Gameplay.this, Gameplay.class);
                    //clickBtn.setVisibility(View.VISIBLE);
                    Animation a = AnimationUtils.loadAnimation(Gameplay.this, R.anim.coockie_animation);
                    a.setAnimationListener(new SimpleAnimationListener() {
                        @Override
                        public void onAnimationEnd(Animation animation) {
                            addMoney(Integer.toString(myUser.getMoneyPerClick()), animX, animY);
                            moneyText.setText(myUser.getCurrentMoneyAmount() + "$");
                        }
                    });
                    v.startAnimation(a);
                }
                return true;
            }
        });

        powerClickBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!myUser.isClickCooldown()){
                    myUser.setPowerClickDuration();
                    Toast.makeText(Gameplay.this,
                            "Double Money/Click for 60 sec", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(Gameplay.this,
                            "Power on cooldown", Toast.LENGTH_LONG).show();
                }
            }
        });

        powerSecondBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!myUser.isSecondCooldown()){
                    myUser.setPowerSecondDuration();
                    Toast.makeText(Gameplay.this,
                            "Double Money/Sec for 60 sec", Toast.LENGTH_LONG).show();
                    moneyPerSecText.setText(myUser.getMoneyPerSecond() + "$/sec");
                }
                else{
                    Toast.makeText(Gameplay.this,
                            "Power on cooldown", Toast.LENGTH_LONG).show();
                }
            }
        });

        settingMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Gameplay.this, SettingMenu.class));
            }
        });

        shopMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Gameplay.this, ShopMenu.class));
            }
        });

        //Update money every 100ms
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                moneyText.setText(myUser.getCurrentMoneyAmount() + "$");

                //TODO - update moneyPerSec
                //moneyPerSecText.setText(myUser.getCurrentMoneyPerSecond() + "$/sec");
            }
        }, 0, 100);

        //TODO - Update local memory every 2 minutes

        if (mAuth.getCurrentUser() == null) {
            Timer timer2 = new Timer();
            timer2.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    //TODO - Must write info
                    dataMan.setSharedPrefs(getSharedPreferences(SHARED_PREFS, 0));

                }
            }, 0, 1200);
        }
        moneyPerSecText.setText(myUser.getMoneyPerSecond() + "$/sec");
    }

    @SuppressLint("WrongConstant")
    private void addMoney(String stringID, int x, int y){
        myUser.addMoney();

        Toast toast = new Toast(this);
        toast.setGravity(Gravity.TOP|Gravity.LEFT, x, y);
        toast.setDuration(0);
        TextView textView = new TextView(this);
        textView.setText("+" + stringID + "$");
        textView.setTextSize(30f);
        textView.setShadowLayer(10,0,0,Color.BLACK);
        textView.setTextColor(Color.WHITE);
        toast.setView(textView);
        toast.show();

    }

}
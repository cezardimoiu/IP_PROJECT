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

import java.util.Timer;
import java.util.TimerTask;

public class Gameplay extends AppCompatActivity {
    private TextView moneyText;
    private TextView moneyPerSecText;
    private Button clickBtn;
    private Button shopMenuBtn;
    private ImageButton settingMenuBtn;
    private static boolean timerNotSet = true;
    int animX, animY;
    private User myUser = User.getInstance();
    private Shop myShop = Shop.getInstance();
    private DataManipulator dataMan = DataManipulator.getInstance();
    private static final String SHARED_PREFS =  "Prefs";
    private int data[][];

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay);

        //TODO - Must update game info

        /* This is for the user */
        /*==============================================================================*/

        // for coins
        SharedPreferences sharedPreferences_1 = getSharedPreferences(SHARED_PREFS, 0);
        myUser.setCurrentMoneyAmount(sharedPreferences_1.getInt
                ("coins", myUser.getCurrentMoneyAmount()));

        // for clicks
        SharedPreferences sharedPreferences_2 = getSharedPreferences(SHARED_PREFS, 0);
        myUser.setClicks(sharedPreferences_2.getInt
                ("clicks", myUser.getClicks()));

        // for currentMoneyIncrease
        SharedPreferences sharedPreferences_3 = getSharedPreferences(SHARED_PREFS, 0);
        myUser.setCurrentMoneyIncrease(sharedPreferences_3.getInt
                ("money_increase", myUser.getCurrentMoneyIncrease()));

        // for currentMoneyPerSecond
        SharedPreferences sharedPreferences_4 = getSharedPreferences(SHARED_PREFS, 0);
        myUser.setCurrentMoneyPerSecond(sharedPreferences_4.getInt
                ("money_per_second", myUser.getCurrentMoneyPerSecond()));

        // for currentRefreshTime
        SharedPreferences sharedPreferences_5 = getSharedPreferences(SHARED_PREFS, 0);
        myUser.setCurrentRefreshTime(sharedPreferences_5.getInt
                ("refresh_time", myUser.getCurrentRefreshTime()));

        //for totakMoneyThisAscension
        SharedPreferences sharedPreferences_6 = getSharedPreferences(SHARED_PREFS, 0);
        myUser.setTotalMoneyThisAscension(sharedPreferences_6.getInt
                ("total_money_this_ascension", myUser.getTotalMoneyThisAscension()));

        // for goldBars
        SharedPreferences sharedPreferences_7 = getSharedPreferences(SHARED_PREFS, 0);
        myUser.setGoldBars(sharedPreferences_7.getInt
                ("gold_bars", myUser.getGoldBars()));
        /*==============================================================================*/


        /*This is for the shop*/
        /* In case Alex decides to use al of the values from the Shop(clicks, prices...)
         *  I will add the commented block of code !!!!!*/
        /*==============================================================================*/


        // for pricesClicks
        data = myShop.getAllShopInfo();
        SharedPreferences sharedPreferencesC0 = getSharedPreferences(SHARED_PREFS, 0);
        data[0][0] = sharedPreferencesC0.getInt("click0", data[0][0]);
       /* SharedPreferences sharedPreferencesC1 = getSharedPreferences(SHARED_PREFS, 0);
        data[0][1] = sharedPreferencesC1.getInt("click1", data[0][1]);
        SharedPreferences sharedPreferencesC2 = getSharedPreferences(SHARED_PREFS, 0);
        data[0][2] = sharedPreferencesC2.getInt("click2", data[0][2]);
        SharedPreferences sharedPreferencesC3 = getSharedPreferences(SHARED_PREFS, 0);
        data[0][3] = sharedPreferencesC3.getInt("click3", data[0][3]);*/

        // for clickBonus
        SharedPreferences sharedPreferencesCB0 = getSharedPreferences(SHARED_PREFS, 0);
        data[1][0] = sharedPreferencesCB0.getInt("clickB0", data[1][0]);
        /*SharedPreferences sharedPreferencesCB1 = getSharedPreferences(SHARED_PREFS, 0);
        data[1][1] = sharedPreferencesCB1.getInt("clickB1", data[1][1]);
        SharedPreferences sharedPreferencesCB2 = getSharedPreferences(SHARED_PREFS, 0);
        data[1][2] = sharedPreferencesCB2.getInt("clickB2", data[1][2]);
        SharedPreferences sharedPreferencesCB3 = getSharedPreferences(SHARED_PREFS, 0);
        data[1][3] = sharedPreferencesCB3.getInt("clickB3", data[1][3]);*/

        // for pricesSecond
        SharedPreferences sharedPreferencesS0 = getSharedPreferences(SHARED_PREFS, 0);
        data[2][0] = sharedPreferencesS0.getInt("second0", data[2][0]);
        /*SharedPreferences sharedPreferencesS1 = getSharedPreferences(SHARED_PREFS, 0);
        data[2][1] = sharedPreferencesS1.getInt("second1", data[2][1]);
        SharedPreferences sharedPreferencesS2 = getSharedPreferences(SHARED_PREFS, 0);
        data[2][2] = sharedPreferencesS2.getInt("second2", data[2][2]);
        SharedPreferences sharedPreferencesS3 = getSharedPreferences(SHARED_PREFS, 0);
        data[2][3] = sharedPreferencesS3.getInt("second3", data[2][3]);*/

        // for secondBonus
        SharedPreferences sharedPreferencesSB0 = getSharedPreferences(SHARED_PREFS, 0);
        data[3][0] = sharedPreferencesSB0.getInt("secondB0", data[3][0]);
        /*SharedPreferences sharedPreferencesSB1 = getSharedPreferences(SHARED_PREFS, 0);
        data[3][1] = sharedPreferencesSB1.getInt("secondB1", data[3][1]);
        SharedPreferences sharedPreferencesSB2 = getSharedPreferences(SHARED_PREFS, 0);
        data[3][2] = sharedPreferencesSB2.getInt("secondB2", data[3][2]);
        SharedPreferences sharedPreferencesSB3 = getSharedPreferences(SHARED_PREFS, 0);
        data[3][3] = sharedPreferencesSB3.getInt("secondB3", data[3][3]);*/
        myShop.setShop(data);

        /*==============================================================================*/

        ImageView img = (ImageView) findViewById(R.id.clicker);
        //clickBtn = (Button) findViewById(R.id.one);
        settingMenuBtn = (ImageButton) findViewById(R.id.settingMenuBtn);
        shopMenuBtn = (Button) findViewById(R.id.shopMenuBtn);
        moneyText = (TextView) findViewById(R.id.moneyView);
        moneyText.setText(myUser.getCurrentMoneyAmount() + "$");
        moneyPerSecText = (TextView) findViewById((R.id.moneyPerSecView));
        moneyPerSecText.setText(myUser.getMoneyPerSecond() + "$/sec");


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
        Timer timer2 = new Timer();
        timer2.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                //TODO - Must write info

                // for coins
                SharedPreferences sharedPreferences_1 =
                        getSharedPreferences(SHARED_PREFS, 0);
                SharedPreferences.Editor editor1 = sharedPreferences_1.edit();
                editor1.putInt("coins", myUser.getCurrentMoneyAmount());
                editor1.commit();

                // for clicks
                SharedPreferences sharedPreferences_2 =
                        getSharedPreferences(SHARED_PREFS, 0);
                SharedPreferences.Editor editor2 = sharedPreferences_2.edit();
                editor2.putInt("clicks", myUser.getClicks());
                editor2.commit();

                // for currentMoneyIncrease
                SharedPreferences sharedPreferences_3 =
                        getSharedPreferences(SHARED_PREFS, 0);
                SharedPreferences.Editor editor3 = sharedPreferences_3.edit();
                editor3.putInt("money_increase", myUser.getCurrentMoneyIncrease());
                editor3.commit();

                // for currentMoneyPerSecond
                SharedPreferences sharedPreferences_4 =
                        getSharedPreferences(SHARED_PREFS, 0);
                SharedPreferences.Editor editor4 = sharedPreferences_4.edit();
                editor4.putInt("money_per_second", myUser.getCurrentMoneyPerSecond());
                editor4.commit();

                // for currentRefreshTime
                SharedPreferences sharedPreferences_5 =
                        getSharedPreferences(SHARED_PREFS, 0);
                SharedPreferences.Editor editor5 = sharedPreferences_5.edit();
                editor5.putInt("total_money_this_ascension", myUser.getCurrentRefreshTime());
                editor5.commit();

                // for totalMoneyThisAscension
                SharedPreferences sharedPreferences_6 =
                        getSharedPreferences(SHARED_PREFS, 0);
                SharedPreferences.Editor editor6 = sharedPreferences_6.edit();
                editor6.putInt("refresh_time", myUser.getTotalMoneyThisAscension());
                editor6.commit();

                // for goldBars
                SharedPreferences sharedPreferences_7 =
                        getSharedPreferences(SHARED_PREFS, 0);
                SharedPreferences.Editor editor7 = sharedPreferences_7.edit();
                editor7.putInt("gold_bars", myUser.getGoldBars());
                editor7.commit();

                /*This is for the shop*/
                /*==============================================================================*/
                SharedPreferences sharedPreferences_C1 =
                        getSharedPreferences(SHARED_PREFS, 0);
                SharedPreferences.Editor editorC1 = sharedPreferences_C1.edit();
                editorC1.putInt("click0", data[0][0]);
                editorC1.commit();

                SharedPreferences sharedPreferences_CB1 =
                        getSharedPreferences(SHARED_PREFS, 0);
                SharedPreferences.Editor editorCB1 = sharedPreferences_CB1.edit();
                editorCB1.putInt("clickB0", data[1][0]);
                editorCB1.commit();

                SharedPreferences sharedPreferences_S1 =
                        getSharedPreferences(SHARED_PREFS, 0);
                SharedPreferences.Editor editorS1 = sharedPreferences_S1.edit();
                editorS1.putInt("second0", data[2][0]);
                editorS1.commit();

                SharedPreferences sharedPreferences_SB1 =
                        getSharedPreferences(SHARED_PREFS, 0);
                SharedPreferences.Editor editorSB1 = sharedPreferences_SB1.edit();
                editorSB1.putInt("secondB0", data[3][0]);
                editorSB1.commit();


            }
        }, 0, 1200);
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
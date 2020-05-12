package com.example.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class ShopMenu extends AppCompatActivity {

    private Button ascendBtn;
    private Button clickBtns[] = new Button[4];
    private Button secondBtns[] = new Button[4];
    private ImageButton backBtn;

    private TextView clickTextView[] = new TextView[4];
    private TextView secondTextView[] = new TextView[4];
    private TextView ascendTextView;
    private TextView moneyView;

    private User myUser;
    private Shop myShop;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_menu);
        myUser = User.getInstance();
        myShop = Shop.getInstance();

        ascendTextView = (TextView) findViewById(R.id.ascensionTextView);

        backBtn = (ImageButton) findViewById(R.id.backBtn);
        ascendBtn = (Button) findViewById(R.id.ascendBtn);
        moneyView = (TextView) findViewById(R.id.moneyView);

        //TODO - add rest of buttons
        clickBtns[0] = (Button) findViewById(R.id.clickButton1);
        clickTextView[0] = (TextView) findViewById(R.id.clickTextView1);


        //TODO - add rest of buttons
        secondBtns[0] = (Button) findViewById(R.id.secondButton1);
        secondTextView[0] = (TextView) findViewById(R.id.secondTextView1);

        //TODO - modify for loop after adding all buttons
        for(int j = 0 ; j <1; j++){
            final int i = j;
            clickBtns[i].setText(myShop.getClickPrice(i) + "$");
            secondBtns[i].setText(myShop.getSecondPrice(i) + "$");

            clickBtns[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ( myShop.buyClick(i)){
                    clickBtns[i].setText(myShop.getClickPrice(i) + "$");
                    moneyView.setText(myUser.getCurrentMoneyAmount() + "$");
                    Toast.makeText(ShopMenu.this,
                            "+ " + myShop.getClickBonus(i) +"$/click",
                            Toast.LENGTH_LONG).show();
                    }
                        else{
                        Toast.makeText(ShopMenu.this,
                                "Not enough $",
                                Toast.LENGTH_LONG).show();
                    }
                    }
            });

            secondBtns[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ( myShop.buySecond(i)) {
                        secondBtns[i].setText(myShop.getSecondPrice(i) + "$");
                        moneyView.setText(myUser.getCurrentMoneyAmount() + "$");
                        Toast.makeText(ShopMenu.this,
                                "+ " + myShop.getSecondBonus(i) + "$/sec",
                                Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(ShopMenu.this,
                                "Not enough $",
                                Toast.LENGTH_LONG).show();
                    }
                }
            });

            clickTextView[i].setText( "+ " + myShop.getClickBonus(i)+ "$ / click");
            secondTextView[i].setText( "+ " + myShop.getSecondBonus(i) + "$ / second");


        }


        ascendTextView.setText("You will receive " + myUser.getGoldBarIfAscend() + " Gold Bars ");
        moneyView.setText(myUser.getCurrentMoneyAmount() + "$");

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShopMenu.this, Gameplay.class));
            }
        });

        ascendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myUser.ascendUser();
                myShop.resetShop();
                Toast.makeText(ShopMenu.this,
                        "You have ascended", Toast.LENGTH_LONG).show();
                startActivity(new Intent(ShopMenu.this, Gameplay.class));
            }
        });

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                moneyView.setText(myUser.getCurrentMoneyAmount() + "$");
            }
        }, 0, 1000);
    }
}

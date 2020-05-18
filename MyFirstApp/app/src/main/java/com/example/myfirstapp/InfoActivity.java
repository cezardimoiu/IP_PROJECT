package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    private TextView moneyText;
    private TextView goldBarsText;
    private TextView clicksText;
    private TextView Sec;
    private TextView moneyClick;
    private ImageView backBtn;
    private User myUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        myUser = User.getInstance();

        backBtn       = (ImageView) findViewById(R.id.backBtn);
        moneyText     = (TextView) findViewById(R.id.moneyText);
        goldBarsText  = (TextView) findViewById(R.id.goldBarsText);
        clicksText    = (TextView) findViewById(R.id.clicksText);
        Sec           = (TextView) findViewById(R.id.Sec);
        moneyClick    = (TextView) findViewById(R.id.moneyC);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InfoActivity.this, SettingMenu.class));
            }
        });
        moneyText.setText("Your total ever money amount is: " + myUser.getTotalMoneyEver() + "$");
        clicksText.setText("Your current number of clicks is: " + myUser.getClicks());
        goldBarsText.setText("Your current number of gold bars is: " + myUser.getGoldBars());
        Sec.setText("Your current money/sec value is: " + myUser.getCurrentMoneyIncrease());
        moneyClick.setText("Your current money/click is: " + myUser.getMoneyPerClick());
    }
}

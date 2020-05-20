package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LeaderboardActivity extends AppCompatActivity {

    private TextView listTextView[] = new TextView[5];

    private TextView currentPlace;

    private Leaderboard leaderboard = Leaderboard.getInstance();
    private User user = User.getInstance();
    private ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        List <Map.Entry<String, Integer>> list = Leaderboard.sort(leaderboard.mapleader);

        listTextView[0] = (TextView) findViewById(R.id.firstPlace);
        listTextView[1] = (TextView) findViewById(R.id.secondPlace);
        listTextView[2] = (TextView) findViewById(R.id.thirdPlace);
        listTextView[3] = (TextView) findViewById(R.id.forthPlace);
        listTextView[4] = (TextView) findViewById(R.id.fifthPlace);
        currentPlace = (TextView) findViewById(R.id.currentPlace);

        backBtn = (ImageView) findViewById(R.id.backBtn);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LeaderboardActivity.this, SettingMenu.class));
            }
        });

        if (user != null || user.getEmail() != null) {
            String username = user.getEmail().split("@")[0];

            for (int i = 0; i < 5; i++) {
                if (list.size() > i) {
                    if (list.get(i).getKey().equals(username))
                        listTextView[i].setText(i + 1 + ". You - " + list.get(i).getValue() + "$");
                    else
                        listTextView[i].setText(i + 1 + "." + list.get(i).getKey() + " - " + list.get(i).getValue() + "$");
                }
                else
                    listTextView[i].setText("-");
            }
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getKey().equals(username)) {
                    currentPlace.setText(i + 1 + ". You - " + list.get(i).getValue() + "$");
                }
            }
        }
    }
}

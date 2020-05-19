package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LeaderboardActivity extends AppCompatActivity {

    private TextView firstPlace;
    private TextView secondPlace;
    private TextView thirdPlace;
    private TextView forthPlace;
    private TextView fifthPlace;
    private TextView currentPlace;

    private Leaderboard leaderboard = Leaderboard.getInstance();
    private User user = User.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        List <Map.Entry<String, Integer>> list = Leaderboard.sort(leaderboard.mapleader);

        firstPlace = (TextView) findViewById(R.id.firstPlace);
        secondPlace = (TextView) findViewById(R.id.secondPlace);
        thirdPlace = (TextView) findViewById(R.id.thirdPlace);
        forthPlace = (TextView) findViewById(R.id.forthPlace);
        fifthPlace = (TextView) findViewById(R.id.fifthPlace);
        currentPlace = (TextView) findViewById(R.id.currentPlace);

        if (list.size() > 0)
            firstPlace.setText("1." + list.get(0).getKey() + " - " + list.get(0).getValue() + "$");
        else
            firstPlace.setText("-");
        if (list.size() > 1)
            secondPlace.setText("2." + list.get(1).getKey() + " - " + list.get(1).getValue() + "$");
        else
            secondPlace.setText("-");
        if (list.size() > 2)
            thirdPlace.setText("3." + list.get(2).getKey() + " - " + list.get(2).getValue() + "$");
        else
            thirdPlace.setText("-");
        if (list.size() > 3)
            forthPlace.setText("4." + list.get(3).getKey() + " - " + list.get(3).getValue() + "$");
        else
            forthPlace.setText("-");
        if (list.size() > 4)
            fifthPlace.setText("5." + list.get(4).getKey() + " - " + list.get(4).getValue() + "$");
        else
            fifthPlace.setText("-");

        String username = user.getEmail().split("@")[0];
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getKey().equals(username)) {
                currentPlace.setText(i + 1 + ". You - " + list.get(i).getValue() + "$");
            }
        }
    }
}

package com.example.myfirstapp;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Leaderboard {
    private static Leaderboard single_instance = null;

    HashMap<String, Integer> leaderboard;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference ref = database.getReference("leaderboard");

    public Leaderboard() {
        leaderboard = new HashMap<>();
    }

    public static HashMap<String, Integer> sort(HashMap<String, Integer> hm)
    {
        List<Map.Entry<String, Integer> > list = new LinkedList<Map.Entry<String, Integer> >(hm.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        HashMap<String, Integer> temp = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    public static Leaderboard getInstance() {
        if (single_instance == null) {
            single_instance = new Leaderboard();
        }
        return single_instance;
    }

    public void addUser(User user)
    {
        String username = user.getEmail().split("@")[0];
        ref.child(username).setValue(user.getTotalMoneyEver());
        leaderboard.put(username, user.getTotalMoneyEver());
    }
}

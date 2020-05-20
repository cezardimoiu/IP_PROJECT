package com.example.myfirstapp;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Leaderboard {
    private static Leaderboard single_instance = null;

    public HashMap<String, Integer> mapleader;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference ref = database.getReference("leaderboard");

    public Leaderboard() {
        mapleader = new HashMap<>();
    }

    public static List<Map.Entry<String, Integer>> sort(HashMap<String, Integer> hm)
    {
        System.out.println(hm);
        List<Map.Entry<String, Integer> > list = new LinkedList<Map.Entry<String, Integer> >(hm.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o2, Map.Entry<String, Integer> o1) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });
        return list;
    }

    public static Leaderboard getInstance() {
        if (single_instance == null) {
            single_instance = new Leaderboard();
        }
        return single_instance;
    }

    public void getDataFromDatabase(DataSnapshot dataSnapshot)
    {
        for (DataSnapshot ent : dataSnapshot.getChildren()) {
            String username = ent.getKey();
            int value = ((Long)ent.getValue()).intValue();
            mapleader.put(username, value);
        }
    }

    public void addUser(User user)
    {
        if (user.getEmail() != null) {
            String username = user.getEmail().split("@")[0];
            ref.child(username).setValue(user.getTotalMoneyEver());
            mapleader.put(username, user.getTotalMoneyEver());
        }
    }

}

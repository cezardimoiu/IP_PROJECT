package com.example.myfirstapp;

import com.google.firebase.database.DataSnapshot;

import java.util.Timer;
import java.util.TimerTask;

class User {
    private static User single_instance = null;

    private int currentMoneyAmount;
    private int totalMoneyThisAscension;
    private int currentMoneyIncrease;
    private int currentMoneyPerSecond;
    private int currentRefreshTime; //in ms
    private String email;
    private int clicks;
    private Timer timer;
    private int goldBars;
    private final double goldBarsBonus = 1.0f;

    private User()
    {
        this.clicks = 0;
        this.currentMoneyAmount = 0;
        this.currentMoneyIncrease = 1;
        this.currentMoneyPerSecond = 0;
        this.currentRefreshTime = 1000;
        this.totalMoneyThisAscension = 0;
        this.goldBars = 0;

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                currentMoneyAmount += currentMoneyPerSecond +
                        currentMoneyPerSecond * (int)(goldBars * goldBarsBonus);
                totalMoneyThisAscension += currentMoneyPerSecond +
                        currentMoneyPerSecond * (int)(goldBars * goldBarsBonus);
                //moneyText.setText(currentMoneyAmount + "$");
            }
        }, 0, currentRefreshTime);

    }

    public void resetUser()
    {
        this.email = null;
        this.clicks = 0;
        this.currentMoneyAmount = 0;
        this.currentMoneyIncrease = 1;
        this.currentMoneyPerSecond = 0;
        this.currentRefreshTime = 1000;
        this.totalMoneyThisAscension = 0;
        this.goldBars = 0;
    }

    public static User getInstance(){
        if (single_instance == null)
            single_instance = new User();

        return single_instance;
    }

    public String getEmail()
    {
        return this.email;
    }

    public int[] getAllUserInfo()
    {
        int rez [] = {goldBars, totalMoneyThisAscension, clicks, currentMoneyIncrease,
        currentMoneyAmount, currentMoneyPerSecond, currentRefreshTime};
        return rez;
    }

    public int getGoldBars()
    {
        return this.goldBars;
    }

    public int getTotalMoneyThisAscension()
    {
        return this.totalMoneyThisAscension;
    }

    public int getClicks()
    {
        return this.clicks;
    }

    public int getCurrentMoneyAmount()
    {
        return this.currentMoneyAmount;
    }

    public int getCurrentMoneyIncrease()
    {
        return this.currentMoneyIncrease;
    }

    public int getCurrentMoneyPerSecond()
    {
        return this.currentMoneyPerSecond;
    }

    public int getCurrentRefreshTime()
    {
        return this.currentRefreshTime;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setClicks(int clicks)
    {
        this.clicks = clicks;
    }

    public void setCurrentMoneyAmount(int currentMoneyAmount)
    {
        this.currentMoneyAmount = currentMoneyAmount;
    }

    public void setCurrentMoneyIncrease(int currentMoneyIncrease)
    {
        this.currentMoneyIncrease = currentMoneyIncrease;
    }

    public void setCurrentMoneyPerSecond(int currentMoneyPerSecond)
    {
        this.currentMoneyPerSecond = currentMoneyPerSecond;
    }

    public void setCurrentRefreshTime(int currentRefreshTime)
    {
        this.currentRefreshTime = currentRefreshTime;
    }

    public void setGoldBars(int goldBars)
    {
        this.goldBars = goldBars;
    }

    public void setTotalMoneyThisAscension(int totalMoneyThisAscension)
    {
        this.totalMoneyThisAscension = totalMoneyThisAscension;
    }

    public void setUser(String email, int data[])
    {
        this.email = email;
        this.goldBars = data[0];
        this.totalMoneyThisAscension = data[1];
        this.clicks = data[2];
        this.currentRefreshTime = data[3];
        this.currentMoneyIncrease = data[4];
        this.currentMoneyAmount = data[5];
        this.currentMoneyPerSecond = data[6];
    }


    public void addMoney()
    {
        this.currentMoneyAmount += this.currentMoneyIncrease +
                this.currentMoneyIncrease * (int)(goldBars * goldBarsBonus);
        this.totalMoneyThisAscension += this.currentMoneyIncrease +
                this.currentMoneyIncrease * (int)(goldBars * goldBarsBonus);
        this.clicks ++;
    }

    public void ascendUser()
    {
        this.currentMoneyAmount = 0;
        this.goldBars += this.totalMoneyThisAscension/(this.goldBars/10 + 100);
        this.totalMoneyThisAscension = 0;
        this.currentRefreshTime = 0;
        this.currentMoneyPerSecond = 1;

    }

    public int getMoneyPerSecond()
    {
        return currentMoneyPerSecond +
                currentMoneyPerSecond * (int)(goldBars * goldBarsBonus);
    }

    public int getMoneyPerClick()
    {
        return this.currentMoneyIncrease +
                this.currentMoneyIncrease * (int)(goldBars * goldBarsBonus);
    }

    public int getGoldBarIfAscend()
    {
        return this.totalMoneyThisAscension/(this.goldBars/5 + 100);
    }

    public void increaseClick(int inc)
    {
        this.currentMoneyIncrease += inc;
    }

    public void increaseSecond(int inc)
    {
        this.currentMoneyPerSecond += inc;
    }

    public boolean purchaseUpgrade(int cost)
    {
        if ( currentMoneyAmount >= cost){
            currentMoneyAmount -= cost;
            return true;
        }
        return false;
    }

    public void getDataFromDatabase(DataSnapshot dataSnapshot) {
        User user = User.getInstance();
        user.setClicks(((Long)dataSnapshot.child("clicks").getValue()).intValue());
        user.setCurrentMoneyAmount(((Long)dataSnapshot.child("currentMoneyAmount").getValue()).intValue());
        user.setCurrentMoneyIncrease(((Long)dataSnapshot.child("currentMoneyIncrease").getValue()).intValue());
        user.setCurrentMoneyPerSecond(((Long)dataSnapshot.child("currentMoneyPerSecond").getValue()).intValue());
        user.setGoldBars(((Long)dataSnapshot.child("goldBars").getValue()).intValue());
        user.setTotalMoneyThisAscension(((Long)dataSnapshot.child("totalMoneyThisAscension").getValue()).intValue());
    }

}

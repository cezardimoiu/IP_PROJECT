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
    private int totalMoneyEver;
    private String email;
    private int clicks;
    private Timer timer;
    private Timer timer2;
    private int goldBars;
    private int powerClickDuration;
    private int powerSecondDuration;
    private int powerClickCooldown;
    private int powerSecondCooldown;
    private final double goldBarsBonus = 0.1f;
    private final int powerDuration = 60;
    private final int powerCooldown = 300;
    private float volume = 1.0f;



    private User()
    {
        this.clicks = 0;
        this.currentMoneyAmount = 0;
        this.currentMoneyIncrease = 1;
        this.currentMoneyPerSecond = 0;
        this.currentRefreshTime = 1000;
        this.totalMoneyThisAscension = 0;
        this.goldBars = 0;
        this.powerClickDuration = 0;
        this.powerSecondDuration = 0;
        this.totalMoneyEver = 0;
        this.powerClickCooldown = 0;
        this.powerSecondCooldown = 0;

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                currentMoneyAmount += getMoneyPerSecond();
                totalMoneyThisAscension += getMoneyPerSecond();
                totalMoneyEver += getMoneyPerSecond();

                //moneyText.setText(currentMoneyAmount + "$");
            }
        }, 0, currentRefreshTime);

        timer2 = new Timer();
        timer2.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if(powerClickDuration > 0)
                    powerClickDuration --;

                if(powerSecondDuration > 0)
                    powerSecondDuration --;

                if(powerSecondCooldown > 0)
                    powerSecondCooldown --;

                if(powerClickCooldown > 0)
                    powerClickCooldown --;

            }
        }, 0, 1000);

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
        this.totalMoneyEver = 0;
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

    public int getTotalMoneyEver()
    {
        return this.totalMoneyEver;
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

    public void setTotalMoneyEver(int totalMoneyEver)
    {
        this.totalMoneyEver = totalMoneyEver;
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
        int buffer = this.getMoneyPerClick();
        this.currentMoneyAmount += buffer;
        this.totalMoneyThisAscension += buffer;
        this.totalMoneyEver += buffer;
        this.clicks ++;
    }

    public void ascendUser()
    {
        this.currentMoneyAmount = 0;
        this.goldBars += this.totalMoneyThisAscension/(this.goldBars/5 + 100);
        this.totalMoneyThisAscension = 0;
        this.currentRefreshTime = 0;
        this.currentMoneyPerSecond = 0;
        this.currentMoneyIncrease = 1;
    }

    public int getMoneyPerSecond()
    {
        int buff = currentMoneyPerSecond +
                (int)(currentMoneyPerSecond * goldBars * goldBarsBonus);
        if ( powerSecondDuration > 0)
            return 2 * buff;
        return buff;
    }

    public int getMoneyPerClick()
    {
        int buff = this.currentMoneyIncrease +
                (int)(this.currentMoneyIncrease * goldBars * goldBarsBonus);

        if(powerClickDuration > 0)
            return 2 * buff;
        return buff;
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
        user.setTotalMoneyEver(((Long)dataSnapshot.child("totalMoneyEver").getValue()).intValue());
    }

    public void setPowerClickDuration(){
        this.powerClickDuration = this.powerDuration;
        this.powerClickCooldown = this.powerCooldown;
    }

    public void setPowerSecondDuration(){
        this.powerSecondDuration = this.powerDuration;
        this.powerSecondCooldown = this.powerCooldown;
    }

    public boolean isSecondCooldown(){
        if ( this.powerSecondCooldown > 0)
            return true;
        return false;
    }

    public boolean isClickCooldown(){
        if ( this.powerClickCooldown > 0)
            return true;
        return false;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public float getVolume() {
        return this.volume;
    }

}

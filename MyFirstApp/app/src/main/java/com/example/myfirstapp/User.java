package com.example.myfirstapp;

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

    private User()
    {
        this.clicks = 0;
        this.currentMoneyAmount = 0;
        this.currentMoneyIncrease = 1;
        this.currentMoneyPerSecond = 1;
        this.currentRefreshTime = 1000;
        this.totalMoneyThisAscension = 0;
        this.goldBars = 0;

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                currentMoneyAmount += currentMoneyPerSecond ;
                //moneyText.setText(currentMoneyAmount + "$");
            }
        }, 0, currentRefreshTime);

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
        this.currentMoneyAmount += this.currentMoneyIncrease + (int)(goldBars * 0.1f);
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

}

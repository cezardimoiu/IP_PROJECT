package com.example.myfirstapp;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.util.SharedPreferencesUtils;


public class DataManipulator{
    private Shop myShop;
    private User myUser;
    private static DataManipulator single_instance = null;
    private static final String SHARED_PREFS =  "Prefs";
    private int data[][];


    private DataManipulator()
    {
        this.myShop = Shop.getInstance();
        this.myUser = User.getInstance();

        //TODO - add file for reading/ writing;
    }

    public static DataManipulator getInstance(){
        if (single_instance == null)
            single_instance = new DataManipulator();

        return single_instance;
    }

    public void writeInfo()
    {
        int userData [] = myUser.getAllUserInfo();
        int shopData [][] = myShop.getAllShopInfo();

        //TODO - write all data to file
    }

    public void readInfo()
    {
        int shopData [][];
        int userData [];
        String email;

        //TODO - read from file


        //myUser.setUser(email, userData);
        //myShop.setShop(shopData);
    }

    public void getSharedPrefs(SharedPreferences prefs)
    {
        /* This is for the user */
        /*==============================================================================*/

        // for coins
        SharedPreferences sharedPreferences_1 = prefs;
        myUser.setCurrentMoneyAmount(sharedPreferences_1.getInt
                ("coins", myUser.getCurrentMoneyAmount()));

        // for clicks
        SharedPreferences sharedPreferences_2 = prefs;
        myUser.setClicks(sharedPreferences_2.getInt
                ("clicks", myUser.getClicks()));

        // for currentMoneyIncrease
        SharedPreferences sharedPreferences_3 = prefs;
        myUser.setCurrentMoneyIncrease(sharedPreferences_3.getInt
                ("money_increase", myUser.getCurrentMoneyIncrease()));

        // for currentMoneyPerSecond
        SharedPreferences sharedPreferences_4 = prefs;
        myUser.setCurrentMoneyPerSecond(sharedPreferences_4.getInt
                ("money_per_second", myUser.getCurrentMoneyPerSecond()));

        // for currentRefreshTime
        SharedPreferences sharedPreferences_5 = prefs;
        myUser.setCurrentRefreshTime(sharedPreferences_5.getInt
                ("refresh_time", myUser.getCurrentRefreshTime()));

        //for totakMoneyThisAscension
        SharedPreferences sharedPreferences_6 = prefs;
        myUser.setTotalMoneyThisAscension(sharedPreferences_6.getInt
                ("total_money_this_ascension", myUser.getTotalMoneyThisAscension()));

        // for goldBars
        SharedPreferences sharedPreferences_7 = prefs;
        myUser.setGoldBars(sharedPreferences_7.getInt
                ("gold_bars", myUser.getGoldBars()));
        /*==============================================================================*/


        /*This is for the shop*/
        /* In case Alex decides to use al of the values from the Shop(clicks, prices...)
         *  I will add the commented block of code !!!!!*/
        //Thanks ROBERT <3
        /*==============================================================================*/


        // for pricesClicks
        data = myShop.getAllShopInfo();
        SharedPreferences sharedPreferencesC0 = prefs;
        data[0][0] = sharedPreferencesC0.getInt("click0", data[0][0]);
       /* SharedPreferences sharedPreferencesC1 = prefs;
        data[0][1] = sharedPreferencesC1.getInt("click1", data[0][1]);
        SharedPreferences sharedPreferencesC2 = prefs;
        data[0][2] = sharedPreferencesC2.getInt("click2", data[0][2]);
        SharedPreferences sharedPreferencesC3 = prefs;
        data[0][3] = sharedPreferencesC3.getInt("click3", data[0][3]);*/

        // for clickBonus
        SharedPreferences sharedPreferencesCB0 = prefs;
        data[1][0] = sharedPreferencesCB0.getInt("clickB0", data[1][0]);
        /*SharedPreferences sharedPreferencesCB1 = prefs;
        data[1][1] = sharedPreferencesCB1.getInt("clickB1", data[1][1]);
        SharedPreferences sharedPreferencesCB2 = prefs;
        data[1][2] = sharedPreferencesCB2.getInt("clickB2", data[1][2]);
        SharedPreferences sharedPreferencesCB3 = prefs;
        data[1][3] = sharedPreferencesCB3.getInt("clickB3", data[1][3]);*/

        // for pricesSecond
        SharedPreferences sharedPreferencesS0 = prefs;
        data[2][0] = sharedPreferencesS0.getInt("second0", data[2][0]);
        /*SharedPreferences sharedPreferencesS1 = prefs;
        data[2][1] = sharedPreferencesS1.getInt("second1", data[2][1]);
        SharedPreferences sharedPreferencesS2 = prefs;
        data[2][2] = sharedPreferencesS2.getInt("second2", data[2][2]);
        SharedPreferences sharedPreferencesS3 = prefs;
        data[2][3] = sharedPreferencesS3.getInt("second3", data[2][3]);*/

        // for secondBonus
        SharedPreferences sharedPreferencesSB0 = prefs;
        data[3][0] = sharedPreferencesSB0.getInt("secondB0", data[3][0]);
        /*SharedPreferences sharedPreferencesSB1 = prefs;
        data[3][1] = sharedPreferencesSB1.getInt("secondB1", data[3][1]);
        SharedPreferences sharedPreferencesSB2 = prefs;
        data[3][2] = sharedPreferencesSB2.getInt("secondB2", data[3][2]);
        SharedPreferences sharedPreferencesSB3 = prefs;
        data[3][3] = sharedPreferencesSB3.getInt("secondB3", data[3][3]);*/
        myShop.setShop(data);

        /*==============================================================================*/
    }

    public void setSharedPrefs(SharedPreferences prefs)
    {
        // for coins
        SharedPreferences sharedPreferences_1 =
                prefs;
        SharedPreferences.Editor editor1 = sharedPreferences_1.edit();
        editor1.putInt("coins", myUser.getCurrentMoneyAmount());
        editor1.commit();

        // for clicks
        SharedPreferences sharedPreferences_2 =
                prefs;
        SharedPreferences.Editor editor2 = sharedPreferences_2.edit();
        editor2.putInt("clicks", myUser.getClicks());
        editor2.commit();

        // for currentMoneyIncrease
        SharedPreferences sharedPreferences_3 =
                prefs;
        SharedPreferences.Editor editor3 = sharedPreferences_3.edit();
        editor3.putInt("money_increase", myUser.getCurrentMoneyIncrease());
        editor3.commit();

        // for currentMoneyPerSecond
        SharedPreferences sharedPreferences_4 =
                prefs;
        SharedPreferences.Editor editor4 = sharedPreferences_4.edit();
        editor4.putInt("money_per_second", myUser.getCurrentMoneyPerSecond());
        editor4.commit();

        // for currentRefreshTime
        SharedPreferences sharedPreferences_5 =
                prefs;
        SharedPreferences.Editor editor5 = sharedPreferences_5.edit();
        editor5.putInt("total_money_this_ascension", myUser.getCurrentRefreshTime());
        editor5.commit();

        // for totalMoneyThisAscension
        SharedPreferences sharedPreferences_6 =
                prefs;
        SharedPreferences.Editor editor6 = sharedPreferences_6.edit();
        editor6.putInt("refresh_time", myUser.getTotalMoneyThisAscension());
        editor6.commit();

        // for goldBars
        SharedPreferences sharedPreferences_7 =
                prefs;
        SharedPreferences.Editor editor7 = sharedPreferences_7.edit();
        editor7.putInt("gold_bars", myUser.getGoldBars());
        editor7.commit();

        /*This is for the shop*/
        /*==============================================================================*/
        SharedPreferences sharedPreferences_C1 =
                prefs;
        SharedPreferences.Editor editorC1 = sharedPreferences_C1.edit();
        editorC1.putInt("click0", data[0][0]);
        editorC1.commit();

        SharedPreferences sharedPreferences_CB1 =
                prefs;
        SharedPreferences.Editor editorCB1 = sharedPreferences_CB1.edit();
        editorCB1.putInt("clickB0", data[1][0]);
        editorCB1.commit();

        SharedPreferences sharedPreferences_S1 =
                prefs;
        SharedPreferences.Editor editorS1 = sharedPreferences_S1.edit();
        editorS1.putInt("second0", data[2][0]);
        editorS1.commit();

        SharedPreferences sharedPreferences_SB1 =
                prefs;
        SharedPreferences.Editor editorSB1 = sharedPreferences_SB1.edit();
        editorSB1.putInt("secondB0", data[3][0]);
        editorSB1.commit();
    }


}

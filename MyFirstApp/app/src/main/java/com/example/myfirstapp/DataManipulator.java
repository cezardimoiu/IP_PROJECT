package com.example.myfirstapp;

public class DataManipulator {

    private Shop myShop;
    private User myUser;
    private static DataManipulator single_instance = null;

    private DataManipulator()
    {
        this.myShop = Shop.getInstance();
        this.myUser = User.getInstance();
        //add file for reading/ writing;
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

        //write all data
    }

    public void readInfo()
    {
        int shopData [][];
        int userData [];
        String email;

        //read from file


        myUser.setUser(email, userData);
        myShop.setShop(shopData);
    }



}

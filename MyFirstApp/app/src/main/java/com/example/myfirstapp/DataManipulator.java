package com.example.myfirstapp;

public class DataManipulator {

    private Shop myShop;
    private User myUser;
    private static DataManipulator single_instance = null;

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


        //setUser(email, userData);
        //myShop.setShop(shopData);
    }



}

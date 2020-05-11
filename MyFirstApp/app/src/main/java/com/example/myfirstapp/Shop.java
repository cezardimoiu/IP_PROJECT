package com.example.myfirstapp;

class Shop {
    private static Shop single_instance = null;
    private final double percentageIncrease = 1.15;
    private int pricesClick[];
    private int clickBonus[];
    private int pricesSecond[];
    private int secondBonus[];
    private User myUser;

    private Shop()
    {
        myUser = User.getInstance();
        pricesClick = new int[]{10, 500, 2500, 10000};
        clickBonus = new int[]{1, 5, 20, 100};
        pricesSecond = new int[]{50, 1000, 5000, 20000};
        secondBonus = new int[]{1, 5, 20, 100};

    }

    public static Shop getInstance(){
        if (single_instance == null)
            single_instance = new Shop();

        return single_instance;
    }

    public void setShop(int data[][]){
        this.pricesClick = data[0];
        this.clickBonus = data[1];
        this.pricesSecond = data[2];
        this.secondBonus = data[3];
    }

    public int[][] getAllShopInfo()
    {
        int rez[][] = new int [4][4];
        rez[0] = this.pricesClick;
        rez[1] = this.clickBonus;
        rez[2] = this.pricesSecond;
        rez[3] = this.secondBonus;
        return rez;
    }

    public int getClickPrice(int index)
    {
        return this.pricesClick[index];
    }

    public int getSecondPrice(int index)
    {
        return this.pricesSecond[index];
    }

    public int getClickBonus(int index)
    {
        return this.clickBonus[index];
    }

    public int getSecondBonus(int index)
    {
        return this.secondBonus[index];
    }

    public double getPercentageIncrease()
    {
        return this.percentageIncrease;
    }

    public boolean buyClick(int index)
    {
        if (myUser.purchaseUpgrade(pricesClick[index])){
            myUser.increaseClick(clickBonus[index]);
            pricesClick[index] = (int)(pricesClick[index] * percentageIncrease);
            return true;
        }
        return false;
    }

    public boolean buySecond(int index)
    {
        if (myUser.purchaseUpgrade(pricesSecond[index])){
            myUser.increaseSecond(secondBonus[index]);
            pricesSecond[index] = (int)(pricesSecond[index] * percentageIncrease);
            return true;
        }
        return false;
    }
}

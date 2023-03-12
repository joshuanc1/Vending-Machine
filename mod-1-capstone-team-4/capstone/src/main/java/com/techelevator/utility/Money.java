package com.techelevator.utility;


public class Money {
    //Variables

    private final int QUARTER = 25;
    private final int DIME = 10;
    private final int NICKEL = 5;

    //Constructor
    public Money(){}


    //Method
    //Return customer change
    public void returnChange (double remainingMoney ){
        int numNickels = 0;
        int numDimes = 0;
        int numQuarters = 0;
        int convertRemainingMoney = (int)Math.round(remainingMoney*100);

        while (convertRemainingMoney - QUARTER >= 0){
            numQuarters++;
            convertRemainingMoney = convertRemainingMoney - QUARTER;
        }
        while (convertRemainingMoney - DIME >= 0){
            numDimes++;
            convertRemainingMoney = convertRemainingMoney - DIME;
        }
        while (convertRemainingMoney - NICKEL >= 0){
            numNickels++;
            convertRemainingMoney = convertRemainingMoney - NICKEL;
        }
        System.out.println("*********************************");
        System.out.println("Thank you. Please take your change. Have a great day!");
        System.out.println("Returned Change: " + "Quarters: " + numQuarters + " Dimes: " + numDimes  + " Nickels: " + numNickels);


    }

    }









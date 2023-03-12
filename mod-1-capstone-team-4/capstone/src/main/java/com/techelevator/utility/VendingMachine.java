package com.techelevator.utility;
import java.text.DecimalFormat;



public class VendingMachine {

    //Parameter
    private double feedCurrentMoney = 0.00;
    private double customersTotalMoney = 0.00;
    private double feedMoneyForLog = 0.00;
    private double itemPriceForLog = 0.00;
    private String itemNameAndKeyForLog = "";
    private double formattedTotalMoney = 0.00;


    private DecimalFormat df = new DecimalFormat();
    private Inventory dispenseInventory;
    private Money coinBox;
    private Beverages bevs;
    private Gum gum;
    private Chips chip;
    private Candy candy;
    private Log currentLog;



    //Getters & Setters
    public double getFeedCurrentMoney() {
        return feedCurrentMoney;
    }

    public double getCustomersTotalMoney() {
        return customersTotalMoney;
    }

    public Inventory getDispenseInventory() {
        return dispenseInventory;
    }

    public Money getCoinBox() {
        return coinBox;
    }

    public double getFeedMoneyForLog() {
        return feedMoneyForLog;
    }

    public double getItemPriceForLog() {
        return itemPriceForLog;
    }

    public String getItemNameAndKeyForLog() {
        return itemNameAndKeyForLog;
    }

    public double getFormattedTotalMoney() {
        return formattedTotalMoney;
    }

    //Constructor

    public VendingMachine(){
        dispenseInventory = new Inventory();
        coinBox = new Money();
        currentLog = new Log();

        bevs = new Beverages();
        gum = new Gum();
        chip = new Chips();
        candy = new Candy();

    }

    public VendingMachine(double feedCurrentMoney, double customersTotalMoney){
        this.feedCurrentMoney = feedCurrentMoney;
        this.customersTotalMoney = customersTotalMoney;
    }


    //Method

    //Calculates Customer's Total Money After Each Time Money Is Fed, & assigns variable for logging fed money
    public double feedMoneyUpdateTotal(String moneyInserted) {
        this.customersTotalMoney += (this.feedCurrentMoney + Double.valueOf(moneyInserted));
        this.customersTotalMoney = Double.valueOf(df.format(customersTotalMoney));
        feedMoneyForLog = Double.valueOf(moneyInserted);
        formattedTotalMoney = Double.valueOf(df.format(customersTotalMoney));
        return this.customersTotalMoney;

    }


    //Traverses Map to find item and dispense with category message, & assigns log variables: price and name and key;
    public void dispenseItem(String keySelected) {

        int counter = 0;

        dispenseInventory.getItemMap();
        for (String itemKey : dispenseInventory.getItemMap().keySet()) {
            if (itemKey.equalsIgnoreCase(keySelected)) {

                double condensedGetPrice = dispenseInventory.getItemMap().get(itemKey).get(0).getPrice();
                String condensedGetName = dispenseInventory.getItemMap().get(itemKey).get(0).getName();
                String condensedGetCatergory = dispenseInventory.getItemMap().get(itemKey).get(0).getCategory();

                if (customersTotalMoney < condensedGetPrice) {
                    System.out.println("********************************");
                    System.out.println("Sorry, insufficient funds");
                    System.out.println("********************************");
                } else {

                    itemPriceForLog = condensedGetPrice; //Get item price for log
                    itemNameAndKeyForLog = condensedGetName + " " + itemKey;
                    int currentStock = dispenseInventory.getItemMap().get(itemKey).size();

                    if (currentStock == 1) {
                        System.out.println("Sorry, This Item Is Sold Out");
                    } else {
                        String specificItemCost = String.valueOf(condensedGetPrice);

                        System.out.printf("Name: " + condensedGetName + " | ");
                        System.out.printf(" Cost: $" + Double.valueOf(specificItemCost) + " | ");

                        this.feedCurrentMoney -= Double.parseDouble(specificItemCost);
                        this.customersTotalMoney += this.feedCurrentMoney;
                        System.out.println(" Money Remaining: $" + Double.valueOf(df.format(customersTotalMoney))); //this.customersTotalMoney);
                        this.feedCurrentMoney = 0.00;

                        dispenseInventory.getItemMap().get(itemKey).remove(0);

                        formattedTotalMoney = Double.valueOf(df.format(customersTotalMoney));

                        if (condensedGetCatergory.equals("Chip")) {
                            chip.message();
                        } else if (condensedGetCatergory.equals("Candy")) {
                            candy.message();
                        } else if (condensedGetCatergory.equals("Drink")) {
                            bevs.message();
                        } else {
                            gum.message();
                        }
                    }
                }
            }
            else {
                counter++;
            }
        }
        if (counter == 16){
            System.out.println("Sorry The Key You Entered Is Invalid");
        }
    }

        // Prints Item Menu
        public void printItemMenu (){
            for (String itemKey : dispenseInventory.getItemMap().keySet()) {
                double condensedGetPrice = dispenseInventory.getItemMap().get(itemKey).get(0).getPrice();
                String condensedGetName = dispenseInventory.getItemMap().get(itemKey).get(0).getName();
                String condensedGetCatergory = dispenseInventory.getItemMap().get(itemKey).get(0).getCategory();


               System.out.println(itemKey + "  | " + condensedGetName + " | " + condensedGetPrice + " | " + condensedGetCatergory + " | " + (dispenseInventory.getItemMap().get(itemKey).size()-1));
        }
    }



    // Resets Customer Money after Finish Transaction
    public void resetCustomerMoney (){
        this.customersTotalMoney = 0.00;
    }


}






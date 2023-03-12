package com.techelevator.utility;

public class Beverages extends Item{

    public Beverages(String name, int price){
        super(name, price);
    }
    public Beverages(){
        super();
    }


    //Print category message
    public void message(){
        System.out.println("Glug Glug, Yum!");
    }

}

package com.techelevator.utility;

public class Chips extends Item{

    public Chips(String name, int price){
        super(name, price);
    }
    public Chips(){
        super();
    }

    //Print category message
    public void message(){
        System.out.println("Crunch Crunch, Yum!");
    }
}

package com.techelevator.utility;

public class Candy extends Item{

    public Candy(String name, int price){
        super(name, price);
    }
    public Candy(){
        super();
    }

    //Print category message
    public void message(){
        System.out.println("Munch Munch, Yum!");
    }
}
